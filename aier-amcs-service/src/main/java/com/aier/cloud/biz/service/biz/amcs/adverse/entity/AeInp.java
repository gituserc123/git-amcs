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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_INP
 * 
 * @author 爱尔眼科
 * @since 2022-08-12 10:17:02
 */
@TableName("T_AE_INP")
public class AeInp extends BaseEntity<AeInp> {
	/** 事件经过描述_1*/
    @Comment(value="事件经过描述_1")
	@TableField(value="event_desc_one")
	@Length(max=2000) private String eventDescOne;

	/** 事件经过描述_2*/
    @Comment(value="事件经过描述_2")
	@TableField(value="event_desc_two")
	@Length(max=2000) private String eventDescTwo;

	/** 事件经过描述*/
    @Comment(value="事件经过描述")
	@TableField(value="event_desc")
	@Length(max=2000) private String eventDesc;

	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;

	/** 入院时间*/
    @Comment(value="入院时间")
	@TableField(value="admission_date")
	private java.util.Date admissionDate;

	/** 门诊诊断名称*/
    @Comment(value="门诊诊断名称")
	@TableField(value="outp_diag_name")
	@Length(max=500) private String outpDiagName;

	/** 住院诊断名称*/
    @Comment(value="住院诊断名称")
	@TableField(value="inp_diag_name")
	@Length(max=500) private String inpDiagName;

	@TableField(exist = false)
	private String payDiagnose;

	public String getPayDiagnose() {
		return StringUtils.join("门诊诊断："+outpDiagName,";住院诊断：",inpDiagName);
	}

	/** 右眼入院视力(OD)类型(1数值2描述）*/
    @Comment(value="右眼入院视力(OD)类型(1数值2描述）")
	@TableField(value="admission_vod_type")
	private Integer admissionVodType;

	/** 右眼入院视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="右眼入院视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="admission_vod")
	@Length(max=2) private String admissionVod;

	/** 右眼入院视力(OD)名称*/
    @Comment(value="右眼入院视力(OD)名称")
	@TableField(value="admission_vod_name")
	@Length(max=10) private String admissionVodName;

	/** 左眼入院视力(OS)类型(1数值2描述）*/
    @Comment(value="左眼入院视力(OS)类型(1数值2描述）")
	@TableField(value="admission_vos_type")
	private Integer admissionVosType;

	/** 左眼入院视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="左眼入院视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="admission_vos")
	@Length(max=2) private String admissionVos;

	/** 左眼入院视力(OS)名称*/
    @Comment(value="左眼入院视力(OS)名称")
	@TableField(value="admission_vos_name")
	@Length(max=10) private String admissionVosName;

	/** 右眼入院眼压(OD)类型编码(码表：iop_type）*/
    @Comment(value="右眼入院眼压(OD)类型编码(码表：iop_type）")
	@TableField(value="admission_iop_type_od")
	@Length(max=2) private String admissionIopTypeOd;

	/** 右眼入院眼压(OD)类型名称*/
    @Comment(value="右眼入院眼压(OD)类型名称")
	@TableField(value="admission_iop_type_od_name")
	@Length(max=20) private String admissionIopTypeOdName;

	/** 右眼入院眼压(OD)数值或指测眼压编码*/
    @Comment(value="右眼入院眼压(OD)数值或指测眼压编码")
	@TableField(value="admission_iop_od")
	@Length(max=10) private String admissionIopOd;

	/** 右眼入院眼压(OD)指测眼压名称*/
    @Comment(value="右眼入院眼压(OD)指测眼压名称")
	@TableField(value="admission_iop_od_name")
	@Length(max=10) private String admissionIopOdName;

	/** 左眼入院眼压(OS)类型编码(码表：iop_type）*/
    @Comment(value="左眼入院眼压(OS)类型编码(码表：iop_type）")
	@TableField(value="admission_iop_type_os")
	@Length(max=2) private String admissionIopTypeOs;

