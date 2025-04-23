package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 廉洁合规报备表实体类，用于记录和管理廉洁合规相关事件的报备信息
 *
 * @author 自动生成
 * @since 2023-10-18
 */
@Data
public class LawIntegrityFiling extends BaseEntity {

    /** 唯一标识，主键 */
    private Long id;

    /** 编号:hospId + 年月日 + 2位序列号 */
    private String eventSn;

    /** 机构ID，关联医院或集团 */
    private Long instId;

    /** 填报机构名称 */
    private String instName;

    /** 填报人姓名 */
    private String reporterName;

    /** 填报时间（业务填报日期） */
    private Date reportDate;

    /** 事件类型，见码表:integrity_event_type */
    private Integer eventType;

    /** 事件类型其他描述 */
    private String eventTypeOther;

    /** 事件实际发生时间 */
    private Date occurrenceTime;

    /** 事件详细经过描述 */
    private String eventDescription;

    /** 涉及金额 */
    private BigDecimal amount;

    /** 关联方名称（患者/供应商/其他主体） */
    private String relatedPartyName;

    /** 最终处理结果及措施 */
    private String handlingResult;

    /** 医院hospId */
    private Long hospId;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;

    private List<LawAttachment> attachs;

    private String bizCode;

    private String bizType;

}