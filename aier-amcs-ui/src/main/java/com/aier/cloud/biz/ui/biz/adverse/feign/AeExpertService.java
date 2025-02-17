package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-amcs-service")
public interface AeExpertService {

	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody Map<String, Object> mData);

	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/updateList")
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Map<String, Object>> lData);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/updateGroup")
    @ResponseBody
    public Boolean updateGroup(@RequestBody AeExpertCondition m);

	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/bind")
    @ResponseBody
    public Map<String, Object> bind(@RequestBody AeExpertCondition bindData);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeExpert/findListByCond")
	@ResponseBody
	public PageResponse <Map<String, Object>> findListByCond(@RequestBody AeExpertCondition m);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeExpert/findEventList")
	@ResponseBody
	public PageResponse <Map<String, Object>> findEventList(@RequestBody AeInfoCondition m);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeExpert/findListByEvent")
	@ResponseBody
	public PageResponse <Map<String, Object>> findListByEvent(@RequestBody AeExpertCondition m);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/delById")
	@ResponseBody
	Boolean delById(@RequestParam("id")Long id);

	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/refresh")
	@ResponseBody
	Boolean refresh(@RequestParam("staffCode")String staffCode);

	@PostMapping("/api/service/biz/amcs/adverse/aeExpert/getAllList")
	@ResponseBody
	public List<Map<String, Object>> getAllList(@RequestBody AeExpertCondition m);
	
}
