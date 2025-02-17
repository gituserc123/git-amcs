package com.aier.cloud.biz.service.biz.amcs.aps.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.aier.cloud.basic.common.exception.BizException;

/**
 * 门诊标签
 *
 *
 * @author xiaokek
 * @since 2023年7月13日 下午3:14:48
 */
public enum RegistMarkEnum implements EnumDict<RegistMarkEnum> {
	普通接诊(10001,"普通接诊","Cha040102"),
	免费接诊(10002,"免费接诊","Cha040101"),
	急诊(10003,"急诊","Cha040103"),
	主治医师接诊(10004,"主治医师接诊","Cha040104"),
	副主任医师接诊(10005,"副主任医师接诊","Cha040106"),
	主任医师接诊(10006,"主任医师接诊","Cha040105"),
	专家接诊(10007,"专家接诊","Cha040107"),
	特需接诊(10008,"特需接诊","Cha040108");
	
	private Integer value;
	private String content;
	private String mark;

	private RegistMarkEnum(Integer value, String content,String mark) {
		this.value = value;
		this.content = content;
		this.mark = mark;
	}
    @Override
	public String getEnumCode() {
		return String.valueOf(value);
	}

	@Override
	public String getEnumDesc() {
		return content;
	}
	public String getMark() {
		return mark;
	}
	
	public static String findMark(String value) {
		RegistMarkEnum[] values = RegistMarkEnum.values();
		for(RegistMarkEnum e : values){
			if(e.getEnumCode().equals(value)) {
				return e.getMark();
			}
		}
		throw BizException.error("标签未维护:"+value);
	}
}





