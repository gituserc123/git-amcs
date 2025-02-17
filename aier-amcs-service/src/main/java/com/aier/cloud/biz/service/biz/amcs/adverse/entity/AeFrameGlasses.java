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

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.basic.starter.service.controller.DictName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_AE_FRAME_GLASSES 框架眼镜不良反应上报表单
 * 
 * @author 爱尔眼科
 * @since 2022-09-29 15:36:34
 */
@TableName("T_AE_FRAME_GLASSES")
public class AeFrameGlasses extends BaseEntity<AeFrameGlasses> {

	private static final long serialVersionUID = -4341139950760292120L;

	/** 基本检查信息左眼配镜处方矫正视力(OS)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼配镜处方矫正视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_pjcf_jzsl_os")
	@Length(max = 2)
	private String jcPjcfJzslOs;

	/** 基本检查信息左眼配镜处方矫正视力(OS)名称 */
    @Comment(value="基本检查信息左眼配镜处方矫正视力(OS)名称")
	@TableField(value = "jc_pjcf_jzsl_os_name")
	@Length(max = 10)
	private String jcPjcfJzslOsName;

	/** 基本检查信息右眼配镜处方下加光(OD) */
    @Comment(value="基本检查信息右眼配镜处方下加光(OD)")
	@TableField(value = "jc_pjcf_xjg_od")
	@Length(max = 10)
	private String jcPjcfXjgOd;

	/** 基本检查信息左眼配镜处方下加光(OS) */
    @Comment(value="基本检查信息左眼配镜处方下加光(OS)")
	@TableField(value = "jc_pjcf_xjg_os")
	@Length(max = 10)
	private String jcPjcfXjgOs;

	/** 基本检查信息右眼配镜处方单侧瞳距(OD) */
    @Comment(value="基本检查信息右眼配镜处方单侧瞳距(OD)")
	@TableField(value = "jc_pjcf_dctj_od")
	@Length(max = 10)
	private String jcPjcfDctjOd;

	/** 基本检查信息左眼配镜处方单侧瞳距(OS) */
    @Comment(value="基本检查信息左眼配镜处方单侧瞳距(OS)")
	@TableField(value = "jc_pjcf_dctj_os")
	@Length(max = 10)
	private String jcPjcfDctjOs;

	/** 基本检查信息右眼配镜参数瞳高(R) */
    @Comment(value="基本检查信息右眼配镜参数瞳高(R)")
	@TableField(value = "jc_pjcs_tg_r")
	@Length(max = 10)
	private String jcPjcsTgR;

	/** 基本检查信息左眼配镜参数瞳高(L) */
    @Comment(value="基本检查信息左眼配镜参数瞳高(L)")
	@TableField(value = "jc_pjcs_tg_l")
	@Length(max = 10)
	private String jcPjcsTgL;

	/** 基本检查信息右眼配镜参数镜片类型(R) */
    @Comment(value="基本检查信息右眼配镜参数镜片类型(R)")
	@TableField(value = "jc_pjcs_jplx_r")
	@Length(max = 10)
	private String jcPjcsJplxR;

	/** 基本检查信息左眼配镜参数镜片类型(L) */
    @Comment(value="基本检查信息左眼配镜参数镜片类型(L)")
	@TableField(value = "jc_pjcs_jplx_l")
	@Length(max = 10)
	private String jcPjcsJplxL;

	/** 基本检查信息右眼配镜参数镜片直径(R) */
    @Comment(value="基本检查信息右眼配镜参数镜片直径(R)")
	@TableField(value = "jc_pjcs_jpzj_r")
	@Length(max = 10)
	private String jcPjcsJpzjR;

	/** 基本检查信息左眼配镜参数镜片直径(L) */
    @Comment(value="基本检查信息左眼配镜参数镜片直径(L)")
	@TableField(value = "jc_pjcs_jpzj_l")
	@Length(max = 10)
	private String jcPjcsJpzjL;

	/** 基本检查信息右眼配镜参数镜架尺寸(R) */
    @Comment(value="基本检查信息右眼配镜参数镜架尺寸(R)")
	@TableField(value = "jc_pjcs_jjcc_r")
	@Length(max = 10)
	private String jcPjcsJjccR;

	/** 基本检查信息左眼配镜参数镜架尺寸(L) */
    @Comment(value="基本检查信息左眼配镜参数镜架尺寸(L)")
	@TableField(value = "jc_pjcs_jjcc_l")
	@Length(max = 10)
	private String jcPjcsJjccL;

	/** 事件起因（码表：event_cause） */
    @Comment(value="事件起因（码表：event_cause）")
	@TableField(value = "event_cause")
	@Length(max = 1)
	private String eventCause;

	/** 患者及家长诉求 */
    @Comment(value="患者及家长诉求")
	@TableField(value = "patient_appeal")
	@Length(max = 512)
	private String patientAppeal;

	/** 事件结果（码表：event_result） */
    @Comment(value="事件结果（码表：event_result）")
	@TableField(value = "event_result")
	@Length(max = 1)
	private String eventResult;

