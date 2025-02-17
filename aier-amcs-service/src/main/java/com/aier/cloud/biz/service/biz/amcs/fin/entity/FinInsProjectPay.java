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

import java.math.BigDecimal;

/**
 * T_FIN_INS_PROJECT_PAY
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */
@TableName("T_FIN_INS_PROJECT_PAY")
public class FinInsProjectPay extends BaseEntity<FinInsProjectPay> {
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 主表ID*/
	@TableField(value="main_id")
	@NotBlank private Long mainId;

	/** monthId*/
	@TableField(value="month_id")
	@NotBlank private Long monthId;
	
	/** 按项目付费结算病种*/
	@TableField(value="settled_by_project_payment")
	@NotBlank @Length(max=50) private String settledByProjectPayment;
	
	/** 医保报销政策 */
	@Comment(value="医保报销政策")
	@TableField(value="reimbursement_policy")
	@Length(max=200)
	@NotBlank private String reimbursementPolicy;

	/** 年度总指标（单位：万元）*/
	@TableField(value="total_indicator")
	private java.math.BigDecimal totalIndicator;

	/** 截至填报月实际已使用医保总额（单位：万元）*/
	@TableField(value="actual_used_amount")
	private java.math.BigDecimal actualUsedAmount;

	/** 超总额原因（500字符以上）*/
	@TableField(value="exceed_reason")
	@NotBlank private String exceedReason;

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
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	public String getSettledByProjectPayment() {
		return settledByProjectPayment;
	}
	public void setSettledByProjectPayment(String settledByProjectPayment) {
		this.settledByProjectPayment = settledByProjectPayment;
	}
	public Long getMonthId() {
		return monthId;
	}

	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}

	public String getReimbursementPolicy() {
		return reimbursementPolicy;
	}

	public void setReimbursementPolicy(String reimbursementPolicy) {
		this.reimbursementPolicy = reimbursementPolicy;
	}

	public BigDecimal getTotalIndicator() {
		return totalIndicator;
	}

	public void setTotalIndicator(BigDecimal totalIndicator) {
		this.totalIndicator = totalIndicator;
	}

	public BigDecimal getActualUsedAmount() {
		return actualUsedAmount;
	}

	public void setActualUsedAmount(BigDecimal actualUsedAmount) {
		this.actualUsedAmount = actualUsedAmount;
	}

	public String getExceedReason() {
		return exceedReason;
	}

	public void setExceedReason(String exceedReason) {
		this.exceedReason = exceedReason;
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