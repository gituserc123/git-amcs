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

import com.aier.cloud.api.amcs.condition.DrugReactDrugsCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDrugReactDrugsService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDrugReactDrugs;

import java.util.List;
import java.util.Map;

/**
 * AeDrugReactDrugs
 * 药品不良反应事件药物信息表
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@Api("药品不良反应事件药物信息表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeDrugReactDrugs")
public class AeDrugReactDrugsController extends BaseController{
 
	@Autowired
	private AeDrugReactDrugsService aeDrugReactDrugsService;
	
	@ApiOperation(value="根据id查询AeDrugReactDrugs药品不良反应事件药物信息表实体")
	@ApiParam(name="id", value="药品不良反应事件药物信息表的id", required=true)
	@RequestMapping(value = "/getAeDrugReactDrugs")
	public @ResponseBody AeDrugReactDrugs getAeDrugReactDrugs(@RequestParam("id") Long id) {
		return aeDrugReactDrugsService.selectById(id);
	}

	@RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public boolean save(@RequestBody AeDrugReactDrugs aeDrugReactDrugs){
		return aeDrugReactDrugsService.save(aeDrugReactDrugs);
	}

	@RequestMapping(value = "/getAllEntity",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Map<String,Object>> getAllEntity(@RequestBody DrugReactDrugsCondition cond){
		return aeDrugReactDrugsService.getAllEntity(cond);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Integer delete(@RequestParam("id") Long id){
		return aeDrugReactDrugsService.delete(id);
	}
}