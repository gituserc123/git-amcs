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
package com.aier.cloud.biz.service.biz.amcs.aps.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * T_AMCS_APS_RULE
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@TableName("T_AMCS_APS_RULE")
public class Rule extends BaseEntity<Rule> {
	/** 医院id*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 指标id*/
	@TableField(value="metric_id")
	@NotBlank private Long metricId;
	/** 集团规则id*/
	@TableField(value="group_rule_id")
	@NotBlank private Long groupRuleId;
	
	/** 指标编码*/
	@TableField(value="metric_code")
	@NotBlank @Length(max=30) private String metricCode;
	
	/** 是否为默认规则（1是0否）*/
	@TableField(value="is_default_rule")
	@NotBlank private Integer isDefaultRule;
	
	/** 规则名称*/
	@TableField(value="rule_name")
	@Length(max=100) private String ruleName;
	
	/** 规则版本*/
	@TableField(value="rule_version")
	@Length(max=30) private String ruleVersion;
	
	/** 指标生效时间*/
	@TableField(value="rule_begin_date")
	private java.util.Date ruleBeginDate;
	
	/** 指标失效时间*/
	@TableField(value="rule_end_date")
	private java.util.Date ruleEndDate;
	
	/** 备注*/
	@TableField(value="remarks")
	@Length(max=500) private String remarks;

	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;

	/** 指标id*/
	@TableField(exist= false)
	@NotBlank private Long metricId2;
	
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getMetricId() {
		return metricId;
	}
	public void setMetricId(Long metricId) {
		this.metricId = metricId;
	}
	public String getMetricCode() {
		return metricCode;
	}
	public void setMetricCode(String metricCode) {
		this.metricCode = metricCode;
	}
	public Integer getIsDefaultRule() {
		return isDefaultRule;
	}
	public void setIsDefaultRule(Integer isDefaultRule) {
		this.isDefaultRule = isDefaultRule;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleVersion() {
		return ruleVersion;
	}
	public void setRuleVersion(String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}
	public java.util.Date getRuleBeginDate() {
		return ruleBeginDate;
	}
	public void setRuleBeginDate(java.util.Date ruleBeginDate) {
		this.ruleBeginDate = ruleBeginDate;
	}
	public java.util.Date getRuleEndDate() {
		return ruleEndDate;
	}
	public void setRuleEndDate(java.util.Date ruleEndDate) {
		this.ruleEndDate = ruleEndDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
	public Long getMetricId2() {
		return metricId2;
	}
	public void setMetricId2(Long metricId2) {
		this.metricId2 = metricId2;
	}
	public Long getGroupRuleId() {
		return groupRuleId;
	}
	public void setGroupRuleId(Long groupRuleId) {
		this.groupRuleId = groupRuleId;
	}
}