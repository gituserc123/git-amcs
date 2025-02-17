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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.RefreshResult;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleDetailServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
/**
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Api("人资绩效计分规则详情表相关接口")
@Controller
@RequestMapping("/api/amcs/aps/ruleDetail")
public class RuleDetailController extends BaseController{

	@Autowired RuleDetailServiceImpl ruleDetailService;
	@Autowired RuleServiceImpl ruleService;
	@Autowired JdbcHelper db;
	@RequestMapping(value = "/refreshGroupRule")
	public @ResponseBody RefreshResult refreshGroupRule(@RequestParam("id") Long id) {
		return ruleDetailService.refreshGroupRule(id);
	}

	@RequestMapping(value = "/refreshHospRule")
	public @ResponseBody RefreshResult refreshHospRule(@RequestParam("id") Long id) {
		Rule rule = db.queryBean("select * from T_AMCS_APS_RULE w where w.id = ?", Rule.class, id);
		return ruleDetailService.refreshHospRule(rule);
	}
	
	@ApiOperation(value="根据id查询RuleDetail人资绩效计分规则详情表实体")
	@ApiParam(name="id", value="人资绩效计分规则详情表的id", required=true)
	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody RuleDetail getRuleDetail(@RequestParam("id") Long id) {
		return ruleDetailService.selectById(id);
	}
	
	
}