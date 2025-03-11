package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * 审核意见表
 *
 * @since 2025-02-25
 */
@TableName("T_LAW_AUDIT_OPINION")
@Data
public class LawAuditOpinion extends LawBaseEntity<LawAuditOpinion> {

    /** 审核明细表Id */
    @Comment(value = "审核明细表Id")
    @TableField(value = "detail_id")
    private Long detailId;

    /** 审核意见 */
    @Comment(value = "审核意见")
    @TableField(value = "opinion")
    private String opinion;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField(value = "creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;

    @TableField(exist=false)
    private String nodeName;

    @TableField(exist=false)
    private String creatorName;


}