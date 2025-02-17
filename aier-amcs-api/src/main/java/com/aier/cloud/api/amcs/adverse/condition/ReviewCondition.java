package com.aier.cloud.api.amcs.adverse.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-08-01 17:24
 **/

public class ReviewCondition extends PageCondition {
	
    private String basicId;
    
    private String node;

    public String getbasicId() {
        return basicId;
    }

    public void setBasicId(String basicId) {
        this.basicId = basicId;
    }

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
    
}
