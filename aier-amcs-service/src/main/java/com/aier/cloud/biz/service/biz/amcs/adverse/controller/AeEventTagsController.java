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
package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeEventTagsService;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
 
/**
 * AeEventTags
 * 事件标签关联表
 * @author 爱尔眼科
 * @since 2023-03-10 17:12:11
 */
@Api("事件标签关联表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeEventTags")
public class AeEventTagsController extends BaseController{
 
	@Autowired
	private AeEventTagsService aeEventTagsService;
	
	@RequestMapping(value = "/findTagsById")
	@ResponseBody
	public String findTagsById(@RequestParam("eventId") Long eventId) {
		return aeEventTagsService.findTagsById(eventId);
	}
	
	@RequestMapping(value = "/findTagDescsById")
	@ResponseBody
	public String findTagDescsById(@RequestParam("eventId") Long eventId) {
		return aeEventTagsService.findTagDescsById(eventId);
	}

	
	@RequestMapping(value = "/findListByCond")
	@ResponseBody
	public List<AeEventTags> findListByCond(@RequestBody AeInfoCondition cond){
		return aeEventTagsService.findListByCond(cond);
	}
}