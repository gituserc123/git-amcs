package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 7.1 性别代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum SexEnum implements EnumDict<SexEnum> {
	K1("1", "男"), K2("2", "女"), K0("0", "未知的性别"), K9("9", "未说明的性别");

	private String code;
	private String value;

	private SexEnum(String code, String value) {
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
		for (SexEnum e : SexEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
