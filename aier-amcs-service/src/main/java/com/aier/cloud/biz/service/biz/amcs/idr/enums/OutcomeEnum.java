package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 * 7.22	转归代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum OutcomeEnum implements EnumDict<OutcomeEnum> {
	K1("1", "治愈"),
	K2("2", "好转"),
	K3("3", "未愈"),
	K4("4", "死亡"),
	K5("5", "其他");

	private String code;
	private String value;

	private OutcomeEnum(String code, String value) {
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
		for (OutcomeEnum e : OutcomeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
