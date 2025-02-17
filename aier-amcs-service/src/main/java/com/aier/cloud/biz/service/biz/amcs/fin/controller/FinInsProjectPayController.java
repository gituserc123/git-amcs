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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsProjectPayService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsProjectPay;

import java.util.List;
import java.util.Map;

/**
 * FinInsProjectPay
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */
@Api("项目付费表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsProjectPay")
public class FinInsProjectPayController extends BaseController{
 
	@Autowired
	private FinInsProjectPayService finInsProjectPayService;
	
	@ApiOperation(value="根据id查询FinInsProjectPay项目付费表实体")
	@ApiParam(name="id", value="项目付费表的id", required=true)
	@RequestMapping(value = "/getFinInsProjectPay")
	public @ResponseBody FinInsProjectPay getFinInsProjectPay(@RequestParam("id") Long id) {
		return finInsProjectPayService.selectById(id);
	}

	@RequestMapping(value = "/save")
	public @ResponseBody Boolean save(@RequestBody FinInsProjectPay finInsProjectPay) {
		return finInsProjectPayService.save(finInsProjectPay);
	}

	@RequestMapping(value = "/getByMainId")
	public @ResponseBody FinInsProjectPay getByMainId(@RequestParam("mainId") Long mainId){
		return finInsProjectPayService.getByMainId(mainId);
	}

	@RequestMapping(value = "/lastList")
	@ResponseBody
	public List<Map<String,Object>> lastList(){
		return finInsProjectPayService.lastList();
	}
}