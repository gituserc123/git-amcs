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
 * 廉洁合规报备表实体类，用于记录和管理廉洁合规相关事件的报备信息
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_INTEGRITY_FILING")
@Data
public class LawIntegrityFiling extends BaseEntity<LawIntegrityFiling> {

    private static final long serialVersionUID = 1L;

    /** 唯一标识，主键 */
    @Comment(value = "唯一标识，主键")
    private Long id;

    /** 编号:hospId + 年月日 + 2位序列号 */
    @Comment(value = "编号:hospId + 年月日 + 2位序列号")
    @TableField(value = "event_sn")
    private String eventSn;

    /** 机构ID，关联医院或集团 */
    @Comment(value = "机构ID，关联医院或集团")
    @TableField(value = "inst_id")
    private Long instId;

    /** 填报机构名称 */
    @Comment(value = "填报机构名称")
    @TableField(value = "inst_name")
    private String instName;

    /** 填报人姓名 */
    @Comment(value = "填报人姓名")
    @TableField(value = "reporter_name")
    private String reporterName;

    /** 填报时间（业务填报日期） */
    @Comment(value = "填报时间（业务填报日期）")
    @TableField(value = "report_date")
    private Date reportDate;

    /** 事件类型，见码表:integrity_event_type */
    @Comment(value = "事件类型，见码表:integrity_event_type")
    @TableField(value = "event_type")
    private Integer eventType;

    /** 事件类型其他描述 */
    @Comment(value = "事件类型其他描述")
    @TableField(value = "event_type_other")
    private String eventTypeOther;

    /** 事件实际发生时间 */
    @Comment(value = "事件实际发生时间")
    @TableField(value = "occurrence_time")
    private Date occurrenceTime;

    /** 事件详细经过描述 */
    @Comment(value = "事件详细经过描述")
    @TableField(value = "event_description")
    private String eventDescription;

    /** 涉及金额 */
    @Comment(value = "涉及金额")
    @TableField(value = "amount")
    private BigDecimal amount;

    /** 关联方名称（患者/供应商/其他主体） */
    @Comment(value = "关联方名称（患者/供应商/其他主体）")
    @TableField(value = "related_party_name")
    private String relatedPartyName;

    /** 最终处理结果及措施 */
    @Comment(value = "最终处理结果及措施")
    @TableField(value = "handling_result")
    private String handlingResult;

    /** 医院hospId */
    @TableField("HOSP_ID")
    private Long hospId;

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


}