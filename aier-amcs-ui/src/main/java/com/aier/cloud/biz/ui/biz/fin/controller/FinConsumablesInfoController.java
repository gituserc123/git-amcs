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
package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinInsConsumablesInfoCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsConsumablesInfo;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.ui.biz.fin.feign.FinConsumablesInfoService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import java.util.Map;


/**
 * FinInsConsumablesInfo
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsConsumablesInfo")
public class FinConsumablesInfoController extends BaseController{

	@Autowired
	private FinConsumablesInfoService finConsumablesInfoService;

	@RequestMapping(value = "/getFinInsConsumablesInfo")
	public @ResponseBody Map<String, Object> getFinInsConsumablesInfo(@RequestParam("id") Long id) {
		return finConsumablesInfoService.getFinInsConsumablesInfo(id);
	}

	@RequestMapping(value = "/getAllCons",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> getAllCons(FinInsConsumablesInfoCondition finInsConsumablesInfoCondition){
		return finConsumablesInfoService.getAll(finInsConsumablesInfoCondition);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(FinInsConsumablesInfo finInsConsumablesInfo) {
		return finConsumablesInfoService.save(finInsConsumablesInfo);
	}

	@RequestMapping(value = "/lastList",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	List<Map<String,Object>> lastList(@RequestParam("consumablesType") Integer consumablesType) {
		List<Map<String,Object>> l= finConsumablesInfoService.lastList(consumablesType);
		return l;
	}
}
