package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_FLOW_NODE
 *
 * @since 2025-02-25
 */
@TableName("T_LAW_FLOW_NODE")
@Data
public class LawFlowNode extends BaseEntity<LawFlowNode> {

    /** 流程信息ID */
    @Comment(value = "流程信息ID")
    @TableField(value = "flow_id")
    private Long flowId;

    /** 节点ID */
    @Comment(value = "节点ID")
    @TableField(value = "node_id")
    private Long nodeId;

    /** 节点编码 */
    @Comment(value = "节点编码")
    @TableField(value = "node_code")
    private String nodeCode;

    /** 节点序号(值:1,2,3,4,5,6....) */
    @Comment(value = "节点序号(值:1,2,3,4,5,6....)")
    @TableField(value = "node_order")
    private Integer nodeOrder;

    /** 节点名称 */
    @Comment(value = "节点名称")
    @TableField(value = "node_name")
    private String nodeName;

    /** 流程顺序号(值:1,5,10...) */
    @Comment(value = "流程顺序号(值:1,5,10...)")
    @TableField(value = "step_order")
    private Integer stepOrder;

    /** 节点类型(如：审批-Audit, 抄送-CC,...) */
    @Comment(value = "节点类型(如：审批-Audit, 抄送-CC,...)")
    @TableField(value = "node_type")
    private String nodeType;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField(value = "creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;
}