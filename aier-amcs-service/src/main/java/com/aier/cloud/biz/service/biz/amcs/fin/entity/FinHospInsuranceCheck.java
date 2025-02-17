package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 医院医保检查统计表
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 09:24:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("T_FIN_HOSP_INSURANCE_CHECK")
@ApiModel(value = "FinHospInsuranceCheck对象", description = "医院医保检查统计表")
public class FinHospInsuranceCheck extends BaseEntity<FinHospInsuranceCheck> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("检查时间")
    @TableField("CHECK_DATE")
    private Date checkDate;

    @ApiModelProperty("检查机构或人员")
    @TableField("CHECK_INST_PERSON")
    private String checkInstPerson;

    @ApiModelProperty("检查内容")
    @TableField("CHECK_CONTENT")
    private String checkContent;

    @ApiModelProperty("医院涉规问题")
    @TableField("REGULATORY_ISSUES")
    private String regulatoryIssues;

    @ApiModelProperty("医院涉规金额（万元）")
    @TableField("REGULATORY_AMOUNT")
    private BigDecimal regulatoryAmount;

    @ApiModelProperty("医院申诉情况")
    @TableField("APPEAL_STATUS")
    private String appealStatus;

    @ApiModelProperty("是否结案(1-是,2-否)")
    @TableField("CASE_CLOSED")
    private Integer caseClosed;

    @ApiModelProperty("扣款（万元）")
    @TableField("DEDUCTION_AMOUNT")
    private BigDecimal deductionAmount;

    @ApiModelProperty("罚款（万元）")
    @TableField("FINE_AMOUNT")
    private BigDecimal fineAmount;

    @ApiModelProperty("合计金额（元）")
    @TableField("SUM_AMOUNT")
    private BigDecimal sumAmount;

    @ApiModelProperty("其他处罚")
    @TableField("OTHER_PENALTY")
    private String otherPenalty;

    @ApiModelProperty("医院名称")
    @TableField("HOSP_NAME")
    private String hospName;

    @ApiModelProperty("医院hospId")
    @TableField("HOSP_ID")
    private Long hospId;

    @ApiModelProperty("医院类型(上市/合伙)")
    @TableField("INVEST_NATURE")
    private String investNature;

    @ApiModelProperty("省区")
    @TableField("HOSP_PARENT")
    private String hospParent;

    @ApiModelProperty("医院等级(省级/县级/...)")
    @TableField("EHR_LEVEL")
    private String ehrLevel;

    @ApiModelProperty("创建者ID")
    @TableField("CREATOR")
    private Long creator;

    @ApiModelProperty("创建时间")
    @TableField(value="CREATE_DATE", fill= FieldFill.INSERT)
    private Date createDate;

    /** 备注*/
    @TableField(value="comments")
    @Length(max=200) private String comments;

    @ApiModelProperty("违约金（万元）")
    @TableField("PENALTY_AMOUNT")
    private BigDecimal penaltyAmount;

    @ApiModelProperty("自查自纠退款（万元）")
    @TableField("SELF_REFUND_AMOUNT")
    private BigDecimal selfRefundAmount;

    /** 附件列表*/
    @TableField(exist = false)
    private List<FinAttachment> instCheckAttaches;
}
