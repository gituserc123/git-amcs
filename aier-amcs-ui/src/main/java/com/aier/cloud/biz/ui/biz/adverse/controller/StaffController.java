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
 package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.sys.feign.StaffService;


/**
 * Staff
 * 用户管理
 * @author 爱尔眼科
 * @since 2018-02-06 14:45:09
 */
@Controller
@RequestMapping("/ui/sys/staff")
public class StaffController extends BaseController{
 
	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "/getStaffByCondition")
	@ResponseBody
	public Object getInstByParent(StaffCondition sc) {
		return staffService.getStaffByCondition(sc);
	}
	
	@RequestMapping(value = "/getHospStaff")
    public @ResponseBody Object getHospStaff(@RequestBody StaffCondition sc) {
		if(ObjectUtils.isEmpty(sc.getInstId())) {
			sc.setInstId(ShiroUtils.getInstId());
		}
    	List o =  staffService.getHospStaff(sc);
		return this.easyuiResult(o); 
    }

    
}