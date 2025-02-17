package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

/**
 * 表达是否的枚举
 *
 * @author xiaokek
 * @since 2021年9月26日 上午11:00:58
 */
public enum UsingSignEnum implements IEnum, EnumDict<UsingSignEnum> {
	/**
	 * 启用
	 */
    ENABLE(1, "启用"), 
	
	/**
	 * 停用
	 */
    DISABLE(0, "停用");

	private Integer value;
	private String content;

	private UsingSignEnum(Integer value, String content) {
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