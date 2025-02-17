package com.aier.cloud.api.amcs.adverse.domain;

public class Audit {
	
	/**
	 * 审核意见
	 */
	private String opinion;
	
	/**
	 * 审核状态
	 */
	private Integer status;

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
