package com.aier.cloud.biz.service.biz.amcs.adverse.entity;
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

import javax.validation.constraints.NotBlank;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeDictName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_AE_VISUAL_TRAIN 视觉训练不良反应上报表单
 * 
 * @author 爱尔眼科
 * @since 2022-10-09 11:00:07
 */
@TableName("T_AE_VISUAL_TRAIN")
public class AeVisualTrain extends BaseEntity<AeVisualTrain> {

	private static final long serialVersionUID = -2969754470855673338L;

	/** 基本检查信息双眼视功能NRA */
    @Comment(value="基本检查信息双眼视功能NRA")
	@TableField(value = "jc_sysgn_nra")
	@Length(max = 10)
	private String jcSysgnNra;

	/** 基本检查信息双眼视功能BCC */
    @Comment(value="基本检查信息双眼视功能BCC")
	@TableField(value = "jc_sysgn_bcc")
	@Length(max = 10)
	private String jcSysgnBcc;

	/** 基本检查信息双眼视功能PRA */
    @Comment(value="基本检查信息双眼视功能PRA")
	@TableField(value = "jc_sysgn_pra")
	@Length(max = 10)
	private String jcSysgnPra;

	/** 基本检查信息双眼视功能AC/A */
    @Comment(value="基本检查信息双眼视功能AC/A")
	@TableField(value = "jc_sysgn_aca")
	@Length(max = 10)
	private String jcSysgnAca;

	/** 基本检查信息双眼视功能右眼调节幅度(OD) */
    @Comment(value="基本检查信息双眼视功能右眼调节幅度(OD)")
	@TableField(value = "jc_djfd_od")
	@Length(max = 10)
	private String jcDjfdOd;

	/** 基本检查信息双眼视功能左眼调节幅度(OS) */
    @Comment(value="基本检查信息双眼视功能左眼调节幅度(OS)")
	@TableField(value = "jc_djfd_os")
	@Length(max = 10)
	private String jcDjfdOs;

	/** 基本检查信息双眼视功能双眼调节幅度(OU) */
    @Comment(value="基本检查信息双眼视功能双眼调节幅度(OU)")
	@TableField(value = "jc_djfd_ou")
	@Length(max = 10)
	private String jcDjfdOu;

	/** 基本检查信息双眼视功能右眼调节灵敏度(OD) */
    @Comment(value="基本检查信息双眼视功能右眼调节灵敏度(OD)")
	@TableField(value = "jc_djlmd_od")
	@Length(max = 10)
	private String jcDjlmdOd;

	/** 基本检查信息双眼视功能左眼调节灵敏度(OS) */
    @Comment(value="基本检查信息双眼视功能左眼调节灵敏度(OS)")
	@TableField(value = "jc_djlmd_os")
	@Length(max = 10)
	private String jcDjlmdOs;

	/** 基本检查信息双眼视功能双眼调节灵敏度(OU) */
    @Comment(value="基本检查信息双眼视功能双眼调节灵敏度(OU)")
	@TableField(value = "jc_djlmd_ou")
	@Length(max = 10)
	private String jcDjlmdOu;

	/** 基本检查信息双眼视功能Worth4dots33cm */
    @Comment(value="基本检查信息双眼视功能Worth4dots33cm")
	@TableField(value = "jc_w4d_33cm")
	@Length(max = 10)
	private String jcW4d33cm;

	/** 基本检查信息双眼视功能Worth4dots1m */
    @Comment(value="基本检查信息双眼视功能Worth4dots1m")
	@TableField(value = "jc_w4d_1m")
	@Length(max = 10)
	private String jcW4d1m;

	/** 基本检查信息双眼视功能Worth4dots3m */
    @Comment(value="基本检查信息双眼视功能Worth4dots3m")
	@TableField(value = "jc_w4d_3m")
	@Length(max = 10)
	private String jcW4d3m;

	/** 基本检查信息双眼视功能Worth4dots集合近点 */
    @Comment(value="基本检查信息双眼视功能Worth4dots集合近点")
	@TableField(value = "jc_w4d_jhjd")
	@Length(max = 10)
	private String jcW4dJhjd;

	/** 基本检查信息双眼视功能Worth4dots立体视 */
    @Comment(value="基本检查信息双眼视功能Worth4dots立体视")
	@TableField(value = "jc_w4d_lts")
	@Length(max = 10)
	private String jcW4dLts;

	/** 基本检查信息双眼视功能同视机检查一级主观斜视角 */
    @Comment(value="基本检查信息双眼视功能同视机检查一级主观斜视角")
	@TableField(value = "jc_tsjjc_yj_zgxsj")
	@Length(max = 10)
	private String jcTsjjcYjZgxsj;

	/** 基本检查信息双眼视功能同视机检查一级客观斜视角REF */
    @Comment(value="基本检查信息双眼视功能同视机检查一级客观斜视角REF")
	@TableField(value = "jc_tsjjc_yj_kgxsjref")
	@Length(max = 10)
	private String jcTsjjcYjKgxsjref;

	/** 基本检查信息双眼视功能同视机检查一级客观斜视角LEF */
    @Comment(value="基本检查信息双眼视功能同视机检查一级客观斜视角LEF")
	@TableField(value = "jc_tsjjc_yj_kgxsjlef")
	@Length(max = 10)
	private String jcTsjjcYjKgxsjlef;

	/** 基本检查信息双眼视功能同视机检查二级融合点 */
    @Comment(value="基本检查信息双眼视功能同视机检查二级融合点")
	@TableField(value = "jc_tsjjc_ej_rhd")
	@Length(max = 10)
	private String jcTsjjcEjRhd;

	/** 基本检查信息双眼视功能同视机检查二级融像范围 */
    @Comment(value="基本检查信息双眼视功能同视机检查二级融像范围")
	@TableField(value = "jc_tsjjc_ej_rxfw")
	@Length(max = 10)
	private String jcTsjjcEjRxfw;

	/** 基本检查信息双眼视功能同视机检查三级 */
    @Comment(value="基本检查信息双眼视功能同视机检查三级")
	@TableField(value = "jc_tsjjc_sj")
	@Length(max = 10)
	private String jcTsjjcSj;

	/** 目前情况患者已训练次数 */
    @Comment(value="目前情况患者已训练次数")
	@TableField(value = "mqqk_yxlcs")
	private Integer mqqkYxlcs;

	/** 目前情况患者剩余训练次数 */
    @Comment(value="目前情况患者剩余训练次数")
	@TableField(value = "mqqk_syxlcs")
	private Integer mqqkSyxlcs;

	/** 目前情况患者训练频率 （码表：train_frequency）*/
    @Comment(value="目前情况患者训练频率 （码表：train_frequency）")
	@TableField(value = "mqqk_xlpl")
	private Integer mqqkXlpl;
	
	/** 目前情况患者训练频率名称 （码表：train_frequency）*/
    @Comment(value="目前情况患者训练频率名称 （码表：train_frequency）")
	@TableField(value = "mqqk_xlpl_name")
	@AeDictName(type = "train_frequency", from = "mqqkXlpl")
	private String mqqkXlplName;

