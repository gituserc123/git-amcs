/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http:/www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.ui.biz.amcs.idr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.idr.AmcsIdrBaseInfo;
import com.aier.cloud.api.amcs.domain.idr.AmcsIdrDictType;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.aier.cloud.biz.ui.biz.amcs.idr.service.AmcsIdrDictTypeService;

/**
 * AmcsIdrDictType 传染病字典表
 * 
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */
@Controller
@RequestMapping("/ui/amcs/idr/amcsIdrDictType")
public class AmcsIdrDictTypeController extends BaseController {

	@Autowired
	private AmcsIdrDictTypeService amcsIdrDictTypeService;

	//@RequiresPermissions("AmcsIdrDict:view")
	@RequestMapping(value = "/getTypeCodeForList")
	public @ResponseBody List<AmcsIdrDictType> getTypeCodeForList(AmcsIdrDictType apply) {
		return amcsIdrDictTypeService.getTypeCodeForList(apply);
	}

	@RequestMapping(value = "/getAmcsIdrDictType")
	public @ResponseBody List<AmcsIdrDictType> getAmcsIdrDictType(AmcsIdrDictType apply) {
		return amcsIdrDictTypeService.getAmcsIdrDictType(apply);
	}

	@RequiresPermissions("AmcsIdrDict:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody AmcsIdrDictType apply) {
		Long id = apply.getId();
		return this.success(amcsIdrDictTypeService.save(apply, ShiroUtils.getId(), ShiroUtils.getLoginName()));
	}

	@RequestMapping(value = "/getPatient")
	@ResponseBody
	public Object getPatient(@NotNull String patientName) {
		return amcsIdrDictTypeService.getPatient(patientName);
	}

	@RequiresPermissions("AmcsIdrDict:view")
	@RequestMapping(value = "/getAmcsIdrDict", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAmcsIdrDict() {
		return "amcs/idr/AmcsIdrDict";
	}

	/**
	 * 获取码表
	 * 
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/getEnum")
	public @ResponseBody List<Map> getEnum(AmcsIdrDictType c) {
		return amcsIdrDictTypeService.getEnum(c);
	}
}
