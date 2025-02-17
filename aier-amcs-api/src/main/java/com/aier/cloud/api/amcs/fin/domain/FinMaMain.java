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
package com.aier.cloud.api.amcs.fin.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_FIN_MA_MAIN
 * 医保管理分析主表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:36
 */
public class FinMaMain extends BaseEntity {

	private Long id;
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 创建者ID*/
	private Long creator;
	
	/** 医保结算等级(码表：LEVEL)*/
	private Integer insuranceSettlementLevel;
	
	/** 卫健委定级(码表：LEVEL)*/
	private Integer healthCommissionLevel;
	
	/** 医院等级(省级/县级/...)*/
	private String ehrLevel;
	
	/** 省区*/
	private String hospParent;
	
	/** 医院类型(上市/合伙)*/
	private String investNature;
	
	/** 医院hospId*/
	private Long hospId;
	
	/** 医院名称*/
	private String hospName;
	
	/** 医保结算政策是否已明确(Is the medical insurance settlement policy clear?)*/
	private Integer mispClearFlag;
	
	/** 医保类型(职工/居民)*/
	private Integer insuranceType;
	
	/** 结算方式(DIP/DRG)*/
	private String settlementMethod;
	
	/** 季度*/
	private Integer maQuarter;
	
	/** 年度*/
	private Integer maYear;
	
	/** 管理分析标识(同一表单内共享,表示同一年同一个季度的记录)*/
	private String maIdentifier;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
}