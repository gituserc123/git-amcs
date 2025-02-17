package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.25	ICD-O-3分化程度代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum Icdo3DegreeEnum implements EnumDict<Icdo3DegreeEnum> {
	K1("1", "Ⅰ级/高分化/已分化NOS"),
	K2("2", "Ⅱ级/中分化/已中等分化"),
	K3("3", "Ⅲ级/低分化"),
	K4("4", "Ⅳ级/未分化/间变"),
	K5("5", "T细胞"),
	K6("6", "B细胞/前B/B前体细胞"),
	K7("7", "无标记淋巴细胞/非T非B"),
	K8("8", "NK（自然杀伤）细胞"),
	K9("9", "等级或分化程度未确定，未指出或不适用的细胞类型未确定，未指出或不适用的");

	private String code;
	private String value;

	private Icdo3DegreeEnum(String code, String value) {
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
		for (Icdo3DegreeEnum e : Icdo3DegreeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
