package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 财务分类
 *
 *
 * @author xiaokek
 * @since 2023年7月24日 下午3:59:00
 */
public enum FinanceCodeEnum implements EnumDict<FinanceCodeEnum> {
    _0("CL01010101", "PMMA普通晶体"), 
    _1("CL01010102", "普通折叠晶体"), 
    _2("CL01010103", "非球面晶体"),
    _3("CL01010112", "功能型晶体");


	private String value;
	private String content;

	private FinanceCodeEnum(String value, String content) {
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