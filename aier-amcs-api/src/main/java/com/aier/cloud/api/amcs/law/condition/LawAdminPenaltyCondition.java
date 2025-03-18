package com.aier.cloud.api.amcs.law.condition;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 民事诉讼仲裁案件查询条件
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Getter
@Setter
public class LawAdminPenaltyCondition extends LawBaseCondition {

    /** 主键 */
    private Long id;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    private Integer status;

    /** 受处罚/告知的单位全称或个人姓名 */
    private String partyName;

    /** 行政处罚/告知的行政机关全称 */
    private String authorityName;

    /** 处罚告知单位 */
    private String penalizedUnit;

    /** 文书类型,码表:document_type */
    private Integer documentType;

    /** 文书类型其他 */
    private String documentTypeOther;

    /** 文书编号 */
    private String documentNo;

    /** 受案时间日期 */
    private Date filingDate;

    /** 处罚日期 */
    private Date penaltyDate;

    /** 收文日期 */
    private Date receiveDate;

    /** 案件名称 */
    private String caseName;

    /** 处罚事由,码表:penalty_reason */
    private Integer penaltyReason;

    /** 处罚事由其他 */
    private String penaltyReasonOther;

    /** 处罚类别,码表:penalty_category */
    private Integer penaltyCategory;

    /** 处罚类别其他 */
    private String penaltyCategoryOther;

    /** 处罚依据 */
    private String penaltyBasis;

    /** 处罚措施 */
    private String penaltyMeasures;

    /** 涉案背景 */
    private String caseBackground;

    /** 事件经过 */
    private String caseProcess;

    /** 涉案金额（万元） */
    private BigDecimal involvedAmount;

    /** 当前状态 */
    private String currentStatus;

    /** 备注 */
    private String remarks;

    /** 申报单位名称 */
    private String applyUnit;

    /** 申报人名称 */
    private String applicantName;

    /** 申报人联系方式 */
    private String applicantPhone;

    /** 申报单位已经采取的处理措施 */
    private String handlingMeasures;

    /** 是否涉及网络舆情 */
    private String isPublicOpinion;

    /** 申报单位初步处理意见及诉求 */
    private String opinionRequirement;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;

    /** 修改者ID */
    private Long modifer;

    /** 修改时间 */
    private Date modifyDate;

    /** 开始时间（用于范围查询） */
    private Date startDate;

    /** 结束时间（用于范围查询） */
    private Date endDate;

}
