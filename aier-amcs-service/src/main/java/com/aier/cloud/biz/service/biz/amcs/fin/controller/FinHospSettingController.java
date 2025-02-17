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
package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;

import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import  com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospSettingService;
import  com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospSetting;

import java.util.List;
import java.util.Map;

/**
 * FinHospSetting
 * 财务医院设置
 * @author 爱尔眼科
 * @since 2023-03-22 14:23:52
 */
@Api("财务医院设置相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finHospSetting")
public class FinHospSettingController extends BaseController {
 
	@Autowired
	private FinHospSettingService finHospSettingService;
	
	@ApiOperation(value="根据id查询FinHospSetting财务医院设置实体")
	@ApiParam(name="id", value="财务医院设置的id", required=true)
	@RequestMapping(value = "/getFinHospSetting")
	public @ResponseBody FinHospSetting getFinHospSetting(@RequestParam("id") Long id) {
		return finHospSettingService.selectById(id);
	}

	@RequestMapping(value = "/getList")
	public @ResponseBody
	List<Map<String,Object>> getList(@RequestBody FinHospSettingCondition cond) {
		add("modifer", "t_sys_staff|id|name", "modiferName");
		return finHospSettingService.getList(cond);
	}

	@RequestMapping("save")
	@ResponseBody
	public Boolean save(@RequestBody FinHospSetting finHospSetting){
		return finHospSettingService.save(finHospSetting);
	}
}