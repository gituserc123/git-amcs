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

import org.hibernate.validator.constraints.Length;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

import java.util.Date;

/**
 * T_AE_OCCUPATION_EXPOSURE
 * 血/体液职业暴露事件上报表表单
 * @author 爱尔眼科
 * @since 2022-09-20 16:45:38
 */
@TableName("T_AE_OCCUPATION_EXPOSURE")
public class AeOccupationExposure extends BaseEntity<AeOccupationExposure> {
	/** 医院ID*/
    @Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 创建时间*/
    @Comment(value="创建时间")
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 创建者ID*/
    @Comment(value="创建者ID")
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 暴露后的处理办法*/
    @Comment(value="暴露后的处理办法")
	@TableField(value="post_expose_dispose")
	private String postExposeDispose;

	/** 暴露后的处理办法(其他)*/
    @Comment(value="暴露后的处理办法(其他)")
	@TableField(value="post_expose_dispose_remark")
	@Length(max=100) private String postExposeDisposeRemark;
	
	/** 发生暴露的工作人员血友病*/
    @Comment(value="发生暴露的工作人员血友病")
	@TableField(value="w_haemophilia")
	private Integer wHaemophilia;
	
	/** 发生暴露的工作人员静脉吸毒*/
    @Comment(value="发生暴露的工作人员静脉吸毒")
	@TableField(value="w_injection_drug")
	private Integer wInjectionDrug;
	
	/** 发生暴露的工作人员梅毒快速血浆反应素*/
    @Comment(value="发生暴露的工作人员梅毒快速血浆反应素")
	@TableField(value="w_syphilis_reagin")
	private Integer wSyphilisReagin;
	
	/** 发生暴露的工作人员梅毒特异性抗体*/
    @Comment(value="发生暴露的工作人员梅毒特异性抗体")
	@TableField(value="w_syphilis_ab")
	private Integer wSyphilisAb;
	
	/** 发生暴露的工作人员HIV艾滋病*/
    @Comment(value="发生暴露的工作人员HIV艾滋病")
	@TableField(value="w_hiv")
	private Integer wHiv;
	
	/** 发生暴露的工作人员HCV丙肝*/
    @Comment(value="发生暴露的工作人员HCV丙肝")
	@TableField(value="w_hcv")
	private Integer wHcv;
	
	/** 发生暴露的工作人员HBsAb乙肝表面抗体*/
    @Comment(value="发生暴露的工作人员HBsAb乙肝表面抗体")
	@TableField(value="w_hbsab")
	private Integer wHbsab;
	
	/** 发生暴露的工作人员HBsAg乙肝表面抗原*/
    @Comment(value="发生暴露的工作人员HBsAg乙肝表面抗原")
	@TableField(value="w_hbsag")
	private Integer wHbsag;
	
	/** 发生暴露的工作人员既往乙肝抗体情况*/
    @Comment(value="发生暴露的工作人员既往乙肝抗体情况")
	@TableField(value="w_hbsab_sign")
	private Integer wHbsabSign;
	
	/** 发生暴露的工作人员是否接种乙肝疫苗*/
    @Comment(value="发生暴露的工作人员是否接种乙肝疫苗")
	@TableField(value="w_hbv_sign")
	private Integer wHbvSign;
	
	/** 发生暴露的工作人员身份*/
    @Comment(value="发生暴露的工作人员身份")
	@TableField(value="w_identity")
	private Integer wIdentity;
	
	/** 发生暴露的工作人员工别*/
    @Comment(value="发生暴露的工作人员工别")
	@TableField(value="w_job_type")
	private Integer wJobType;
	
	/** 发生暴露的工作人员本专业工作年限*/
    @Comment(value="发生暴露的工作人员本专业工作年限")
	@TableField(value="w_job_duration")
	private Integer wJobDuration;
	
	/** 发生暴露的工作人员在职情况*/
    @Comment(value="发生暴露的工作人员在职情况")
	@TableField(value="w_on_job")
	@Length(max=20) private String wOnJob;
	
	/** 发生暴露的工作人员类别*/
    @Comment(value="发生暴露的工作人员类别")
	@TableField(value="w_type")
	@Length(max=20) private String wType;
	
	/** 发生暴露的工作人员学历(码表: education_type)*/
    @Comment(value="发生暴露的工作人员学历(码表: education_type)")
	@TableField(value="w_edu")
	@Length(max=20) private String wEdu;
	
