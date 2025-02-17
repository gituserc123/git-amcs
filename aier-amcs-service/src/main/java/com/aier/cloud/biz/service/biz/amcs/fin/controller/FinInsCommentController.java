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

import com.aier.cloud.api.amcs.fin.condition.FinInsCommentCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsCommentService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsComment;

import java.util.List;
import java.util.Map;

/**
 * FinInsComment
 * 沟通意见表
 * @author 爱尔眼科
 * @since 2023-04-27 17:00:17
 */
@Api("沟通意见表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsComment")
public class FinInsCommentController extends BaseController {
 
	@Autowired
	private FinInsCommentService finInsCommentService;
	
	@ApiOperation(value="根据id查询FinInsComment沟通意见表实体")
	@ApiParam(name="id", value="沟通意见表的id", required=true)
	@RequestMapping(value = "/getFinInsComment")
	public @ResponseBody FinInsComment getFinInsComment(@RequestParam("id") Long id) {
		return finInsCommentService.selectById(id);
	}
	@RequestMapping(value = "/getCommentByAssociatedId")
	public @ResponseBody List<Map<String, Object>> getCommentByAssociatedId(@RequestParam("associatedId") Long associatedId){
		add("creator", "t_sys_staff|id|name", "creatorName");
		return finInsCommentService.getCommentByAssociatedId(associatedId);
	}
	@RequestMapping(value = "/save")
	@ResponseBody
	public Boolean save(@RequestBody FinInsComment finInsComment){
		Object o=null;
		return finInsCommentService.save(finInsComment);
	}
}