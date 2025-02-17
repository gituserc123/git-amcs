package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-24 17:26
 **/
@Data
public class FinHospSettingCondition extends PageCondition implements Serializable {
    private Long hospId;
    private Integer usingSign;

    // 医院列表
    private List<Long> hospList;
}
