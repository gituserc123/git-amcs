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
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailCalc;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailCalcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
/**
 * RuleDetailCalc
 * 人资绩效计分规则详情表(用于计算拓展)
 * @author 爱尔眼科
 * @since 2023-03-27 14:59:08
 */
@Api("人资绩效计分规则详情表(用于计算拓展)相关接口")
@Controller
@RequestMapping("/api/amcs/aps/ruleDetailCalc")
public class RuleDetailCalcController extends BaseController{
 
	@Autowired
	private RuleDetailCalcService ruleDetailCalcService;
	
	@ApiOperation(value="根据id查询RuleDetailCalc人资绩效计分规则详情表(用于计算拓展)实体")
	@ApiParam(name="id", value="人资绩效计分规则详情表(用于计算拓展)的id", required=true)
	@RequestMapping(value = "/getRuleDetailCalc")
	public @ResponseBody RuleDetailCalc getRuleDetailCalc(@RequestParam("id") Long id) {
		return ruleDetailCalcService.selectById(id);
	}
}