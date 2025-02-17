/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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

import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeFocusDomain;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeFocusEventService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeFocusEvent;
 
/**
 * AeFocusEvent
 * 重点标记事件
 * @author 爱尔眼科
 * @since 2024-03-01 09:38:04
 */
@Api("重点标记事件相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeFocusEvent")
public class AeFocusEventController extends BaseController{
 
	@Autowired
	private AeFocusEventService aeFocusEventService;
	
	@ApiOperation(value="根据id查询AeFocusEvent重点标记事件实体")
	@ApiParam(name="id", value="重点标记事件的id", required=true)
	@RequestMapping(value = "/getAeFocusEvent")
	public @ResponseBody AeFocusEvent getAeFocusEvent(@RequestParam("id") Long id) {
		return aeFocusEventService.selectById(id);
	}

	@ApiOperation(value="增加重点事件信息")
	@RequestMapping(value = "/add")
	public @ResponseBody Long add(@RequestBody AeFocusDomain domain) {
		AeFocusEvent focus = new AeFocusEvent();
		BeanUtils.copyProperties(domain, focus);
		aeFocusEventService.insert(focus);
		return focus.getId();
	}


	@ApiOperation(value="删除已标记重点事件")
	@RequestMapping(value = "/del")
	public @ResponseBody Boolean del(@RequestParam("id") Long id) {
		return aeFocusEventService.deleteById(id);
	}


}