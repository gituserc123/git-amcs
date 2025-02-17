package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.42	诊断依据代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum DiagnosticbasisEnum implements EnumDict<DiagnosticbasisEnum> {
	
	K1("1", "尸检"),
	K2("2", "病理"),
	K3("3", "手术"),
	K4("4", "临床+理化"),
	K5("5", "临床"),
	K6("6", "死后推断"),
	K9("9", "不详");

	private String code;
	private String value;

	private DiagnosticbasisEnum(String code, String value) {
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
		for (DiagnosticbasisEnum e : DiagnosticbasisEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
