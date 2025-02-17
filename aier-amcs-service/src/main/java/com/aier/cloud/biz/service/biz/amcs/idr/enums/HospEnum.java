package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *报告病人的法定单位编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum HospEnum implements EnumDict<HospEnum> {
	
	
	K1802("511421117", "仁寿爱尔眼科医院有限公司"), 
	
	K9999("511421117", "仁寿爱尔眼科医院有限公司");

	private String code;
	private String value;

	private HospEnum(String code, String value) {
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
		for (HospEnum e : HospEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
