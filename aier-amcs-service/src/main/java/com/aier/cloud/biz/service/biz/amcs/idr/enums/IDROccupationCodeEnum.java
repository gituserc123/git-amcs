package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 7.1 性别代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum IDROccupationCodeEnum implements EnumDict<IDROccupationCodeEnum> {
	K01("01","幼托儿童"),
	K02("02","散居儿童"),
	K03("03","学生"),
	K04("04","教师"),
	K05("05","保育员及保姆"),
	K06("06","餐饮食品业"),
	K07("07","公共场所服务员"),
	K08("08","商业服务"),
	K09("09","医务人员"),
	K10("10","工人"),
	K11("11","民工"),
	K12("12","农民"),
	K13("13","牧民"),
	K14("14","渔(船)民"),
	K15("15","海员及长途驾驶员"),
	K16("16","干部职员"),
	K17("17","离退人员"),
	K18("18","家务及待业"),
	K19("19","羁押人员"),
	K20("20","不详"),
	K99("99","其他");

	private String code;
	private String value;

	private IDROccupationCodeEnum(String code, String value) {
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
		for (IDROccupationCodeEnum e : IDROccupationCodeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
