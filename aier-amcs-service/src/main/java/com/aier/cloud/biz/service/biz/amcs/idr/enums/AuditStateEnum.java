package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 *  
 *7.44	卡片状态代码
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum AuditStateEnum implements EnumDict<AuditStateEnum> { 
	K0("0", "未审核"),  
	K3("3", "已审核"),  
	K4("4", "已删除"),  
	K5("5", "上传初报") , 
	K6("6", "上传修订"),
	K7("7", "上传审核"),  
	K8("8", "上传删除"),   
	K9("9", "上传恢复");

	private String code;
	private String value;

	private AuditStateEnum(String code, String value) {
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
		for (AuditStateEnum e : AuditStateEnum.values()) {
			if (e.name().equals(key)) {
				include = true;
				break;
			}
		}
		return include;
	}
}