	/** 发生暴露的工作人员职称获得时间*/
    @Comment(value="发生暴露的工作人员职称获得时间")
	@TableField(value="w_tech_title_time")
	private java.util.Date wTechTitleTime;
	
	/** 发生暴露的工作人员专业技术职称(码表:technical_post_type)*/
    @Comment(value="发生暴露的工作人员专业技术职称(码表:technical_post_type)")
	@TableField(value="w_tech_title")
	@Length(max=20) private String wTechTitle;
	
	/** 发生暴露的工作人员年龄*/
    @Comment(value="发生暴露的工作人员年龄")
	@TableField(value="w_age")
	private Integer wAge;
	
	/** 发生暴露的工作人员性别(码表：sex)*/
    @Comment(value="发生暴露的工作人员性别(码表：sex)")
	@TableField(value="w_sex")
	@Length(max=4) private String wSex;
	
	/** 发生暴露的工作人员姓名*/
    @Comment(value="发生暴露的工作人员姓名")
	@TableField(value="w_name")
	@Length(max=50) private String wName;
	
	/** 患者相关性行为*/
    @Comment(value="患者相关性行为")
	@TableField(value="p_rela_behavior")
	private Integer pRelaBehavior;
	
	/** 患者血友病*/
    @Comment(value="患者血友病")
	@TableField(value="p_haemophilia")
	private Integer pHaemophilia;
	
	/** 患者静脉吸毒*/
    @Comment(value="患者静脉吸毒")
	@TableField(value="p_injection_drug")
	private Integer pInjectionDrug;
	
	/** 患者梅毒快速血浆反应素*/
    @Comment(value="患者梅毒快速血浆反应素")
	@TableField(value="p_syphilis_reagin")
	private Integer pSyphilisReagin;
	
	/** 患者梅毒特异性抗体*/
    @Comment(value="患者梅毒特异性抗体")
	@TableField(value="p_syphilis_ab")
	private Integer pSyphilisAb;
	
	/** 患者HIV艾滋病*/
    @Comment(value="患者HIV艾滋病")
	@TableField(value="p_hiv")
	private Integer pHiv;
	
	/** 患者HCV丙肝*/
    @Comment(value="患者HCV丙肝")
	@TableField(value="p_hcv")
	private Integer pHcv;
	
	/** 患者HBsAb乙肝表面抗体*/
    @Comment(value="患者HBsAb乙肝表面抗体")
	@TableField(value="p_hbsab")
	private Integer pHbsab;
	
	/** 患者HBsAg乙肝表面抗原*/
    @Comment(value="患者HBsAg乙肝表面抗原")
	@TableField(value="p_hbsag")
	private Integer pHbsag;
	
	/** 血标本送检日期*/
    @Comment(value="血标本送检日期")
	@TableField(value="blood_send_date")
	private java.util.Date bloodSendDate;
	
	/** 患者所在科室*/
    @Comment(value="患者所在科室")
	@TableField(value="patient_department")
	@Length(max=50) private String patientDepartment;
	
	/** 事件发生后受影响的对象*/
    @Comment(value="事件发生后受影响的对象")
	@TableField(value="post_effect_obj")
	@Length(max=100) private String postEffectObj;

	/** 事件发生后受影响的对象(其他)*/
    @Comment(value="事件发生后受影响的对象(其他)")
	@TableField(value="post_effect_obj_remark")
	@Length(max=100) private String postEffectObjRemark;
	
	/** 锐器伤后是否进行了定期追踪和检测*/
    @Comment(value="锐器伤后是否进行了定期追踪和检测")
	@TableField(value="so_trace_check")
	private Integer soTraceCheck;
	
	/** 锐器是否被污染*/
    @Comment(value="锐器是否被污染")
	@TableField(value="so_pollute_flag")
	private Integer soPolluteFlag;
	
	/** 保护措施,(其他,输入的文本内容)*/
    @Comment(value="保护措施,(其他,输入的文本内容)")
	@TableField(value="protect_measure_remark")
	@Length(max=100) private String protectMeasureRemark;
	
	/** 保护措施*/
    @Comment(value="保护措施")
	@TableField(value="protect_measure")
	@Length(max=4) private String protectMeasure;
	
	/** 发生方式,(其他,输入的文本内容)*/
    @Comment(value="发生方式,(其他,输入的文本内容)")
	@TableField(value="occur_form_remark")
	@Length(max=100) private String occurFormRemark;
	
	/** 发生方式*/
    @Comment(value="发生方式")
	@TableField(value="occur_form")
	@Length(max=4) private String occurForm;
	
