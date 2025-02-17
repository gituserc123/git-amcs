package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *（心脑）最高诊断依据
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum TumorDiagnosisEnum implements EnumDict<TumorDiagnosisEnum> {
	K1("1", "病理学诊断"),
	K2("2", "临床诊断"),
	K3("3", "死亡补发"),
	K4("4", "不详");

	private String code;
	private String value;

	private TumorDiagnosisEnum(String code, String value) {
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
		for (TumorDiagnosisEnum e : TumorDiagnosisEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
