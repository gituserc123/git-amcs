package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDisputeMatter;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawDisputeMatterService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 纠纷事项表相关接口
 */
@Api("纠纷事项相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/disputeMatter")
public class LawDisputeMatterController extends BaseController {

    @Autowired
    private LawDisputeMatterService lawDisputeMatterService;

    /**
     * 根据ID查询纠纷事项信息
     *
     * @param id 纠纷事项的ID
     * @return 纠纷事项实体
     */
    @ApiOperation(value = "根据ID查询纠纷事项信息")
    @ApiParam(name = "id", value = "纠纷事项的ID", required = true)
    @RequestMapping(value = "/getLawDisputeMatter", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody LawDisputeMatter getLawDisputeMatter(@RequestParam("id") Long id) {
        return lawDisputeMatterService.selectById(id);
    }

    /**
     * 根据条件查询纠纷事项列表
     *
     * @param condition 查询条件
     * @return 纠纷事项列表
     */
    @ApiOperation(value = "根据条件查询纠纷事项列表")
    @RequestMapping(value = "/findListByCond", method = RequestMethod.POST)
    public @ResponseBody Object findListByCond(@RequestBody LawDisputeMatterCondition condition) {
        Page<Map<String, Object>> page = tranToPage(condition);
        return returnPageResponse(page, lawDisputeMatterService.getAll(page, condition));
    }

    /**
     * 新增或更新纠纷事项信息
     *
     * @param lawDisputeMatter 纠纷事项实体
     * @return 操作结果
     */
    @ApiOperation(value = "新增或更新纠纷事项信息")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Object saveOrUpdate(@RequestBody LawDisputeMatter lawDisputeMatter) {
        boolean result = lawDisputeMatterService.insertOrUpdate(lawDisputeMatter);
        return result;
    }

    /**
     * 删除纠纷事项信息
     *
     * @param id 纠纷事项的ID
     * @return 操作结果
     */
    @ApiOperation(value = "删除纠纷事项信息")
    @ApiParam(name = "id", value = "纠纷事项的ID", required = true)
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public @ResponseBody Object deleteById(@RequestParam("id") Long id) {
        boolean result = lawDisputeMatterService.deleteById(id);
        return result;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody LawDisputeMatter lawDisputeMatter) {
        return lawDisputeMatterService.save(lawDisputeMatter);
    }
}