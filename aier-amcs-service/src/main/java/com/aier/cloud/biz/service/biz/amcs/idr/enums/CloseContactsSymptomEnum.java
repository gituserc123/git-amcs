package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *报告病人的法定单位编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum CloseContactsSymptomEnum implements EnumDict<CloseContactsSymptomEnum> {
	
	
	K1("1", "有"),  
	K0("0", "无");

	private String code;
	private String value;

	private CloseContactsSymptomEnum(String code, String value) {
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
		for (CloseContactsSymptomEnum e : CloseContactsSymptomEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
