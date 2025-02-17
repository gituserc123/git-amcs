package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *死亡地点代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum DeathPlaceEnum implements EnumDict<DeathPlaceEnum> {
	
	K1("1", "医疗卫生机构"),
	K2("2", "来院途中"),
	K3("3", "家中"),
	K4("4", "养老服务机构"),
	K9("9", "其它场所"),
	K0("0", "不详")  ;

	private String code;
	private String value;

	private DeathPlaceEnum(String code, String value) {
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
		for (DeathPlaceEnum e : DeathPlaceEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
