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
package com.aier.cloud.biz.ui.biz.amcs.jtr.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.condition.PositionCondition;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.amcs.jtr.feign.PositionService;


/**
 * Position
 * 医生跨院岗位表
 * @author 爱尔眼科
 * @since 2021-09-26 14:29:33
 */
@Controller
@RequestMapping("/ui/amcs/jtr/position")
public class PositionController extends BaseController{

	@Autowired
	private PositionService positionService;

	@RequiresPermissions("AmcsPosition:view")
	@RequestMapping(value = "/getPosition")
	public @ResponseBody
	Map getPosition(@RequestParam("id") Long id) {
		return positionService.selectById(id);
	}

	@RequiresPermissions("Calendar:view")
	@RequestMapping("/getAll")
	public @ResponseBody
	List<Map<String, Object>> getAll(PositionCondition cond){
		return positionService.getAll(cond);
	}

}
