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
package com.aier.cloud.biz.service.biz.amcs.cat.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_CAT_LACRIMAL
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
@TableName("T_AMCS_CAT_LACRIMAL")
public class Lacrimal extends BaseEntity<Lacrimal> {
	/** 检查日期*/
	@TableField(value="check_date")
	private java.util.Date checkDate;

	/** 患者id*/
	private Long patientId;
	
	/** 患者姓名*/
	@TableField(value="patient_name")
	@Length(max=20) private String patientName;
	
	/** 联系电话*/
	@TableField(value="mobile_phone")
	@Length(max=20) private String mobilePhone;
	
	/** 渠道*/
	@TableField(value="channel")
	private Integer channel;
	
	/** od返流*/
	@TableField(value="od_backflow")
	@Length(max=10) private String odBackflow;
	
	/** od不入喉*/
	@TableField(value="od_inthroat")
	@Length(max=10) private String odInthroat;
	
	/** od分泌物*/
	@TableField(value="od_secretion")
	@Length(max=10) private String odSecretion;
	
	/** os返流2*/
	@TableField(value="os_backflow")
	@Length(max=10) private String osBackflow;
	
	/** os不入喉*/
	@TableField(value="os_inthroat")
	@Length(max=10) private String osInthroat;
	
	/** os分泌物*/
	@TableField(value="os_secretion")
	@Length(max=10) private String osSecretion;
	
	/** 首诊医生处置情况*/
	@TableField(value="fst_treat_info")
	@Length(max=10) private String fstTreatInfo;
	
	/** 首诊科室ID*/
	@TableField(value="fst_dept_id")
	private Long fstDeptId;
	
	/** 首诊科室*/
	@TableField(value="fst_dept_name")
	@Length(max=20) private String fstDeptName;
	
	/** 首诊医生ID*/
	@TableField(value="fst_doctor_id")
	private Long fstDoctorId;
	
	/** 首诊医生*/
	@TableField(value="fst_doctor_name")
	@Length(max=20) private String fstDoctorName;
	
	/** 青光眼医生ID*/
	@TableField(value="glaucoma_doctor_id")
	private Long glaucomaDoctorId;
	
	/** 青光眼医生姓名*/
	@TableField(value="glaucoma_doctor_name")
	@Length(max=20) private String glaucomaDoctorName;
	
	/** 青光眼医生诊断结果*/
	@TableField(value="glaucoma_result")
	@Length(max=200) private String glaucomaResult;
	
	/** 未治原因*/
	@TableField(value="untreat_reason")
	@Length(max=100) private String untreatReason;
	
	/** 是否治疗泪道*/
	@TableField(value="is_treat")
	private Integer isTreat;
	
	/** 医院Id*/
	@TableField(value="hosp_id")
	private Long hospId;
	

	public java.util.Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(java.util.Date checkDate) {
		this.checkDate = checkDate;
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
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public String getOdBackflow() {
		return odBackflow;
	}
	public void setOdBackflow(String odBackflow) {
		this.odBackflow = odBackflow;
	}
	public String getOdInthroat() {
		return odInthroat;
	}
	public void setOdInthroat(String odInthroat) {
		this.odInthroat = odInthroat;
	}
	public String getOdSecretion() {
		return odSecretion;
	}
	public void setOdSecretion(String odSecretion) {
		this.odSecretion = odSecretion;
	}
	public String getOsBackflow() {
		return osBackflow;
	}
	public void setOsBackflow(String osBackflow) {
		this.osBackflow = osBackflow;
	}
	public String getOsInthroat() {
		return osInthroat;
	}
	public void setOsInthroat(String osInthroat) {
		this.osInthroat = osInthroat;
	}
	public String getOsSecretion() {
		return osSecretion;
	}
	public void setOsSecretion(String osSecretion) {
		this.osSecretion = osSecretion;
	}
	public String getFstTreatInfo() {
		return fstTreatInfo;
	}
	public void setFstTreatInfo(String fstTreatInfo) {
		this.fstTreatInfo = fstTreatInfo;
	}
	public Long getFstDeptId() {
		return fstDeptId;
	}
	public void setFstDeptId(Long fstDeptId) {
		this.fstDeptId = fstDeptId;
	}
	public String getFstDeptName() {
		return fstDeptName;
	}
	public void setFstDeptName(String fstDeptName) {
		this.fstDeptName = fstDeptName;
	}
	public Long getFstDoctorId() {
		return fstDoctorId;
	}
	public void setFstDoctorId(Long fstDoctorId) {
		this.fstDoctorId = fstDoctorId;
	}
	public String getFstDoctorName() {
		return fstDoctorName;
	}
	public void setFstDoctorName(String fstDoctorName) {
		this.fstDoctorName = fstDoctorName;
	}
	public Long getGlaucomaDoctorId() {
		return glaucomaDoctorId;
	}
	public void setGlaucomaDoctorId(Long glaucomaDoctorId) {
		this.glaucomaDoctorId = glaucomaDoctorId;
	}
	public String getGlaucomaDoctorName() {
		return glaucomaDoctorName;
	}
	public void setGlaucomaDoctorName(String glaucomaDoctorName) {
		this.glaucomaDoctorName = glaucomaDoctorName;
	}
	public String getGlaucomaResult() {
		return glaucomaResult;
	}
	public void setGlaucomaResult(String glaucomaResult) {
		this.glaucomaResult = glaucomaResult;
	}
	public String getUntreatReason() {
		return untreatReason;
	}
	public void setUntreatReason(String untreatReason) {
		this.untreatReason = untreatReason;
	}
	public Integer getIsTreat() {
		return isTreat;
	}
	public void setIsTreat(Integer isTreat) {
		this.isTreat = isTreat;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
}