	/** 目前情况右眼裸眼远视力(OD)类型(1数值2描述） */
    @Comment(value="目前情况右眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value = "mqqk_lyy_vod_type")
	private Integer mqqkLyyVodType;

	/** 目前情况右眼裸眼远视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况右眼裸眼远视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_lyy_vod")
	@Length(max = 2)
	private String mqqkLyyVod;

	/** 目前情况右眼裸眼远视力(OD)名称 */
    @Comment(value="目前情况右眼裸眼远视力(OD)名称")
	@TableField(value = "mqqk_lyy_vod_name")
	@Length(max = 10)
	private String mqqkLyyVodName;

	/** 目前情况左眼裸眼远视力(OS)类型(1数值2描述） */
    @Comment(value="目前情况左眼裸眼远视力(OS)类型(1数值2描述）")
	@TableField(value = "mqqk_lyy_vos_type")
	private Integer mqqkLyyVosType;

	/** 目前情况左眼裸眼远视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况左眼裸眼远视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_lyy_vos")
	@Length(max = 2)
	private String mqqkLyyVos;

	/** 目前情况左眼裸眼远视力(OS)名称 */
    @Comment(value="目前情况左眼裸眼远视力(OS)名称")
	@TableField(value = "mqqk_lyy_vos_name")
	@Length(max = 10)
	private String mqqkLyyVosName;

	/** 目前情况右眼裸眼近视力(OD)类型(1数值2描述） */
    @Comment(value="目前情况右眼裸眼近视力(OD)类型(1数值2描述）")
	@TableField(value = "mqqk_lyj_vod_type")
	private Integer mqqkLyjVodType;

	/** 目前情况右眼裸眼近视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况右眼裸眼近视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_lyj_vod")
	@Length(max = 2)
	private String mqqkLyjVod;

	/** 目前情况右眼裸眼近视力(OD)名称 */
    @Comment(value="目前情况右眼裸眼近视力(OD)名称")
	@TableField(value = "mqqk_lyj_vod_name")
	@Length(max = 10)
	private String mqqkLyjVodName;

	/** 目前情况左眼裸眼近视力(OS)类型(1数值2描述） */
    @Comment(value="目前情况左眼裸眼近视力(OS)类型(1数值2描述）")
	@TableField(value = "mqqk_lyj_vos_type")
	private Integer mqqkLyjVosType;

	/** 目前情况左眼裸眼近视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况左眼裸眼近视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_lyj_vos")
	@Length(max = 2)
	private String mqqkLyjVos;

	/** 目前情况左眼裸眼近视力(OS)名称 */
    @Comment(value="目前情况左眼裸眼近视力(OS)名称")
	@TableField(value = "mqqk_lyj_vos_name")
	@Length(max = 10)
	private String mqqkLyjVosName;

	/** 目前情况右眼屈光矫正框架镜球镜视力(OD) */
    @Comment(value="目前情况右眼屈光矫正框架镜球镜视力(OD)")
	@TableField(value = "mqqk_qgjz_kjj_qj_vod")
	@Length(max = 10)
	private String mqqkQgjzKjjQjVod;

	/** 目前情况左眼屈光矫正框架镜球镜视力(OS) */
    @Comment(value="目前情况左眼屈光矫正框架镜球镜视力(OS)")
	@TableField(value = "mqqk_qgjz_kjj_qj_vos")
	@Length(max = 10)
	private String mqqkQgjzKjjQjVos;

	/** 目前情况右眼屈光矫正框架镜柱镜视力(OD) */
    @Comment(value="目前情况右眼屈光矫正框架镜柱镜视力(OD)")
	@TableField(value = "mqqk_qgjz_kjj_zj_vod")
	@Length(max = 10)
	private String mqqkQgjzKjjZjVod;

	/** 目前情况左眼屈光矫正框架镜柱镜视力(OS) */
    @Comment(value="目前情况左眼屈光矫正框架镜柱镜视力(OS)")
	@TableField(value = "mqqk_qgjz_kjj_zj_vos")
	@Length(max = 10)
	private String mqqkQgjzKjjZjVos;

	/** 目前情况右眼屈光矫正框架镜轴位视力(OD) */
    @Comment(value="目前情况右眼屈光矫正框架镜轴位视力(OD)")
	@TableField(value = "mqqk_qgjz_kjj_cw_vod")
	@Length(max = 10)
	private String mqqkQgjzKjjCwVod;

	/** 目前情况左眼屈光矫正框架镜轴位视力(OS) */
    @Comment(value="目前情况左眼屈光矫正框架镜轴位视力(OS)")
	@TableField(value = "mqqk_qgjz_kjj_cw_vos")
	@Length(max = 10)
	private String mqqkQgjzKjjCwVos;

	/** 目前情况右眼屈光矫正框架镜矫正视力(OD)类型(1数值2描述） */
    @Comment(value="目前情况右眼屈光矫正框架镜矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vod_type")
	private Integer mqqkQgjzKjjJzslVodType;

	/** 目前情况右眼屈光矫正框架镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况右眼屈光矫正框架镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vod")
	@Length(max = 2)
	private String mqqkQgjzKjjJzslVod;

	/** 目前情况右眼屈光矫正框架镜矫正视力(OD)名称 */
    @Comment(value="目前情况右眼屈光矫正框架镜矫正视力(OD)名称")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vod_name")
	@Length(max = 10)
	private String mqqkQgjzKjjJzslVodName;

	/** 目前情况左眼屈光矫正框架镜矫正视力(OS)类型(1数值2描述） */
    @Comment(value="目前情况左眼屈光矫正框架镜矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vos_type")
	@Length(max = 1)
	private String mqqkQgjzKjjJzslVosType;

	/** 目前情况左眼屈光矫正框架镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="目前情况左眼屈光矫正框架镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vos")
	@Length(max = 2)
	private String mqqkQgjzKjjJzslVos;

	/** 目前情况左眼屈光矫正框架镜矫正视力(OS)名称 */
    @Comment(value="目前情况左眼屈光矫正框架镜矫正视力(OS)名称")
	@TableField(value = "mqqk_qgjz_kjj_jzsl_vos_name")
	@Length(max = 10)
	private String mqqkQgjzKjjJzslVosName;

	/** 目前情况双眼视功能视远斜视度 */
    @Comment(value="目前情况双眼视功能视远斜视度")
	@TableField(value = "mqqk_sysgn_syxsd")
	@Length(max = 10)
	private String mqqkSysgnSyxsd;

	/** 目前情况双眼视功能视近斜视度 */
    @Comment(value="目前情况双眼视功能视近斜视度")
	@TableField(value = "mqqk_sysgn_sjxsd")
	@Length(max = 10)
	private String mqqkSysgnSjxsd;

	/** 目前情况双眼视功能NRA */
    @Comment(value="目前情况双眼视功能NRA")
	@TableField(value = "mqqk_sysgn_nra")
	@Length(max = 10)
	private String mqqkSysgnNra;

	/** 目前情况双眼视功能BCC */
    @Comment(value="目前情况双眼视功能BCC")
	@TableField(value = "mqqk_sysgn_bcc")
	@Length(max = 10)
	private String mqqkSysgnBcc;

	/** 目前情况双眼视功能PRA */
    @Comment(value="目前情况双眼视功能PRA")
	@TableField(value = "mqqk_sysgn_pra")
	@Length(max = 10)
	private String mqqkSysgnPra;

	/** 目前情况双眼视功能AC/A */
    @Comment(value="目前情况双眼视功能AC/A")
	@TableField(value = "mqqk_sysgn_aca")
	@Length(max = 10)
	private String mqqkSysgnAca;

	/** 目前情况双眼视功能右眼调节幅度(OD) */
    @Comment(value="目前情况双眼视功能右眼调节幅度(OD)")
	@TableField(value = "mqqk_djfd_od")
	@Length(max = 10)
	private String mqqkDjfdOd;

	/** 目前情况双眼视功能左眼调节幅度(OS) */
    @Comment(value="目前情况双眼视功能左眼调节幅度(OS)")
	@TableField(value = "mqqk_djfd_os")
	@Length(max = 10)
	private String mqqkDjfdOs;

	/** 目前情况双眼视功能双眼调节幅度(OU) */
    @Comment(value="目前情况双眼视功能双眼调节幅度(OU)")
	@TableField(value = "mqqk_djfd_ou")
	@Length(max = 10)
	private String mqqkDjfdOu;

	/** 目前情况双眼视功能右眼调节灵敏度(OD) */
    @Comment(value="目前情况双眼视功能右眼调节灵敏度(OD)")
	@TableField(value = "mqqk_djlmd_od")
	@Length(max = 10)
	private String mqqkDjlmdOd;

	/** 目前情况双眼视功能左眼调节灵敏度(OS) */
    @Comment(value="目前情况双眼视功能左眼调节灵敏度(OS)")
	@TableField(value = "mqqk_djlmd_os")
	@Length(max = 10)
	private String mqqkDjlmdOs;

	/** 目前情况双眼视功能双眼调节灵敏度(OU) */
    @Comment(value="目前情况双眼视功能双眼调节灵敏度(OU)")
	@TableField(value = "mqqk_djlmd_ou")
	@Length(max = 10)
	private String mqqkDjlmdOu;

	/** 目前情况双眼视功能Worth4dots33cm */
    @Comment(value="目前情况双眼视功能Worth4dots33cm")
	@TableField(value = "mqqk_w4d_33cm")
	@Length(max = 10)
	private String mqqkW4d33cm;

	/** 目前情况双眼视功能Worth4dots1m */
    @Comment(value="目前情况双眼视功能Worth4dots1m")
	@TableField(value = "mqqk_w4d_1m")
	@Length(max = 10)
	private String mqqkW4d1m;

	/** 目前情况双眼视功能Worth4dots3m */
    @Comment(value="目前情况双眼视功能Worth4dots3m")
	@TableField(value = "mqqk_w4d_3m")
	@Length(max = 10)
	private String mqqkW4d3m;

	/** 目前情况双眼视功能Worth4dots集合近点 */
    @Comment(value="目前情况双眼视功能Worth4dots集合近点")
	@TableField(value = "mqqk_w4d_jhjd")
	@Length(max = 10)
	private String mqqkW4dJhjd;

	/** 目前情况双眼视功能Worth4dots立体视 */
    @Comment(value="目前情况双眼视功能Worth4dots立体视")
	@TableField(value = "mqqk_w4d_lts")
	@Length(max = 10)
	private String mqqkW4dLts;

	/** 目前情况双眼视功能同视机检查一级主观斜视角 */
    @Comment(value="目前情况双眼视功能同视机检查一级主观斜视角")
	@TableField(value = "mqqk_tsjjc_yj_zgxsj")
	@Length(max = 10)
	private String mqqkTsjjcYjZgxsj;

	/** 目前情况双眼视功能同视机检查一级客观斜视角REF */
    @Comment(value="目前情况双眼视功能同视机检查一级客观斜视角REF")
	@TableField(value = "mqqk_tsjjc_yj_kgxsjref")
	@Length(max = 10)
	private String mqqkTsjjcYjKgxsjref;

	/** 目前情况双眼视功能同视机检查一级客观斜视角LEF */
    @Comment(value="目前情况双眼视功能同视机检查一级客观斜视角LEF")
	@TableField(value = "mqqk_tsjjc_yj_kgxsjlef")
	@Length(max = 10)
	private String mqqkTsjjcYjKgxsjlef;

	/** 目前情况双眼视功能同视机检查二级融合点 */
    @Comment(value="目前情况双眼视功能同视机检查二级融合点")
	@TableField(value = "mqqk_tsjjc_ej_rhd")
	@Length(max = 10)
	private String mqqkTsjjcEjRhd;

	/** 目前情况双眼视功能同视机检查二级融像范围 */
    @Comment(value="目前情况双眼视功能同视机检查二级融像范围")
	@TableField(value = "mqqk_tsjjc_ej_rxfw")
	@Length(max = 10)
	private String mqqkTsjjcEjRxfw;

	/** 目前情况双眼视功能同视机检查三级 */
    @Comment(value="目前情况双眼视功能同视机检查三级")
	@TableField(value = "mqqk_tsjjc_sj")
	@Length(max = 10)
	private String mqqkTsjjcSj;

	/** 患者及家属诉求 */
    @Comment(value="患者及家属诉求")
	@TableField(value = "patient_appeals")
	@Length(max = 500)
	private String patientAppeals;

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

	/** 患者训练建档日期 */
    @Comment(value="患者训练建档日期")
	@TableField(value = "training_file_date")
	private java.util.Date trainingFileDate;

	/** 诊断 （码表：diag_type）*/
    @Comment(value="诊断 （码表：diag_type）")
	@TableField(value = "diag")
	private Integer diag;
	
	/** 诊断名称 */
    @Comment(value="诊断名称")
	@TableField(value = "diag_name")
	@AeDictName(type = "diag_type", from = "diag")
	private String diagName;

	@TableField(exist = false)
	private String payDiagnose;
	
	
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

	public String getPayDiagnose() {
		return diagName;
	}
	/** 缴费次数 */
    @Comment(value="缴费次数")
	@TableField(value = "pay_times")
	private Integer payTimes;

	/** 缴费费用 */
    @Comment(value="缴费费用")
	@TableField(value = "pay_fee")
	private java.math.BigDecimal payFee;

	/** 最后一次训练时间 */
    @Comment(value="最后一次训练时间")
	@TableField(value = "last_training_date")
	private java.util.Date lastTrainingDate;

	/** 基本检查信息右眼裸眼远视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_lyy_vod_type")
	private Integer jcLyyVodType;

	/** 基本检查信息右眼裸眼远视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_lyy_vod")
	@Length(max = 2)
	private String jcLyyVod;

	/** 基本检查信息右眼裸眼远视力(OD)名称 */
    @Comment(value="基本检查信息右眼裸眼远视力(OD)名称")
	@TableField(value = "jc_lyy_vod_name")
	@Length(max = 10)
	private String jcLyyVodName;

	/** 基本检查信息左眼裸眼远视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_lyy_vos_type")
	private Integer jcLyyVosType;

	/** 基本检查信息左眼裸眼远视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼裸眼远视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_lyy_vos")
	@Length(max = 2)
	private String jcLyyVos;

	/** 基本检查信息左眼裸眼远视力(OD)名称 */
    @Comment(value="基本检查信息左眼裸眼远视力(OD)名称")
	@TableField(value = "jc_lyy_vos_name")
	@Length(max = 10)
	private String jcLyyVosName;

	/** 基本检查信息右眼裸眼近视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼裸眼近视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_lyj_vod_type")
	private Integer jcLyjVodType;

	/** 基本检查信息右眼裸眼近视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼裸眼近视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_lyj_vod")
	@Length(max = 2)
	private String jcLyjVod;

	/** 基本检查信息右眼裸眼近视力(OD)名称 */
    @Comment(value="基本检查信息右眼裸眼近视力(OD)名称")
	@TableField(value = "jc_lyj_vod_name")
	@Length(max = 10)
	private String jcLyjVodName;

	/** 基本检查信息左眼裸眼近视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼裸眼近视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_lyj_vos_type")
	private Integer jcLyjVosType;

	/** 基本检查信息左眼裸眼近视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼裸眼近视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_lyj_vos")
	@Length(max = 2)
	private String jcLyjVos;

	/** 基本检查信息左眼裸眼近视力(OD)名称 */
    @Comment(value="基本检查信息左眼裸眼近视力(OD)名称")
	@TableField(value = "jc_lyj_vos_name")
	@Length(max = 10)
	private String jcLyjVosName;

	/** 基本检查信息右眼屈光矫正框架镜球镜视力(OD) */
    @Comment(value="基本检查信息右眼屈光矫正框架镜球镜视力(OD)")
	@TableField(value = "jc_qgjz_kjj_qj_vod")
	@Length(max = 10)
	private String jcQgjzKjjQjVod;

	/** 基本检查信息左眼屈光矫正框架镜球镜视力(OS) */
    @Comment(value="基本检查信息左眼屈光矫正框架镜球镜视力(OS)")
	@TableField(value = "jc_qgjz_kjj_qj_vos")
	@Length(max = 10)
	private String jcQgjzKjjQjVos;

	/** 基本检查信息右眼屈光矫正框架镜柱镜视力(OD) */
    @Comment(value="基本检查信息右眼屈光矫正框架镜柱镜视力(OD)")
	@TableField(value = "jc_qgjz_kjj_zj_vod")
	@Length(max = 10)
	private String jcQgjzKjjZjVod;

	/** 基本检查信息左眼屈光矫正框架镜柱镜视力(OS) */
    @Comment(value="基本检查信息左眼屈光矫正框架镜柱镜视力(OS)")
	@TableField(value = "jc_qgjz_kjj_zj_vos")
	@Length(max = 10)
	private String jcQgjzKjjZjVos;

	/** 基本检查信息右眼屈光矫正框架镜轴位视力(OD) */
    @Comment(value="基本检查信息右眼屈光矫正框架镜轴位视力(OD)")
	@TableField(value = "jc_qgjz_kjj_cw_vod")
	@Length(max = 10)
	private String jcQgjzKjjCwVod;

	/** 基本检查信息左眼屈光矫正框架镜轴位视力(OS) */
    @Comment(value="基本检查信息左眼屈光矫正框架镜轴位视力(OS)")
	@TableField(value = "jc_qgjz_kjj_cw_vos")
	@Length(max = 10)
	private String jcQgjzKjjCwVos;

	/** 基本检查信息右眼屈光矫正框架镜矫正视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼屈光矫正框架镜矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_qgjz_kjj_jzsl_vod_type")
	private Integer jcQgjzKjjJzslVodType;

	/** 基本检查信息右眼屈光矫正框架镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼屈光矫正框架镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_qgjz_kjj_jzsl_vod")
	@Length(max = 2)
	private String jcQgjzKjjJzslVod;

	/** 基本检查信息右眼屈光矫正框架镜矫正视力(OD)名称 */
    @Comment(value="基本检查信息右眼屈光矫正框架镜矫正视力(OD)名称")
	@TableField(value = "jc_qgjz_kjj_jzsl_vod_name")
	@Length(max = 10)
	private String jcQgjzKjjJzslVodName;

	/** 基本检查信息左眼屈光矫正框架镜矫正视力(OS)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼屈光矫正框架镜矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "jc_qgjz_kjj_jzsl_vos_type")
	private Integer jcQgjzKjjJzslVosType;

	/** 基本检查信息左眼屈光矫正框架镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼屈光矫正框架镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_qgjz_kjj_jzsl_vos")
	@Length(max = 2)
	private String jcQgjzKjjJzslVos;

	/** 基本检查信息左眼屈光矫正框架镜矫正视力(OS)名称 */
    @Comment(value="基本检查信息左眼屈光矫正框架镜矫正视力(OS)名称")
	@TableField(value = "jc_qgjz_kjj_jzsl_vos_name")
	@Length(max = 10)
	private String jcQgjzKjjJzslVosName;

	/** 基本检查信息右眼角膜塑形镜矫正视力(OD)类型(1数值2描述） */
    @Comment(value="基本检查信息右眼角膜塑形镜矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_jmsxj_jzsl_vod_type")
	private Integer jcJmsxjJzslVodType;

	/** 基本检查信息右眼角膜塑形镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息右眼角膜塑形镜矫正视力(OD)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_jmsxj_jzsl_vod")
	@Length(max = 2)
	private String jcJmsxjJzslVod;

	/** 基本检查信息右眼角膜塑形镜矫正视力(OD)名称 */
    @Comment(value="基本检查信息右眼角膜塑形镜矫正视力(OD)名称")
	@TableField(value = "jc_jmsxj_jzsl_vod_name")
	@Length(max = 10)
	private String jcJmsxjJzslVodName;

	/** 基本检查信息左眼角膜塑形镜矫正视力(OS)类型(1数值2描述） */
    @Comment(value="基本检查信息左眼角膜塑形镜矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "jc_jmsxj_jzsl_vos_type")
	private Integer jcJmsxjJzslVosType;

	/** 基本检查信息左眼角膜塑形镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc） */
    @Comment(value="基本检查信息左眼角膜塑形镜矫正视力(OS)编码（码表：far_eyesight或eyesight_desc）")
	@TableField(value = "jc_jmsxj_jzsl_vos")
	@Length(max = 2)
	private String jcJmsxjJzslVos;

	/** 基本检查信息左眼角膜塑形镜矫正视力(OS)名称 */
    @Comment(value="基本检查信息左眼角膜塑形镜矫正视力(OS)名称")
	@TableField(value = "jc_jmsxj_jzsl_vos_name")
	@Length(max = 10)
	private String jcJmsxjJzslVosName;

	/** 基本检查信息双眼视功能视远斜视度 */
    @Comment(value="基本检查信息双眼视功能视远斜视度")
	@TableField(value = "jc_sysgn_syxsd")
	@Length(max = 10)
	private String jcSysgnSyxsd;

	/** 基本检查信息双眼视功能视近斜视度 */
    @Comment(value="基本检查信息双眼视功能视近斜视度")
	@TableField(value = "jc_sysgn_sjxsd")
	@Length(max = 10)
	private String jcSysgnSjxsd;

	public String getJcSysgnNra() {
		return jcSysgnNra;
	}

	public void setJcSysgnNra(String jcSysgnNra) {
		this.jcSysgnNra = jcSysgnNra;
	}

	public String getJcSysgnBcc() {
		return jcSysgnBcc;
	}

	public void setJcSysgnBcc(String jcSysgnBcc) {
		this.jcSysgnBcc = jcSysgnBcc;
	}

	public String getJcSysgnPra() {
		return jcSysgnPra;
	}

	public void setJcSysgnPra(String jcSysgnPra) {
		this.jcSysgnPra = jcSysgnPra;
	}

	public String getJcSysgnAca() {
		return jcSysgnAca;
	}

	public void setJcSysgnAca(String jcSysgnAca) {
		this.jcSysgnAca = jcSysgnAca;
	}

	public String getJcDjfdOd() {
		return jcDjfdOd;
	}

	public void setJcDjfdOd(String jcDjfdOd) {
		this.jcDjfdOd = jcDjfdOd;
	}

	public String getJcDjfdOs() {
		return jcDjfdOs;
	}

	public void setJcDjfdOs(String jcDjfdOs) {
		this.jcDjfdOs = jcDjfdOs;
	}

	public String getJcDjfdOu() {
		return jcDjfdOu;
	}

	public void setJcDjfdOu(String jcDjfdOu) {
		this.jcDjfdOu = jcDjfdOu;
	}

	public String getJcDjlmdOd() {
		return jcDjlmdOd;
	}

	public void setJcDjlmdOd(String jcDjlmdOd) {
		this.jcDjlmdOd = jcDjlmdOd;
	}

	public String getJcDjlmdOs() {
		return jcDjlmdOs;
	}

	public void setJcDjlmdOs(String jcDjlmdOs) {
		this.jcDjlmdOs = jcDjlmdOs;
	}

	public String getJcDjlmdOu() {
		return jcDjlmdOu;
	}

	public void setJcDjlmdOu(String jcDjlmdOu) {
		this.jcDjlmdOu = jcDjlmdOu;
	}

	public String getJcW4d33cm() {
		return jcW4d33cm;
	}

	public void setJcW4d33cm(String jcW4d33cm) {
		this.jcW4d33cm = jcW4d33cm;
	}

	public String getJcW4d1m() {
		return jcW4d1m;
	}

	public void setJcW4d1m(String jcW4d1m) {
		this.jcW4d1m = jcW4d1m;
	}

	public String getJcW4d3m() {
		return jcW4d3m;
	}

	public void setJcW4d3m(String jcW4d3m) {
		this.jcW4d3m = jcW4d3m;
	}

	public String getJcW4dJhjd() {
		return jcW4dJhjd;
	}

	public void setJcW4dJhjd(String jcW4dJhjd) {
		this.jcW4dJhjd = jcW4dJhjd;
	}

	public String getJcW4dLts() {
		return jcW4dLts;
	}

	public void setJcW4dLts(String jcW4dLts) {
		this.jcW4dLts = jcW4dLts;
	}

	public String getJcTsjjcYjZgxsj() {
		return jcTsjjcYjZgxsj;
	}

	public void setJcTsjjcYjZgxsj(String jcTsjjcYjZgxsj) {
		this.jcTsjjcYjZgxsj = jcTsjjcYjZgxsj;
	}

	public String getJcTsjjcYjKgxsjref() {
		return jcTsjjcYjKgxsjref;
	}

	public void setJcTsjjcYjKgxsjref(String jcTsjjcYjKgxsjref) {
		this.jcTsjjcYjKgxsjref = jcTsjjcYjKgxsjref;
	}

	public String getJcTsjjcYjKgxsjlef() {
		return jcTsjjcYjKgxsjlef;
	}

	public void setJcTsjjcYjKgxsjlef(String jcTsjjcYjKgxsjlef) {
		this.jcTsjjcYjKgxsjlef = jcTsjjcYjKgxsjlef;
	}

	public String getJcTsjjcEjRhd() {
		return jcTsjjcEjRhd;
	}

	public void setJcTsjjcEjRhd(String jcTsjjcEjRhd) {
		this.jcTsjjcEjRhd = jcTsjjcEjRhd;
	}

	public String getJcTsjjcEjRxfw() {
		return jcTsjjcEjRxfw;
	}

	public void setJcTsjjcEjRxfw(String jcTsjjcEjRxfw) {
		this.jcTsjjcEjRxfw = jcTsjjcEjRxfw;
	}

	public String getJcTsjjcSj() {
		return jcTsjjcSj;
	}

	public void setJcTsjjcSj(String jcTsjjcSj) {
		this.jcTsjjcSj = jcTsjjcSj;
	}

	public Integer getMqqkYxlcs() {
		return mqqkYxlcs;
	}

	public void setMqqkYxlcs(Integer mqqkYxlcs) {
		this.mqqkYxlcs = mqqkYxlcs;
	}

	public Integer getMqqkSyxlcs() {
		return mqqkSyxlcs;
	}

	public void setMqqkSyxlcs(Integer mqqkSyxlcs) {
		this.mqqkSyxlcs = mqqkSyxlcs;
	}

	public Integer getMqqkXlpl() {
		return mqqkXlpl;
	}

	public void setMqqkXlpl(Integer mqqkXlpl) {
		this.mqqkXlpl = mqqkXlpl;
	}

	public Integer getMqqkLyyVodType() {
		return mqqkLyyVodType;
	}

	public void setMqqkLyyVodType(Integer mqqkLyyVodType) {
		this.mqqkLyyVodType = mqqkLyyVodType;
	}

	public String getMqqkLyyVod() {
		return mqqkLyyVod;
	}

	public void setMqqkLyyVod(String mqqkLyyVod) {
		this.mqqkLyyVod = mqqkLyyVod;
	}

	public String getMqqkLyyVodName() {
		return mqqkLyyVodName;
	}

	public void setMqqkLyyVodName(String mqqkLyyVodName) {
		this.mqqkLyyVodName = mqqkLyyVodName;
	}

	public Integer getMqqkLyyVosType() {
		return mqqkLyyVosType;
	}

	public void setMqqkLyyVosType(Integer mqqkLyyVosType) {
		this.mqqkLyyVosType = mqqkLyyVosType;
	}

	public String getMqqkLyyVos() {
		return mqqkLyyVos;
	}

	public void setMqqkLyyVos(String mqqkLyyVos) {
		this.mqqkLyyVos = mqqkLyyVos;
	}

	public String getMqqkLyyVosName() {
		return mqqkLyyVosName;
	}

	public void setMqqkLyyVosName(String mqqkLyyVosName) {
		this.mqqkLyyVosName = mqqkLyyVosName;
	}

	public Integer getMqqkLyjVodType() {
		return mqqkLyjVodType;
	}

	public void setMqqkLyjVodType(Integer mqqkLyjVodType) {
		this.mqqkLyjVodType = mqqkLyjVodType;
	}

	public String getMqqkLyjVod() {
		return mqqkLyjVod;
	}

	public void setMqqkLyjVod(String mqqkLyjVod) {
		this.mqqkLyjVod = mqqkLyjVod;
	}

	public String getMqqkLyjVodName() {
		return mqqkLyjVodName;
	}

	public void setMqqkLyjVodName(String mqqkLyjVodName) {
		this.mqqkLyjVodName = mqqkLyjVodName;
	}

	public Integer getMqqkLyjVosType() {
		return mqqkLyjVosType;
	}

	public void setMqqkLyjVosType(Integer mqqkLyjVosType) {
		this.mqqkLyjVosType = mqqkLyjVosType;
	}

	public String getMqqkLyjVos() {
		return mqqkLyjVos;
	}

	public void setMqqkLyjVos(String mqqkLyjVos) {
		this.mqqkLyjVos = mqqkLyjVos;
	}

	public String getMqqkLyjVosName() {
		return mqqkLyjVosName;
	}

	public void setMqqkLyjVosName(String mqqkLyjVosName) {
		this.mqqkLyjVosName = mqqkLyjVosName;
	}

	public String getMqqkQgjzKjjQjVod() {
		return mqqkQgjzKjjQjVod;
	}

	public void setMqqkQgjzKjjQjVod(String mqqkQgjzKjjQjVod) {
		this.mqqkQgjzKjjQjVod = mqqkQgjzKjjQjVod;
	}

	public String getMqqkQgjzKjjQjVos() {
		return mqqkQgjzKjjQjVos;
	}

	public void setMqqkQgjzKjjQjVos(String mqqkQgjzKjjQjVos) {
		this.mqqkQgjzKjjQjVos = mqqkQgjzKjjQjVos;
	}

	public String getMqqkQgjzKjjZjVod() {
		return mqqkQgjzKjjZjVod;
	}

	public void setMqqkQgjzKjjZjVod(String mqqkQgjzKjjZjVod) {
		this.mqqkQgjzKjjZjVod = mqqkQgjzKjjZjVod;
	}

	public String getMqqkQgjzKjjZjVos() {
		return mqqkQgjzKjjZjVos;
	}

	public void setMqqkQgjzKjjZjVos(String mqqkQgjzKjjZjVos) {
		this.mqqkQgjzKjjZjVos = mqqkQgjzKjjZjVos;
	}

	public String getMqqkQgjzKjjCwVod() {
		return mqqkQgjzKjjCwVod;
	}

	public void setMqqkQgjzKjjCwVod(String mqqkQgjzKjjCwVod) {
		this.mqqkQgjzKjjCwVod = mqqkQgjzKjjCwVod;
	}

	public String getMqqkQgjzKjjCwVos() {
		return mqqkQgjzKjjCwVos;
	}

	public void setMqqkQgjzKjjCwVos(String mqqkQgjzKjjCwVos) {
		this.mqqkQgjzKjjCwVos = mqqkQgjzKjjCwVos;
	}

	public Integer getMqqkQgjzKjjJzslVodType() {
		return mqqkQgjzKjjJzslVodType;
	}

	public void setMqqkQgjzKjjJzslVodType(Integer mqqkQgjzKjjJzslVodType) {
		this.mqqkQgjzKjjJzslVodType = mqqkQgjzKjjJzslVodType;
	}

	public String getMqqkQgjzKjjJzslVod() {
		return mqqkQgjzKjjJzslVod;
	}

	public void setMqqkQgjzKjjJzslVod(String mqqkQgjzKjjJzslVod) {
		this.mqqkQgjzKjjJzslVod = mqqkQgjzKjjJzslVod;
	}

	public String getMqqkQgjzKjjJzslVodName() {
		return mqqkQgjzKjjJzslVodName;
	}

	public void setMqqkQgjzKjjJzslVodName(String mqqkQgjzKjjJzslVodName) {
		this.mqqkQgjzKjjJzslVodName = mqqkQgjzKjjJzslVodName;
	}

	public String getMqqkQgjzKjjJzslVosType() {
		return mqqkQgjzKjjJzslVosType;
	}

	public void setMqqkQgjzKjjJzslVosType(String mqqkQgjzKjjJzslVosType) {
		this.mqqkQgjzKjjJzslVosType = mqqkQgjzKjjJzslVosType;
	}

	public String getMqqkQgjzKjjJzslVos() {
		return mqqkQgjzKjjJzslVos;
	}

	public void setMqqkQgjzKjjJzslVos(String mqqkQgjzKjjJzslVos) {
		this.mqqkQgjzKjjJzslVos = mqqkQgjzKjjJzslVos;
	}

	public String getMqqkQgjzKjjJzslVosName() {
		return mqqkQgjzKjjJzslVosName;
	}

	public void setMqqkQgjzKjjJzslVosName(String mqqkQgjzKjjJzslVosName) {
		this.mqqkQgjzKjjJzslVosName = mqqkQgjzKjjJzslVosName;
	}

	public String getMqqkSysgnSyxsd() {
		return mqqkSysgnSyxsd;
	}

	public void setMqqkSysgnSyxsd(String mqqkSysgnSyxsd) {
		this.mqqkSysgnSyxsd = mqqkSysgnSyxsd;
	}

	public String getMqqkSysgnSjxsd() {
		return mqqkSysgnSjxsd;
	}

	public void setMqqkSysgnSjxsd(String mqqkSysgnSjxsd) {
		this.mqqkSysgnSjxsd = mqqkSysgnSjxsd;
	}

	public String getMqqkSysgnNra() {
		return mqqkSysgnNra;
	}

	public void setMqqkSysgnNra(String mqqkSysgnNra) {
		this.mqqkSysgnNra = mqqkSysgnNra;
	}

	public String getMqqkSysgnBcc() {
		return mqqkSysgnBcc;
	}

	public void setMqqkSysgnBcc(String mqqkSysgnBcc) {
		this.mqqkSysgnBcc = mqqkSysgnBcc;
	}

	public String getMqqkSysgnPra() {
		return mqqkSysgnPra;
	}

	public void setMqqkSysgnPra(String mqqkSysgnPra) {
		this.mqqkSysgnPra = mqqkSysgnPra;
	}

	public String getMqqkSysgnAca() {
		return mqqkSysgnAca;
	}

	public void setMqqkSysgnAca(String mqqkSysgnAca) {
		this.mqqkSysgnAca = mqqkSysgnAca;
	}

	public String getMqqkDjfdOd() {
		return mqqkDjfdOd;
	}

	public void setMqqkDjfdOd(String mqqkDjfdOd) {
		this.mqqkDjfdOd = mqqkDjfdOd;
	}

	public String getMqqkDjfdOs() {
		return mqqkDjfdOs;
	}

	public void setMqqkDjfdOs(String mqqkDjfdOs) {
		this.mqqkDjfdOs = mqqkDjfdOs;
	}

	public String getMqqkDjfdOu() {
		return mqqkDjfdOu;
	}

	public void setMqqkDjfdOu(String mqqkDjfdOu) {
		this.mqqkDjfdOu = mqqkDjfdOu;
	}

	public String getMqqkDjlmdOd() {
		return mqqkDjlmdOd;
	}

	public void setMqqkDjlmdOd(String mqqkDjlmdOd) {
		this.mqqkDjlmdOd = mqqkDjlmdOd;
	}

	public String getMqqkDjlmdOs() {
		return mqqkDjlmdOs;
	}

	public void setMqqkDjlmdOs(String mqqkDjlmdOs) {
		this.mqqkDjlmdOs = mqqkDjlmdOs;
	}

	public String getMqqkDjlmdOu() {
		return mqqkDjlmdOu;
	}

	public void setMqqkDjlmdOu(String mqqkDjlmdOu) {
		this.mqqkDjlmdOu = mqqkDjlmdOu;
	}

	public String getMqqkW4d33cm() {
		return mqqkW4d33cm;
	}

	public void setMqqkW4d33cm(String mqqkW4d33cm) {
		this.mqqkW4d33cm = mqqkW4d33cm;
	}

	public String getMqqkW4d1m() {
		return mqqkW4d1m;
	}

	public void setMqqkW4d1m(String mqqkW4d1m) {
		this.mqqkW4d1m = mqqkW4d1m;
	}

	public String getMqqkW4d3m() {
		return mqqkW4d3m;
	}

	public void setMqqkW4d3m(String mqqkW4d3m) {
		this.mqqkW4d3m = mqqkW4d3m;
	}

	public String getMqqkW4dJhjd() {
		return mqqkW4dJhjd;
	}

	public void setMqqkW4dJhjd(String mqqkW4dJhjd) {
		this.mqqkW4dJhjd = mqqkW4dJhjd;
	}

	public String getMqqkW4dLts() {
		return mqqkW4dLts;
	}

	public void setMqqkW4dLts(String mqqkW4dLts) {
		this.mqqkW4dLts = mqqkW4dLts;
	}

	public String getMqqkTsjjcYjZgxsj() {
		return mqqkTsjjcYjZgxsj;
	}

	public void setMqqkTsjjcYjZgxsj(String mqqkTsjjcYjZgxsj) {
		this.mqqkTsjjcYjZgxsj = mqqkTsjjcYjZgxsj;
	}

	public String getMqqkTsjjcYjKgxsjref() {
		return mqqkTsjjcYjKgxsjref;
	}

	public void setMqqkTsjjcYjKgxsjref(String mqqkTsjjcYjKgxsjref) {
		this.mqqkTsjjcYjKgxsjref = mqqkTsjjcYjKgxsjref;
	}

	public String getMqqkTsjjcYjKgxsjlef() {
		return mqqkTsjjcYjKgxsjlef;
	}

	public void setMqqkTsjjcYjKgxsjlef(String mqqkTsjjcYjKgxsjlef) {
		this.mqqkTsjjcYjKgxsjlef = mqqkTsjjcYjKgxsjlef;
	}

	public String getMqqkTsjjcEjRhd() {
		return mqqkTsjjcEjRhd;
	}

	public void setMqqkTsjjcEjRhd(String mqqkTsjjcEjRhd) {
		this.mqqkTsjjcEjRhd = mqqkTsjjcEjRhd;
	}

	public String getMqqkTsjjcEjRxfw() {
		return mqqkTsjjcEjRxfw;
	}

	public void setMqqkTsjjcEjRxfw(String mqqkTsjjcEjRxfw) {
		this.mqqkTsjjcEjRxfw = mqqkTsjjcEjRxfw;
	}

	public String getMqqkTsjjcSj() {
		return mqqkTsjjcSj;
	}

	public void setMqqkTsjjcSj(String mqqkTsjjcSj) {
		this.mqqkTsjjcSj = mqqkTsjjcSj;
	}

	public String getPatientAppeals() {
		return patientAppeals;
	}

	public void setPatientAppeals(String patientAppeals) {
		this.patientAppeals = patientAppeals;
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

	public java.util.Date getTrainingFileDate() {
		return trainingFileDate;
	}

	public void setTrainingFileDate(java.util.Date trainingFileDate) {
		this.trainingFileDate = trainingFileDate;
	}

	public Integer getDiag() {
		return diag;
	}

	public void setDiag(Integer diag) {
		this.diag = diag;
	}

	public Integer getPayTimes() {
		return payTimes;
	}

	public void setPayTimes(Integer payTimes) {
		this.payTimes = payTimes;
	}

	public java.math.BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(java.math.BigDecimal payFee) {
		this.payFee = payFee;
	}

	public java.util.Date getLastTrainingDate() {
		return lastTrainingDate;
	}

	public void setLastTrainingDate(java.util.Date lastTrainingDate) {
		this.lastTrainingDate = lastTrainingDate;
	}

	public Integer getJcLyyVodType() {
		return jcLyyVodType;
	}

	public void setJcLyyVodType(Integer jcLyyVodType) {
		this.jcLyyVodType = jcLyyVodType;
	}

	public String getJcLyyVod() {
		return jcLyyVod;
	}

	public void setJcLyyVod(String jcLyyVod) {
		this.jcLyyVod = jcLyyVod;
	}

	public String getJcLyyVodName() {
		return jcLyyVodName;
	}

	public void setJcLyyVodName(String jcLyyVodName) {
		this.jcLyyVodName = jcLyyVodName;
	}

	public Integer getJcLyyVosType() {
		return jcLyyVosType;
	}

	public void setJcLyyVosType(Integer jcLyyVosType) {
		this.jcLyyVosType = jcLyyVosType;
	}

	public String getJcLyyVos() {
		return jcLyyVos;
	}

	public void setJcLyyVos(String jcLyyVos) {
		this.jcLyyVos = jcLyyVos;
	}

	public String getJcLyyVosName() {
		return jcLyyVosName;
	}

	public void setJcLyyVosName(String jcLyyVosName) {
		this.jcLyyVosName = jcLyyVosName;
	}

	public Integer getJcLyjVodType() {
		return jcLyjVodType;
	}

	public void setJcLyjVodType(Integer jcLyjVodType) {
		this.jcLyjVodType = jcLyjVodType;
	}

	public String getJcLyjVod() {
		return jcLyjVod;
	}

	public void setJcLyjVod(String jcLyjVod) {
		this.jcLyjVod = jcLyjVod;
	}

	public String getJcLyjVodName() {
		return jcLyjVodName;
	}

	public void setJcLyjVodName(String jcLyjVodName) {
		this.jcLyjVodName = jcLyjVodName;
	}

	public Integer getJcLyjVosType() {
		return jcLyjVosType;
	}

	public void setJcLyjVosType(Integer jcLyjVosType) {
		this.jcLyjVosType = jcLyjVosType;
	}

	public String getJcLyjVos() {
		return jcLyjVos;
	}

	public void setJcLyjVos(String jcLyjVos) {
		this.jcLyjVos = jcLyjVos;
	}

	public String getJcLyjVosName() {
		return jcLyjVosName;
	}

	public void setJcLyjVosName(String jcLyjVosName) {
		this.jcLyjVosName = jcLyjVosName;
	}

	public String getJcQgjzKjjQjVod() {
		return jcQgjzKjjQjVod;
	}

	public void setJcQgjzKjjQjVod(String jcQgjzKjjQjVod) {
		this.jcQgjzKjjQjVod = jcQgjzKjjQjVod;
	}

	public String getJcQgjzKjjQjVos() {
		return jcQgjzKjjQjVos;
	}

	public void setJcQgjzKjjQjVos(String jcQgjzKjjQjVos) {
		this.jcQgjzKjjQjVos = jcQgjzKjjQjVos;
	}

	public String getJcQgjzKjjZjVod() {
		return jcQgjzKjjZjVod;
	}

	public void setJcQgjzKjjZjVod(String jcQgjzKjjZjVod) {
		this.jcQgjzKjjZjVod = jcQgjzKjjZjVod;
	}

	public String getJcQgjzKjjZjVos() {
		return jcQgjzKjjZjVos;
	}

	public void setJcQgjzKjjZjVos(String jcQgjzKjjZjVos) {
		this.jcQgjzKjjZjVos = jcQgjzKjjZjVos;
	}

	public String getJcQgjzKjjCwVod() {
		return jcQgjzKjjCwVod;
	}

	public void setJcQgjzKjjCwVod(String jcQgjzKjjCwVod) {
		this.jcQgjzKjjCwVod = jcQgjzKjjCwVod;
	}

	public String getJcQgjzKjjCwVos() {
		return jcQgjzKjjCwVos;
	}

	public void setJcQgjzKjjCwVos(String jcQgjzKjjCwVos) {
		this.jcQgjzKjjCwVos = jcQgjzKjjCwVos;
	}

	public Integer getJcQgjzKjjJzslVodType() {
		return jcQgjzKjjJzslVodType;
	}

	public void setJcQgjzKjjJzslVodType(Integer jcQgjzKjjJzslVodType) {
		this.jcQgjzKjjJzslVodType = jcQgjzKjjJzslVodType;
	}

	public String getJcQgjzKjjJzslVod() {
		return jcQgjzKjjJzslVod;
	}

	public void setJcQgjzKjjJzslVod(String jcQgjzKjjJzslVod) {
		this.jcQgjzKjjJzslVod = jcQgjzKjjJzslVod;
	}

	public String getJcQgjzKjjJzslVodName() {
		return jcQgjzKjjJzslVodName;
	}

	public void setJcQgjzKjjJzslVodName(String jcQgjzKjjJzslVodName) {
		this.jcQgjzKjjJzslVodName = jcQgjzKjjJzslVodName;
	}

	public Integer getJcQgjzKjjJzslVosType() {
		return jcQgjzKjjJzslVosType;
	}

	public void setJcQgjzKjjJzslVosType(Integer jcQgjzKjjJzslVosType) {
		this.jcQgjzKjjJzslVosType = jcQgjzKjjJzslVosType;
	}

	public String getJcQgjzKjjJzslVos() {
		return jcQgjzKjjJzslVos;
	}

	public void setJcQgjzKjjJzslVos(String jcQgjzKjjJzslVos) {
		this.jcQgjzKjjJzslVos = jcQgjzKjjJzslVos;
	}

	public String getJcQgjzKjjJzslVosName() {
		return jcQgjzKjjJzslVosName;
	}

	public void setJcQgjzKjjJzslVosName(String jcQgjzKjjJzslVosName) {
		this.jcQgjzKjjJzslVosName = jcQgjzKjjJzslVosName;
	}

	public Integer getJcJmsxjJzslVodType() {
		return jcJmsxjJzslVodType;
	}

	public void setJcJmsxjJzslVodType(Integer jcJmsxjJzslVodType) {
		this.jcJmsxjJzslVodType = jcJmsxjJzslVodType;
	}

	public String getJcJmsxjJzslVod() {
		return jcJmsxjJzslVod;
	}

	public void setJcJmsxjJzslVod(String jcJmsxjJzslVod) {
		this.jcJmsxjJzslVod = jcJmsxjJzslVod;
	}

	public String getJcJmsxjJzslVodName() {
		return jcJmsxjJzslVodName;
	}

	public void setJcJmsxjJzslVodName(String jcJmsxjJzslVodName) {
		this.jcJmsxjJzslVodName = jcJmsxjJzslVodName;
	}

	public Integer getJcJmsxjJzslVosType() {
		return jcJmsxjJzslVosType;
	}

	public void setJcJmsxjJzslVosType(Integer jcJmsxjJzslVosType) {
		this.jcJmsxjJzslVosType = jcJmsxjJzslVosType;
	}

	public String getJcJmsxjJzslVos() {
		return jcJmsxjJzslVos;
	}

	public void setJcJmsxjJzslVos(String jcJmsxjJzslVos) {
		this.jcJmsxjJzslVos = jcJmsxjJzslVos;
	}

	public String getJcJmsxjJzslVosName() {
		return jcJmsxjJzslVosName;
	}

	public void setJcJmsxjJzslVosName(String jcJmsxjJzslVosName) {
		this.jcJmsxjJzslVosName = jcJmsxjJzslVosName;
	}

	public String getJcSysgnSyxsd() {
		return jcSysgnSyxsd;
	}

	public void setJcSysgnSyxsd(String jcSysgnSyxsd) {
		this.jcSysgnSyxsd = jcSysgnSyxsd;
	}

	public String getJcSysgnSjxsd() {
		return jcSysgnSjxsd;
	}

	public void setJcSysgnSjxsd(String jcSysgnSjxsd) {
		this.jcSysgnSjxsd = jcSysgnSjxsd;
	}

	public String getMqqkXlplName() {
		return mqqkXlplName;
	}

	public void setMqqkXlplName(String mqqkXlplName) {
		this.mqqkXlplName = mqqkXlplName;
	}

	public String getDiagName() {
		return diagName;
	}

	public void setDiagName(String diagName) {
		this.diagName = diagName;
	}
	
}
