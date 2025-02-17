package com.aier.cloud.api.amcs.adverse.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum CountEnum implements IEnum, EnumDict<CountEnum> {

	// 统计类型 1 未完结 2 未完结(超过90天) 3 纠纷 4 赔偿

	UNFINISHED("未完结", 1), UNFINISHED90("未完结(超过90天)", 2), DISPUTE("纠纷", 3), COMPENSATION("赔偿", 4), EVENTS_PROGRESS("事件进展", 5);

	private String desc;

	private Integer value;

	CountEnum(String desc, Integer value) {
		this.desc = desc;
		this.value = value;
	}

	@Override
	public String getEnumCode() {
		return null;
	}

	@Override
	public String getEnumDesc() {
		return desc;
	}

	public Integer getValue() {
		return value;
	}

}
