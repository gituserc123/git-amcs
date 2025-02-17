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

import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsMonthService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMonth;

import java.util.List;
import java.util.Map;

/**
 * FinInsMonth
 * 医院填报月度主表
 * @author 爱尔眼科
 * @since 2023-03-27 14:39:29
 */
@Api("医院填报月度主表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsMonth")
public class FinInsMonthController extends BaseController{
 
	@Autowired
	private FinInsMonthService finInsMonthService;
	
	@ApiOperation(value="根据id查询FinInsMonth医院填报月度主表实体")
	@ApiParam(name="id", value="医院填报月度主表的id", required=true)
	@RequestMapping(value = "/getFinInsMonth")
	public @ResponseBody FinInsMonth getFinInsMonth(@RequestParam("id") Long id) {
		return finInsMonthService.selectById(id);
	}

	@RequestMapping(value = "/getList")
	@ResponseBody
	public List<Map<String, Object>> getList(@RequestBody FinInsMonthCondition cond){
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("hospId", "t_sys_institution|ahis_hosp|name", "hospName");
		return finInsMonthService.getList(cond);
	}

	@RequestMapping(value = "/unReport")
	@ResponseBody
	public List<Map<String,Object>> unReport(@RequestBody FinInsMonthCondition cond){
		add("hospId", "t_sys_institution|ahis_hosp|name", "hospName");
		return finInsMonthService.unReport(cond);
	}

	@RequestMapping(value = "/new")
	@ResponseBody
	public Boolean newLine(@RequestParam("hospId") Long hospId){
		return finInsMonthService.newLine(hospId);
	}


	@RequestMapping(value = "/canSubmit")
	@ResponseBody
	public Boolean canSubmit(@RequestParam("id") Long id){

		return finInsMonthService.canSubmit(id);
	}


	@RequestMapping(value = "/submitToProvince")
	@ResponseBody
	public Boolean submitToProvince(@RequestParam("id") Long id){
		return finInsMonthService.submitToProvince(id);
	}

	@RequestMapping(value = "/submitToGroup")
	@ResponseBody
	public Boolean submitToGroup(@RequestParam("id") Long id){
		return finInsMonthService.submitToGroup(id);
	}

	@RequestMapping(value = "/returnToHos")
	@ResponseBody
	public Boolean returnToHos(@RequestParam("id") Long id){
		return finInsMonthService.returnToHos(id);
	}
	/**
	 * 通过monthId删除
	 */
	@RequestMapping(value = "/deleteByMonthId")
	@ResponseBody
	public Boolean deleteByMonthId(@RequestParam("monthId") String monthId){
		return finInsMonthService.deleteByMonthId(monthId);
	}

}