	/** 左眼入院眼压(OS)类型名称*/
    @Comment(value="左眼入院眼压(OS)类型名称")
	@TableField(value="admission_iop_type_os_name")
	@Length(max=20) private String admissionIopTypeOsName;

	/** 左眼入院眼压(OS)数值或指测眼压编码*/
    @Comment(value="左眼入院眼压(OS)数值或指测眼压编码")
	@TableField(value="admission_iop_os")
	@Length(max=10) private String admissionIopOs;

	/** 左眼入院眼压(OS)指测眼压名称*/
    @Comment(value="左眼入院眼压(OS)指测眼压名称")
	@TableField(value="admission_iop_os_name")
	@Length(max=10) private String admissionIopOsName;

	/** 出院时间*/
    @Comment(value="出院时间")
	@TableField(value="discharge_date")
	private java.util.Date dischargeDate;

	/** 拟施治疗(按手术、注药、打激光等格式填写) */
    @Comment(value="拟施治疗(按手术、注药、打激光等格式填写)")
	@TableField(value="intended_treatment")
	@Length(max=500) private String intendedTreatment;

	/** 实施治疗（按手术、注药、打激光等格式填写）*/
    @Comment(value="实施治疗（按手术、注药、打激光等格式填写）")
	@TableField(value="implement_treatment")
	@Length(max=500) private String implementTreatment;

	/** 治疗时间*/
    @Comment(value="治疗时间")
	@TableField(value="treat_date")
	private java.util.Date treatDate;

	/** 切口类别（码表：cut_type）*/
    @Comment(value="切口类别（码表：cut_type）")
	@TableField(value="cut_type")
	private Integer cutType;

	/** 切口类别名称*/
    @Comment(value="切口类别名称")
	@TableField(value="cut_type_name")
	private String cutTypeName;

	/** 右眼出院视力(OD)类型(1数值2描述）*/
    @Comment(value="右眼出院视力(OD)类型(1数值2描述）")
	@TableField(value="discharge_vod_type")
	private Integer dischargeVodType;

	/** 右眼出院视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="右眼出院视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="discharge_vod")
	@Length(max=2) private String dischargeVod;

	/** 右眼出院视力(OD)名称*/
    @Comment(value="右眼出院视力(OD)名称")
	@TableField(value="discharge_vod_name")
	@Length(max=10) private String dischargeVodName;

	/** 左眼出院视力(OS)类型(1数值2描述）*/
    @Comment(value="左眼出院视力(OS)类型(1数值2描述）")
	@TableField(value="discharge_vos_type")
	private Integer dischargeVosType;

	/** 左眼出院视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
    @Comment(value="左眼出院视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value="discharge_vos")
	@Length(max=2) private String dischargeVos;

	/** 左眼出院视力(OS)名称*/
    @Comment(value="左眼出院视力(OS)名称")
	@TableField(value="discharge_vos_name")
	@Length(max=10) private String dischargeVosName;

	/** 右眼出院眼压(OD)类型编码(码表：iop_type）*/
    @Comment(value="右眼出院眼压(OD)类型编码(码表：iop_type）")
	@TableField(value="discharge_iop_type_od")
	@Length(max=2) private String dischargeIopTypeOd;

	/** 右眼出院眼压(OD)类型名称*/
    @Comment(value="右眼出院眼压(OD)类型名称")
	@TableField(value="discharge_iop_type_od_name")
	@Length(max=20) private String dischargeIopTypeOdName;

	/** 右眼出院眼压(OD)数值或指测眼压编码*/
    @Comment(value="右眼出院眼压(OD)数值或指测眼压编码")
	@TableField(value="discharge_iop_od")
	@Length(max=10) private String dischargeIopOd;

	/** 右眼出院眼压(OD)指测眼压名称*/
    @Comment(value="右眼出院眼压(OD)指测眼压名称")
	@TableField(value="discharge_iop_od_name")
	@Length(max=10) private String dischargeIopOdName;

	/** 左眼出院眼压(OS)类型编码(码表：iop_type）*/
    @Comment(value="左眼出院眼压(OS)类型编码(码表：iop_type）")
	@TableField(value="discharge_iop_type_os")
	@Length(max=2) private String dischargeIopTypeOs;

