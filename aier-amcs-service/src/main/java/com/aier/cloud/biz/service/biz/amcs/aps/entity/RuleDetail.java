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

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * T_AMCS_APS_RULE_DETAIL
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@TableName("T_AMCS_APS_RULE_DETAIL")
public class RuleDetail extends BaseEntity<RuleDetail> {
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
	@NotBlank @Length(max=30) private String financeCodeValue;
	/** 财务分类*/
	@TableField(value="finance_code")
	@NotBlank @Length(max=30) private String financeCode;
	
	/** 计分项目ID*/
	@TableField(value="item_id")
	private Long itemId;
	
	/** 计分项目编码*/
	@TableField(value="item_code")
	@Length(max=50) private String itemCode;
	
	/** 计分项目名称*/
	@TableField(value="item_name")
	@Length(max=50) private String itemName;
	
	/** 计分项目等级*/
	@TableField(value="item_level")
	@Length(max=50) private String itemLevel;

	/** 计分项目病种*/
	@TableField(value="item_icd")
	@Length(max=50) private String itemIcd;
	/** 计分项目病种*/
	@TableField(value="remark")
	@Length(max=50) private String remark;
	
	/** 分值或比例*/
	@TableField(value="item_score")
	@NotBlank private java.math.BigDecimal itemScore;

	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;
	

	/** 区间段*/
	@TableField(value="rangeJson", exist=false)
	@NotBlank private String rangeJson;
	/** 区间段*/
	@TableField(exist=false)
	@NotBlank private Object itemPrice;

	@TableField(exist=false)
	@NotBlank private String groupItemCode;
	@TableField(exist=false)
	@NotBlank private String groupItemName;
	@TableField(exist=false)
	@NotBlank private String groupRuleItemTypeDesc;
	@TableField(exist=false)
	@NotBlank private BigDecimal groupItemScore;
	@TableField(exist=false)
	@NotBlank private String groupRangeJsonShow;
	
	

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
	public String getFinanceCodeValue() {
		return financeCodeValue;
	}
	public void setFinanceCodeValue(String financeCodeValue) {
		this.financeCodeValue = financeCodeValue;
	}
	public String getRangeJson() {
		return rangeJson;
	}
	public void setRangeJson(String rangeJson) {
		this.rangeJson = rangeJson;
	}
	public String getGroupItemCode() {
		return groupItemCode;
	}
	public void setGroupItemCode(String groupItemCode) {
		this.groupItemCode = groupItemCode;
	}
	public String getGroupItemName() {
		return groupItemName;
	}
	public void setGroupItemName(String groupItemName) {
		this.groupItemName = groupItemName;
	}
	public String getGroupRuleItemTypeDesc() {
		return groupRuleItemTypeDesc;
	}
	public void setGroupRuleItemTypeDesc(String groupRuleItemTypeDesc) {
		this.groupRuleItemTypeDesc = groupRuleItemTypeDesc;
	}
	public BigDecimal getGroupItemScore() {
		return groupItemScore;
	}
	public void setGroupItemScore(BigDecimal groupItemScore) {
		this.groupItemScore = groupItemScore;
	}
	public String getGroupRangeJsonShow() {
		return groupRangeJsonShow;
	}
	public void setGroupRangeJsonShow(String groupRangeJsonShow) {
		this.groupRangeJsonShow = groupRangeJsonShow;
	}
	public Object getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Object itemPrice) {
		this.itemPrice = itemPrice;
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
}