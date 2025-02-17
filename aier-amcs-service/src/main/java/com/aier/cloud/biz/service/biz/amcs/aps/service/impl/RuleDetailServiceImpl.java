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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aier.cloud.api.amcs.domain.RefreshResult;
import com.aier.cloud.api.amcs.enums.RuleItemTypeEnum;
import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.util.DbUtil;
import com.aier.cloud.biz.service.biz.amcs.aps.dao.RuleDetailMapper;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.MetricDto;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.enums.RegistMarkEnum;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailService;
import com.aier.cloud.biz.service.biz.sys.feign.BasedService;
import com.aier.cloud.biz.service.biz.sys.feign.SystemService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class RuleDetailServiceImpl extends ServiceImpl<RuleDetailMapper, RuleDetail> implements RuleDetailService {

	@Resource JdbcHelper db;
	@Resource BasedService basedService;
	@Resource SystemService systemService;
	@Resource RuleServiceImpl ruleService;
	@Resource ItemSourceService1 itemService;
	@Resource RuleDetailCalcServiceImpl ruleCalc;
	@Resource MetricServiceImpl metricService;

	public List getRuleDetail(MetricDto dto) {
		EntityWrapper w = new EntityWrapper<>();
		w.eq("rule_id", dto.getRuleId());
		return this.selectList(w);
	}
	
	public RefreshResult refreshAllGroup() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("  from t_amcs_aps_rule a ");
		sql.append(" where a.metric_code in ");
		sql.append("       (select w.metric_code ");
		sql.append("          from t_amcs_aps_metric w ");
		sql.append("         where w.rule_ext_type = 'A' ");
		sql.append("           and w.finance_code is not null) ");
		sql.append("   and a.hosp_id = 0 ");
		sql.append("   and a.is_default_rule = 1 ");

		List<Rule> rds = db.queryBeanList(sql.toString(), Rule.class);
		
		for(Rule rule : rds){
			this.refreshGroupRule(rule.getId());
		}
		
		return null;
	}
	
	public RefreshResult refreshAllHosp() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("  from t_amcs_aps_rule a ");
		sql.append(" where a.metric_code in ");
		sql.append("       (select w.metric_code ");
		sql.append("          from t_amcs_aps_metric w ");
		sql.append("         where w.rule_ext_type = 'A' ");
		sql.append("           and w.finance_code is not null) ");
		sql.append("   and a.hosp_id <> 0 ");
		sql.append("   and a.is_default_rule = 1 ");

		List<Rule> rds = db.queryBeanList(sql.toString(), Rule.class);
		for(Rule rule : rds){
			refreshHospRule(rule);
		}
		
		return null;
	}
	
	public RefreshResult refreshHosp(Long hospId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("  from t_amcs_aps_rule a ");
		sql.append(" where a.metric_code in ");
		sql.append("       (select w.metric_code ");
		sql.append("          from t_amcs_aps_metric w ");
		sql.append("         where w.rule_ext_type = 'A' ");
		sql.append("           and w.finance_code is not null) ");
		if(hospId == null) {
			sql.append("   and a.hosp_id <> 0 ");
		}else {
			sql.append("   and a.hosp_id = ? ");
		}
		sql.append("   and a.is_default_rule = 1 ");

		List<Rule> rds = null;
		if(hospId == null) {
			rds = db.queryBeanList(sql.toString(), Rule.class);
		}else {
			rds = db.queryBeanList(sql.toString(), Rule.class,hospId);
		}
		for(Rule rule : rds){
			refreshHospRule(rule);
		}
		
		return null;
	}

	public RefreshResult<RuleDetail> refreshHospRule(Rule rule) {
		Metric metric = db.queryBean("select * from T_AMCS_APS_METRIC w where w.id = ?", Metric.class, rule.getMetricId());
		List<RuleDetail> ruleDetails = db.queryBeanList("select * from T_AMCS_APS_RULE_DETAIL w where w.rule_id = ?", RuleDetail.class, rule.getId());
		Map<String, RuleDetail> ruleDetailMap = Maps.newHashMap();
		ruleDetails.forEach(rd ->{
			ruleDetailMap.put(rd.getItemCode(), rd);
		});

		List<RuleDetail> result = Lists.newArrayList();
		if(ScoreExtendTypeEnum.A.is(metric.getRuleExtType())) {
			UserContext.runWithChangedHospId(rule.getHospId(), ()->{
				List<RuleDetail> newRds = ruleService.initRuleDetail(rule);
				for(RuleDetail hd : newRds) {
					if(!ruleDetailMap.containsKey(hd.getItemCode())) {
						hd.setItemScore(BigDecimal.valueOf(0));
						hd.setModifer(UserContext.getUserId());
						hd.setUsingSign(2);
						result.add(hd);
					}
				}
				DbUtil.insertBatch(this, result);
				ruleCalc.saveForCalc(rule, metric);
				return null;
			});
			
		}

		sendApsRefreshMessage(metric, rule, result);
		return RefreshResult.ok(result);
	}
	
	private void sendApsRefreshMessage(Metric metric, Rule rule, List<RuleDetail> result) {
		if(result.size() > 0) {
			Map msg = Maps.newHashMap();
			
			
			List msgs = Lists.newArrayList();
			for(int i = 0; i< result.size() && i < 4; i++) {
				Map m = Maps.newHashMap();
				m.put("epItemId", rule.getMetricCode());
				m.put("hospId", rule.getHospId());
				m.put("extType", metric.getRuleExtType());
				m.put("ruleItemCode", result.get(i).getItemCode());
				m.put("ruleItemName", result.get(i).getItemName());
				msgs.add(m);
			}
			msg.put("list", msgs);
			try {
				systemService.sendApsRefreshMessage(msg);
			}catch (Exception e) {
			}
		}
	}

	public RefreshResult refreshHospRuleWithOutGroup(Rule rule) {
		return this.refreshHospRuleWithOutGroup(rule, false);
	}
	
	/**
	 * 直接刷医院的指标而不管集团的配置是什么
	 * @param rule
	 * @return
	 */
	public RefreshResult refreshHospRuleWithOutGroup(Rule rule, boolean deleteAll) {
		Metric metric = db.queryBean("select * from T_AMCS_APS_METRIC w where w.id = ?", Metric.class, rule.getMetricId());
		
		//无视以前的数据，直接全刷，一般用于第一次
		if(deleteAll) {
			db.update("delete from T_AMCS_APS_RULE_DETAIL w where w.rule_id = ?", rule.getId());
		}
		List<RuleDetail> ruleDetails = db.queryBeanList("select * from T_AMCS_APS_RULE_DETAIL w where w.rule_id = ?", RuleDetail.class, rule.getId());
		Map<String, RuleDetail> ruleDetailMap = Maps.newHashMap();
		ruleDetails.forEach(rd ->{
			ruleDetailMap.put(rd.getItemCode(), rd);
		});

		List<RuleDetail> result = Lists.newArrayList();
		if(ScoreExtendTypeEnum.I.is(metric.getRuleExtType())) {
			UserContext.runWithChangedHospId(rule.getHospId(), ()->{
				List<Map> items = itemService.queryItems(rule, metric);
				for(Map item : items) {
					if(!ruleDetailMap.containsKey(MapUtils.getString(item, "ITEM_CODE"))) {
						RuleDetail hd = new RuleDetail();
						hd.setFinanceCodeValue(MapUtils.getString(item, "FINANCE_CODE_VALUE"));
						hd.setFinanceCode(MapUtils.getString(item, "FINANCE_CODE"));
						hd.setRuleId(rule.getId());
						hd.setRuleItemType(RuleItemTypeEnum.A.getEnumCode());
						hd.setItemId(null);
						hd.setItemCode(MapUtils.getString(item, "ITEM_CODE"));
						hd.setItemName(MapUtils.getString(item, "ITEM_NAME"));
						hd.setItemScore(metric.getDefaultPoint());
						hd.setModifer(UserContext.getUserId() == null ? 1L : UserContext.getUserId());
						result.add(hd);
					}
				}
				DbUtil.insertBatch(this, result);
				return null;
			});
			
		}

		return RefreshResult.ok(result);
	}
	
	public RefreshResult refreshGroupRule(Long ruleId) {
		Rule rule = db.queryBean("select * from T_AMCS_APS_RULE w where w.id = ?", Rule.class, ruleId);
		Metric metric = db.queryBean("select * from T_AMCS_APS_METRIC w where w.id = ?", Metric.class, rule.getMetricId());
		List<RuleDetail> ruleDetails = db.queryBeanList("select * from T_AMCS_APS_RULE_DETAIL w where w.rule_id = ?", RuleDetail.class, ruleId);
		final RuleDetail source;
		if(ruleDetails.size() >0) {
			source = ruleDetails.get(0);
		}else {
			source = null;
		}
		Map<String, RuleDetail> ruleDetailMap = Maps.newHashMap();
		Map<String, String> financeCodeMap = Maps.newHashMap();
		ruleDetails.forEach(rd ->{
			ruleDetailMap.put(rd.getItemCode(), rd);
			financeCodeMap.put(rd.getFinanceCode(), rd.getFinanceCodeValue());
		});
		List<RuleDetail> rds = Lists.newArrayList();
		return UserContext.runWithChangedHospId(rule.getHospId(), ()->{
			if(ScoreExtendTypeEnum.D.is(metric.getRuleExtType())) {
				//刷手术分类
				List<Map> items = itemService.queryItems(rule, metric);
				items.forEach(opr ->{
					String code = MapUtils.getString(opr, "OPERATIONCLASSIFYCODE", "---");
					if(!ruleDetailMap.containsKey(code)) {
						RuleDetail hd = new RuleDetail();
						hd.setFinanceCodeValue(null);
						hd.setRuleId(rule.getId());
						hd.setRuleItemType(source == null ? "D": source.getRuleItemType());
						hd.setItemId(MapUtils.getLong(opr, "ID"));
						hd.setItemCode(MapUtils.getString(opr, "OPERATIONCLASSIFYCODE"));
						hd.setItemName(MapUtils.getString(opr, "OPERATIONCLASSIFY"));
						hd.setItemLevel(MapUtils.getString(opr, "OPERATIONGRADE"));
						hd.setItemIcd(MapUtils.getString(opr, "DISEASETYPENAME"));
						//粟楚娴(本部) 5-30 上午11:29:03
						//如果是新刷进去的术式   分值就为0
						hd.setItemScore(new BigDecimal(0));
						hd.setModifer(UserContext.getUserId());
						hd.setUsingSign(2);
						rds.add(hd);
					}
				});
				//收费项目
			}else if(ScoreExtendTypeEnum.A.is(metric.getRuleExtType())) {
				if(StringUtils.isNotBlank(metric.getFinanceCode())) {
					//刷收费项目
					List<Map> items = itemService.queryItems(rule, metric);
					for(Map item : items){
						String itemCode = MapUtils.getString(item, "ITEM_CODE");
						if(!ruleDetailMap.containsKey(itemCode)) {
							RuleDetail hd = new RuleDetail();
							hd.setFinanceCodeValue(source == null ? metric.getFinanceCode() : source.getFinanceCodeValue());
							hd.setFinanceCode(metric.getFinanceCode());
							hd.setRuleId(rule.getId());
							hd.setRuleItemType(source == null ? "A": source.getRuleItemType());
							hd.setItemCode(itemCode);
							hd.setItemId(MapUtils.getLong(item, "ID", 0L));
							hd.setItemName(MapUtils.getString(item, "ITEM_NAME"));
							hd.setItemScore(BigDecimal.valueOf(0));
							hd.setModifer(UserContext.getUserId());
							hd.setUsingSign(2);
							
							rds.add(hd);
						}
					}
					
				}
			}
			else if(ScoreExtendTypeEnum.I.is(metric.getRuleExtType())) {
				List<Map> items = itemService.queryItems(rule, metric);
				for(Map item : items){
					String itemCode = MapUtils.getString(item, "ITEM_CODE");
					String financeCode = MapUtils.getString(item, "FINANCE_CODE");
					String financeCodeValue = MapUtils.getString(item, "FINANCE_CODE_VALUE");
					if(!ruleDetailMap.containsKey(itemCode)) {
						RuleDetail hd = new RuleDetail();
						hd.setFinanceCodeValue(financeCodeValue);
						hd.setFinanceCode(financeCode);
						hd.setRuleId(rule.getId());
						hd.setRuleItemType(source == null ? "A": source.getRuleItemType());
						hd.setItemCode(itemCode);
						hd.setItemId(MapUtils.getLong(item, "ID", 0L));
						hd.setItemName(MapUtils.getString(item, "ITEM_NAME"));
						hd.setItemScore(BigDecimal.valueOf(0));
						hd.setModifer(UserContext.getUserId());
						hd.setUsingSign(2);
						
						rds.add(hd);
					}
				}
			}
			
			DbUtil.insertBatch(this, rds);
			
			ruleCalc.saveForCalc(rule, metric);
			return RefreshResult.ok(rds);
		});
		
	}

	/**
	 * 新建集团规则初始化用
	 * @param rule
	 * @return
	 */
	public List<RuleDetail> initGroupRuleDetail(Rule rule) {
		Long mid = rule.getMetricId();
		Metric metric = metricService.selectById(mid);
		List<RuleDetail> rds = Lists.newArrayList();
		 if(ScoreExtendTypeEnum.contains(metric.getRuleExtType(), ScoreExtendTypeEnum.E,ScoreExtendTypeEnum.J)) {
			//本指标
			RuleDetail hd = new RuleDetail();
			hd.setFinanceCodeValue("");
			hd.setRuleId(rule.getId());
			hd.setRuleItemType("元".equals(metric.getUnitName()) ?RuleItemTypeEnum.A.getEnumCode() :RuleItemTypeEnum.D.getEnumCode());
			hd.setItemId(metric.getId());
			hd.setItemCode(ScoreExtendTypeEnum.E.getEnumCode());
			hd.setItemName(ScoreExtendTypeEnum.E.getEnumDesc());
			hd.setItemScore(metric.getDefaultPoint());
			hd.setModifer(UserContext.getUserId());
			rds.add(hd);
			
		}else if(ScoreExtendTypeEnum.contains(metric.getRuleExtType(), ScoreExtendTypeEnum.K)) {
			/**
			 * 2、“初诊量”、“复诊量”指标的拓展类型为“绩效挂号分类”下面分类为“”
				ahis标签管理中增加“绩效挂号分类”将8个详细分类与收费项目进行关联（俊杰为人资开放权限）
				aps增加两个指标，取消10001-10008指标
			 */
			List<Metric> ms = db.queryBeanList("select * from T_AMCS_APS_METRIC w where w.metric_code in ('10001',"
					+ "'10002','10003','10004','10005','10006','10007','10008')", Metric.class);
			
			for(Metric mm : ms){
				RuleDetail hd = new RuleDetail();
				hd.setFinanceCodeValue("");
				hd.setRuleId(rule.getId());
				hd.setRuleItemType(RuleItemTypeEnum.D.getEnumCode());
				hd.setItemId(mm.getId());
				hd.setItemCode(RegistMarkEnum.findMark(mm.getMetricCode()));
				hd.setItemName(mm.getMetricName());
				hd.setItemScore(mm.getDefaultPoint());
				hd.setModifer(UserContext.getUserId());
				rds.add(hd);
			}
		}else if(ScoreExtendTypeEnum.contains(metric.getRuleExtType(), ScoreExtendTypeEnum.L)) {
			/**
			 * 角膜接触镜处方  切换计分扩展类型为 ：      L        角塑初复配
				1        初配
				2        复配
			 */
			RuleDetail hd = new RuleDetail();
			hd.setFinanceCodeValue("");
			hd.setRuleId(rule.getId());
			hd.setRuleItemType(RuleItemTypeEnum.D.getEnumCode());
			hd.setItemId(1L);
			hd.setItemCode("1");
			hd.setItemName("初配");
			hd.setItemScore(BigDecimal.ZERO);
			hd.setModifer(UserContext.getUserId());
			rds.add(hd);
			hd = new RuleDetail();
			hd.setFinanceCodeValue("");
			hd.setRuleId(rule.getId());
			hd.setRuleItemType(RuleItemTypeEnum.D.getEnumCode());
			hd.setItemId(2L);
			hd.setItemCode("2");
			hd.setItemName("复配");
			hd.setItemScore(BigDecimal.ZERO);
			hd.setModifer(UserContext.getUserId());
			rds.add(hd);
		}else if(ScoreExtendTypeEnum.contains(metric.getRuleExtType(), ScoreExtendTypeEnum.A,ScoreExtendTypeEnum.I)) {
			/**
			 * 晶体类型 沈俊杰确定 finance_code like 'CL010101%'
			 */
			List<Map> items = itemService.queryItems(rule, metric);
			
			for(Map item : items){
				RuleDetail hd = new RuleDetail();
				hd.setFinanceCodeValue(MapUtils.getString(item, "FINANCE_CODE_VALUE"));
				hd.setFinanceCode(MapUtils.getString(item, "FINANCE_CODE"));
				hd.setRuleId(rule.getId());
				if(Objects.equals("元", metric.getUnitName())) {
					hd.setRuleItemType(RuleItemTypeEnum.A.getEnumCode());
				}else {
					hd.setRuleItemType(RuleItemTypeEnum.D.getEnumCode());
				}
				hd.setItemId(null);
				hd.setItemCode(MapUtils.getString(item, "ITEM_CODE"));
				hd.setItemName(MapUtils.getString(item, "ITEM_NAME"));
				hd.setItemScore(metric.getDefaultPoint());
				hd.setModifer(UserContext.getUserId());
				rds.add(hd);
			}
		}
		
		return rds;
	}
	
}

