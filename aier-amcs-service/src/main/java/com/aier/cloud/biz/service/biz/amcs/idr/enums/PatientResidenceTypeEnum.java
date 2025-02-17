package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *病人所属地类型编码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum PatientResidenceTypeEnum implements EnumDict<PatientResidenceTypeEnum> {
	
	 
	K0("0", "本地"),
	K1("1", "异地")   ;

	private String code;
	private String value;

	private PatientResidenceTypeEnum(String code, String value) {
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
		for (PatientResidenceTypeEnum e : PatientResidenceTypeEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
