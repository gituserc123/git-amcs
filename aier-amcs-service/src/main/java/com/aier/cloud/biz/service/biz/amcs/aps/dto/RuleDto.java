package com.aier.cloud.biz.service.biz.amcs.aps.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;

public class RuleDto extends PageCondition{
	Long ruleId;
	List<RuleDetail> rds;
	Set<String> itemCodes = Collections.EMPTY_SET;

	/** 医院id*/
	@NotBlank private Long hospId;
	/**id*/
	@NotBlank private Long id;

	/** 指标id*/
	@NotBlank private Long metricId;
	/** 集团规则id*/
	@NotBlank private Long groupRuleId;
	
	/** 指标编码*/
	@NotBlank @Length(max=30) private String metricCode;
	
	/** 是否为默认规则（1是0否）*/
	@NotBlank private Integer isDefaultRule;
	
	/** 规则名称*/
	@Length(max=100) private String ruleName;
	
	/** 规则版本*/
	@Length(max=30) private String ruleVersion;
	
	/** 指标生效时间*/
	private java.util.Date ruleBeginDate;
	
	/** 指标失效时间*/
	private java.util.Date ruleEndDate;
	

	/** 启停标识（1启用0停用）*/
	@NotBlank private Integer usingSign;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public List<RuleDetail> getRds() {
		return rds;
	}

	public void setRds(List<RuleDetail> rds) {
		this.rds = rds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<String> getItemCodes() {
		return itemCodes;
	}

	public void setItemCodes(Set<String> itemCodes) {
		this.itemCodes = itemCodes;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
}
