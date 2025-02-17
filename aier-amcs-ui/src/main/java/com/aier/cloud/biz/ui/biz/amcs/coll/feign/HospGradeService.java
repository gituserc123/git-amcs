package com.aier.cloud.biz.ui.biz.amcs.coll.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-amcs-service")
public interface HospGradeService {
	
	@PostMapping(value = "/api/service/biz/amcs/coll/amcsCollHospGrade/findList")
	@ResponseBody
	public PageResponse <Map<String, Object>> findList(@RequestBody CollCondition cond);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsCollHospGrade/findById")
    @ResponseBody
    public Map<String, Object> findById(@RequestParam("id") Long id);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsCollHospGrade/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody Map<String, Object> mData);

}
