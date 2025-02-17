package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import lombok.Data;

@Data
public class CollCondition extends PageCondition{
	
	// 医院ID
	private Long hospId;

}
