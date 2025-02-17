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

import java.math.BigDecimal;

/**
 * T_FIN_INS_DRG_PAY
 * DRG付费表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:55
 */
@TableName("T_FIN_INS_DRG_PAY")
public class FinInsDrgPay extends BaseEntity<FinInsDrgPay> {
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
	
	/** 实际执行DIP付费的时间（已执行DIP付费的医院填写并填写附表1）*/
	@TableField(value="actual_execution_time")
	private java.util.Date actualExecutionTime;
	
	/** 预计执行DIP付费的时间（暂未执行DIP付费的医院填写）*/
	@TableField(value="expected_execution_time")
	private java.util.Date expectedExecutionTime;
	
	/** DIP结算系数或机构系数或差异系数*/
	@TableField(value="differential_coefficient")
	private java.math.BigDecimal differentialCoefficient;
	
	/** 截至填报月预计节余/超支总金额（万元，节余填正，超支填负数）*/
	@TableField(value="balance_or_overspend")
	private java.math.BigDecimal balanceOrOverspend;
	
	/** “三新项目”除外支付政策*/
	@TableField(value="excluding_three_new")
	@Length(max=200) private String excludingThreeNew;

	/** 超总额整改措施（500字符以上）*/
	@TableField(value="rectification_measures")
	@NotBlank private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	@TableField(value="feedback_to_management")
	@NotBlank private String feedbackToManagement;

	/** DRG总额控制（万元） */
	@TableField(value="total_balance_control")
	private java.math.BigDecimal totalBalanceControl;

	/** DRG总权重控制 */
	@TableField(value="overall_weight_control")
	private java.math.BigDecimal overallWeightControl;



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
	public java.util.Date getActualExecutionTime() {
		return actualExecutionTime;
	}
	public void setActualExecutionTime(java.util.Date actualExecutionTime) {
		this.actualExecutionTime = actualExecutionTime;
	}
	public java.util.Date getExpectedExecutionTime() {
		return expectedExecutionTime;
	}
	public void setExpectedExecutionTime(java.util.Date expectedExecutionTime) {
		this.expectedExecutionTime = expectedExecutionTime;
	}
	public java.math.BigDecimal getDifferentialCoefficient() {
		return differentialCoefficient;
	}
	public void setDifferentialCoefficient(java.math.BigDecimal differentialCoefficient) {
		this.differentialCoefficient = differentialCoefficient;
	}
	public java.math.BigDecimal getBalanceOrOverspend() {
		return balanceOrOverspend;
	}
	public void setBalanceOrOverspend(java.math.BigDecimal balanceOrOverspend) {
		this.balanceOrOverspend = balanceOrOverspend;
	}
	public String getExcludingThreeNew() {
		return excludingThreeNew;
	}
	public void setExcludingThreeNew(String excludingThreeNew) {
		this.excludingThreeNew = excludingThreeNew;
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

	public BigDecimal getTotalBalanceControl() {
		return totalBalanceControl;
	}

	public void setTotalBalanceControl(BigDecimal totalBalanceControl) {
		this.totalBalanceControl = totalBalanceControl;
	}

	public BigDecimal getOverallWeightControl() {
		return overallWeightControl;
	}

	public void setOverallWeightControl(BigDecimal overallWeightControl) {
		this.overallWeightControl = overallWeightControl;
	}
}