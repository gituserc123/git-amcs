package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *报告卡状态
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum DelflagEnum implements EnumDict<DelflagEnum> {
	
	K1("1", "未删除"), 
	K0("0", "删除")  ; 
	private String code;
	private String value;

	private DelflagEnum(String code, String value) {
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
		for (DelflagEnum e : DelflagEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
