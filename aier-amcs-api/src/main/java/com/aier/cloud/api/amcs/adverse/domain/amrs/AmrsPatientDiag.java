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
 * T_AMRS_PATIENT_DIAG 患者诊断信息表
 * 
 * @author lc
 * @since 2018-12-14 10:18:12
 */
public class AmrsPatientDiag extends BaseEntity{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 2066034892767374470L;

	/** 诊疗机构 */
	private Long hospId;

	/** 病案号（年4位+6位ID，按年重算清零） */
	private String medicalNumber;

	/** 住院次数（2位住院次数，患者在本机构住院次数） */
	private Integer inpTimes;

	/** 诊断序号（0主要诊断；次要诊断依次排序；同一就诊号有且只有一条主要诊断） */
	private Integer diagOrder;

	/** ICD-10诊断版本 */
	private Integer diagVersions;

	/** ICD-10编码 */
	private String icd;

	/** ICD-10编码名称 */
	private String icdName;

	/** 疗效编号 */
	private String efficacyCode;

	/** 疗效名称 */
	private String efficacyName;

	/** 入院病情名称 */
	private String admisCndtName;

	/** 入院病情编号 */
	private String admisCndtCode;

	/** 住院号 */
	private String inpNumber;

	/** 眼别编码（码表：eye_type） */
	private Integer diagEyeTypeCode;

	/** 眼别名称 */
	private String diagEyeTypeName;

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

	public Integer getDiagOrder() {
		return diagOrder;
	}

	public void setDiagOrder(Integer diagOrder) {
		this.diagOrder = diagOrder;
	}

	public Integer getDiagVersions() {
		return diagVersions;
	}

	public void setDiagVersions(Integer diagVersions) {
		this.diagVersions = diagVersions;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public String getIcdName() {
		return icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}

	public String getEfficacyCode() {
		return efficacyCode;
	}

	public void setEfficacyCode(String efficacyCode) {
		this.efficacyCode = efficacyCode;
	}

	public String getEfficacyName() {
		return efficacyName;
	}

	public void setEfficacyName(String efficacyName) {
		this.efficacyName = efficacyName;
	}

	public String getAdmisCndtName() {
		return admisCndtName;
	}

	public void setAdmisCndtName(String admisCndtName) {
		this.admisCndtName = admisCndtName;
	}

	public String getAdmisCndtCode() {
		return admisCndtCode;
	}

	public void setAdmisCndtCode(String admisCndtCode) {
		this.admisCndtCode = admisCndtCode;
	}

	public String getInpNumber() {
		return inpNumber;
	}

	public void setInpNumber(String inpNumber) {
		this.inpNumber = inpNumber;
	}

	public Integer getDiagEyeTypeCode() {
		return diagEyeTypeCode;
	}

	public void setDiagEyeTypeCode(Integer diagEyeTypeCode) {
		this.diagEyeTypeCode = diagEyeTypeCode;
	}

	public String getDiagEyeTypeName() {
		return diagEyeTypeName;
	}

	public void setDiagEyeTypeName(String diagEyeTypeName) {
		this.diagEyeTypeName = diagEyeTypeName;
	}

}