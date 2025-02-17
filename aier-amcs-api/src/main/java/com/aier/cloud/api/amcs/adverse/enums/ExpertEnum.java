package com.aier.cloud.api.amcs.adverse.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

/**
 * @program: amcs
 * @description: 专家角色枚举表
 * @author: WM
 * @create: 2023-02-16
 **/
public enum ExpertEnum implements IEnum,EnumDict<ExpertEnum> {

	CATARACT(1,"白内障",1),
	GLAUCOMA(1<<1,"青光眼",2),
	FUNDUS(1<<2,"眼底病",3),
	TEARDUCT(1<<3,"泪道",4),
	REFRACTION(1<<4,"屈光",5),
	CORNEA(1<<5,"角膜及眼表",6),
	ORBITAL(1<<6,"眼眶与眼整形",7),
	VISION(1<<7,"视光",8),
	STRABISMUS(1<<8,"斜弱视",9),
    SINSPECTION(1<<9,"特检",10),
    ANESTHESIA(1<<10,"麻醉",11),
    PHARMACOLOGY(1<<11,"药学",12),
    EXAMINATION(1<<12,"检验",13);
	
	private Integer value;
    private String desc;
    private Integer seq;


    ExpertEnum(Integer value, String desc,Integer seq) {
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