	/** 粘膜接触关联操作,(其他,输入的文本内容)*/
    @Comment(value="粘膜接触关联操作,(其他,输入的文本内容)")
	@TableField(value="mucosa_touch_opertion_remark")
	@Length(max=100) private String mucosaTouchOpertionRemark;
	
	/** 粘膜接触关联操作*/
    @Comment(value="粘膜接触关联操作")
	@TableField(value="mucosa_touch_opertion")
	@Length(max=4) private String mucosaTouchOpertion;
	
	/** 关联操作,(其他,输入的文本内容)*/
    @Comment(value="关联操作,(其他,输入的文本内容)")
	@TableField(value="rela_operation_remark")
	@Length(max=100) private String relaOperationRemark;
	
	/** 关联操作*/
    @Comment(value="关联操作")
	@TableField(value="rela_operation")
	@Length(max=4) private String relaOperation;
	
	/** 粘膜接触物质,(其他,输入的文本内容)*/
    @Comment(value="粘膜接触物质,(其他,输入的文本内容)")
	@TableField(value="mucosa_touch_material_remark")
	@Length(max=100) private String mucosaTouchMaterialRemark;
	
	/** 粘膜接触物质*/
    @Comment(value="粘膜接触物质")
	@TableField(value="mucosa_touch_material")
	@Length(max=4) private String mucosaTouchMaterial;
	
	/** 锐器名称,(其他,输入的文本内容)*/
    @Comment(value="锐器名称,(其他,输入的文本内容)")
	@TableField(value="sharp_obj_name_remark")
	@Length(max=100) private String sharpObjNameRemark;
	
	/** 锐器名称*/
    @Comment(value="锐器名称")
	@TableField(value="sharp_obj_name")
	@Length(max=4) private String sharpObjName;
	
	/** 粘膜接触部位,(其他,输入的文本内容)*/
    @Comment(value="粘膜接触部位,(其他,输入的文本内容)")
	@TableField(value="mucosa_touch_part_remark")
	@Length(max=100) private String mucosaTouchPartRemark;
	
	/** 粘膜接触部位*/
    @Comment(value="粘膜接触部位")
	@TableField(value="mucosa_touch_part")
	@Length(max=4) private String mucosaTouchPart;
	
	/** 锐器类型*/
    @Comment(value="锐器类型")
	@TableField(value="sharp_obj_type")
	private Integer sharpObjType;
	
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;

	/** 患者采集时间*/
    @Comment(value="患者采集时间")
	@TableField(value="p_collect_time")
	private java.util.Date pCollectTime;

	/** 工作人员采集时间*/
    @Comment(value="工作人员采集时间")
	@TableField(value="w_collect_time")
	private java.util.Date wCollectTime;

