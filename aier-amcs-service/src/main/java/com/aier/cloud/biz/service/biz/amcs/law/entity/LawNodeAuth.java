package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_NODE_AUTH
 *
 * @since 2025-02-25
 */
@TableName("T_LAW_NODE_AUTH")
@Data
public class LawNodeAuth extends BaseEntity<LawNodeAuth> {

    /** 节点Id */
    @Comment(value = "节点Id")
    @TableField("node_id")
    private Long nodeId;

    /** 节点名称 */
    @Comment(value = "节点名称")
    @TableField("node_name")
    private String nodeName;

    /** 节点编码 */
    @Comment(value = "节点编码")
    @TableField("node_code")
    private String nodeCode;

    /** 用户Id */
    @Comment(value = "用户Id")
    @TableField("staff_id")
    private Long staffId;

    /** 用户姓名 */
    @Comment(value = "用户姓名")
    @TableField("staff_name")
    private String staffName;

    /** 用户机构Id */
    @Comment(value = "用户机构Id")
    @TableField("staff_inst_id")
    private Long staffInstId;

    /** 用户机构名称 */
    @Comment(value = "用户机构名称")
    @TableField("staff_inst_name")
    private String staffInstName;

    /** 用户工号 */
    @Comment(value = "用户工号")
    @TableField("staff_code")
    private String staffCode;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField("creator")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;


    @TableField(exist = false)
    private String nodeNames;


    @TableField(exist = false)
    private String nodeIds;

}