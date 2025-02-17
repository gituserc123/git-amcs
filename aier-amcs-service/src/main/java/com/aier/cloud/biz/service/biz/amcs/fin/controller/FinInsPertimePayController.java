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

import com.aier.cloud.basic.starter.service.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsPertimePayService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsPertimePay;

import java.util.List;
import java.util.Map;

/**
 * FinInsPertimePay
 * 按人头付费表
 * @author 爱尔眼科
 * @since 2023-04-05 17:47:56
 */
@Api("按人头付费表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsPertimePay")
public class FinInsPertimePayController extends BaseController {
 
	@Autowired
	private FinInsPertimePayService finInsPertimePayService;
	
	@ApiOperation(value="根据id查询FinInsPertimePay按人头付费表实体")
	@ApiParam(name="id", value="按人头付费表的id", required=true)
	@RequestMapping(value = "/getFinInsPertimePay")
	public @ResponseBody FinInsPertimePay getFinInsPertimePay(@RequestParam("id") Long id) {
		return finInsPertimePayService.selectById(id);
	}

	@RequestMapping(value = "/save")
	public @ResponseBody Boolean save(@RequestBody FinInsPertimePay finInsPertimePay) {
		return finInsPertimePayService.save(finInsPertimePay);
	}

	@RequestMapping(value = "/getByMainId")
	public @ResponseBody FinInsPertimePay getByMainId(@RequestParam("mainId") Long mainId){
		return finInsPertimePayService.getByMainId(mainId);
	}

	@RequestMapping(value = "/lastList")
	@ResponseBody
	public List<Map<String,Object>> lastList(){
		return finInsPertimePayService.lastList();
	}
}