package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.12	艾滋病附卡感染途径代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum PossibleInfectionRouteEnum implements EnumDict<PossibleInfectionRouteEnum> {
	
	K1("1", "注射毒品"),
	K2("2", "异性传播"),
	K3("3", "同性传播"),
	K4("4", "性接触+注射毒品"),
	K5("5", "采血(浆)"),
	K6("6", "输血/血制品"),
	K7("7", "母婴传播"),
	K8("8", "职业暴露"),
	K10("10", "其他"),
	K99("99", "不详");

	private String code;
	private String value;

	private PossibleInfectionRouteEnum(String code, String value) {
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
		for (PossibleInfectionRouteEnum e : PossibleInfectionRouteEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
