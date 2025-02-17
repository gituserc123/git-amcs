package com.aier.cloud.api.amcs.adverse.enums;

import java.io.Serializable;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum EventTypeEnum  implements IEnum,EnumDict<EventTypeEnum> {
		MEDICAL("医疗", 1),
		CARE("护理", 2),
		INFECT("院感", 3),
		DRUG("药品", 4),
		DEVICE("医疗器械", 5),
		OTHER("其他", 9),
		;
		
		private String desc;
		
		private Integer type;
		
		EventTypeEnum(String desc, Integer type){
			this.desc = desc;
			this.type = type;
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


		@Override
		public Serializable getValue() {
			// TODO Auto-generated method stub
			return null;
		}

		public Integer getType() {
			return type;
		}

}
