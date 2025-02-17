package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 手术等级
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:51:27
 */
public enum OprLevelEnum implements EnumDict<OprLevelEnum> {
    _0("0", "0"), 
    _1("1", "1"), 
    _2("2", "2"),
    _3("3", "3"),
    _4("4", "4");

	private String value;
	private String content;

	private OprLevelEnum(String value, String content) {
		this.value = value;
		this.content = content;
	}
	
    public String getContent() {
        return content;
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