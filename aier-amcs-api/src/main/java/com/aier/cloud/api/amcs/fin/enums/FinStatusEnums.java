package com.aier.cloud.api.amcs.fin.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * @program: amcs
 * @description: 医保上报状态枚举
 * @author: hsw
 * @create: 2023-03-14 15:51
 **/
public enum FinStatusEnums{
    REPORT(1,"上报"),
    PROVINCE(5,"省区审核"),
    PROVINCE_AGREE(6,"省区审核完成"),
    RETURN(9,"退回"),
    ;
    private Integer value;
    private String content;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    FinStatusEnums(Integer value, String content) {
        this.value = value;
        this.content = content;
    }


}
