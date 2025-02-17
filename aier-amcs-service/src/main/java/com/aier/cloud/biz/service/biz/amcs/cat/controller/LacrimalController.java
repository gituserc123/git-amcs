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
package com.aier.cloud.biz.service.biz.amcs.cat.controller;

import com.aier.cloud.api.amcs.condition.LacrimalCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
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
import com.aier.cloud.biz.service.biz.amcs.cat.service.LacrimalService;
import com.aier.cloud.biz.service.biz.amcs.cat.entity.Lacrimal;

import java.util.List;
import java.util.Map;

/**
 * Lacrimal
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
@Api("泪道筛查与转诊登记表相关接口")
@Controller
@RequestMapping("/api/amcs/cat/lacrimal")
public class LacrimalController extends BaseController{
 
	@Autowired
	private LacrimalService lacrimalService;
	
	@ApiOperation(value="根据id查询Lacrimal泪道筛查与转诊登记表实体")
	@ApiParam(name="id", value="泪道筛查与转诊登记表的id", required=true)
	@RequestMapping(value = "/getLacrimal")
	public @ResponseBody Lacrimal getLacrimal(@RequestParam("id") Long id) {
		return lacrimalService.selectById(id);
	}


	@ApiOperation(value = "条件查询详情")
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody LacrimalCondition lacrimalCondition){
		Page<Map<String, Object>> page = tranToPage(lacrimalCondition);
		return  returnPageResponse(page,lacrimalService.getAll(page,lacrimalCondition));
	}

	@ApiOperation(value="保存泪道")
	@ApiParam(name="save", value="清单", required=true)
	@RequestMapping(value = "/save")
	@ResponseBody
	public boolean save(@RequestBody Lacrimal lacrimal){
		return lacrimalService.save(lacrimal);
	}

	@ApiOperation(value = "条件查询详情")
	@RequestMapping(value = "/getAllEntityList")
	@ResponseBody
	public List<Lacrimal> getAllEntityList(@RequestBody LacrimalCondition lacrimalCondition){
		return lacrimalService.getAll(lacrimalCondition);
	}

	@ApiOperation(value = "删除泪道记录")
	@RequestMapping(value = "/delLacrimal")
	@ResponseBody
	public Integer delLacrimal(@RequestBody LacrimalCondition lacrimalCondition){
		return lacrimalService.delLacrimal(lacrimalCondition);
	}

}