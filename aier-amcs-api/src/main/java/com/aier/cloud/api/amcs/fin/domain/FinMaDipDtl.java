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

import java.util.Date;

/**
 * T_FIN_MA_DIP_DTL
 * 医保管理分析DIP表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:37
 */
public class FinMaDipDtl extends BaseEntity {

	private Long id;
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 创建者ID*/
	private Long creator;
	
	/** 医院hospId*/
	private Long hospId;
	
	/** 业务键*/
	private String busiSign;
	
	/** 双眼*/
	private java.math.BigDecimal coupleEye;
	
	/** 单眼*/
	private java.math.BigDecimal singleEye;
	
	/** 手术名称code*/
	private String operationCode;
	
	/** 手术名称*/
	private String operationName;
	
	/** 诊断名称code*/
	private String diagCode;
	
	/** 诊断名称*/
	private String diagName;
	
	/** 医疗结算项编码*/
	private String medicalItemCode;
	
	/** 医疗结算项名称*/
	private String medicalItemName;
	
	/** 管理分析标识*/
	private String maIdentifier;
	
	/** 主表主键*/
	private Long mainId;

	private java.util.Date modifyDate;

	private Long modifer;

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
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getBusiSign() {
		return busiSign;
	}
	public void setBusiSign(String busiSign) {
		this.busiSign = busiSign;
	}
	public java.math.BigDecimal getCoupleEye() {
		return coupleEye;
	}
	public void setCoupleEye(java.math.BigDecimal coupleEye) {
		this.coupleEye = coupleEye;
	}
	public java.math.BigDecimal getSingleEye() {
		return singleEye;
	}
	public void setSingleEye(java.math.BigDecimal singleEye) {
		this.singleEye = singleEye;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getDiagName() {
		return diagName;
	}
	public void setDiagName(String diagName) {
		this.diagName = diagName;
	}
	public String getMedicalItemCode() {
		return medicalItemCode;
	}
	public void setMedicalItemCode(String medicalItemCode) {
		this.medicalItemCode = medicalItemCode;
	}
	public String getMedicalItemName() {
		return medicalItemName;
	}
	public void setMedicalItemName(String medicalItemName) {
		this.medicalItemName = medicalItemName;
	}
	public String getMaIdentifier() {
		return maIdentifier;
	}
	public void setMaIdentifier(String maIdentifier) {
		this.maIdentifier = maIdentifier;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	@Override
	public Date getModifyDate() {
		return modifyDate;
	}

	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public Long getModifer() {
		return modifer;
	}

	@Override
	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}
}