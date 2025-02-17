package com.aier.cloud.api.amcs.adverse.condition;

import java.util.List;

import com.aier.cloud.api.amcs.adverse.domain.Attachment;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import lombok.Data;

@Data
public class AttachmentCondition extends PageCondition{
	
	private Long basicId;
	
	private Long prevId;
	
	private String tag;

	private List<Attachment> attachments;
	
	private List<Long> basicIds;
	
}
