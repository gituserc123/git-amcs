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

import com.aier.cloud.api.amcs.fin.condition.FinMaDrgDtlCondition;
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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaDrgDtlService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaDrgDtl;

import java.util.List;
import java.util.Map;

/**
 * FinMaDrgDtl
 * 医保管理分析DRG表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:37
 */
@Api("医保管理分析DRG表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finMaDrgDtl")
public class FinMaDrgDtlController extends BaseController{
 
	@Autowired
	private FinMaDrgDtlService finMaDrgDtlService;
	
	@ApiOperation(value="根据id查询FinMaDrgDtl医保管理分析DRG表实体")
	@ApiParam(name="id", value="医保管理分析DRG表的id", required=true)
	@RequestMapping(value = "/getFinMaDrgDtl")
	public @ResponseBody FinMaDrgDtl getFinMaDrgDtl(@RequestParam("id") Long id) {
		return finMaDrgDtlService.selectById(id);
	}

	@ApiOperation(value = "保存")
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, String> save(@RequestBody FinMaDrgDtl finMaDrgDtl){
		return finMaDrgDtlService.save(finMaDrgDtl);
	}

	@ApiOperation(value = "不分页查询")
	@RequestMapping(value = "/getAllRecord")
	@ResponseBody
	public List<FinMaDrgDtl> getAllRecord(@RequestBody FinMaDrgDtlCondition cond){
		return finMaDrgDtlService.getAllRecord(cond);
	}
}