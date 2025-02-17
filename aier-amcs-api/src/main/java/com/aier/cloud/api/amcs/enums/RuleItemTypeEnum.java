package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 计分规则类型
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:51:27
 */
public enum RuleItemTypeEnum implements EnumDict<RuleItemTypeEnum> {
    A("A", "金额-固定比例"), 
    B("B", "金额-区间分值"), 
    C("C", "金额-区间比例"), 
    D("D", "数量-固定分值"), 
    E("E", "数量-区间分值");

	private String value;
	private String content;

	private RuleItemTypeEnum(String value, String content) {
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