	/** 详细情况*/
	@Comment(value="详细情况")
	@TableField(value="event_desc")
	@Length(max=2000) private String eventDesc;
	

	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
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
	public String getPostExposeDispose() {
		return postExposeDispose;
	}
	public void setPostExposeDispose(String postExposeDispose) {
		this.postExposeDispose = postExposeDispose;
	}
	public Integer getWHaemophilia() {
		return wHaemophilia;
	}
	public void setWHaemophilia(Integer wHaemophilia) {
		this.wHaemophilia = wHaemophilia;
	}
	public Integer getWInjectionDrug() {
		return wInjectionDrug;
	}
	public void setWInjectionDrug(Integer wInjectionDrug) {
		this.wInjectionDrug = wInjectionDrug;
	}
	public Integer getWSyphilisReagin() {
		return wSyphilisReagin;
	}
	public void setWSyphilisReagin(Integer wSyphilisReagin) {
		this.wSyphilisReagin = wSyphilisReagin;
	}
	public Integer getWSyphilisAb() {
		return wSyphilisAb;
	}
	public void setWSyphilisAb(Integer wSyphilisAb) {
		this.wSyphilisAb = wSyphilisAb;
	}
	public Integer getWHiv() {
		return wHiv;
	}
	public void setWHiv(Integer wHiv) {
		this.wHiv = wHiv;
	}
	public Integer getWHcv() {
		return wHcv;
	}
	public void setWHcv(Integer wHcv) {
		this.wHcv = wHcv;
	}
	public Integer getWHbsab() {
		return wHbsab;
	}
	public void setWHbsab(Integer wHbsab) {
		this.wHbsab = wHbsab;
	}
	public Integer getWHbsag() {
		return wHbsag;
	}
	public void setWHbsag(Integer wHbsag) {
		this.wHbsag = wHbsag;
	}
	public Integer getWHbsabSign() {
		return wHbsabSign;
	}
	public void setWHbsabSign(Integer wHbsabSign) {
		this.wHbsabSign = wHbsabSign;
	}
	public Integer getWHbvSign() {
		return wHbvSign;
	}
	public void setWHbvSign(Integer wHbvSign) {
		this.wHbvSign = wHbvSign;
	}
	public Integer getWIdentity() {
		return wIdentity;
	}
	public void setWIdentity(Integer wIdentity) {
		this.wIdentity = wIdentity;
	}
	public Integer getWJobType() {
		return wJobType;
	}
	public void setWJobType(Integer wJobType) {
		this.wJobType = wJobType;
	}
	public Integer getWJobDuration() {
		return wJobDuration;
	}
	public void setWJobDuration(Integer wJobDuration) {
		this.wJobDuration = wJobDuration;
	}
	public String getWOnJob() {
		return wOnJob;
	}
	public void setWOnJob(String wOnJob) {
		this.wOnJob = wOnJob;
	}
	public String getWType() {
		return wType;
	}
	public void setWType(String wType) {
		this.wType = wType;
	}
	public String getWEdu() {
		return wEdu;
	}
	public void setWEdu(String wEdu) {
		this.wEdu = wEdu;
	}
	public java.util.Date getWTechTitleTime() {
		return wTechTitleTime;
	}
	public void setWTechTitleTime(java.util.Date wTechTitleTime) {
		this.wTechTitleTime = wTechTitleTime;
	}
	public String getWTechTitle() {
		return wTechTitle;
	}
	public void setWTechTitle(String wTechTitle) {
		this.wTechTitle = wTechTitle;
	}
	public Integer getWAge() {
		return wAge;
	}
	public void setWAge(Integer wAge) {
		this.wAge = wAge;
	}
	public String getWSex() {
		return wSex;
	}
	public void setWSex(String wSex) {
		this.wSex = wSex;
	}
	public String getWName() {
		return wName;
	}
	public void setWName(String wName) {
		this.wName = wName;
	}
	public Integer getPRelaBehavior() {
		return pRelaBehavior;
	}
	public void setPRelaBehavior(Integer pRelaBehavior) {
		this.pRelaBehavior = pRelaBehavior;
	}
	public Integer getPHaemophilia() {
		return pHaemophilia;
	}
	public void setPHaemophilia(Integer pHaemophilia) {
		this.pHaemophilia = pHaemophilia;
	}
	public Integer getPInjectionDrug() {
		return pInjectionDrug;
	}
	public void setPInjectionDrug(Integer pInjectionDrug) {
		this.pInjectionDrug = pInjectionDrug;
	}
	public Integer getPSyphilisReagin() {
		return pSyphilisReagin;
	}
	public void setPSyphilisReagin(Integer pSyphilisReagin) {
		this.pSyphilisReagin = pSyphilisReagin;
	}
	public Integer getPSyphilisAb() {
		return pSyphilisAb;
	}
	public void setPSyphilisAb(Integer pSyphilisAb) {
		this.pSyphilisAb = pSyphilisAb;
	}
	public Integer getPHiv() {
		return pHiv;
	}
	public void setPHiv(Integer pHiv) {
		this.pHiv = pHiv;
	}
	public Integer getPHcv() {
		return pHcv;
	}
	public void setPHcv(Integer pHcv) {
		this.pHcv = pHcv;
	}
	public Integer getPHbsab() {
		return pHbsab;
	}
	public void setPHbsab(Integer pHbsab) {
		this.pHbsab = pHbsab;
	}
	public Integer getPHbsag() {
		return pHbsag;
	}
	public void setPHbsag(Integer pHbsag) {
		this.pHbsag = pHbsag;
	}
	public java.util.Date getBloodSendDate() {
		return bloodSendDate;
	}
	public void setBloodSendDate(java.util.Date bloodSendDate) {
		this.bloodSendDate = bloodSendDate;
	}
	public String getPatientDepartment() {
		return patientDepartment;
	}
	public void setPatientDepartment(String patientDepartment) {
		this.patientDepartment = patientDepartment;
	}
	public String getPostEffectObj() {
		return postEffectObj;
	}
	public void setPostEffectObj(String postEffectObj) {
		this.postEffectObj = postEffectObj;
	}
	public Integer getSoTraceCheck() {
		return soTraceCheck;
	}
	public void setSoTraceCheck(Integer soTraceCheck) {
		this.soTraceCheck = soTraceCheck;
	}
	public Integer getSoPolluteFlag() {
		return soPolluteFlag;
	}
	public void setSoPolluteFlag(Integer soPolluteFlag) {
		this.soPolluteFlag = soPolluteFlag;
	}
	public String getProtectMeasureRemark() {
		return protectMeasureRemark;
	}
	public void setProtectMeasureRemark(String protectMeasureRemark) {
		this.protectMeasureRemark = protectMeasureRemark;
	}
	public String getProtectMeasure() {
		return protectMeasure;
	}
	public void setProtectMeasure(String protectMeasure) {
		this.protectMeasure = protectMeasure;
	}
	public String getOccurFormRemark() {
		return occurFormRemark;
	}
	public void setOccurFormRemark(String occurFormRemark) {
		this.occurFormRemark = occurFormRemark;
	}
	public String getOccurForm() {
		return occurForm;
	}
	public void setOccurForm(String occurForm) {
		this.occurForm = occurForm;
	}
	public String getMucosaTouchOpertionRemark() {
		return mucosaTouchOpertionRemark;
	}
	public void setMucosaTouchOpertionRemark(String mucosaTouchOpertionRemark) {
		this.mucosaTouchOpertionRemark = mucosaTouchOpertionRemark;
	}
	public String getMucosaTouchOpertion() {
		return mucosaTouchOpertion;
	}
	public void setMucosaTouchOpertion(String mucosaTouchOpertion) {
		this.mucosaTouchOpertion = mucosaTouchOpertion;
	}
	public String getRelaOperationRemark() {
		return relaOperationRemark;
	}
	public void setRelaOperationRemark(String relaOperationRemark) {
		this.relaOperationRemark = relaOperationRemark;
	}
	public String getRelaOperation() {
		return relaOperation;
	}
	public void setRelaOperation(String relaOperation) {
		this.relaOperation = relaOperation;
	}
	public String getMucosaTouchMaterialRemark() {
		return mucosaTouchMaterialRemark;
	}
	public void setMucosaTouchMaterialRemark(String mucosaTouchMaterialRemark) {
		this.mucosaTouchMaterialRemark = mucosaTouchMaterialRemark;
	}
	public String getMucosaTouchMaterial() {
		return mucosaTouchMaterial;
	}
	public void setMucosaTouchMaterial(String mucosaTouchMaterial) {
		this.mucosaTouchMaterial = mucosaTouchMaterial;
	}
	public String getSharpObjNameRemark() {
		return sharpObjNameRemark;
	}
	public void setSharpObjNameRemark(String sharpObjNameRemark) {
		this.sharpObjNameRemark = sharpObjNameRemark;
	}
	public String getSharpObjName() {
		return sharpObjName;
	}
	public void setSharpObjName(String sharpObjName) {
		this.sharpObjName = sharpObjName;
	}
	public String getMucosaTouchPartRemark() {
		return mucosaTouchPartRemark;
	}
	public void setMucosaTouchPartRemark(String mucosaTouchPartRemark) {
		this.mucosaTouchPartRemark = mucosaTouchPartRemark;
	}
	public String getMucosaTouchPart() {
		return mucosaTouchPart;
	}
	public void setMucosaTouchPart(String mucosaTouchPart) {
		this.mucosaTouchPart = mucosaTouchPart;
	}
	public Integer getSharpObjType() {
		return sharpObjType;
	}
	public void setSharpObjType(Integer sharpObjType) {
		this.sharpObjType = sharpObjType;
	}
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}

	public String getPostEffectObjRemark() {
		return postEffectObjRemark;
	}

	public void setPostEffectObjRemark(String postEffectObjRemark) {
		this.postEffectObjRemark = postEffectObjRemark;
	}

	public String getPostExposeDisposeRemark() {
		return postExposeDisposeRemark;
	}

	public void setPostExposeDisposeRemark(String postExposeDisposeRemark) {
		this.postExposeDisposeRemark = postExposeDisposeRemark;
	}

	public Date getpCollectTime() {
		return pCollectTime;
	}

	public void setpCollectTime(Date pCollectTime) {
		this.pCollectTime = pCollectTime;
	}

	public Date getwCollectTime() {
		return wCollectTime;
	}

	public void setwCollectTime(Date wCollectTime) {
		this.wCollectTime = wCollectTime;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
}
