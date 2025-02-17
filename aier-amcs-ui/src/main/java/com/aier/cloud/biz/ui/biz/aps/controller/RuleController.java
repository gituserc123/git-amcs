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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/ui/amcs/aps/rule")
public class RuleController extends BaseController{

	@Autowired
	private RuleService ruleService;
	@Autowired
	private MetricService metricService;
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
    @GetMapping(value="/page/addRule")
    public String addRule(Map<String, Object> map,  @RequestParam(value="metricId",required =false) Long metricId) {
		map.put("metric", metricService.getByCondition(Maps.newHashMap("metricId", metricId)).get(0));
        return "aps/metric/rule/addRule";
    }
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
    @GetMapping(value="/page/editRule")
    public String editRule(Map<String, Object> map,  @RequestParam(value="id") Long id) {
		map.put("bean", ruleService.getRule(id,null));
        return "aps/metric/rule/editRule";
    }
	

	@RequiresPermissions("ApsMetric:view")
    @GetMapping(value="/page/editRuleDetail")
    public String editRuleDetail(Map<String, Object> map,  @RequestParam(value="ruleId") Long ruleId) {
		Map rule = ruleService.getRule(ruleId,null);
		map.put("entity", rule);
		map.put("metric", metricService.getByCondition(Maps.newHashMap("metricId", MapUtils.getLong(rule, "metricId"))).get(0));
		String ruleExtType = MapUtils.getString(rule, "ruleExtType");
		if(StringUtils.equalsIgnoreCase(ruleExtType,"B")){
			throw BizException.error("计分拓展类型为【固定套餐】，集团HR无需集团设定规则，医院HR根据院内的固定套餐设置");
		}
		
        return "aps/metric/rule/editRuledetail" + ruleExtType ;
    }

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
    @GetMapping(value="/page/editScoreRange")
    public String editRuleRange(Map<String, Object> map) {
        return "aps/metric/rule/editScoreRange";
    }

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
	@RequestMapping(value = "/updateRule")
	public @ResponseBody Object updateRule(@RequestParam Map m) {
		ruleService.updateRule(m);
		return success();
	}
	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody Object getRuleDetail(@RequestParam Map m) {
		return ruleService.getRuleDetail(m);
	}

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
	@RequestMapping(value = "/updateRuleDetail")
	public @ResponseBody Object updateRuleDetail(@RequestBody Map m) {
		return success(ruleService.updateRuleDetail(m));
	}
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view"})
	@RequestMapping(value = "/refreshRuleFromGroup")
	public @ResponseBody Object refreshRuleFromGroup(@RequestBody Map m) {
		List<Map> result = ruleService.refreshRuleFromGroup(m);
		result.forEach(map ->{
			Object v1 = map.get("itemCode");
			Object v2 = map.get("itemName");
			map.clear();
			map.put(v1, v2);
		});
		if(result.size() == 0) {
			return success(Arrays.asList("您的指标与集团版本一致，无需刷新。"));
		}
		return success(Arrays.asList("刷新成功，新增"+result.size()+"条 ：</br>" + result));
	}
	
}
