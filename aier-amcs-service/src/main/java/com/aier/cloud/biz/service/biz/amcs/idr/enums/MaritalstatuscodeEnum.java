package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 婚姻状况代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum MaritalstatuscodeEnum implements EnumDict<MaritalstatuscodeEnum> {
	K10("10", "未婚"),
	K20("20", "已婚"),
	K21("21", "初婚"),
	K22("22", "再婚"),
	K23("23", "复婚"),
	K30("30", "丧偶"),
	K40("40", "离婚"),
	K90("90", "未说明的婚姻状况");

	private String code;
	private String value;

	private MaritalstatuscodeEnum(String code, String value) {
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
		for (MaritalstatuscodeEnum e : MaritalstatuscodeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
