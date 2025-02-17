package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.43	订正状态代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum CorrectionStateEnum implements EnumDict<CorrectionStateEnum> {
	
	K1("0", "未订正"),  
	K2("1", "订正"); 

	private String code;
	private String value;

	private CorrectionStateEnum(String code, String value) {
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
		for (CorrectionStateEnum e : CorrectionStateEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
