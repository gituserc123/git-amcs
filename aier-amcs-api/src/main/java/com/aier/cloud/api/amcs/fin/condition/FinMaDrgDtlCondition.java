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
package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import java.util.Date;
import java.util.List;

/**
 * T_FIN_MA_DRG_DTL
 * 医保管理分析DRG表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:37
 */
public class FinMaDrgDtlCondition extends PageCondition {

	private Long id;

	private java.util.Date modifyDate;

	private Long modifer;
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
	
	/** DRG分组编码*/
	private String groupCode;
	
	/** DRG分组*/
	private String groupName;
	
	/** 医疗结算项编码*/
	private String medicalItemCode;
	
	/** 医疗结算项名称*/
	private String medicalItemName;
	
	/** 管理分析标识*/
	private String maIdentifier;
	
	/** 主表主键*/
	private Long mainId;

	private List<Long> mainIdList;
	

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public List<Long> getMainIdList() {
		return mainIdList;
	}

	public void setMainIdList(List<Long> mainIdList) {
		this.mainIdList = mainIdList;
	}
}