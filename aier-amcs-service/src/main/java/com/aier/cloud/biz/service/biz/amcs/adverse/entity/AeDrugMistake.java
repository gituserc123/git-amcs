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

/**
 * T_AE_DRUG_MISTAKE
 * 给药错误事件上报表表单
 * @author 爱尔眼科
 * @since 2022-11-29 10:09:40
 */
@TableName("T_AE_DRUG_MISTAKE")
public class AeDrugMistake extends BaseEntity<AeDrugMistake> {
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;
	
	/** 给药错误事件类型*/
    @Comment(value="给药错误事件类型")
	@TableField(value="mistake_event_type")
	@Length(max=20) private String mistakeEventType;
	
	/** 医嘱给药频次*/
    @Comment(value="医嘱给药频次")
	@TableField(value="doc_adv_drug_freq")
	@Length(max=30) private String docAdvDrugFreq;
	
	/** 医嘱给药频次(其他,输入的文本内容)*/
    @Comment(value="医嘱给药频次(其他,输入的文本内容)")
	@TableField(value="doc_adv_drug_freq_remark")
	@Length(max=255) private String docAdvDrugFreqRemark;
	
	/** 医嘱给药时间*/
    @Comment(value="医嘱给药时间")
	@TableField(value="doc_adv_drug_time")
	private java.util.Date docAdvDrugTime;
	
	/** 错误给药时间*/
    @Comment(value="错误给药时间")
	@TableField(value="mistake_drug_time")
	private java.util.Date mistakeDrugTime;

	/** 医嘱药物*/
    @Comment(value="医嘱药物")
	@TableField(value="doc_adv_drug")
	@Length(max=40) private String docAdvDrug;

	/** 医嘱药物(其他,输入的文本内容)*/
    @Comment(value="医嘱药物(其他,输入的文本内容)")
	@TableField(value="doc_adv_drug_remark")
	@Length(max=255) private String docAdvDrugRemark;

	/** 错误药物*/
    @Comment(value="错误药物")
	@TableField(value="mistake_drug")
	@Length(max=40) private String mistakeDrug;

	/** 错误药物(其他,输入的文本内容)*/
    @Comment(value="错误药物(其他,输入的文本内容)")
	@TableField(value="mistake_drug_remark")
	@Length(max=255) private String mistakeDrugRemark;
	
	/** 医嘱给药途径*/
    @Comment(value="医嘱给药途径")
	@TableField(value="doc_adv_drug_way")
	@Length(max=80) private String docAdvDrugWay;
	
	/** 错误给药途径*/
    @Comment(value="错误给药途径")
	@TableField(value="mistake_drug_way")
	@Length(max=80) private String mistakeDrugWay;
	
	/** 医嘱给药情况*/
    @Comment(value="医嘱给药情况")
	@TableField(value="doc_adv_drug_situation")
	@Length(max=80) private String docAdvDrugSituation;
	
	/** 实际执行情况*/
    @Comment(value="实际执行情况")
	@TableField(value="actual_exec_situation")
	@Length(max=80) private String actualExecSituation;
	
	/** 医嘱给药速度*/
    @Comment(value="医嘱给药速度")
	@TableField(value="doc_adv_drug_speed")
	@Length(max=80) private String docAdvDrugSpeed;
	
	/** 实际给药速度*/
    @Comment(value="实际给药速度")
	@TableField(value="actual_drug_speed")
	@Length(max=80) private String actualDrugSpeed;
	
	/** 医嘱给药剂量*/
    @Comment(value="医嘱给药剂量")
	@TableField(value="doc_adv_drug_dosage")
	@Length(max=40) private String docAdvDrugDosage;
	
	/** 实际给药剂量*/
    @Comment(value="实际给药剂量")
	@TableField(value="actual_drug_dosage")
	@Length(max=40) private String actualDrugDosage;
	
	/** 医嘱给药剂型*/
    @Comment(value="医嘱给药剂型")
	@TableField(value="doc_adv_drug_dosage_form")
	@Length(max=40) private String docAdvDrugDosageForm;
	
	/** 实际给药剂型*/
    @Comment(value="实际给药剂型")
	@TableField(value="actual_drug_dosage_form")
	@Length(max=40) private String actualDrugDosageForm;
	
	/** 医嘱给药床号及姓名*/
    @Comment(value="医嘱给药床号及姓名")
	@TableField(value="doc_adv_drug_bed_name")
	@Length(max=80) private String docAdvDrugBedName;
	
	/** 实际给药床号及姓名*/
    @Comment(value="实际给药床号及姓名")
	@TableField(value="actual_drug_bed_name")
	@Length(max=80) private String actualDrugBedName;
	
	/** 其他*/
    @Comment(value="其他")
	@TableField(value="event_other_text")
	@Length(max=80) private String eventOtherText;
	
	/** 药物名称*/
    @Comment(value="药物名称")
	@TableField(value="drug_name")
	@Length(max=80) private String drugName;
	
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


