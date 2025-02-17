package com.aier.cloud.biz.service.biz.amcs.aps.dto;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

public class MetricDto extends PageCondition{
	String usingSign;
	String pFlag;
	public String getpFlag() {
		return pFlag;
	}

	public void setpFlag(String pFlag) {
		this.pFlag = pFlag;
	}

	String typeCode;
	Long metricId;
	Long ruleId;
	Long hospId;

	public String getUsingSign() {
		return usingSign;
	}

	public void setUsingSign(String usingSign) {
		this.usingSign = usingSign;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Long getMetricId() {
		return metricId;
	}

	public void setMetricId(Long metricId) {
		this.metricId = metricId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
}
