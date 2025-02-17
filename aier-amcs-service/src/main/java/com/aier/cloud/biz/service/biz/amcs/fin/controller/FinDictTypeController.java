/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinDictTypeService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinDictType;

import java.util.List;
import java.util.Map;

/**
 * FinDictType
 * 财务字典表
 * @author 爱尔眼科
 * @since 2023-03-13 11:28:02
 */
@Api("财务字典表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finDictType")
public class FinDictTypeController extends BaseController {
 
	@Autowired
	private FinDictTypeService finDictTypeService;

	@RequestMapping(value = "/getList")
	public @ResponseBody List<Map> getList(
			@ApiParam(name="paraType", value="码表类型", required=true) @RequestParam("paraType") String typeCode,
			@ApiParam(name="filter", value="过滤参数", required=false) @RequestParam(value="filter",required=false) String filter ) {
		return finDictTypeService.getList(typeCode,filter);
	}
	
	@ApiOperation(value="根据id查询FinDictType财务字典表实体")
	@ApiParam(name="id", value="财务字典表的id", required=true)
	@RequestMapping(value = "/getFinDictType")
	public @ResponseBody FinDictType getFinDictType(@RequestParam("id") Long id) {
		return finDictTypeService.selectById(id);
	}

	@RequestMapping(value = "/getMoreList")
	public @ResponseBody
	Map<String, List> getMoreList(@RequestParam("paraType[]") List<String> paraTypes) {
		return finDictTypeService.getMoreList(paraTypes);
	}

	@RequestMapping(value="/save")
	public @ResponseBody Boolean save(@RequestBody FinDictType finDictType){
		return finDictTypeService.save(finDictType);
	}

	@RequestMapping(value="/del")
	public @ResponseBody Boolean del(@RequestParam("id") Long id){
		return finDictTypeService.del(id);
	}

	@RequestMapping(value="/getType")
	public @ResponseBody List getType(@RequestParam(value="hospId",required = false ) Long hospId){
		return finDictTypeService.getType(hospId);
	}
}