	/** 处理过程右眼综合验光球镜(OD) */
    @Comment(value="处理过程右眼综合验光球镜(OD)")
	@TableField(value = "cl_yg_qj_od")
	@Length(max = 10)
	private String clYgQjOd;

	/** 处理过程左眼综合验光球镜(OS) */
    @Comment(value="处理过程左眼综合验光球镜(OS)")
	@TableField(value = "cl_yg_qj_os")
	@Length(max = 10)
	private String clYgQjOs;

	/** 处理过程右眼综合验光柱镜(OD) */
    @Comment(value="处理过程右眼综合验光柱镜(OD)")
	@TableField(value = "cl_yg_zj_od")
	@Length(max = 10)
	private String clYgZjOd;

	/** 处理过程左眼综合验光柱镜(OS) */
    @Comment(value="处理过程左眼综合验光柱镜(OS)")
	@TableField(value = "cl_yg_zj_os")
	@Length(max = 10)
	private String clYgZjOs;

	/** 处理过程右眼综合验光轴位(OD) */
    @Comment(value="处理过程右眼综合验光轴位(OD)")
	@TableField(value = "cl_yg_cw_od")
	@Length(max = 10)
	private String clYgCwOd;

	/** 处理过程左眼综合验光轴位(OS) */
    @Comment(value="处理过程左眼综合验光轴位(OS)")
	@TableField(value = "cl_yg_cw_os")
	@Length(max = 10)
	private String clYgCwOs;

