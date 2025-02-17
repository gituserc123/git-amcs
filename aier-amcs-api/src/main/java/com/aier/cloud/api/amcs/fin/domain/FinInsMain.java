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
package com.aier.cloud.api.amcs.fin.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * T_FIN_INS_MAIN
 * 医保政策主表
 * @author 爱尔眼科
 * @since 2023-03-16 15:38:21
 */

@Data
public class FinInsMain extends BaseEntity {
	/** 月主表ID*/
	@TableField(value="month_id")
	@NotBlank private Long monthId;

	/** 医保坏账核销情况原因*/
	@TableField(value="insurance_bad_debt_cause")
	@Length(max=100) private String insuranceBadDebtCause;

	/** 慈善核销金额（万元）*/
	@TableField(value="charity_bad_debt_amt")
	private java.math.BigDecimal charityBadDebtAmt;

	/** 慈善坏账核销情况原因*/
	@TableField(value="charity_bad_debt_cause")
	@Length(max=100) private String charityBadDebtCause;

	/** 年医保扣罚款情况扣款金额（万元）*/
	@TableField(value="penalty_deduction_amount")
	private java.math.BigDecimal penaltyDeductionAmount;

	/** 年医保扣罚款情况罚款金额（万元）*/
	@TableField(value="penalty_fee_amount")
	private java.math.BigDecimal penaltyFeeAmount;

	/** 住院结算政策（码表:SETTLEMENT_POLICY）*/
	@TableField(value="hosp_settlement_policy")
	@NotBlank private String hospSettlementPolicy;

	/** 医保协议规定预留金比例（%）*/
	@TableField(value="agreement_reserve_ratio")
	private java.math.BigDecimal agreementReserveRatio;

	/** 自费率考核指标（≤%）*/
	@TableField(value="self_payment_rate")
	private java.math.BigDecimal selfPaymentRate;

	/** 自费率超标原因*/
	@TableField(value="reasons_for_exceeding")
	@Length(max=100) private String reasonsForExceeding;

	/** 定点服务协议限制性条款*/
	@TableField(value="designated_service_agreement")
	@Length(max=400) private String designatedServiceAgreement;

	/** 慈善协议是否到当地医保局备案(1是，2否）*/
	@TableField(value="is_agreement")
	private Integer isAgreement;

	/** 未备案原因*/
	@TableField(value="unfiled_agreement_reasons")
	@Length(max=100) private String unfiledAgreementReasons;

	/** 是否存在超协议标准优免行为(1是，2否）*/
	@TableField(value="existence_agreed_standards")
	private Integer existenceAgreedStandards;

	/** 医院实际开展日间手术的病种*/
	@TableField(value="day_surgery_in_hospital")
	@Length(max=50) private String daySurgeryInHospital;

	/** 日间手术结算政策*/
	@TableField(value="day_surgery_settlement_policy")
	@Length(max=200) private String daySurgerySettlementPolicy;

	/** 门诊统筹结算政策*/
	@TableField(value="outp_unified_settlement_policy")
	@Length(max=200) private String outpUnifiedSettlementPolicy;

	/** 特（慢）结算政策*/
	@TableField(value="special_settlement_policy")
	@Length(max=200) private String specialSettlementPolicy;

	/** 其他事项*/
	@TableField(value="other_issues")
	@Length(max=200) private String otherIssues;

	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;

	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;

	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 医保统筹区（码表：POOLING_AREA）*/
	@TableField(value="pooling_area")
	@Length(max=20) private String poolingArea;

	/** 医保类别（码表：INSURANCE_TYPE）*/
	@TableField(value="insurance_type")
	@NotBlank private Integer insuranceType;

	/** 卫健委定级(码表：LEVEL)*/
	@TableField(value="health_commission_level")
	@NotBlank private Integer healthCommissionLevel;

	/** 医保结算等级(码表：LEVEL)*/
	@TableField(value="insurance_settlement_level")
	@NotBlank private Integer insuranceSettlementLevel;

	/** 应收医保款期末余额（万元）*/
	@TableField(value="receivable_balance_end_period")
	@NotBlank private java.math.BigDecimal receivableBalanceEndPeriod;

	/** 应收医保款回款率*/
	@TableField(value="receivable_collection_rate")
	@NotBlank private java.math.BigDecimal receivableCollectionRate;

	/** 医保坏账核销金额（万元）*/
	@TableField(value="insurance_bad_debt_amt")
	private java.math.BigDecimal insuranceBadDebtAmt;


	/** 备案医保局是否出具书面回执(1是，0否）*/
	@TableField(value="IS_WRITTEN_ACK")
	private Integer isWrittenAck;


	/** 本次填报周期*/
	@TableField(value="period")
	private Integer period;

}