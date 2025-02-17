package com.aier.cloud.api.amcs.adverse.enums;

import java.io.Serializable;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum OperateEnum implements IEnum,EnumDict<OperateEnum> {

	//1 保存 2 暂存 3 合并 5 取消 7 点评 8 集团查阅
	SAVE("保存", 1),
	STASH("暂存", 2),
	MERGE("合并", 3),
	CANCEL("取消", 5),
	RETURN("回退", 6),
	REVIEW("点评", 7),
	LOOK("查阅", 8),
	
	QUERY_MULTI("多次上报查询", 11), //包含合并事件和进展上报事件
	QUERY_INCREASE("进展上报", 12),
	QUERY_MERGE("合并事件", 13),

	Archived("归档", 21),
	
	;
	
	private String desc;
	
	private Integer type;
	
	OperateEnum(String desc, Integer type){
		this.desc = desc;
		this.type = type;
	}
	public static OperateEnum typeOf(Integer type){
		for(OperateEnum oe:OperateEnum.values()){
			if(oe.getType().equals(type)){
				return oe;
			}
		}
		return null;
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

	public Integer getType() {
		return type;
	}


	
}
