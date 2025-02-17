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

import com.aier.cloud.basic.common.util.SpringUtils;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeDictName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeTransCodeService;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.SpringBootBeanUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * T_AE_CORNEAL_CONTACT
 * 
 * @author 爱尔眼科
 * @since 2022-12-09 17:47:49
 */
@Data
@TableName("T_AE_CORNEAL_CONTACT")
public class AeCornealContact extends BaseEntity<AeCornealContact> {
	/**
	 * 基本检查信息右眼综合验光矫正视力(OD)名称
	 */
	@Comment(value = "基本检查信息右眼综合验光矫正视力(OD)名称")
	@TableField(value = "jc_yg_jzsl_od_name")
	@Length(max = 10)
	private String jcYgJzslOdName;

	/**
	 * 基本检查信息左眼综合验光矫正视力(OS)类型(1数值2描述）
	 */
	@Comment(value = "基本检查信息左眼综合验光矫正视力(OS)类型(1数值2描述）")
	@TableField(value = "jc_yg_jzsl_os_type")
	private Integer jcYgJzslOsType;

	/**
	 * 基本检查信息左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc）
	 */
	@Comment(value = "基本检查信息左眼综合验光矫正视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_yg_jzsl_os")
	@Length(max = 2)
	private String jcYgJzslOs;

	/**
	 * 基本检查信息右眼综合验光矫正视力(OS)名称
	 */
	@Comment(value = "基本检查信息右眼综合验光矫正视力(OS)名称")
	@TableField(value = "jc_yg_jzsl_os_name")
	@Length(max = 10)
	private String jcYgJzslOsName;

	/**
	 * 基本检查信息右眼角膜平K值(OD)
	 */
	@Comment(value = "基本检查信息右眼角膜平K值(OD)")
	@TableField(value = "jc_jmpkz_od")
	@Length(max = 10)
	private String jcJmpkzOd;

	/**
	 * 基本检查信息右眼角膜平K轴位(OD)
	 */
	@Comment(value = "基本检查信息右眼角膜平K轴位(OD)")
	@TableField(value = "jc_jmpkzw_od")
	@Length(max = 10)
	private String jcJmpkzwOd;

	/**
	 * 基本检查信息左眼角膜平K值(OS)
	 */
	@Comment(value = "基本检查信息左眼角膜平K值(OS)")
	@TableField(value = "jc_jmpkz_os")
	@Length(max = 10)
	private String jcJmpkzOs;

	/**
	 * 基本检查信息左眼角膜平K轴位(OS)
	 */
	@Comment(value = "基本检查信息左眼角膜平K轴位(OS)")
	@TableField(value = "jc_jmpkzw_os")
	@Length(max = 10)
	private String jcJmpkzwOs;

	/**
	 * 基本检查信息右眼配镜处方球镜(OD)
	 */
	@Comment(value = "基本检查信息右眼配镜处方球镜(OD)")
	@TableField(value = "jc_pjcf_qj_od")
	@Length(max = 10)
	private String jcPjcfQjOd;

	/**
	 * 患者诉求
	 */
	@Comment(value = "患者诉求")
	@TableField(value = "demand")
	@Length(max = 500)
	private String demand;

	/**
	 * 镜片品牌其它
	 */
	@Comment(value = "镜片品牌其它")
	@TableField(value = "contact_brand_remark")
	@Length(max = 50)
	private String contactBrandRemark;

	/**
	 * 镜片完整度OS（码表：lens_integrity)
	 */
	@Comment(value = "镜片完整度OS（码表：lens_integrity)")
	@TableField(value = "lens_integrity_os")
	@Length(max = 2)
	private String lensIntegrityOs;

	/**
	 * 镜片完整度名称OS
	 */
	@Comment(value = "镜片完整度名称OS")
	@TableField(value = "lens_integrity_os_name")
	@AeDictName(type = "lens_integrity", from = "lensIntegrityOs")
	@Length(max = 50)
	private String lensIntegrityOsName;

