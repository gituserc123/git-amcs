package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
public class FinHospInsuranceCheckCondition extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;

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

    private Long hospId;

    private String investNature;

    private String hospParent;

    private String ehrLevel;

    private Long creator;

    private Date createDate;

    private BigDecimal modifer;

    private Date modifyDate;

    // 医院列表
    private List<Long> hospList;

    private String checkDateBegin;

    private String checkDateEnd;

    private Integer isAttach;

    // 省区Id
    private Long province;

}
