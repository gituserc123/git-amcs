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
package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_UNPL_REOPERATION
 * 非计划再手术上报表单
 * @author 爱尔眼科
 * @since 2024-11-14 11:15:38
 */
@Data
@TableName("T_AE_UNPL_REOPERATION")
public class AeUnplReoperation extends BaseEntity<AeUnplReoperation> {
	/** 目前情况*/
	@TableField(value="curr_situation")
	@Length(max=2000) private String currSituation;

	/** 再次手术时间*/
	@TableField(value="reop_surg_date")
	private java.util.Date reopSurgDate;

	/** 再次手术名称*/
	@TableField(value="reop_surg_name")
	@Length(max=50) private String reopSurgName;

	/** 再次手术医生ID*/
	@TableField(value="reop_surg_doct_id")
	private Long reopSurgDoctId;

	/** 再次手术医生名称*/
	@TableField(value="reop_surg_doct_name")
	@Length(max=30) private String reopSurgDoctName;

	/** 患者是否知情*/
	@TableField(value="is_consent")
	private Integer isConsent;

	/** 患者是否理解*/
	@TableField(value="is_underst")
	private Integer isUnderst;

	/** 是否为医源性*/
	@TableField(value="is_iatrogenic")
	private Integer isIatrogenic;

	/** 是否达到再次手术目的*/
	@TableField(value="is_goal_achieved")
	private Integer isGoalAchieved;

	/** 术后右眼入院视力(OD)类型(1数值2描述）*/
	@TableField(value="post_vod_type")
	private Integer postVodType;

	/** 术后右眼入院视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
	@TableField(value="post_vod")
	@Length(max=2) private String postVod;

	/** 术后右眼入院视力(OD)名称*/
	@TableField(value="post_vod_name")
	@Length(max=10) private String postVodName;

	/** 术后左眼入院视力(OS)类型(1数值2描述）*/
	@TableField(value="post_vos_type")
	private Integer postVosType;

	/** 术后左眼入院视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
	@TableField(value="post_vos")
	@Length(max=2) private String postVos;

	/** 术后左眼入院视力(OS)名称*/
	@TableField(value="post_vos_name")
	@Length(max=10) private String postVosName;

	/** 术后右眼入院眼压(OD)类型编码(码表：iop_type）*/
	@TableField(value="post_iop_type_od")
	@Length(max=2) private String postIopTypeOd;

	/** 术后右眼入院眼压(OD)类型名称*/
	@TableField(value="post_iop_type_od_name")
	@Length(max=20) private String postIopTypeOdName;

	/** 术后右眼入院眼压(OD)数值或指测眼压编码*/
	@TableField(value="post_iop_od")
	@Length(max=10) private String postIopOd;

	/** 术后右眼入院眼压(OD)指测眼压名称*/
	@TableField(value="post_iop_od_name")
	@Length(max=10) private String postIopOdName;

	/** 术后左眼入院眼压(OS)类型编码(码表：iop_type）*/
	@TableField(value="post_iop_type_os")
	@Length(max=2) private String postIopTypeOs;

	/** 术后左眼入院眼压(OS)类型名称*/
	@TableField(value="post_iop_type_os_name")
	@Length(max=20) private String postIopTypeOsName;

	/** 术后左眼入院眼压(OS)数值或指测眼压编码*/
	@TableField(value="post_iop_os")
	@Length(max=10) private String postIopOs;

	/** 术后左眼入院眼压(OS)指测眼压名称*/
	@TableField(value="post_iop_os_name")
	@Length(max=10) private String postIopOsName;

	/** 患者及家属诉求*/
	@TableField(value="patient_appeals")
	@Length(max=600) private String patientAppeals;

	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;

	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;

	/** 不良事件基础表ID*/
	@TableField(value="basic_id")
	private Long basicId;

	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 入院时间*/
	@TableField(value="admission_date")
	private java.util.Date admissionDate;

	/** 非计划情况*/
	@TableField(value="unpl_situation")
	@Length(max=200) private String unplSituation;

	/** 目前诊断*/
	@TableField(value="curr_diagnosis")
	@Length(max=200) private String currDiagnosis;

	/** 右眼入院视力(OD)类型(1数值2描述）*/
	@TableField(value="admission_vod_type")
	private Integer admissionVodType;

