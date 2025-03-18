package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_FLOW_INSTANCE
 *
 * @since 2025-02-25
 */
@TableName("T_LAW_FLOW_INSTANCE")
@Data
public class LawFlowInstance extends BaseEntity<LawFlowInstance> {

    /** 流程信息ID */
    @Comment(value = "流程信息ID")
    @TableField(value = "flow_id")
    private Long flowId;

    /** 业务表Id */
    @Comment(value = "业务表Id")
    @TableField("biz_id")
    private Long bizId;

    /** 业务表Code;值:各个业务表表英文名,比如:civil-case */
    @Comment(value = "业务表Code;值:各个业务表表英文名,比如:civil-case")
    @TableField("biz_code")
    private String bizCode;

    /** 业务表类型Type;值:1,2,3,4,5,6.... */
    @Comment(value = "业务表类型Type;值:1,2,3,4,5,6....")
    @TableField("biz_type")
    private Integer bizType;

    /** 业务表名称;各个业务表表中文名,比如:民事诉讼仲裁案件主表 */
    @Comment(value = "业务表名称;各个业务表表中文名,比如:民事诉讼仲裁案件主表")
    @TableField("biz_name")
    private String bizName;

    /** 上一处理节点 */
    @Comment(value = "上一处理节点")
    @TableField("prev_node")
    private String prevNode;

    /** 上一处理节点名称 */
    @Comment(value = "上一处理节点名称")
    @TableField("prev_node_name")
    private String prevNodeName;

    /** 当前处理节点 */
    @Comment(value = "当前处理节点")
    @TableField("current_node")
    private String currentNode;

    /** 当前处理节点名称 */
    @Comment(value = "当前处理节点名称")
    @TableField("current_node_name")
    private String currentNodeName;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField("creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;
}