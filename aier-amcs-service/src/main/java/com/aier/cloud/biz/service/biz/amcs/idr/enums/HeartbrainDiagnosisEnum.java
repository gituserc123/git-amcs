package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *（心脑）最高诊断依据
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum HeartbrainDiagnosisEnum implements EnumDict<HeartbrainDiagnosisEnum> {
	
	K1("1", "临床症状"),
	K2("2", "心电图"),
	K3("3", "血管造影"),
	K4("4", "CT/磁共振"),
	K5("5", "体格检查"),
	K6("6", "超声"),
	K7("7", "心肌酶"),
	K8("8", "腰穿"),
	K9("9", "尸检"),
	K10("10", "病理");

	private String code;
	private String value;

	private HeartbrainDiagnosisEnum(String code, String value) {
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
		for (HeartbrainDiagnosisEnum e : HeartbrainDiagnosisEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
