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

/**
 * T_FIN_MA_DRG_DTL
 * 医保管理分析DRG表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:37
 */
@TableName("T_FIN_MA_DRG_DTL")
public class FinMaDrgDtl extends BaseEntity<FinMaDrgDtl> {
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 医院hospId*/
	@TableField(value="hosp_id")
	private Long hospId;
	
	/** 业务键*/
	@TableField(value="busi_sign")
	@Length(max=20) private String busiSign;
	
	/** 双眼*/
	@TableField(value="couple_eye")
	private java.math.BigDecimal coupleEye;
	
	/** 单眼*/
	@TableField(value="single_eye")
	private java.math.BigDecimal singleEye;
	
	/** DRG分组编码*/
	@TableField(value="group_code")
	@Length(max=40) private String groupCode;
	
	/** DRG分组*/
	@TableField(value="group_name")
	@Length(max=40) private String groupName;
	
	/** 医疗结算项编码*/
	@TableField(value="medical_item_code")
	@Length(max=20) private String medicalItemCode;
	
	/** 医疗结算项名称*/
	@TableField(value="medical_item_name")
	@Length(max=40) private String medicalItemName;
	
	/** 管理分析标识*/
	@TableField(value="ma_identifier")
	@Length(max=20) private String maIdentifier;
	
	/** 主表主键*/
	@TableField(value="main_id")
	private Long mainId;
	

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
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
}