	/** 左眼出院眼压(OS)类型名称*/
    @Comment(value="左眼出院眼压(OS)类型名称")
	@TableField(value="discharge_iop_type_os_name")
	@Length(max=20) private String dischargeIopTypeOsName;

	/** 左眼出院眼压(OS)数值或指测眼压编码*/
    @Comment(value="左眼出院眼压(OS)数值或指测眼压编码")
	@TableField(value="discharge_iop_os")
	@Length(max=10) private String dischargeIopOs;

	/** 左眼出院眼压(OS)指测眼压名称*/
    @Comment(value="左眼出院眼压(OS)指测眼压名称")
	@TableField(value="discharge_iop_os_name")
	@Length(max=10) private String dischargeIopOsName;

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
	@Length(max=20) private String unplanName;

	/** 目前情况*/
    @Comment(value="目前情况")
	@TableField(value="curr_situation")
	@Length(max=600) private String currSituation;

	/** 患者及家属诉求*/
    @Comment(value="患者及家属诉求")
	@TableField(value="patient_appeals")
	@Length(max=600) private String patientAppeals;

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


	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public java.util.Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(java.util.Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getOutpDiagName() {
		return outpDiagName;
	}
	public void setOutpDiagName(String outpDiagName) {
		this.outpDiagName = outpDiagName;
	}
	public String getInpDiagName() {
		return inpDiagName;
	}
	public void setInpDiagName(String inpDiagName) {
		this.inpDiagName = inpDiagName;
	}
	public Integer getAdmissionVodType() {
		return admissionVodType;
	}
	public void setAdmissionVodType(Integer admissionVodType) {
		this.admissionVodType = admissionVodType;
	}
	public String getAdmissionVod() {
		return admissionVod;
	}
	public void setAdmissionVod(String admissionVod) {
		this.admissionVod = admissionVod;
	}
	public String getAdmissionVodName() {
		return admissionVodName;
	}
	public void setAdmissionVodName(String admissionVodName) {
		this.admissionVodName = admissionVodName;
	}
	public Integer getAdmissionVosType() {
		return admissionVosType;
	}
	public void setAdmissionVosType(Integer admissionVosType) {
		this.admissionVosType = admissionVosType;
	}
	public String getAdmissionVos() {
		return admissionVos;
	}
	public void setAdmissionVos(String admissionVos) {
		this.admissionVos = admissionVos;
	}
	public String getAdmissionVosName() {
		return admissionVosName;
	}
	public void setAdmissionVosName(String admissionVosName) {
		this.admissionVosName = admissionVosName;
	}
	public String getAdmissionIopTypeOd() {
		return admissionIopTypeOd;
	}
	public void setAdmissionIopTypeOd(String admissionIopTypeOd) {
		this.admissionIopTypeOd = admissionIopTypeOd;
	}
	public String getAdmissionIopTypeOdName() {
		return admissionIopTypeOdName;
	}
	public void setAdmissionIopTypeOdName(String admissionIopTypeOdName) {
		this.admissionIopTypeOdName = admissionIopTypeOdName;
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

	public String getAdmissionIopOd() {
		return admissionIopOd;
	}
	public void setAdmissionIopOd(String admissionIopOd) {
		this.admissionIopOd = admissionIopOd;
	}
	public String getAdmissionIopOdName() {
		return admissionIopOdName;
	}
	public void setAdmissionIopOdName(String admissionIopOdName) {
		this.admissionIopOdName = admissionIopOdName;
	}
	public String getAdmissionIopTypeOs() {
		return admissionIopTypeOs;
	}
	public void setAdmissionIopTypeOs(String admissionIopTypeOs) {
		this.admissionIopTypeOs = admissionIopTypeOs;
	}
	public String getAdmissionIopTypeOsName() {
		return admissionIopTypeOsName;
	}
	public void setAdmissionIopTypeOsName(String admissionIopTypeOsName) {
		this.admissionIopTypeOsName = admissionIopTypeOsName;
	}
	public String getAdmissionIopOs() {
		return admissionIopOs;
	}
	public void setAdmissionIopOs(String admissionIopOs) {
		this.admissionIopOs = admissionIopOs;
	}
	public String getAdmissionIopOsName() {
		return admissionIopOsName;
	}
	public void setAdmissionIopOsName(String admissionIopOsName) {
		this.admissionIopOsName = admissionIopOsName;
	}
	public java.util.Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(java.util.Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getIntendedTreatment() {
		return intendedTreatment;
	}
	public void setIntendedTreatment(String intendedTreatment) {
		this.intendedTreatment = intendedTreatment;
	}
	public String getImplementTreatment() {
		return implementTreatment;
	}
	public void setImplementTreatment(String implementTreatment) {
		this.implementTreatment = implementTreatment;
	}
	public java.util.Date getTreatDate() {
		return treatDate;
	}
	public void setTreatDate(java.util.Date treatDate) {
		this.treatDate = treatDate;
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
	public Integer getDischargeVodType() {
		return dischargeVodType;
	}
	public void setDischargeVodType(Integer dischargeVodType) {
		this.dischargeVodType = dischargeVodType;
	}
	public String getDischargeVod() {
		return dischargeVod;
	}
	public void setDischargeVod(String dischargeVod) {
		this.dischargeVod = dischargeVod;
	}
	public String getDischargeVodName() {
		return dischargeVodName;
	}
	public void setDischargeVodName(String dischargeVodName) {
		this.dischargeVodName = dischargeVodName;
	}
	public Integer getDischargeVosType() {
		return dischargeVosType;
	}
	public void setDischargeVosType(Integer dischargeVosType) {
		this.dischargeVosType = dischargeVosType;
	}
	public String getDischargeVos() {
		return dischargeVos;
	}
	public void setDischargeVos(String dischargeVos) {
		this.dischargeVos = dischargeVos;
	}
	public String getDischargeVosName() {
		return dischargeVosName;
	}
	public void setDischargeVosName(String dischargeVosName) {
		this.dischargeVosName = dischargeVosName;
	}
	public String getDischargeIopTypeOd() {
		return dischargeIopTypeOd;
	}
	public void setDischargeIopTypeOd(String dischargeIopTypeOd) {
		this.dischargeIopTypeOd = dischargeIopTypeOd;
	}
	public String getDischargeIopTypeOdName() {
		return dischargeIopTypeOdName;
	}
	public void setDischargeIopTypeOdName(String dischargeIopTypeOdName) {
		this.dischargeIopTypeOdName = dischargeIopTypeOdName;
	}
	public String getDischargeIopOd() {
		return dischargeIopOd;
	}
	public void setDischargeIopOd(String dischargeIopOd) {
		this.dischargeIopOd = dischargeIopOd;
	}
	public String getDischargeIopOdName() {
		return dischargeIopOdName;
	}
	public void setDischargeIopOdName(String dischargeIopOdName) {
		this.dischargeIopOdName = dischargeIopOdName;
	}
	public String getDischargeIopTypeOs() {
		return dischargeIopTypeOs;
	}
	public void setDischargeIopTypeOs(String dischargeIopTypeOs) {
		this.dischargeIopTypeOs = dischargeIopTypeOs;
	}
	public String getDischargeIopTypeOsName() {
		return dischargeIopTypeOsName;
	}
	public void setDischargeIopTypeOsName(String dischargeIopTypeOsName) {
		this.dischargeIopTypeOsName = dischargeIopTypeOsName;
	}
	public String getDischargeIopOs() {
		return dischargeIopOs;
	}
	public void setDischargeIopOs(String dischargeIopOs) {
		this.dischargeIopOs = dischargeIopOs;
	}
	public String getDischargeIopOsName() {
		return dischargeIopOsName;
	}
	public void setDischargeIopOsName(String dischargeIopOsName) {
		this.dischargeIopOsName = dischargeIopOsName;
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
	
	
}
