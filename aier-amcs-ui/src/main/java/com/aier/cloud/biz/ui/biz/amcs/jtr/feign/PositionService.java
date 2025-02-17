package com.aier.cloud.biz.ui.biz.amcs.jtr.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.condition.PositionCondition;


/**
 * 参数服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-amcs-service")
public interface PositionService {
	@RequestMapping(value="/api/amcs/jtr/position/selectById", method=RequestMethod.POST)
	@ResponseBody
	Map selectById(Long id);
	@RequestMapping(value="/api/amcs/jtr/position/getAll", method=RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> getAll(PositionCondition cond);

	
}
