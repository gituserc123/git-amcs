package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import com.aier.cloud.basic.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 纠纷事项表
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_DISPUTE_MATTER")
@Data
public class LawDisputeMatter extends BaseEntity<LawDisputeMatter> {

    private static final long serialVersionUID = 1L;

    /** 机构ID，关联医院或集团 */
    @Comment(value = "机构ID，关联医院或集团")
    @TableField(value = "inst_id")
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    @Comment(value = "机构名称，包含医院或集团名称")
    @TableField(value = "inst_name")
    private String instName;

    /** 省区/上级机构ID */
    @Comment(value = "省区/上级机构ID")
    @TableField(value = "super_inst_id")
    private Long superInstId;

    /** 省区/上级机构名称 */
    @Comment(value = "省区/上级机构名称")
    @TableField(value = "super_inst_name")
    private String superInstName;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    @Comment(value = "状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档")
    @TableField(value = "status")
    private Integer status;

    /** 对方全称 */
    @Comment(value = "对方全称")
    @TableField(value = "opponent_full_name")
    private String opponentFullName;

    /** 我方全称 */
    @Comment(value = "我方全称")
    @TableField(value = "our_full_name")
    private String ourFullName;

    /** 申报单位名称 */
    @Comment(value = "申报单位名称")
    @TableField(value = "apply_unit")
    private String applyUnit;

    /** 申报人名称 */
    @Comment(value = "申报人名称")
    @TableField(value = "applicant_name")
    private String applicantName;

    /** 申报人联系方式 */
    @Comment(value = "申报人联系方式")
    @TableField(value = "applicant_phone")
    private String applicantPhone;

    /** 纠纷事项产生的原因及经过 */
    @Comment(value = "纠纷事项产生的原因及经过")
    @TableField(value = "dispute_cause_process")
    private String disputeCauseProcess;

    /** 是否涉及网络舆情 */
    @Comment(value = "是否涉及网络舆情")
    @TableField(value = "is_public_opinion")
    private String isPublicOpinion;

    /** 网络舆情发展情况 */
    @Comment(value = "网络舆情发展情况")
    @TableField(value = "opinion_development")
    private String opinionDevelopment;

    /** 申报单位处理措施 */
    @Comment(value = "申报单位处理措施")
    @TableField(value = "handling_measures")
    private String handlingMeasures;

    /** 申报单位处理意见及诉求 */
    @Comment(value = "申报单位处理意见及诉求")
    @TableField(value = "opinion_requirement")
    private String opinionRequirement;

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

    /** 修改者ID */
    @Comment(value = "修改者ID")
    @TableField(value = "modifer")
    @NotBlank
    private Long modifer;

    /** 修改时间 */
    @Comment(value = "修改时间")
    @TableField(value = "modify_date", fill = FieldFill.UPDATE)
    @NotBlank
    private Date modifyDate;

    // 扩展字段（根据业务需要添加）
    /** 开始时间（查询用） */
    @TableField(exist = false)
    private Date startDate;

    /** 结束时间（查询用） */
    @TableField(exist = false)
    private Date endDate;

    /** 关联附件列表 */
    @TableField(exist = false)
    private List<LawAttachment> attachs;

    /** 业务编码 */
    @TableField(exist = false)
    private String bizCode;

    @TableField(exist = false)
    private String currentNodeName; // 当前处理节点名称
}