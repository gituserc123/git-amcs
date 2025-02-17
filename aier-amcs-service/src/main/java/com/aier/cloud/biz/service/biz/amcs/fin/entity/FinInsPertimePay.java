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
 * T_FIN_INS_PERTIME_PAY
 * 按人头付费表
 * @author 爱尔眼科
 * @since 2023-04-05 17:47:56
 */
@TableName("T_FIN_INS_PERTIME_PAY")
public class FinInsPertimePay extends BaseEntity<FinInsPertimePay> {
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
	
	/** 按人头（次均）结算政策*/
	@TableField(value="settlement_policy")
	@NotBlank @Length(max=200) private String settlementPolicy;
	
	/** 截至填报月预计节余/超支总金额（万元，节余填正，超支填负数）*/
	@TableField(value="balance_or_deficit_amount")
	@NotBlank private java.math.BigDecimal balanceOrDeficitAmount;
	
	/** 超次均原因*/
	@TableField(value="exceeded_reason")
	@Length(max=100) private String exceededReason;
	

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
	public String getSettlementPolicy() {
		return settlementPolicy;
	}
	public void setSettlementPolicy(String settlementPolicy) {
		this.settlementPolicy = settlementPolicy;
	}
	public java.math.BigDecimal getBalanceOrDeficitAmount() {
		return balanceOrDeficitAmount;
	}
	public void setBalanceOrDeficitAmount(java.math.BigDecimal balanceOrDeficitAmount) {
		this.balanceOrDeficitAmount = balanceOrDeficitAmount;
	}
	public String getExceededReason() {
		return exceededReason;
	}
	public void setExceededReason(String exceededReason) {
		this.exceededReason = exceededReason;
	}
}