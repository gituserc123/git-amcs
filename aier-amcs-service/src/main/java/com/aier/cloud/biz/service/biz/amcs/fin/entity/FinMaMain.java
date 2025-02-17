/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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
 * T_FIN_MA_MAIN
 * 医保管理分析主表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:36
 */
@TableName("T_FIN_MA_MAIN")
public class FinMaMain extends BaseEntity<FinMaMain> {
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 医保结算等级(码表：LEVEL)*/
	@TableField(value="insurance_settlement_level")
	private Integer insuranceSettlementLevel;
	
	/** 卫健委定级(码表：LEVEL)*/
	@TableField(value="health_commission_level")
	private Integer healthCommissionLevel;
	
	/** 医院等级(省级/县级/...)*/
	@TableField(value="ehr_level")
	@Length(max=40) private String ehrLevel;
	
	/** 省区*/
	@TableField(value="hosp_parent")
	@Length(max=40) private String hospParent;
	
	/** 医院类型(上市/合伙)*/
	@TableField(value="invest_nature")
	@Length(max=10) private String investNature;
	
	/** 医院hospId*/
	@TableField(value="hosp_id")
	private Long hospId;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@Length(max=40) private String hospName;
	
	/** 医保结算政策是否已明确(Is the medical insurance settlement policy clear?)*/
	@TableField(value="misp_clear_flag")
	private Integer mispClearFlag;
	
	/** 医保类型(职工/居民)*/
	@TableField(value="insurance_type")
	private Integer insuranceType;
	
	/** 结算方式(DIP/DRG)*/
	@TableField(value="settlement_method")
	@Length(max=10) private String settlementMethod;
	
	/** 季度*/
	@TableField(value="ma_quarter")
	private Integer maQuarter;
	
	/** 年度*/
	@TableField(value="ma_year")
	private Integer maYear;
	
	/** 管理分析标识(同一表单内共享,表示同一年同一个季度的记录)*/
	@TableField(value="ma_identifier")
	@Length(max=20) private String maIdentifier;

	/** 业务键*/
	@TableField(exist = false)
	private String busiSign;

	/** 单眼*/
	@TableField(exist = false)
	private java.math.BigDecimal singleEye;
	

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
	public Integer getInsuranceSettlementLevel() {
		return insuranceSettlementLevel;
	}
	public void setInsuranceSettlementLevel(Integer insuranceSettlementLevel) {
		this.insuranceSettlementLevel = insuranceSettlementLevel;
	}
	public Integer getHealthCommissionLevel() {
		return healthCommissionLevel;
	}
	public void setHealthCommissionLevel(Integer healthCommissionLevel) {
		this.healthCommissionLevel = healthCommissionLevel;
	}
	public String getEhrLevel() {
		return ehrLevel;
	}
	public void setEhrLevel(String ehrLevel) {
		this.ehrLevel = ehrLevel;
	}
	public String getHospParent() {
		return hospParent;
	}
	public void setHospParent(String hospParent) {
		this.hospParent = hospParent;
	}
	public String getInvestNature() {
		return investNature;
	}
	public void setInvestNature(String investNature) {
		this.investNature = investNature;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public Integer getMispClearFlag() {
		return mispClearFlag;
	}
	public void setMispClearFlag(Integer mispClearFlag) {
		this.mispClearFlag = mispClearFlag;
	}
	public Integer getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(Integer insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getSettlementMethod() {
		return settlementMethod;
	}
	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}
	public Integer getMaQuarter() {
		return maQuarter;
	}
	public void setMaQuarter(Integer maQuarter) {
		this.maQuarter = maQuarter;
	}
	public Integer getMaYear() {
		return maYear;
	}
	public void setMaYear(Integer maYear) {
		this.maYear = maYear;
	}
	public String getMaIdentifier() {
		return maIdentifier;
	}
	public void setMaIdentifier(String maIdentifier) {
		this.maIdentifier = maIdentifier;
	}

	public String getBusiSign() {
		return busiSign;
	}

	public void setBusiSign(String busiSign) {
		this.busiSign = busiSign;
	}

	public BigDecimal getSingleEye() {
		return singleEye;
	}

	public void setSingleEye(BigDecimal singleEye) {
		this.singleEye = singleEye;
	}
}