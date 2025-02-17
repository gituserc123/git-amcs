package com.aier.cloud.api.amcs.fin.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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
public class FinHospInsuranceCheck extends BaseEntity {

    private Date checkDate;

    private String checkInstPerson;

    private String checkContent;

    private String regulatoryIssues;

    private BigDecimal regulatoryAmount;

    private String appealStatus;

    private Integer caseClosed;

    private BigDecimal deductionAmount;

    private BigDecimal fineAmount;

    private String otherPenalty;

    private String hospName;

    private BigDecimal hospId;

    private String investNature;

    private String hospParent;

    private String ehrLevel;

    private Long creator;

    private Date createDate;

    private Long modifer;

    private Date modifyDate;

    private String comments;

    // 违约金（元）
    private BigDecimal penaltyAmount;

    // 自查自纠退款（元）
    private BigDecimal selfRefundAmount;

    // 合计金额（元）
    private BigDecimal sumAmount;


}
