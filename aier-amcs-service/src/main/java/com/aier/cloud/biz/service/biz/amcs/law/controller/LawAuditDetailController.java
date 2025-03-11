package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditDetail;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAuditDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 审核明细表相关接口
 */
@Api("审核明细表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/auditDetail")
public class LawAuditDetailController extends BaseController {

    @Autowired
    private LawAuditDetailService lawAuditDetailService;

    @PostMapping(value = "/getLawAuditDetailListByInstanceId")
    @ResponseBody
    public List<LawAuditDetail> getLawAuditDetailListByInstanceId(@RequestParam("instanceId") Long instanceId) {
        return lawAuditDetailService.getLawAuditDetailListByInstanceId(instanceId);
    }

    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public LawAuditDetail save(@RequestBody LawAuditDetail lawAuditDetail) {
        lawAuditDetailService.insert(lawAuditDetail);
        return lawAuditDetail;
    }

}