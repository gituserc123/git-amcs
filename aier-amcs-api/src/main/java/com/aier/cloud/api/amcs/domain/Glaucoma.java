/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.api.amcs.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMCS_CAT_GLAUCOMA
 * 青光眼筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
public class Glaucoma extends BaseEntity {
	/** 就诊日期*/
	private java.util.Date outpatientDate;
	
	/** 患者id*/
	private Long patientId;
	
	/** 患者姓名*/
	private String patientName;

	/** 联系电话*/
	private String mobilePhone;
	
	/** od眼压*/
	private java.math.BigDecimal odPressure;
	
	/** os眼压*/
	private java.math.BigDecimal osPressure;
	
	/** 首诊科室（转出）ID*/
	private Long fstOutDeptId;
	
	/** 首诊科室（转出）*/
	private String fstOutDeptName;
	
	/** 首诊医生（转出）ID*/
	private Long fstOutDoctorId;
	
	/** 首诊医生（转出）*/
	private String fstOutDoctorName;
	
	/** 接诊医生诊断结果（转入）*/
	private String inMedicalResult;
	
	/** 接诊医生处置情况（转入）*/
	private String inTreatInfo;
	
	/** 未治疗原因*/
	private String untreatReason;
	
	/** 医院Id*/
	private Long hospId;
	
	/** 首诊医生处置情况（转出）*/
	private String outTreatInfo;
	
	/** 接诊医生（转入）ID*/
	private Long inDoctorId;
	
	/** 接诊医生（转入）*/
	private String inDoctorName;
	

	public java.util.Date getOutpatientDate() {
		return outpatientDate;
	}
	public void setOutpatientDate(java.util.Date outpatientDate) {
		this.outpatientDate = outpatientDate;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public java.math.BigDecimal getOdPressure() {
		return odPressure;
	}
	public void setOdPressure(java.math.BigDecimal odPressure) {
		this.odPressure = odPressure;
	}
	public java.math.BigDecimal getOsPressure() {
		return osPressure;
	}
	public void setOsPressure(java.math.BigDecimal osPressure) {
		this.osPressure = osPressure;
	}
	public Long getFstOutDeptId() {
		return fstOutDeptId;
	}
	public void setFstOutDeptId(Long fstOutDeptId) {
		this.fstOutDeptId = fstOutDeptId;
	}
	public String getFstOutDeptName() {
		return fstOutDeptName;
	}
	public void setFstOutDeptName(String fstOutDeptName) {
		this.fstOutDeptName = fstOutDeptName;
	}
	public Long getFstOutDoctorId() {
		return fstOutDoctorId;
	}
	public void setFstOutDoctorId(Long fstOutDoctorId) {
		this.fstOutDoctorId = fstOutDoctorId;
	}
	public String getFstOutDoctorName() {
		return fstOutDoctorName;
	}
	public void setFstOutDoctorName(String fstOutDoctorName) {
		this.fstOutDoctorName = fstOutDoctorName;
	}
	public String getInMedicalResult() {
		return inMedicalResult;
	}
	public void setInMedicalResult(String inMedicalResult) {
		this.inMedicalResult = inMedicalResult;
	}
	public String getInTreatInfo() {
		return inTreatInfo;
	}
	public void setInTreatInfo(String inTreatInfo) {
		this.inTreatInfo = inTreatInfo;
	}
	public String getUntreatReason() {
		return untreatReason;
	}
	public void setUntreatReason(String untreatReason) {
		this.untreatReason = untreatReason;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getOutTreatInfo() {
		return outTreatInfo;
	}
	public void setOutTreatInfo(String outTreatInfo) {
		this.outTreatInfo = outTreatInfo;
	}
	public Long getInDoctorId() {
		return inDoctorId;
	}
	public void setInDoctorId(Long inDoctorId) {
		this.inDoctorId = inDoctorId;
	}
	public String getInDoctorName() {
		return inDoctorName;
	}
	public void setInDoctorName(String inDoctorName) {
		this.inDoctorName = inDoctorName;
	}
}