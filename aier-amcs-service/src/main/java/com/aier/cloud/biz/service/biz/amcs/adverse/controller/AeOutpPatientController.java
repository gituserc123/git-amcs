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
package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOutpPatient;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOutpPatientService;

import io.swagger.annotations.Api;
 
/**
 * AeOutpPatient
 * 门诊患者不良事件(手术患者)
 * @author 爱尔眼科
 * @since 2022-12-06 16:25:50
 */
@Api("门诊患者不良事件(手术患者)相关接口")
@Controller
@RequestMapping("/api/amcs/adverse/aeOutpPatient")
public class AeOutpPatientController extends BaseController{
 
	@Autowired
	private AeOutpPatientService service;
	
	@RequestMapping(value = "/selectById")
	public @ResponseBody AeOutpPatient selectById(@RequestParam("id") Long id) {
		return service.selectById(id);
	}
}