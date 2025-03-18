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
 * T_LAW_ADMIN_PENALTY
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_ADMIN_PENALTY")
@Data
public class LawAdminPenalty extends BaseEntity<LawAdminPenalty> {

    private static final long serialVersionUID = 1L;

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

    /** 受处罚/告知的单位全称或个人姓名 */
    @Comment(value = "受处罚/告知的单位全称或个人姓名")
    @TableField(value = "party_name")
    private String partyName;

    /** 行政处罚/告知的行政机关全称 */
    @Comment(value = "行政处罚/告知的行政机关全称")
    @TableField(value = "authority_name")
    private String authorityName;

    /** 处罚告知单位 */
    @Comment(value = "处罚告知单位")
    @TableField(value = "penalized_unit")
    private String penalizedUnit;

    /** 文书类型,码表:document_type */
    @Comment(value = "文书类型,码表:document_type")
    @TableField(value = "document_type")
    private Integer documentType;

    /** 文书类型其他 */
    @Comment(value = "文书类型其他")
    @TableField(value = "document_type_other")
    private String documentTypeOther;

    /** 文书编号 */
    @Comment(value = "文书编号")
    @TableField(value = "document_no")
    private String documentNo;

    /** 受案时间日期 */
    @Comment(value = "受案时间日期")
    @TableField(value = "filing_date")
    private Date filingDate;

    /** 处罚日期 */
    @Comment(value = "处罚日期")
    @TableField(value = "penalty_date")
    private Date penaltyDate;

    /** 收文日期 */
    @Comment(value = "收文日期")
    @TableField(value = "receive_date")
    private Date receiveDate;

    /** 案件名称 */
    @Comment(value = "案件名称")
    @TableField(value = "case_name")
    private String caseName;

    /** 处罚事由,码表:penalty_reason */
    @Comment(value = "处罚事由,码表:penalty_reason")
    @TableField(value = "penalty_reason")
    private Integer penaltyReason;

    /** 处罚事由其他 */
    @Comment(value = "处罚事由其他")
    @TableField(value = "penalty_reason_other")
    private String penaltyReasonOther;

    /** 处罚类别,码表:penalty_category */
    @Comment(value = "处罚类别,码表:penalty_category")
    @TableField(value = "penalty_category")
    private Integer penaltyCategory;

    /** 处罚类别其他 */
    @Comment(value = "处罚类别其他")
    @TableField(value = "penalty_category_other")
    private String penaltyCategoryOther;

    /** 处罚依据 */
    @Comment(value = "处罚依据")
    @TableField(value = "penalty_basis")
    private String penaltyBasis;

    /** 处罚措施 */
    @Comment(value = "处罚措施")
    @TableField(value = "penalty_measures")
    private String penaltyMeasures;

    /** 涉案背景 */
    @Comment(value = "涉案背景")
    @TableField(value = "case_background")
    private String caseBackground;

    /** 事件经过 */
    @Comment(value = "事件经过")
    @TableField(value = "case_process")
    private String caseProcess;

    /** 涉案金额（万元） */
    @Comment(value = "涉案金额（万元）")
    @TableField(value = "involved_amount")
    private BigDecimal involvedAmount;

    /** 当前状态 */
    @Comment(value = "当前状态")
    @TableField(value = "current_status")
    private String currentStatus;

    /** 备注 */
    @Comment(value = "备注")
    @TableField(value = "remarks")
    private String remarks;

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

    /** 申报单位已经采取的处理措施 */
    @Comment(value = "申报单位已经采取的处理措施")
    @TableField(value = "handling_measures")
    private String handlingMeasures;

    /** 是否涉及网络舆情 */
    @Comment(value = "是否涉及网络舆情")
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