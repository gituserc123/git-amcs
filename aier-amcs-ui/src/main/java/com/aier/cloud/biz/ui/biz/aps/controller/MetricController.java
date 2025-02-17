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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.enums.PrescriptionTypeEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.aps.feign.MetricService;
import com.aier.cloud.biz.ui.biz.common.feign.DictService;
import com.aier.cloud.biz.ui.biz.common.feign.MedicalFeignService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.ui.template.directive.EnumDictResovler;
import com.aier.cloud.center.common.context.UserContext;
import com.google.common.collect.Lists;

/**
 * Metric
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Controller
@RequestMapping("/ui/amcs/aps/metric")
public class MetricController extends BaseController{

	@Autowired
	private MetricService metricService;
	@Autowired
	private MedicalFeignService medicalService;
	@Autowired
	private DictService basedService;
	@Resource DictService ds;
	@Resource InstitutionService is;

	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
    @GetMapping(value="/page/{name}")
    public String page(Map<String, Object> map, @PathVariable("name") String name, @RequestParam(value="id",required =false) Long id,
    		@RequestParam(value="hospId",required =false) Long hospId) {
		map.put("id", id);
		map.put("hospId", hospId);
		if("detail".equals(name)) {
			Map param = Maps.newHashMap("metricId", id);
			map.put("bean", metricService.getByCondition(param).get(0));
		}
		if(name.equals("ruleManage")) {
			Map param = Maps.newHashMap("metricId", id);
			Map bean = (Map)metricService.getByCondition(param).get(0);
			String ruleExtType = MapUtils.getString(bean, "ruleExtType");
			if(StringUtils.equalsIgnoreCase(ruleExtType,"B")){
				throw BizException.error("本指标计分拓展类型为【固定套餐】，无需集团设定规则，医院HR根据院内的固定套餐设置");
			}
			map.put("bean", bean);
		}
		
		if(name.equals("ruleManageHosp")) {
			Map param = Maps.newHashMap("metricId", id);
			param.put("hospId", hospId);
			Map bean = (Map)metricService.getByCondition(param).get(0);
			String ruleExtType = MapUtils.getString(bean, "ruleExtType");
			map.put("bean", bean);
		}
		
		if(name.equals("indexHosp")) {
			map.put("hospId", UserContext.getTenantId());
			if(Objects.equals(UserContext.getTenantId(),0L)) {
				//throw BizException.error("集团之人暂无编辑医院指标的权限");
			}
		}
        return "aps/metric/"+name;
    }
	
	@RequiresPermissions("ApsMetric:view")
    @GetMapping(value="/page/addMetric")
    public String addMetric(Map<String, Object> map,@RequestParam(value="id",required =false) Long id) {
		if(true) {
			throw BizException.warn("指标项需严格定义，涉及绩效计算开发，暂不支持从界面新增。可联系管理员从后台添加。");
		}
		map.put("id", id);
        return "aps/metric/addMetric";
    }
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getTree")
	public @ResponseBody Object getTree() {
		List<Map> result = ds.getAllSubListByRootParent(Arrays.asList("aps_config"));
		result.forEach(m -> {
			m.put("id", m.remove("typeCode"));
			m.put("pid", m.remove("parentTypeCode"));
			m.put("name", m.remove("typeDesc"));
		});
		return result;
	}
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getList")
	public @ResponseBody Object getList(@RequestParam Map m) {
		return metricService.getList(m);
	}
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getByCondition")
	public @ResponseBody Object getByCondition(@RequestParam Map m) {
		return metricService.getByCondition(m);
	}
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getRuleVersion")
	public @ResponseBody Object getRuleVersion(@RequestParam Map m) {
		return metricService.getRuleVersion(m);
	}
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getRuleDetail")
	public @ResponseBody Object getRuleDetail(@RequestParam Map m) {
		return metricService.getRuleDetail(m);
	}

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/updateMetric")
	public @ResponseBody Object updateMetric(@RequestParam Map m) {
		metricService.updateMetric(m);
		return success();
	}

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/query")
	public @ResponseBody Object query(@RequestParam Map param) {
		if(StringUtils.isBlank(MapUtils.getString(param, "q"))) {
			return Collections.EMPTY_LIST;
		}
		
		param.put("groupServiceName", param.get("q"));
		param.put("drugsDesc", param.get("q"));
		param.put("materialName", param.get("q"));
		param.put("rows",1000);
		PageResponse<Map> dcgMedicalService = basedService.dcgMedicalService(param);
		PageResponse<Map> dcgMaterial = basedService.dcgMaterial(param);
		PageResponse<Map> dcgDrugs = basedService.dcgDrugs(param);
		dcgDrugs.getRows().forEach(m ->{
			m.put("groupServiceName", m.remove("drugsDesc"));
			m.put("groupServiceCode", m.remove("groupDrugsCode"));
		});
		
		dcgMaterial.getRows().forEach(m ->{
			m.put("groupServiceName", m.remove("materialDesc"));
			m.put("groupServiceCode", m.remove("groupMaterialCode"));
		});
		List<Map> result = Lists.newArrayList();
		result.addAll(dcgMedicalService.getRows());
		result.addAll(dcgMaterial.getRows());
		result.addAll(dcgDrugs.getRows());
		return result;
	}
	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryOpr")
	public @ResponseBody Object queryOpr(@RequestParam Map m) {
		m.put("operationClassify", m.get("q"));
		return basedService.getDcgMeritsLevel(m);
	}
	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryMazui")
	public @ResponseBody Object queryMazui(@RequestParam Map m) {
		return basedService.getList("anesthesia_mode", "");
	}
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryAvis")
	public @ResponseBody Object queryAvis(@RequestParam Map m) {
		return basedService.getList("vision_sell_type", "");
	}
	
	@Resource EnumDictResovler r;
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryAvisG")
	public @ResponseBody Object queryAvisG(@RequestParam Map m) {
		return r.getDictList(PrescriptionTypeEnum.class.getName());
	}

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryDiseases")
	public @ResponseBody Object queryDiseases(@RequestParam Map m) {
		return basedService.getList("performance_diseases", "");
	}
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/getHosp")
	public @ResponseBody Object getHosp(@RequestParam Map m) {
		InstCondition c = new InstCondition();
		c.setQueryType("1");
		c.setInstType("4");
		c.setKey(MapUtils.getString(m, "q"));
		c.setInstId(100001L);
		return is.getInstByConditionForSelect(c);
	}
	

	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/autoComplete/queryHospJ")
	public @ResponseBody Object queryHospJ(@RequestParam Map param) {
		return metricService.queryHospJ(param);
	}
	
	@RequiresPermissions(logical=Logical.OR,value= {"ApsMetric:view","ApsMetricHosp:view","HospRuleQuery:view"})
	@RequestMapping(value = "/getRuleItemTypeSelect")
	public @ResponseBody Object getRuleItemTypeSelect(@RequestParam Map m) {
		return success(metricService.getRuleItemTypeSelect(m));
	}
}
