package com.aier.cloud.api.amcs.adverse.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-07 11:27
 **/
public enum NodeEnum implements IEnum,EnumDict<NodeEnum> {
	RETRUN(-1,"回退",-1),
	STASH(0,"暂存",0),
    REPORTING(1,"上报",1),
    HOSPITAL_REVIEWS(1<<1,"医院审核",2),
    PROVINCE_REVIEWS(1<<2,"省区审核",3),
    GROUP_REVIEWS(1<<3,"集团审核",4),
    EXPERT_REVIEWS(1<<4,"专家点评",5),
    DEPT_REVIEWS(1,"科主任审核",11),
    ;

    private Integer value ;
    private String desc;
    private Integer seq;


    NodeEnum(Integer value, String desc,Integer seq) {
        this.value = value;
        this.desc = desc;
        this.seq = seq;
    }

    public Integer getSeq() {
        return seq;
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
