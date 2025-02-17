package com.aier.cloud.biz.ui.biz.adverse.service;

import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-03 15:15
 **/
@Service
public class HospHandleService {

    @Autowired
    private InstitutionService institutionService;

    /**
     * @Author hsw
     * @Description 递归查询父机构下所有医院
     * @Date 15:20 2023/1/3
     * @Param [instId]
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> allHospFromParent(Long instId){
        List list=new JSONArray();
        JSONArray o=(JSONArray) institutionService.getInstByParent(instId);
        if (o.size()==0){
            return new ArrayList<>();
        }
        o.forEach(jo->{
            if (((JSONObject)jo).get("instTypeName").equals("医院")){
                list.add(jo);
            }else{
                list.addAll(allHospFromParent(((JSONObject)jo).getLong("id")));
            }
        });

        return list;
    }

    public List<JSONObject> getHospByParent(Long instId) {
        Map<String, Object> queryParam = Maps.newHashMap();
        queryParam.put("instId", instId);
        List<Map> hospList = institutionService.getHosp(queryParam);
        List<JSONObject> result = new ArrayList<>();

        for (Map<String, Object> hosp : hospList) {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : hosp.entrySet()) {
                String camelCaseKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
                jsonObject.put(camelCaseKey, entry.getValue());
            }
            result.add(jsonObject);
        }

        return result;
    }
}
