package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

public class PatientOperCondition extends PageCondition {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 1L;

	/** 手术码名称 */
	private String likeOperName;

	/** 手术码 */
	private String operCode;

	/** 手术码 */
	private String likeOperCode;

	/** 是否附加手术 */
	private Integer isAttachOper;

	/** 病案号 */
	private String medicalNumber;

	/** 次数 */
	private Integer inpTimes;

	/** 排序字段 */
	private String orderField;

	/** 住院医师姓名 */
	private String likeOperDoctName;

	/** 麻醉方式(码表：anesthesia_mode) */
	private Integer anesthesiaMode;

	/**
	 * 是否为升序 ASC（ 默认： true ）
	 */
	private Boolean orderByAsc = true;

	public String getLikeOperDoctName() {
		return likeOperDoctName;
	}

	public void setLikeOperDoctName(String likeOperDoctName) {
		this.likeOperDoctName = likeOperDoctName;
	}

	public String getLikeOperCode() {
		return likeOperCode;
	}

	public void setLikeOperCode(String likeOperCode) {
		this.likeOperCode = likeOperCode;
	}

	public String getLikeOperName() {
		return likeOperName;
	}

	public void setLikeOperName(String likeOperName) {
		this.likeOperName = likeOperName;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public Integer getIsAttachOper() {
		return isAttachOper;
	}

	public void setIsAttachOper(Integer isAttachOper) {
		this.isAttachOper = isAttachOper;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Boolean getOrderByAsc() {
		return orderByAsc;
	}

	public void setOrderByAsc(Boolean orderByAsc) {
		this.orderByAsc = orderByAsc;
	}

	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}

	public Integer getInpTimes() {
		return inpTimes;
	}

	public void setInpTimes(Integer inpTimes) {
		this.inpTimes = inpTimes;
	}

	public Integer getAnesthesiaMode() {
		return anesthesiaMode;
	}

	public void setAnesthesiaMode(Integer anesthesiaMode) {
		this.anesthesiaMode = anesthesiaMode;
	}

}
