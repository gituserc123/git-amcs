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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aier.cloud.api.amcs.enums.RuleItemTypeEnum;
import com.aier.cloud.api.amcs.enums.RuleUsingEnum;
import com.aier.cloud.biz.service.biz.amcs.aps.dao.MetricMapper;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.MetricDto;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.service.MetricService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Metric
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class MetricServiceImpl extends ServiceImpl<MetricMapper, Metric> implements MetricService {

	@Resource RuleServiceImpl rs;
	
	public List getMetric(MetricDto dto) {
		return null;
	}

	public List getList(MetricDto dto) {
		if(StringUtils.isBlank(dto.getTypeCode())) {
			return Collections.EMPTY_LIST;
		}
		EntityWrapper w = new EntityWrapper<>();
		w.eq("type_code", dto.getTypeCode());
		if(StringUtils.isNotBlank(dto.getUsingSign())) {
			w.eq("using_sign", dto.getUsingSign());
		}
		w.orderAsc(Arrays.asList("metric_code"));
		List<Metric> metrics = this.baseMapper.selectList(w);
		metrics.forEach(m->{
			EntityWrapper ww = new EntityWrapper();
			ww.eq("metric_id", m.getId());
			ww.eq("is_default_rule", RuleUsingEnum._P.getEnumCode());
			ww.orderBy("rule_begin_date", false);
			List<Rule> rules = rs.selectList(ww);
			if(rules.size() > 0) {
				m.setpFlag("1");
				m.setpRuleId(rules.get(0).getId());
			}else {
				m.setpFlag("0");
			}
		});
		
		if(StringUtils.isNotBlank(dto.getpFlag())) {
			return metrics.stream().filter( m ->{
				return m.getpFlag().equals(dto.getpFlag());
			}).collect(Collectors.toList());
		}
		return metrics;
	}

	public List getByCondition(MetricDto dto) {
		Long hospId = UserContext.getTenantId();
		if(!RuleServiceImpl.isHospUser()) {
			hospId = dto.getHospId();
			if(hospId == null) {
				hospId = 0L;
			}
		}
		
		return UserContext.runWithChangedHospId(hospId, ()->{
			EntityWrapper w = new EntityWrapper<>();
			if(dto.getMetricId() != null) {
				w.eq("id", dto.getMetricId());
			}
			return this.selectList(w);
			
		});
	}

	public void updateMetric(Metric m) {
		if(m.getId() != null) {
			if(m.getAllowItemType() == null ) {
				m.setAllowItemType(" ");
			}
			if(m.getRemarks() != null ) {
				m.setRemarks(m.getRemarks().replaceAll("\"", "”"));
			}
			this.updateById(m);
		}else {
			
		}
	}

	public Object getRuleItemTypeSelect(MetricDto dto) {
		Metric m = this.selectById(dto.getMetricId());
		String allow = m.getAllowItemType();
		List<Map> result = Lists.newArrayList();
		if(StringUtils.isNotBlank(allow)) {
			String[] allows = allow.trim().split(",");
			for(String a : allows){
				RuleItemTypeEnum em = RuleItemTypeEnum.valueOf(a);
				Map mm = Maps.newHashMap();
				mm.put("valueCode", em.getEnumCode());
				mm.put("valueDesc", em.getEnumDesc());
				result.add(mm);
			}
		}
		return result;
	}

	
	
	
}

