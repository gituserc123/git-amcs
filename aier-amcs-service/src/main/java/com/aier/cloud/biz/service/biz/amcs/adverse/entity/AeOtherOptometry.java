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
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_AE_OUTP_PATIENT 门诊患者不良事件(手术患者)
 * 
 * @author 爱尔眼科
 * @since 2022-12-06 10:25:47
 */
@TableName("T_AE_OTHER_OPTOMETRY")
public class AeOtherOptometry extends BaseEntity<AeOtherOptometry> {

	private static final long serialVersionUID = 5973724110251733644L;

	/** 不良事件基础表ID */
    @Comment(value="不良事件基础表ID")
	@TableField(value = "basic_id")
	private Long basicId;

	/** 就诊时间 */
    @Comment(value="就诊时间")
	@TableField(value = "visit_date")
	private java.util.Date visitDate;

	/** 诊断 */
    @Comment(value="诊断")
	@TableField(value = "diagnose")
	@Length(max = 200)
	private String diagnose;

	@TableField(exist = false)
	private String payDiagnose;

	public String getPayDiagnose() {
		return diagnose;
	}

	/** 事件经过描述 */
    @Comment(value="事件经过描述")
	@TableField(value = "detail_info")
	@Length(max = 1000)
	private String detailInfo;

	/** 创建者ID */
    @Comment(value="创建者ID")
	@TableField(value = "creator")
	@NotBlank
	private Long creator;

	/** 创建时间 */
    @Comment(value="创建时间")
	@TableField(value = "create_date", fill = FieldFill.INSERT)
	@NotBlank
	private java.util.Date createDate;

	/** 医院ID */
    @Comment(value="医院ID")
	@TableField(value = "hosp_id")
	private Long hospId;

	/** 目前情况右眼矫正视力(OD)类型(1数值2描述） */
    @Comment(value="目前情况右眼矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "mqqk_jzsl_vod_type")
	private Integer mqqkJzslVodType;

	/** 目前情况右眼矫正视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况右眼矫正视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_jzsl_vod")
	@Length(max = 2)
	private String mqqkJzslVod;

	/** 目前情况右眼矫正视力(OD)名称 */
    @Comment(value="目前情况右眼矫正视力(OD)名称")
	@TableField(value = "mqqk_jzsl_vod_name")
	@Length(max = 10)
	private String mqqkJzslVodName;

	/** 目前情况左眼矫正视力(OS)类型(1数值2描述） */
    @Comment(value="目前情况左眼矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "mqqk_jzsl_vos_type")
	@Length(max = 1)
	private String mqqkJzslVosType;

	/** 目前情况左眼矫正视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况左眼矫正视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_jzsl_vos")
	@Length(max = 2)
	private String mqqkJzslVos;

	/** 目前情况左眼屈矫正视力(OS)名称 */
    @Comment(value="目前情况左眼屈矫正视力(OS)名称")
	@TableField(value = "mqqk_jzsl_vos_name")
	@Length(max = 10)
	private String mqqkJzslVosName;

	/** 基本检查信息右眼裸眼远视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value = "naked_far_vod_type")
	private Integer nakedFarVodType;

	/** 基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "naked_far_vod")
	@Length(max = 2)
	private String nakedFarVod;

	/** 基本检查信息右眼裸眼远视力(OD)名称 */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)名称")
	@TableField(value = "naked_far_vod_name")
	@Length(max = 10)
	private String nakedFarVodName;

	/** 基本检查信息左眼裸眼远视力(OS)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼裸眼远视力(OS)类型(1数值2描述）")
	@TableField(value = "naked_far_vos_type")
	private Integer nakedFarVosType;

	/** 基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "naked_far_vos")
	@Length(max = 2)
	private String nakedFarVos;

	/** 基本检查信息右眼裸眼远视力(OS)名称 */
    @Comment(value="基本检查信息右眼裸眼远视力(OS)名称")
	@TableField(value = "naked_far_vos_name")
	@Length(max = 10)
	private String nakedFarVosName;

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

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
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

	public Integer getMqqkJzslVodType() {
		return mqqkJzslVodType;
	}

	public void setMqqkJzslVodType(Integer mqqkJzslVodType) {
		this.mqqkJzslVodType = mqqkJzslVodType;
	}

	public String getMqqkJzslVod() {
		return mqqkJzslVod;
	}

	public void setMqqkJzslVod(String mqqkJzslVod) {
		this.mqqkJzslVod = mqqkJzslVod;
	}

	public String getMqqkJzslVodName() {
		return mqqkJzslVodName;
	}

	public void setMqqkJzslVodName(String mqqkJzslVodName) {
		this.mqqkJzslVodName = mqqkJzslVodName;
	}

	public String getMqqkJzslVosType() {
		return mqqkJzslVosType;
	}

	public void setMqqkJzslVosType(String mqqkJzslVosType) {
		this.mqqkJzslVosType = mqqkJzslVosType;
	}

	public String getMqqkJzslVos() {
		return mqqkJzslVos;
	}

	public void setMqqkJzslVos(String mqqkJzslVos) {
		this.mqqkJzslVos = mqqkJzslVos;
	}

	public String getMqqkJzslVosName() {
		return mqqkJzslVosName;
	}

	public void setMqqkJzslVosName(String mqqkJzslVosName) {
		this.mqqkJzslVosName = mqqkJzslVosName;
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
}
