package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import com.aier.cloud.basic.core.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * T_LAW_CIVIL_CASE
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_CIVIL_CASE")
@Data
public class LawCivilCase extends BaseEntity<LawCivilCase> {

    private static final long serialVersionUID = -4413922036810547519L;

    /** 机构ID，关联医院或集团 */
    @Comment(value = "机构ID，关联医院或集团")
    @TableField(value = "inst_id")
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    @Comment(value = "机构名称，包含医院或集团名称")
    @TableField(value = "inst_name")
    private String instName;

    /** 省区/上级机构ID */
    @Comment(value = "省区/上级机构ID")
    @TableField(value = "super_inst_id")
    private Long superInstId;

    /** 省区/上级机构名称 */
    @Comment(value = "省区/上级机构名称")
    @TableField(value = "super_inst_name")
    private String superInstName;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    @Comment(value = "状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档")
    @TableField(value = "status")
    private Integer status;

    /** 案件类别，如民事/裁等 */
    @Comment(value = "案件类别，如民事/裁等")
    @TableField(value = "case_category")
    private String caseCategory;

    /** 案件类型一级，见码表:case_type_one */
    @Comment(value = "案件类型一级，见码表:case_type_one")
    @TableField(value = "case_type_one")
    private String caseTypeOne;

    /** 案件类型二级，见码表:case_type_two */
    @Comment(value = "案件类型二级，见码表:case_type_two")
    @TableField(value = "case_type_two")
    private String caseTypeTwo;

    /** 案件类型二级其他描述 */
    @Comment(value = "案件类型二级其他描述")
    @TableField(value = "case_type_two_desc")
    private String caseTypeTwoDesc;

    /** 我方诉讼主体 */
    @Comment(value = "我方诉讼主体")
    @TableField(value = "our_litigation_subject")
    private String ourLitigationSubject;

    /** 原告（申请人）全称 */
    @Comment(value = "原告（申请人）全称")
    @TableField(value = "plaintiff_name")
    private String plaintiffName;

    /** 被告（被申请人）全称 */
    @Comment(value = "被告（被申请人）全称")
    @TableField(value = "defendant_name")
    private String defendantName;

    /** 案由 */
    @Comment(value = "案由")
    @TableField(value = "case_cause")
    private String caseCause;

    /** 案由其他描述 */
    @Comment(value = "案由其他描述")
    @TableField(value = "case_cause_desc")
    private String caseCauseDesc;

    /** 案号 */
    @Comment(value = "案号")
    @TableField(value = "case_no")
    private String caseNo;

    /** 案情简介 */
    @Comment(value = "案情简介")
    @TableField(value = "case_desc")
    private String caseDesc;

    /** 涉案金额 */
    @Comment(value = "涉案金额")
    @TableField(value = "involved_amount")
    private BigDecimal involvedAmount;

    /** 诉讼阶段 */
    @Comment(value = "诉讼阶段")
    @TableField(value = "litigation_phase")
    private String litigationPhase;

    /** 诉讼阶段其他描述 */
    @Comment(value = "诉讼阶段其他描述")
    @TableField(value = "litigation_phase_desc")
    private String litigationPhaseDesc;

    /** 诉讼进展 */
    @Comment(value = "诉讼进展")
    @TableField(value = "litigation_progress")
    private String litigationProgress;

    /** 仲裁阶段 */
    @Comment(value = "仲裁阶段")
    @TableField(value = "arbitration_phase")
    private String arbitrationPhase;

    /** 仲裁阶段其他描述 */
    @Comment(value = "仲裁阶段其他描述")
    @TableField(value = "arbitration_phase_desc")
    private String arbitrationPhaseDesc;

    /** 收案法院名称 */
    @Comment(value = "收案法院名称")
    @TableField(value = "court_name")
    private String courtName;

    /** 承办人/经办人姓名 */
    @Comment(value = "承办人/经办人姓名")
    @TableField(value = "handler_name")
    private String handlerName;

    /** 承办人/经办人联系电话 */
    @Comment(value = "承办人/经办人联系电话")
    @TableField(value = "handler_phone")
    private String handlerPhone;

    /** 申报单位名称 */
    @Comment(value = "申报单位名称")
    @TableField(value = "apply_unit")
    private String applyUnit;

    /** 申报人名称 */
    @Comment(value = "申报人名称")
    @TableField(value = "applicant_name")
    private String applicantName;

    /** 申报人联系方式 */
    @Comment(value = "申报人联系方式")
    @TableField(value = "applicant_phone")
    private String applicantPhone;

    /** 诉讼、仲裁案件产生的原因及经过 */
    @Comment(value = "诉讼、仲裁案件产生的原因及经过")
    @TableField(value = "case_reason_process")
    private String caseReasonProcess;

    /** 是否涉及网络舆情，Y/N */
    @Comment(value = "是否涉及网络舆情，Y/N")
    @TableField(value = "is_public_opinion")
    private String isPublicOpinion;

    /** 申报单位初步处理意见及诉求 */
    @Comment(value = "申报单位初步处理意见及诉求")
    @TableField(value = "opinion_requirement")
    private String opinionRequirement;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField(value = "creator")
    @NotBlank
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @NotBlank
    private Date createDate;

    @TableField(exist = false)
    private String currentNodeName; // 当前处理节点名称
}