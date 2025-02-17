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

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;

import com.aier.cloud.api.amcs.fin.domain.FinHospSetting;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinHospSettingService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * FinHospSetting
 * 财务医院设置
 * @author 爱尔眼科
 * @since 2023-03-22 14:23:52
 */
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finHospSetting")
public class FinHospSettingController extends BaseController{

	@Autowired
	private FinHospSettingService finHospSettingService;

	@Autowired
	private FinDictFeignService finDictFeignService;

	@Autowired
	private AdverseEventService adverseEventService;

	@Autowired
	private HospHandleService hospHandleService;

	private static final String HOSP_SETTING="amcs/fin/config/FinHospSettingManage";

	@RequiresPermissions("Service/biz/amcs/finFinHospSetting:view")
	@RequestMapping(value = "/getFinHospSetting")
	public @ResponseBody Map<String, Object> getFinHospSetting(@RequestParam("id") Long id) {
		return finHospSettingService.getFinHospSetting(id);
	}

	@RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST})
	public String index(Model model){
		model.addAttribute("hospId", UserContext.getTenantId());
		return HOSP_SETTING;
	};


	@RequestMapping(value = "/getList",method = { RequestMethod.POST})
	@ResponseBody
	public List<Map<String,Object>> getList(FinHospSettingCondition cond){
		List<Map<String,Object>> list =finHospSettingService.getFinSettingList(cond);
		list.forEach(row->{
			row.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",row.get("insuranceType").toString()));
		});
		return list;
	}

	@RequestMapping(value = "/save",method = { RequestMethod.POST})
	@ResponseBody
	public Object save( FinHospSetting finHospSetting){
		if (finHospSettingService.save(finHospSetting)){
			return this.success("提交成功");
		}else{
			return this.fail("提交失败");
		}
	}

	/**
	 * 该方法用于DIP,DRG分析表填报的时候的下拉菜单
	 * @return
	 */
	@RequestMapping(value = "/getSelectList",method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Map<String,Object>> getSelectList(){
		FinHospSettingCondition cond = new FinHospSettingCondition();
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
			// 医院登录
			cond.setHospId(shiroUser.getTenantId());
			cond.setHospList(null);
		} else {
			if (!Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
				// 省区或者t_province_role_config表授权的人员登录
				Object instList;
				if (provinceRoleConfigList.size() > 0) {
					instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
				} else {
					instList = hospHandleService.allHospFromParent(shiroUser.getInstId());
				}
				if (instList != null) {
					JSONArray ja = (JSONArray) instList;
					ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
					ja.stream().forEach(j -> {
						JSONObject jo = (JSONObject) j;
						hospList.add(jo.getLong("ahisHosp"));
					});
					// 如果hospList为空，说明当前机构下没有医院，直接返回
					if (hospList.size() > 0) {
						cond.setHospId(null);
						cond.setHospList(hospList);
					}else{
						cond.setHospId(0L);
						cond.setHospList(null);
					}
				}else{
					cond.setHospId(0L);
					cond.setHospList(null);
				}
			}
		}

		return this.queryFinHospSetting(cond);
	}

	/**
	 * 该方法用于DIP,DRG分析表渲染表格的统筹区域的文本值
	 * @return
	 */
	public List<Map<String,Object>> getAreaRenderSelectList(){
		FinHospSettingCondition cond = new FinHospSettingCondition();
		return this.queryFinHospSetting(cond);
	}

	public List<Map<String,Object>> queryFinHospSetting(FinHospSettingCondition cond){
		List<Map<String,Object>> list =finHospSettingService.getFinSettingList(cond);
		List<Map> dictIns = finDictFeignService.getList("INSURANCE_TYPE");
		Map<String, String> mapDictIns = dictIns.stream().collect(Collectors.toMap(m -> MapUtils.getString(m, "valueCode"), m -> MapUtils.getString(m, "valueDesc")));
		list.stream().forEach(item -> item.put("text", item.get("poolingArea") + mapDictIns.get(item.get("insuranceType").toString())));
		List<Map<String,Object>> retList = Lists.newArrayList();
		Map<String,Object> sItem1 = Maps.newHashMap();
		sItem1.put("id",10001L);
		sItem1.put("text","职工");
		Map<String,Object> sItem2 = Maps.newHashMap();
		sItem2.put("id",10002L);
		sItem2.put("text","居民");
		Map<String,Object> sItem3 = Maps.newHashMap();
		sItem3.put("id",10003L);
		sItem3.put("text","职工和居民");
		retList.add(sItem1);
		retList.add(sItem2);
		retList.add(sItem3);
		retList.addAll(list);
		return retList;
	}
}
