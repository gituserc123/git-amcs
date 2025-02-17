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
package com.aier.cloud.biz.service.biz.amcs.aps.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.sys.feign.BasedService;
import com.aier.cloud.center.common.context.UserContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Service
public class ItemSourceService1 {
	
	
	@Value("${spring.profiles.active}")
	private String profile;

	@Resource JdbcHelper db;
	@Resource ItemSourceServiceSit sit;
	@Resource BasedService basedService;
	
	public List<Map> queryItems(Rule rule, Metric metric){
		if("sit".equalsIgnoreCase(profile)) {
			return sit.queryItems(rule, metric);
		}
		
		List<Map> items = Lists.newArrayList();
		//D("D", "手术分类"), 
		if(ScoreExtendTypeEnum.D.is(metric.getRuleExtType())) {
			Map m = Maps.newHashMap();
			m.put("rows", 10000);
			items = (List)basedService.getDcgMeritsLevel(m).getRows();
			
			//    A("A", "收费项目"), 
		}else if(ScoreExtendTypeEnum.A.is(metric.getRuleExtType())) {
			items = basedService.getHospItems("1", Sets.newHashSet(metric.getFinanceCode()));
		}else if(ScoreExtendTypeEnum.I.is(metric.getRuleExtType())) {
			items = sit.queryItems(rule, metric);
		}
		return items;
	}

	public List<Map> getHospItems(Set<String> itemCodes) {
		if("sit".equalsIgnoreCase(profile)) {
			return sit.getHospItems(itemCodes);
		}
		return basedService.getHospItems(null, itemCodes);
	}
	
	public List<Map> queryHospJ(Map param) {
		
		if(StringUtils.isBlank(MapUtils.getString(param, "q"))
				||StringUtils.isBlank(MapUtils.getString(param, "id"))) {
			return Collections.EMPTY_LIST;
		}
		
		
		param.put("serviceName", param.get("q"));
		param.put("drugsName", param.get("q"));
		param.put("materialName", param.get("q"));
		param.put("rows",500);

		Long ruleId = MapUtils.getLong(param, "id");
		Long hospId = db.queryLong("select hosp_id from t_amcs_aps_rule where id = ?", ruleId);
		
		if("sit".equalsIgnoreCase(profile)) {
			return sit.queryHospJ(hospId, MapUtils.getString(param, "q"));
		}
		
		return UserContext.runWithChangedHospId(hospId, ()->{
			PageResponse<Map> dcgMedicalService = basedService.dchMedicalService(param);
			PageResponse<Map> dcgMaterial = basedService.dchMaterial(param);
			PageResponse<Map> dcgDrugs = basedService.dchDrugs(param);
			dcgDrugs.getRows().forEach(m ->{
				m.put("serviceName", m.remove("drugsName"));
				m.put("serviceCode", m.remove("drugsCode"));
			});
			
			dcgMaterial.getRows().forEach(m ->{
				m.put("serviceName", m.remove("materialName"));
				m.put("serviceCode", m.remove("materialCode"));
			});
			List<Map> result = Lists.newArrayList();
			result.addAll(dcgMedicalService.getRows());
			result.addAll(dcgMaterial.getRows());
			result.addAll(dcgDrugs.getRows());
			return result;
			
		});
	}

}

