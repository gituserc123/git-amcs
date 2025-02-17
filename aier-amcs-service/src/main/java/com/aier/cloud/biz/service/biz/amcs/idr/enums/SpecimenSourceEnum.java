package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.11	艾滋病附卡样本来源代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum SpecimenSourceEnum implements EnumDict<SpecimenSourceEnum> {
	
	K1("1", "基层推介"),
	K2("2", "因症就诊"),
	K3("3", "转诊追踪"),
	K4("4", "术前检测"),
	K5("5", "受血(制品)前检测"),
	K6("6", "性病门诊"),
	K7("7", "其他就诊者检测"),
	K8("8", "婚前检查(含涉外婚姻)"),
	K9("9", "孕产期检查"),
	K10("10", "检测咨询"),
	K11("11", "阳性者配偶或性伴检测"),
	K12("12", "女性阳性者子女检测"),
	K13("13", "职业暴露检测"),
	K14("14", "娱乐场所人员体检"),
	K15("15", "有偿供血(浆)人员检测"),
	K16("16", "无偿献血人员检测"),
	K17("17", "出入境人员体检"),
	K18("18", "新兵体检"),
	K19("19", "强制/劳教戒毒人员检测"),
	K20("20", "妇教所/女劳收教人员检测"),
	K21("21", "其他羁押人员体检"),
	K22("22", "专题调查"),
	K24("24", "接触者筛查"),
	K25("25", "样病例监测（ILI）"),
	K26("26", "不明原因肺炎监测(PUE)"),
	K27("27", "急性重症呼吸道疾病监测（SARI）"),
	K28("28", "主动筛查"),
	K29("29", "健康体检"),
	K30("30", "主动监测"),
	K31("31", "被动监测"),
	K32("32", "患者就医"),
	K33("33", "主动病例侦查"),
	K99("99", "其他");

	private String code;
	private String value;

	private SpecimenSourceEnum(String code, String value) {
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
		for (SpecimenSourceEnum e : SpecimenSourceEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
