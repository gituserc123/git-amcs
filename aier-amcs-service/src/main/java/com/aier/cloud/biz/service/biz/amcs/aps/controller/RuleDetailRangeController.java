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
package com.aier.cloud.biz.service.biz.amcs.aps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailRange;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailRangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
/**
 * RuleDetailRange
 * 人资绩效计分规则范围表
 * @author 爱尔眼科
 * @since 2022-03-16 17:06:22
 */
@Api("人资绩效计分规则范围表相关接口")
@Controller
@RequestMapping("/api/amcs/aps/ruleDetailRange")
public class RuleDetailRangeController extends BaseController{
 
	@Autowired
	private RuleDetailRangeService ruleDetailRangeService;
	
	@ApiOperation(value="根据id查询RuleDetailRange人资绩效计分规则范围表实体")
	@ApiParam(name="id", value="人资绩效计分规则范围表的id", required=true)
	@RequestMapping(value = "/getRuleDetailRange")
	public @ResponseBody RuleDetailRange getRuleDetailRange(@RequestParam("id") Long id) {
		return ruleDetailRangeService.selectById(id);
	}
}