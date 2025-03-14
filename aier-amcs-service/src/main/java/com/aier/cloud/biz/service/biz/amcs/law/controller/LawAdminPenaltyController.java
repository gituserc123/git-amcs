package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAdminPenalty;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAdminPenaltyService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 行政处罚事项表相关接口
 */
@Api("行政处罚事项相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/adminPenalty")
public class LawAdminPenaltyController extends BaseController {

    @Autowired
    private LawAdminPenaltyService lawAdminPenaltyService;

    /**
     * 根据ID查询行政处罚事项信息
     *
     * @param id 行政处罚事项的ID
     * @return 行政处罚事项实体
     */
    @ApiOperation(value = "根据ID查询行政处罚事项信息")
    @ApiParam(name = "id", value = "行政处罚事项的ID", required = true)
    @RequestMapping(value = "/getLawAdminPenalty", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody LawAdminPenalty getLawAdminPenalty(@RequestParam("id") Long id) {
        return lawAdminPenaltyService.selectById(id);
    }

    /**
     * 根据条件查询行政处罚事项列表
     *
     * @param condition 查询条件
     * @return 行政处罚事项列表
     */
    @ApiOperation(value = "根据条件查询行政处罚事项列表")
    @RequestMapping(value = "/findListByCond", method = RequestMethod.POST)
    public @ResponseBody Object findListByCond(@RequestBody LawAdminPenaltyCondition condition) {
        Page<Map<String, Object>> page = tranToPage(condition);
        return returnPageResponse(page, lawAdminPenaltyService.getAll(page, condition));
    }

    /**
     * 新增或更新行政处罚事项信息
     *
     * @param lawAdminPenalty 行政处罚事项实体
     * @return 操作结果
     */
    @ApiOperation(value = "新增或更新行政处罚事项信息")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Object saveOrUpdate(@RequestBody LawAdminPenalty lawAdminPenalty) {
        boolean result = lawAdminPenaltyService.insertOrUpdate(lawAdminPenalty);
        return result;
    }

    /**
     * 删除行政处罚事项信息
     *
     * @param id 行政处罚事项的ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除行政处罚事项信息")
    @ApiParam(name = "id", value = "行政处罚事项的ID", required = true)
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public @ResponseBody Object deleteById(@RequestParam("id") Long id) {
        boolean result = lawAdminPenaltyService.deleteById(id);
        return result;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody LawAdminPenalty lawAdminPenalty) {
        return lawAdminPenaltyService.save(lawAdminPenalty);
    }
}