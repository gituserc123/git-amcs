package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.15	艾滋病附卡性病史代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum VenerealHistoryEnum implements EnumDict<VenerealHistoryEnum> {
	
 
	K1("1", "有"),  
	K2("2", "无"),  
	K9("9", "不详"); 


	private String code;
	private String value;

	private VenerealHistoryEnum(String code, String value) {
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
		for (VenerealHistoryEnum e : VenerealHistoryEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
