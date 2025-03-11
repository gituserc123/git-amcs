package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeInfo;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawNodeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 节点信息表相关接口
 */
@Api("节点信息表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/nodeInfo")
public class LawNodeInfoController extends BaseController {

    @Autowired
    private LawNodeInfoService lawNodeInfoService;

    @ApiOperation("查询所有节点信息")
    @PostMapping(value = "/getAllNodeInfo")
    @ResponseBody
    public List<LawNodeInfo> getAllNodeInfo() {
        return lawNodeInfoService.selectList(null);
    }

    @PostMapping(value = "/getNodeByCode")
    @ResponseBody
    public LawNodeInfo getNodeByCode(@RequestParam("nodeCode") String nodeCode) {
        return lawNodeInfoService.getNodeByCode(nodeCode);
    }
}