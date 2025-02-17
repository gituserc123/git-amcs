package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2024-03-01 17:19
 **/
public enum PeriodEnum implements IEnum, EnumDict<YesOrNoEnum> {
    _1(1,"截止到1月"),
    _2(2,"截止到2月"),
    _3(3,"截止到3月"),
    _4(4,"截止到4月"),
    _5(5,"截止到5月"),
    _6(6,"截止到6月"),
    _7(7,"截止到7月"),
    _8(8,"截止到8月"),
    _9(9,"截止到9月"),
    _10(10,"截止到10月"),
    _11(11,"截止到11月"),
    _12(12,"截止到12月"),
    ;
    private Integer value;
    private String content;

    private PeriodEnum(Integer value, String content) {
        this.value = value;
        this.content = content;
    }

    public static PeriodEnum getValue(Integer value) {
        for (PeriodEnum periodEnum : values()) {
            if (periodEnum.getValue().equals(value)) {
                return periodEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String getEnumCode() {
        return value.toString();
    }

    @Override
    public String getEnumDesc() {
        return content;
    }

    @Override
    public String getFirstSpell() {
        return EnumDict.super.getFirstSpell();
    }
}
