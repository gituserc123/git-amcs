package com.aier.cloud.api.amcs.adverse.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 事件概况
 *
 */
@Data
public class AeOverview {

	/** 严重程度*/
	private String severityLevel;
	
	/** 事件分级(码表:event_level)*/
	private String eventLevel;
	
	/** 亚专科(码表: sub_type)*/
	private String subspecialty;
	
	private Integer subspecialtyCode;
	
	/** 事件分类一级(码表: grade_one)*/
	private String gradeOne;
	
	private Integer gradeOneCode;
	
	/** 事件分类二级I(码表: grade_two_a)*/
	private String gradeTwoA;
	
	private Integer gradeTwoACode;
	
	/** 事件分类二级II(码表: grade_two_b)*/
	private String gradeTwoB;
	
	private Integer gradeTwoBCode;
	
	/** 事件分类二级说明*/
	private String gradeTwoRemark;

	/** 发生日期*/
	private Date eventDate;


	
	
}
