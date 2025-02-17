/*
c * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
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
 package com.aier.cloud.ui.biz.sys.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.feign.MyWorkSpaceService;
import com.aier.cloud.basic.api.response.domain.sys.SysMyworkspace;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.center.common.context.UserContext;

/**
 * 我的工作台
 * @author rain_deng
 */
@Controller
@RequestMapping("/ui/sys/myworkspace")
public class MyWorkSpaceController extends BaseController{
 
	@Autowired
	private MyWorkSpaceService s;

	@RequestMapping(value = "/create")
	@ResponseBody
	public Object create(@RequestParam Map favor) {
		favor.put("staffId", UserContext.getUserId());
		s.create(favor);
		return this.success();
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Map favor) {
		favor.put("staffId", UserContext.getUserId());
		s.delete(favor);
		return this.success();
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list() {
		List<SysMyworkspace> myworkspaces = s.getSysMyworkspace(ShiroUtils.getId());
		return this.success(myworkspaces.stream().map(SysMyworkspace::getModuleCategorize).distinct().collect(Collectors.toList()));
	}
}