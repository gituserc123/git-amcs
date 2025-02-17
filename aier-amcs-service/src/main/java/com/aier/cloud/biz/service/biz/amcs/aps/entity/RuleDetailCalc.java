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
 * T_AMCS_APS_RULE_DETAIL_CALC
 * 人资绩效计分规则详情表(用于计算拓展)
 * @author 爱尔眼科
 * @since 2023-03-27 16:29:23
 */
@TableName("T_AMCS_APS_RULE_DETAIL_CALC")
public class RuleDetailCalc extends BaseEntity<RuleDetailCalc> {
	/** id*/
	@TableField(value="rule_detail_id")
	@NotBlank private Long ruleDetailId;
	
	/** 医院id*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 规则id*/
	@TableField(value="rule_id")
	@NotBlank private Long ruleId;
	
	/** 计分规则类型*/
	@TableField(value="rule_item_type")
	@NotBlank @Length(max=30) private String ruleItemType;
	
	/** 财务分类*/
	@TableField(value="finance_code_value")
	@Length(max=100) private String financeCodeValue;
	
	/** 计分项目ID*/
	@TableField(value="item_id")
	private Long itemId;
	
	/** 计分项目编码*/
	@TableField(value="item_code")
	@Length(max=200) private String itemCode;
	
	/** 计分项目名称*/
	@TableField(value="item_name")
	@Length(max=200) private String itemName;
	
	/** 计分项目等级*/
	@TableField(value="item_level")
	@Length(max=50) private String itemLevel;
	
	/** 计分项目病种*/
	@TableField(value="item_icd")
	@Length(max=50) private String itemIcd;
	
	/** 分值或比例*/
	@TableField(value="item_score")
	@NotBlank private java.math.BigDecimal itemScore;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;
	
	/** 财务分类编码*/
	@TableField(value="finance_code")
	@Length(max=50) private String financeCode;
	
	/** 备注*/
	@TableField(value="remark")
	@Length(max=2000) private String remark;
	
	/** 计算类型(1:收费项目,2:财务分类)*/
	@TableField(value="calc_type")
	private Integer calcType;
	

	public Long getRuleDetailId() {
		return ruleDetailId;
	}
	public void setRuleDetailId(Long ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleItemType() {
		return ruleItemType;
	}
	public void setRuleItemType(String ruleItemType) {
		this.ruleItemType = ruleItemType;
	}
	public String getFinanceCodeValue() {
		return financeCodeValue;
	}
	public void setFinanceCodeValue(String financeCodeValue) {
		this.financeCodeValue = financeCodeValue;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemLevel() {
		return itemLevel;
	}
	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
	}
	public String getItemIcd() {
		return itemIcd;
	}
	public void setItemIcd(String itemIcd) {
		this.itemIcd = itemIcd;
	}
	public java.math.BigDecimal getItemScore() {
		return itemScore;
	}
	public void setItemScore(java.math.BigDecimal itemScore) {
		this.itemScore = itemScore;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
	public String getFinanceCode() {
		return financeCode;
	}
	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCalcType() {
		return calcType;
	}
	public void setCalcType(Integer calcType) {
		this.calcType = calcType;
	}
}