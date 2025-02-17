package com.aier.cloud.api.amcs.adverse.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import lombok.Data;

@Data
public class AeExpertCondition  extends PageCondition{
	private Long id;
	// 所选学组
	private String groups;
	
	// 多个学组累加值
	private Integer groupsValue;
	
	//所在学组
	private Integer groupValue;
	//姓名
	private String staffName;
	//工号
	private String staffCode;
	//查询关键字
	private String staffKey;
	// 是否显示专家事件绑定状态
	private Boolean showExpertStatus;
	//事件ID
	private Long eventId;
	//是否省区专家
	private Boolean isProvince;
	//省区ID
	private String provinceCode;

	private String eventIds;

	private String experts;

}
