package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.50	直接死亡诊断代码表
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum SymptomsEnum implements EnumDict<SymptomsEnum> {
	
	K1("1", "急性脑血管疾病"),
	K2("2", "糖尿病（酮症、乳酸酸中毒、高渗性昏迷等）"),
	K3("3", "恶性肿瘤"),
	K4("4", "慢性肾衰、维持性透析"),
	K5("5", "慢性阻塞性肺炎急性加重期（AECOPD）"),
	K6("6", "急性或慢性呼吸衰竭（非新冠重型或危重型）"),
	K7("7", "急性冠脉综合征（ACS）"),
	K8("8", "急性、慢性心衰"),
	K9("9", "恶性心律失常"),
	K10("10", "脓毒症（Sepsis）"),
	K11("11", "感染性休克"),
	K12("12", "医院获得性肺炎"),
	K13("13", "社区获得性肺炎（非新冠）"),
	K14("14", "消化道出血"),
	K15("15", "严重电解质紊乱"),
	K16("16", "血流感染"),
	K17("17", "急腹症"),
	K18("18", "哮喘持续状态"),
	K19("19", "夹层动脉瘤"),
	K20("20", "肝硬化（失代偿）"),
	K21("21", "急性肺栓塞"),
	K22("22", "猝死"),
	K23("23", "其他");

	private String code;
	private String value;

	private SymptomsEnum(String code, String value) {
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
		for (SymptomsEnum e : SymptomsEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
