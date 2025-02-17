package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *乙肝HBsAg阳性时间取值为：“1=大于6个月，2=6个月内由阴性转为阳性，3=既往未检测或结果不详”
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum HbsagtestpositivetypeEnum implements EnumDict<HbsagtestpositivetypeEnum> {
	
	
	K1("1", "大于6个月"),  
	K2("2", "6个月内由阴性转为阳性"),  
	K3("3", "既往未检测或结果不详");

	private String code;
	private String value;

	private HbsagtestpositivetypeEnum(String code, String value) {
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
		for (HbsagtestpositivetypeEnum e : HbsagtestpositivetypeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
