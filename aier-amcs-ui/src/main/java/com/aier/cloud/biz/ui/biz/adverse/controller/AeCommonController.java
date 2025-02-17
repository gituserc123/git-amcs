package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.ObjectUtil;
import com.aier.cloud.api.amcs.adverse.condition.AttachmentCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeFocusDomain;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeBasicInfo;
import com.aier.cloud.api.amcs.adverse.domain.AeOperationRecord;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.EventTypeEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseDictService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventTagService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeAttachmentService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeCommonService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeRecordService;
import com.aier.cloud.biz.ui.biz.adverse.feign.InstService;
import com.aier.cloud.biz.ui.biz.adverse.feign.MedicalService;
import com.aier.cloud.biz.ui.biz.adverse.feign.PatientInfoService;
import com.aier.cloud.biz.ui.biz.adverse.feign.StaffService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.adverse.service.OaPayService;
import com.aier.cloud.biz.ui.biz.common.feign.MedicalFeignService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/common")
public class AeCommonController extends BaseController {

	@Autowired
	private AeCommonService aeCommonService;

	@Autowired
	private AeAttachmentService aeAttachmentService;

	@Autowired
	private InstService instService;

	@Autowired
	private MedicalFeignService medicalFeignService;

	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private MedicalService medicalService;

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private HospHandleService hospHandleService;

	@Autowired
	private AdverseEventService adverseEventService;

	@Autowired
	private AdverseEventTagService adverseEventTagService;

	@Autowired
	private AeRecordService aeRecordService;

	@Autowired
	private OaPayService oaPayService;

	@Autowired
	private AdverseDictService aes;

	@Autowired
	private StaffService staffService;

	@Value("${spring.profiles.active}")
	private String profile;

	@Value("${amcs.adverse.cond.roleName:集团视光不良事件管理}")
	private String roleName;

	private final static String AE_URL_PREFIX = "amcs/adverseEvent/eventManage/";

	private final static String EVENT_INDEX = "amcs/adverseEvent/eventManage/eventList";

	private final static String REVIEW_INDEX = "amcs/adverseEvent/eventManage/eventReviewList";

	private final static String RETURN_INDEX = "amcs/adverseEvent/eventManage/eventReturnList";

	private final static String TRACK_INDEX = "amcs/adverseEvent/eventManage/eventTrackList";

	private final static String PROVINCE_COLUMN = "province";

	private final static String TAG_COLUMN = "tag";

	private final static String REPORTTIMES_COLUMN = "times";

	private final static String GROUP_VIEW_COLUMN = "groupReview";

	private final static String REVIEW_COLUMN = "review";


