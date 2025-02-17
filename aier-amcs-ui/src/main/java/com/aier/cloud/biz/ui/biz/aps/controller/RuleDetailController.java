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
package com.aier.cloud.biz.ui.biz.aps.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.RefreshResult;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.aps.feign.RuleDetailService;

/**
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Controller
@RequestMapping("/ui/amcs/aps/ruleDetail")
public class RuleDetailController extends BaseController{

	@Autowired
	private RuleDetailService ruleDetailService;

	@RequiresPermissions("Service/biz/amcs/apsRuleDetail:view")
	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody Map<String, Object> getRuleDetail(@RequestParam("id") Long id) {
		return ruleDetailService.selectById(id);
	}
	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/refreshGroupRule")
	public @ResponseBody Object refreshGroupRule(@RequestParam("id") Long id) {
		return success(ruleDetailService.refreshGroupRule(id));
	}
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/refreshHospRule")
	public @ResponseBody Object refreshHospRule(@RequestParam("id") Long id) {
		return success(ruleDetailService.refreshHospRule(id));
	}
}
