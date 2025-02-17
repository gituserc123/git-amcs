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
package com.aier.cloud.biz.ui.biz.amcs.idr.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.idr.AmcsIdrBaseInfo;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.amcs.idr.service.AmcsIdrBaseInfoService;

/**
 * AmcsIdrHfmd 手足口病附卡
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@Controller
@RequestMapping("/ui/amcs/idr/amcsIDRsearch")
public class AmcsIDRsearchController extends BaseController {

	@Autowired
	private AmcsIdrBaseInfoService amcsIdrBaseInfoService;

	/**
	 * 病例报告记录
	 * 
	 * @return
	 */
	@RequiresPermissions("AmcsIDRsearch:view")
	@RequestMapping(value = "/getAmcsIDRsearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAmcsIDRsearch() {
		return "amcs/idr/AmcsIDRsearch";
	}

	/**
	 * 根据条件查询申请主表列表
	 * 
	 * @param apply
	 * @return
	 */
	@RequiresPermissions("AmcsIDRsearch:view")
	@RequestMapping(value = "/getMainByCondForList")
	@ResponseBody
	public Map<String, Object> getMainByCondForList(AmcsIdrBaseInfo apply) {
		List tmpList = amcsIdrBaseInfoService.getMainByCondForList(apply);
		return this.list(tmpList);
	}

}
