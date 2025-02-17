package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.14	艾滋病附卡实验室检测结论代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum LaborTestConclusionEnum implements EnumDict<LaborTestConclusionEnum> {
	
	
	K01("01", "确认结果阳性"),  
	K02("02", "替代策略检测阳性"),  
	K03("03", "核酸检测阳性"); 


	private String code;
	private String value;

	private LaborTestConclusionEnum(String code, String value) {
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
		for (LaborTestConclusionEnum e : LaborTestConclusionEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
