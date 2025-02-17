/*
 * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
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
package com.aier.cloud.api.amcs.adverse.domain.amrs;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMRS_PATIENT_OPER 患者手术信息表
 * 
 * @author lc
 * @since 2018-12-14 10:18:12
 */
public class AmrsPatientOper extends BaseEntity{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = -7910273489090189420L;

	/** 手术类型编码 */
	private String operationTypeCode;

	/** 手术类型名称 */
	private String operationTypeName;

	/** 手术码名称 */
	private String operName;

	/** 手术日期 */
	private java.util.Date operDate;

	/** 切口编号 */
	private String incsCode;

	/** 切口名称 */
	private String incsName;

	/** 愈合编号 */
	private String healCode;

	/** 愈合名称 */
	private String healName;

	/** 手术医生编号 */
	private Long operDoctCode;

	/** 手术医生姓名 */
	private String operDoctName;

	/** 麻醉方式(码表：anesthesia_mode) */
	private Integer anesthesiaMode;

	/** 是否附加手术 */
	private Integer isAttachOper;

	/** I助编号 */
	private Long asst1Id;

	/** I助名称 */
	private String asst1Name;

	/** II助编号 */
	private Long asst2Id;

	/** II助名称 */
	private String asst2Name;

	/** 麻醉医师ID */
	private Long analgesistId;

	/** 麻醉医师姓名 */
	private String analgesistName;

	/** 手术所在科室科号 */
	private String operDepCode;

	/** 手术所在科室名称 */
	private String operDepName;

	/** 择期手术编号 */
	private String electiveOperCode;

	/** 择期手术名称 */
	private String electiveOperName;

	/** 手术级别编号 */
	private String operLevelCode;

	/** 手术级别名称 */
	private String operLevelName;

	/** 眼别编码（码表：eye_type） */
	private Integer operEyeTypeCode;

	/** 眼别名称 */
	private String operEyeTypeName;

	/** 手术码版本号（码表：opr_version） */
	private Integer operVersions;

	/** 出院日期 */
	private java.util.Date discDate;

	/** 住院号 */
	private String inpNumber;

	/** 手术开始时间 */
	private java.util.Date operationBegin;

	/** 手术结束时间 */
	private java.util.Date operationEnd;

	/** 手术持续时长 xxhxxmin */
	private String operationLast;

	/** 麻醉方式名称 */
	private String anesthesiaModeName;

	/** 诊疗机构 */
	private Long hospId;

	/** 病案号（年4位+6位ID，按年重算清零） */
	private String medicalNumber;

	/** 住院次数（2位住院次数，患者在本机构住院次数） */
	private Integer inpTimes;

	/** 手术次数 */
	private Integer operTimes;

	/** 手术码 */
	private String operCode;

	public String getOperationTypeCode() {
		return operationTypeCode;
	}

	public void setOperationTypeCode(String operationTypeCode) {
		this.operationTypeCode = operationTypeCode;
	}

	public String getOperationTypeName() {
		return operationTypeName;
	}

	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
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

	public Integer getOperTimes() {
		return operTimes;
	}

