package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.5	传染病诊断类型代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum DiagnosistypeEnum implements EnumDict<DiagnosistypeEnum> {
	
	
	K1("1", "临床诊断病例"),
	K2("2", "确诊病例"),
	K3("3", "疑似病例"),
	K4("4", "病原携带者"),
	K5("5", "阳性检测"),
	K6("6", "埃博拉留观病例");

	private String code;
	private String value;

	private DiagnosistypeEnum(String code, String value) {
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
		for (DiagnosistypeEnum e : DiagnosistypeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
