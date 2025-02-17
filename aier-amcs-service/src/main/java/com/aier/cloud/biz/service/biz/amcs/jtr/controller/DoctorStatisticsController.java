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
package com.aier.cloud.biz.service.biz.amcs.jtr.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorStatistics;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorStatisticsService;


/**
 * 职改统计内部服务
 *
 * @author xiaokek
 * @since 2021年10月27日 下午4:09:12
 */
@Controller
@RequestMapping("/api/amcs/jtr/doctorStatistics")
public class DoctorStatisticsController extends BaseController{

	@Autowired
	private DoctorStatisticsService doctorStatisticsService;


	@RequestMapping(value = "/saveJobStatistics",method = {RequestMethod.POST })
	public @ResponseBody void saveJobStatistics(@RequestBody JobStatisticsDto d) throws ParseException{
		doctorStatisticsService.saveJobStatistics(d);
	}
	@RequestMapping(value = "/getDoctorStatistics")
	public @ResponseBody
	DoctorStatistics getDoctorStatistics(@RequestParam("id") Long id) {
		return doctorStatisticsService.selectById(id);
	}


	@RequestMapping(value = "/saveDoctorStatistics",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Boolean saveDoctorStatistics(@RequestBody DoctorStatistics doctorstatistics) {

		return doctorStatisticsService.saveDoctorStatistics(doctorstatistics);
	}
	@RequestMapping(value = "/last60Days",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	//@RequiresPermissions("AmcsDoctorStatistics:view")
	public List<Map<String,Object>> last60Days(@RequestParam(value = "userCode") String userCode, @RequestParam(value = "hospId") Long hospId){
		return doctorStatisticsService.last60Days(userCode,hospId);
	}
	@RequestMapping(value = "/getDoctorStatistics",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<?> getDoctorStatistics(@RequestParam(value = "statisticsDate") String statisticsDate, @RequestParam(value = "userCode") String userCode, @RequestParam(value = "hospId") Long hospId){
		return doctorStatisticsService.getDoctorStatistics(statisticsDate,userCode,hospId);
	}

	@RequestMapping(value = "/getByDateRange",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> getByDateRange(@RequestParam(value = "startDate") String startDate,@RequestParam(value = "endDate") String endDate,@RequestParam(value = "userCode") String userCode){
		return doctorStatisticsService.getByDateRange(startDate,endDate,userCode);
	}
}
