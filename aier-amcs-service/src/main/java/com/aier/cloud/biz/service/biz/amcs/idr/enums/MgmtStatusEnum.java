package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *取值为“0或空=未收治；1=已收治；3=已到位未收治”值域范围。
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum MgmtStatusEnum implements EnumDict<MgmtStatusEnum> {
	
	
	K1("1", "已收治"),  
	K0("0", "未收治"),
	K3("3", "已到位未收治");

	private String code;
	private String value;

	private MgmtStatusEnum(String code, String value) {
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
		for (MgmtStatusEnum e : MgmtStatusEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