	/** 详细情况*/
	@Comment(value="详细情况")
	@TableField(value="event_desc")
	@Length(max=2000) private String eventDesc;
	

	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public String getMistakeEventType() {
		return mistakeEventType;
	}
	public void setMistakeEventType(String mistakeEventType) {
		this.mistakeEventType = mistakeEventType;
	}
	public String getDocAdvDrugFreq() {
		return docAdvDrugFreq;
	}
	public void setDocAdvDrugFreq(String docAdvDrugFreq) {
		this.docAdvDrugFreq = docAdvDrugFreq;
	}
	public String getDocAdvDrugFreqRemark() {
		return docAdvDrugFreqRemark;
	}
	public void setDocAdvDrugFreqRemark(String docAdvDrugFreqRemark) {
		this.docAdvDrugFreqRemark = docAdvDrugFreqRemark;
	}
	public java.util.Date getDocAdvDrugTime() {
		return docAdvDrugTime;
	}
	public void setDocAdvDrugTime(java.util.Date docAdvDrugTime) {
		this.docAdvDrugTime = docAdvDrugTime;
	}
	public java.util.Date getMistakeDrugTime() {
		return mistakeDrugTime;
	}
	public void setMistakeDrugTime(java.util.Date mistakeDrugTime) {
		this.mistakeDrugTime = mistakeDrugTime;
	}
	public String getDocAdvDrug() {
		return docAdvDrug;
	}
	public void setDocAdvDrug(String docAdvDrug) {
		this.docAdvDrug = docAdvDrug;
	}
	public String getDocAdvDrugRemark() {
		return docAdvDrugRemark;
	}
	public void setDocAdvDrugRemark(String docAdvDrugRemark) {
		this.docAdvDrugRemark = docAdvDrugRemark;
	}

	public String getMistakeDrug() {
		return mistakeDrug;
	}

	public void setMistakeDrug(String mistakeDrug) {
		this.mistakeDrug = mistakeDrug;
	}

	public String getMistakeDrugRemark() {
		return mistakeDrugRemark;
	}

	public void setMistakeDrugRemark(String mistakeDrugRemark) {
		this.mistakeDrugRemark = mistakeDrugRemark;
	}

	public String getDocAdvDrugWay() {
		return docAdvDrugWay;
	}
	public void setDocAdvDrugWay(String docAdvDrugWay) {
		this.docAdvDrugWay = docAdvDrugWay;
	}
	public String getMistakeDrugWay() {
		return mistakeDrugWay;
	}
	public void setMistakeDrugWay(String mistakeDrugWay) {
		this.mistakeDrugWay = mistakeDrugWay;
	}
	public String getDocAdvDrugSituation() {
		return docAdvDrugSituation;
	}
	public void setDocAdvDrugSituation(String docAdvDrugSituation) {
		this.docAdvDrugSituation = docAdvDrugSituation;
	}
	public String getActualExecSituation() {
		return actualExecSituation;
	}
	public void setActualExecSituation(String actualExecSituation) {
		this.actualExecSituation = actualExecSituation;
	}
	public String getDocAdvDrugSpeed() {
		return docAdvDrugSpeed;
	}
	public void setDocAdvDrugSpeed(String docAdvDrugSpeed) {
		this.docAdvDrugSpeed = docAdvDrugSpeed;
	}
	public String getActualDrugSpeed() {
		return actualDrugSpeed;
	}
	public void setActualDrugSpeed(String actualDrugSpeed) {
		this.actualDrugSpeed = actualDrugSpeed;
	}
	public String getDocAdvDrugDosage() {
		return docAdvDrugDosage;
	}
	public void setDocAdvDrugDosage(String docAdvDrugDosage) {
		this.docAdvDrugDosage = docAdvDrugDosage;
	}
	public String getActualDrugDosage() {
		return actualDrugDosage;
	}
	public void setActualDrugDosage(String actualDrugDosage) {
		this.actualDrugDosage = actualDrugDosage;
	}
	public String getDocAdvDrugDosageForm() {
		return docAdvDrugDosageForm;
	}
	public void setDocAdvDrugDosageForm(String docAdvDrugDosageForm) {
		this.docAdvDrugDosageForm = docAdvDrugDosageForm;
	}
	public String getActualDrugDosageForm() {
		return actualDrugDosageForm;
	}
	public void setActualDrugDosageForm(String actualDrugDosageForm) {
		this.actualDrugDosageForm = actualDrugDosageForm;
	}
	public String getDocAdvDrugBedName() {
		return docAdvDrugBedName;
	}
	public void setDocAdvDrugBedName(String docAdvDrugBedName) {
		this.docAdvDrugBedName = docAdvDrugBedName;
	}
	public String getActualDrugBedName() {
		return actualDrugBedName;
	}
	public void setActualDrugBedName(String actualDrugBedName) {
		this.actualDrugBedName = actualDrugBedName;
	}
	public String getEventOtherText() {
		return eventOtherText;
	}
	public void setEventOtherText(String eventOtherText) {
		this.eventOtherText = eventOtherText;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
}
