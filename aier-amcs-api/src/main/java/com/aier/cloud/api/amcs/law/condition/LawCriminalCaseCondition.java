package com.aier.cloud.api.amcs.law.condition;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LawCriminalCaseCondition  extends LawBaseCondition {


    /** 案件唯一标识，主键 */
    private Long id;

    /** 状态:1-创建,2-审批中,3-结束,4-OA流程中,5-归档 */
    private Integer status;

    /** 犯罪嫌疑人名称 */
    private String suspectName;

    /** 案件主办公安机关名称 */
    private String handlingPsb;

    /** 案件主办检察院名称 */
    private String prosecutorOffice;

    /** 案件主审法院名称 */
    private String courtName;

    /** 案件类型 */
    private String caseType;

    /** 罪名 */
    private String chargeName;

    /** 罪名其他描述 */
    private String chargeNameDesc;

    /** 案号 */
    private String caseNo;

    /** 案件描述 */
    private String caseDesc;

    /** 涉案金额 */
    private BigDecimal involvedAmount;

    /** 诉讼阶段 */
    private String litigationPhase;

    /** 当前状态 */
    private String currentStatus;

    /** 备注 */
    private String remarks;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;
}
