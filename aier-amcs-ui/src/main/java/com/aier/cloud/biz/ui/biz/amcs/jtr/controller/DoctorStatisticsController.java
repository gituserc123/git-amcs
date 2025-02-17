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
package com.aier.cloud.biz.ui.biz.amcs.jtr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.condition.JobParam;
import com.aier.cloud.api.amcs.domain.DoctorStatistics;
import com.aier.cloud.biz.ui.biz.amcs.jtr.feign.JobService;
import com.aier.cloud.biz.ui.biz.amcs.jtr.feign.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.amcs.jtr.feign.DoctorStatisticsService;
import com.aier.cloud.center.common.context.UserContext;


/**
 * DoctorStatistics
 * 职改医生统计表
 * @author 爱尔眼科
 * @since 2021-09-26 14:29:33
 */
@Controller
@RequestMapping("/ui/amcs/jtr/doctorStatistics")
public class DoctorStatisticsController extends BaseController{
	private static final  String CALENDAR="amcs/jtr/calendar" ;
	private static final  String FILL_IN="amcs/jtr/doctorstatistic" ;
	private static final  String HISTORY="amcs/jtr/historyHandle" ;

	@Autowired
	private DoctorStatisticsService doctorStatisticsService;
@Autowired
private JobService jobService;

@Autowired
private SystemService systemService;
	@RequestMapping(value = "/calendar",method = { RequestMethod.GET, RequestMethod.POST})
	public String calendar(Model model){
		model.addAttribute("userCode", UserContext.getUserCode());
		model.addAttribute("userName", UserContext.getUserName());
		model.addAttribute("currentTitle",doctorStatisticsService.getJobDoctorTitle(UserContext.getUserCode()));
		return CALENDAR;
	};
/**
 *@description:  填报入口
 * @param hospId,statisticDate
 */
	@RequestMapping(value = "/fillIn",method = { RequestMethod.GET, RequestMethod.POST})
	public String fillIn(Model model,@RequestParam("hospId") Long hospId,@RequestParam("statisticsDate") String statisticsDate,@RequestParam("hospName") String hospName){
		model.addAttribute("hospId", hospId);
		model.addAttribute("hospName", hospName);
		model.addAttribute("statisticsDate", statisticsDate);
		model.addAttribute("userCode", UserContext.getUserCode());

		return FILL_IN;
	};
	@RequestMapping(value = "/historyHandle",method = { RequestMethod.GET, RequestMethod.POST})
	public String historyHandle(Model model){

		model.addAttribute("userCode", UserContext.getUserCode());

		return HISTORY;
	};



	@RequiresPermissions("AmcsDoctorStatistics:view")
	@RequestMapping(value = "/getDoctorStatistics")
	public @ResponseBody
	Map getDoctorStatistics(@RequestParam("id") Long id) {
		return doctorStatisticsService.selectById(id);
	}




	@RequestMapping(value = "/saveDoctorStatistics",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object saveDoctorStatistics(DoctorStatistics doctorstatistics){

		if (doctorStatisticsService.saveDoctorStatistics(doctorstatistics))
		{
			return this.success("成功提交！");
		}else {
			return this.fail("提交失败！");
		}
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

	@RequestMapping(value = "/startJob",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object startJob(@RequestParam(value = "beginDate") String beginDate,@RequestParam(value = "endDate") String endDate,@RequestParam(value="hospId") Long hospId){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		SimpleDateFormat sdf1 =   new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		JobParam param = new JobParam();
		param.setHospId(hospId);
		try {
			param.setBeginDate(sdf.parse(beginDate));
			param.setEndDate(sdf1.parse(endDate+" 23:59:59"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		param.addExt("staffId", UserContext.getUserId());


		try {
			jobService.runSingleJob("com.aier.cloud.biz.ahis.external.d000000.job.Job_AMCS",param);
			return this.success();
		} catch (Exception e) {
			e.printStackTrace();
			return this.fail();
		}



	}

	@RequestMapping(value = "/getByHospId")
	public @ResponseBody Map<String, Object> getByHospId(@RequestParam("hospId") Long hospId) {
		Map<String,Object> map= systemService.getByHospId(hospId);
		return map;
	}
}
