package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;

@FeignClient(name = "aier-amcs-service")
public interface AdverseEventTagService {

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeEventTags/findTagsById")
	@ResponseBody
	String findTagsById(@RequestParam("eventId") Long eventId);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeEventTags/findTagDescsById")
	@ResponseBody
	String findTagDescsById(@RequestParam("eventId") Long eventId);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeEventTags/findListByCond1")
	@ResponseBody
	List<Map<String,Object>> findListByCond1(@RequestBody Map<String, Object> cond);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeEventTags/findListByCond")
	@ResponseBody
	List<Map<String,Object>> findListByCond(@RequestBody AeInfoCondition cond);
}
