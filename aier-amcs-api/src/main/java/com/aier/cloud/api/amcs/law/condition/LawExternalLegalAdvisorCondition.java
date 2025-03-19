package com.aier.cloud.api.amcs.law.condition;

import com.aier.cloud.api.amcs.law.domain.LawAttachment;
import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 外聘法律顾问信息登记表实体类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Data
public class LawExternalLegalAdvisorCondition extends LawBaseCondition {

    /** 主键ID */
    private Long id;

    /** 申报单位名称 */
    private String applyUnit;

    /** 申报人姓名 */
    private String applicantName;

    /** 律师事务所全称 */
    private String lawFirmName;

    /** 统一社会信用代码 */
    private String unifiedCreditCode;

    /** 律师事务所注册地址 */
    private String registeredAddress;

    /** 主办律师姓名 */
    private String hostLawyerName;

    /** 主办律师联系方式 */
    private String hostLawyerContact;

    /** 擅长领域，见码表:expertise_field */
    private Integer expertiseField;

    /** 擅长领域补充说明 */
    private String expertiseOther;

    /** 资质证明文件信息 */
    private String qualificationProof;

    /** 服务类型，见码表:service_type */
    private Integer serviceType;

    /** 服务类型补充说明 */
    private String serviceTypeOther;

    /** 服务内容与范围 */
    private String serviceScope;

    /** 备注信息 */
    private String remark;

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

    /** 附件列表 */
    private List<LawAttachment> attachs;

    /** 业务编码 */
    private String bizCode;

    /** 业务类型 */
    private String bizType;
}