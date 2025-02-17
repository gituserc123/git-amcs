package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.PageEnum;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.adverse.enums.CountEnum;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.feign.CountIndexService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/ui/amcs/countIndex")
public class CountIndexController extends BaseController {

	private static final String GROUP = "amcs/adverseEvent/countIndex/group";

	private static final String PROVINCE = "amcs/adverseEvent/countIndex/province";

	private static final String HOSP_MANAGE = "amcs/adverseEvent/countIndex/hospManage";

	private static final String HOSP_REPORT = "amcs/adverseEvent/countIndex/hospReport";

	private static final String HOSP_REPORT_UNFINISHED = "amcs/adverseEvent/countIndex/hospReport/unfinished";

	private static final String HOSP_REPORT_UNFINISHED90 = "amcs/adverseEvent/countIndex/hospReport/unfinished90";

	private static final String HOSP_REPORT_DISPUTE = "amcs/adverseEvent/countIndex/hospReport/dispute";

	private static final String HOSP_REPORT_COMPENSATION = "amcs/adverseEvent/countIndex/hospReport/compensation";

	private static final String HOSP_MANAGE_UNFINISHED = "amcs/adverseEvent/countIndex/hospManage/unfinished";

	private static final String HOSP_MANAGE_UNFINISHED90 = "amcs/adverseEvent/countIndex/hospManage/unfinished90";

	private static final String HOSP_MANAGE_DISPUTE = "amcs/adverseEvent/countIndex/hospManage/dispute";

	private static final String HOSP_MANAGE_COMPENSATION = "amcs/adverseEvent/countIndex/hospManage/compensation";

	private static final String HOSP_MANAGE_EVENTS_PROGRESS = "amcs/adverseEvent/countIndex/hospManage/eventsProgress";

	private static final String GROUP_UNFINISHED = "amcs/adverseEvent/countIndex/group/unfinished";

	private static final String GROUP_UNFINISHED90 = "amcs/adverseEvent/countIndex/group/unfinished90";

	private static final String GROUP_DISPUTE = "amcs/adverseEvent/countIndex/group/dispute";

	private static final String GROUP_COMPENSATION = "amcs/adverseEvent/countIndex/group/compensation";

	private static final String GROUP_EVENTS_PROGRESS = "amcs/adverseEvent/countIndex/group/eventsProgress";

	private static final String PROVINCE_UNFINISHED = "amcs/adverseEvent/countIndex/province/unfinished";

	private static final String PROVINCE_UNFINISHED90 = "amcs/adverseEvent/countIndex/province/unfinished90";

	private static final String PROVINCE_DISPUTE = "amcs/adverseEvent/countIndex/province/dispute";

	private static final String PROVINCE_COMPENSATION = "amcs/adverseEvent/countIndex/province/compensation";

	private static final String PROVINCE_EVENTS_PROGRESS = "amcs/adverseEvent/countIndex/province/eventsProgress";

	private static final String COUNT_PARAMSPAGE = "amcs/adverseEvent/countIndex/group/countByParamsPage";

	private static final String GROUP_INFECTION = "amcs/adverseEvent/countIndex/group/infectionGroup";

	private static final String GROUP_OTHERMULTIPLE = "amcs/adverseEvent/countIndex/group/otherMultipleGroup";

	@Value("${amcs.adverse.cond.roleName:集团视光不良事件管理}")
	private String roleName;

	@Autowired
	private CountIndexService service;

	@Autowired
	private AdverseEventService adverseEventService;

	@Autowired
	private HospHandleService hospHandleService;

	@Autowired
	private InstitutionService institutionService;

	@RequiresPermissions("IndexGroup:view")
	@RequestMapping(value = "/indexGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String indexGroup(Map<String, Object> map) {
		return GROUP;
	}

	@RequestMapping(value = "/unfinishedGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinishedGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_UNFINISHED;
	}

	@RequestMapping(value = "/unfinishedGroupGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinishedGroupGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			cond.setCountType(CountEnum.UNFINISHED.getValue());
			return service.findByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/unfinished90Group", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinished90Group(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_UNFINISHED90;
	}

	@RequestMapping(value = "/unfinished90GroupGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinished90GroupGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			cond.setCountType(CountEnum.UNFINISHED90.getValue());
			return service.findByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/disputeGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String disputeGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_DISPUTE;
	}

	@RequestMapping(value = "/disputeGroupGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> disputeGroupGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			cond.setCountType(CountEnum.DISPUTE.getValue());
			return service.findByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/compensationGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String compensationGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_COMPENSATION;
	}