	/** 右眼入院视力(OD)编码（码表：far_eyesight或eyesight_desc）*/
	@TableField(value="admission_vod")
	@Length(max=2) private String admissionVod;

	/** 右眼入院视力(OD)名称*/
	@TableField(value="admission_vod_name")
	@Length(max=10) private String admissionVodName;

	/** 左眼入院视力(OS)类型(1数值2描述）*/
	@TableField(value="admission_vos_type")
	private Integer admissionVosType;

	/** 左眼入院视力(OS)编码（码表：far_eyesight或eyesight_desc）*/
	@TableField(value="admission_vos")
	@Length(max=2) private String admissionVos;

	/** 左眼入院视力(OS)名称*/
	@TableField(value="admission_vos_name")
	@Length(max=10) private String admissionVosName;

	/** 右眼入院眼压(OD)类型编码(码表：iop_type）*/
	@TableField(value="admission_iop_type_od")
	@Length(max=2) private String admissionIopTypeOd;

	/** 右眼入院眼压(OD)类型名称*/
	@TableField(value="admission_iop_type_od_name")
	@Length(max=20) private String admissionIopTypeOdName;

	/** 右眼入院眼压(OD)数值或指测眼压编码*/
	@TableField(value="admission_iop_od")
	@Length(max=10) private String admissionIopOd;

	/** 右眼入院眼压(OD)指测眼压名称*/
	@TableField(value="admission_iop_od_name")
	@Length(max=10) private String admissionIopOdName;

	/** 左眼入院眼压(OS)类型编码(码表：iop_type）*/
	@TableField(value="admission_iop_type_os")
	@Length(max=2) private String admissionIopTypeOs;

	/** 左眼入院眼压(OS)类型名称*/
	@TableField(value="admission_iop_type_os_name")
	@Length(max=20) private String admissionIopTypeOsName;

	/** 左眼入院眼压(OS)数值或指测眼压编码*/
	@TableField(value="admission_iop_os")
	@Length(max=10) private String admissionIopOs;

	/** 左眼入院眼压(OS)指测眼压名称*/
	@TableField(value="admission_iop_os_name")
	@Length(max=10) private String admissionIopOsName;

	/** 病情概述*/
	@TableField(value="cond_summary")
	@Length(max=2000) private String condSummary;

	/** 首次手术时间*/
	@TableField(value="first_surg_date")
	private java.util.Date firstSurgDate;

	/** 首次手术名称*/
	@TableField(value="first_surg_name")
	@Length(max=50) private String firstSurgName;

	/** 首次手术医生ID*/
	@TableField(value="first_surg_doct_id")
	private Long firstSurgDoctId;

	/** 首次手术医生名称*/
	@TableField(value="first_surg_doct_name")
	@Length(max=30) private String firstSurgDoctName;

	/** 首次手术情况*/
	@TableField(value="first_surg_sit")
	@Length(max=2000) private String firstSurgSit;

	/** 麻醉方式*/
	@TableField(value="anes_method")
	@Length(max=10) private String anesMethod;

	/** 是否晶体植入（0.否，1.是）*/
	@TableField(value="is_iol")
	private Integer isIol;

	/** 晶体类型*/
	@TableField(value="iol_type_code")
	@Length(max=40) private String iolTypeCode;

	/** 晶体类型名称*/
	@TableField(value="iol_type_name")
	@Length(max=200) private String iolTypeName;

	/** 再次手术原因和目的*/
	@TableField(value="reop_reason")
	@Length(max=2000) private String reopReason;

	/** 拟再次手术时间*/
	@TableField(value="plan_reop_date")
	private java.util.Date planReopDate;

	/** 拟再次手术名称*/
	@TableField(value="plan_reop_name")
	@Length(max=200) private String planReopName;

	/** 拟再次手术医生ID*/
	@TableField(value="plan_reop_doct_id")
	private Long planReopDoctId;

	/** 拟再次手术医生名称*/
	@TableField(value="plan_reop_doct_name")
	@Length(max=30) private String planReopDoctName;

	/** 手术中风险防范措施*/
	@TableField(value="risk_prev_measure")
	@Length(max=200) private String riskPrevMeasure;

	/** 非计划情况（码表：unplan）*/
	@Comment(value="非计划情况（码表：unplan）")
	@TableField(value="unplan")
	private Integer unplan;


}