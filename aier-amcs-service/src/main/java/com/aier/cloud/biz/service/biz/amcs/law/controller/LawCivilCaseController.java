package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCivilCase;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawCivilCaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 民事诉讼仲裁案件主表相关接口
 */
@Api("民事诉讼仲裁案件相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/civilCase")
public class LawCivilCaseController extends BaseController {

    @Autowired
    private LawCivilCaseService lawCivilCaseService;

    /**
     * 根据ID查询民事诉讼仲裁案件信息
     *
     * @param id 民事诉讼仲裁案件的ID
     * @return 民事诉讼仲裁案件实体
     */
    @ApiOperation(value = "根据ID查询民事诉讼仲裁案件信息")
    @ApiParam(name = "id", value = "民事诉讼仲裁案件的ID", required = true)
    @RequestMapping(value = "/getLawCivilCase", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody LawCivilCase getLawCivilCase(@RequestParam("id") Long id) {
        return lawCivilCaseService.selectById(id);
    }

    /**
     * 根据条件查询民事诉讼仲裁案件列表
     *
     * @param condition 查询条件
     * @return 民事诉讼仲裁案件列表
     */
    @ApiOperation(value = "根据条件查询民事诉讼仲裁案件列表")
    @RequestMapping(value = "/findListByCond", method = RequestMethod.POST)
    public @ResponseBody Object findListByCond(@RequestBody LawCivilCaseCondition condition) {
        return null;
    }

    /**
     * 新增或更新民事诉讼仲裁案件信息
     *
     * @param lawCivilCase 民事诉讼仲裁案件实体
     * @return 操作结果
     */
    @ApiOperation(value = "新增或更新民事诉讼仲裁案件信息")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Object saveOrUpdate(@RequestBody LawCivilCase lawCivilCase) {
        boolean result = lawCivilCaseService.insertOrUpdate(lawCivilCase);
        return result;
    }

    /**
     * 删除民事诉讼仲裁案件信息
     *
     * @param id 民事诉讼仲裁案件的ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除民事诉讼仲裁案件信息")
    @ApiParam(name = "id", value = "民事诉讼仲裁案件的ID", required = true)
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public @ResponseBody Object deleteById(@RequestParam("id") Long id) {
        boolean result = lawCivilCaseService.deleteById(id);
        return result;
    }

    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object save(@RequestBody LawCivilCase lawCivilCase) {
        return lawCivilCaseService.save(lawCivilCase);
    }
}
