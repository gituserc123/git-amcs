/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.ui.biz.amcs.cat.controller;

import com.aier.cloud.api.amcs.condition.LacrimalCondition;
import com.aier.cloud.api.amcs.domain.Lacrimal;
import com.aier.cloud.basic.api.request.condition.base.PatientInfoCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.ui.biz.amcs.cat.feign.MedicalFeignService;
import com.aier.cloud.biz.ui.biz.amcs.cat.service.LacrimalService;
import com.aier.cloud.center.common.context.UserContext;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Lacrimal
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
@Controller
@RequestMapping("/ui/amcs/cat/lacrimal")
public class LacrimalController extends BaseController{

	private static final String LACRIMAL_LIST_PAGE = "amcs/cat/lacrimalList";
	private static final String LACRIMAL_HIS_LIST_PAGE = "amcs/cat/lacrimalHisList";

	@Autowired
	private LacrimalService lacrimalService;

	@Autowired
	private MedicalFeignService medicalFeignService;

	@RequestMapping(value = "/getLacrimal")
	public @ResponseBody Map<String, Object> getLacrimal(@RequestParam("id") Long id) {
		return lacrimalService.getLacrimal(id);
	}

	@RequestMapping(value = "/getLacrimalPage", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLacrimalPage(){
		return LACRIMAL_LIST_PAGE;
	}

	@RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(LacrimalCondition pageCondition) {
		return lacrimalService.getAll(pageCondition);
	}

	@RequestMapping(value = "/saveLacrimal", method = RequestMethod.POST)
	@ResponseBody
	public Object saveLacrimal(Lacrimal lacrimal) {
		return this.success(lacrimalService.saveLacrimal(lacrimal));
	}

	@RequestMapping(value = "/getHisLacrimalPage", method = {RequestMethod.GET, RequestMethod.POST})
	public String getHisLacrimalPage(){
		return LACRIMAL_HIS_LIST_PAGE;
	}

	@RequestMapping(value = "/getHisAll", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResponse<Map<String, Object>> getHisAll(LacrimalCondition pageCondition) {
		if(null == pageCondition.getPatientName() || pageCondition.getPatientName().equals("")){
			return null;
		}
		PatientInfoCondition cond = new PatientInfoCondition();
		cond.setHospId(UserContext.getUser().getTenantId());
		cond.setSearchValue(pageCondition.getPatientName().trim());
		cond.setPage(1);
		cond.setRows(10);
		cond.setIsOutp(true);
		cond.setIsOutp002(true);
		cond.setOutp002Days(0);
		List<Map<String,Object>> list = Lists.newArrayList();
		try{
			PageResponse<Map<String, Object>> pageResponseMap = medicalFeignService.pageBlurFindByParam(cond);
			if(Optional.ofNullable(pageResponseMap.getRows()).isPresent() && pageResponseMap.getRows().size()>0){
				List<Long> patientIds = pageResponseMap.getRows().stream().map(ml -> (Long)ml.get("id")).collect(Collectors.toList());
				LacrimalCondition lacrimalCondition = new LacrimalCondition();
				lacrimalCondition.setPatientIds(patientIds);
				List<Lacrimal> lacrimals = lacrimalService.getAllEntityList(lacrimalCondition);
				pageResponseMap.getRows().stream().forEach(item -> {
					Map<String,Object> map = Maps.newHashMap();
					map.put("patientId",item.get("id"));
					map.put("patientName",item.get("name"));
					map.put("checkDate",item.get("createDate"));
					Optional<Lacrimal> optionalLacrimal = lacrimals.stream().filter(lm -> lm.getPatientId().compareTo((Long)item.get("id"))==0).findFirst();
					if(optionalLacrimal.isPresent()){
						map.put("id",optionalLacrimal.get().getId());
					}
					list.add(map);
				});
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		PageResponse<Map<String, Object>> pageResponse = new PageResponse<Map<String, Object>>();
		pageResponse.setRows(list);
		pageResponse.setTotal(list.size());
		return pageResponse;
	}

	@RequestMapping("/batchSave")
	@ResponseBody
	public Object batchSave(@RequestBody ArrayList<Lacrimal> listLacrimal){
		listLacrimal.stream().forEach(la -> {
			if(la.getId()==null){
				lacrimalService.saveLacrimal(la);
			}
		});
		return this.success("sucess");
	}

	@RequestMapping(value = "/queryHisLacrimalInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object queryHisLacrimalInfo(LacrimalCondition pageCondition){
		List<Map<String,Object>> list = medicalFeignService.getOutpRegistTrans(UserContext.getUser().getTenantId(),pageCondition.getPatientId());
		if(Optional.ofNullable(list).isPresent() && list.size()>0){
			Map<String,Object> lc = list.get(0);
			Lacrimal lacrimal = new Lacrimal();
			lacrimal.setId(pageCondition.getId());
			if(null != lc.get("doctorName") && !lc.get("doctorName").equals("")){
				lacrimal.setGlaucomaDoctorName(String.valueOf(lc.get("doctorName")));
			}
			if(null != lc.get("diagNames") && !lc.get("diagNames").equals("")){
				lacrimal.setGlaucomaResult(String.valueOf(lc.get("diagNames")));
			}
			if(lacrimal.getGlaucomaDoctorName()!=null || lacrimal.getGlaucomaResult()!=null){
				lacrimalService.saveLacrimal(lacrimal);
			}
		}
		return this.success();
	}

	@RequestMapping(value = "/delLacrimal", method = RequestMethod.POST)
	@ResponseBody
	public Object delLacrimal(LacrimalCondition lacrimalCondition){
		return lacrimalService.delLacrimal(lacrimalCondition);
	}
}
