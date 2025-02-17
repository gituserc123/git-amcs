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
 * T_AE_DRUG_REACT_DRUGS
 * 药品不良反应事件药物信息表
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@TableName("T_AE_DRUG_REACT_DRUGS")
public class AeDrugReactDrugs extends BaseEntity<AeDrugReactDrugs> {
	/** 医院编号*/
	@TableField(value="hosp_no")
	@Length(max=10) private String hospNo;
	
	/** 类型*/
	@TableField(value="type_drug")
	@Length(max=20) private String typeDrug;
	
	/** 药品不良反应事件表ID*/
	@TableField(value="react_id")
	private Long reactId;
	
	/** */
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** */
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 用药原因*/
	@TableField(value="medication_reason")
	@Length(max=200) private String medicationReason;
	
	/** 用药起止时间*/
	@TableField(value="medication_begin_time")
	@Length(max=200) private java.util.Date medicationBeginTime;

	/** 用药结束时间*/
	@TableField(value="medication_end_time")
	@Length(max=200) private java.util.Date medicationEndTime;
	
	/** 默认频次*/
	@TableField(value="default_freq")
	private Integer defaultFreq;
	
	/** 用法用量*/
	@TableField(value="usage_drug")
	@Length(max=200) private java.math.BigDecimal usageDrug;
	
	/** 批号*/
	@TableField(value="batch_number")
	@Length(max=100) private String batchNumber;

	/** 国药准字*/
	@TableField(value="medical_approval_num")
	@Length(max=100) private String medicalApprovalNum;


	/** 生产厂家*/
	@TableField(value="manufacturer")
	@Length(max=200) private String manufacturer;
	
	/** 通用名称*/
	@TableField(value="common_name")
	@Length(max=200) private String commonName;
	
	/** 商品名称*/
	@TableField(value="trade_name")
	@Length(max=200) private String tradeName;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@Length(max=30) private String hospName;

	/** 医院ID*/
	@Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** HIS药物ID*/
	@Comment(value="HIS药物ID")
	@TableField(value="his_drug_id")
	@NotBlank private Long hisDrugId;

	/** 用量单位(码表:react_usage_unit)*/
	@TableField(value="usage_unit")
	private Integer usageUnit;
	

	public String getHospNo() {
		return hospNo;
	}
	public void setHospNo(String hospNo) {
		this.hospNo = hospNo;
	}
	public String getTypeDrug() {
		return typeDrug;
	}
	public void setTypeDrug(String typeDrug) {
		this.typeDrug = typeDrug;
	}
	public Long getReactId() {
		return reactId;
	}
	public void setReactId(Long reactId) {
		this.reactId = reactId;
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
	public String getMedicationReason() {
		return medicationReason;
	}
	public void setMedicationReason(String medicationReason) {
		this.medicationReason = medicationReason;
	}
	public Integer getDefaultFreq() {
		return defaultFreq;
	}
	public void setDefaultFreq(Integer defaultFreq) {
		this.defaultFreq = defaultFreq;
	}
	public java.math.BigDecimal getUsageDrug() {
		return usageDrug;
	}
	public void setUsageDrug(java.math.BigDecimal usageDrug) {
		this.usageDrug = usageDrug;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getMedicalApprovalNum() {
		return medicalApprovalNum;
	}

	public void setMedicalApprovalNum(String medicalApprovalNum) {
		this.medicalApprovalNum = medicalApprovalNum;
	}

	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Long getHisDrugId() {
		return hisDrugId;
	}

	public void setHisDrugId(Long hisDrugId) {
		this.hisDrugId = hisDrugId;
	}

	public java.util.Date getMedicationBeginTime() {
		return medicationBeginTime;
	}

	public void setMedicationBeginTime(java.util.Date medicationBeginTime) {
		this.medicationBeginTime = medicationBeginTime;
	}

	public java.util.Date getMedicationEndTime() {
		return medicationEndTime;
	}

	public void setMedicationEndTime(java.util.Date medicationEndTime) {
		this.medicationEndTime = medicationEndTime;
	}

	public Integer getUsageUnit() {
		return usageUnit;
	}

	public void setUsageUnit(Integer usageUnit) {
		this.usageUnit = usageUnit;
	}
}