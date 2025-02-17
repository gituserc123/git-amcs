package com.aier.cloud.api.amcs.adverse.enums;


import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum StatusEnum  implements IEnum,EnumDict<StatusEnum> {
	
		VALID("有效", 0),
		FINISH("结束", 1),
		CANCEL("取消", 2);
		
		private String desc;
		
		private Integer value;
		
		StatusEnum(String desc, Integer value){
			this.desc = desc;
			this.value = value;
		}

		@Override
		public String getEnumCode() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String getEnumDesc() {
			// TODO Auto-generated method stub
			return desc;
		}

		public Integer getValue() {
			return value;
		}

}
