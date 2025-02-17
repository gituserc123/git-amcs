package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *死亡原因
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum DeathcauseEnum implements EnumDict<DeathcauseEnum> {
	K1("1", "因本病死亡"),
	K2("2", "非因本病死亡")  ;

	private String code;
	private String value;

	private DeathcauseEnum(String code, String value) {
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
		for (DeathcauseEnum e : DeathcauseEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