	public void setOperTimes(Integer operTimes) {
		this.operTimes = operTimes;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public java.util.Date getOperDate() {
		return operDate;
	}

	public void setOperDate(java.util.Date operDate) {
		this.operDate = operDate;
	}

	public String getIncsCode() {
		return incsCode;
	}

	public void setIncsCode(String incsCode) {
		this.incsCode = incsCode;
	}

	public String getIncsName() {
		return incsName;
	}

	public void setIncsName(String incsName) {
		this.incsName = incsName;
	}

	public String getHealCode() {
		return healCode;
	}

	public void setHealCode(String healCode) {
		this.healCode = healCode;
	}

	public String getHealName() {
		return healName;
	}

	public void setHealName(String healName) {
		this.healName = healName;
	}

	public Long getOperDoctCode() {
		return operDoctCode;
	}

	public void setOperDoctCode(Long operDoctCode) {
		this.operDoctCode = operDoctCode;
	}

	public String getOperDoctName() {
		return operDoctName;
	}

	public void setOperDoctName(String operDoctName) {
		this.operDoctName = operDoctName;
	}

	public Integer getAnesthesiaMode() {
		return anesthesiaMode;
	}

	public void setAnesthesiaMode(Integer anesthesiaMode) {
		this.anesthesiaMode = anesthesiaMode;
	}

	public Integer getIsAttachOper() {
		return isAttachOper;
	}

	public void setIsAttachOper(Integer isAttachOper) {
		this.isAttachOper = isAttachOper;
	}

	public Long getAsst1Id() {
		return asst1Id;
	}

	public void setAsst1Id(Long asst1Id) {
		this.asst1Id = asst1Id;
	}

	public String getAsst1Name() {
		return asst1Name;
	}

	public void setAsst1Name(String asst1Name) {
		this.asst1Name = asst1Name;
	}

	public Long getAsst2Id() {
		return asst2Id;
	}

	public void setAsst2Id(Long asst2Id) {
		this.asst2Id = asst2Id;
	}

	public String getAsst2Name() {
		return asst2Name;
	}

	public void setAsst2Name(String asst2Name) {
		this.asst2Name = asst2Name;
	}

	public Long getAnalgesistId() {
		return analgesistId;
	}

	public void setAnalgesistId(Long analgesistId) {
		this.analgesistId = analgesistId;
	}

	public String getAnalgesistName() {
		return analgesistName;
	}

	public void setAnalgesistName(String analgesistName) {
		this.analgesistName = analgesistName;
	}

	public String getOperDepCode() {
		return operDepCode;
	}

	public void setOperDepCode(String operDepCode) {
		this.operDepCode = operDepCode;
	}

	public String getOperDepName() {
		return operDepName;
	}

	public void setOperDepName(String operDepName) {
		this.operDepName = operDepName;
	}

	public String getElectiveOperCode() {
		return electiveOperCode;
	}

	public void setElectiveOperCode(String electiveOperCode) {
		this.electiveOperCode = electiveOperCode;
	}

	public String getElectiveOperName() {
		return electiveOperName;
	}

	public void setElectiveOperName(String electiveOperName) {
		this.electiveOperName = electiveOperName;
	}

	public String getOperLevelCode() {
		return operLevelCode;
	}

	public void setOperLevelCode(String operLevelCode) {
		this.operLevelCode = operLevelCode;
	}

	public String getOperLevelName() {
		return operLevelName;
	}

	public void setOperLevelName(String operLevelName) {
		this.operLevelName = operLevelName;
	}

	public Integer getOperEyeTypeCode() {
		return operEyeTypeCode;
	}

	public void setOperEyeTypeCode(Integer operEyeTypeCode) {
		this.operEyeTypeCode = operEyeTypeCode;
	}

	public String getOperEyeTypeName() {
		return operEyeTypeName;
	}

	public void setOperEyeTypeName(String operEyeTypeName) {
		this.operEyeTypeName = operEyeTypeName;
	}

	public Integer getOperVersions() {
		return operVersions;
	}

	public void setOperVersions(Integer operVersions) {
		this.operVersions = operVersions;
	}

	public java.util.Date getDiscDate() {
		return discDate;
	}

	public void setDiscDate(java.util.Date discDate) {
		this.discDate = discDate;
	}

	public String getInpNumber() {
		return inpNumber;
	}

	public void setInpNumber(String inpNumber) {
		this.inpNumber = inpNumber;
	}

	public java.util.Date getOperationBegin() {
		return operationBegin;
	}

	public void setOperationBegin(java.util.Date operationBegin) {
		this.operationBegin = operationBegin;
	}

	public java.util.Date getOperationEnd() {
		return operationEnd;
	}

	public void setOperationEnd(java.util.Date operationEnd) {
		this.operationEnd = operationEnd;
	}

	public String getOperationLast() {
		return operationLast;
	}

	public void setOperationLast(String operationLast) {
		this.operationLast = operationLast;
	}

	public String getAnesthesiaModeName() {
		return anesthesiaModeName;
	}

	public void setAnesthesiaModeName(String anesthesiaModeName) {
		this.anesthesiaModeName = anesthesiaModeName;
	}
}