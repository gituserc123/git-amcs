package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *是否
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum YesNo2Enum implements EnumDict<YesNo2Enum> { 
	K1("1", "是"),  
	K2("2", "否");
	private String code;
	private String value;

	private YesNo2Enum(String code, String value) {
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
		for (YesNo2Enum e : YesNo2Enum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
