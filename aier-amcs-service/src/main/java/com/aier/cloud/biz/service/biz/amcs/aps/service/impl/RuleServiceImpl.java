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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aier.cloud.api.amcs.enums.RuleItemTypeEnum;
import com.aier.cloud.api.amcs.enums.RuleUsingEnum;
import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.basic.api.response.domain.outp.OrderTempl;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.core.base.util.SysUtil;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.util.DbUtil;
import com.aier.cloud.biz.service.biz.amcs.aps.dao.RuleMapper;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.MetricDto;
import com.aier.cloud.biz.service.biz.amcs.aps.dto.RuleDto;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailRange;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleService;
import com.aier.cloud.biz.service.biz.sys.feign.BasedService;
import com.aier.cloud.biz.service.biz.sys.feign.InpRegistService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Rule
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleService {
    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


	@Resource RuleDetailServiceImpl detailService;
	@Resource ItemSourceService1 itemService;
	@Resource MetricServiceImpl metricService;
	@Resource RuleDetailRangeServiceImpl rangeService;
	@Resource JdbcHelper db;
	@Resource InpRegistService medicalService;
	@Resource RuleDetailCalcServiceImpl ruleCalc;
	
	public List getRuleVersion(MetricDto dto) {
		EntityWrapper w = new EntityWrapper<>();
		w.eq("metric_id", dto.getMetricId());
		if(StringUtils.equals("0", dto.getUsingSign())) {
			w.in("is_default_rule", Arrays.asList(RuleUsingEnum._4.getEnumCode()));
		}else if(StringUtils.equals("1", dto.getUsingSign())) {
			w.in("is_default_rule", Arrays.asList(RuleUsingEnum._0.getEnumCode(),RuleUsingEnum._P.getEnumCode(),RuleUsingEnum._T.getEnumCode()));
		}
		w.orderBy(" modify_date desc");
		return this.selectList(w);
	}

	public void updateRule(Rule m) {
		m.setHospId(null);
		checkRule(m);
		m.setModifer(UserContext.getUserId());
		if(m.getId() != null) {
			this.updateById(m);
		}else {
			this.insert(m);
			if(isHospUser()) {
				List<RuleDetail> hospRds = initRuleDetail(m);
				DbUtil.insertBatch(detailService, hospRds);
			}else {
				List<RuleDetail> groupRds = detailService.initGroupRuleDetail(m);
				DbUtil.insertBatch(detailService, groupRds);
			}
		}
		ruleCalc.saveForCalc(m);
	}

	
	

	private void checkRule(Rule m) {
		EntityWrapper w = new EntityWrapper<>();
		if(m.getId() != null) {
			w.ne("id", m.getId());
		}
		w.eq("is_default_rule", m.getIsDefaultRule());
		w.eq("metric_id", m.getMetricId());
		int count = this.selectCount(w);
		if(count > 0 && RuleUsingEnum._T.is(m.getIsDefaultRule())) {
			throw BizException.error(RuleUsingEnum._T.getEnumDesc() + "最多维护一条！");
		}
	}

	/**
	 * 医院创建规则时，需要从集团下载规则
	 * @param m
	 */
	public List<RuleDetail> initRuleDetail(Rule m) {
		Rule groupRule = UserContext.runWithChangedHospId(0L, ()->{
			if(m.getGroupRuleId() == null) {
				return null;
			}
			return this.selectById(m.getGroupRuleId());
		});
		Metric metric = metricService.selectById(m.getMetricId());
		
		//收费项目
		if(ScoreExtendTypeEnum.A.is(metric.getRuleExtType())) {
			List<RuleDetail> rds = db.queryBeanList("select * from t_amcs_aps_rule_detail w where w.rule_id = ? and w.hosp_id = 0", RuleDetail.class, groupRule.getId());
			Set<String> itemCodes = Sets.newHashSet();
			rds.forEach(rd -> {
				itemCodes.add(rd.getItemCode());
			});
			Map<String,Long> hospItemCodeOfId = Maps.newHashMap();
			
			//用于处理xx_xxxxxx_xx这样的省区编码
			Map<String,String> provinceCode = Maps.newHashMap();
			List<Map> hospItems = itemService.getHospItems(itemCodes);
			hospItems.forEach(hi -> {
				if(StringUtils.isNotBlank(MapUtils.getString(hi, "TRUE_CODE"))) {
					if(!MapUtils.getString(hi, "TRUE_CODE").equalsIgnoreCase(MapUtils.getString(hi, "ITEM_CODE")) ) {
						provinceCode.put(MapUtils.getString(hi, "ITEM_CODE"), MapUtils.getString(hi, "TRUE_CODE"));
					}
				}
				
				hospItemCodeOfId.put(MapUtils.getString(hi, "ITEM_CODE"), MapUtils.getLong(hi, "ID"));
			});
			
			List<RuleDetail> hospRds = Lists.newArrayList();
			rds.forEach(rd -> {
				if(hospItemCodeOfId.containsKey(rd.getItemCode())) {
					RuleDetail hd = new RuleDetail();
					hd.setFinanceCodeValue(rd.getFinanceCodeValue());
					hd.setRuleId(m.getId());
					hd.setRuleItemType(rd.getRuleItemType());
					hd.setItemId(hospItemCodeOfId.get(rd.getItemCode()));
					hd.setItemCode(rd.getItemCode());
					hd.setItemName(rd.getItemName());
					hd.setItemScore(rd.getItemScore());
					hd.setModifer(UserContext.getUserId());
					hospRds.add(hd);
				}
				
				//用于处理xx_xxxxxx_xx这样的省区编码
				for(Map.Entry<String, String> en : provinceCode.entrySet()) {
					if(StringUtils.equalsIgnoreCase(en.getValue(),rd.getItemCode())) {
						RuleDetail hd = new RuleDetail();
						hd.setFinanceCodeValue(rd.getFinanceCodeValue());
						hd.setRuleId(m.getId());
						hd.setRuleItemType(rd.getRuleItemType());
						hd.setItemCode(en.getKey());
						hd.setItemId(hospItemCodeOfId.get(hd.getItemCode()));
						hd.setItemName(rd.getItemName());
						hd.setItemScore(rd.getItemScore());
						hd.setModifer(UserContext.getUserId());
						hospRds.add(hd);
					}
				}
			});
			
			return hospRds;
			
			//直接复制集团版本
		}else if(Arrays.asList("C","D","E","F","G","H","I","J","K","L").contains(metric.getRuleExtType())){
			List<RuleDetail> rds = db.queryBeanList("select * from t_amcs_aps_rule_detail w where w.rule_id = ? and w.hosp_id = 0", RuleDetail.class, groupRule.getId());
			List<RuleDetail> hospRds = Lists.newArrayList();
			rds.forEach(rd -> {
				RuleDetail hd = new RuleDetail();
				hd.setFinanceCodeValue(rd.getFinanceCodeValue());
				hd.setRuleId(m.getId());
				hd.setRuleItemType(rd.getRuleItemType());
				hd.setItemId(rd.getItemId());
				hd.setItemCode(rd.getItemCode());
				hd.setItemName(rd.getItemName());
				hd.setItemLevel(rd.getItemLevel());
				hd.setItemIcd(rd.getItemIcd());
				hd.setItemScore(rd.getItemScore());
				hd.setModifer(UserContext.getUserId());
				hd.setFinanceCode(rd.getFinanceCode());
				hospRds.add(hd);
			});
			return hospRds;
			
			
			//固定套餐
		}else if(Arrays.asList("B").contains(metric.getRuleExtType())){
			List<RuleDetail> hospRds = Lists.newArrayList();
			List<OrderTempl> ots = medicalService.getFixedSetMealForView();
			ots.forEach(rd -> {
				RuleDetail hd = new RuleDetail();
				hd.setFinanceCodeValue(null);
				hd.setRuleId(m.getId());
				hd.setRuleItemType(RuleItemTypeEnum.D.getEnumCode());
				hd.setItemId(rd.getId());
				hd.setItemCode(rd.getId().toString());
				hd.setItemName(rd.getTemplName());
				hd.setItemLevel(rd.getTemplTypeName());
				hd.setItemIcd(null);
				hd.setItemScore(new BigDecimal(0));
				//粟楚娴(本部) 5-30 上午11:29:03
				//默认配置无套餐  最好是自己添加
				hd.setUsingSign(0);
				hd.setModifer(UserContext.getUserId());
				hospRds.add(hd);
			});
			return hospRds;
		}else {
			throw BizException.error("不支持的拓展类型"+metric.getRuleExtType());
		}
	}

	public List getRuleDetail(RuleDto m) {
		Rule thisRule = this.selectById(m.getId());
		EntityWrapper w = new EntityWrapper<>();
		w.eq("rule_id", m.getId());
		if(CollectionUtils.isNotEmpty(m.getItemCodes())) {
			DbUtil.in(w, "item_code", m.getItemCodes());
		}
		w.orderBy("item_code");
		List<RuleDetail> rds = detailService.selectList(w);
		if(CollectionUtils.isNotEmpty(rds)) {
			
			Set<Long> rdIds = SysUtil.getEntityIds(rds, "getId");
			EntityWrapper ww = new EntityWrapper();
			DbUtil.in(ww, "detail_id", rdIds);
			List<RuleDetailRange> rdRanges = rangeService.selectList(ww);
			Map<Long, List<RuleDetailRange>> rdRangesMap = Maps.newHashMap();
			rdRanges.forEach(rd ->{
				if(rdRangesMap.containsKey(rd.getDetailId())) {
					rdRangesMap.get(rd.getDetailId()).add(rd);
				}else {
					rdRangesMap.put(rd.getDetailId(), Lists.newArrayList());
					rdRangesMap.get(rd.getDetailId()).add(rd);
				}
			});
			Set<String> dbItemCodes = Sets.newLinkedHashSet();
			rds.forEach(rd ->{
				dbItemCodes.add(rd.getItemCode());
				rd.setRangeJson(JsonUtil.toJson(rdRangesMap.getOrDefault(rd.getId(), Collections.EMPTY_LIST)));
			});
			
			
			/**
			 * 医院查规则需要把集团的规则整合一起显示
			 */
			if(isHospUser()) {
				
				Metric metric = metricService.selectById(thisRule.getMetricId());
				Long groupRuleId = thisRule.getGroupRuleId();
				if(needMergeGroup(metric)) {
					Rule groupRule = UserContext.runWithChangedHospId(0L, ()->{
						return this.selectById(groupRuleId);
					});
					
					List<RuleDetail> groupRds = UserContext.runWithChangedHospId(0L, ()->{
						RuleDto nm = new RuleDto();
						nm.setId(groupRule.getId());
						nm.setItemCodes(dbItemCodes);
						return this.getRuleDetail(nm);
					});
					
					List<Map> hospItems = itemService.getHospItems(dbItemCodes);
					Map<String, Object> prices = Maps.newHashMap();
					hospItems.forEach(hi -> {
						prices.put(MapUtils.getString(hi, "ITEM_CODE"), MapUtils.getObject(hi, "PRICE"));
					});
					
					
					for(RuleDetail hd : rds) {
						hd.setItemPrice(prices.get(hd.getItemCode()));
						for(RuleDetail grd : groupRds) {
							if(StringUtils.equalsIgnoreCase(hd.getItemCode(), grd.getItemCode())
									|| 
									//省区编码的情况，截取中间部分去判断
								(hd.getItemCode().contains("_") && hd.getItemCode().indexOf(grd.getItemCode()) > 0)) {
								hd.setGroupItemCode(grd.getItemCode());
								hd.setGroupItemName(grd.getItemName());
								hd.setGroupItemScore(grd.getItemScore());
								hd.setGroupRangeJsonShow(grd.getRangeJson());
								hd.setGroupRuleItemTypeDesc(RuleItemTypeEnum.valueOf(grd.getRuleItemType()).getEnumDesc());
								break;
							}
						}
					}
				}
			}
		}

		rds.sort(new Comparator<RuleDetail>() {
			@Override
			public int compare(RuleDetail o1, RuleDetail o2) {
				if(o1.getItemCode() == null) {
					return 1;
				}else {
					if(o2.getItemCode() == null) {
						return -1;
					}else {
						return (o2.getUsingSign()+"_"+o2.getItemCode()).compareTo(o1.getUsingSign()+"_"+o1.getItemCode());
					}
				}
			}
		});
		return rds;
	}
	

	public String updateRuleDetail(RuleDto dto) {
		Set<String> duplicateItem = Sets.newHashSet();
		List<RuleDetail> rs = dto.getRds();
		if(dto.getRuleId() != null) {
			Rule thisRule = this.selectById(dto.getRuleId());
			thisRule.setModifer(UserContext.getUserId());
			thisRule.setModifyDate(new Date());
			this.updateById(thisRule); //把修改人修改时间刷一下
			Metric metric = metricService.selectById(thisRule.getMetricId());
			
			EntityWrapper w = new EntityWrapper<>();
			w.eq("rule_id", dto.getRuleId());
			List<RuleDetail> rds = detailService.selectList(w);
			Set<Long> detailIds = SysUtil.getEntityIds(rds, "getId");
			detailService.delete(w);
			
			if(CollectionUtils.isNotEmpty(detailIds)) {
				w = new EntityWrapper<>();
				DbUtil.in(w, "detail_id", detailIds);
				rangeService.delete(w);
			}

			if(CollectionUtils.isNotEmpty(rs)) {
				Set<String> itemCode = Sets.newHashSet();
				rs.forEach(r -> {
					//医院的编辑，如果是为空，说明医院没有这个项目，直接不存。
					if(StringUtils.isBlank(r.getItemCode())) {
						return;
					}

					//已经存过了，直接不存。
					if(itemCode.contains(r.getItemCode())) {
						duplicateItem.add(r.getItemName());
						return;
					}else {
						itemCode.add(r.getItemCode());
					}
					
					r.setModifer(UserContext.getUserId());
					r.setRuleId(dto.getRuleId());
					r.setId(null);
					r.setHospId(null);
					//新刷新的项目
					if(Objects.equals(2, r.getUsingSign())){
						r.setUsingSign(1);
					}
					detailService.insert(r);
					
					if(StringUtils.isNotBlank(r.getRangeJson())) {
						List<RuleDetailRange> rdr = gson.fromJson(r.getRangeJson(), new TypeToken<List<RuleDetailRange>>() {
						}.getType());
						if(CollectionUtils.isNotEmpty(rdr)) {
							int i = 1;
							for(RuleDetailRange rd : rdr){
								rd.setId(null);
								rd.setHospId(null);
								rd.setDetailId(r.getId());
								rd.setModifer(UserContext.getUserId());
								rd.setRangeOrder(i++);
							}
							rangeService.insertBatch(rdr);
						}
					}
				});
				
			}

			ruleCalc.saveForCalc(thisRule);
		}
		if(duplicateItem.size() != 0) {
			StringBuilder msg = new StringBuilder("保存成功，但发现重复项目，系统已自动为您过滤！");
			duplicateItem.forEach(a ->{
				msg.append(a + ",");
			});
			return msg.toString();
		}
		return "保存成功！";
	}

	public List<Rule> getGroupRuleList(Rule m) {
		return UserContext.runWithChangedHospId(0L, ()->{
			EntityWrapper w = new EntityWrapper();
			w.eq("metric_id", m.getMetricId());
			w.eq("is_default_rule", 1); //只取正式版
			w.orderBy("rule_begin_date", false);
			return this.selectList(w);
		});
	}
	
	
	public static boolean isHospUser() {
		return !Objects.equals(0L, UserContext.getTenantId());
	}
	
	/**
	 * 是否需要从集团合并数据后显示规则明细？
	 * @param metric
	 * @return
	 */
	public static boolean needMergeGroup(Metric metric) {
		/**
		 * 固定套餐	集团HR无需集团设定规则，医院HR根据院内的固定套餐设置
			本指标	集团HR设置统一的计分规则，医院HR下载集团规则，可设置医院的规则
		 */
		return !Arrays.asList("B","I").contains(metric.getRuleExtType());
	}

	public List refreshRuleFromGroup(RuleDto m) {
		Rule hospRule = this.selectById(m.getRuleId());
		Rule groupRule = findRealGroupRule(hospRule);  
		
		EntityWrapper w = new EntityWrapper<>();
		w.eq("rule_id", hospRule.getId());
		List<RuleDetail> hospDetails = detailService.selectList(w);
		
		Map<String, RuleDetail> hospDetailsMap = Maps.newHashMap();
		for(RuleDetail rd : hospDetails){
			hospDetailsMap.put(rd.getItemCode(), rd);
		}
		
		List<RuleDetail> groupDetails = UserContext.runWithChangedHospId(0L, ()->{
			EntityWrapper wg = new EntityWrapper<>();
			wg.eq("rule_id", groupRule.getId());
			return detailService.selectList(wg);
		});
		List<RuleDetail> newRules = Lists.newArrayList();
		for(RuleDetail rd : groupDetails){
			if(!hospDetailsMap.containsKey(rd.getItemCode())) {
				RuleDetail hd = new RuleDetail();
				hd.setFinanceCodeValue(rd.getFinanceCodeValue());
				hd.setRuleId(hospRule.getId());
				hd.setRuleItemType(rd.getRuleItemType());
				hd.setItemId(rd.getItemId());
				hd.setItemCode(rd.getItemCode());
				hd.setItemName(rd.getItemName());
				hd.setItemLevel(rd.getItemLevel());
				hd.setItemIcd(rd.getItemIcd());
				hd.setItemScore(BigDecimal.valueOf(0)); //2022-08-16 粟楚娴 如果    刷新了集团的指标  医院没有配置的    就把医院标准显示为0
				hd.setModifer(UserContext.getUserId());
				hd.setFinanceCode(rd.getFinanceCode());
				newRules.add(hd);
			}
		}
		DbUtil.insertBatch(detailService, newRules);
		return newRules;
	}

	/**
	 * 医院用的集团规则如果失效了，就去找一个正式规则给他刷新
	 * @param hospRule
	 * @return
	 */
	private Rule findRealGroupRule(Rule hospRule) {
		Rule temp = UserContext.runWithChangedHospId(0L, ()->{
			return this.selectById(hospRule.getGroupRuleId());
		});
		
		if(!RuleUsingEnum._P.is(temp.getIsDefaultRule()) &&
				!RuleUsingEnum._T.is(temp.getIsDefaultRule())) {
			List<Rule> group = getGroupRuleList(temp);
			if(CollectionUtils.isNotEmpty(group)) {
				temp = group.get(0);
			}
		}
		return temp;
	}
	
}

