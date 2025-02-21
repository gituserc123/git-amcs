package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDictType;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 法务字典表相关接口
 */
@Api("法务字典表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/dictType")
public class LawDictTypeController extends BaseController {

    @Autowired
    private LawDictTypeService lawDictTypeService;

    /**
     * 根据ID查询法务字典信息
     *
     * @param id 法务字典的ID
     * @return 法务字典实体
     */
    @ApiOperation(value = "根据ID查询法务字典信息")
    @ApiParam(name = "id", value = "法务字典的ID", required = true)
    @RequestMapping(value = "/getLawDictType", method = RequestMethod.GET)
    public @ResponseBody LawDictType getLawDictType(@RequestParam("id") Long id) {
        return lawDictTypeService.selectById(id);
    }

    /**
     * 根据字典类型编码查询字典列表
     *
     * @param typeCode 字典类型编码
     * @return 字典列表
     */
    @ApiOperation(value = "根据字典类型编码查询字典列表")
    @ApiParam(name = "typeCode", value = "字典类型编码", required = true)
    @RequestMapping(value = "/selectByTypeCode", method = RequestMethod.GET)
    public @ResponseBody List<LawDictType> selectByTypeCode(@RequestParam("typeCode") String typeCode) {
        return lawDictTypeService.selectByTypeCode(typeCode);
    }

    /**
     * 根据父级字典ID查询子字典列表
     *
     * @param
     * @return 子字典列表
     */
    @ApiOperation(value = "根据父级字典ID查询子字典列表")
    @ApiParam(name = "parentId", value = "父级字典ID", required = true)
    @RequestMapping(value = "/selectSubDicts", method = RequestMethod.GET)
    public @ResponseBody List<LawDictType> selectSubDicts(@RequestParam("typeCode") String typeCode, @RequestParam("valueCode") String valueCode) {
        return lawDictTypeService.selectSubDicts(typeCode,valueCode);
    }

    /**
     * 根据参数值编码查询字典信息
     *
     * @param valueCode 参数值编码
     * @return 字典信息
     */
    @ApiOperation(value = "根据参数值编码查询字典信息")
    @ApiParam(name = "valueCode", value = "参数值编码", required = true)
    @RequestMapping(value = "/selectByValueCode", method = RequestMethod.GET)
    public @ResponseBody LawDictType selectByValueCode(@RequestParam("valueCode") String valueCode) {
        return lawDictTypeService.selectByValueCode(valueCode);
    }

    /**
     * 根据启停标识查询字典列表
     *
     * @param usingSign 启停标识（1启用，0停用）
     * @return 字典列表
     */
    @ApiOperation(value = "根据启停标识查询字典列表")
    @ApiParam(name = "usingSign", value = "启停标识（1启用，0停用）", required = true)
    @RequestMapping(value = "/selectByUsingSign", method = RequestMethod.GET)
    public @ResponseBody List<LawDictType> selectByUsingSign(@RequestParam("usingSign") Integer usingSign) {
        return lawDictTypeService.selectByUsingSign(usingSign);
    }
}