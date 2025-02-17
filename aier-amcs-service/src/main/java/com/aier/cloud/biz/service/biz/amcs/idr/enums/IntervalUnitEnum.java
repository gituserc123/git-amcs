package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.40	时间间隔单位代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum IntervalUnitEnum implements EnumDict<IntervalUnitEnum> {
	KA("A", "小时"),
	KD("D", "日"),
	KM("M", "月"),
	KY("Y", "年");

	private String code;
	private String value;

	private IntervalUnitEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public String getEnumCode() {
		return code;
	}

	@Override
	public String getEnumDesc() {
		return value;
	}

	public static boolean isInclude(String key) {
		boolean include = false;
		for (IntervalUnitEnum e : IntervalUnitEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
