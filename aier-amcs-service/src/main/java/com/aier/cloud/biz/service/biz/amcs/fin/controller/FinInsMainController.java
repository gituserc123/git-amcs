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

import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
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

import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsMainService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;

import java.util.List;
import java.util.Map;

/**
 * FinInsMain
 * 医保政策主表
 * @author 爱尔眼科
 * @since 2023-03-28 15:47:55
 */
@Api("医保政策主表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsMain")
public class FinInsMainController extends BaseController {
 
	@Autowired
	private FinInsMainService finInsMainService;
	
	@ApiOperation(value="根据id查询FinInsMain医保政策主表实体")
	@ApiParam(name="id", value="医保政策主表的id", required=true)
	@RequestMapping(value = "/getFinInsMain")
	public @ResponseBody FinInsMain getFinInsMain(@RequestParam("id") Long id) {
		return finInsMainService.selectById(id);
	}


	@RequestMapping(value = "/getList")
	@ResponseBody
	public List<Map<String, Object>> getList(@RequestBody FinInsMainCondition cond){
		return finInsMainService.getList(cond);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Boolean save(@RequestBody FinInsMain finInsMain){
		return finInsMainService.save(finInsMain);
	}

	@RequestMapping(value = "/queryListByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> queryListByCond(@RequestBody FinInsMainCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("hospId", "t_sys_institution|ahis_hosp|name", "hospName");
		return returnPageResponse(page, finInsMainService.queryListByCond(page, cond));
	}

	@RequestMapping(value = "/queryListByCondNoPage")
	@ResponseBody
	public List<Map<String, Object>> queryListByCondNoPage(@RequestBody FinInsMainCondition cond) {
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("hospId", "t_sys_institution|ahis_hosp|name", "hospName");
		return  finInsMainService.queryListByCond(cond);
	}

	@RequestMapping(value = "/lastList")
	@ResponseBody
	public List<Map<String,Object>> lastList(){
		return finInsMainService.lastList();
	}
}