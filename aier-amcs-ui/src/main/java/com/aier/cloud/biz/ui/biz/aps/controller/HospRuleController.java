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
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.aps.feign.MetricService;
import com.aier.cloud.biz.ui.biz.aps.feign.RuleService;

/**
 * Rule
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Controller
@RequestMapping("/ui/amcs/aps/hospRule")
public class HospRuleController extends BaseController{

	@Autowired
	private RuleService ruleService;
	@Autowired
	private MetricService metricService;

	@RequiresPermissions("ApsMetricHosp:view")
    @GetMapping(value="/page/addRuleHosp")
    public String addRule(Map<String, Object> map,  @RequestParam(value="metricId",required =false) Long metricId,
    		@RequestParam(value="hospId",required =false) Long hospId) {
		Map metric = (Map)metricService.getByCondition(Maps.newHashMap("metricId", metricId)).get(0);
		map.put("metric", metric);
		map.put("hospId", hospId);
		map.put("groupRuleRequired", !StringUtils.equalsIgnoreCase(MapUtils.getString(metric, "ruleExtType"),"B"));
		
		
        return "aps/metric/hospRule/addRuleHosp";
    }
	@RequiresPermissions("ApsMetricHosp:view")
    @GetMapping(value="/page/editRuleHosp")
    public String editRuleHosp(Map<String, Object> map,  @RequestParam(value="id") Long id,
    		@RequestParam(value="hospId",required =false) Long hospId) {
		map.put("bean", ruleService.getRule(id,hospId));
		map.put("hospId", hospId);
        return "aps/metric/hospRule/editRuleHosp";
    }
	

	@RequiresPermissions("ApsMetricHosp:view")
    @GetMapping(value="/page/editRuleDetailHosp")
    public String editRuleDetailHosp(Map<String, Object> map,  @RequestParam(value="ruleId") Long ruleId,
    		@RequestParam(value="hospId",required =false) Long hospId) {
		Map rule = ruleService.getRule(ruleId,hospId);
		rule.put("ruleExtTypeName", ScoreExtendTypeEnum.valueOf(MapUtils.getString(rule, "ruleExtType")).getEnumDesc());
		map.put("entity", rule);
		map.put("hospId", hospId);
		String ruleExtType = MapUtils.getString(rule, "ruleExtType");
		if((hospId==null || hospId.equals(0L)) && StringUtils.equalsIgnoreCase(ruleExtType,"B")){
			throw BizException.error("计分拓展类型为【固定套餐】，集团HR无需设定集团规则，医院HR根据院内的固定套餐设置");
		}
		
        return "aps/metric/hospRule/editRuledetailHosp" + ruleExtType ;
    }
	
	@RequiresPermissions("ApsMetricHosp:view")
    @GetMapping(value="/page/editScoreRangeHosp")
    public String editRuleRange(Map<String, Object> map) {
        return "aps/metric/hospRule/editScoreRangeHosp";
    }

	@RequiresPermissions("ApsMetricHosp:view")
	@RequestMapping(value = "/updateRule")
	public @ResponseBody Object updateRule(@RequestParam Map m) {
		ruleService.updateRule(m);
		return success();
	}
	

	@RequiresPermissions("ApsMetricHosp:view")
	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody Object getRuleDetail(@RequestParam Map m) {
		return ruleService.getRuleDetail(m);
	}

	@RequiresPermissions("ApsMetricHosp:view")
	@RequestMapping(value = "/updateRuleDetail")
	public @ResponseBody Object updateRuleDetail(@RequestBody Map m) {
		return success(ruleService.updateRuleDetail(m));
	}
	

	@RequiresPermissions("ApsMetricHosp:view")
	@RequestMapping(value = "/getGroupRuleList")
	public @ResponseBody Object getGroupRuleList(@RequestParam Map m) {
		return ruleService.getGroupRuleList(m);
	}
	
}
