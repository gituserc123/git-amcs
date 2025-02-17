package com.aier.cloud.api.amcs.adverse.condition;

import java.util.Arrays;
import java.util.List;

import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

@Data
public class AeInfoCondition extends PageCondition {

	private static final long serialVersionUID = 2450823207363108850L;
	private Long id;
	// 不良事件类型 1 医疗 2 护理 3 院感
	private Integer type;
	// 页面类型 1 事件列表 2 医院审核 3 省区审核 4 集团审核 5 专家点评
	private Integer pageType;

	// 统计类型 1 未完结 2 未完结(超过90天) 3 纠纷 4 赔偿
	private Integer countType;
	// 上报次数
	private Integer reportTimes;
	// 是否为主事件
	private Boolean isPrimary;
	// 是否为待合并事件
	private Boolean isMerge;
	// 是否为专家点评
	private Boolean isExpert;
	// 是否显示专家关联
	private Boolean showExpertStatus = false;
	// 是否指派专家
	private Boolean hasAssignExpert;
	// 是否是省区相关事件
	private Boolean isProvince;
	// 指派日期
	private String assignDateBegin;

	private String assignDateEnd;
	
	// 是否显示专家关联
	private Boolean showArchived;

	// 集团是否已查阅
	private Integer isGroupReview;
	
	// 是否高风险
	private Integer isHighRisk;

	private Long basicId;

	private Long masterId;

	private Long prevId;

	private List<Long> mergeIds;
	
	private List<Long> basicIds;
	
	private Long expertId;
	
	private String types;
	
	private String tags;
	
	private String severityLevels;

	// 查询类型
	private Integer operateType = 0;

	private Integer finishSign;

	// 医院ID
	private Long hospId;

	private String eventCode;

	private String eventName;
	// 患者编号（门诊号、住院号）
	private String patientNo;

	// 所处节点
	private NodeEnum nodeEnum;

	private Integer node;

	private Integer nodeSeq;

	// 医院列表
	private List<Long> hospList;

	// 事件分级
	private String eventLevel;

	// 事件分级(描述)
	private String eventLevelDesc;

	// 事件类型
	private String eventType;

	// 事件分类
	private String gradeOne;

	// 上报日期
	private String reportDate;

	// 上报结束日期
	private String reportEndDate;

	private String reportDateBegin;

	private String reportDateEnd;

	// 发生日期
	private String eventDate;

	// 发生结束日期
	private String eventEndDate;

	private String eventDateBegin;

	private String eventDateEnd;
	
	// 省区审核通过日期
	private String provAuditDateBegin;
	
	private String provAuditDateEnd;

	// 状态
	private String reviewStatus;
	private Integer pReviewStatus; //省区点评
	private Integer gReviewStatus; //集团点评
	private Integer eReviewStatus; //专家点评

	// 创建人
	private Long creator;
	// 操作者
	private Long operator;
	// 创建者列表
	private List<Object> staffList;

	private Integer status;

	// 患者姓名
	private String patientName;

	// 事件分类一级
	private Integer gradeOneCode;

	// 事件分类二级
	private Integer gradeTwoACode;

	// 省区Id
	private Long province;

	// 亚专科Code
	private String subspecialtyCode;

	// 退回状态
	private Integer rebackStatus;
	// 回退节点
	private Integer rebackNode;

	/** 赔偿金额 */
	private java.math.BigDecimal compensationAmount;

	/** 赔偿金额上限 */
	private java.math.BigDecimal compensationUpAmount;

	/** 减免金额 */
	private java.math.BigDecimal deductionAmount;

	/** 减免金额上限 */
	private java.math.BigDecimal deductionUpAmount;

	/** 排序字段 */
	private String orderField;
	
	/**
	 * 是否为升序 ASC（ 默认： true ）
	 */
	private Boolean orderByAsc = true;

	// 上报次数
	private Integer maxReportTimes;
	
	//显示所有节点
	private Boolean showAllNode = false;
	
	//赔付/减免金额发生开始时间
	private String amountDateBegin;
	//赔付/减免金额发生结束时间
	private String amountDateEnd;

	// 事件分类一级字符串
	private String gradeOneCodeStr;

	// 事件分类二级字符串
	private String gradeTwoACodeStr;

	private Integer unplan;
	
	private String eventLevels;
	//非本系统首次上报事件
	private String unOtherReport;
	// 超期天数
	private Integer overDays;
   // 重点事件
	private Boolean isFocus = false;

	/** EHR科室主任是否查阅 (0 未查阅 1 已查阅)*/
	private Integer isEhrDeptReview;

	// 人资等级（10集团总部 11省区 12省会级医院 13地市级医院 14县市级医院 15眼科门诊 20大区 30区域）
	private String ehrLevel;

	/** 上报人所属EHR标准部门编号*/
	private String ehrAiStandDeptId;

	/** 上报人所属EHR标准部门编号描述*/
	private String ehrAiStandDeptIdDescr;

	/** 上报人所属EHR标准部门编号字符串*/
	private String ehrAiStandDeptIds;

	// 亚专科
	private String subspecialty;

	// 医院Id字符串(逗号隔开)
	private String strHospIds;

	// 首页展示（集团）菜单,柱状图穿透时类型区分:不良事件类型分布图(groupByGradeOne),省区不良事件上报分布图(groupByHospId),亚专科(groupBySubspecialty),医院不良事件上报TOP20分布图(groupByHospName)
	private String groupType;

    // 目前已查阅和未查阅的判断逻辑为，只要集团有一人查阅则视为已查阅，需将此逻辑在集团护理部6人账号进行优化，
	// 集团护理部人员包括：陈梅、余丽娇、李乐之、张立交、郑敏、周海南，将此6人视为一个整体，这6人任意一人查阅过，则视为已查阅，否则视为未查阅
	private List<Long> groupReviewEmpIds;
	//创建者姓名
	private String createName;
	//是否为省区未审核消息提醒
	private Boolean isProvinceReview = false;

	// 当事人员姓名
	private String staffName;


}
