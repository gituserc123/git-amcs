package com.aier.cloud.api.amcs.adverse.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class AeBasicInfo extends BaseEntity{
		
    private Long masterId;
    
    private String eventCode;
    
    private String tags;
	
	/** 前置事件(多次上报前一次ID)*/
	private Long prevId;
	
	/** 严重程度*/
	private String severityLevel;
	
	/** 医院名称*/
	private String hospName;
	
	/** 院内事件(0.否，1.是)*/
	private Integer hospSign;
	
	/** 医院ID*/
	private Long hospId;
	
	/** 患者ID*/
	private Long patientId;
	
	/** 患者姓名*/
	private String patientName;
	
	/** 患者性别*/
	private Integer patientSex;
	
	/** 患者年龄*/
	private Integer patientAge;
	
	/** 患者上报次数*/
	private Integer reportTimes;
	
	/** 事件名称*/
	private String eventName;
	
	/** 所在节点 （0 上报 1医院管理部门 2 医院CEO 3 省区 4 集团  5 完结  ）*/
	private Integer node;
	
	/** 状态审核状态 （0 待审核 1 通过 2 取消 3 退回）*/
	private Integer status;
	
	/** 亚专科(码表: sub_type)*/
	private String subspecialty;
	
	/** 发生日期*/
	private java.util.Date eventDate;
	
	/** 日期类型(码表: date_type)*/
	private String dateType;
	
	/** 事件分类一级(码表: grade_one)*/
	private String gradeOne;
	
	/** 事件分类二级I(码表: grade_two_a)*/
	private String gradeTwoA;
	
	/** 事件分类二级II(码表: grade_two_b)*/
	private String gradeTwoB;
	
	/** 事件分类二级说明*/
	private String gradeTwoRemark;
	
	/** 事件处理进展(码表: progress_type)*/
	private String processProgress;
	
	/** 事件处理方式(码表: process_type)*/
	private String processType;
	
	/** 赔偿金额*/
	java.math.BigDecimal compensationAmount;
	
	/** 减免金额*/
	java.math.BigDecimal deductionAmount;

	/** 累计赔偿金额*/
	java.math.BigDecimal allCompensationAmount = BigDecimal.valueOf(0.0);

	/** 累计减免金额*/
	java.math.BigDecimal allDeductionAmount = BigDecimal.valueOf(0.0);
	
	/** 事件分级(码表:event_level)*/
	private String eventLevel;
	
	/** 发生地点(码表: address_type)*/
	private String address;
	
	/** 发生时段(码表: period_type)*/
	private String period;
	
	/** 发现者(码表: person_type)*/
	private String finder;
	/** 患者编号(门诊号、病案号、病历号等)*/
	private String patientNo;

	/** 是否构成纠纷(0 否 1 是)*/
	private Integer disputeSign;
	
	/** 是否完结(0 否 1 是)*/
	private Integer finishSign;
   /** 是否高风险(0 否 1 是)*/
	private Integer isHighRisk;
	
	/** 当事人ID*/
	private Long staffId;
	
	/** 当事人年龄*/
	private Integer staffAge;
	
	/** 当事人性别(码表：sex)*/
	private String staffSex;
	
	/** 当事人学历(码表: education_type)*/
	private String staffDegree;
	
	/** 当事人在职情况(码表:work_type)*/
	private String staffWork;
	
	/** 当事人工作年限(码表: work_year_type)*/
	private String staffYears;
	
	/** 当事人职称(码表:technical_post_type)*/
	private String technicalPost;
	
	/** 当事人职称获取时间*/
	private String technicalPostDate;
	
	/** 当事人类别(码表:staff_type)*/
	private String staffType;
	
	/** 当事人姓名*/
	private String staffName;
	
	/** 原因*/
	private String reason;
	
	/** 意见及结果*/
	private String opinion;

	/** 出生年月日 */
	private java.util.Date patientBirth;

	/** 职业 */
	private String patientJob;

	/** 国籍 */
	private String patientNationality;

	/** 身份证号 */
	private String patientIdNumber;
	/** 其它处理结果*/
	 private String otherProcessResult;


	/** oa返回的单据号*/
	private Long oaRequestId;

	/** 是否归档（1 是 0 否）*/
	private Integer isArchived;

	/** 创建者ID*/
	private Long creator;
	
	/** 创建时间 */
	private java.util.Date createDate;
	
	private Integer subspecialtyCode;

	private Integer gradeOneCode;

	private Integer gradeTwoACode;
	
	private Integer gradeTwoBCode;

	private Integer damageType;

	// 人资等级（10集团总部 11省区 12省会级医院 13地市级医院 14县市级医院 15眼科门诊 20大区 30区域）
	private String ehrLevel;



	/** 手输原因*/
	private String manualInputReason;


}
