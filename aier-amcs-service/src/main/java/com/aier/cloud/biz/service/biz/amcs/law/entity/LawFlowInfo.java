package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_FLOW_INFO
 *
 * @since 2025-02-24
 */
@TableName("T_LAW_FLOW_INFO")
@Data
public class LawFlowInfo extends BaseEntity<LawFlowInfo> {

    /** 流程名称 */
    @Comment(value = "流程名称")
    @TableField(value = "FLOW_NAME")
    private String flowName;

    /** 流程描述 */
    @Comment(value = "流程描述")
    @TableField(value = "FLOW_DESC")
    private String flowDesc;

    /** 创建者ID */
    @Comment(value = "创建者ID")
    @TableField(value = "CREATOR")
    private Long creator;

    /** 创建时间 */
    @Comment(value = "创建时间")
    @TableField(value="create_date", fill= FieldFill.INSERT)
    private Date createDate;
}