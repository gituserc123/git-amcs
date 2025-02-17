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
package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinInsPricePolicyCondition;
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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsPricePolicyService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsPricePolicy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

/**
 * FinInsPricePolicy
 * 价格政策表
 * @author 爱尔眼科
 * @since 2023-08-07 15:09:57
 */
@Api("价格政策表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsPricePolicy")
public class FinInsPricePolicyController extends BaseController{
 
	@Autowired
	private FinInsPricePolicyService finInsPricePolicyService;

	@Autowired
	private RedisService redisService;
	
	@ApiOperation(value="根据id查询FinInsPricePolicy价格政策表实体")
	@ApiParam(name="id", value="价格政策表的id", required=true)
	@RequestMapping(value = "/getFinInsPricePolicy")
	public @ResponseBody FinInsPricePolicy getFinInsPricePolicy(@RequestParam("id") Long id) {
		return finInsPricePolicyService.selectById(id);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody FinInsPricePolicyCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return  returnPageResponse(page,finInsPricePolicyService.getAll(page,cond));
	}

	@RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> save(@RequestBody FinInsPricePolicy finInsPricePolicy) throws Exception {
		// 防止开多个填报页面，保存产生多条记录的情况
		if(null == finInsPricePolicy.getId()){
			Calendar calendar = Calendar.getInstance();
			// 获取当前年
			int curYear = calendar.get(Calendar.YEAR);
			FinInsPricePolicyCondition cond = new FinInsPricePolicyCondition();
			cond.setHospId(UserContext.getTenantId());
			cond.setCurYear(String.valueOf(curYear));
			PageResponse<Map<String, Object>> pageResponse = this.getAll(cond);
			if(Objects.nonNull(pageResponse.getRows()) && pageResponse.getRows().size()>0){
				finInsPricePolicy.setId(((FinInsPricePolicy)pageResponse.getRows().get(0)).getId());
			}
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		try {
			result = finInsPricePolicyService.save(finInsPricePolicy);
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
}