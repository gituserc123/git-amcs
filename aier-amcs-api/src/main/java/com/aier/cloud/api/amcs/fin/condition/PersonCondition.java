package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-09-01 17:00
 **/

@Data
public class PersonCondition extends PageCondition implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long hospId;

    // 医院列表
    private List<Long> hospList;

    // 省区Id
    private Long province;
}
