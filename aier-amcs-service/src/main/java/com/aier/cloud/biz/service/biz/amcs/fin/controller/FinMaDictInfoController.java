/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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

import com.aier.cloud.api.amcs.fin.condition.FinMaDictInfoCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaDictInfoService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaDictInfo;

import java.util.List;
import java.util.Map;

/**
 * FinMaDictInfo
 * 医保管理分析字典表
 * @author 爱尔眼科
 * @since 2024-02-22 14:32:33
 */
@Api("医保管理分析字典表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finMaDictInfo")
public class FinMaDictInfoController extends BaseController{
 
	@Autowired
	private FinMaDictInfoService finMaDictInfoService;
	
	@ApiOperation(value="根据id查询FinMaDictInfo医保管理分析字典表实体")
	@ApiParam(name="id", value="医保管理分析字典表的id", required=true)
	@RequestMapping(value = "/getFinMaDictInfo")
	public @ResponseBody FinMaDictInfo getFinMaDictInfo(@RequestParam("id") Long id) {
		return finMaDictInfoService.selectById(id);
	}

	@RequestMapping(value ="/getMaDictList")
	@ResponseBody
	List<FinMaDictInfo> getMaDictList(@RequestBody FinMaDictInfoCondition finMaDictInfoCondition){
		return finMaDictInfoService.getMaDictList(finMaDictInfoCondition);
	}

	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> save(@RequestBody FinMaDictInfo finMaDictInfo){
		return finMaDictInfoService.save(finMaDictInfo);
	}
}