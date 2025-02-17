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
package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertEventService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertService;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpert;
 
/**
 * AeExpert
 * 专家记录表
 * @author 爱尔眼科
 * @since 2023-02-07 15:54:34
 */
@Api("专家记录表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeExpert")
public class AeExpertController extends BaseController {

	@Autowired
	private AeExpertService aeExpertService;

	@Autowired
	private AeBasicInfoService aeBasicInfoService;

	@Autowired
	private AeExpertEventService aeExpertEventService;


	@RequestMapping(value = "/findEventList")
	@ResponseBody
	public PageResponse<Map<String, Object>> findEventList(@RequestBody AeInfoCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page, aeBasicInfoService.findListForExpert(page, cond));
	}

	@RequestMapping(value = "/getAllList")
	@ResponseBody
	public List<AeExpert> getAllList(AeExpertCondition cond) {
		return aeExpertService.getExpertsByCond(cond);
	}

	@RequestMapping(value = "/findListByEvent")
	@ResponseBody
	public PageResponse<Map<String, Object>> findListByEvent(@RequestBody AeExpertCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page, aeExpertEventService.findListByEvent(cond));
	}

	@ApiOperation(value = "根据id查询AeExpert专家记录表实体")
	@ApiParam(name = "id", value = "专家记录表的id", required = true)
	@RequestMapping(value = "/getAeExpert")
	public @ResponseBody AeExpert getAeExpert(@RequestParam("id") Long id) {
		return aeExpertService.selectById(id);
	}

	@ApiOperation(value = "根据id查询更新学组")
	@RequestMapping(value = "/updateGroup")
	public @ResponseBody Boolean updateGroup(@RequestBody AeExpertCondition cond) {
		AeExpert expert = aeExpertService.selectById(cond.getId());
		expert.setGroupValue(cond.getGroupValue());
		return aeExpertService.updateById(expert);
	}


	@RequestMapping(value = "/findListByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> findListByCond(@RequestBody AeExpertCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page, aeExpertService.findListByCond(page, cond));
	}

	@RequestMapping(value = "/save")
	public @ResponseBody Map<String, Object> save(@RequestBody Map<String, Object> mData) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		if (mData.containsKey("id")) {
			Long id = Long.parseLong(mData.get("id").toString());
			AeExpert expert = aeExpertService.selectById(id);
			//判断是否是关联学组
			if (mData.containsKey("groupValue")) {
				Integer groupValue = Integer.parseInt(mData.get("groupValue").toString());
				expert.setGroupValue(groupValue);
				aeExpertService.insertOrUpdate(expert);
			}

		} else {
			//判断是否已存在此专家
			String staffCode = mData.get("CODE").toString();
			AeExpertCondition cond = new AeExpertCondition();
			cond.setStaffCode(staffCode);
			List<Map<String, Object>> expertList = aeExpertService.findListByCond(cond);
			if (expertList.size() > 0) {
				result.put("code", "204");
				result.put("msg", "此专家已存在！");
			} else {
				try {
					aeExpertService.save(mData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.put("code", "500");
					result.put("msg", "保存失败");
					result.put("success", false);
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/updateList")
	public @ResponseBody Map<String, Object> updateList(@RequestBody List<Map<String, Object>> lData) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		List<AeExpert> experts = Lists.newArrayList();
		try {
			for (Map<String, Object> curData : lData) {
				// 获取Id,根据Id查出相应专家，并更新专家所在省区字段
				Long id = Long.parseLong(curData.get("id").toString());
				AeExpert expert = aeExpertService.selectById(id);
				expert.setProvinceCodes(curData.get("provinceCodes").toString());
				expert.setProvinceNames(curData.get("provinceNames").toString());
				experts.add(expert);
			}
			aeExpertService.insertOrUpdateBatch(experts);
		} catch (Exception ex) {
			result.put("code", "500");
			result.put("msg", "更新失败");
			result.put("success", false);
		}

		return result;
	}

	@RequestMapping(value = "/bind")
	public @ResponseBody Map<String, Object> bind(@RequestBody AeExpertCondition bindData) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		try {
			aeExpertEventService.bind(bindData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("code", "500");
			result.put("msg", "绑定失败");
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping(value = "/delById")
	public @ResponseBody boolean delById(@RequestParam("id") Long id) {
		try {
			aeExpertService.del(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/refresh")
	public @ResponseBody boolean refresh(@RequestParam("staffCode") String staffCode) {
		try{
			aeExpertService.refresh(staffCode);
		}catch(Exception e){
			return false;
		}
		return true;
	}


}