	@RequestMapping(value = "/compensationGroupGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> compensationGroupGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			cond.setCountType(CountEnum.COMPENSATION.getValue());
			return service.findByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/eventsProgressGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String eventsProgressGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_EVENTS_PROGRESS;
	}

	@RequestMapping(value = "/eventsProgressGroupGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> eventsProgressGroupGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			cond.setCountType(CountEnum.EVENTS_PROGRESS.getValue());
			return service.findByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/group", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> group(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		cond.setPageType(PageEnum.GROUP_REVIEWS.getValue());
		this.addExtQueryCond(cond);
		if(Constants.GroupReviewEmp.contains(ShiroUtils.getLoginCode())){
			// "首页展示（集团）" 页面: 陈梅、余丽娇、李乐之、张立交、郑敏、周海南，将此6人视为一个整体，这6人任意一人查阅过，则视为已查阅，否则视为未查阅
			cond.setGroupReviewEmpIds(Arrays.asList(82512L,46244L,52780L,72308L,79904L,1612902187437109250L));
		}
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countIndex(cond);
		}
		return null;
	}

	@RequestMapping(value = "/groupByGradeOne", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupByGradeOne(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countByGradeOne(cond);
		}
		return null;
	}

	@RequestMapping(value = "/groupByHospId", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupByHospId(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countProvinceByHospId(cond);
		}
		return null;
	}

	@RequestMapping(value = "/groupByEventLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupByEventLevel(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countByEventLevel(cond);
		}
		return null;
	}

	@RequestMapping(value = "/groupBySeverityLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupBySeverityLevel(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countBySeverityLevel(cond);
		}
		return null;
	}

	@RequestMapping(value = "/groupBySubspecialty", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupBySubspecialty(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countBySubspecialty(cond);
		}
		return null;
	}

	@RequiresPermissions("IndexProvince:view")
	@RequestMapping(value = "/indexProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public String indexProvince(Map<String, Object> map) {
		return PROVINCE;
	}

	@RequestMapping(value = "/unfinishedProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinishedProvince(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return PROVINCE_UNFINISHED;
	}

	@RequestMapping(value = "/unfinishedProvinceGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinishedProvinceGrid(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					cond.setCountType(CountEnum.UNFINISHED.getValue());
					return service.findByParamsPage(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/unfinished90Province", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinished90Province(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return PROVINCE_UNFINISHED90;
	}

	@RequestMapping(value = "/unfinished90ProvinceGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinished90ProvinceGrid(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					cond.setCountType(CountEnum.UNFINISHED90.getValue());
					return service.findByParamsPage(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/disputeProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public String disputeProvince(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return PROVINCE_DISPUTE;
	}

	@RequestMapping(value = "/disputeProvinceGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> disputeProvinceGrid(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					cond.setCountType(CountEnum.DISPUTE.getValue());
					return service.findByParamsPage(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/compensationProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public String compensationProvince(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return PROVINCE_COMPENSATION;
	}

	@RequestMapping(value = "/compensationProvinceGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> compensationProvinceGrid(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					cond.setCountType(CountEnum.COMPENSATION.getValue());
					return service.findByParamsPage(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/eventsProgressProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public String eventsProgressProvince(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return PROVINCE_EVENTS_PROGRESS;
	}

	@RequestMapping(value = "/eventsProgressProvinceGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> eventsProgressProvinceGrid(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					cond.setCountType(CountEnum.EVENTS_PROGRESS.getValue());
					return service.findByParamsPage(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/province", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> province(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					return service.countIndex(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/provinceByGradeOne", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> provinceByGradeOne(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					return service.countByGradeOne(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/provinceByHospId", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> provinceByHospId(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					return service.countByHospId(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/provinceByEventLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> provinceByEventLevel(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					return service.countByEventLevel(cond);
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/provinceBySubspecialty", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> provinceBySubspecialty(AeInfoCondition cond) {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(UserContext.getUserCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (CollectionUtils.isNotEmpty(provinceRoleConfigList)) {
			Object instList = null;
			instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
			if (null != instList) {
				JSONArray ja = (JSONArray) instList;
				ArrayList<Long> hospList = Lists.newArrayList();
				ja.stream().forEach(j -> {
					JSONObject jo = (JSONObject) j;
					hospList.add(jo.getLong("ahisHosp"));
				});
				if (CollectionUtils.isNotEmpty(hospList)) {
					cond.setHospList(hospList);
					return service.countBySubspecialty(cond);
				}
			}
		}
		return null;
	}

	@RequiresPermissions("IndexHospManage:view")
	@RequestMapping(value = "/indexhospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String indexhospManage(Map<String, Object> map) {
		return HOSP_MANAGE;
	}

	@RequestMapping(value = "/unfinishedHospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinishedHospManage(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_MANAGE_UNFINISHED;
	}

	@RequestMapping(value = "/unfinishedHospManageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinishedHospManageGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.UNFINISHED.getValue());
		cond.setHospId(UserContext.getTenantId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/unfinished90HospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinished90HospManage(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_MANAGE_UNFINISHED90;
	}

	@RequestMapping(value = "/unfinished90HospManageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinished90HospManageGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.UNFINISHED90.getValue());
		cond.setHospId(UserContext.getTenantId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/disputeHospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String disputeHospManage(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_MANAGE_DISPUTE;
	}

	@RequestMapping(value = "/disputeHospManageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> disputeHospManageGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.DISPUTE.getValue());
		cond.setHospId(UserContext.getTenantId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/compensationHospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String compensationHospManage(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_MANAGE_COMPENSATION;
	}

	@RequestMapping(value = "/compensationHospManageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> compensationHospManageGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.COMPENSATION.getValue());
		cond.setHospId(UserContext.getTenantId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/eventsProgressHospManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String eventsProgressHospManage(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_MANAGE_EVENTS_PROGRESS;
	}

	@RequestMapping(value = "/eventsProgressHospManageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> eventsProgressHospManageGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.EVENTS_PROGRESS.getValue());
		cond.setHospId(UserContext.getTenantId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/hospManage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> hospManage(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		return service.countIndex(cond);
	}

	@RequestMapping(value = "/hospManageByGradeOne", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> hospManagetByGradeOne(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		return service.countByGradeOne(cond);
	}

	@RequestMapping(value = "/hospManageByDepartment", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> hospManagetByDepartment(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		return service.countByDepartment(cond);
	}

	@RequestMapping(value = "/hospManageByEventLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> hospManageByEventLevel(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		return service.countByEventLevel(cond);
	}

	@RequestMapping(value = "/hospManageBySubspecialty", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> hospManageBySubspecialty(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		return service.countBySubspecialty(cond);
	}

	@RequiresPermissions("IndexHospReport:view")
	@RequestMapping(value = "/indexHospReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String indexHospReport(Map<String, Object> map) {
		return HOSP_REPORT;
	}

	@RequestMapping(value = "/unfinishedHospReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinishedHospReport(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_REPORT_UNFINISHED;
	}

	@RequestMapping(value = "/unfinishedHospReportGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinishedHospReportGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.UNFINISHED.getValue());
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/unfinished90HospReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String unfinished90HospReport(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_REPORT_UNFINISHED90;
	}

	@RequestMapping(value = "/unfinished90HospReportGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> unfinished90HospReportGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.UNFINISHED90.getValue());
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/disputeHospReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String disputeHospReport(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_REPORT_DISPUTE;
	}

	@RequestMapping(value = "/disputeHospReportGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> disputeHospReportGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.DISPUTE.getValue());
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/compensationHospReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String compensationHospReport(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		return HOSP_REPORT_COMPENSATION;
	}

	@RequestMapping(value = "/compensationHospReportGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> compensationHospReportGrid(AeInfoCondition cond) {
		cond.setCountType(CountEnum.COMPENSATION.getValue());
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.findByParamsPage(cond);
	}

	@RequestMapping(value = "/hospReport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> hospReport(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.countIndex(cond);
	}

	@RequestMapping(value = "/hospReportByGradeOne", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> hospReportByGradeOne(AeInfoCondition cond) {
		cond.setHospId(UserContext.getTenantId());
		cond.setCreator(UserContext.getUserId());
		return service.countByGradeOne(cond);
	}


	@RequestMapping(value = "/groupByHospName", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> countHospitalByHospName(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			List<Map<String, Object>> top20Hosp = service.countHospitalByHospName(cond);
			top20Hosp.stream().forEach(th -> {
				Integer hospId = MapUtils.getInteger(th,"hospid");
				Institution institution = institutionService.getByAhisHosp(hospId);
				th.put("hospName",institution.getShortName());
			});

			return top20Hosp;
		}
		return null;
	}

	@RequestMapping(value = "/countByParamsPage", method = { RequestMethod.GET, RequestMethod.POST })
	public String countByParamsPage(Map<String, Object> map, String condJson) {
		AeInfoCondition cond = JSON.parseObject(condJson,AeInfoCondition.class);
		map.put("cond",JSON.toJSONString(cond));
		map.put("groupType",cond.getGroupType());
		return COUNT_PARAMSPAGE;
	}

	@RequestMapping(value = "/countByParamsPageGrid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> countByParamsPageGrid(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				if(Objects.isNull(cond.getHospId())){
					Object instList = hospHandleService.allHospFromParent(cond.getProvince());
					if (!Objects.isNull(instList)) {
						JSONArray ja = (JSONArray) instList;
						ArrayList<Long> hospList = Lists.newArrayList();
						ja.stream().forEach(j -> {
							JSONObject jo = (JSONObject) j;
							hospList.add(jo.getLong("ahisHosp"));
						});
						cond.setHospId(null);
						// 如果hospList为空，说明当前机构下没有医院，直接返回
						if (CollectionUtils.isNotEmpty(hospList)) {
							cond.setHospList(hospList);
						} else {
							return null;
						}
					}
				}
			}else{
				if(Objects.isNull(cond.getHospId())){
					if(Objects.isNull(cond.getStrHospIds())){
						Object instList = hospHandleService.allHospFromParent(cond.getProvince());
						if (!Objects.isNull(instList)) {
							JSONArray ja = (JSONArray) instList;
							ArrayList<Long> hospList = Lists.newArrayList();
							ja.stream().forEach(j -> {
								JSONObject jo = (JSONObject) j;
								hospList.add(jo.getLong("ahisHosp"));
							});
							cond.setHospId(null);
							// 如果hospList为空，说明当前机构下没有医院，直接返回
							if (CollectionUtils.isNotEmpty(hospList)) {
								cond.setHospList(hospList);
							} else {
								return null;
							}
						}
					}else{
						cond.setHospId(null);
						cond.setHospList(Arrays.stream(cond.getStrHospIds().split(",")).map(Long::parseLong).collect(Collectors.toList()));
					}
				}
			}
			return service.countByParamsPage(cond);
		}
		return null;
	}

	@RequestMapping(value = "/infectionGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String infectionGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_INFECTION;
	}

	@RequestMapping(value = "/queryInfectionByParams", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> queryInfectionByParams(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.queryInfectionByParams(cond);
		}
		return null;
	}

	@RequestMapping(value = "/otherMultipleGroup", method = { RequestMethod.GET, RequestMethod.POST })
	public String otherMultipleGroup(Map<String, Object> map, AeInfoCondition cond) {
		map.put("reportDateBegin", cond.getReportDateBegin());
		map.put("reportDateEnd", cond.getReportDateEnd());
		map.put("eventDateBegin", cond.getEventDateBegin());
		map.put("eventDateEnd", cond.getEventDateEnd());
		map.put("province", cond.getProvince());
		return GROUP_OTHERMULTIPLE;
	}

	@RequestMapping(value = "/groupProvReviewOver10Days", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> groupProvReviewOver10Days(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.countProvReviewOver10Days(cond);
		}
		return null;
	}

	@RequestMapping(value = "/queryOtherMultipleByParams", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody PageResponse<Map<String, Object>> queryOtherMultipleByParams(AeInfoCondition cond) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		this.addExtQueryCond(cond);
		if (shiroUser.getInstId() == 100002L) {
			// 集团登录
			if (!Objects.isNull(cond.getProvince())) {
				Object instList = hospHandleService.allHospFromParent(cond.getProvince());
				if (!Objects.isNull(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					cond.setHospId(null);
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (CollectionUtils.isNotEmpty(hospList)) {
						cond.setHospList(hospList);
					} else {
						return null;
					}
				}
			}
			return service.queryOtherMultipleByParams(cond);
		}
		return null;
	}

	private void addExtQueryCond(AeInfoCondition cond){
		// 如果包含"集团视光不良事件管理"角色,只允许查询视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
		if(ShiroUtils.hasRole(roleName)){
			List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(),EventEnum.FRAMEGLASSES.getValue(),EventEnum.VISUALTRAIN.getValue(),EventEnum.OTHEROPTOMETRY.getValue());
			cond.setEventCode(optometryList.stream().collect(Collectors.joining(",")));
		}
	}

}
