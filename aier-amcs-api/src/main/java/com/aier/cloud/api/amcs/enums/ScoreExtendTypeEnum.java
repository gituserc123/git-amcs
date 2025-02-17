package com.aier.cloud.api.amcs.enums;

import java.util.Arrays;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 计分拓展类型
 *
 *
 * @author xiaokek
 * @since 2022年3月8日 下午4:53:39
 */
public enum ScoreExtendTypeEnum implements EnumDict<ScoreExtendTypeEnum> {
    A("A", "收费项目"), 
    B("B", "固定套餐"), 
    C("C", "麻醉方式"), 
    D("D", "手术分类"), 
    E("E", "本指标"),
    F("F", "视光单据类型"),
    G("G", "视光处方类型"),
    H("H", "病种分类"), //performance_diseases
    I("I", "晶体型号"), //performance_diseases
    J("J", "收费项目_特例"),
    K("K", "挂号分类"),
    L("L", "角塑初复配"); 
	
	
	public enum CalcType implements EnumDict<CalcType> {
		_1(1, "收費項目"),
		_2(2, "財務分類");

		Integer value;
		String content;

		private CalcType(Integer value, String content) {
			this.value = value;
			this.content = content;
		}

		@Override
		public String getEnumCode() {
			return value.toString();
		}

		@Override
		public String getEnumDesc() {
			return content;
		}
	}
	
	
	private String value;
	private String content;

	private ScoreExtendTypeEnum(String value, String content) {
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
	
	public static boolean contains(String value, ScoreExtendTypeEnum... values) {
		return Arrays.asList(values).contains(ScoreExtendTypeEnum.valueOf(value));
	}
}





