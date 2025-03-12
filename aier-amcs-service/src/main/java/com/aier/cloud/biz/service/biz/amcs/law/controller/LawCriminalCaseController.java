package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCriminalCase;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawCriminalCaseService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 刑事案件登记表相关接口
 */
@Api("刑事案件管理相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/criminalCase")
public class LawCriminalCaseController extends BaseController {

    @Autowired
    private LawCriminalCaseService lawCriminalCaseService;

    /**
     * 根据ID查询民事诉讼仲裁案件信息
     *
     * @param id 民事诉讼仲裁案件的ID
     * @return 民事诉讼仲裁案件实体
     */
    @ApiOperation(value = "根据ID查询民事诉讼仲裁案件信息")
    @ApiParam(name = "id", value = "民事诉讼仲裁案件的ID", required = true)
    @RequestMapping(value = "/getLawCriminalCase", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody LawCriminalCase getLawCriminalCase(@RequestParam("id") Long id) {
        return lawCriminalCaseService.selectById(id);
    }

    /**
     * 根据条件查询刑事案件列表
     *
     * @param condition 查询条件
     * @return 刑事案件列表（分页）
     */
    @ApiOperation(value = "根据条件分页查询刑事案件列表")
    @PostMapping("/findListByCond")
    public Object findListByCond(@RequestBody LawCriminalCaseCondition condition) {
        Page<Map<String, Object>> page = tranToPage(condition);
        return returnPageResponse(page, lawCriminalCaseService.getAll(page, condition));
    }

    /**
     * 保存/更新刑事案件信息
     *
     * @param criminalCase 案件实体
     * @return 操作结果
     */
    @ApiOperation(value = "保存或更新刑事案件信息")
    @PostMapping("/save")
    public Object save(@RequestBody LawCriminalCase criminalCase) {
        return lawCriminalCaseService.save(criminalCase);
    }
}
