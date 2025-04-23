package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawIntegrityFilingCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawIntegrityFiling;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawIntegrityFilingService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 廉洁合规报备相关接口
 */
@Api("廉洁合规报备相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/integrityFiling")
public class LawIntegrityFilingController extends BaseController {

    @Autowired
    private LawIntegrityFilingService lawIntegrityFilingService;

    /**
     * 根据ID查询廉洁合规报备信息
     *
     * @param id 廉洁合规报备的ID
     * @return 廉洁合规报备实体
     */
    @ApiOperation(value = "根据ID查询廉洁合规报备信息")
    @ApiParam(name = "id", value = "廉洁合规报备的ID", required = true)
    @RequestMapping(value = "/getLawIntegrityFiling", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody LawIntegrityFiling getLawIntegrityFiling(@RequestParam("id") Long id) {
        return lawIntegrityFilingService.selectById(id);
    }

    /**
     * 根据条件查询廉洁合规报备列表
     *
     * @param condition 查询条件
     * @return 廉洁合规报备列表
     */
    @ApiOperation(value = "根据条件查询廉洁合规报备列表")
    @RequestMapping(value = "/findListByCond", method = RequestMethod.POST)
    public @ResponseBody Object findListByCond(@RequestBody LawIntegrityFilingCondition condition) {
        Page<Map<String, Object>> page = tranToPage(condition);
        return returnPageResponse(page, lawIntegrityFilingService.getAll(page, condition));
    }

    /**
     * 新增或更新廉洁合规报备信息
     *
     * @param lawIntegrityFiling 廉洁合规报备实体
     * @return 操作结果
     */
    @ApiOperation(value = "新增或更新廉洁合规报备信息")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Object saveOrUpdate(@RequestBody LawIntegrityFiling lawIntegrityFiling) {
        boolean result = lawIntegrityFilingService.insertOrUpdate(lawIntegrityFiling);
        return result;
    }

    /**
     * 删除廉洁合规报备信息
     *
     * @param id 廉洁合规报备的ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除廉洁合规报备信息")
    @ApiParam(name = "id", value = "廉洁合规报备的ID", required = true)
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public @ResponseBody Object deleteById(@RequestParam("id") Long id) {
        boolean result = lawIntegrityFilingService.deleteById(id);
        return result;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody LawIntegrityFiling lawIntegrityFiling) {
        return lawIntegrityFilingService.save(lawIntegrityFiling);
    }


    /**
     * 根据前缀查询事件编号的数量
     *
     * @param
     * @return 操作结果
     */
    @RequestMapping(value = "/getEventSnCount", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Integer getEventSnCount(@RequestParam("prefixEventSn") String prefixEventSn,@RequestParam("hospId") Long hospId) {
        EntityWrapper<LawIntegrityFiling> wrapper = new EntityWrapper<>();
        wrapper.like("event_sn", prefixEventSn + "%");
        wrapper.eq("hosp_id", hospId);
        return lawIntegrityFilingService.selectCount(wrapper);
    }

}