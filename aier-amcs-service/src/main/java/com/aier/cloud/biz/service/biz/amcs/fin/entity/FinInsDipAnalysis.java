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
package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_FIN_INS_DIP_ANALYSIS
 * DIP盈亏分析表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@TableName("T_FIN_INS_DIP_ANALYSIS")
public class FinInsDipAnalysis extends BaseEntity<FinInsDipAnalysis> {
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 月主表ID*/
	@TableField(value="month_id")
	private Long monthId;
	
	/** 主表ID*/
	@TableField(value="main_id")
	@NotBlank private Long mainId;
	
	/** 病种（白内障、青光眼、角结膜、泪道、眼底、其他）*/
	@TableField(value="disease_type")
	@NotBlank @Length(max=10) private String diseaseType;
	
	/** DIP分组名称*/
	@TableField(value="group_name")
	@NotBlank @Length(max=50) private String groupName;

	/** DIP分组编码*/
	@Comment(value="DIP分组编码")
	@TableField(value="group_code")
	@NotBlank @Length(max=100) private String groupCode;
	
	/** 主要诊断名称*/
	@Comment(value="主要诊断名称")
	@TableField(value="main_diagnosis_name")
	@NotBlank @Length(max=30) private String mainDiagnosisName;
	
	/** 主要手术及操作名称*/
	@Comment(value="主要手术及操作名称")
	@TableField(value="main_surgery_name")
	@NotBlank @Length(max=100) private String mainSurgeryName;
	
	/** 2023年总病例数*/
	@TableField(value="total_cases")
	@NotBlank private java.math.BigDecimal totalCases;
	
	/** 实际住院总费用*/
	@TableField(value="actual_hospitalization_cost")
	@NotBlank private java.math.BigDecimal actualHospitalizationCost;
	
	/** 预计DIP结算医疗总费用*/
	@TableField(value="expected_settlement_cost")
	@NotBlank private java.math.BigDecimal expectedSettlementCost;
	
	/** 预计节余/超支总金额（节余填正，超支填负数）*/
	@TableField(value="expected_balance_amount")
	@NotBlank private java.math.BigDecimal expectedBalanceAmount;
	
	/** 例均实际住院费用*/
	@TableField(value="avg_actual_cost")
	@NotBlank private java.math.BigDecimal avgActualCost;
	
	/** 例均预计DIP结算医疗费用*/
	@TableField(value="avg_expected_settlement")
	@NotBlank private java.math.BigDecimal avgExpectedSettlement;
	
	/** 例均预计节余/超支金额*/
	@TableField(value="avg_expected_balance_amount")
	@NotBlank private java.math.BigDecimal avgExpectedBalanceAmount;
	
	/** 盈亏情况分析     （含医用耗材建议临床使用的价格区间）*/
	@TableField(value="profit_loss_analysis")
	@Length(max=200) private String profitLossAnalysis;

	/** 医保局年度考核指标     （有年度考核指标的医院填写）*/
	@TableField(value="annual_assessment_targets")
	@Length(max=200) private String annualAssessmentTargets;

	/** 医保统筹区/类别 */
	@TableField(value="insurance_plan_category")
	private Long insurancePlanCategory;
	

	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getMonthId() {
		return monthId;
	}
	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	public String getDiseaseType() {
		return diseaseType;
	}
	public void setDiseaseType(String diseaseType) {
		this.diseaseType = diseaseType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getMainDiagnosisName() {
		return mainDiagnosisName;
	}
	public void setMainDiagnosisName(String mainDiagnosisName) {
		this.mainDiagnosisName = mainDiagnosisName;
	}
	public String getMainSurgeryName() {
		return mainSurgeryName;
	}
	public void setMainSurgeryName(String mainSurgeryName) {
		this.mainSurgeryName = mainSurgeryName;
	}
	public java.math.BigDecimal getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(java.math.BigDecimal totalCases) {
		this.totalCases = totalCases;
	}
	public java.math.BigDecimal getActualHospitalizationCost() {
		return actualHospitalizationCost;
	}
	public void setActualHospitalizationCost(java.math.BigDecimal actualHospitalizationCost) {
		this.actualHospitalizationCost = actualHospitalizationCost;
	}
	public java.math.BigDecimal getExpectedSettlementCost() {
		return expectedSettlementCost;
	}
	public void setExpectedSettlementCost(java.math.BigDecimal expectedSettlementCost) {
		this.expectedSettlementCost = expectedSettlementCost;
	}
	public java.math.BigDecimal getExpectedBalanceAmount() {
		return expectedBalanceAmount;
	}
	public void setExpectedBalanceAmount(java.math.BigDecimal expectedBalanceAmount) {
		this.expectedBalanceAmount = expectedBalanceAmount;
	}
	public java.math.BigDecimal getAvgActualCost() {
		return avgActualCost;
	}
	public void setAvgActualCost(java.math.BigDecimal avgActualCost) {
		this.avgActualCost = avgActualCost;
	}
	public java.math.BigDecimal getAvgExpectedSettlement() {
		return avgExpectedSettlement;
	}
	public void setAvgExpectedSettlement(java.math.BigDecimal avgExpectedSettlement) {
		this.avgExpectedSettlement = avgExpectedSettlement;
	}
	public java.math.BigDecimal getAvgExpectedBalanceAmount() {
		return avgExpectedBalanceAmount;
	}
	public void setAvgExpectedBalanceAmount(java.math.BigDecimal avgExpectedBalanceAmount) {
		this.avgExpectedBalanceAmount = avgExpectedBalanceAmount;
	}
	public String getProfitLossAnalysis() {
		return profitLossAnalysis;
	}
	public void setProfitLossAnalysis(String profitLossAnalysis) {
		this.profitLossAnalysis = profitLossAnalysis;
	}
	public String getAnnualAssessmentTargets() {
		return annualAssessmentTargets;
	}
	public void setAnnualAssessmentTargets(String annualAssessmentTargets) {
		this.annualAssessmentTargets = annualAssessmentTargets;
	}

	public Long getInsurancePlanCategory() {
		return insurancePlanCategory;
	}

	public void setInsurancePlanCategory(Long insurancePlanCategory) {
		this.insurancePlanCategory = insurancePlanCategory;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}