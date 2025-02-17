package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 * 传染病、慢病数据NCD、死因CODRIS数据
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum KeyEnum implements EnumDict<KeyEnum> {
	
	
	KIDR("IDR", "传染病"),  
	KNCD("NCD", "慢数据")	,  
	KCODRIS("CODRIS", "死因");

	private String code;
	private String value;

	private KeyEnum(String code, String value) {
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
		for (KeyEnum e : KeyEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
