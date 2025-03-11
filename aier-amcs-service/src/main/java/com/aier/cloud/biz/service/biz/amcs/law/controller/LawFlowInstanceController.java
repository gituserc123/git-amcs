package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowInstance;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawFlowInstanceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 流程实例表相关接口
 */
@Api("流程实例表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/flowInstance")
public class LawFlowInstanceController extends BaseController {

    @Autowired
    private LawFlowInstanceService lawFlowInstanceService;

    @PostMapping(value = "/getFlowInstanceByBizId")
    @ResponseBody
    public LawFlowInstance getFlowInstanceByBizId(@RequestParam("bizId") Long bizId){
        return lawFlowInstanceService.getFlowInstanceByBizId(bizId);
    }

    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object save(@RequestBody LawFlowInstance lawFlowInstance){
        lawFlowInstanceService.insertOrUpdate(lawFlowInstance);
        return lawFlowInstance;
    }
}