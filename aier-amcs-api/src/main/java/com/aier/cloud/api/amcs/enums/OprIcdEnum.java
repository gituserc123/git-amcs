package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 手术等级
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:51:27
 */
public enum OprIcdEnum implements EnumDict<OprIcdEnum> {
	_1("角结膜病"),
	_2("晶状体病"),
	_3("泪道疾病"),
	_4("青光眼"),
	_5("屈光"),
	_6("斜弱视"),
	_7("眼底病"),
	_8("眼肌屈光"),
	_9("眼眶病"),
	_10("眼外伤"),
	_11("眼整形");

	private String value;
	private String content;

	private OprIcdEnum(String content) {
		this.value = content;
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