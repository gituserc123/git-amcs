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
package com.aier.cloud.biz.service.biz.amcs.idr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.idr.service.AmcsIdrDictTypeService;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrDictType;

/**
 * AmcsIdrDictType 传染病字典表
 * 
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */
@Api("传染病字典表相关接口")
@Controller
@RequestMapping("/api/amcs/idr/amcsIdrDictType")
public class AmcsIdrDictTypeController extends BaseController {

	@Autowired
	private AmcsIdrDictTypeService amcsIdrDictTypeService;

	@ApiOperation(value = "根据id查询AmcsIdrDictType传染病字典表实体")
	@ApiParam(name = "id", value = "传染病字典表的id", required = true)
	@RequestMapping(value = "/selectById")
	public @ResponseBody AmcsIdrDictType selectById(@RequestParam("id") Long id) {
		return amcsIdrDictTypeService.selectById(id);
	}

	 
	@ApiParam(name = "typeCode", value = "传染病字典表的类型", required = true)
	@RequestMapping("/getAmcsIdrDictType")
	@ResponseBody
	public List<AmcsIdrDictType> getAmcsIdrDictType(@RequestBody AmcsIdrDictType apply) { 
		List<AmcsIdrDictType> tmpList = amcsIdrDictTypeService.getAmcsIdrDictType(apply);
		return tmpList;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AmcsIdrDictType save(@RequestBody AmcsIdrDictType apply,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrDictTypeService.save(apply, loginUserId, loginUserName);

	}
	
	@ApiParam(name = "typeCode", value = "传染病字典表的类型", required = true)
	@RequestMapping("/getTypeCodeForList")
	@ResponseBody
	public List<AmcsIdrDictType> getTypeCodeForList(@RequestBody AmcsIdrDictType apply) { 
		List<AmcsIdrDictType> tmpList = amcsIdrDictTypeService.getTypeCodeForList(apply);
		
		//int iBoole= amcsIdrDictTypeService.getdiagCodeIDR("B15");
		return tmpList;
	}
	/**
	 * 判断ICD10编码是否是传染病
	 * @param diagCode ,ICD10编码
	 * @return 0不是传染病，1是传染病
	 */
	@ApiParam(name = "typeCode", value = "判断ICD10编码是否是传染病", required = true)
	@RequestMapping("/getdiagCodeIDR")
	@ResponseBody
	public int   getdiagCodeIDR(String  diagCode)  { 
		return amcsIdrDictTypeService.getdiagCodeIDR(diagCode);
	}
	@RequestMapping("/getPatient")
	@ResponseBody
	public AmcsIdrBaseInfo getPatient(@RequestParam("patientName")  String patientName) { 
		AmcsIdrBaseInfo  tmpList = amcsIdrDictTypeService.getPatient(patientName);
		return tmpList;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/getEnum")
	@ResponseBody
	public List<Map> getEnum(@RequestBody AmcsIdrDictType c) {
		List<Map> tmpList = amcsIdrDictTypeService.getEnum(c);
		return tmpList;
	} 
	 
	 
}