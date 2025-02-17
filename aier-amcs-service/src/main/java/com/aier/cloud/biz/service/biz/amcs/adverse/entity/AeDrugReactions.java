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
package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_DRUG_REACTIONS
 * 药品不良反应事件报告
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@TableName("T_AE_DRUG_REACTIONS")
public class AeDrugReactions extends BaseEntity<AeDrugReactions> {
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 创建人*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 集团评价内容*/
	@TableField(value="group_evaluation_content")
	@Length(max=500) private String groupEvaluationContent;
	
	/** 集团评价*/
	@TableField(value="group_evaluation")
	@Length(max=10) private String groupEvaluation;
	
	/** 后遗症表现*/
	@TableField(value="performance")
	@Length(max=200) private String performance;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@Length(max=30) private String hospName;
	
	/** 医院编号*/
	@TableField(value="hosp_no")
	@Length(max=10) private String hospNo;
	
	/** 报告人签名*/
	@TableField(value="signature_reporter")
	@Length(max=20) private String signatureReporter;
	
	/** 报告人职称*/
	@TableField(value="title_reporter")
	@Length(max=20) private String titleReporter;
	
	/** 报告人职业*/
	@TableField(value="occupation_reporter")
	@Length(max=20) private String occupationReporter;
	
	/** 签名2*/
	@TableField(value="autograph2")
	@Length(max=20) private String autograph2;
	
	/** 签名1*/
	@TableField(value="autograph1")
	@Length(max=20) private String autograph1;
	
	/** 医院评价*/
	@TableField(value="hospital_evaluation")
	@Length(max=10) private String hospitalEvaluation;
	
	/** 报告人评价*/
	@TableField(value="report_evaluation")
	@Length(max=10) private String reportEvaluation;
	
	/** 国外有无类似不良反应*/
	@TableField(value="similar_abroad")
	@Length(max=10) private String similarAbroad;
	
	/** 国内有无类似不良反应*/
	@TableField(value="similar_in_china")
	@Length(max=10) private String similarInChina;
	
	/** 对原患疾病影响*/
	@TableField(value="impact_on_disease")
	@Length(max=10) private String impactOnDisease;
	
	/** 原患疾病*/
	@TableField(value="original_disease")
	@Comment(value="原患疾病")
	@Length(max=500) private String originalDisease;
	
	/** 死亡时间*/
	@TableField(value="death_date")
	@Length(max=20) private java.util.Date deathDate;
	
	/** 直接死因*/
	@TableField(value="cause_of_death")
	@Length(max=200) private String causeOfDeath;
	
	/** 不良反应结果*/
	@TableField(value="adverse_results")
	@Length(max=10) private String adverseResults;
	
	/** 不良反应描述*/
	@TableField(value="adverse_reactions_description")
	@Length(max=2000) private String adverseReactionsDescription;
	
	/** 病历号门诊号*/
	@TableField(value="medical_record_number")
	@Length(max=30) private String medicalRecordNumber;
	
	/** 不良反应发生时间*/
	@TableField(value="occur_date")
	private java.util.Date occurDate;
	
	/** 不良反应事件名称*/
	@TableField(value="adverse_reactions_name")
	@Length(max=20) private String adverseReactionsName;
	
	/** 既往药品*/
	@TableField(value="past_drugs")
	@Length(max=20) private String pastDrugs;
	
	/** 家族药品*/
	@TableField(value="family_medicine")
	@Length(max=20) private String familyMedicine;
	
	/** 联系方式*/
	@TableField(value="contact_details")
	@Length(max=20) private String contactDetails;
	
	/** 体重*/
	@TableField(value="body_weight")
	@Length(max=20) private String bodyWeight;
	
	/** 民族*/
	@TableField(value="nationality")
	@Length(max=20) private String nationality;
	
	/** 出生日期*/
	@TableField(value="birth_date")
	@Length(max=20) private String birthDate;
	
	/** 性别*/
	@TableField(value="sex")
	@Length(max=20) private String sex;
	
	/** 患者姓名*/
	@TableField(value="patient_name")
	@Length(max=20) private String patientName;
	
	/** 上报集团日期*/
	@TableField(value="report_group")
	@Length(max=20) private java.util.Date reportGroup;
	
	/** 报告国家日期*/
	@TableField(value="reporting_countries")
	@Length(max=20) private java.util.Date reportingCountries;
	
	/** 电话*/
	@TableField(value="telephone")
	@Length(max=20) private String telephone;
	
	/** 科室*/
	@TableField(value="department")
	@Length(max=50) private String department;
	
	/** 单位名称*/
	@TableField(value="unit_name")
	@Length(max=50) private String unitName;
	
	/** 类型*/
	@TableField(value="type_level")
	@Length(max=20) private String typeLevel;
	
	/** 是否纠纷*/
	@TableField(value="dispute")
	@Length(max=20) private String dispute;

	/** 医院ID*/
	@Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 附件路径*/
	@TableField(value="url")
	@Length(max=10) private String url;

	/** 怀疑药品数目*/
	@TableField(exist = false)
	private Integer sdNum;

	/** 不良反应事件名称编码*/
	@TableField(value="adverse_reactions_code")
	@Length(max=4) private String adverseReactionsCode;

	/** 反应/事件是否消失或减轻*/
	@TableField(value="ease_react")
	@Length(max=4) private String easeReact;

