package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 证件类型代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum IDCardTypeEnum implements EnumDict<IDCardTypeEnum> {
	K01("01", "居民身份证"),
	K02("02", "居民户口簿"),
	K03("03", "护照"),
	K04("04", "军官证"),
	K05("05", "驾驶证"),
	K06("06", "港澳居民来往内地通行证"),
	K07("07", "台湾居民来往内地通行证"),
	K99("99", "其他法定有效证件");

	private String code;
	private String value;

	private IDCardTypeEnum(String code, String value) {
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
		for (IDCardTypeEnum e : IDCardTypeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
