package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

/**
 * @program: amcs
 * @description: 岗位查询条件
 * @author: hsw
 * @create: 2021-09-26 16:37
 **/

public class PositionCondition extends PageCondition {
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
