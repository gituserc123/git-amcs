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
package com.aier.cloud.biz.service.biz.amcs.coll.controller;

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

import com.aier.cloud.api.amcs.condition.DocSurgCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsDocSurgPrivService;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsDocSurgPriv;
 
/**
 * AmcsDocSurgPriv
 * 医生手术权限表
 * @author 爱尔眼科
 * @since 2023-05-16 10:36:22
 */
@Api("医生手术权限表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/coll/amcsDocSurg")
public class AmcsDocSurgController extends BaseController{
 
	@Autowired
	private AmcsDocSurgPrivService amcsDocSurgPrivService;
	
	@ApiOperation(value="根据id查询AmcsDocSurgPriv医生手术权限表实体")
	@ApiParam(name="id", value="医生手术权限表的id", required=true)
	@RequestMapping(value = "/getAmcsDocSurgPriv")
	public @ResponseBody AmcsDocSurgPriv getAmcsDocSurgPriv(@RequestParam("id") Long id) {
		return amcsDocSurgPrivService.selectById(id);
	}
	
	
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody Map<String, Object> mInfo){
		Map<String, Object> result = Maps.newHashMap();

		result.put("code", "200");
		result.put("success", true);
		
		try {
			amcsDocSurgPrivService.save(mInfo);
			result.put("msg", "保存成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("success", false);
			result.put("code", "500");
			result.put("msg", "保存失败");
			throw new BizException(e);
		}
		
		return result;
	}
	
	
	@RequestMapping(value="/findListByDoctor")
	@ResponseBody
	public List<AmcsDocSurgPriv> findListByDoctor(@RequestParam("doctorId") Long doctorId){
		return amcsDocSurgPrivService.findListByDoctor(doctorId);
	}
	
	@RequestMapping(value="/findListByHospDoctor")
	@ResponseBody
	public PageResponse<Map<String, Object>> findListByHospDoctor(@RequestBody DocSurgCondition cond){
		add("modifer", "t_sys_staff|id|name", "modiferName");
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, amcsDocSurgPrivService.findListByDoctor(page, cond));
	}
	
	
	@RequestMapping(value="/findCountByProv")
	@ResponseBody
	public PageResponse<Map<String, Object>> findCountByProv(@RequestBody DocSurgCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, amcsDocSurgPrivService.findCountByProv(page, cond));
	}
	
	
	@RequestMapping(value="/del")
	@ResponseBody
	public Boolean del(@RequestParam("id") Long id){
		return amcsDocSurgPrivService.deleteById(id);
	}
	
	@RequestMapping(value="/findListByHosp")
	@ResponseBody
	public List<AmcsDocSurgPriv> findListByHosp(@RequestParam("hospId") Long hospId){
		add("modifer", "t_sys_staff|id|name", "modiferName");
		return amcsDocSurgPrivService.findListByHosp(hospId);
	}
	
	@RequestMapping(value="/findStaffIdByHosp")
	@ResponseBody
	public List<Long> findStaffIdByHosp(@RequestParam("hospId") Long hospId){
		return amcsDocSurgPrivService.findStaffIdByHosp(hospId);
	}
}