package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import java.util.List;
import java.util.Map;

import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDictTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("不良事件字典表接口")
@RestController
@RequestMapping("/api/amcs/adverse/aeDictType")
public class AeDictTypeController extends BaseController{
	
	@Autowired
	private AeDictTypeService service;
	
	@RequestMapping(value = "/getList")
	public @ResponseBody List<Map> getList(
			@ApiParam(name="paraType", value="码表类型", required=true) @RequestParam("paraType") String typeCode,
			@ApiParam(name="filter", value="过滤参数", required=false) @RequestParam(value="filter",required=false) String filter ) {
		return service.getList(typeCode,filter);
	}

	@RequestMapping(value = "/getListByFilters")
	public @ResponseBody List<Map> getListByFilters(
			@ApiParam(name="paraType", value="码表类型", required=true) @RequestParam("paraType") String typeCode,
			@ApiParam(name="filters", value="过滤参数", required=false) @RequestParam(value="filters",required=false) String filters ) {
		return service.getListByFilters(typeCode,filters);
	}


	
	@RequestMapping(value = "/getMoreList")
	public @ResponseBody Map<String, List> getMoreList(@RequestParam("paraType[]") List<String> paraTypes) {
		return service.getMoreList(paraTypes);
	}

	@RequestMapping(value="/save")
	public @ResponseBody Boolean save(@RequestBody AeDictType aeDictType){
		return service.save(aeDictType);
	}

	@RequestMapping(value="/del")
	public @ResponseBody Boolean del(@RequestParam("id") Long id){
		return service.del(id);
	}

}
