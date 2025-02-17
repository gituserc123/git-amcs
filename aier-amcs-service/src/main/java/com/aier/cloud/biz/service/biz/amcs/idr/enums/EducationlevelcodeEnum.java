package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *文化程度代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum EducationlevelcodeEnum implements EnumDict<EducationlevelcodeEnum> {
	
	K10("10", "研究生教育"),
	K11("11", "博士研究生毕业"),
	K12("12", "博士研究生结业"),
	K13("13", "博士研究生肄业"),
	K14("14", "硕士研究生毕业"),
	K15("15", "硕士研究生结业"),
	K16("16", "硕士研究生肄业"),
	K17("17", "研究生班毕业"),
	K18("18", "研究生班结业"),
	K19("19", "研究生班肄业"),
	K20("20", "大学本科"),
	K21("21", "大学本科毕业"),
	K22("22", "大学本科结业"),
	K23("23", "大学本科肄业"),
	K28("28", "大学普通班毕业"),
	K30("30", "专科教育"),
	K31("31", "大学专科毕业"),
	K32("32", "大学专科结业"),
	K33("33", "大学专科肄业"),
	K40("40", "中等职业教育"),
	K41("41", "中等专科毕业"),
	K42("42", "中等专科结业"),
	K43("43", "中等专科肄业"),
	K44("44", "职业高中毕业"),
	K45("45", "职业高中结业"),
	K46("46", "职业高中肄业"),
	K47("47", "技工学校毕业"),
	K48("48", "技工学校结业"),
	K49("49", "技工学校肄业"),
	K60("60", "普通高级中学教育"),
	K61("61", "普通高中毕业"),
	K62("62", "普通高中结业"),
	K63("63", "普通高中肄业"),
	K70("70", "初级中学教育"),
	K71("71", "初级毕业"),
	K73("73", "初中肄业"),
	K80("80", "小学教育"),
	K81("81", "小学毕业"),
	K82("82", "小学肄业"),
	K90("90", "其他") ;

	private String code;
	private String value;

	private EducationlevelcodeEnum(String code, String value) {
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
		for (EducationlevelcodeEnum e : EducationlevelcodeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
