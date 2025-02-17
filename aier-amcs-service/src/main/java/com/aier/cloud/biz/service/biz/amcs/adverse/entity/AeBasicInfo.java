package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;

import lombok.Data;

import com.aier.cloud.basic.core.base.BaseEntity;

import java.math.BigDecimal;

/**
 * T_AE_BASIC_INFO
 *
 * @since 2022-12-05 11:21:46
 */
@TableName("T_AE_BASIC_INFO")
@Data
public class AeBasicInfo extends BaseEntity<AeBasicInfo> {

	private static final long serialVersionUID = -4413922036810547519L;

	/** 其它处理结果*/
	@Comment(value="其它处理结果")
	@TableField(value="other_process_result")
	private String otherProcessResult;

	/** 主不良事件（不良事件合并后选择的主事件）*/
	@Comment(value="主不良事件（不良事件合并后选择的主事件）")
	@TableField(value="master_id")
	private Long masterId;

	/** 前置事件(多次上报前一次ID)*/
	@Comment(value="前置事件(多次上报前一次ID)")
	@TableField(value="prev_id")
	private Long prevId;

	/** 医院ID*/
	@Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 医院名称*/
	@Comment(value="医院名称")
	@TableField(value="hosp_name")
	private String hospName;

	/** 院内事件(0.否，1.是)*/
	@Comment(value="院内事件(0.否，1.是)")
	@TableField(value="hosp_sign")
	private Integer hospSign;

	/** 患者ID*/
	@Comment(value="患者ID")
	@TableField(value="patient_id")
	private Long patientId;

	/** 患者编号(门诊号、病案号、病历号等)*/
	@Comment(value="患者编号(门诊号、病案号、病历号等)")
	@TableField(value="patient_no")
	private String patientNo;

	/** 患者就诊号(门诊号、住院号)*/
	@Comment(value="患者就诊号(门诊号、住院号)")
	@TableField(value="patient_visit_no")
	private String patientVisitNo;

	/** 患者姓名*/
	@Comment(value="患者姓名")
	@TableField(value="patient_name")
	private String patientName;

	/** 患者性别*/
	@Comment(value="患者性别")
	@TableField(value="patient_sex")
	private Integer patientSex;

	/** 患者年龄*/
	@Comment(value="患者年龄")
	@TableField(value="patient_age")
	private Integer patientAge;

	/** 患者上报次数*/
	@Comment(value="患者上报次数")
	@TableField(value="report_times")
	private Integer reportTimes;

	/** 事件名称*/
	@Comment(value="事件名称")
	@TableField(value="event_name")
	private String eventName;

	/** 所在节点 （1 上报 2医院 3 省区 4 集团  5专家点评  9 完结）*/
	@Comment(value="所在节点 （1 上报 2医院 3 省区 4 集团  5专家点评  9 完结）")
	@TableField(value="node")
	private Integer node;

	/** 事件状态 （0 正常 1 结束 2 取消）
	 */
	@TableField(value="status")
	private Integer status;

	/** 亚专科(码表: sub_type)*/
	@Comment(value="亚专科(码表: sub_type)")
	@TableField(value="subspecialty")
	private String subspecialty;

	/** 发生日期*/
	@Comment(value="发生日期")
	@TableField(value="event_date")
	private java.util.Date eventDate;

	/** 日期类型(码表: date_type)*/
	@Comment(value="日期类型(码表: date_type)")
	@TableField(value="date_type")
	private String dateType;

	/** 事件分类一级(码表: grade_one)*/
	@Comment(value="事件分类一级(码表: grade_one)")
	@TableField(value="grade_one")
	private String gradeOne;

	/** 事件分类二级I(码表: grade_two_a)*/
	@Comment(value="事件分类二级I(码表: grade_two_a)")
	@TableField(value="grade_two_a")
	private String gradeTwoA;

	/** 事件分类二级II(码表: grade_two_b)*/
	@Comment(value="事件分类二级II(码表: grade_two_b)")
	@TableField(value="grade_two_b", strategy=FieldStrategy.IGNORED)
	private String gradeTwoB;

	/** 事件分类二级说明*/
	@Comment(value="事件分类二级说明")
	@TableField(value="grade_two_remark")
	private String gradeTwoRemark;

	/** 事件处理进展(码表: progress_type)*/
	@Comment(value="事件处理进展(码表: progress_type)")
	@TableField(value="process_progress")
	private String processProgress;

