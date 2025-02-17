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

import com.aier.cloud.api.amcs.fin.domain.FinInsPertimePay;
import com.aier.cloud.biz.ui.biz.fin.feign.FinPertimePayService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * FinInsPertimePay
 * 按人头付费表
 * @author 爱尔眼科
 * @since 2023-04-05 17:47:56
 */
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsPertimePay")
public class FinPertimePayController extends BaseController{

	@Autowired
	private FinPertimePayService finPertimePayService;

	//@RequiresPermissions("Service/biz/amcs/finFinInsPertimePay:view")
	@RequestMapping(value = "/getFinInsPertimePay")
	public @ResponseBody Map<String, Object> getFinInsPertimePay(@RequestParam("id") Long id) {
		return finPertimePayService.getFinInsPertimePay(id);
	}

	@RequestMapping(value ="/saveFinInsPertimePay",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody FinInsPertimePay finInsPertimePay){
		return this.success(finPertimePayService.save(finInsPertimePay));
	}

	@RequestMapping(value ="/getByMainId",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public FinInsPertimePay getByMainId(Long mainId){
		FinInsPertimePay finInsPertimePay = finPertimePayService.getByMainId(mainId);
		if(finInsPertimePay!=null){
			return finInsPertimePay;
		}else {
			return new FinInsPertimePay();
		}
	}

	@RequestMapping(value = "/lastList",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	List<Map<String,Object>> lastList() {
		List<Map<String,Object>> l= finPertimePayService.lastList();
		l.forEach(m ->{
			m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString()));
		});
		return l;
	}
}