	/**
	 * 事件状态 （码表：event_status）
	 */
	@Comment(value = "事件状态 （码表：event_status）")
	@TableField(value = "event_status_name")
	@AeDictName(type = "event_status", from = "eventStatus")
	@Length(max = 50)
	private String eventStatusName;

	/**
	 * 满意度名称（码表：satisfaction）
	 */
	@Comment(value = "满意度名称（码表：satisfaction）")
	@TableField(value = "satisfaction_name")
	@AeDictName(type = "satisfaction", from = "satisfaction")
	@Length(max = 50)
	private String satisfactionName;

	/**
	 * 镜片划痕情况OS（码表：lens_scratches）
	 */
	@Comment(value = "镜片划痕情况OS（码表：lens_scratches）")
	@TableField(value = "lens_scratchs_os")
	@Length(max = 2)
	private String lensScratchsOs;

	/**
	 * 镜片划痕情况名称OS（码表：lens_scratches）
	 */
	@Comment(value = "镜片划痕情况名称OS（码表：lens_scratches）")
	@TableField(value = "lens_scratchs_os_name")
	@AeDictName(type = "lens_scratches", from = "lensScratchsOs")
	@Length(max = 50)
	private String lensScratchsOsName;

	/**
	 * 镜片清洁情况OS（码表：lens_cleaning)
	 */
	@Comment(value = "镜片清洁情况OS（码表：lens_cleaning)")
	@TableField(value = "lens_cleaning_os")
	@Length(max = 2)
	private String lensCleaningOs;

	/**
	 * 镜片清洁情况名称OS（码表：lens_cleaning)
	 */
	@Comment(value = "镜片清洁情况名称OS（码表：lens_cleaning)")
	@TableField(value = "lens_cleaning_os_name")
	@AeDictName(type = "lens_cleaning", from = "lensCleaningOs")
	@Length(max = 50)
	private String lensCleaningOsName;

	/**
	 * 镜片完整度OD（码表：lens_integrity)
	 */
	@Comment(value = "镜片完整度OD（码表：lens_integrity)")
	@TableField(value = "lens_integrity_od")
	@Length(max = 2)
	private String lensIntegrityOd;

	/**
	 * 镜片完整度名称OD
	 */
	@Comment(value = "镜片完整度名称OD")
	@TableField(value = "lens_integrity_od_name")
	@AeDictName(type = "lens_integrity", from = "lensIntegrityOd")
	@Length(max = 50)
	private String lensIntegrityOdName;

	/**
	 * 镜片划痕情况OD（码表：lens_scratches）
	 */
	@Comment(value = "镜片划痕情况OD（码表：lens_scratches）")
	@TableField(value = "lens_scratchs_od")
	@Length(max = 2)
	private String lensScratchsOd;

	/**
	 * 镜片划痕情况名称OD（码表：lens_scratches）
	 */
	@Comment(value = "镜片划痕情况名称OD（码表：lens_scratches）")
	@TableField(value = "lens_scratchs_od_name")
	@AeDictName(type = "lens_scratches", from = "lensScratchsOd")
	@Length(max = 50)
	private String lensScratchsOdName;

	/**
	 * 镜片清洁情况OD（码表：lens_cleaning)
	 */
	@Comment(value = "镜片清洁情况OD（码表：lens_cleaning)")
	@TableField(value = "lens_cleaning_od")
	@Length(max = 2)
	private String lensCleaningOd;

	/**
	 * 镜片清洁情况名称OD（码表：lens_cleaning)
	 */
	@Comment(value = "镜片清洁情况名称OD（码表：lens_cleaning)")
	@TableField(value = "lens_cleaning_od_name")
	@AeDictName(type = "lens_cleaning", from = "lensCleaningOd")
	@Length(max = 50)
	private String lensCleaningOdName;

	/**
	 * 医院ID
	 */
	@Comment(value = "医院ID")
	@TableField(value = "hosp_id")
	@NotBlank
	private Long hospId;

	/**
	 * 事件经过
	 */
	@Comment(value = "事件经过")
	@TableField(value = "proc_descr")
	@Length(max = 2000)
	private String procDescr;