	/** 事件处理方式(码表: process_type)*/
	@Comment(value="事件处理方式(码表: process_type)")
	@TableField(value="process_type")
	private String processType;

	/** 赔偿金额*/
	@Comment(value="赔偿金额")
	@TableField(value="compensation_amount")
	private java.math.BigDecimal compensationAmount;

	/** 减免金额*/
	@Comment(value="减免金额")
	@TableField(value="deduction_amount")
	private java.math.BigDecimal deductionAmount;

	/** 事件分级(码表:event_level)*/
	@Comment(value="事件分级(码表:event_level)")
	@TableField(value="event_level")
	private String eventLevel;

	/** 发生地点(码表: address_type)*/
	@Comment(value="发生地点(码表: address_type)")
	@TableField(value="address")
	private String address;

	/** 发生时段(码表: period_type)*/
	@Comment(value="发生时段(码表: period_type)")
	@TableField(value="period")
	private String period;

	/** 发现者(码表: person_type)*/
	@Comment(value="发现者(码表: person_type)")
	@TableField(value="finder")
	private String finder;

	/** 是否构成纠纷(0 否 1 是)*/
	@Comment(value="是否构成纠纷(0 否 1 是)")
	@TableField(value="dispute_sign")
	private Integer disputeSign;

	/** 是否完结(0 否 1 是)*/
	@Comment(value="是否完结(0 否 1 是)")
	@TableField(value="finish_sign")
	private Integer finishSign;

	/** 当事人ID*/
	@Comment(value="当事人ID")
	@TableField(value="staff_id")
	private Long staffId;

	/** 当事人年龄*/
	@Comment(value="当事人年龄")
	@TableField(value="staff_age")
	private Integer staffAge;

	/** 当事人性别(码表：sex)  */
	@Comment(value="当事人性别(码表：sex)")
	@TableField(value="staff_sex")
	private String staffSex;

	/** 当事人学历(码表: education_type) */
	@Comment(value="当事人学历(码表: education_type)")
	@TableField(value="staff_degree")
	private String staffDegree;

	/** 当事人在职情况(码表:work_type)*/
	@Comment(value="当事人在职情况(码表:work_type)")
	@TableField(value="staff_work")
	private String staffWork;

	/** 当事人工作年限(码表: work_year_type)*/
	@Comment(value="当事人工作年限(码表: work_year_type)")
	@TableField(value="staff_years")
	private String staffYears;

	/** 当事人职称(码表:technical_post_type)*/
	@Comment(value="当事人职称(码表:technical_post_type)")
	@TableField(value="technical_post")
	private String technicalPost;

	/** 当事人职称获取时间*/
	@Comment(value="当事人职称获取时间")
	@TableField(value="technical_post_date")
	private String technicalPostDate;

	/** 当事人类别(码表:staff_type) */
	@Comment(value="当事人类别(码表:staff_type)")
	@TableField(value="staff_type")
	private String staffType;

	/** 当事人姓名*/
	@Comment(value="当事人姓名")
	@TableField(value="staff_name")
	private String staffName;

	/** 原因*/
	@Comment(value="原因")
	@TableField(value="reason")
	private String reason;

	/** 意见及结果*/
	@Comment(value="意见及结果")
	@TableField(value="opinion")
	private String opinion;

	/** 创建者ID*/
	@Comment(value="创建者ID")
	@TableField(value="creator")
	@NotBlank private Long creator;

	/** 创建者姓名*/
	@Comment(value="创建者姓名")
	@TableField(value="create_name")
	@NotBlank private String createName;

	/** 创建时间*/
	@Comment(value="创建时间")
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;

	/** 事件编码*/
	@Comment(value="事件编码")
	@TableField(value="event_code")
	@NotBlank private String eventCode;

	/** 出生年月日*/
	@Comment(value="出生年月日")
	@TableField(value="patient_birth")
	private java.util.Date patientBirth;

	/** 职业*/
	@Comment(value="职业")
	@TableField(value="patient_job")
	private String patientJob;

	/** 国籍 */
	@Comment(value="国籍")
	@TableField(value="patient_nationality")
	private String patientNationality;

	/** 身份证号*/
	@Comment(value="身份证号")
	@TableField(value="patient_id_number")
	private String patientIdNumber;

	/** 事件ID*/
	@Comment(value="事件ID")
	@TableField(value="event_id")
	private Long eventId;

