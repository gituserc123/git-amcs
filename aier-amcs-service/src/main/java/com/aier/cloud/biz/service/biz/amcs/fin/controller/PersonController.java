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

import com.aier.cloud.api.amcs.fin.condition.PersonCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.aier.cloud.biz.service.biz.amcs.fin.service.PersonService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * Person
 * 医保系统相关人员表
 * @author 爱尔眼科
 * @since 2023-08-29 15:14:28
 */
@Api("医保系统相关人员表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/person")
public class PersonController extends BaseController {
 
	@Autowired
	private PersonService personService;
	
	@ApiOperation(value="根据id查询Person医保系统相关人员表实体")
	@ApiParam(name="id", value="医保系统相关人员表的id", required=true)
	@RequestMapping(value = "/getPerson")
	public @ResponseBody Person getPerson(@RequestParam("id") Long id) {
		return personService.selectById(id);
	}

	@RequestMapping(value ="/save")
	public @ResponseBody Boolean savePerson(@RequestBody  Person person){
		return personService.save(person);
	}

	@RequestMapping(value="/getPersonByHospId")
	public @ResponseBody Person getPersonByHospId(@RequestParam Long id){ return personService.getPersonByHospId(id);}

	@RequestMapping(value = "/list")
	public
	@ResponseBody List<Map<String,Object>> getPersonList(@RequestBody PersonCondition personCondition){

		add("modifer", "t_sys_staff|id|name", "modiferName");
		add("hospId", "t_sys_institution|ahis_hosp|name", "hospName");

		return personService.getPersonList(personCondition);
	}
}