package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *1：是，2：否
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum YesNoEnum implements EnumDict<YesNoEnum> {
	
	
	K1("1", "是"),  
	K0("0", "否");

	private String code;
	private String value;

	private YesNoEnum(String code, String value) {
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
		for (YesNoEnum e : YesNoEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
