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

import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.based.DcgDiag;
import com.aier.cloud.basic.api.response.domain.base.PageResponse; 
import com.aier.cloud.basic.starter.service.controller.BaseController; 
import com.aier.cloud.biz.service.biz.amcs.idr.service.AmcsIdrBaseInfoService;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo; 
import com.aier.cloud.biz.service.biz.amcs.idr.enums.AuditStateEnum;
import com.aier.cloud.biz.service.biz.amcs.idr.enums.OperateTypeEnum; 
/**
 * AmcsIdrBaseInfo 传染病基本信息表
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@Api("传染病基本信息表相关接口")
@Controller
@RequestMapping("/api/amcs/idr/amcsIdrBaseInfo")
public class AmcsIdrBaseInfoController extends BaseController {

	@Autowired
	private AmcsIdrBaseInfoService amcsIdrBaseInfoService;

	@ApiOperation(value = "根据id查询AmcsIdrBaseInfo传染病基本信息表实体")
	@ApiParam(name = "id", value = "传染病基本信息表的id", required = true)
	@RequestMapping(value = "/getAmcsIdrBaseInfo")
	public @ResponseBody AmcsIdrBaseInfo getAmcsIdrBaseInfo(@RequestParam("id") Long id) {
		return amcsIdrBaseInfoService.selectById(id);
	}
	@RequestMapping("/del")
	@ResponseBody
	public String del(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.del(id, loginUserId, loginUserName);

	}
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KAdd);

	}
	
	@RequestMapping("/auditUn")
	@ResponseBody
	public String auditUn(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.auditUn(id, loginUserId, loginUserName);

	}
	
	@RequestMapping("/auditById")
	@ResponseBody
	public String auditById(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.auditById(id, loginUserId, loginUserName);

	}
	@RequestMapping("/uploadRes")
	@ResponseBody
	
	public String uploadRes(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KRes);

	}	
	@RequestMapping("/uploadAud")
	@ResponseBody
	public String uploadAud(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KAud);

	}	
	@RequestMapping("/uploadMod")
	@ResponseBody
	public String uploadMod(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KMod);

	}	
	@RequestMapping("/uploadDel")
	@ResponseBody
	public String uploadDel(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KDel);

	}
	@RequestMapping("/uploadSeach")
	@ResponseBody
	public String uploadSeach(@RequestParam("id") String id,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.upload(id, loginUserId, loginUserName, OperateTypeEnum.KSea);

	}
	@RequestMapping("/save")
	@ResponseBody
	public AmcsIdrBaseInfo save(@RequestBody AmcsIdrBaseInfo apply,@RequestParam("loginUserId") Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.save(apply, loginUserId, loginUserName);

	}
	@RequestMapping("/audit")
	@ResponseBody
	public AmcsIdrBaseInfo audit(@RequestBody AmcsIdrBaseInfo apply,@RequestParam("loginUserId")  Long loginUserId,@RequestParam("loginUserName")  String loginUserName) {
		return amcsIdrBaseInfoService.audit(apply, loginUserId, loginUserName);

	}
	@RequestMapping("/getList")
	@ResponseBody
	public PageResponse<Map> getList(@RequestBody BasedCommonCondition d) {
		return amcsIdrBaseInfoService.getList(d);

	}
	@RequestMapping("/getByMainId")
	@ResponseBody
	public AmcsIdrBaseInfo getByMainId(@RequestParam("id")  Long id) {
		return amcsIdrBaseInfoService.getByMainId(id);

	}
	@RequestMapping("/getHospStaff")
	@ResponseBody
	public List<Map> getHospStaff(@RequestBody StaffCondition d) {
		return amcsIdrBaseInfoService.getHospStaff(d);

	}
	
	@RequestMapping("/getDcgDiagList")
	@ResponseBody
	public  List<DcgDiag>  getDcgDiagList(@RequestBody DcgDiagCondition d) {
		return amcsIdrBaseInfoService.getDcgDiagList(d);

	}
	@RequestMapping("/getMainByCondForList")
	@ResponseBody
	public List<AmcsIdrBaseInfo> getMainByCondForList(@RequestBody AmcsIdrBaseInfo apply) {
		TransCode.add("auditstate", AuditStateEnum.class);
		return amcsIdrBaseInfoService.getMainByCondForList(apply);
	}
	 
	 
}