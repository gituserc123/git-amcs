package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.49	临床严重程度编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum NewPneumSeverityEnum implements EnumDict<NewPneumSeverityEnum> {
	K2("2", "轻型"),
	K3("3", "重型"),
	K4("4", "危重型"),
	K5("5", "无症状感染者"),
	K6("6", "中型") ;

	private String code;
	private String value;

	private NewPneumSeverityEnum(String code, String value) {
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
		for (NewPneumSeverityEnum e : NewPneumSeverityEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
