package com.aier.cloud.biz.ui.biz.amcs.coll.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.condition.DocSurgCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-amcs-service")
public interface HospDocSurgService {
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/save")
    @ResponseBody
	public Map<String, Object> save(@RequestBody Map<String, Object> m);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/findListByDoctor")
    @ResponseBody
    public List<Map<String, Object>> findListByDoctor(@RequestParam("doctorId") Long doctorId);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/del")
    @ResponseBody
    public Boolean del(@RequestParam("id") Long id);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/findListByHosp")
    @ResponseBody
    public List<Map<String, Object>> findListByHosp(@RequestParam("hospId") Long hospId);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/findCountByProv")
    @ResponseBody
    public PageResponse<Map<String, Object>> findCountByProv(@RequestBody DocSurgCondition cond);
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/findListByHospDoctor")
    @ResponseBody
    public PageResponse<Map<String, Object>> findListByHospDoctor(@RequestBody DocSurgCondition cond);
	
	
	@PostMapping("/api/service/biz/amcs/coll/amcsDocSurg/findStaffIdByHosp")
    @ResponseBody
    public List<Long> findStaffIdByHosp(@RequestParam("hospId") Long hospId);

}
