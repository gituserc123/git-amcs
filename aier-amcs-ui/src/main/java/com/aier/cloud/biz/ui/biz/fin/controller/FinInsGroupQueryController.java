package com.aier.cloud.biz.ui.biz.fin.controller;

import cn.hutool.json.JSONUtil;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsGroupQuery")
public class FinInsGroupQueryController  extends BaseController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HospHandleService hospHandleService;

    private static final String FIN_GROUP_QUERY_LIST="amcs/fin/business/FinGroupQueryList";

    @RequestMapping(value = "/queryPage")
    public String listPage(HttpServletRequest request){
        return FIN_GROUP_QUERY_LIST;
    }

    @RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> tree(){
        List<Map<String, Object>> modules = new ArrayList<>();
        List<Institution> list = institutionService.getListByInstType(InstEnum.PROVINCE);
        if (CollectionUtils.isEmpty(list)) {
            return modules;
        }

        list.forEach(inst -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", inst.getName());

            map.put("treepath", inst.getTreePaths());
            map.put("id", inst.getId());
            map.put("pid", inst.getParentId());
            modules.add(map);
            Object o=redisService.get("AE:SUB_HOSP:"+inst.getId());
            if(ObjectUtils.isEmpty(o)){
                o=hospHandleService.allHospFromParent(inst.getId());
                redisService.set("AE:SUB_HOSP:"+inst.getId(),o, TimeUnit.DAYS.toSeconds(1));
            }
            if(Objects.nonNull(o)){
                JSONArray arrHosp = (JSONArray)o;
                arrHosp.stream().forEach(hp -> {
                    JSONObject obj = (JSONObject)hp;
                    Map<String, Object> subMap = new HashMap<>();
                    subMap.put("name", obj.get("name"));
                    subMap.put("pid", inst.getId());
                    subMap.put("treepath", "0," + inst.getId());
                    subMap.put("id", obj.get("id"));
                    subMap.put("hospId", obj.get("ahisHosp"));
                    modules.add(subMap);
                });
            }
        });
        Map<String, Object> allMap = Maps.newHashMap();
        allMap.put("name", "全部");
        allMap.put("treepath", JSONUtil.createArray());
        allMap.put("id", 99);
        allMap.put("pid", 0);
        modules.add(0,allMap);
        return modules;
    }
}
