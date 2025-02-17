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

import javax.validation.constraints.NotBlank;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.basic.starter.service.controller.DictName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeDictName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_AE_OUTP_PATIENT
 * 门诊患者不良事件(手术患者）
 * @author 爱尔眼科
 * @since 2022-12-06 16:35:59
 */
@TableName("T_AE_OUTP_PATIENT")
public class AeOutpPatient extends BaseEntity<AeOutpPatient> {

	private static final long serialVersionUID = 5080215170608109163L;

	/** 门诊时间*/
    @Comment(value="门诊时间")
	@TableField(value="visit_date")
	private java.util.Date visitDate;
	
	/** 门诊诊断名称*/
    @Comment(value="门诊诊断名称")
	@TableField(value="outp_diag_name")
	@Length(max=500) private String outpDiagName;

	@TableField(exist = false)
	private String payDiagnose;

	public String getPayDiagnose() {
		return outpDiagName;
	}
	
	/** 右眼门诊视力(OD)类型(1数值2描述）*/
    @Comment(value="右眼门诊视力(OD)类型(1数值2描述）")
	@TableField(value="outp_vod_type")
	private Integer outpVodType;
	
	/** 右眼门诊视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="右眼门诊视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="outp_vod")
	@Length(max=2) private String outpVod;
	
	/** 右眼门诊视力(OD)名称*/
    @Comment(value="右眼门诊视力(OD)名称")
	@TableField(value="outp_vod_name")
	@Length(max=10) private String outpVodName;
	
	/** 左眼门诊视力(OS)类型(1数值2描述）*/
    @Comment(value="左眼门诊视力(OS)类型(1数值2描述）")
	@TableField(value="outp_vos_type")
	private Integer outpVosType;
	
	/** 左眼门诊视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="左眼门诊视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="outp_vos")
	@Length(max=2) private String outpVos;
	
	/** 左眼门诊视力(OS)名称*/
    @Comment(value="左眼门诊视力(OS)名称")
	@TableField(value="outp_vos_name")
	@Length(max=10) private String outpVosName;
	
	/** 右眼门诊眼压(OD)类型编码(码表：iop_type）*/
    @Comment(value="右眼门诊眼压(OD)类型编码(码表：iop_type）")
	@TableField(value="outp_iop_type_od")
	@Length(max=2) private String outpIopTypeOd;
	
	/** 右眼门诊眼压(OD)类型名称*/
    @Comment(value="右眼门诊眼压(OD)类型名称")
	@TableField(value="outp_iop_type_od_name")
	@DictName(type = "iop_type", from = "outpIopTypeOd")
	@Length(max=20) private String outpIopTypeOdName;
	
	/** 右眼门诊眼压(OD)数值或指测眼压编码*/
    @Comment(value="右眼门诊眼压(OD)数值或指测眼压编码")
	@TableField(value="outp_iop_od")
	@Length(max=10) private String outpIopOd;
	
	/** 右眼门诊眼压(OD)指测眼压名称*/
    @Comment(value="右眼门诊眼压(OD)指测眼压名称")
	@TableField(value="outp_iop_od_name")
	@Length(max=10) private String outpIopOdName;
	
	/** 左眼门诊眼压(OS)类型编码(码表：iop_type）*/
    @Comment(value="左眼门诊眼压(OS)类型编码(码表：iop_type）")
	@TableField(value="outp_iop_type_os")
	@Length(max=2) private String outpIopTypeOs;
	
	/** 左眼门诊眼压(OS)类型名称*/
    @Comment(value="左眼门诊眼压(OS)类型名称")
	@TableField(value="outp_iop_type_os_name")
	@DictName(type = "iop_type", from = "outpIopTypeOs")
	@Length(max=20) private String outpIopTypeOsName;
	
	/** 左眼门诊眼压(OS)数值或指测眼压编码*/
    @Comment(value="左眼门诊眼压(OS)数值或指测眼压编码")
	@TableField(value="outp_iop_os")
	@Length(max=10) private String outpIopOs;
	
	/** 左眼门诊眼压(OS)指测眼压名称*/
    @Comment(value="左眼门诊眼压(OS)指测眼压名称")
	@TableField(value="outp_iop_os_name")
	@Length(max=10) private String outpIopOsName;
	
	/** 拟施手术*/
    @Comment(value="拟施手术")
	@TableField(value="intended_operation")
	@Length(max=500) private String intendedOperation;
	
	/** 实施手术*/
    @Comment(value="实施手术")
	@TableField(value="implement_operation")
	@Length(max=500) private String implementOperation;
	
	/** 右眼当前视力(OD)类型(1数值2描述）*/
    @Comment(value="右眼当前视力(OD)类型(1数值2描述）")
	@TableField(value="current_vod_type")
	private Integer currentVodType;
	
	/** 右眼当前视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="右眼当前视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="current_vod")
	@Length(max=2) private String currentVod;
	
	/** 右眼当前视力(OD)名称*/
    @Comment(value="右眼当前视力(OD)名称")
	@TableField(value="current_vod_name")
	@Length(max=10) private String currentVodName;
	
	/** 左眼当前视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="左眼当前视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="current_vos_type")
	private Integer currentVosType;
	
	/** 左眼当前视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="左眼当前视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="current_vos")
	@Length(max=2) private String currentVos;
	
	/** 左眼当前视力(OS)名称*/
    @Comment(value="左眼当前视力(OS)名称")
	@TableField(value="current_vos_name")
	@Length(max=10) private String currentVosName;
	
	/** 右眼当前眼压(OD)类型编码(码表：iop_type）*/
    @Comment(value="右眼当前眼压(OD)类型编码(码表：iop_type）")
	@TableField(value="current_iop_type_od")
	@Length(max=2) private String currentIopTypeOd;
	
	/** 右眼当前眼压(OD)类型名称*/
    @Comment(value="右眼当前眼压(OD)类型名称")
	@TableField(value="current_iop_type_od_name")
	@DictName(type = "iop_type", from = "currentIopTypeOd")
	@Length(max=20) private String currentIopTypeOdName;
	
	/** 右眼当前眼压(OD)数值或指测眼压编码*/
    @Comment(value="右眼当前眼压(OD)数值或指测眼压编码")
	@TableField(value="current_iop_od")
	@Length(max=10) private String currentIopOd;
	
	/** 右眼当前眼压(OD)指测眼压名称*/
    @Comment(value="右眼当前眼压(OD)指测眼压名称")
	@TableField(value="current_iop_od_name")
	@Length(max=10) private String currentIopOdName;
	
	/** 左眼当前眼压(OS)类型编码(码表：iop_type）*/
    @Comment(value="左眼当前眼压(OS)类型编码(码表：iop_type）")
	@TableField(value="current_iop_type_os")
	@Length(max=2) private String currentIopTypeOs;
	
	/** 左眼当前眼压(OS)类型名称*/
    @Comment(value="左眼当前眼压(OS)类型名称")
	@TableField(value="current_iop_type_os_name")
	@DictName(type = "iop_type", from = "currentIopTypeOs")
	@Length(max=20) private String currentIopTypeOsName;
	
	/** 左眼当前眼压(OS)数值或指测眼压编码*/
    @Comment(value="左眼当前眼压(OS)数值或指测眼压编码")
	@TableField(value="current_iop_os")
	@Length(max=10) private String currentIopOs;
	
	/** 左眼当前眼压(OS)指测眼压名称*/
    @Comment(value="左眼当前眼压(OS)指测眼压名称")
	@TableField(value="current_iop_os_name")
	@Length(max=10) private String currentIopOsName;
	
	/** 非计划情况（码表：unplan）*/
    @Comment(value="非计划情况（码表：unplan）")
	@TableField(value="unplan")
	private Integer unplan;
	
	/** 非计划情况名称*/
    @Comment(value="非计划情况名称")
	@TableField(value="unplan_name")
	@AeDictName(type = "unplan", from = "unplan")
	@Length(max=20) private String unplanName;
	
	/** 目前情况*/
    @Comment(value="目前情况")
	@TableField(value="curr_situation")
	@Length(max=500) private String currSituation;
	
	/** 患者及家属诉求*/
    @Comment(value="患者及家属诉求")
	@TableField(value="patient_appeals")
	@Length(max=500) private String patientAppeals;
	
	/** 事件经过描述*/
    @Comment(value="事件经过描述")
	@TableField(value="event_desc")
	@Length(max=2000) private String eventDesc;
	
	/** 创建者ID*/
    @Comment(value="创建者ID")
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
    @Comment(value="创建时间")
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
    @Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;

	/** 事件经过描述_1 */
    @Comment(value="事件经过描述_1")
	@TableField(value = "event_desc_one")
	@Length(max = 2000)
	private String eventDescOne;

	/** 事件经过描述_2 */
    @Comment(value="事件经过描述_2")
	@TableField(value = "event_desc_two")
	@Length(max = 2000)
	private String eventDescTwo;
    
    /** 是否晶体植入（0.否，1.是）*/
	@TableField(value="is_iol")
	private Integer isIol;
	
	/** 晶体类型*/
	@TableField(value="iol_type_code")
	@Length(max=40) private String iolTypeCode;
	
	/** 晶体类型名称*/
	@TableField(value="iol_type_name")
	@Length(max=200) private String iolTypeName;
	
	/** 手术医生id*/
	@TableField(value="oper_doct_code")
	private Long operDoctCode;
	
	/** 手术医生姓名*/
	@TableField(value="oper_doct_name")
	@Length(max=30) private String operDoctName;

	/** 切口类别（码表：cut_type）*/
	@Comment(value="切口类别（码表：cut_type）")
	@TableField(value="cut_type")
	private Integer cutType;

	/** 切口类别名称*/
	@Comment(value="切口类别名称")
	@TableField(value="cut_type_name")
	private String cutTypeName;
	

	public java.util.Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(java.util.Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getOutpDiagName() {
		return outpDiagName;
	}
	public void setOutpDiagName(String outpDiagName) {
		this.outpDiagName = outpDiagName;
	}
	public Integer getOutpVodType() {
		return outpVodType;
	}
	public void setOutpVodType(Integer outpVodType) {
		this.outpVodType = outpVodType;
	}
	public String getOutpVod() {
		return outpVod;
	}
	public void setOutpVod(String outpVod) {
		this.outpVod = outpVod;
	}
	public String getOutpVodName() {
		return outpVodName;
	}
	public void setOutpVodName(String outpVodName) {
		this.outpVodName = outpVodName;
	}
	public Integer getOutpVosType() {
		return outpVosType;
	}
	public void setOutpVosType(Integer outpVosType) {
		this.outpVosType = outpVosType;
	}
	public String getOutpVos() {
		return outpVos;
	}
	public void setOutpVos(String outpVos) {
		this.outpVos = outpVos;
	}
	public String getOutpVosName() {
		return outpVosName;
	}
	public void setOutpVosName(String outpVosName) {
		this.outpVosName = outpVosName;
	}
	public String getOutpIopTypeOd() {
		return outpIopTypeOd;
	}
	public void setOutpIopTypeOd(String outpIopTypeOd) {
		this.outpIopTypeOd = outpIopTypeOd;
	}
	public String getOutpIopTypeOdName() {
		return outpIopTypeOdName;
	}
	public void setOutpIopTypeOdName(String outpIopTypeOdName) {
		this.outpIopTypeOdName = outpIopTypeOdName;
	}
	public String getOutpIopOd() {
		return outpIopOd;
	}
	public void setOutpIopOd(String outpIopOd) {
		this.outpIopOd = outpIopOd;
	}
	public String getOutpIopOdName() {
		return outpIopOdName;
	}
	public void setOutpIopOdName(String outpIopOdName) {
		this.outpIopOdName = outpIopOdName;
	}
	public String getOutpIopTypeOs() {
		return outpIopTypeOs;
	}
	public void setOutpIopTypeOs(String outpIopTypeOs) {
		this.outpIopTypeOs = outpIopTypeOs;
	}
	public String getOutpIopTypeOsName() {
		return outpIopTypeOsName;
	}
	public void setOutpIopTypeOsName(String outpIopTypeOsName) {
		this.outpIopTypeOsName = outpIopTypeOsName;
	}
	public String getOutpIopOs() {
		return outpIopOs;
	}
	public void setOutpIopOs(String outpIopOs) {
		this.outpIopOs = outpIopOs;
	}
	public String getOutpIopOsName() {
		return outpIopOsName;
	}
	public void setOutpIopOsName(String outpIopOsName) {
		this.outpIopOsName = outpIopOsName;
	}
	public String getIntendedOperation() {
		return intendedOperation;
	}
	public void setIntendedOperation(String intendedOperation) {
		this.intendedOperation = intendedOperation;
	}
	public String getImplementOperation() {
		return implementOperation;
	}
	public void setImplementOperation(String implementOperation) {
		this.implementOperation = implementOperation;
	}
	public Integer getCurrentVodType() {
		return currentVodType;
	}
	public void setCurrentVodType(Integer currentVodType) {
		this.currentVodType = currentVodType;
	}
	public String getCurrentVod() {
		return currentVod;
	}
	public void setCurrentVod(String currentVod) {
		this.currentVod = currentVod;
	}
	public String getCurrentVodName() {
		return currentVodName;
	}
	public void setCurrentVodName(String currentVodName) {
		this.currentVodName = currentVodName;
	}
	public Integer getCurrentVosType() {
		return currentVosType;
	}
	public void setCurrentVosType(Integer currentVosType) {
		this.currentVosType = currentVosType;
	}
	public String getCurrentVos() {
		return currentVos;
	}
	public void setCurrentVos(String currentVos) {
		this.currentVos = currentVos;
	}
	public String getCurrentVosName() {
		return currentVosName;
	}
	public void setCurrentVosName(String currentVosName) {
		this.currentVosName = currentVosName;
	}
	public String getCurrentIopTypeOd() {
		return currentIopTypeOd;
	}
	public void setCurrentIopTypeOd(String currentIopTypeOd) {
		this.currentIopTypeOd = currentIopTypeOd;
	}
	public String getCurrentIopTypeOdName() {
		return currentIopTypeOdName;
	}
	public void setCurrentIopTypeOdName(String currentIopTypeOdName) {
		this.currentIopTypeOdName = currentIopTypeOdName;
	}
	public String getCurrentIopOd() {
		return currentIopOd;
	}
	public void setCurrentIopOd(String currentIopOd) {
		this.currentIopOd = currentIopOd;
	}
	public String getCurrentIopOdName() {
		return currentIopOdName;
	}
	public void setCurrentIopOdName(String currentIopOdName) {
		this.currentIopOdName = currentIopOdName;
	}
	public String getCurrentIopTypeOs() {
		return currentIopTypeOs;
	}
	public void setCurrentIopTypeOs(String currentIopTypeOs) {
		this.currentIopTypeOs = currentIopTypeOs;
	}
	public String getCurrentIopTypeOsName() {
		return currentIopTypeOsName;
	}
	public void setCurrentIopTypeOsName(String currentIopTypeOsName) {
		this.currentIopTypeOsName = currentIopTypeOsName;
	}
	public String getCurrentIopOs() {
		return currentIopOs;
	}
	public void setCurrentIopOs(String currentIopOs) {
		this.currentIopOs = currentIopOs;
	}
	public String getCurrentIopOsName() {
		return currentIopOsName;
	}
	public void setCurrentIopOsName(String currentIopOsName) {
		this.currentIopOsName = currentIopOsName;
	}
	public Integer getUnplan() {
		return unplan;
	}
	public void setUnplan(Integer unplan) {
		this.unplan = unplan;
	}
	public String getUnplanName() {
		return unplanName;
	}
	public void setUnplanName(String unplanName) {
		this.unplanName = unplanName;
	}
	public String getCurrSituation() {
		return currSituation;
	}
	public void setCurrSituation(String currSituation) {
		this.currSituation = currSituation;
	}
	public String getPatientAppeals() {
		return patientAppeals;
	}
	public void setPatientAppeals(String patientAppeals) {
		this.patientAppeals = patientAppeals;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
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
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}

	public String getEventDescOne() {
		return eventDescOne;
	}

	public void setEventDescOne(String eventDescOne) {
		this.eventDescOne = eventDescOne;
	}

	public String getEventDescTwo() {
		return eventDescTwo;
	}

	public void setEventDescTwo(String eventDescTwo) {
		this.eventDescTwo = eventDescTwo;
	}
	public Integer getIsIol() {
		return isIol;
	}
	public void setIsIol(Integer isIol) {
		this.isIol = isIol;
	}
	public String getIolTypeCode() {
		return iolTypeCode;
	}
	public void setIolTypeCode(String iolTypeCode) {
		this.iolTypeCode = iolTypeCode;
	}
	public String getIolTypeName() {
		return iolTypeName;
	}
	public void setIolTypeName(String iolTypeName) {
		this.iolTypeName = iolTypeName;
	}
	public Long getOperDoctCode() {
		return operDoctCode;
	}
	public void setOperDoctCode(Long operDoctCode) {
		this.operDoctCode = operDoctCode;
	}
	public String getOperDoctName() {
		return operDoctName;
	}
	public void setOperDoctName(String operDoctName) {
		this.operDoctName = operDoctName;
	}

	public void setPayDiagnose(String payDiagnose) {
		this.payDiagnose = payDiagnose;
	}

	public Integer getCutType() {
		return cutType;
	}

	public void setCutType(Integer cutType) {
		this.cutType = cutType;
	}

	public String getCutTypeName() {
		return cutTypeName;
	}

	public void setCutTypeName(String cutTypeName) {
		this.cutTypeName = cutTypeName;
	}
}
