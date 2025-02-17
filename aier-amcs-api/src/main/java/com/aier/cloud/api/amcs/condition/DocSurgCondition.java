package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import lombok.Data;

@Data
public class DocSurgCondition extends PageCondition{
	
	private String surgType;
	
	private Long provId;
	
	private Long hospId;
	
	private Long doctorId;

}
