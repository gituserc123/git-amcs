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
 * T_AMCS_APS_METRIC
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@TableName("T_AMCS_APS_METRIC")
public class Metric extends BaseEntity<Metric> {
	/** 指标编码*/
	@TableField(value="metric_code")
	@NotBlank @Length(max=50) private String metricCode;
	
	/** 指标名称*/
	@TableField(value="metric_name")
	@NotBlank @Length(max=100) private String metricName;
	
	/** 首拼码*/
	@TableField(value="first_spell")
	@Length(max=100) private String firstSpell;
	
	/** 指标计算频率*/
	@TableField(value="rule_frequency")
	private String ruleFrequency;
	
	/** 指标计算拓展类型*/
	@TableField(value="rule_ext_type")
	@Length(max=20) private String ruleExtType;
	
	/** 备注*/
	@TableField(value="remarks")
	@Length(max=500) private String remarks;
	
	@TableField(value="unit_name")
	@Length(max=30) private String unitName;
	@TableField(value="finance_code")
	@Length(max=30) private String financeCode;
	@TableField(value="allow_item_type")
	@Length(max=50) private String allowItemType;
	public String getAllowItemType() {
		return allowItemType;
	}
	public void setAllowItemType(String allowItemType) {
		this.allowItemType = allowItemType;
	}
	/** 分值或比例*/
	@TableField(value="default_point")
	@NotBlank private java.math.BigDecimal defaultPoint;

	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;
	

	/** 存在测试配置？ 1有0无*/
	@TableField(exist=false)
	String tFlag;
	/** 存在正式配置？ 1有0无*/
	@TableField(exist=false)
	String pFlag;
	/** 正式配置ruleId*/
	@TableField(exist=false)
	Long pRuleId;
	

	public Long getpRuleId() {
		return pRuleId;
	}
	public void setpRuleId(Long pRuleId) {
		this.pRuleId = pRuleId;
	}
	public String getMetricCode() {
		return metricCode;
	}
	public void setMetricCode(String metricCode) {
		this.metricCode = metricCode;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getFirstSpell() {
		return firstSpell;
	}
	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
	public String getRuleFrequency() {
		return ruleFrequency;
	}
	public void setRuleFrequency(String ruleFrequency) {
		this.ruleFrequency = ruleFrequency;
	}
	public String getRuleExtType() {
		return ruleExtType;
	}
	public void setRuleExtType(String ruleExtType) {
		this.ruleExtType = ruleExtType;
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
	public String gettFlag() {
		return tFlag;
	}
	public void settFlag(String tFlag) {
		this.tFlag = tFlag;
	}
	public String getpFlag() {
		return pFlag;
	}
	public void setpFlag(String pFlag) {
		this.pFlag = pFlag;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getFinanceCode() {
		return financeCode;
	}
	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}
	public java.math.BigDecimal getDefaultPoint() {
		return defaultPoint;
	}
	public void setDefaultPoint(java.math.BigDecimal defaultPoint) {
		this.defaultPoint = defaultPoint;
	}
}