	/**
	 * 基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）
	 */
	@Comment(value = "基本检查信息右眼裸眼远视力(OD)类型(1数值2描述）")
	@TableField(value = "naked_far_vod_type")
	private Integer nakedFarVodType;

	/**
	 * 基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc）
	 */
	@Comment(value = "基本检查信息右眼裸眼远视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "naked_far_vod")
	@Length(max = 2)
	private String nakedFarVod;

	/**
	 * 基本检查信息右眼裸眼远视力(OD)名称
	 */
	@Comment(value = "基本检查信息右眼裸眼远视力(OD)名称")
	@TableField(value = "naked_far_vod_name")
	@Length(max = 10)
	private String nakedFarVodName;

	/**
	 * 基本检查信息左眼裸眼远视力(OS)类型(1数值2描述）
	 */
	@Comment(value = "基本检查信息左眼裸眼远视力(OS)类型(1数值2描述）")
	@TableField(value = "naked_far_vos_type")
	private Integer nakedFarVosType;

	/**
	 * 基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc）
	 */
	@Comment(value = "基本检查信息左眼裸眼远视力(OS)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "naked_far_vos")
	@Length(max = 2)
	private String nakedFarVos;

	/**
	 * 基本检查信息右眼裸眼远视力(OS)名称
	 */
	@Comment(value = "基本检查信息右眼裸眼远视力(OS)名称")
	@TableField(value = "naked_far_vos_name")
	@Length(max = 10)
	private String nakedFarVosName;

	/**
	 * 基本检查信息右眼眼压(OD)类型编码(码表：iop_type）
	 */
	@Comment(value = "基本检查信息右眼眼压(OD)类型编码(码表：iop_type）")
	@TableField(value = "iop_type_od")
	@Length(max = 2)
	private String iopTypeOd;

	/**
	 * 基本检查信息右眼眼压(OD)类型名称
	 */
	@Comment(value = "基本检查信息右眼眼压(OD)类型名称")
	@TableField(value = "iop_type_od_name")
	@Length(max = 20)
	private String iopTypeOdName;

	/**
	 * 基本检查信息右眼眼压数值或指测眼压编码(OD)
	 */
	@Comment(value = "基本检查信息右眼眼压数值或指测眼压编码(OD)")
	@TableField(value = "iop_od")
	@Length(max = 10)
	private String iopOd;

	/**
	 * 基本检查信息右眼眼压指测眼压名称(OD)
	 */
	@Comment(value = "基本检查信息右眼眼压指测眼压名称(OD)")
	@TableField(value = "iop_od_name")
	@Length(max = 10)
	private String iopOdName;

	/**
	 * 基本检查信息左眼眼压(OS)类型编码(码表：iop_type）
	 */
	@Comment(value = "基本检查信息左眼眼压(OS)类型编码(码表：iop_type）")
	@TableField(value = "iop_type_os")
	@Length(max = 2)
	private String iopTypeOs;

	/**
	 * 基本检查信息左眼眼压(OS)类型名称
	 */
	@Comment(value = "基本检查信息左眼眼压(OS)类型名称")
	@TableField(value = "iop_type_os_name")
	@Length(max = 20)
	private String iopTypeOsName;

	/**
	 * 基本检查信息左眼眼压数值或指测眼压编码(OS)
	 */
	@Comment(value = "基本检查信息左眼眼压数值或指测眼压编码(OS)")
	@TableField(value = "iop_os")
	@Length(max = 10)
	private String iopOs;

	/**
	 * 基本检查信息左眼眼压指测眼压名称(OS)
	 */
	@Comment(value = "基本检查信息左眼眼压指测眼压名称(OS)")
	@TableField(value = "iop_os_name")
	@Length(max = 10)
	private String iopOsName;

	/**
	 * 基本检查信息右眼综合验光球镜(OD)
	 */
	@Comment(value = "基本检查信息右眼综合验光球镜(OD)")
	@TableField(value = "jc_yg_qj_od")
	@Length(max = 10)
	private String jcYgQjOd;

	/**
	 * 基本检查信息左眼综合验光球镜(OS)
	 */
	@Comment(value = "基本检查信息左眼综合验光球镜(OS)")
	@TableField(value = "jc_yg_qj_os")
	@Length(max = 10)
	private String jcYgQjOs;

	/**
	 * 基本检查信息右眼综合验光柱镜(OD)
	 */
	@Comment(value = "基本检查信息右眼综合验光柱镜(OD)")
	@TableField(value = "jc_yg_zj_od")
	@Length(max = 10)
	private String jcYgZjOd;

	/**
	 * 基本检查信息左眼综合验光柱镜(OS)
	 */
	@Comment(value = "基本检查信息左眼综合验光柱镜(OS)")
	@TableField(value = "jc_yg_zj_os")
	@Length(max = 10)
	private String jcYgZjOs;

	/**
	 * 基本检查信息右眼综合验光轴位(OD)
	 */
	@Comment(value = "基本检查信息右眼综合验光轴位(OD)")
	@TableField(value = "jc_yg_cw_od")
	@Length(max = 10)
	private String jcYgCwOd;

	/**
	 * 基本检查信息左眼综合验光轴位(OS)
	 */
	@Comment(value = "基本检查信息左眼综合验光轴位(OS)")
	@TableField(value = "jc_yg_cw_os")
	@Length(max = 10)
	private String jcYgCwOs;

	/**
	 * 基本检查信息右眼综合验光矫正视力(OD)类型(1数值2描述）
	 */
	@Comment(value = "基本检查信息右眼综合验光矫正视力(OD)类型(1数值2描述）")
	@TableField(value = "jc_yg_jzsl_od_type")
	private Integer jcYgJzslOdType;

	/**
	 * 基本检查信息右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc）
	 */
	@Comment(value = "基本检查信息右眼综合验光矫正视力(OD)编码（码表far_eyesight或eyesight_desc）")
	@TableField(value = "jc_yg_jzsl_od")
	@Length(max = 2)
	private String jcYgJzslOd;

	/**
	 * 不良事件基础表ID
	 */
	@Comment(value = "不良事件基础表ID")
	@TableField(value = "basic_id")
	private Long basicId;

	/**
	 * 轴位（OS）
	 */
	@Comment(value = "轴位（OS）")
	@TableField(value = "ax_os")
	@Length(max = 16)
	private String axOs;

	/**
	 * 门诊时间
	 */
	@Comment(value = "门诊时间")
	@TableField(value = "visit_date")
	private java.util.Date visitDate;

	/**
	 * 门诊诊断
	 */
	@Comment(value = "门诊诊断")
	@TableField(value = "visit_diagnose")
	@Length(max = 10)
	private String visitDiagnose;


	@TableField(exist = false)
	private String payDiagnose;

	public String getPayDiagnose() {
		String value = null;
		if(!ObjectUtils.isEmpty(visitDiagnose)){
			AeTransCodeService aeTransCodeService = (AeTransCodeService) SpringBootBeanUtil.getApplicationContext().getBean("com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeTransCodeService");
			value = aeTransCodeService.getValue("visit_diagnose", visitDiagnose);
		}

		return value;
	}

	/**
	 * 不良反应级别(码表:adverse_level)
	 */
	@Comment(value = "不良反应级别(码表:adverse_level)")
	@TableField(value = "adverse_level")
	@Length(max = 4)
	private String adverseLevel;

	/**
	 * 镜片类别（码表：contact_lens_type）
	 */
	@Comment(value = "镜片类别（码表：contact_lens_type）")
	@TableField(value = "contact_type")
	@Length(max = 4)
	private String contactType;

	/**
	 * 镜片品牌（码表：contact_brand）
	 */
	@Comment(value = "镜片品牌（码表：contact_brand）")
	@TableField(value = "contact_brand")
	@Length(max = 4)
	private String contactBrand;

	/**
	 * 镜片设计（码表：contact_design）
	 */
	@Comment(value = "镜片设计（码表：contact_design）")
	@TableField(value = "contact_design")
	@Length(max = 4)
	private String contactDesign;


	/**
	 * 初次戴镜日期
	 */
	@Comment(value = "初次戴镜日期")
	@TableField(value = "first_wear")
	private java.util.Date firstWear;

	/**
	 * 镜片颜色是否正确（1 是 2 否）
	 */
	@Comment(value = "镜片颜色是否正确（1 是 2 否）")
	@TableField(value = "color_sign")
	private Integer colorSign;

	/**
	 * 镜片是否超期（1 是 2 否）
	 */
	@Comment(value = "镜片是否超期（1 是 2 否）")
	@TableField(value = "expire_sign")
	private Integer expireSign;

	/**
	 * 是否按期复查（1 是 2 否  3 其他）
	 */
	@Comment(value = "是否按期复查（1 是 2 否  3 其他）")
	@TableField(value = "review_sign")
	private Integer reviewSign;

	/**
	 * 病情转归 （1.好转 2.恶化 3.其他）
	 */
	@Comment(value = "病情转归 （1.好转 2.恶化 3.其他）")
	@TableField(value = "convert_status")
	private Integer convertStatus;

	/**
	 * 病情转归说明
	 */
	@Comment(value = "病情转归说明")
	@TableField(value = "convert_remark")
	@Length(max = 100)
	private String convertRemark;

	/**
	 * 事件状态 （码表：event_status）
	 */
	@Comment(value = "事件状态 （码表：event_status）")
	@TableField(value = "event_status")
	private Integer eventStatus;

	/**
	 * 事件状态说明
	 */
	@Comment(value = "事件状态说明")
	@TableField(value = "event_status_remark")
	@Length(max = 100)
	private String eventStatusRemark;

	/**
	 * 满意度（码表：satisfaction）
	 */
	@Comment(value = "满意度（码表：satisfaction）")
	@TableField(value = "satisfaction")
	@Length(max = 4)
	private String satisfaction;

	/**
	 * 满意度说明
	 */
	@Comment(value = "满意度说明")
	@TableField(value = "satisfaction_remark")
	@Length(max = 100)
	private String satisfactionRemark;

	/**
	 * 目前矫正视力（OD）（码表：far_eyesight）
	 */
	@Comment(value = "目前矫正视力（OD）（码表：far_eyesight）")
	@TableField(value = "cur_ccd_od")
	@Length(max = 4)
	private String curCcdOd;

	/**
	 * 目前矫正视力（OS）（码表：far_eyesight）
	 */
	@Comment(value = "目前矫正视力（OS）（码表：far_eyesight）")
	@TableField(value = "cur_ccd_os")
	@Length(max = 4)
	private String curCcdOs;

	/**
	 * 用药情况
	 */
	@Comment(value = "用药情况")
	@TableField(value = "grug_remak")
	@Length(max = 500)
	private String grugRemak;

	/**
	 * 症状及体征
	 */
	@Comment(value = "症状及体征")
	@TableField(value = "vital_sign")
	@Length(max = 500)
	private String vitalSign;

	/**
	 * 眼前节照相
	 */
	@Comment(value = "眼前节照相")
	@TableField(value = "anterio_segment")
	@Length(max = 200)
	private String anterioSegment;

	/**
	 * 眼部B超
	 */
	@Comment(value = "眼部B超")
	@TableField(value = "eye_b_ultrasonic")
	@Length(max = 200)
	private String eyeBUltrasonic;

	/**
	 * 其他相关病历附件
	 */
	@Comment(value = "其他相关病历附件")
	@TableField(value = "other_attachment")
	@Length(max = 200)
	private String otherAttachment;

	/**
	 * 创建者ID
	 */
	@Comment(value = "创建者ID")
	@TableField(value = "creator")
	@NotBlank
	private Long creator;

	/**
	 * 创建时间
	 */
	@Comment(value = "创建时间")
	@TableField(value = "create_date", fill = FieldFill.INSERT)
	@NotBlank
	private java.util.Date createDate;

	/**
	 * 门诊诊断其它
	 */
	@Comment(value = "门诊诊断其它")
	@TableField(value = "visit_diagnose_remark")
	@Length(max = 500)
	private String visitDiagnoseRemark;

}
