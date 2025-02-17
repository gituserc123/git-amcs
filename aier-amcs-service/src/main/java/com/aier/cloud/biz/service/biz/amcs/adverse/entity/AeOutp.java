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
 * T_AE_OUTP
 * 
 * @author 爱尔眼科
 * @since 2022-07-25 16:49:03
 */
@TableName("T_AE_OUTP")
public class AeOutp extends BaseEntity<AeOutp> {
	/** 基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc）*/
    @Comment(value="基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value="naked_far_vos")
	@Length(max=2) private String nakedFarVos;

	/** 基本检查信息右眼裸眼远视力(OS)名称*/
    @Comment(value="基本检查信息右眼裸眼远视力(OS)名称")
	@TableField(value="naked_far_vos_name")
	@Length(max=10) private String nakedFarVosName;

	/** 基本检查信息右眼眼压(OD)类型编码(码表：iop_type）*/
    @Comment(value="基本检查信息右眼眼压(OD)类型编码(码表：iop_type）")
	@TableField(value="iop_type_od")
	@Length(max=2) private String iopTypeOd;

	/** 基本检查信息右眼眼压(OD)类型名称*/
    @Comment(value="基本检查信息右眼眼压(OD)类型名称")
	@TableField(value="iop_type_od_name")
	@Length(max=20) private String iopTypeOdName;

	/** 基本检查信息右眼眼压数值或指测眼压编码(OD)*/
    @Comment(value="基本检查信息右眼眼压数值或指测眼压编码(OD)")
	@TableField(value="iop_od")
	@Length(max=10) private String iopOd;

	/** 基本检查信息右眼眼压指测眼压名称(OD)*/
    @Comment(value="基本检查信息右眼眼压指测眼压名称(OD)")
	@TableField(value="iop_od_name")
	@Length(max=10) private String iopOdName;

	/** 基本检查信息左眼眼压(OS)类型编码(码表：iop_type）*/
    @Comment(value="基本检查信息左眼眼压(OS)类型编码(码表：iop_type）")
	@TableField(value="iop_type_os")
	@Length(max=2) private String iopTypeOs;

	/** 基本检查信息左眼眼压(OS)类型名称*/
    @Comment(value="基本检查信息左眼眼压(OS)类型名称")
	@TableField(value="iop_type_os_name")
	@Length(max=20) private String iopTypeOsName;

	/** 基本检查信息左眼眼压数值或指测眼压编码(OS)*/
    @Comment(value="基本检查信息左眼眼压数值或指测眼压编码(OS)")
	@TableField(value="iop_os")
	@Length(max=10) private String iopOs;

	/** 基本检查信息左眼眼压指测眼压名称(OS)*/
    @Comment(value="基本检查信息左眼眼压指测眼压名称(OS)")
	@TableField(value="iop_os_name")
	@Length(max=10) private String iopOsName;

	/** 基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）*/
    @Comment(value="基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value="naked_far_vod_type")
	private Integer nakedFarVodType;

	/** 基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc）*/
    @Comment(value="基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value="naked_far_vod")
	@Length(max=2) private String nakedFarVod;

	/** 基本检查信息右眼裸眼远视力(OD)名称*/
    @Comment(value="基本检查信息右眼裸眼远视力(OD)名称")
	@TableField(value="naked_far_vod_name")
	@Length(max=10) private String nakedFarVodName;

	/** 基本检查信息左眼裸眼远视力(OS)类型(1数值2描述）*/
    @Comment(value="基本检查信息左眼裸眼远视力(OS)类型(1数值2描述）")
	@TableField(value="naked_far_vos_type")
	private Integer nakedFarVosType;

	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;

	/** 门诊时间*/
    @Comment(value="门诊时间")
	@TableField(value="visit_date")
	private java.util.Date visitDate;

	/** 门诊诊断*/
    @Comment(value="门诊诊断")
	@TableField(value="visit_diagnose")
	@Length(max=500) private String visitDiagnose;

	@TableField(exist = false)
	private String payDiagnose;

	public String getPayDiagnose() {
		return visitDiagnose;
	}


	/** 右眼视力*/
    @Comment(value="右眼视力")
	@TableField(value="vod")
	@Length(max=10) private String vod;

	/** 左眼视力*/
    @Comment(value="左眼视力")
	@TableField(value="vos")
	@Length(max=10) private String vos;

	/** 右眼眼压*/
    @Comment(value="右眼眼压")
	@TableField(value="pod")
	@Length(max=10) private String pod;

	/** 左眼眼压*/
    @Comment(value="左眼眼压")
	@TableField(value="pos")
	@Length(max=10) private String pos;

	/** 目前情况*/
    @Comment(value="目前情况")
	@TableField(value="cur_situation")
	@Length(max=500) private String curSituation;

	/** 治疗情况*/
    @Comment(value="治疗情况")
	@TableField(value="treat_situation")
	@Length(max=500) private String treatSituation;

	/** 诉求*/
    @Comment(value="诉求")
	@TableField(value="patient_appeal")
	@Length(max=500) private String patientAppeal;

	/** 事件经过*/
    @Comment(value="事件经过")
	@TableField(value="proc_descr")
	@Length(max=2000) private String procDescr;

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
	private Long hospId;

	/** 事件经过描述_1 */
    @Comment(value="事件经过描述_1")
	@TableField(value = "proc_descr_one")
	@Length(max = 2000)
	private String procDescrOne;

	/** 事件经过描述_2 */
    @Comment(value="事件经过描述_2")
	@TableField(value = "proc_descr_two")
	@Length(max = 2000)
	private String procDescrTwo;


	public String getNakedFarVos() {
		return nakedFarVos;
	}
	public void setNakedFarVos(String nakedFarVos) {
		this.nakedFarVos = nakedFarVos;
	}
	public String getNakedFarVosName() {
		return nakedFarVosName;
	}
	public void setNakedFarVosName(String nakedFarVosName) {
		this.nakedFarVosName = nakedFarVosName;
	}
	public String getIopTypeOd() {
		return iopTypeOd;
	}
	public void setIopTypeOd(String iopTypeOd) {
		this.iopTypeOd = iopTypeOd;
	}
	public String getIopTypeOdName() {
		return iopTypeOdName;
	}
	public void setIopTypeOdName(String iopTypeOdName) {
		this.iopTypeOdName = iopTypeOdName;
	}
	public String getIopOd() {
		return iopOd;
	}
	public void setIopOd(String iopOd) {
		this.iopOd = iopOd;
	}
	public String getIopOdName() {
		return iopOdName;
	}
	public void setIopOdName(String iopOdName) {
		this.iopOdName = iopOdName;
	}
	public String getIopTypeOs() {
		return iopTypeOs;
	}
	public void setIopTypeOs(String iopTypeOs) {
		this.iopTypeOs = iopTypeOs;
	}
	public String getIopTypeOsName() {
		return iopTypeOsName;
	}
	public void setIopTypeOsName(String iopTypeOsName) {
		this.iopTypeOsName = iopTypeOsName;
	}
	public String getIopOs() {
		return iopOs;
	}
	public void setIopOs(String iopOs) {
		this.iopOs = iopOs;
	}
	public String getIopOsName() {
		return iopOsName;
	}
	public void setIopOsName(String iopOsName) {
		this.iopOsName = iopOsName;
	}
	public Integer getNakedFarVodType() {
		return nakedFarVodType;
	}
	public void setNakedFarVodType(Integer nakedFarVodType) {
		this.nakedFarVodType = nakedFarVodType;
	}
	public String getNakedFarVod() {
		return nakedFarVod;
	}
	public void setNakedFarVod(String nakedFarVod) {
		this.nakedFarVod = nakedFarVod;
	}
	public String getNakedFarVodName() {
		return nakedFarVodName;
	}
	public void setNakedFarVodName(String nakedFarVodName) {
		this.nakedFarVodName = nakedFarVodName;
	}
	public Integer getNakedFarVosType() {
		return nakedFarVosType;
	}
	public void setNakedFarVosType(Integer nakedFarVosType) {
		this.nakedFarVosType = nakedFarVosType;
	}
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public java.util.Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(java.util.Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitDiagnose() {
		return visitDiagnose;
	}
	public void setVisitDiagnose(String visitDiagnose) {
		this.visitDiagnose = visitDiagnose;
	}
	public String getVod() {
		return vod;
	}
	public void setVod(String vod) {
		this.vod = vod;
	}
	public String getVos() {
		return vos;
	}
	public void setVos(String vos) {
		this.vos = vos;
	}
	public String getPod() {
		return pod;
	}
	public void setPod(String pod) {
		this.pod = pod;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getCurSituation() {
		return curSituation;
	}
	public void setCurSituation(String curSituation) {
		this.curSituation = curSituation;
	}
	public String getTreatSituation() {
		return treatSituation;
	}
	public void setTreatSituation(String treatSituation) {
		this.treatSituation = treatSituation;
	}
	public String getPatientAppeal() {
		return patientAppeal;
	}
	public void setPatientAppeal(String patientAppeal) {
		this.patientAppeal = patientAppeal;
	}
	public String getProcDescr() {
		return procDescr;
	}
	public void setProcDescr(String procDescr) {
		this.procDescr = procDescr;
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

	public String getProcDescrOne() {
		return procDescrOne;
	}

	public void setProcDescrOne(String procDescrOne) {
		this.procDescrOne = procDescrOne;
	}

	public String getProcDescrTwo() {
		return procDescrTwo;
	}

	public void setProcDescrTwo(String procDescrTwo) {
		this.procDescrTwo = procDescrTwo;
	}
}
