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

import com.aier.cloud.api.amcs.fin.condition.FinInsConsumablesInfoCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsDipAnalysisCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsDipAnalysis;
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

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsConsumablesInfoService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsConsumablesInfo;

import java.util.List;
import java.util.Map;

/**
 * FinInsConsumablesInfo
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */
@Api("耗材附属信息表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsConsumablesInfo")
public class FinInsConsumablesInfoController extends BaseController{
 
	@Autowired
	private FinInsConsumablesInfoService finInsConsumablesInfoService;
	
	@ApiOperation(value="根据id查询FinInsConsumablesInfo耗材附属信息表实体")
	@ApiParam(name="id", value="耗材附属信息表的id", required=true)
	@RequestMapping(value = "/getFinInsConsumablesInfo")
	public @ResponseBody FinInsConsumablesInfo getFinInsConsumablesInfo(@RequestParam("id") Long id) {
		return finInsConsumablesInfoService.selectById(id);
	}

	@RequestMapping(value = "/save")
	public @ResponseBody Boolean save(@RequestBody FinInsConsumablesInfo finInsConsumablesInfo) {
		return finInsConsumablesInfoService.save(finInsConsumablesInfo);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody FinInsConsumablesInfoCondition finInsConsumablesInfoCondition) {
		add("creator", "T_SYS_STAFF|ID|NAME", "creatorName");
		add("modifer", "T_SYS_STAFF|ID|NAME", "modiferName");
		Page<Map<String, Object>> page = tranToPage(finInsConsumablesInfoCondition);
		return returnPageResponse(page,finInsConsumablesInfoService.getAll(page,finInsConsumablesInfoCondition));
	}

	@RequestMapping(value = "/lastList")
	@ResponseBody
	public List<Map<String,Object>> lastList(@RequestParam("consumablesType") Integer consumablesType){
		return finInsConsumablesInfoService.lastList(consumablesType);
	}
}