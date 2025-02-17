package com.aier.cloud.api.amcs.adverse.domain;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @program: amcs
 * @description: 住院接口实体
 * @author: hsw
 * @create: 2022-12-06 15:56
 **/
@Data
public class ApiInDomain {
	/* 患者ID */
	private Long patientId;

	/* 住院诊断 */
	private String diagName;
	/* 患者姓名 */
	private String patientName;
	/* 病案号 */
	private String medicalNumber;

	/* 住院次数 */
	private Integer inpTimes;

	/** 入院时间 */
	private java.util.Date inpTime;

	/** 门（急）诊诊断 */
	private String outpDiagName;

	/** 入院诊断 */
	private String inpDiagName;

	/** 入院视力类型（OS） 1数值 2描述 */
	private String inpVisionOsType;

	/** 入院视力编号（OS） */
	private String inpVisionOs;

	/** 入院视力（OS） */
	private String inpVisionOsName;

	/** 入院视力类型（OD） 1数值 2描述 */
	private String inpVisionOdType;

	/** 入院视力编号（OD） */
	private String inpVisionOd;

	/** 入院视力（OD） */
	private String inpVisionOdName;

	/** 出院视力类型（OS） 1数值 2描述 */
	private String dischargeVisionOsType;

	/** 出院视力编号（OS） */
	private String dischargeVisionOs;

	/** 出院视力（OS） */
	private String dischargeVisionOsName;

	/** 出院视力类型（OD） 1数值 2描述 */
	private String dischargeVisionOdType;

	/** 出院视力编号（OD） */
	private String dischargeVisionOd;

	/** 出院视力（OD） */
	private String dischargeVisionOdName;

	/** 出院时间 */
	private java.util.Date dischargeTime;

	/** 相关病历 */
	private String emrUrl;

	/** 病案接口 */
	private Map<String, AemrVision> aemrVisionMap;

	/** 拟手术*/
	private String operPlan;
	/** 手术*/
	private String oper;

	/** 切口类别*/
	private String cutType;

	/** 入院右眼眼压类型（OD）(码表iop_type)*/
	private String inpTonometryOdType;

	/** 入院右眼眼压类型名称（OD）(码表iop_type)*/
	private String inpTonometryOdTypeName;

	/** 入院右眼眼压数值（OD）(单位mmHg)*/
	private java.math.BigDecimal inpIopOd;

	/** 入院右眼指测眼压结果编码（OD）（码表：finger_tonometry）*/
	private String inpFingerTonometryOd;

	/** 入院右眼指测眼压结果名称（OD）（码表：finger_tonometry）*/
	private String inpFingerTonometryOdName;

	/** 入院左眼眼压类型（OS）(码表iop_type)*/
	private String inpTonometryOsType;

	/** 入院左眼眼压类型名称（OS）(码表iop_type)*/
	private String inpTonometryOsTypeName;

	/** 入院左眼眼压数值（OS）(单位mmHg)*/
	private java.math.BigDecimal inpIopOs;

	/** 入院左眼指测眼压结果编码（OS）（码表：finger_tonometry）*/
	private String inpFingerTonometryOs;

	/** 入院左眼指测眼压结果名称（OS）（码表：finger_tonometry）*/
	private String inpFingerTonometryOsName;

	/** 出院右眼眼压类型（OD）(码表iop_type)*/
	private String discTonometryOdType;

	/** 出院右眼眼压类型名称（OD）(码表iop_type)*/
	private String discTonometryOdTypeName;

	/** 出院右眼眼压数值（OD）(单位mmHg)*/
	private java.math.BigDecimal discIopOd;

	/** 出院右眼指测眼压结果编码（OD）（码表：finger_tonometry）*/
	private String discFingerTonometryOd;

	/** 出院右眼指测眼压结果名称（OD）（码表：finger_tonometry）*/
	private String discFingerTonometryOdName;

	/** 出院左眼眼压类型（OS）(码表iop_type)*/
	private String discTonometryOsType;

	/** 出院左眼眼压类型名称（OS）(码表iop_type)*/
	private String discTonometryOsTypeName;

	/** 出院左眼眼压数值（OS）(单位mmHg)*/
	java.math.BigDecimal discIopOs;

	/** 出院左眼指测眼压结果编码（OS）（码表：finger_tonometry）*/
	private String discFingerTonometryOs;

	/** 出院左眼指测眼压结果名称（OS）（码表：finger_tonometry）*/
	private String discFingerTonometryOsName;


}
