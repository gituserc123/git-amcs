package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 刑事案件登记表实体类
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_CRIMINAL_CASE")
@Data
public class LawCriminalCase extends BaseEntity<LawCriminalCase> {

    private static final long serialVersionUID = 1L;

    /** 机构ID，关联医院或集团 */
    @TableField("INST_ID")
    @Comment(value = "机构ID，关联医院或集团")
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    @TableField("INST_NAME")
    @Comment(value = "机构名称，包含医院或集团名称")
    private String instName;

    /** 省区/上级机构ID */
    @TableField("SUPER_INST_ID")
    @Comment(value = "省区/上级机构ID")
    private Long superInstId;

    /** 省区/上级机构名称 */
    @TableField("SUPER_INST_NAME")
    @Comment(value = "省区/上级机构名称")
    private String superInstName;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    @TableField("STATUS")
    @Comment(value = "状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档")
    private Integer status;

    /** 犯罪嫌疑人名称 */
    @TableField("SUSPECT_NAME")
    @Comment(value = "犯罪嫌疑人名称，名称应为全称，须与相关证照或自然人身份证登记名称一致")
    private String suspectName;

    /** 案件主办公安机关名称 */
    @TableField("HANDLING_PSB")
    @Comment(value = "案件主办公安机关名称")
    private String handlingPsb;

    /** 案件主办检察院名称 */
    @TableField("PROSECUTOR_OFFICE")
    @Comment(value = "案件主办检察院名称")
    private String prosecutorOffice;

    /** 案件主审法院名称 */
    @TableField("COURT_NAME")
    @Comment(value = "案件主审法院名称")
    private String courtName;

    /** 案件类型 */
    @TableField("CASE_TYPE")
    @Comment(value = "设置的案件类型，选择对应选项;码表:CRIMINAL_CASE_TYPE")
    private String caseType;

    /** 罪名 */
    @TableField("CHARGE_NAME")
    @Comment(value = "公安机关、检察院相关文书或者法院传票内容，在系统设置的罪名中选择;码表:..")
    private String chargeName;

    /** 罪名其他描述 */
    @TableField("CHARGE_NAME_DESC")
    @Comment(value = "罪名其他描述")
    private String chargeNameDesc;

    /** 案号 */
    @TableField("CASE_NO")
    @Comment(value = "公安机关、检察院相关文书或者法院传票内容填写具体案号")
    private String caseNo;

    /** 案件描述 */
    @TableField("CASE_DESC")
    @Comment(value = "阐述填报案件发生的经过及前因后果")
    private String caseDesc;

    /** 涉案金额 */
    @TableField("INVOLVED_AMOUNT")
    @Comment(value = "案件涉及的具体金额（如有）")
    private BigDecimal involvedAmount;

    /** 诉讼阶段 */
    @TableField("LITIGATION_PHASE")
    @Comment(value = "案件办理阶段的不同;码表:litigation_phase")
    private String litigationPhase;

    /** 当前状态 */
    @TableField("CURRENT_STATUS")
    @Comment(value = "该案已经采取的相应措施，与办案机关、审判机关的交流情况，案件的具体办理进度等")
    private String currentStatus;

    /** 备注 */
    @TableField("REMARKS")
    @Comment(value = "申报单位其他认为需要说明的事项")
    private String remarks;

    /** 创建者ID */
    @TableField("CREATOR")
    @Comment(value = "创建者ID")
    private Long creator;

    /** 创建时间 */
    @TableField("CREATE_DATE")
    @Comment(value = "创建时间")
    private Date createDate;

    @TableField(exist = false)
    private String currentNodeName; // 当前处理节点名称
}