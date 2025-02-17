package com.aier.cloud.api.amcs.adverse.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum PageEnum implements IEnum,EnumDict<PageEnum> {
    EVENT_LIST(1,"事件列表"),
    HOSPITAL_REVIEWS(2,"医院审核"),
    PROVINCE_REVIEWS(3,"省区审核"),
    GROUP_REVIEWS(4,"集团审核"),
    EXPERT_REVIEWS(5,"专家点评"),
    ;

    private Integer value ;
    private String desc;


    PageEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public String getEnumCode() {
        return String.valueOf(value);
    }

    @Override
    public String getEnumDesc() {
        return desc;
    }

    @Override
    public String getFirstSpell() {
        return EnumDict.super.getFirstSpell();
    }

    @Override
    public boolean is(Object o) {
        return EnumDict.super.is(o);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