	/** 下一节点（1 上报 2医院 3 省区 4 集团  5专家点评  9 完结）*/
	@Comment(value="下一节点（1 上报 2医院 3 省区 4 集团  5专家点评  9 完结）")
	@TableField(value="next_node")
	private Integer nextNode;

	/** 发生科室*/
	@Comment(value="发生科室")
	@TableField(value="department")
	private String department;

	/** 当事人眼科工作年限*/
	@Comment(value="当事人眼科工作年限")
	@TableField(value="staff_years_in_eye")
	private Integer staffYearsInEye;

	/** 发生地点(其他)*/
	@Comment(value="发生地点(其他)")
	@TableField(value="address_remark")
	private String addressRemark;

	/** 亚专科*/
	@Comment(value="亚专科")
	@TableField(value="subspecialty_code")
	private Integer subspecialtyCode;

	/** 事件分类二级II*/
	@Comment(value="事件分类二级II")
	@TableField(value="grade_one_code")
	private Integer gradeOneCode;

	/** */
	@Comment(value="")
	@TableField(value="grade_two_a_code")
	private Integer gradeTwoACode;

	/** 发生地点(具体病区)*/
	@Comment(value="发生地点(具体病区)")
	@TableField(value="address_inarea_remark")
	private String addressInareaRemark;

	/** 发生地点(具体手术室)*/
	@Comment(value="发生地点(具体手术室)")
	@TableField(value="address_operroom_remark")
	private String addressOperroomRemark;

	/** 集团是否查阅(0 未查阅 1 已查阅)*/
	@Comment(value="集团是否查阅(0 未查阅 1 已查阅)")
	@TableField(value="is_group_review")
	private Integer isGroupReview;

	/** oa返回的单据号*/
	@Comment(value="oa返回的单据号")
	@TableField(value="oa_request_id")
	private Long oaRequestId;

	/** 是否归档（1 是 0 否）*/
	@Comment(value="是否归档（1 是 0 否）")
	@TableField(value="is_archived")
	private Integer isArchived;

	/** 赔付/减免金额发生时间*/
	@Comment(value="赔付/减免金额发生时间")
	@TableField(value="amount_date")
	private java.util.Date amountDate;

	/** 严重程度(码表: severity_level)*/
	@Comment(value="严重程度(码表: severity_level)")
	@TableField(value="severity_level")
	private String severityLevel;

	/** 是否高风险(0.否，1.是)*/
	@Comment(value="是否高风险(0.否，1.是)")
	@TableField(value="is_high_risk")
	private Integer isHighRisk;

	/** 是否首次上报(0.否，1.是)*/
	@Comment(value="是否首次上报(0.否，1.是)")
	@TableField(value="is_first_report")
	private Integer isFirstReport;

	@TableField(exist=false)
	private Long instId;

	@TableField(exist=false)
	private String tags;

	@TableField(exist=false)
	private BigDecimal allCompensationAmount;

	@TableField(exist=false)
	private BigDecimal allDeductionAmount;

	/** 伤害类型*/
	@Comment(value="伤害类型")
	@TableField(value="damage_type")
	private Integer damageType;

	/** 医院等级*/
	@Comment(value="人资等级（10集团总部 11省区 12省会级医院 13地市级医院 14县市级医院 15眼科门诊 20大区 30区域）")
	@TableField(value="ehr_level")
	private String ehrLevel;

	/** EHR科室主任是否查阅 (0 未查阅 1 已查阅)*/
	@Comment(value="EHR科室主任是否查阅 (0 未查阅 1 已查阅)")
	@TableField(value="is_ehr_dept_review")
	private Integer isEhrDeptReview;

	/** 上报人所属EHR标准部门编号*/
	@Comment(value="上报人所属EHR标准部门编号")
	@TableField(value="ehr_ai_stand_deptid")
	private String ehrAiStandDeptId;

	/** 上报人所属EHR标准部门编号描述*/
	@Comment(value="上报人所属EHR标准部门编号描述")
	@TableField(value="ehr_ai_stand_deptid_descr")
	private String ehrAiStandDeptIdDescr;

	/** 患者姓名*/
	@Comment(value="手输原因")
	@TableField(value="manual_input_reason")
	private String manualInputReason;

	/** 是否发送延迟消息(0 否 1 是)*/
	@Comment(value="是否发送延迟消息(0 否 1 是)")
	@TableField(value="is_send_delay")
	private Integer isSendDelay;

}