	@RequestMapping(value = "/listPage")
	public String listPage(HttpServletRequest request, @RequestParam(value = "page_type") Integer pageType,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "node", required = false) Integer node,
			@RequestParam(value = "is_review", required = false, defaultValue = "false") Boolean isReview,
			@RequestParam(value = "is_expert", required = false, defaultValue = "false") Boolean isExpert,
			@RequestParam(value = "isGroupReview", required = false) Integer isGroupReview,
			@RequestParam(value = "formData", required = false) String formData) {
		request.setAttribute("pageType", pageType);
		request.setAttribute("type", type);
		request.setAttribute("node", node);
		request.setAttribute("isReview", isReview);
		request.setAttribute("isExpert", isExpert);
		if (Objects.nonNull(isGroupReview)) {
			request.setAttribute("isGroupReview", isGroupReview);
		}
		if (Objects.nonNull(formData) && formData.length() > 0) {
			request.setAttribute("formDataParam", formData);
			JSONObject formJson = JSON.parseObject(formData, JSONObject.class);
			formJson.keySet().stream().forEach(key -> {
				Object value = formJson.get(key);
				if (value instanceof JSONArray) {
					JSONArray array = (JSONArray) value;
					String result = IntStream.range(0, array.size())
							.mapToObj(array::getString)
							.collect(Collectors.joining(", "));
					request.setAttribute(key, result);
				} else {
					request.setAttribute(key, value);
				}
			});
		}
		Integer empType = this.getEmpType();
		request.setAttribute("empType", empType);
		if (Constants.Province.equals(empType)) {
			Long instId = this.getProvinceId();
			request.setAttribute("insiId", instId);
		}
		if (isReview) {
			return REVIEW_INDEX;
		} else {
			return EVENT_INDEX;
		}
	}

	@RequestMapping(value = "/listReturnPage")
	public String listReturnPage(HttpServletRequest request) {
		return RETURN_INDEX;
	}

	private int getEmpType() {
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (!ShiroUtils.getIsHosp() || provinceRoleConfigList.size() > 0) {
			// 集团或省区登录
			if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
				// 集团登录
				return Constants.Group;
			} else {
				// 省区登录
				return Constants.Province;
			}
		}
		return Constants.Hosp;
	}

	private Long getProvinceId() {
		Long provinceId = null;
		Long instId = ShiroUtils.getInstId();
		InstCondition cond = new InstCondition();
		cond.setInstId(instId);
		List<Map> list = institutionService.getProvince(cond);
		List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
		if (l.size() > 0) {
			provinceId = ((Integer) l.get(0).get("id")).longValue();
		}
		return provinceId;
	}

	@RequestMapping(value = "/listTrackPage")
	public String listTrackPage(HttpServletRequest request,
			@RequestParam(value = "page_type", required = false, defaultValue = "0") Integer pageType,
			@RequestParam(value = "is_index", required = false, defaultValue = "0") Integer isIndex) {
		request.setAttribute("pageType", pageType);
		request.setAttribute("isIndex", isIndex);
		Integer empType = this.getEmpType();
		request.setAttribute("empType", empType);
		if (Constants.Province.equals(empType)) {
			Long instId = this.getProvinceId();
			request.setAttribute("insiId", instId);
		}
		return TRACK_INDEX;
	}

	@RequestMapping(value = "/groupView", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> groupView(@RequestParam Long basicId) {
		try {
			aeCommonService.updateGroupReview(basicId);
		} catch (Exception ex) {
			return this.fail();
		}
		return this.success();
	}

	@RequestMapping(value = "/focus", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> focus(@RequestParam Long basicId) {
		Map<String, Object> rst = Maps.newHashMap();
		AeFocusDomain focus = new AeFocusDomain();
		focus.setBasicId(basicId);
		focus.setModifer(UserContext.getUserId());
		focus.setModifyDate(new Date());
		try {
			Long focusId = aeCommonService.addFouce(focus);
			rst = this.success();
			rst.put("focusId", focusId);
		} catch (Exception ex) {
			rst = this.fail();
		}
		return rst;
	}

	@RequestMapping(value = "/delFocus", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delFocus(@RequestParam Long focusId) {
		try {
			aeCommonService.delFouce(focusId);
			return this.success();
		} catch (Exception ex) {
			return this.fail();
		}
	}

	/**
	 * 
	 * @param request
	 * @param eventCode
	 * @param pageType
	 * @param id
	 * @param isReview
	 * @param isExpert
	 * @param isIncrease
	 *                    是否是进展上报
	 * @param showOperate
	 *                    页面是否显示操作按钮
	 * @param showLast
	 *                    是否显示最新一次二次上报事件
	 * @param sourceType
	 *                    页面来源 0 -- eventList页面 1 -- eventReturnList页面
	 * @return
	 */
	@RequestMapping(value = "/indexPage")
	public String indexPage(HttpServletRequest request, @RequestParam String eventCode,
			@RequestParam(required = false) String patientNo,
			@RequestParam(value = "page_type", required = false, defaultValue = "0") Integer pageType,
			@RequestParam(required = false) Long id,
			@RequestParam(value = "is_review", required = false, defaultValue = "0") Boolean isReview,
			@RequestParam(value = "is_expert", required = false, defaultValue = "0") Boolean isExpert,
			@RequestParam(value = "isIncrease", required = false, defaultValue = "0") Boolean isIncrease,
			@RequestParam(value = "showOperate", required = false, defaultValue = "1") Boolean showOperate,
			@RequestParam(value = "showLast", required = false, defaultValue = "0") Boolean showLast,
			@RequestParam(value = "sourceType", required = false, defaultValue = "0") Integer sourceType,
			@RequestParam(value = "isEhrDeptReview", required = false, defaultValue = "2") Integer isEhrDeptReview) {
		EventEnum eEnum = EventEnum.findEnumByCode(eventCode);
		Integer reportTimes = 1;
		if (ObjectUtils.isEmpty(eEnum)) {
			return null;
		}
		Integer type = eEnum.getType();
		String IndexUrl = AE_URL_PREFIX.concat(eEnum.getName()).concat("Manage");
		request.setAttribute("hospId", ShiroUtils.getTenantId());
		request.setAttribute("instId", ShiroUtils.getInstId());
		request.setAttribute("hospName", ShiroUtils.getInstName());
		Long deptId = ShiroUtils.getDeptId();
		request.setAttribute("showOperate", showOperate);
		request.setAttribute("eventCode", eventCode);
		request.setAttribute("pageType", pageType);
		request.setAttribute("type", type);
		request.setAttribute("isReview", isReview);
		request.setAttribute("profile", profile);
		request.setAttribute("sourceType", sourceType);
		request.setAttribute("isIncrease", isIncrease);

		Institution institution = instService.getDeptDetailByInstId(deptId);
		if (!ObjectUtils.isEmpty(institution)) {
			request.setAttribute("department", institution.getName());
		}

		Map<String, Object> aeInfo = Maps.newHashMap();

		// 获取医院信息
		if (!ObjectUtils.isEmpty(id)) {
			// 从事件列表进去时默认显示最近一次事件
			aeInfo = aeCommonService.getLastEvent(id, eEnum, showLast);
			reportTimes = Integer.valueOf(aeInfo.get("reportTimes").toString());

			// 查询是否存在病例数据
			boolean hasEmr = aeAttachmentService.hasEmr(id);
			aeInfo.put("hasEmr", hasEmr);

			// 计算总的赔偿金额
			AeInfoCondition cond = new AeInfoCondition();
			cond.setOperateType(OperateEnum.QUERY_MULTI.getType());
			cond.setId(id);
			cond.setMasterId(id);
			AeBasicInfo amountInfo = aeCommonService.sumAmount(cond);
			if (ObjectUtils.isEmpty(amountInfo)) {
				request.setAttribute("allCompensationAmount", 0);
				request.setAttribute("allDeductionAmount", 0);
			} else {
				request.setAttribute("allCompensationAmount", amountInfo.getAllCompensationAmount());
				request.setAttribute("allDeductionAmount", amountInfo.getAllDeductionAmount());
			}

			// 进展上报
			if (isIncrease) {
				reportTimes += 1;
				// 清空ID主键
				aeInfo.remove("eventId");
				aeInfo.remove("basicId");
				request.setAttribute("operate", true);
				aeInfo.put("reportTimes", reportTimes);
				aeInfo.put("prevId", id);
				String tags = adverseEventTagService.findTagsById(id);
				request.setAttribute("tags", tags);
				// 进展上报清空赔偿金额和减免金额
				aeInfo.put("compensationAmount", 0);
				aeInfo.put("deductionAmount", 0);
				aeInfo.put("oaRequestId", null);

			} else {
				// 判断当前页面和事件所在节点 页面已过节点情况时，相关按钮不可操作
				Integer node = Integer.valueOf(aeInfo.get("node").toString());

				// 判断是否是集团查阅，集团查阅时，直接更新状态为已查阅
				if (pageType == NodeEnum.GROUP_REVIEWS.getSeq()) {
					cond = new AeInfoCondition();
					cond.setBasicId(id);
					cond.setType(OperateEnum.REVIEW.getType());
					cond.setNode(NodeEnum.PROVINCE_REVIEWS.getSeq());
					AeOperationRecord aeOperationRecord = aeRecordService.getOperatorRecord(cond);
					if (!ObjectUtils.isEmpty(aeOperationRecord)) {
						request.setAttribute("aeOperationRecord", aeOperationRecord);
					}
					request.setAttribute("basicId", id);
					aeCommonService.updateGroupReview(id);
				}
				request.setAttribute("basicId", id);
				// 判断是否科室查阅
				if (isEhrDeptReview.intValue() == 2) {
					request.setAttribute("isEhrDeptReview", 0); // 非科室查阅
				} else {
					request.setAttribute("isEhrDeptReview", 1); // 科室查阅
					if (isEhrDeptReview.intValue() == 0) {
						// 更新T_AE_BASIC_INFO表IS_EHR_DEPT_REVIEW标志位为1-已查阅
						aeCommonService.updateEhrDeptReview(id);
					}
				}
				if (isReview && pageType > 1) {
					// 医院完成审核即可进行事件点评
					if (node >= NodeEnum.HOSPITAL_REVIEWS.getSeq() || isExpert) {
						request.setAttribute("operate", true);
					} else {
						request.setAttribute("operate", false);
					}
				} else {
					if (pageType == node) {
						if (node == NodeEnum.REPORTING.getSeq() || node == NodeEnum.GROUP_REVIEWS.getSeq()) {
							request.setAttribute("operate", true);
						} else {
							request.setAttribute("operate", false);
						}
					} else if (pageType < node && pageType != 0) {
						request.setAttribute("operate", false);
					} else {
						request.setAttribute("operate", true);
					}
				}
				// 获取标签信息
				String tags = adverseEventTagService.findTagsById(id);
				request.setAttribute("tags", tags);
			}

		} else {
			request.setAttribute("allCompensationAmount", 0);
			request.setAttribute("allDeductionAmount", 0);
			// 根据表单类型配置默认标签选项
			if (EventTypeEnum.MEDICAL.getType().equals(type)) {
				request.setAttribute("tags", 1);
			} else if (EventTypeEnum.CARE.getType().equals(type)) {
				request.setAttribute("tags", 2);
			} else if (EventTypeEnum.INFECT.getType().equals(type)) {
				request.setAttribute("tags", 5);
			} else if (EventTypeEnum.OTHER.getType().equals(type)) {
				request.setAttribute("tags", 9);
			} else {
				request.setAttribute("tags", -1);
			}
			request.setAttribute("operate", true);
			aeInfo.put("reportTimes", reportTimes);
			aeInfo.put("patientNo", patientNo);
		}

		request.setAttribute("ae", aeInfo);
		String str = JSONUtil.toJsonStr(aeInfo);
		str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("'", "\\'");
		str = str.replaceAll("`", "\\\\`");
		request.setAttribute("aeJson", str);
		request.setAttribute("reportTimes", reportTimes);
		request.setAttribute("comboboxFilter", eEnum.getFilter());

		return IndexUrl;
	}

	@RequestMapping(value = "/findByBasicId", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findByBasicId(@RequestParam String eventCode, @RequestParam Long id) {
		Map<String, Object> mInfo = Maps.newHashMap();
		EventEnum eEnum = EventEnum.findEnumByCode(eventCode);
		mInfo = aeCommonService.findById(id, eEnum);
		return mInfo;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> save(@RequestBody Map<String, Object> mData) {
		if (UserContext.getTenantId() == 9999L && profile.equals("prod")) {
			throw new BizException("虚拟医院不能上报！");
		}
		// 获取医院信息
		if (ObjectUtils.isEmpty(mData.get("eventCode"))) {
			return this.fail("未传入不良事件编码！");
		} else {
			EventEnum eEnum = EventEnum.findEnumByCode(mData.get("eventCode").toString());
			if (ObjectUtils.isEmpty(eEnum)) {
				return this.fail("未找到匹配的不良事件编码");
			}
			// 传入EHR科室ID
			try {
				if (StringUtils.isEmpty(mData.get("basicId"))) {
					SysCommonCondition sysCommonCondition = new SysCommonCondition();
					sysCommonCondition.setCode(ShiroUtils.getLoginCode());
					List<Map<String, Object>> ehrInfoLists = staffService.getJobPos(sysCommonCondition);
					if (Objects.nonNull(ehrInfoLists) && ehrInfoLists.size() > 0) {
						Optional<Map<String, Object>> ehrOpt = ehrInfoLists.stream().filter(
								ei -> MapUtils.getString(ei, "INST_ID").equals(String.valueOf(ShiroUtils.getInstId())))
								.findFirst();
						if (ehrOpt.isPresent()) {
							mData.put("ehrAiStandDeptId", MapUtils.getString(ehrOpt.get(), "DEPTID_ONE"));
							mData.put("ehrAiStandDeptIdDescr",
									MapUtils.getString(ehrOpt.get(), "DEPTID_DESCR_ONE"));
						}
					}
				}
			} catch (Exception e) {
				logger.info("获取当前登录人员EHR科室信息失败!{}", e.getMessage());
			}

			Map<String, Object> map = aeCommonService.save(mData, eEnum);
			/*
			 * //自动提交oa流程 if(profile.equals("sit")){ try {
			 * if(mData.get("node").equals("2")&&mData.get("operateType").equals(1)){ Long
			 * id=Long.parseLong(mData.get("basicId").toString()); AeBasicInfo
			 * basicInfo=aeCommonService.getBasicById(id);
			 * if(basicInfo.getCompensationAmount().compareTo(BigDecimal.ZERO)>0&&
			 * ObjectUtils.isEmpty(basicInfo.getOaRequestId())){ payCreate(id); } } } catch
			 * (Exception e) { e.printStackTrace(); } }
			 */
			return map;
		}
	}

	@PostMapping(value = "/saveBasicList")
	public @ResponseBody Map<String, Object> saveBasicList(@RequestBody List<AeBasicInfo> basicList) {
		if (aeCommonService.update(basicList)) {
			return success("操作成功");
		} else {
			return this.fail("操作失败");
		}
	}

	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> merge(@RequestParam String masterId,
			@RequestParam(value = "mergeIds[]") List<Long> mergeIds) {
		if (ObjectUtils.isEmpty(masterId))
			return this.fail("无法获取主ID");
		aeCommonService.merge(Long.parseLong(masterId), mergeIds);
		return this.success();
	}

	@RequestMapping(value = "/reback", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> reback(@RequestParam(value = "node", required = false) Integer node,
			@RequestParam String eventCode, @RequestParam Long basicId) {
		if (ObjectUtils.isEmpty(eventCode)) {
			return this.fail("未传入不良事件编码！");
		}
		EventEnum eEnum = EventEnum.findEnumByCode(eventCode);
		aeCommonService.reback(basicId, eEnum, node);
		return this.success();
	}

	@RequestMapping(value = "/findMultiList", method = RequestMethod.POST)
	@ResponseBody
	public PageResponse<Map<String, Object>> findMultiList(AeInfoCondition cond) {
		cond.setOperateType(OperateEnum.QUERY_MULTI.getType());
		cond.setShowAllNode(true);
		PageResponse<Map<String, Object>> retList = aeCommonService.findListByCond(cond);
		retList.getRows().stream().forEach(item -> {
			// 报表类型
			item.put("reportType", EventEnum.findEnumByCode(String.valueOf(item.get("eventCode"))).getReportType());
		});
		return retList;
	}

	@RequestMapping(value = "/findReturnList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findReturnList(AeInfoCondition cond) {
		cond = this.setPermissionByPage(cond);
		cond = setHospListByUser(cond);
		// 如果包含"集团视光不良事件管理"角色,只允许查询视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
		if (ShiroUtils.hasRole(roleName)) {
			List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(), EventEnum.FRAMEGLASSES.getValue(),
					EventEnum.VISUALTRAIN.getValue(), EventEnum.OTHEROPTOMETRY.getValue());
			if (ObjectUtil.isEmpty(cond.getEventCode())) {
				cond.setEventCode(optometryList.stream().collect(Collectors.joining(",")));
			}
		}
		return this.addExt(aeCommonService.findReturnList(cond), Arrays.asList(PROVINCE_COLUMN));
	}

	@RequestMapping(value = "/findReviewList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findReviewList(AeInfoCondition cond) {
		cond = this.setPermissionByPage(cond);
		if (ObjectUtils.isEmpty(cond.getIsExpert()) || !cond.getIsExpert()) {
			cond = setHospListByUser(cond);
		}
		// 如果包含"集团视光不良事件管理"角色,只允许查询视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
		if (ShiroUtils.hasRole(roleName)) {
			List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(), EventEnum.FRAMEGLASSES.getValue(),
					EventEnum.VISUALTRAIN.getValue(), EventEnum.OTHEROPTOMETRY.getValue());
			if (ObjectUtil.isEmpty(cond.getEventType())) {
				cond.setTypes(optometryList.stream().collect(Collectors.joining(",")));
			}
		}
		return this.addExt(aeCommonService.findReviewList(cond), Arrays.asList(PROVINCE_COLUMN, REVIEW_COLUMN));
	}

	@RequestMapping(value = "/findList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findList(AeInfoCondition cond) {
		cond = this.setPermissionByPage(cond);
		// 设置医院
		cond = setHospListByUser(cond);
		if(Constants.GroupReviewEmp.contains(ShiroUtils.getLoginCode())){
			// "首页展示（集团）" 页面: 陈梅、余丽娇、李乐之、张立交、郑敏、周海南，将此6人视为一个整体，这6人任意一人查阅过，则视为已查阅，否则视为未查阅
			cond.setGroupReviewEmpIds(Arrays.asList(82512L,46244L,52780L,72308L,79904L,1612902187437109250L));
		}
		// 判断需要查询归档，没有时则设置为False
		if (ObjectUtils.isEmpty(cond.getShowArchived())) {
			cond.setShowArchived(false);
		}
		List<String> rowsArray = Lists.newArrayList();
		rowsArray.add(PROVINCE_COLUMN);
		rowsArray.add(REPORTTIMES_COLUMN);
		rowsArray.add(TAG_COLUMN);
		if (NodeEnum.GROUP_REVIEWS.getSeq().equals(cond.getPageType())) {
			rowsArray.add(GROUP_VIEW_COLUMN);
		}

		cond.setOperator(UserContext.getUserId());
		// 如果包含"集团视光不良事件管理"角色,只允许查询视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
		if (ShiroUtils.hasRole(roleName)) {
			List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(), EventEnum.FRAMEGLASSES.getValue(),
					EventEnum.VISUALTRAIN.getValue(), EventEnum.OTHEROPTOMETRY.getValue());
			if (ObjectUtil.isEmpty(cond.getEventType())) {
				cond.setTypes(optometryList.stream().collect(Collectors.joining(",")));
			}
		}
		return this.addExt(aeCommonService.findListByCond(cond), rowsArray);
	}

	@RequestMapping(value = "/queryList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> queryList(AeInfoCondition cond) {

		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		// 组装查询条件 --begin
		// 判断需要查询归档，没有时则设置为False
		if (ObjectUtils.isEmpty(cond.getShowArchived())) {
			cond.setShowArchived(false);
		}
		if (cond.getEventLevelDesc() != null) {
			cond.setEventLevel(cond.getEventLevelDesc());
		}
		if (cond.getEventCode() == null) {
			if (cond.getEventType() != null) {
				if (cond.getEventType().equals(EventTypeEnum.MEDICAL.getType().toString())) {
					cond.setEventCode(Arrays.stream(EventEnum.values()).map(EventEnum::getValue)
							.filter(value -> value.startsWith(EventTypeEnum.MEDICAL.getType().toString()))
							.collect(Collectors.joining(",")));
				} else if (cond.getEventType().equals(EventTypeEnum.CARE.getType().toString())) {
					cond.setEventCode(Arrays.stream(EventEnum.values()).map(EventEnum::getValue)
							.filter(value -> value.startsWith(EventTypeEnum.CARE.getType().toString()))
							.collect(Collectors.joining(",")));
				} else if (cond.getEventType().equals(EventTypeEnum.INFECT.getType().toString())) {
					cond.setEventCode(Arrays.stream(EventEnum.values()).map(EventEnum::getValue)
							.filter(value -> value.startsWith(EventTypeEnum.INFECT.getType().toString()))
							.collect(Collectors.joining(",")));
				} else {
					cond.setEventCode(Arrays.stream(EventEnum.values()).map(EventEnum::getValue)
							.filter(value -> value.startsWith(EventTypeEnum.OTHER.getType().toString()))
							.collect(Collectors.joining(",")));
				}
				cond.setEventType(null);
			}
		}
		/*
		if (cond.getUnplan() != null && cond.getUnplan() > 0) {
			// 非计划情况只存在于住院患者不良事件,门诊患者不良事件,医院感染不良事件
			cond.setEventCode(Arrays.asList(EventEnum.INPATIENT.getValue(), EventEnum.OUTPPATIENT.getValue(),
					EventEnum.INFECTION.getValue()).stream().collect(Collectors.joining(",")));
		}*/
		// 如果包含"集团视光不良事件管理"角色,只允许查询视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
		if (ShiroUtils.hasRole(roleName)) {
			List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(), EventEnum.FRAMEGLASSES.getValue(),
					EventEnum.VISUALTRAIN.getValue(), EventEnum.OTHEROPTOMETRY.getValue());
			if (ObjectUtil.isEmpty(cond.getEventCode())) {
				cond.setEventCode(optometryList.stream().collect(Collectors.joining(",")));
			}
		}
		if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
			cond.setHospId(shiroUser.getTenantId());
		} else {
			// 集团或省区登录
			if (shiroUser.getInstId() == 100002L) {
				// 集团登录
				if (cond.getProvince() != null && cond.getProvince() > 0) {
					if (cond.getHospId() != null && cond.getHospId() > 0) {
						Institution inst = institutionService.getById(cond.getHospId());
						cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
						cond.setHospList(null);
					} else {
						Object instList = hospHandleService.allHospFromParent(cond.getProvince());
						if (instList != null) {
							JSONArray ja = (JSONArray) instList;
							ArrayList<Long> hospList = Lists.newArrayList();
							ja.stream().forEach(j -> {
								JSONObject jo = (JSONObject) j;
								hospList.add(jo.getLong("ahisHosp"));
							});
							cond.setHospId(null);
							// 如果hospList为空，说明当前机构下没有医院，直接返回
							if (hospList.size() > 0) {
								cond.setHospList(hospList);
							} else {
								return new PageResponse<>();
							}
						}
					}
				}
			} else {
				// 省区登录
				if (cond.getHospId() != null && cond.getHospId() > 0) {
					Institution inst = institutionService.getById(cond.getHospId());
					cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
					cond.setHospList(null);
				} else {
					Object instList;
					if (provinceRoleConfigList.size() > 0) {
						instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
					} else {
						instList = hospHandleService.allHospFromParent(shiroUser.getInstId());
					}

					if (instList != null) {
						JSONArray ja = (JSONArray) instList;
						ArrayList<Long> hospList = Lists.newArrayList();
						ja.stream().forEach(j -> {
							JSONObject jo = (JSONObject) j;
							hospList.add(jo.getLong("ahisHosp"));
						});
						cond.setHospId(null);
						// 如果hospList为空，说明当前机构下没有医院，直接返回
						if (hospList.size() > 0) {
							cond.setHospList(hospList);
						} else {
							return new PageResponse<>();
						}
					}
				}
			}
		}
		// 组装查询条件 --end
		/** inst_id=>hosp_id */
		if (Objects.nonNull(cond.getHospId()) && cond.getHospId() > 9999) {
			Institution inst = institutionService.getById(cond.getHospId());
			if (!ObjectUtils.isEmpty(inst)) {
				cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
			}
		}
		// 判断是否符合上报时限,规则:上报时间-发生时间≤3天（72小时）属于符合上报时限
		PageResponse<Map<String, Object>> retVal = aeCommonService.queryListByCond(cond);
		List<Map<String, Object>> dataList = retVal.getRows();
		if (Objects.nonNull(dataList) && dataList.size() > 0) {
			AeInfoCondition basicCond = new AeInfoCondition();
			List<Long> basicList = dataList.stream().map(item -> Long.valueOf(item.get("id").toString()))
					.collect(Collectors.toList());
			basicCond.setBasicIds(basicList);
			List<Map<String, Object>> tags = adverseEventTagService.findListByCond(basicCond);
			Map<String, List<Map<String, Object>>> mapTags = tags.stream()
					.collect(Collectors.groupingBy(d -> String.valueOf(d.get("eventId"))));
			Map<String, Object> tagStrMap = new HashMap<>();
			mapTags.keySet().stream().forEach(mt -> tagStrMap.put(mt, mapTags.get(mt).stream()
					.map(item -> String.valueOf(item.get("tagCodeName"))).collect(Collectors.joining(","))));
			// 附件查询
			AttachmentCondition attCond = new AttachmentCondition();
			attCond.setBasicIds(basicList);
			attCond.setTag("adv_querylist");
			List<Map<String, Object>> attachs = aeAttachmentService.queryByCond(attCond);
			Map<String, List<Map<String, Object>>> mapAttachs = attachs.stream()
					.collect(Collectors.groupingBy(d -> String.valueOf(d.get("basicid"))));
			List<Map> unplanDict = aes.getList("unplan", null);
			List<Map> severityLevelDict = aes.getList("severity_level", null);
			Map<String, Map> existHosp = new HashMap<>();
			dataList.stream().forEach(obj -> {
				if (Objects.nonNull(obj.get("createDate")) && Objects.nonNull(obj.get("eventDate"))) {
					LocalDate eventDateTime = LocalDate.parse(String.valueOf(obj.get("eventDate")),
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					// 修改为取T_AE_OPERATION_RECORD表最早的操作时间,如果该时间为空，则取createDateTime
					LocalDate minOperDate = LocalDate.parse(
							MapUtils.getString(obj, "minOperDate") != null ? MapUtils.getString(obj, "minOperDate")
									: MapUtils.getString(obj, "createDate"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					long daysDiff = ChronoUnit.DAYS.between(eventDateTime, minOperDate);
					if (daysDiff > 30L) {
						obj.put("isConform", 2);
					} else {
						obj.put("isConform", 1);
					}
				} else {
					obj.put("isConform", 2);
				}
				// 报表类型
				obj.put("reportType", EventEnum.findEnumByCode(String.valueOf(obj.get("eventCode"))).getReportType());
				// 涉及科室/人员类型
				obj.put("tagCodeName", tagStrMap.get(String.valueOf(obj.get("id"))));
				// 非计划情况
				if (obj.get("unplan") != null) {
					Optional<Map> matchItem = unplanDict.stream().filter(
							ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("unplan"))))
							.findFirst();
					if (matchItem.isPresent()) {
						obj.put("unplanDesc", matchItem.get().get("valueDesc"));
					}
				}
				if (obj.get("inpUnplan") != null) {
					Optional<Map> matchItem = unplanDict.stream().filter(
							ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("inpUnplan"))))
							.findFirst();
					if (matchItem.isPresent()) {
						obj.put("unplanDesc", matchItem.get().get("valueDesc"));
					}
				}
				if (obj.get("outpPatientUnplan") != null) {
					Optional<Map> matchItem = unplanDict.stream().filter(ud -> String.valueOf(ud.get("valueCode"))
							.equals(String.valueOf(obj.get("outpPatientUnplan")))).findFirst();
					if (matchItem.isPresent()) {
						obj.put("unplanDesc", matchItem.get().get("valueDesc"));
					}
				}
				if (obj.get("infectionUnplan") != null) {
					Optional<Map> matchItem = unplanDict.stream().filter(ud -> String.valueOf(ud.get("valueCode"))
							.equals(String.valueOf(obj.get("infectionUnplan")))).findFirst();
					if (matchItem.isPresent()) {
						obj.put("unplanDesc", matchItem.get().get("valueDesc"));
					}
				}
				// 严重程度
				if (obj.get("severityLevel") != null) {
					Optional<Map> matchItem = severityLevelDict.stream()
							.filter(sld -> String.valueOf(sld.get("valueCode"))
									.equals(MapUtils.getString(obj, "severityLevel")))
							.findFirst();
					if (matchItem.isPresent()) {
						obj.put("severityLevelDesc", matchItem.get().get("valueDesc"));
					} else {
						obj.put("severityLevelDesc", "");
					}
				}
				// 医院类型和省区
				if (Objects.nonNull(obj.get("hospId"))) {
					System.out.println(obj.get("hospId"));
					if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
						obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
						obj.put("investNature", existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
						if (existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL") != null) {
							obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(
									String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL"))));
						}
					} else {
						InstCondition cond_1 = new InstCondition();
						cond_1.setInstId(Long.parseLong(String.valueOf(obj.get("hospId"))));
						cond_1.setInstType(InstEnum.HOSP.getEnumCode());
						List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
						if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
							Map resultMap = hosps.get(0);
							/** 查询一级省区 */
							InstCondition cond_2 = new InstCondition();
							cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
							List<Map> list = institutionService.getProvince(cond_2);
							List<Map> l = list.stream().filter(e -> e.get("grade").equals(1))
									.collect(Collectors.toList());
							obj.put("hospParent", l.get(0).get("name"));
							obj.put("investNature", resultMap.get("INVEST_NATURE"));
							if (resultMap.get("EHR_LEVEL") != null) {
								obj.put("ehrLevel",
										EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
							}
							resultMap.put("PARENT_NAME", l.get(0).get("name"));
							existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
						}
					}
				}
				// 附件信息
				obj.put("attachs", mapAttachs.get(String.valueOf(obj.get("id"))));
			});
		}
		return retVal;
	}

	private PageResponse<Map<String, Object>> addExt(PageResponse<Map<String, Object>> res, List<String> extColumns) {
		AeInfoCondition cond = new AeInfoCondition();
		List<Map<String, Object>> dataList = res.getRows();

		if(dataList.size() == 0) return res;
		List<Long> basicList = dataList.stream().map(item -> Long.valueOf(item.get("id").toString()))
				.collect(Collectors.toList());
		cond.setBasicIds(basicList);
		// 获取事件对于标签名称
		List<Map<String, Object>> tags = adverseEventTagService.findListByCond(cond);
		Map<String, List<Map<String, Object>>> mapTags = tags.stream()
				.collect(Collectors.groupingBy(d -> String.valueOf(d.get("eventId"))));
		Map<String, Object> tagStrMap = Maps.newHashMap();
		mapTags.keySet().stream().forEach(mt -> tagStrMap.put(mt, mapTags.get(mt).stream()
				.map(item -> String.valueOf(item.get("tagCode"))).collect(Collectors.joining(","))));
		// 获取查询列表中医院所在的省区
		Map<Object, Object> provinceList = Maps.newHashMap();
		HashSet<Object> hospIdSet = dataList.stream().map(data -> data.get("hospId"))
				.collect(Collectors.toCollection(HashSet::new));
		hospIdSet.stream().forEach(hospId -> {
			InstCondition cond_1 = new InstCondition();
			cond_1.setInstId(Long.parseLong(String.valueOf(hospId)));
			cond_1.setInstType(InstEnum.HOSP.getEnumCode());
			List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
			if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
				Map resultMap = hosps.get(0);
				/** 查询一级省区 */
				InstCondition cond_2 = new InstCondition();
				cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
				List<Map> list = institutionService.getProvince(cond_2);
				List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
				if (!ObjectUtils.isEmpty(l)) {
					provinceList.put(hospId, l.get(0).get("name"));
				}
			}
		});

		// 获取事件的最大上报次数已经最新上报时间
		List<Map<String, Object>> lastEventInfos = aeCommonService.getLastEventInfo(basicList);
		Map<Object, Map<String, Object>> lastEventMaps = Maps.newHashMap();
		lastEventInfos.stream().forEach(obj -> lastEventMaps.put(obj.get("basicId"), obj));
		// 获取时间的查阅记录
		List<Map<String, Object>> lookList = aeCommonService.findLookList(basicList);

		Map<Object, Boolean> lookMaps = Maps.newHashMap();
		lookList.stream().forEach(obj -> lookMaps.put(obj.get("basicId"), true));
        //获取点评状态
		Map<Object, Boolean> expertReviews = Maps.newHashMap();
		Map<Object, Boolean> groupReviews = Maps.newHashMap();
		Map<Object, Boolean> provinceReviews = Maps.newHashMap();
		if(extColumns.contains(REVIEW_COLUMN)){
			List<Map<String, Object>> expertReviewList = aeCommonService.findReviewList(basicList, NodeEnum.EXPERT_REVIEWS.getSeq());
			List<Map<String, Object>> groupReviewList = aeCommonService.findReviewList(basicList, NodeEnum.GROUP_REVIEWS.getSeq());
			List<Map<String, Object>> provinceReviewList = aeCommonService.findReviewList(basicList, NodeEnum.PROVINCE_REVIEWS.getSeq());
			expertReviewList.stream().forEach(obj -> expertReviews.put(obj.get("basicId"), true));
			groupReviewList.stream().forEach(obj -> groupReviews.put(obj.get("basicId"), true));
			provinceReviewList.stream().forEach(obj -> provinceReviews.put(obj.get("basicId"), true));
		}

		dataList.stream().forEach(obj -> {

			if (extColumns.contains(TAG_COLUMN)) {
				obj.put("tags", tagStrMap.get(obj.get("id").toString()));
			}

			if (extColumns.contains(PROVINCE_COLUMN)) {
				Object curHospId = obj.get("hospId");
				obj.put("hospParent", provinceList.get(curHospId));
			}

			if (extColumns.contains(REPORTTIMES_COLUMN)) {
				Map<String, Object> lastInfo = lastEventMaps.get(obj.get("id"));
				if (ObjectUtils.isEmpty(lastInfo)) {
					obj.put("maxReportTimes", obj.get("reportTimes"));
					obj.put("lastReportDate", obj.get("createDate"));
				} else {
					obj.put("maxReportTimes", lastInfo.get("lastTime"));
					obj.put("lastReportDate", lastInfo.get("lastDate"));
				}
			}

			if (extColumns.contains(GROUP_VIEW_COLUMN)) {
				Boolean hasLook = lookMaps.get(obj.get("id"));
				if (!ObjectUtils.isEmpty(hasLook)) {
					obj.put("isGroupReview", 1);
				} else {
					obj.put("isGroupReview", 0);
				}
			}
			if(extColumns.contains(REVIEW_COLUMN)){
				Boolean hasExpertReview = expertReviews.get(obj.get("id"));
				if (!ObjectUtils.isEmpty(hasExpertReview)) {
					obj.put("eReviewStatus", 1);
				} else {
					obj.put("eReviewStatus", 0);
				}
				Boolean hasGroupReview = groupReviews.get(obj.get("id"));
				if (!ObjectUtils.isEmpty(hasGroupReview)) {
					obj.put("gReviewStatus", 1);
				} else {
					obj.put("gReviewStatus", 0);
				}
				Boolean hasProvinceReview = provinceReviews.get(obj.get("id"));
				if (!ObjectUtils.isEmpty(hasProvinceReview)) {
					obj.put("pReviewStatus", 1);
				} else {
					obj.put("pReviewStatus", 0);
				}
			}

		});

		return res;
	}

	private AeInfoCondition setHospListByUser(AeInfoCondition cond) {

		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);

		if (provinceRoleConfigList.size() == 0 && ShiroUtils.getIsHosp()) {
			cond.setHospId(ShiroUtils.getTenantId());
		} else { // 集团或省区
			Long parentId = null;
			Long hospId = null;

			if (cond.getHospId() != null && cond.getHospId() > 0) {
				hospId = cond.getHospId();
				// 判断是否是四位AHIS ID
				if (hospId / 10000 != 0) {
					Institution inst = institutionService.getById(hospId);
					hospId = Long.parseLong(inst.getAhisHosp().toString());
				}
			} else {
				if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
					parentId = cond.getProvince();
				} else if (provinceRoleConfigList.size() > 0) {
					parentId = provinceRoleConfigList.get(0).getProvinceId();
				} else {
					parentId = ShiroUtils.getInstId();
				}
			}

			if (!ObjectUtils.isEmpty(hospId)) {
				cond.setHospId(hospId);
			} else if (!ObjectUtils.isEmpty(parentId)) {
				Object instList = hospHandleService.allHospFromParent(parentId);
				if (!ObjectUtils.isEmpty(instList)) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = Lists.newArrayList();
					ja.forEach(item -> {
						JSONObject obj = (JSONObject) item;
						hospList.add(obj.getLong("ahisHosp"));
					});

					cond.setHospList(hospList);
					cond.setHospId(null);
				} else {
					// 当省区找不到医院时设置一个不存在的医院ID,然其查到的数据为空
					cond.setHospId(-1L);
				}
			}
		}

		return cond;
	}

	/**
	 * 根据页面类型增加相应权限
	 *
	 * @param cond
	 * @return
	 */
	private AeInfoCondition setPermissionByPage(AeInfoCondition cond) {
		// 获取当前医院所在省区的所有医院
		Long tenatId = UserContext.getTenantId();
		Integer pageType = cond.getPageType();

		if (!ObjectUtils.isEmpty(pageType)) {
			switch (pageType) {
				case 1:
					if (ShiroUtils.hasPermission("IncreaseEvent:hosp")) {
						cond.setHospId(tenatId);
					} else {
						cond.setCreator(UserContext.getUserId());
					}
					break;
				case 2:
					if (ObjectUtils.isEmpty(cond.getNode())) {
						cond.setNodeEnum(NodeEnum.HOSPITAL_REVIEWS);
					}
					cond.setHospId(tenatId);
					break;
				case 3:
					ArrayList<Long> hospList = Lists.newArrayList();
					if (ObjectUtils.isEmpty(cond.getNode())) {
						cond.setNodeEnum(NodeEnum.PROVINCE_REVIEWS);
					}

					Map<String, Object> mParams = new HashMap<String, Object>();
					mParams.put("hosps", new ArrayList<Map<String, Object>>() {
						{
							add(new HashMap<String, Object>() {
								{
									put("hospId", tenatId);
								}
							});
						}
					});

					Map<Long, Object> mProvinces = instService.getParentByHosp(mParams);
					Map<String, Object> curProvince = (Map<String, Object>) mProvinces.get(tenatId);
					if (!ObjectUtils.isEmpty(curProvince)) {
						Long provinceId = Long.valueOf(curProvince.get("provinceId").toString());
						List<Map> insts = instService.getForTree(provinceId);
						for (Map curInst : insts) {
							hospList.add(Long.parseLong(curInst.get("id").toString()));
						}
					}

					cond.setHospList(hospList);
					break;
				case 4:
					if (ObjectUtils.isEmpty(cond.getNode())) {
						cond.setNodeEnum(NodeEnum.GROUP_REVIEWS);
					}
					break;
				case 5:
					cond.setExpertId(UserContext.getUserId());
					break;
				default:
					break;

			}
		}

		return cond;

	}

	@RequestMapping(value = "/findMergeList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findMergeList(AeInfoCondition cond) {
		if (ObjectUtils.isEmpty(cond.getPatientName()))
			return null;
		return aeCommonService.findListByCond(cond);
	}

	@RequestMapping(value = "/findOpinionList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findOpinionList(AeInfoCondition cond) {
		return this.addExt(aeCommonService.findOpinionList(cond), Arrays.asList(PROVINCE_COLUMN));
	}

	@RequestMapping(value = "/findOpinionForReview", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> findOpinionForReview(AeInfoCondition cond) {
		return aeCommonService.findOpinionForReview(cond);
	}

	@RequestMapping(value = "/findByPatientNo", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> findByPatientNo(@RequestParam("patientNo") String patientNo) {
		Map<String, Object> mPatientInfo = Maps.newHashMap();
		List<Map<String, Object>> patientInfoList = medicalService.getPatientInfoByRegNumber(patientNo);
		if (!ObjectUtils.isEmpty(patientInfoList) && patientInfoList.size() > 0) {
			mPatientInfo = patientInfoList.get(0);
		}
		return mPatientInfo;

	}

	@RequestMapping(value = "/getPatientInfo", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getPatientInfo(@RequestParam("patientId") Long patientId) {
		Map<String, Object> mPatientInfo = Maps.newHashMap();
		mPatientInfo = patientInfoService.getById(patientId);
		return mPatientInfo;
	}

	@RequestMapping(value = "/delById", method = { RequestMethod.POST })
	@ResponseBody
	public Object delById(@RequestParam("id") Long id) {
		return aeCommonService.delById(id);
	}

	@RequestMapping(value = "/payCreate", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> payCreate(@RequestParam("id") Long id) {
		return oaPayService.payCreate(id);
	}

	@RequestMapping(value = "/delOa", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean delOa(@RequestParam("id") Long id) {
		return aeCommonService.delOa(id);
	}


	@RequestMapping(value = "/delAmount", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean delAmount(@RequestParam("id") Long id, @RequestParam("type") String type) {
		return aeCommonService.delAmount(id, type);
	}

	@RequestMapping(value = "/archivedById", method = { RequestMethod.POST })
	@ResponseBody
	public Object archivedById(@RequestParam("id") Long id) {
		return aeCommonService.archivedById(id);
	}

}
