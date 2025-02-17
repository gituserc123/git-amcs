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

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_FIN_INS_ADVANCE_TOTAL
 * 总额预付表
 * @author 爱尔眼科
 * @since 2023-04-04 16:59:21
 */
@TableName("T_FIN_INS_ADVANCE_TOTAL")
public class FinInsAdvanceTotal extends BaseEntity<FinInsAdvanceTotal> {
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
	
	/** 2023年总额指标（万元）*/
	@TableField(value="total_amount")
	@NotBlank private java.math.BigDecimal totalAmount;
	
	/** 截至填报月实际已使用医保总额（万元）*/
	@TableField(value="actual_medical_insurance_total")
	@NotBlank private java.math.BigDecimal actualMedicalInsuranceTotal;
	
	/** 超总额原因*/
	@TableField(value="exceeded_total_amount_reason")
	@Length(max=100) private String exceededTotalAmountReason;
	
	/** 总额指标是否包含单病种结算费用（1是，2否）*/
	@TableField(value="single_disease_settlement_sign")
	private Integer singleDiseaseSettlementSign;

	/** 超总额整改措施（500字符以上）*/
	@TableField(value="rectification_measures")
	@NotBlank private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	@TableField(value="feedback_to_management")
	@NotBlank private String feedbackToManagement;



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
	public java.math.BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(java.math.BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public java.math.BigDecimal getActualMedicalInsuranceTotal() {
		return actualMedicalInsuranceTotal;
	}
	public void setActualMedicalInsuranceTotal(java.math.BigDecimal actualMedicalInsuranceTotal) {
		this.actualMedicalInsuranceTotal = actualMedicalInsuranceTotal;
	}
	public String getExceededTotalAmountReason() {
		return exceededTotalAmountReason;
	}
	public void setExceededTotalAmountReason(String exceededTotalAmountReason) {
		this.exceededTotalAmountReason = exceededTotalAmountReason;
	}
	public Integer getSingleDiseaseSettlementSign() {
		return singleDiseaseSettlementSign;
	}
	public void setSingleDiseaseSettlementSign(Integer singleDiseaseSettlementSign) {
		this.singleDiseaseSettlementSign = singleDiseaseSettlementSign;
	}

	public String getRectificationMeasures() {
		return rectificationMeasures;
	}

	public void setRectificationMeasures(String rectificationMeasures) {
		this.rectificationMeasures = rectificationMeasures;
	}

	public String getFeedbackToManagement() {
		return feedbackToManagement;
	}

	public void setFeedbackToManagement(String feedbackToManagement) {
		this.feedbackToManagement = feedbackToManagement;
	}
}