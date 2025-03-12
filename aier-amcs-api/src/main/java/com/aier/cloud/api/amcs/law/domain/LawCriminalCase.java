package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 刑事案件登记表实体类
 *
 * @since 2023-10-18
 */
@Data
public class LawCriminalCase extends BaseEntity {

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

    /** 犯罪嫌疑人名称 */
    private String suspectName;

    /** 案件主办公安机关名称 */
    private String handlingPsb;

    /** 案件主办检察院名称 */
    private String prosecutorOffice;

    /** 案件主审法院名称 */
    private String courtName;

    /** 案件类型 */
    private String caseType;

    /** 罪名 */
    private String chargeName;

    /** 罪名其他描述 */
    private String chargeNameDesc;

    /** 案号 */
    private String caseNo;

    /** 案件描述 */
    private String caseDesc;

    /** 涉案金额 */
    private BigDecimal involvedAmount;

    /** 诉讼阶段 */
    private String litigationPhase;

    /** 当前状态 */
    private String currentStatus;

    /** 备注 */
    private String remarks;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;

    private List<LawAttachment> attachs;

    private String bizCode;
}