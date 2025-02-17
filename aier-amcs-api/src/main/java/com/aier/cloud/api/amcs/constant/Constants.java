package com.aier.cloud.api.amcs.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {
	//当前平台编码
	public final static String PlatformCode = "amcs";

	//集团
	public final static Long RootInstId = 100001L;

	//集团总部
	public final static Long GroupInstId = 100002L;

	public final static Integer Hosp = 3;

	public final static Integer Province = 2;

	public final static Integer Group = 1;

	public final static String SubmitMsg = "%s已上报\"%s\"，请及时审核。(审核请登录AHIS)";

	public final static String SecondaryMsg = "%s \"%s\"已上报进展情况，可登录AHIS查阅";

	public final static String HospAuditMsg = "你院%s已上报不良事件。(查阅请登录AHIS)";

	public final static String ProvinceMsg = "%s有%d起不良事件待审核，请及时审核。(审核请登录AHIS)";

	public final static String RebackMsg = "你上报的不良事件“%s”被退回，请登录系统修改不良事件并重新提交";

	public final static String ExpertJobMsg = "尊敬的%s专家，您已收到集团委派的不良事件%d起，请登录AHIS进行不良事件点评！";

	public final static String ProvinceExpertJobMsg = "尊敬的%s专家，您已收到省区委派的不良事件%d起，请登录AHIS进行不良事件点评！";

	public final static String HospRebackMsg = "你院%s所上报的“%s”被退回，请及时提醒上报人查看并重新提交！";

	public final static String ReviewMsg = "%s已对你院“%s”进行点评，请登录系统查看！";

	public final static String EventLevelIMsg = "%s、%s已上报I级(警告事件):%s,发生日期%s,请及时查阅!";

	public final static String EventLevelIIMsg = "%s、%s已上报II级(不良事件):%s,发生日期%s,请及时查阅!";

	public final static String ProvinceDirectorMsg = "%s已上报%s级不良事件:%s,查看详情请登录AHIS系统";

	public final static String AmountChangeMsg = "%s、%s新增赔付金额%s元,请及时查阅!";

	public final static String SubmitDeptDirectorMsg = "%s%s已上报不良事件：\"%s\"，请及时查阅。(查阅请登录AHIS)";

	public final static String DelayFinishMsg = "%s不良事件:患者姓名:%s,%s超过3个月未完结，请确认该事件是否完结，并及时上报完结信息！";

	public final static String SERVICE_PREFIX = "com.aier.cloud.biz.service.biz.amcs.adverse.service.impl.";

	public final static String ENTITY_PREFIX= "com.aier.cloud.biz.service.biz.amcs.adverse.entity.";

	public final static String SERVICE_SUFFIX = "ServiceImpl";

	public final static String EventLevelI = "I级（警告事件）";

	public final static String EventLevelII = "II 级（不良事件）";

	public final static String[] EventLevelIIUser = new String[]{"0068312"};

	public final static List<String> GroupReviewEmp = Arrays.asList("0068312","0001276","1079235","0024138","0011169","0065921");

}

