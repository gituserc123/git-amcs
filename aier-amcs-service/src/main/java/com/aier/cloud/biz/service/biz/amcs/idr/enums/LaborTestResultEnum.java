package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.8	手足口病的实验室结果代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum LaborTestResultEnum implements EnumDict<LaborTestResultEnum> {
	
	
	K1("1", "EV71"),  
	K2("2", "Cox  A16"),  
	K3("3", "其他肠道病毒"); 

	private String code;
	private String value;

	private LaborTestResultEnum(String code, String value) {
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
		for (LaborTestResultEnum e : LaborTestResultEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
