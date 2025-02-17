package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 * 7.34	最高诊断单位代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum HighestDiagnosisUnitEnum implements EnumDict<HighestDiagnosisUnitEnum> {
	
	K1("1", "三级医院"),
	K2("2", "二级医院"),
	K3("3", "乡镇卫生院或社区卫生服务机构"),
	K4("4", "村卫生室"),
	K9("9", "其他医疗卫生机构"),
	K0("0", "未就诊");
 

	private String code;
	private String value;

	private HighestDiagnosisUnitEnum(String code, String value) {
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
		for (HighestDiagnosisUnitEnum e : HighestDiagnosisUnitEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
