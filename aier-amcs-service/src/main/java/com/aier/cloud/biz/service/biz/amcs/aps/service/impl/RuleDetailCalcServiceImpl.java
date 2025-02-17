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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum.CalcType;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.core.base.util.DbUtil;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.aps.dao.RuleDetailCalcMapper;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailCalc;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailCalcService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;

/**
 * RuleDetailCalc
 * 人资绩效计分规则详情表(用于计算拓展)
 * @author 爱尔眼科
 * @since 2023-03-27 14:59:08
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class RuleDetailCalcServiceImpl extends ServiceImpl<RuleDetailCalcMapper, RuleDetailCalc> implements RuleDetailCalcService {

	@Resource JdbcHelper db;

	public void saveForCalc(Rule rule, Metric metric) {
		if(rule == null || metric == null) {
			throw BizException.error("配置错误,请xiaokek检查");
		}
		
		List<RuleDetail> ruleDetails = db.queryBeanList("select * from t_Amcs_Aps_Rule_Detail w where w.rule_id = ?", RuleDetail.class, rule.getId());
		if(CollectionUtils.isNotEmpty(ruleDetails)){
			//本指标特例的处理
			if(ScoreExtendTypeEnum.J.is(metric.getRuleExtType())) {
				db.update("delete from t_Amcs_Aps_Rule_Detail_calc w where w.rule_id = ?", rule.getId());
				
				List<RuleDetailCalc> ruleCalcs = Lists.newArrayList();
				for(RuleDetail ruleDetail : ruleDetails){
					RuleDetailCalc rc = new RuleDetailCalc();
					try{
						BeanUtils.copyProperties(rc, ruleDetail);
					} catch(Exception e){
					}
					//本指标的处理
					if("E".equals(ruleDetail.getItemCode())) {
						rc.setCalcType(Integer.valueOf(CalcType._2.getEnumCode()));
						rc.setItemCode(metric.getFinanceCode());
					}else {
						rc.setCalcType(Integer.valueOf(CalcType._1.getEnumCode()));
					}
					rc.setRuleDetailId(ruleDetail.getId());
					rc.setId(null);
					ruleCalcs.add(rc);
				}
			
				DbUtil.insertBatch(this, ruleCalcs);
			}
		}
		
	}

	public void saveForCalc(Rule rule) {
		Metric metric = db.queryBean("select * from T_AMCS_APS_METRIC w where w.id = ?", Metric.class, rule.getMetricId());
		this.saveForCalc(rule, metric);
	}
}

