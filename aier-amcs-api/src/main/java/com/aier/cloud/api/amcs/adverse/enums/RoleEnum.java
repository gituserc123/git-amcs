package com.aier.cloud.api.amcs.adverse.enums;

import java.io.Serializable;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum RoleEnum implements IEnum,EnumDict<RoleEnum> {
	
	HOSP_DEAN("医院院长", 11),
	HOSP_MEDICAL("医务部主管", 12),
	HOSP_CARE("护理部主任", 13),
	PROVINCE_DEAN("省区院长", 21),
	PROVINCE_MEDICAL("省区医务", 22),
	PROVINCE_CARE("省区护理", 23),
	HEAD("集团不良事件管理", 31)
	;
	
	private String desc;
	
	private Integer node;
	
	RoleEnum(String desc, Integer node){
		this.desc = desc;
		this.node = node;
	}

	@Override
	public String getEnumCode() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getEnumDesc() {
		// TODO Auto-generated method stub
		return desc;
	}


	@Override
	public Serializable getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getNode() {
		return node;
	}
	
	

}
