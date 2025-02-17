package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 计分规则类型
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:51:27
 */
public enum RuleUsingEnum implements EnumDict<RuleUsingEnum> {
    _0("0", "备用"), 
    _P("1", "正式（P）"), 
    _T("2", "测试（T）"),
    _4("4", "禁用");

	private String value;
	private String content;

	private RuleUsingEnum(String value, String content) {
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