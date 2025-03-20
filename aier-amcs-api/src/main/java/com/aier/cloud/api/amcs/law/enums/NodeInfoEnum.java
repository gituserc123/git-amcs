package com.aier.cloud.api.amcs.law.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum NodeInfoEnum implements IEnum, EnumDict<NodeInfoEnum> {

    NODE001("NODE001", "申报人", 1),
    NODE002("NODE002", "医院CEO", 2),
    NODE003("NODE003", "省区法务", 3),
    NODE004("NODE004", "省区CEO", 4),
    NODE005("NODE005", "集团法务主管", 5),
    NODE006("NODE006", "集团职能总监", 6),
    NODE007("NODE007", "集团法律事务部经理", 7),
    NODE008("NODE008", "法务监察中心总监", 8);

    private final String code;
    private final String desc;
    private final int value;

    NodeInfoEnum(String code, String desc, int value) {
        this.code = code;
        this.desc = desc;
        this.value = value;
    }

    @Override
    public String getEnumCode() {
        return code;
    }

    @Override
    public String getEnumDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    // 可根据需要添加字段的getter方法
    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getValueInt() {
        return value;
    }
}