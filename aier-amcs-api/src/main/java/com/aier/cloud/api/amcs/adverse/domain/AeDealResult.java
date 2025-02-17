package com.aier.cloud.api.amcs.adverse.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AeDealResult {
	/** 是否构成纠纷(0 否 1 是)*/
	private Integer disputeSign;
	/** 是否完结(0 否 1 是)*/
	private Integer finishSign;
	
	/** 赔偿金额*/
	private BigDecimal compensationAmount;
	/** 减免金额*/
	private BigDecimal deductionAmount;
	/** 其它处理结果*/
	private String otherProcessResult;
	/** oa返回的单据号*/
	private Long oaRequestId;
}
