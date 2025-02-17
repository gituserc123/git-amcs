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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.amcs.idr.service.AmcsIdrAfpService;

import org.apache.shiro.authz.annotation.RequiresPermissions; 

import java.util.Map; 

/**
 * AmcsIdrAfp
 * AFP病附卡
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@Controller
@RequestMapping("/ui/amcs/idr/amcsIdrAfp")
public class AmcsIdrAfpController extends BaseController{

	@Autowired
	private AmcsIdrAfpService amcsIdrAfpService;

	@RequiresPermissions("Service/biz/amcs/idrAmcsIdrAfp:view")
	@RequestMapping(value = "/getAmcsIdrAfp", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, Object> getAmcsIdrAfp(@RequestParam("id") Long id) {
		return null;// amcsIdrAfpService.selectById(id);
	}
}
