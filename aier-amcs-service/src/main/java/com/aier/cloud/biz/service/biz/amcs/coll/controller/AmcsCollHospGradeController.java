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

import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsCollAttachmentService;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsCollHospGradeService;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollAttachment;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollHospGrade;
 
/**
 * AmcsCollHospGrade
 * 医院等级采集表
 * @author 爱尔眼科
 * @since 2023-03-29 09:19:34
 */
@Api("医院等级采集表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/coll/amcsCollHospGrade")
public class AmcsCollHospGradeController extends BaseController{
 
	@Autowired
	private AmcsCollHospGradeService amcsCollHospGradeService;
	
	@Autowired
	private AmcsCollAttachmentService amcsCollAttachmentService;
	
	
	@RequestMapping(value = "/findList")
	@ResponseBody
	public PageResponse<Map<String, Object>> findList(@RequestBody CollCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page,amcsCollHospGradeService.findList(page, cond));
	}
	
	@ApiOperation(value="根据id查询AmcsHospGradeColl医院等级采集表实体")
	@ApiParam(name="id", value="医院等级采集表的id", required=true)
	@RequestMapping(value = "/findById")
	public @ResponseBody AmcsCollHospGrade findById(@RequestParam("id") Long id) {
		AmcsCollHospGrade grade = amcsCollHospGradeService.selectById(id);
		List<AmcsCollAttachment> attachments =  amcsCollAttachmentService.findByCollId(id);
		grade.setAttachments(attachments);
		return grade;
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody Map<String, Object> save(@RequestBody Map<String, Object> mData) throws Exception {

		Map<String, Object> result = Maps.newHashMap();

		result.put("code", "200");
		result.put("success", true);
		
		try {
			amcsCollHospGradeService.save(mData);
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
}