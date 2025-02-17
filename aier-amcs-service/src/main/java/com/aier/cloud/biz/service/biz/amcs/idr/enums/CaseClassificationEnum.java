package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *密切接触者有无相同症状编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum CaseClassificationEnum implements EnumDict<CaseClassificationEnum> {
	
	K1("1", "急性"),
	K2("2", "慢性"),
	K3("3", "未分型");

	private String code;
	private String value;

	private CaseClassificationEnum(String code, String value) {
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
		for (CaseClassificationEnum e : CaseClassificationEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
