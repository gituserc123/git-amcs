package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 * 7.27	肿瘤分期T
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum TumorStageTEnum implements EnumDict<TumorStageTEnum> {
	
	K0("0", "T0期"),
	K1("1", "T1期"),
	K2("2", "T2期"),
	K3("3", "T3期"),
	K4("4", "T4期"),
	K9("9", "无法评估原发肿瘤大小");

	private String code;
	private String value;

	private TumorStageTEnum(String code, String value) {
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
		for (TumorStageTEnum e : TumorStageTEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
