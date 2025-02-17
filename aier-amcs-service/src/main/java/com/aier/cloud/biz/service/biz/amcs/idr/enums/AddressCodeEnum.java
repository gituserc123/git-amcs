package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 地址类型代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum AddressCodeEnum implements EnumDict<AddressCodeEnum> {
	
	K01("01", "本县区"),
	K02("02", "本市其它县区"),
	K03("03", "本省其它地市"),
	K04("04", "其他省"),
	K05("05", "港澳台 "),
	K06("06", "外籍 ");

	private String code;
	private String value;

	private AddressCodeEnum(String code, String value) {
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
		for (AddressCodeEnum e : AddressCodeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
