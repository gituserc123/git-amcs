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

import com.aier.cloud.api.amcs.condition.GlaucomaCondition;
import com.aier.cloud.api.amcs.domain.Glaucoma;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.amcs.cat.feign.MedicalFeignService;
import com.aier.cloud.biz.ui.biz.amcs.cat.service.GlaucomaService;
import com.aier.cloud.center.common.context.UserContext;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Glaucoma
 * 青光眼筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:37
 */
@Controller
@RequestMapping("/ui/amcs/cat/glaucoma")
public class GlaucomaController extends BaseController{

	private static final String GLAUCOMA_LIST_PAGE = "amcs/cat/glaucomaList";
	private static final String GLAUCOMA_HIS_LIST_PAGE = "amcs/cat/glaucomaHisList";

	@Autowired
	private GlaucomaService glaucomaService;

	@Autowired
	private MedicalFeignService medicalFeignService;

	@RequestMapping(value = "/getGlaucoma")
	public @ResponseBody Map<String, Object> getGlaucoma(@RequestParam("id") Long id) {
		return glaucomaService.getGlaucoma(id);
	}

	@RequestMapping(value = "/getGlaucomaPage", method = {RequestMethod.GET, RequestMethod.POST})
	public String getGlaucomaPage(){

		return GLAUCOMA_LIST_PAGE;
	}

	@RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(GlaucomaCondition pageCondition) {
		return glaucomaService.getAll(pageCondition);
	}

	@RequestMapping(value = "/saveGlaucoma", method = RequestMethod.POST)
	@ResponseBody
	public Object saveGlaucoma(Glaucoma glaucoma) {
		return this.success(glaucomaService.saveGlaucoma(glaucoma));
	}

	@RequestMapping(value = "/getHisGlaucomaPage", method = {RequestMethod.GET, RequestMethod.POST})
	public String getHisGlaucomaPage(){
		return GLAUCOMA_HIS_LIST_PAGE;
	}

	@RequestMapping(value = "/getHisAll", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public PageResponse<Map<String, Object>> getHisAll(GlaucomaCondition pageCondition) {
		Long hospId = UserContext.getUser().getTenantId();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		// 当天时间:结束时间
		c.setTime(new Date());
		Date d = c.getTime();
		String day = format.format(d);
		//System.out.println("当天："+day);
		// 过去一月:开始时间
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		//System.out.println("过去一月："+mon);
		PageResponse<Map<String, Object>> pageResponse = new PageResponse<Map<String, Object>>();
		List<Map<String,Object>> list = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		param.put("hospId",String.valueOf(hospId));
		param.put("startDate",mon);
		param.put("endDate",day);
		try{
			List<Map<String,Object>> medicalLists = medicalFeignService.getExamIopByRegDate(param);
			if(medicalLists!=null && medicalLists.size() > 0) {
				List<Long> patientIds = medicalLists.stream().map(ml -> (Long) ml.get("patientId")).collect(Collectors.toList());
				GlaucomaCondition glaucomaCondition = new GlaucomaCondition();
				glaucomaCondition.setPatientIds(patientIds);
				List<Glaucoma> glaucomaList = glaucomaService.getAllEntityList(glaucomaCondition);
				medicalLists.stream().forEach(ml -> {
					Map<String, Object> convertMap = Maps.newHashMap();
					convertMap.put("outpatientDate", ml.get("regDate"));
					convertMap.put("patientId", ml.get("patientId"));
					convertMap.put("patientName", ml.get("patientName"));
					convertMap.put("odPressure", ml.get("iopOd"));
					convertMap.put("osPressure", ml.get("iopOs"));
					convertMap.put("fstOutDeptName", ml.get("deptName"));
					convertMap.put("fstOutDoctorName", ml.get("doctorName"));
					convertMap.put("fstOutDoctorId", ml.get("doctorId"));
					convertMap.put("inDoctorName", ml.get("doctorNameIn"));
					convertMap.put("inMedicalResult", ml.get("diagnames"));
					Optional<Glaucoma> ga = glaucomaList.stream().filter(item -> item.getPatientId().compareTo(((Long) ml.get("patientId"))) == 0).findFirst();
					if (ga.isPresent()) {
						convertMap.put("id", ga.get().getId());
					}
					list.add(convertMap);
				});
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		pageResponse.setRows(list);
		pageResponse.setTotal(list.size());

		return pageResponse;
	}

	@RequestMapping("/batchSave")
	@ResponseBody
	public Object batchSave(@RequestBody ArrayList<Glaucoma> listGlaucoma){
		listGlaucoma.stream().forEach(glaucoma -> glaucomaService.saveGlaucoma(glaucoma));
		return this.success("sucess");
	}

	@RequestMapping(value = "/delGlaucoma", method = RequestMethod.POST)
	@ResponseBody
	public Object delGlaucoma(GlaucomaCondition glaucomaCondition){
		return glaucomaService.delGlaucoma(glaucomaCondition);
	}

}
