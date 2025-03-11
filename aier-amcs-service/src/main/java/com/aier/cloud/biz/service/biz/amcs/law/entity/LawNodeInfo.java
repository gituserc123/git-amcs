package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_NODE_INFO
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_NODE_INFO")
@Data
public class LawNodeInfo extends LawBaseEntity<LawNodeInfo> {

    /** 节点编码 */
    @Comment(value = "节点编码")
    @TableField(value = "node_code")
    private String nodeCode;

    /** 节点名称 */
    @Comment(value = "节点名称")
    @TableField(value = "node_name")
    private String nodeName;

    /** 节点序号(值:1,2,3,4,5,6....) */
    @Comment(value = "节点序号(值:1,2,3,4,5,6....)")
    @TableField(value = "node_order")
    private Integer nodeOrder;

    /** 节点描述 */
    @Comment(value = "节点描述")
    @TableField(value = "node_desc")
    private String nodeDesc;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField(value = "creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;
}