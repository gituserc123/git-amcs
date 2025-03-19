package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 外聘法律顾问信息登记表实体类
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_EXTERNAL_LEGAL_ADVISOR")
@Data
public class LawExternalLegalAdvisor extends BaseEntity<LawExternalLegalAdvisor> {

    private static final long serialVersionUID = -4413922036810547519L;

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

    /** 申报单位名称 */
    @Comment(value = "申报单位名称")
    @TableField(value = "apply_unit")
    private String applyUnit;

    /** 申报人姓名 */
    @Comment(value = "申报人姓名")
    @TableField(value = "applicant_name")
    private String applicantName;

    /** 律师事务所全称 */
    @Comment(value = "律师事务所全称")
    @TableField(value = "law_firm_name")
    private String lawFirmName;

    /** 统一社会信用代码 */
    @Comment(value = "统一社会信用代码")
    @TableField(value = "unified_credit_code")
    private String unifiedCreditCode;

    /** 律师事务所注册地址 */
    @Comment(value = "律师事务所注册地址")
    @TableField(value = "registered_address")
    private String registeredAddress;

    /** 主办律师姓名 */
    @Comment(value = "主办律师姓名")
    @TableField(value = "host_lawyer_name")
    private String hostLawyerName;

    /** 主办律师联系方式 */
    @Comment(value = "主办律师联系方式")
    @TableField(value = "host_lawyer_contact")
    private String hostLawyerContact;

    /** 擅长领域，见码表:expertise_field */
    @Comment(value = "擅长领域，见码表:expertise_field")
    @TableField(value = "expertise_field")
    private Integer expertiseField;

    /** 擅长领域补充说明 */
    @Comment(value = "擅长领域补充说明")
    @TableField(value = "expertise_other")
    private String expertiseOther;

    /** 资质证明文件信息 */
    @Comment(value = "资质证明文件信息")
    @TableField(value = "qualification_proof")
    private String qualificationProof;

    /** 服务类型，见码表:service_type */
    @Comment(value = "服务类型，见码表:service_type")
    @TableField(value = "service_type")
    private Integer serviceType;

    /** 服务类型补充说明 */
    @Comment(value = "服务类型补充说明")
    @TableField(value = "service_type_other")
    private String serviceTypeOther;

    /** 服务内容与范围 */
    @Comment(value = "服务内容与范围")
    @TableField(value = "service_scope")
    private String serviceScope;

    /** 备注信息 */
    @Comment(value = "备注信息")
    @TableField(value = "remark")
    private String remark;

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