package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 计分频率类型
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:55:21
 */
public enum ScoreFrequencyTypeEnum implements EnumDict<ScoreFrequencyTypeEnum> {
	_1("A", "天"), 
    _2("B", "月"), 
    _3("C", "季度"), 
    _4("D", "年");

	private String value;
	private String content;

	private ScoreFrequencyTypeEnum(String value, String content) {
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