	/** 是否再次出现同样反应/事件*/
	@TableField(value="same_react_again")
	@Length(max=4) private String sameReactAgain;

	/** 正常/取消状态*/
	@TableField(value="react_status")
	private Integer reactStatus;

	/** 上报人姓名*/
	@TableField(value="creator_name")
	@Length(max=30) private String creatorName;
	

	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public String getGroupEvaluationContent() {
		return groupEvaluationContent;
	}
	public void setGroupEvaluationContent(String groupEvaluationContent) {
		this.groupEvaluationContent = groupEvaluationContent;
	}
	public String getGroupEvaluation() {
		return groupEvaluation;
	}
	public void setGroupEvaluation(String groupEvaluation) {
		this.groupEvaluation = groupEvaluation;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getHospNo() {
		return hospNo;
	}
	public void setHospNo(String hospNo) {
		this.hospNo = hospNo;
	}
	public String getSignatureReporter() {
		return signatureReporter;
	}
	public void setSignatureReporter(String signatureReporter) {
		this.signatureReporter = signatureReporter;
	}
	public String getTitleReporter() {
		return titleReporter;
	}
	public void setTitleReporter(String titleReporter) {
		this.titleReporter = titleReporter;
	}
	public String getOccupationReporter() {
		return occupationReporter;
	}
	public void setOccupationReporter(String occupationReporter) {
		this.occupationReporter = occupationReporter;
	}
	public String getAutograph2() {
		return autograph2;
	}
	public void setAutograph2(String autograph2) {
		this.autograph2 = autograph2;
	}
	public String getAutograph1() {
		return autograph1;
	}
	public void setAutograph1(String autograph1) {
		this.autograph1 = autograph1;
	}
	public String getHospitalEvaluation() {
		return hospitalEvaluation;
	}
	public void setHospitalEvaluation(String hospitalEvaluation) {
		this.hospitalEvaluation = hospitalEvaluation;
	}
	public String getReportEvaluation() {
		return reportEvaluation;
	}
	public void setReportEvaluation(String reportEvaluation) {
		this.reportEvaluation = reportEvaluation;
	}
	public String getSimilarAbroad() {
		return similarAbroad;
	}
	public void setSimilarAbroad(String similarAbroad) {
		this.similarAbroad = similarAbroad;
	}
	public String getSimilarInChina() {
		return similarInChina;
	}
	public void setSimilarInChina(String similarInChina) {
		this.similarInChina = similarInChina;
	}
	public String getImpactOnDisease() {
		return impactOnDisease;
	}
	public void setImpactOnDisease(String impactOnDisease) {
		this.impactOnDisease = impactOnDisease;
	}
	public String getOriginalDisease() {
		return originalDisease;
	}
	public void setOriginalDisease(String originalDisease) {
		this.originalDisease = originalDisease;
	}
	public java.util.Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(java.util.Date deathDate) {
		this.deathDate = deathDate;
	}
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	public String getAdverseResults() {
		return adverseResults;
	}
	public void setAdverseResults(String adverseResults) {
		this.adverseResults = adverseResults;
	}
	public String getAdverseReactionsDescription() {
		return adverseReactionsDescription;
	}
	public void setAdverseReactionsDescription(String adverseReactionsDescription) {
		this.adverseReactionsDescription = adverseReactionsDescription;
	}
	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}
	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}
	public java.util.Date getOccurDate() {
		return occurDate;
	}
	public void setOccurDate(java.util.Date occurDate) {
		this.occurDate = occurDate;
	}
	public String getAdverseReactionsName() {
		return adverseReactionsName;
	}
	public void setAdverseReactionsName(String adverseReactionsName) {
		this.adverseReactionsName = adverseReactionsName;
	}
	public String getPastDrugs() {
		return pastDrugs;
	}
	public void setPastDrugs(String pastDrugs) {
		this.pastDrugs = pastDrugs;
	}
	public String getFamilyMedicine() {
		return familyMedicine;
	}
	public void setFamilyMedicine(String familyMedicine) {
		this.familyMedicine = familyMedicine;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(String bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public java.util.Date getReportGroup() {
		return reportGroup;
	}
	public void setReportGroup(java.util.Date reportGroup) {
		this.reportGroup = reportGroup;
	}
	public java.util.Date getReportingCountries() {
		return reportingCountries;
	}
	public void setReportingCountries(java.util.Date reportingCountries) {
		this.reportingCountries = reportingCountries;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(String typeLevel) {
		this.typeLevel = typeLevel;
	}
	public String getDispute() {
		return dispute;
	}
	public void setDispute(String dispute) {
		this.dispute = dispute;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSdNum() {
		return sdNum;
	}

	public void setSdNum(Integer sdNum) {
		this.sdNum = sdNum;
	}

	public String getAdverseReactionsCode() {
		return adverseReactionsCode;
	}

	public void setAdverseReactionsCode(String adverseReactionsCode) {
		this.adverseReactionsCode = adverseReactionsCode;
	}

	public String getEaseReact() {
		return easeReact;
	}

	public void setEaseReact(String easeReact) {
		this.easeReact = easeReact;
	}

	public String getSameReactAgain() {
		return sameReactAgain;
	}

	public void setSameReactAgain(String sameReactAgain) {
		this.sameReactAgain = sameReactAgain;
	}

	public Integer getReactStatus() {
		return reactStatus;
	}

	public void setReactStatus(Integer reactStatus) {
		this.reactStatus = reactStatus;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}