package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 省区角色类型
 * 院长 医务 护理
 * @author chendongdong
 * @since 2022年4月26日 下午4:51:27
 */
public enum RoleTypeEnum implements EnumDict<RoleTypeEnum> {
	DEAN("1", "院长"),
	MEDICAL("2", "医务"),
	NURSE("3", "护理");

	private String value;
	private String content;

	private RoleTypeEnum(String value, String content) {
		this.value = value;
		this.content = content;
	}
	
    public String getContent() {
        return content;
    }


    public static RoleTypeEnum value2Of(String value){
		RoleTypeEnum[] roles = RoleTypeEnum.values();
		for (RoleTypeEnum roleType:roles){
			if (roleType.value.equals(value)){
				return roleType;
			}
		}
		return null;
	}

    @Override
	public String getEnumCode() {
		return String.valueOf(value);
	}

	@Override
	public String getEnumDesc() {
		return content;
	}
}