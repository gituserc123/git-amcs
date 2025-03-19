package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawExternalLegalAdvisor;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawExternalLegalAdvisorService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 外聘法律顾问信息登记表相关接口
 */
@Api("外聘法律顾问信息相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/externalLegalAdvisor")
public class LawExternalLegalAdvisorController extends BaseController {

    @Autowired
    private LawExternalLegalAdvisorService lawExternalLegalAdvisorService;

    /**
     * 根据ID查询外聘法律顾问信息
     *
     * @param id 外聘法律顾问信息的ID
     * @return 外聘法律顾问信息实体
     */
    @ApiOperation(value = "根据ID查询外聘法律顾问信息")
    @ApiParam(name = "id", value = "外聘法律顾问信息的ID", required = true)
    @RequestMapping(value = "/getLawExternalLegalAdvisor", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody LawExternalLegalAdvisor getLawExternalLegalAdvisor(@RequestParam("id") Long id) {
        return lawExternalLegalAdvisorService.selectById(id);
    }

    /**
     * 根据条件查询外聘法律顾问信息列表
     *
     * @param condition 查询条件
     * @return 外聘法律顾问信息列表
     */
    @ApiOperation(value = "根据条件查询外聘法律顾问信息列表")
    @RequestMapping(value = "/findListByCond", method = RequestMethod.POST)
    public @ResponseBody Object findListByCond(@RequestBody LawExternalLegalAdvisorCondition condition) {
        Page<Map<String, Object>> page = tranToPage(condition);
        return returnPageResponse(page, lawExternalLegalAdvisorService.getAll(page, condition));
    }

    /**
     * 保存外聘法律顾问信息
     *
     * @param lawExternalLegalAdvisor 外聘法律顾问信息实体
     * @return 保存结果
     */
    @ApiOperation(value = "保存外聘法律顾问信息")
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody LawExternalLegalAdvisor lawExternalLegalAdvisor) {
        return lawExternalLegalAdvisorService.save(lawExternalLegalAdvisor);
    }
}