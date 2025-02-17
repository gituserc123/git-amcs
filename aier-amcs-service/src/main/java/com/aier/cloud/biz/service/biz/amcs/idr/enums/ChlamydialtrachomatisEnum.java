package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.46	生殖道沙眼衣原体感染编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum ChlamydialtrachomatisEnum implements EnumDict<ChlamydialtrachomatisEnum> {
	
	
	K1("1", "确诊病例"),  
	K2("2", "无症状感染"); 

	private String code;
	private String value;

	private ChlamydialtrachomatisEnum(String code, String value) {
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
		for (ChlamydialtrachomatisEnum e : ChlamydialtrachomatisEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