	/** 处理过程右眼综合验光矫正视力(OD)类型(1数值2描述） */
    @Comment(value="处理过程右眼综合验光矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "cl_yg_jzsl_od_type")
	private Integer clYgJzslOdType;

	/** 处理过程右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="处理过程右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "cl_yg_jzsl_od")
	@Length(max = 2)
	private String clYgJzslOd;

	/** 处理过程右眼综合验光矫正视力(OD)名称 */
    @Comment(value="处理过程右眼综合验光矫正视力(OD)名称")
	@TableField(value = "cl_yg_jzsl_od_name")
	@Length(max = 10)
	private String clYgJzslOdName;

	/** 处理过程左眼综合验光矫正视力(OS)类型(1数值2描述） */
    @Comment(value="处理过程左眼综合验光矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "cl_yg_jzsl_os_type")
	private Integer clYgJzslOsType;

	/** 处理过程左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="处理过程左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "cl_yg_jzsl_os")
	@Length(max = 2)
	private String clYgJzslOs;

	/** 处理过程左眼综合验光矫正视力(OS)名称 */
    @Comment(value="处理过程左眼综合验光矫正视力(OS)名称")
	@TableField(value = "cl_yg_jzsl_os_name")
	@Length(max = 10)
	private String clYgJzslOsName;

	/** 处理过程右眼配镜处方球镜(OD) */
    @Comment(value="处理过程右眼配镜处方球镜(OD)")
	@TableField(value = "cl_pjcf_qj_od")
	@Length(max = 10)
	private String clPjcfQjOd;

	/** 处理过程左眼配镜处方球镜(OS) */
    @Comment(value="处理过程左眼配镜处方球镜(OS)")
	@TableField(value = "cl_pjcf_qj_os")
	@Length(max = 10)
	private String clPjcfQjOs;

	/** 处理过程右眼配镜处方柱镜(OD) */
    @Comment(value="处理过程右眼配镜处方柱镜(OD)")
	@TableField(value = "cl_pjcf_zj_od")
	@Length(max = 10)
	private String clPjcfZjOd;

	/** 处理过程左眼配镜处方柱镜(OS) */
    @Comment(value="处理过程左眼配镜处方柱镜(OS)")
	@TableField(value = "cl_pjcf_zj_os")
	@Length(max = 10)
	private String clPjcfZjOs;

	/** 处理过程右眼配镜处方轴位(OD) */
    @Comment(value="处理过程右眼配镜处方轴位(OD)")
	@TableField(value = "cl_pjcf_cw_od")
	@Length(max = 10)
	private String clPjcfCwOd;

	/** 处理过程左眼配镜处方轴位(OS) */
    @Comment(value="处理过程左眼配镜处方轴位(OS)")
	@TableField(value = "cl_pjcf_cw_os")
	@Length(max = 10)
	private String clPjcfCwOs;

	/** 处理过程右眼配镜处方矫正视力(OD)类型(1数值2描述） */
    @Comment(value="处理过程右眼配镜处方矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "cl_pjcf_jzsl_od_type")
	private Integer clPjcfJzslOdType;

	/** 处理过程右眼配镜处方矫正视力(OD)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="处理过程右眼配镜处方矫正视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "cl_pjcf_jzsl_od")
	@Length(max = 2)
	private String clPjcfJzslOd;

	/** 处理过程右眼配镜处方矫正视力(OD)名称 */
    @Comment(value="处理过程右眼配镜处方矫正视力(OD)名称")
	@TableField(value = "cl_pjcf_jzsl_od_name")
	@Length(max = 10)
	private String clPjcfJzslOdName;

	/** 处理过程左眼配镜处方矫正视力(OS)类型(1数值2描述） */
    @Comment(value="处理过程左眼配镜处方矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "cl_pjcf_jzsl_os_type")
	private Integer clPjcfJzslOsType;

	/** 处理过程左眼配镜处方矫正视力(OS)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="处理过程左眼配镜处方矫正视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "cl_pjcf_jzsl_os")
	@Length(max = 2)
	private String clPjcfJzslOs;

	/** 处理过程左眼配镜处方矫正视力(OS)名称 */
    @Comment(value="处理过程左眼配镜处方矫正视力(OS)名称")
	@TableField(value = "cl_pjcf_jzsl_os_name")
	@Length(max = 10)
	private String clPjcfJzslOsName;

	/** 处理过程右眼配镜处方下加光(OD) */
    @Comment(value="处理过程右眼配镜处方下加光(OD)")
	@TableField(value = "cl_pjcf_xjg_od")
	@Length(max = 10)
	private String clPjcfXjgOd;

	/** 处理过程左眼配镜处方下加光(OS) */
    @Comment(value="处理过程左眼配镜处方下加光(OS)")
	@TableField(value = "cl_pjcf_xjg_os")
	@Length(max = 10)
	private String clPjcfXjgOs;

	/** 处理过程右眼配镜处方单侧瞳距(OD) */
    @Comment(value="处理过程右眼配镜处方单侧瞳距(OD)")
	@TableField(value = "cl_pjcf_dctj_od")
	@Length(max = 10)
	private String clPjcfDctjOd;

	/** 处理过程左眼配镜处方单侧瞳距(OS) */
    @Comment(value="处理过程左眼配镜处方单侧瞳距(OS)")
	@TableField(value = "cl_pjcf_dctj_os")
	@Length(max = 10)
	private String clPjcfDctjOs;

	/** 处理过程右眼配镜参数瞳高(R) */
    @Comment(value="处理过程右眼配镜参数瞳高(R)")
	@TableField(value = "cl_pjcs_tg_r")
	@Length(max = 10)
	private String clPjcsTgR;

	/** 处理过程左眼配镜参数瞳高(L) */
    @Comment(value="处理过程左眼配镜参数瞳高(L)")
	@TableField(value = "cl_pjcs_tg_l")
	@Length(max = 10)
	private String clPjcsTgL;

	/** 处理过程右眼配镜参数镜片类型(R) */
    @Comment(value="处理过程右眼配镜参数镜片类型(R)")
	@TableField(value = "cl_pjcs_jplx_r")
	@Length(max = 10)
	private String clPjcsJplxR;

	/** 处理过程左眼配镜参数镜片类型(L) */
    @Comment(value="处理过程左眼配镜参数镜片类型(L)")
	@TableField(value = "cl_pjcs_jplx_l")
	@Length(max = 10)
	private String clPjcsJplxL;

	/** 处理过程右眼配镜参数镜片直径(R) */
    @Comment(value="处理过程右眼配镜参数镜片直径(R)")
	@TableField(value = "cl_pjcs_jpzj_r")
	@Length(max = 10)
	private String clPjcsJpzjR;

	/** 处理过程左眼配镜参数镜片直径(L) */
    @Comment(value="处理过程左眼配镜参数镜片直径(L)")
	@TableField(value = "cl_pjcs_jpzj_l")
	@Length(max = 10)
	private String clPjcsJpzjL;

	/** 处理过程右眼配镜参数镜架尺寸(R) */
    @Comment(value="处理过程右眼配镜参数镜架尺寸(R)")
	@TableField(value = "cl_pjcs_jjcc_r")
	@Length(max = 10)
	private String clPjcsJjccR;

	/** 处理过程左眼配镜参数镜架尺寸(L) */
    @Comment(value="处理过程左眼配镜参数镜架尺寸(L)")
	@TableField(value = "cl_pjcs_jjcc_l")
	@Length(max = 10)
	private String clPjcsJjccL;

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
	@NotBlank
	private Long hospId;

	/** 不良事件基础表ID */
    @Comment(value="不良事件基础表ID")
	@TableField(value = "basic_id")
	private Long basicId;

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

	/** 基本检查信息右眼裸眼远视力(OD)名称 */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)名称")
	@TableField(value = "naked_far_vos_name")
	@Length(max = 10)
	private String nakedFarVosName;

	/** 基本检查信息右眼眼压(OD)类型编码(码表：iop_type） */
    @Comment(value="基本检查信息右眼眼压(OD)类型编码(码表：iop_type）")
	@TableField(value = "iop_type_od")
	@Length(max = 2)
	private String iopTypeOd;

	/** 基本检查信息右眼眼压(OD)类型名称 */
    @Comment(value="基本检查信息右眼眼压(OD)类型名称")
	@TableField(value = "iop_type_od_name")
	@DictName(type = "iop_type", from = "iopTypeOd")
	@Length(max = 20)
	private String iopTypeOdName;

	/** 基本检查信息右眼眼压数值或指测眼压编码(OD) */
    @Comment(value="基本检查信息右眼眼压数值或指测眼压编码(OD)")
	@TableField(value = "iop_od")
	@Length(max = 10)
	private String iopOd;

	/** 基本检查信息右眼眼压指测眼压名称(OD) */
    @Comment(value="基本检查信息右眼眼压指测眼压名称(OD)")
	@TableField(value = "iop_od_name")
	@Length(max = 10)
	private String iopOdName;

	/** 基本检查信息左眼眼压(OS)类型编码(码表：iop_type） */
    @Comment(value="基本检查信息左眼眼压(OS)类型编码(码表：iop_type）")
	@TableField(value = "iop_type_os")
	@Length(max = 2)
	private String iopTypeOs;

	/** 基本检查信息左眼眼压(OS)类型名称 */
    @Comment(value="基本检查信息左眼眼压(OS)类型名称")
	@TableField(value = "iop_type_os_name")
	@DictName(type = "iop_type", from = "iopTypeOs")
	@Length(max = 20)
	private String iopTypeOsName;

	/** 基本检查信息左眼眼压数值或指测眼压编码(OS) */
    @Comment(value="基本检查信息左眼眼压数值或指测眼压编码(OS)")
	@TableField(value = "iop_os")
	@Length(max = 10)
	private String iopOs;

	/** 基本检查信息左眼眼压指测眼压名称(OS) */
    @Comment(value="基本检查信息左眼眼压指测眼压名称(OS)")
	@TableField(value = "iop_os_name")
	@Length(max = 10)
	private String iopOsName;

	/** 基本检查信息右眼综合验光球镜(OD) */
    @Comment(value="基本检查信息右眼综合验光球镜(OD)")
	@TableField(value = "jc_yg_qj_od")
	@Length(max = 10)
	private String jcYgQjOd;

	/** 基本检查信息左眼综合验光球镜(OS) */
    @Comment(value="基本检查信息左眼综合验光球镜(OS)")
	@TableField(value = "jc_yg_qj_os")
	@Length(max = 10)
	private String jcYgQjOs;

	/** 基本检查信息右眼综合验光柱镜(OD) */
    @Comment(value="基本检查信息右眼综合验光柱镜(OD)")
	@TableField(value = "jc_yg_zj_od")
	@Length(max = 10)
	private String jcYgZjOd;

	/** 基本检查信息左眼综合验光柱镜(OS) */
    @Comment(value="基本检查信息左眼综合验光柱镜(OS)")
	@TableField(value = "jc_yg_zj_os")
	@Length(max = 10)
	private String jcYgZjOs;

	/** 基本检查信息右眼综合验光轴位(OD) */
    @Comment(value="基本检查信息右眼综合验光轴位(OD)")
	@TableField(value = "jc_yg_cw_od")
	@Length(max = 10)
	private String jcYgCwOd;

	/** 基本检查信息左眼综合验光轴位(OS) */
    @Comment(value="基本检查信息左眼综合验光轴位(OS)")
	@TableField(value = "jc_yg_cw_os")
	@Length(max = 10)
	private String jcYgCwOs;

	/** 基本检查信息右眼综合验光矫正视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼综合验光矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_yg_jzsl_od_type")
	private Integer jcYgJzslOdType;

	/** 基本检查信息右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_yg_jzsl_od")
	@Length(max = 2)
	private String jcYgJzslOd;

	/** 基本检查信息右眼综合验光矫正视力(OD)名称 */
    @Comment(value="基本检查信息右眼综合验光矫正视力(OD)名称")
	@TableField(value = "jc_yg_jzsl_od_name")
	@Length(max = 10)
	private String jcYgJzslOdName;

	/** 基本检查信息左眼综合验光矫正视力(OS)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼综合验光矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "jc_yg_jzsl_os_type")
	private Integer jcYgJzslOsType;

	/** 基本检查信息左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_yg_jzsl_os")
	@Length(max = 2)
	private String jcYgJzslOs;

	/** 基本检查信息右眼综合验光矫正视力(OS)名称 */
    @Comment(value="基本检查信息右眼综合验光矫正视力(OS)名称")
	@TableField(value = "jc_yg_jzsl_os_name")
	@Length(max = 10)
	private String jcYgJzslOsName;

	/** 基本检查信息右眼角膜平K值(OD) */
    @Comment(value="基本检查信息右眼角膜平K值(OD)")
	@TableField(value = "jc_jmpkz_od")
	@Length(max = 10)
	private String jcJmpkzOd;

	/** 基本检查信息右眼角膜平K轴位(OD) */
    @Comment(value="基本检查信息右眼角膜平K轴位(OD)")
	@TableField(value = "jc_jmpkzw_od")
	@Length(max = 10)
	private String jcJmpkzwOd;

	/** 基本检查信息左眼角膜平K值(OS) */
    @Comment(value="基本检查信息左眼角膜平K值(OS)")
	@TableField(value = "jc_jmpkz_os")
	@Length(max = 10)
	private String jcJmpkzOs;

	/** 基本检查信息左眼角膜平K轴位(OS) */
    @Comment(value="基本检查信息左眼角膜平K轴位(OS)")
	@TableField(value = "jc_jmpkzw_os")
	@Length(max = 10)
	private String jcJmpkzwOs;

	/** 基本检查信息右眼配镜处方球镜(OD) */
    @Comment(value="基本检查信息右眼配镜处方球镜(OD)")
	@TableField(value = "jc_pjcf_qj_od")
	@Length(max = 10)
	private String jcPjcfQjOd;

	/** 基本检查信息左眼配镜处方球镜(OS) */
    @Comment(value="基本检查信息左眼配镜处方球镜(OS)")
	@TableField(value = "jc_pjcf_qj_os")
	@Length(max = 10)
	private String jcPjcfQjOs;

	/** 基本检查信息右眼配镜处方柱镜(OD) */
    @Comment(value="基本检查信息右眼配镜处方柱镜(OD)")
	@TableField(value = "jc_pjcf_zj_od")
	@Length(max = 10)
	private String jcPjcfZjOd;

	/** 基本检查信息左眼配镜处方柱镜(OS) */
    @Comment(value="基本检查信息左眼配镜处方柱镜(OS)")
	@TableField(value = "jc_pjcf_zj_os")
	@Length(max = 10)
	private String jcPjcfZjOs;

	/** 基本检查信息右眼配镜处方轴位(OD) */
    @Comment(value="基本检查信息右眼配镜处方轴位(OD)")
	@TableField(value = "jc_pjcf_cw_od")
	@Length(max = 10)
	private String jcPjcfCwOd;

	/** 基本检查信息左眼配镜处方轴位(OS) */
    @Comment(value="基本检查信息左眼配镜处方轴位(OS)")
	@TableField(value = "jc_pjcf_cw_os")
	@Length(max = 10)
	private String jcPjcfCwOs;

	/** 基本检查信息右眼配镜处方矫正视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼配镜处方矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_pjcf_jzsl_od_type")
	private Integer jcPjcfJzslOdType;

	/** 基本检查信息右眼配镜处方矫正视力(OD)编码（码表far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼配镜处方矫正视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_pjcf_jzsl_od")
	@Length(max = 2)
	private String jcPjcfJzslOd;

	/** 基本检查信息右眼配镜处方矫正视力(OD)名称 */
    @Comment(value="基本检查信息右眼配镜处方矫正视力(OD)名称")
	@TableField(value = "jc_pjcf_jzsl_od_name")
	@Length(max = 10)
	private String jcPjcfJzslOdName;

	/** 基本检查信息左眼配镜处方矫正视力(OS)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼配镜处方矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "jc_pjcf_jzsl_os_type")
	private Integer jcPjcfJzslOsType;
    
    /** 事件经过*/
    @Comment(value="事件经过")
	@TableField(value="proc_descr")
	@Length(max=2000) private String procDescr;
    
	public String getProcDescr() {
		return procDescr;
	}

	public void setProcDescr(String procDescr) {
		this.procDescr = procDescr;
	}

	public String getJcPjcfJzslOs() {
		return jcPjcfJzslOs;
	}

	public void setJcPjcfJzslOs(String jcPjcfJzslOs) {
		this.jcPjcfJzslOs = jcPjcfJzslOs;
	}

	public String getJcPjcfJzslOsName() {
		return jcPjcfJzslOsName;
	}

	public void setJcPjcfJzslOsName(String jcPjcfJzslOsName) {
		this.jcPjcfJzslOsName = jcPjcfJzslOsName;
	}

	public String getJcPjcfXjgOd() {
		return jcPjcfXjgOd;
	}

	public void setJcPjcfXjgOd(String jcPjcfXjgOd) {
		this.jcPjcfXjgOd = jcPjcfXjgOd;
	}

	public String getJcPjcfXjgOs() {
		return jcPjcfXjgOs;
	}

	public void setJcPjcfXjgOs(String jcPjcfXjgOs) {
		this.jcPjcfXjgOs = jcPjcfXjgOs;
	}

	public String getJcPjcfDctjOd() {
		return jcPjcfDctjOd;
	}

	public void setJcPjcfDctjOd(String jcPjcfDctjOd) {
		this.jcPjcfDctjOd = jcPjcfDctjOd;
	}

	public String getJcPjcfDctjOs() {
		return jcPjcfDctjOs;
	}

	public void setJcPjcfDctjOs(String jcPjcfDctjOs) {
		this.jcPjcfDctjOs = jcPjcfDctjOs;
	}

	public String getJcPjcsTgR() {
		return jcPjcsTgR;
	}

	public void setJcPjcsTgR(String jcPjcsTgR) {
		this.jcPjcsTgR = jcPjcsTgR;
	}

	public String getJcPjcsTgL() {
		return jcPjcsTgL;
	}

	public void setJcPjcsTgL(String jcPjcsTgL) {
		this.jcPjcsTgL = jcPjcsTgL;
	}

	public String getJcPjcsJplxR() {
		return jcPjcsJplxR;
	}

	public void setJcPjcsJplxR(String jcPjcsJplxR) {
		this.jcPjcsJplxR = jcPjcsJplxR;
	}

	public String getJcPjcsJplxL() {
		return jcPjcsJplxL;
	}

	public void setJcPjcsJplxL(String jcPjcsJplxL) {
		this.jcPjcsJplxL = jcPjcsJplxL;
	}

	public String getJcPjcsJpzjR() {
		return jcPjcsJpzjR;
	}

	public void setJcPjcsJpzjR(String jcPjcsJpzjR) {
		this.jcPjcsJpzjR = jcPjcsJpzjR;
	}

	public String getJcPjcsJpzjL() {
		return jcPjcsJpzjL;
	}

	public void setJcPjcsJpzjL(String jcPjcsJpzjL) {
		this.jcPjcsJpzjL = jcPjcsJpzjL;
	}

	public String getJcPjcsJjccR() {
		return jcPjcsJjccR;
	}

	public void setJcPjcsJjccR(String jcPjcsJjccR) {
		this.jcPjcsJjccR = jcPjcsJjccR;
	}

	public String getJcPjcsJjccL() {
		return jcPjcsJjccL;
	}

	public void setJcPjcsJjccL(String jcPjcsJjccL) {
		this.jcPjcsJjccL = jcPjcsJjccL;
	}

	public String getEventCause() {
		return eventCause;
	}

	public void setEventCause(String eventCause) {
		this.eventCause = eventCause;
	}

	public String getPatientAppeal() {
		return patientAppeal;
	}

	public void setPatientAppeal(String patientAppeal) {
		this.patientAppeal = patientAppeal;
	}

	public String getEventResult() {
		return eventResult;
	}

	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}

	public String getClYgQjOd() {
		return clYgQjOd;
	}

	public void setClYgQjOd(String clYgQjOd) {
		this.clYgQjOd = clYgQjOd;
	}

	public String getClYgQjOs() {
		return clYgQjOs;
	}

	public void setClYgQjOs(String clYgQjOs) {
		this.clYgQjOs = clYgQjOs;
	}

	public String getClYgZjOd() {
		return clYgZjOd;
	}

	public void setClYgZjOd(String clYgZjOd) {
		this.clYgZjOd = clYgZjOd;
	}

	public String getClYgZjOs() {
		return clYgZjOs;
	}

	public void setClYgZjOs(String clYgZjOs) {
		this.clYgZjOs = clYgZjOs;
	}

	public String getClYgCwOd() {
		return clYgCwOd;
	}

	public void setClYgCwOd(String clYgCwOd) {
		this.clYgCwOd = clYgCwOd;
	}

	public String getClYgCwOs() {
		return clYgCwOs;
	}

	public void setClYgCwOs(String clYgCwOs) {
		this.clYgCwOs = clYgCwOs;
	}

	public Integer getClYgJzslOdType() {
		return clYgJzslOdType;
	}

	public void setClYgJzslOdType(Integer clYgJzslOdType) {
		this.clYgJzslOdType = clYgJzslOdType;
	}

	public String getClYgJzslOd() {
		return clYgJzslOd;
	}

	public void setClYgJzslOd(String clYgJzslOd) {
		this.clYgJzslOd = clYgJzslOd;
	}

	public String getClYgJzslOdName() {
		return clYgJzslOdName;
	}

	public void setClYgJzslOdName(String clYgJzslOdName) {
		this.clYgJzslOdName = clYgJzslOdName;
	}

	public Integer getClYgJzslOsType() {
		return clYgJzslOsType;
	}

	public void setClYgJzslOsType(Integer clYgJzslOsType) {
		this.clYgJzslOsType = clYgJzslOsType;
	}

	public String getClYgJzslOs() {
		return clYgJzslOs;
	}

	public void setClYgJzslOs(String clYgJzslOs) {
		this.clYgJzslOs = clYgJzslOs;
	}

	public String getClYgJzslOsName() {
		return clYgJzslOsName;
	}

	public void setClYgJzslOsName(String clYgJzslOsName) {
		this.clYgJzslOsName = clYgJzslOsName;
	}

	public String getClPjcfQjOd() {
		return clPjcfQjOd;
	}

	public void setClPjcfQjOd(String clPjcfQjOd) {
		this.clPjcfQjOd = clPjcfQjOd;
	}

	public String getClPjcfQjOs() {
		return clPjcfQjOs;
	}

	public void setClPjcfQjOs(String clPjcfQjOs) {
		this.clPjcfQjOs = clPjcfQjOs;
	}

	public String getClPjcfZjOd() {
		return clPjcfZjOd;
	}

	public void setClPjcfZjOd(String clPjcfZjOd) {
		this.clPjcfZjOd = clPjcfZjOd;
	}

	public String getClPjcfZjOs() {
		return clPjcfZjOs;
	}

	public void setClPjcfZjOs(String clPjcfZjOs) {
		this.clPjcfZjOs = clPjcfZjOs;
	}

	public String getClPjcfCwOd() {
		return clPjcfCwOd;
	}

	public void setClPjcfCwOd(String clPjcfCwOd) {
		this.clPjcfCwOd = clPjcfCwOd;
	}

	public String getClPjcfCwOs() {
		return clPjcfCwOs;
	}

	public void setClPjcfCwOs(String clPjcfCwOs) {
		this.clPjcfCwOs = clPjcfCwOs;
	}

	public Integer getClPjcfJzslOdType() {
		return clPjcfJzslOdType;
	}

	public void setClPjcfJzslOdType(Integer clPjcfJzslOdType) {
		this.clPjcfJzslOdType = clPjcfJzslOdType;
	}

	public String getClPjcfJzslOd() {
		return clPjcfJzslOd;
	}

	public void setClPjcfJzslOd(String clPjcfJzslOd) {
		this.clPjcfJzslOd = clPjcfJzslOd;
	}

	public String getClPjcfJzslOdName() {
		return clPjcfJzslOdName;
	}

	public void setClPjcfJzslOdName(String clPjcfJzslOdName) {
		this.clPjcfJzslOdName = clPjcfJzslOdName;
	}

	public Integer getClPjcfJzslOsType() {
		return clPjcfJzslOsType;
	}

	public void setClPjcfJzslOsType(Integer clPjcfJzslOsType) {
		this.clPjcfJzslOsType = clPjcfJzslOsType;
	}

	public String getClPjcfJzslOs() {
		return clPjcfJzslOs;
	}

	public void setClPjcfJzslOs(String clPjcfJzslOs) {
		this.clPjcfJzslOs = clPjcfJzslOs;
	}

	public String getClPjcfJzslOsName() {
		return clPjcfJzslOsName;
	}

	public void setClPjcfJzslOsName(String clPjcfJzslOsName) {
		this.clPjcfJzslOsName = clPjcfJzslOsName;
	}

	public String getClPjcfXjgOd() {
		return clPjcfXjgOd;
	}

	public void setClPjcfXjgOd(String clPjcfXjgOd) {
		this.clPjcfXjgOd = clPjcfXjgOd;
	}

	public String getClPjcfXjgOs() {
		return clPjcfXjgOs;
	}

	public void setClPjcfXjgOs(String clPjcfXjgOs) {
		this.clPjcfXjgOs = clPjcfXjgOs;
	}

	public String getClPjcfDctjOd() {
		return clPjcfDctjOd;
	}

	public void setClPjcfDctjOd(String clPjcfDctjOd) {
		this.clPjcfDctjOd = clPjcfDctjOd;
	}

	public String getClPjcfDctjOs() {
		return clPjcfDctjOs;
	}

	public void setClPjcfDctjOs(String clPjcfDctjOs) {
		this.clPjcfDctjOs = clPjcfDctjOs;
	}

	public String getClPjcsTgR() {
		return clPjcsTgR;
	}

	public void setClPjcsTgR(String clPjcsTgR) {
		this.clPjcsTgR = clPjcsTgR;
	}

	public String getClPjcsTgL() {
		return clPjcsTgL;
	}

	public void setClPjcsTgL(String clPjcsTgL) {
		this.clPjcsTgL = clPjcsTgL;
	}

	public String getClPjcsJplxR() {
		return clPjcsJplxR;
	}

	public void setClPjcsJplxR(String clPjcsJplxR) {
		this.clPjcsJplxR = clPjcsJplxR;
	}

	public String getClPjcsJplxL() {
		return clPjcsJplxL;
	}

	public void setClPjcsJplxL(String clPjcsJplxL) {
		this.clPjcsJplxL = clPjcsJplxL;
	}

	public String getClPjcsJpzjR() {
		return clPjcsJpzjR;
	}

	public void setClPjcsJpzjR(String clPjcsJpzjR) {
		this.clPjcsJpzjR = clPjcsJpzjR;
	}

	public String getClPjcsJpzjL() {
		return clPjcsJpzjL;
	}

	public void setClPjcsJpzjL(String clPjcsJpzjL) {
		this.clPjcsJpzjL = clPjcsJpzjL;
	}

	public String getClPjcsJjccR() {
		return clPjcsJjccR;
	}

	public void setClPjcsJjccR(String clPjcsJjccR) {
		this.clPjcsJjccR = clPjcsJjccR;
	}

	public String getClPjcsJjccL() {
		return clPjcsJjccL;
	}

	public void setClPjcsJjccL(String clPjcsJjccL) {
		this.clPjcsJjccL = clPjcsJjccL;
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

	public String getJcYgQjOd() {
		return jcYgQjOd;
	}

	public void setJcYgQjOd(String jcYgQjOd) {
		this.jcYgQjOd = jcYgQjOd;
	}

	public String getJcYgQjOs() {
		return jcYgQjOs;
	}

	public void setJcYgQjOs(String jcYgQjOs) {
		this.jcYgQjOs = jcYgQjOs;
	}

	public String getJcYgZjOd() {
		return jcYgZjOd;
	}

	public void setJcYgZjOd(String jcYgZjOd) {
		this.jcYgZjOd = jcYgZjOd;
	}

	public String getJcYgZjOs() {
		return jcYgZjOs;
	}

	public void setJcYgZjOs(String jcYgZjOs) {
		this.jcYgZjOs = jcYgZjOs;
	}

	public String getJcYgCwOd() {
		return jcYgCwOd;
	}

	public void setJcYgCwOd(String jcYgCwOd) {
		this.jcYgCwOd = jcYgCwOd;
	}

	public String getJcYgCwOs() {
		return jcYgCwOs;
	}

	public void setJcYgCwOs(String jcYgCwOs) {
		this.jcYgCwOs = jcYgCwOs;
	}

	public Integer getJcYgJzslOdType() {
		return jcYgJzslOdType;
	}

	public void setJcYgJzslOdType(Integer jcYgJzslOdType) {
		this.jcYgJzslOdType = jcYgJzslOdType;
	}

	public String getJcYgJzslOd() {
		return jcYgJzslOd;
	}

	public void setJcYgJzslOd(String jcYgJzslOd) {
		this.jcYgJzslOd = jcYgJzslOd;
	}

	public String getJcYgJzslOdName() {
		return jcYgJzslOdName;
	}

	public void setJcYgJzslOdName(String jcYgJzslOdName) {
		this.jcYgJzslOdName = jcYgJzslOdName;
	}

	public Integer getJcYgJzslOsType() {
		return jcYgJzslOsType;
	}

	public void setJcYgJzslOsType(Integer jcYgJzslOsType) {
		this.jcYgJzslOsType = jcYgJzslOsType;
	}

	public String getJcYgJzslOs() {
		return jcYgJzslOs;
	}

	public void setJcYgJzslOs(String jcYgJzslOs) {
		this.jcYgJzslOs = jcYgJzslOs;
	}

	public String getJcYgJzslOsName() {
		return jcYgJzslOsName;
	}

	public void setJcYgJzslOsName(String jcYgJzslOsName) {
		this.jcYgJzslOsName = jcYgJzslOsName;
	}

	public String getJcJmpkzOd() {
		return jcJmpkzOd;
	}

	public void setJcJmpkzOd(String jcJmpkzOd) {
		this.jcJmpkzOd = jcJmpkzOd;
	}

	public String getJcJmpkzwOd() {
		return jcJmpkzwOd;
	}

	public void setJcJmpkzwOd(String jcJmpkzwOd) {
		this.jcJmpkzwOd = jcJmpkzwOd;
	}

	public String getJcJmpkzOs() {
		return jcJmpkzOs;
	}

	public void setJcJmpkzOs(String jcJmpkzOs) {
		this.jcJmpkzOs = jcJmpkzOs;
	}

	public String getJcJmpkzwOs() {
		return jcJmpkzwOs;
	}

	public void setJcJmpkzwOs(String jcJmpkzwOs) {
		this.jcJmpkzwOs = jcJmpkzwOs;
	}

	public String getJcPjcfQjOd() {
		return jcPjcfQjOd;
	}

	public void setJcPjcfQjOd(String jcPjcfQjOd) {
		this.jcPjcfQjOd = jcPjcfQjOd;
	}

	public String getJcPjcfQjOs() {
		return jcPjcfQjOs;
	}

	public void setJcPjcfQjOs(String jcPjcfQjOs) {
		this.jcPjcfQjOs = jcPjcfQjOs;
	}

	public String getJcPjcfZjOd() {
		return jcPjcfZjOd;
	}

	public void setJcPjcfZjOd(String jcPjcfZjOd) {
		this.jcPjcfZjOd = jcPjcfZjOd;
	}

	public String getJcPjcfZjOs() {
		return jcPjcfZjOs;
	}

	public void setJcPjcfZjOs(String jcPjcfZjOs) {
		this.jcPjcfZjOs = jcPjcfZjOs;
	}

	public String getJcPjcfCwOd() {
		return jcPjcfCwOd;
	}

	public void setJcPjcfCwOd(String jcPjcfCwOd) {
		this.jcPjcfCwOd = jcPjcfCwOd;
	}

	public String getJcPjcfCwOs() {
		return jcPjcfCwOs;
	}

	public void setJcPjcfCwOs(String jcPjcfCwOs) {
		this.jcPjcfCwOs = jcPjcfCwOs;
	}

	public Integer getJcPjcfJzslOdType() {
		return jcPjcfJzslOdType;
	}

	public void setJcPjcfJzslOdType(Integer jcPjcfJzslOdType) {
		this.jcPjcfJzslOdType = jcPjcfJzslOdType;
	}

	public String getJcPjcfJzslOd() {
		return jcPjcfJzslOd;
	}

	public void setJcPjcfJzslOd(String jcPjcfJzslOd) {
		this.jcPjcfJzslOd = jcPjcfJzslOd;
	}

	public String getJcPjcfJzslOdName() {
		return jcPjcfJzslOdName;
	}

	public void setJcPjcfJzslOdName(String jcPjcfJzslOdName) {
		this.jcPjcfJzslOdName = jcPjcfJzslOdName;
	}

	public Integer getJcPjcfJzslOsType() {
		return jcPjcfJzslOsType;
	}

	public void setJcPjcfJzslOsType(Integer jcPjcfJzslOsType) {
		this.jcPjcfJzslOsType = jcPjcfJzslOsType;
	}
}
