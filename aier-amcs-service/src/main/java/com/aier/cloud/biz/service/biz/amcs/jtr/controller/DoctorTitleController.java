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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorTitle;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorTitleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
/**
 * DoctorTitle
 * 员工职称（执业证）信息
 * @author 爱尔眼科
 * @since 2021-11-12 17:28:03
 */
@Api("员工职称（执业证）信息相关接口")
@Controller
@RequestMapping("/api/amcs/jtr/doctorTitle")
public class DoctorTitleController extends BaseController{
 
	@Autowired
	private DoctorTitleService doctorTitleService;
	
	@RequestMapping(value = "/saveJobDoctorTitle",method = {RequestMethod.POST })
	public @ResponseBody void saveJobDoctorTitle(@RequestBody JobStatisticsDto d){
		doctorTitleService.saveJobDoctorTitle(d);
	}
	@RequestMapping(value ="/getJobDoctorTitle",method = {RequestMethod.POST })
	public @ResponseBody String getJobDoctorTitle(@RequestParam(value = "statisticsDate") String userCode){
		return doctorTitleService.getJobDoctorTitle(userCode);
	}
}