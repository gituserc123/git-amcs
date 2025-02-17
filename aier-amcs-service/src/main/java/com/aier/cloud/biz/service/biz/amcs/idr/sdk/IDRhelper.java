package com.aier.cloud.biz.service.biz.amcs.idr.sdk;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 帮助类 禁止包外调用
 * 
 * @author Luo
 *
 */
 
public final class IDRhelper {
	public static final String GJ_DATE = "yyyy-MM-dd HH";
	public static final String GJ_DATE1 = "yyyy-MM-dd";
	public static final String GJ_DATE2= "yyyy-MM-dd HH:mm:ss";
public static String transDateStr(Date dt, String Formate) {
		if(dt==null)
		{
			return null;
		}
		return DateFormatUtils.format(dt, Formate);
	}
}
