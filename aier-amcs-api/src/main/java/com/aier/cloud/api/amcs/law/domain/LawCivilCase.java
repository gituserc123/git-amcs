package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 民事诉讼仲裁案件查询条件
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Data
public class LawCivilCase extends BaseEntity {

    /** 机构ID，关联医院或集团 */
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    private String instName;

    /** 省区/上级机构ID */
    private Long superInstId;

    /** 省区/上级机构名称 */
    private String superInstName;

    /** 案件类别，如民事/裁等 */
    private String caseCategory;

    /** 案件类型一级，见码表:case_type_one */
    private String caseTypeOne;

    /** 案件类型二级，见码表:case_type_two */
    private String caseTypeTwo;

    /** 案件类型二级其他描述 */
    private String caseTypeTwoDesc;

    /** 我方诉讼主体 */
    private String ourLitigationSubject;

    /** 原告（申请人）全称 */
    private String plaintiffName;

    /** 被告（被申请人）全称 */
    private String defendantName;

    /** 案由 */
    private String caseCause;

    /** 案由其他描述 */
    private String caseCauseDesc;

    /** 案号 */
    private String caseNo;

    /** 案情简介 */
    private String caseDesc;

    /** 涉案金额 */
    private BigDecimal involvedAmount;

    /** 诉讼阶段 */
    private String litigationPhase;

    /** 诉讼阶段其他描述 */
    private String litigationPhaseDesc;

    /** 诉讼进展 */
    private String litigationProgress;

    /** 仲裁阶段 */
    private String arbitrationPhase;

    /** 仲裁阶段其他描述 */
    private String arbitrationPhaseDesc;

    /** 收案法院名称 */
    private String courtName;

    /** 承办人/经办人姓名 */
    private String handlerName;

    /** 承办人/经办人联系电话 */
    private String handlerPhone;

    /** 申报单位名称 */
    private String applyUnit;

    /** 申报人名称 */
    private String applicantName;

    /** 申报人联系方式 */
    private String applicantPhone;

    /** 诉讼、仲裁案件产生的原因及经过 */
    private String caseReasonProcess;

    /** 是否涉及网络舆情，Y/N */
    private String isPublicOpinion;

    /** 申报单位初步处理意见及诉求 */
    private String opinionRequirement;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;

    /** 开始时间（用于范围查询） */
    private Date startDate;

    /** 结束时间（用于范围查询） */
    private Date endDate;

    private List<LawAttachment> attachs;


}
