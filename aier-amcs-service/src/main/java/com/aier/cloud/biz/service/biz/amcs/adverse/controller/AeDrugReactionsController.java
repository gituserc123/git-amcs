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

import com.aier.cloud.api.amcs.condition.DrugReactionsCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDrugReactionsService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDrugReactions;

import java.util.List;
import java.util.Map;

/**
 * AeDrugReactions
 * 药品不良反应事件报告
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@Api("药品不良反应事件报告相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeDrugReactions")
public class AeDrugReactionsController extends BaseController{
 
	@Autowired
	private AeDrugReactionsService aeDrugReactionsService;

	@Autowired
	private RedisService redisService;
	
	@ApiOperation(value="根据id查询AeDrugReactions药品不良反应事件报告实体")
	@ApiParam(name="id", value="药品不良反应事件报告的id", required=true)
	@RequestMapping(value = "/getAeDrugReactions")
	public @ResponseBody AeDrugReactions getAeDrugReactions(@RequestParam("id") Long id) {
		return aeDrugReactionsService.selectById(id);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody DrugReactionsCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		//List<Map<String, Object>> retLists = aeDrugReactionsService.getAll(page,cond);
		return  returnPageResponse(page,aeDrugReactionsService.getAll(page,cond));
	}

	@RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> save(@RequestBody AeDrugReactions aeDrugReactions) throws Exception {
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		try {
			result = aeDrugReactionsService.save(aeDrugReactions);
		} catch (Exception e) {
			result.put("code", "500");
			String msg=redisService.get("AE:SAVEMESSAGE:"+ UserContext.getUserCode());
			if(StringUtils.isNotEmpty(msg)){
				result.put("msg", msg);
				throw new BizException(msg);
			}else{
				result.put("msg", "保存失败");
			}
			result.put("success", false);
			throw e;
		}
		return result;
	}

	@RequestMapping(value = "/getAllEntity")
	@ResponseBody
	public List<Map<String, Object>> getAllEntity(@RequestBody DrugReactionsCondition cond){
		add("creator", "t_sys_staff|id|name", "creatorName");
		List<Map<String, Object>> retLists = aeDrugReactionsService.getAllEntity(cond);
		return  retLists;
	}
}