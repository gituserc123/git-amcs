package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

/**
 * 表达是否的枚举
 *
 * @author xiaokek
 * @since 2021年9月26日 上午11:00:58
 */
public enum YesOrNoEnum implements IEnum, EnumDict<YesOrNoEnum> {
	/**
	 * 启用
	 */
    YES(1, "是"),

	/**
	 * 停用
	 */
    NO(0, "否");

	private Integer value;
	private String content;

	private YesOrNoEnum(Integer value, String content) {
		this.value = value;
		this.content = content;
	}
	
	@Override
	public Integer getValue() {
        return value;
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