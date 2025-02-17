package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *疾病诊断为乙肝时填写，取值为：“1=，2=，3=未测”值域范围，非必填。
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum LiveRpunctureTestEnum implements EnumDict<LiveRpunctureTestEnum> {
	
	
	K1("1", "急性病变"),  
	K2("2", "慢性病变"),  
	K3("3", "未测");

	private String code;
	private String value;

	private LiveRpunctureTestEnum(String code, String value) {
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
		for (LiveRpunctureTestEnum e : LiveRpunctureTestEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
