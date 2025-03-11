package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowNode;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawFlowNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程节点表相关接口
 */
@Api("流程节点表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/flowNode")
public class LawFlowNodeController extends BaseController {

    @Autowired
    private LawFlowNodeService lawFlowNodeService;

    /**
     * 获取所有流程节点信息
     */
    @ApiOperation("获取所有流程节点信息")
    @PostMapping("/getFlowNodeByFlowId")
    @ResponseBody
    public List<LawFlowNode> getFlowNodeByFlowId(@RequestParam("flowId") Long flowId) {
        return lawFlowNodeService.getFlowNodeByFlowId(flowId);
    }

    // 在这里可以添加更多接口方法，例如根据条件查询、新增、修改、删除等
}