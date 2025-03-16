package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 纠纷事项表
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Data
public class LawDisputeMatter extends BaseEntity {

    /** 机构ID，关联医院或集团 */
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    private String instName;

    /** 省区/上级机构ID */
    private Long superInstId;

    /** 省区/上级机构名称 */
    private String superInstName;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    private Integer status;

    /** 对方全称 */
    private String opponentFullName;

    /** 我方全称 */
    private String ourFullName;

    /** 申报单位名称 */
    private String applyUnit;

    /** 申报人名称 */
    private String applicantName;

    /** 申报人联系方式 */
    private String applicantPhone;

    /** 纠纷事项产生的原因及经过 */
    private String disputeCauseProcess;

    /** 是否涉及网络舆情 */
    private String isPublicOpinion;

    /** 网络舆情发展情况 */
    private String opinionDevelopment;

    /** 申报单位处理措施 */
    private String handlingMeasures;

    /** 申报单位处理意见及诉求 */
    private String opinionRequirement;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;

    // 以下为扩展字段，根据实际业务需要添加
    /** 开始时间（用于范围查询） */
    private Date startDate;

    /** 结束时间（用于范围查询） */
    private Date endDate;

    /** 附件列表 */
    private List<LawAttachment> attachs;

    /** 业务编码（可扩展字段） */
    private String bizCode;

    private String bizType;
}