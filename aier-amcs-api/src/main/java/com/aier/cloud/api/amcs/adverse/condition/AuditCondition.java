package com.aier.cloud.api.amcs.adverse.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

public class AuditCondition extends PageCondition {

	 private String basicId;

	public String getbasicId() {
		return basicId;
	}

	public void setBasicId(String basicId) {
		this.basicId = basicId;
	}
	 
}
