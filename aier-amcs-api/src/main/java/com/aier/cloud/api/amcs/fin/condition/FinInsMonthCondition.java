package com.aier.cloud.api.amcs.fin.condition;

import lombok.Data;

import java.util.List;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-29 10:50
 **/

@Data
public class FinInsMonthCondition {
    private Long hospId;
    // 医院列表
    private List<Long> hospList;
    // 省区Id
    private Long province;
    /** 状态 */
    private List<Integer> status;
    private String statusStr;
    private String unReportType;
}
