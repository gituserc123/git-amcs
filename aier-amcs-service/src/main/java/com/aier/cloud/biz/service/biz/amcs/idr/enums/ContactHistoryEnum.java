package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.13	艾滋病附卡接触史代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum ContactHistoryEnum implements EnumDict<ContactHistoryEnum> {
	
	K1("1", "注射毒品史"),
	K2("2", "非婚异性性接触史"),
	K3("3", "配偶/固定性伴阳性"),
	K4("4", "男男性行为史"),
	K5("5", "献血（浆）史"),
	K6("6", "输血/血制品史"),
	K7("7", "母亲阳性"),
	K8("8", "职业暴露史"),
	K9("9", "手术史"),
	K11("11", "其他"),
	K10("10", "不详"),
	K12("12", "非商业"),
	K13("13", "商业");

	private String code;
	private String value;

	private ContactHistoryEnum(String code, String value) {
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
		for (ContactHistoryEnum e : ContactHistoryEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
