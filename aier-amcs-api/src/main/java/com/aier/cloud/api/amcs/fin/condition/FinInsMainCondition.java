package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.util.List;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-29 10:50
 **/

@Data
public class FinInsMainCondition extends PageCondition {
    private Long hospId;
    private Long monthId;
    // 医院列表
    private List<Long> hospList;
    // 省区Id
    private Long province;
    // 医保类别（码表：INSURANCE_TYPE）
    private Integer insuranceType;
    // 卫健委定级(码表：LEVEL)
    private Integer healthCommissionLevel;
    // 医保结算等级(码表：LEVEL)
    private Integer insuranceSettlementLevel;
    // 应收医保款回款率
    private Integer receivableCollectionRateSelectValue;
    // DIP节余/超支
    private Integer dipBo;
    // DRG节余/超支
    private Integer drgBo;
    // 按人头节余/超支
    private Integer perBo;
    /** 状态 */
    private List<Integer> status;
    private String statusStr;

    private String year;

    private String month;

    private String monthMax = "01,03,05,07,08,10,12";
    private String monthMiddle = "04,06,09,11";
    private String monthMin = "02";

    private Integer period;

    // 上报日期
    private String reportDate;

    // 上报结束日期
    private String reportDateEnd;

    private String reportDateBegin;
}
