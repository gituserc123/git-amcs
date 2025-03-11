package com.aier.cloud.biz.service.biz.amcs.law.controller;

import cn.hutool.core.date.DateUtil;
import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeAuth;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawNodeAuthService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 节点权限表相关接口
 */
@Api("节点权限表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/nodeAuth")
public class LawNodeAuthController extends BaseController {

    @Autowired
    private LawNodeAuthService lawNodeAuthService;


    @PostMapping(value = "/getLawNodeAuthListByStaffId")
    @ResponseBody
    public List<LawNodeAuth> getLawNodeAuthListByStaffId(@RequestParam("staffId") Long staffId) {
        return lawNodeAuthService.getLawNodeAuthListByStaffId(staffId);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Object save(@RequestBody LawNodeAuth lawNodeAuth) {
        return lawNodeAuthService.save(lawNodeAuth);
    }

    @RequestMapping(value = "/getAll")
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(@RequestBody LawNodeAuthCondition cond){
        Page<Map<String, Object>> page = tranToPage(cond);
        List<LawNodeAuth> retLists = lawNodeAuthService.getAll(page,cond);
        return  returnPageResponse(page,retLists);
    }

    @RequestMapping(value = "/saveNodeAuth")
    @ResponseBody
    public Object saveNodeAuth(@RequestBody List<LawNodeAuth> list){
        list.stream().forEach(f->{
            f.setCreateDate(DateUtil.date());
            f.setCreator(UserContext.getUserId());
            f.setModifer(UserContext.getUserId());
            f.setModifyDate(DateUtil.date());
            lawNodeAuthService.insert(f);
        });
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/deleteNodeAuth")
    @ResponseBody
    public Object deleteNodeAuth(@RequestParam("staffId") Long staffId,@RequestParam("staffInstId") Long staffInstId){
        EntityWrapper<LawNodeAuth> ew = new EntityWrapper<>();
        ew.eq("staff_id", staffId);
        ew.eq("staff_inst_id", staffInstId);
        List<LawNodeAuth> list = lawNodeAuthService.selectList(ew);
        if(CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(f-> lawNodeAuthService.deleteById(f.getId()));
        }
        return Maps.newHashMap();
    }


    @PostMapping(value = "/getLawNodeAuthListByParams")
    @ResponseBody
    public List<LawNodeAuth> getLawNodeAuthListByParams(@RequestParam("instIds") List<Long> instIds,@RequestParam("nodeCode") String nodeCode) {
        EntityWrapper<LawNodeAuth> ew = new EntityWrapper<>();
        ew.in("staff_inst_id", instIds);
        ew.eq("node_code", nodeCode);
        return lawNodeAuthService.selectList(ew);
    }



}