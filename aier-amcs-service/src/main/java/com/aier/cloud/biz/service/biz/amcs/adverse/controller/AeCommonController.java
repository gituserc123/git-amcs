package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import java.util.List;
import java.util.Map;

import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.center.common.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeCommonService;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;


@Api("相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/common")
public class AeCommonController extends BaseController{
	
	@Autowired
	private AeCommonService aeService;
	@Autowired
	private RedisService redisService;
	@RequestMapping(value = "/save")
	public @ResponseBody Map<String, Object> save(@RequestBody Map<String, Object> mData,@RequestParam("eEnum") EventEnum eEnum) throws Exception {

		Map<String, Object> result = Maps.newHashMap();

		result.put("code", "200");
		result.put("success", true);

		try {
			Map<String, Object> data = aeService.save(mData, eEnum);
			result.put("msg", "保存成功！");
			result.put("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	@RequestMapping(value = "/merge")
	public @ResponseBody boolean merge(@RequestParam Long masterId, @RequestParam List<Long> mergeIds) {
		try {
			return aeService.merge(masterId, mergeIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/reback")
	public @ResponseBody boolean reback(@RequestParam Long basicId,
			@RequestParam("eEnum") EventEnum eEnum,
			@RequestParam(value = "node", required = false)  Integer node) {
		try {
			if(ObjectUtils.isEmpty(node)) {
				node = NodeEnum.REPORTING.getSeq();
			}
			return aeService.reback(basicId, eEnum, node);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/findById")
	public @ResponseBody Map<String, Object> findById(@RequestParam(required = false) Long id, @RequestParam EventEnum eEnum) {
		Map<String, Object> mEventInfo = Maps.newHashMap();
		try {
			mEventInfo = aeService.findById(id, eEnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mEventInfo;
	}
	
	@RequestMapping(value="/findListByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> listNode(@RequestBody AeInfoCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, null);
	}

}
