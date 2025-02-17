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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.enums.RuleUsingEnum;
import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.api.amcs.enums.ScoreFrequencyTypeEnum;
import com.aier.cloud.basic.api.domain.enums.YesNoEnum;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.MetricDto;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.ItemSourceService1;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.MetricServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleDetailServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleServiceImpl;
import com.aier.cloud.center.common.context.UserContext;

import io.swagger.annotations.Api;
 
/**
 * Metric
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Api("人资绩效计分规则表相关接口")
@Controller
@RequestMapping("/api/amcs/aps/metric")
public class MetricController extends BaseController{

	@Autowired
	private MetricServiceImpl metricService;
	@Autowired
	private RuleServiceImpl ruleServiceImpl;
	@Autowired
	private RuleDetailServiceImpl ruleDetailServiceImpl;

	@Autowired
	ItemSourceService1 itemSourceService1;

	@RequestMapping(value = "/queryHospJ")
	public @ResponseBody Object queryHospJ(@RequestBody Map param) {
		return itemSourceService1.queryHospJ(param);
		
	}

	@RequestMapping(value = "/getRuleItemTypeSelect")
	public @ResponseBody Object getRuleItemTypeSelect(@RequestBody MetricDto dto) {
		return metricService.getRuleItemTypeSelect(dto);
		
	}

	@RequestMapping(value = "/getList")
	public @ResponseBody List getList(@RequestBody MetricDto dto) {
		add("modifer", "t_sys_staff|id|name","modifer");
		add("ruleExtType", ScoreExtendTypeEnum.class);
		add("ruleFrequency", ScoreFrequencyTypeEnum.class);
		
		return UserContext.runWithChangedHospId(dto.getHospId(), ()->{
			return metricService.getList(dto);
		});
	}
	@RequestMapping(value = "/getByCondition")
	public @ResponseBody List getByCondition(@RequestBody MetricDto dto) {
		add("modifer", "t_sys_staff|id|name","modiferName");
		add("ruleExtType", ScoreExtendTypeEnum.class,"ruleExtTypeName");
		add("ruleFrequency", ScoreFrequencyTypeEnum.class,"ruleFrequencyName");
		return metricService.getByCondition(dto);
	}
	@RequestMapping(value = "/updateMetric")
	public @ResponseBody void updateMetric(@RequestBody Metric m) {
		metricService.updateMetric(m);
	}

	@RequestMapping(value = "/getRuleVersion")
	public @ResponseBody List getRuleVersion(@RequestBody MetricDto dto) {
		add("modifer", "t_sys_staff|id|name","modifer");
		add("isDefaultRule", RuleUsingEnum.class,"isDefaultRuleName");
		return UserContext.runWithChangedHospId(dto.getHospId(), ()->{
			return ruleServiceImpl.getRuleVersion(dto);
		});
	}
	

	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody List getRuleDetail(@RequestBody MetricDto dto) {
		add("modifer", "t_sys_staff|id|name","modifer");
		add("isDefaultRule", YesNoEnum.class,"isDefaultRule");
		return UserContext.runWithChangedHospId(dto.getHospId(), ()->{
			return ruleDetailServiceImpl.getRuleDetail(dto);
		});
	}
}