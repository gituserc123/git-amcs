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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.enums.RuleItemTypeEnum;
import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.api.amcs.enums.UsingSignEnum;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.RuleDto;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.MetricServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleServiceImpl;
import com.aier.cloud.center.common.context.UserContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
/**
 * Rule
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Api("人资绩效计分规则表相关接口")
@Controller
@RequestMapping("/api/amcs/aps/rule")
public class RuleController extends BaseController{

	@Autowired
	private RuleServiceImpl ruleService;
	@Autowired
	private MetricServiceImpl metricService;
	@Autowired
	private JdbcHelper db;
	
	@ApiOperation(value="根据id查询Rule人资绩效计分规则表实体")
	@ApiParam(name="id", value="人资绩效计分规则表的id", required=true)
	@RequestMapping(value = "/getRule")
	public @ResponseBody Rule getRule(@RequestParam("id") Long id,@RequestParam(value="hospId",required=false) Long hospId) {
		Rule rule = UserContext.runWithChangedHospId(hospId, ()->{
			return ruleService.selectById(id);
		});
		rule.setMetricId2(rule.getMetricId());
		add("metricCode", "t_amcs_aps_metric|metric_code|metric_name","metricName");
		add("metricId", "t_amcs_aps_metric|id|rule_ext_type","ruleExtType");
//		add("ruleExtType", ScoreExtendTypeEnum.class,"ruleExtTypeName");
		return rule;
	}

	@RequestMapping(value = "/updateRule")
	public @ResponseBody void updateRule(@RequestBody Rule m) {
		UserContext.runWithChangedHospId(m.getHospId(), ()->{
			ruleService.updateRule(m);
			return null;
		});
	}
	

	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody List getRuleDetail(@RequestBody RuleDto m) {
		Long hospId = db.queryLong("select hosp_id from T_AMCS_APS_RULE where id = ? ", m.getId());
		List result = UserContext.runWithChangedHospId(hospId, ()->{
			return ruleService.getRuleDetail(m);
		});
		add("ruleItemType", RuleItemTypeEnum.class,"ruleItemTypeDesc");
		add("usingSign", UsingSignEnum.class,"usingSignDesc");
		return result;
	}

	@RequestMapping(value = "/updateRuleDetail")
	public @ResponseBody String updateRuleDetail(@RequestBody RuleDto m) {
		Long hospId = db.queryLong("select hosp_id from T_AMCS_APS_RULE where id = ? ", m.getRuleId());
		return UserContext.runWithChangedHospId(hospId, ()->{
			return ruleService.updateRuleDetail(m);
		});
	}

	@RequestMapping(value = "/getGroupRuleList")
	public @ResponseBody List getGroupRuleList(@RequestBody Rule m) {
		add("ruleItemType", RuleItemTypeEnum.class,"ruleItemTypeDesc");
		return UserContext.runWithChangedHospId(m.getHospId(), ()->{
			return ruleService.getGroupRuleList(m);
		});
	}
	
	/**
	 * 从集团搞数据过来刷新到医院，通常是因为集团加了。
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/refreshRuleFromGroup")
	public @ResponseBody List refreshRuleFromGroup(@RequestBody RuleDto m) {
		Long hospId = db.queryLong("select hosp_id from T_AMCS_APS_RULE where id = ? ", m.getRuleId());
		return UserContext.runWithChangedHospId(hospId, ()->{
			return ruleService.refreshRuleFromGroup(m);
		});
	}
	
	
}