package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeCommonService;
import com.aier.cloud.biz.ui.biz.adverse.feign.StaffService;

@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/dept")
public class AeDepQueryController extends BaseController {
	
	private final static String INDEX = "amcs/adverseEvent/eventManage/eventDepQuery";

	private final static String EVENT_DEPT_REVIEW = "amcs/adverseEvent/eventManage/eventDepReview";
	
	@Autowired
    private AeCommonService aeCommonService;
	
	@Autowired
    private StaffService staffService;

	@Autowired
	private InstitutionService institutionService;
	
	@RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
		return INDEX;
    }
	
	@RequestMapping(value = "/findList", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> findList(AeInfoCondition cond) {
		cond.setStaffList(getDeptStaffs());
		return addExt(aeCommonService.findListByCond(cond));
    }

	@RequestMapping(value = "/deptReview")
	public String deptReview(HttpServletRequest request) {
		return EVENT_DEPT_REVIEW;
	}

	@RequestMapping(value = "/findListReview", method = {RequestMethod.POST})
	@ResponseBody
	public PageResponse<Map<String, Object>> findListReview(AeInfoCondition cond) {
		cond.setStaffList(getDeptStaffs());
		try{
			SysCommonCondition sysCommonCondition = new SysCommonCondition();
			sysCommonCondition.setCode(ShiroUtils.getLoginCode());
			List<Map<String, Object>> ehrInfoLists = staffService.getJobPos(sysCommonCondition);
			if(Objects.nonNull(ehrInfoLists) && ehrInfoLists.size()>0){
				String ehrAiStandDeptIds = ehrInfoLists.stream().filter(ei -> Objects.nonNull(ei.get("INST_ID")) && MapUtils.getString(ei,"INST_ID").equals(String.valueOf(ShiroUtils.getInstId())))
						.map(ei -> MapUtils.getString(ei,"DEPTID_ONE")).collect(Collectors.joining(","));
				if(Objects.nonNull(ehrAiStandDeptIds) && ehrAiStandDeptIds.length()>0){
					cond.setEhrAiStandDeptIds(ehrAiStandDeptIds);
				}else{
					cond.setEhrAiStandDeptId("0");
				}
			}
		}catch (Exception e){
			logger.info("获取当前登录人员EHR科室信息失败!{}", e.getMessage());
		}
		return addExt(aeCommonService.findListByCond(cond));
	}
	
	/**
	 * 获取当前用户所在部门的员工
	 * @return
	 */
	private List<Object> getDeptStaffs() {
		List<Object> staffIdList = Lists.newArrayList();
		
		Long instId = ShiroUtils.getInstId();
		Long deptId = ShiroUtils.getDeptId();
		StaffCondition sc = new StaffCondition();
		sc.setInstId(instId);
		sc.setDeptId(deptId);
		List<Map<String, Object>> staffs = staffService.getHospStaff(sc);
		
		for(Map<String, Object> staff: staffs) {
			staffIdList.add(staff.get("id"));
		}
		return staffIdList;
		
	}
	
	private PageResponse<Map<String, Object>> addExt(PageResponse<Map<String, Object>> res){
		Map<String, Map> existHosp = new HashMap<>();
    	List<Map<String, Object>> dataList = res.getRows();
    
    	dataList.stream().forEach(obj -> {
    		Long id = Long.parseLong(obj.get("id").toString());
			Map<String, Object> lastInfo = aeCommonService.getLast(id);
			obj.put("maxReportTimes", lastInfo.get("reportTimes"));
			// 医院类型和省区
			if (Objects.nonNull(obj.get("hospId"))) {
				System.out.println(obj.get("hospId"));
				if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
					obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
					obj.put("investNature", existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
					if(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL") != null){
						obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL"))));
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
						if(resultMap.get("EHR_LEVEL")!=null){
							obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
						}
						resultMap.put("PARENT_NAME", l.get(0).get("name"));
						existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
					}
				}
			}
    	});
		return res;
	}

}
