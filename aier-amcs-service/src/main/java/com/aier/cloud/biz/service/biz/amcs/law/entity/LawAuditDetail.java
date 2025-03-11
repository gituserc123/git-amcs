package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_AUDIT_DETAIL
 *
 * @since 2025-02-25
 */
@TableName("T_LAW_AUDIT_DETAIL")
@Data
public class LawAuditDetail extends LawBaseEntity<LawAuditDetail> {

    /** 流程实例表Id */
    @Comment(value = "流程实例表Id")
    @TableField("instance_id")
    private Long instanceId;

    /** 当前处理节点Id */
    @Comment(value = "当前处理节点Id")
    @TableField("node_id")
    private Long nodeId;

    /** 当前操作用户 */
    @Comment(value = "当前操作用户")
    @TableField("staff_id")
    private Long staffId;

    /** 操作类型(值: '提交-SUBMIT', '退回-REJECT', ...) */
    @Comment(value = "操作类型(值: '提交-SUBMIT', '退回-REJECT', ...)")
    @TableField("action")
    private String action;

    /** 下一处理节点Id */
    @Comment(value = "下一处理节点Id")
    @TableField("next_node_id")
    private Long nextNodeId;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField("creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;
}