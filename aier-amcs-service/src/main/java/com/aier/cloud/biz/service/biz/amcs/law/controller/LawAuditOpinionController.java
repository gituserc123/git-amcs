package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditOpinion;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAuditOpinionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 审核意见表相关接口
 */
@Api("审核意见表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/auditOpinion")
public class LawAuditOpinionController extends BaseController {

    @Autowired
    private LawAuditOpinionService lawAuditOpinionService;

    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object save(@RequestBody LawAuditOpinion lawAuditOpinion) {
        return lawAuditOpinionService.insert(lawAuditOpinion);
    }

    @RequestMapping(value = "/getListByDetailIds",method = { RequestMethod.POST })
    @ResponseBody
    public List<LawAuditOpinion> getListByDetailIds(@RequestBody LawAuditOpinionCondition cond) {
        add("creator", "t_sys_staff|id|name", "creatorName");
        return lawAuditOpinionService.getListByDetailIds(cond);
    }

}