package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-amcs-service")
public interface CountIndexService {
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countIndex")
	@ResponseBody
	Map<String, Object> countIndex(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countByGradeOne")
	@ResponseBody
	List<Map<String, Object>> countByGradeOne(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countByDepartment")
	@ResponseBody
	List<Map<String, Object>> countByDepartment(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countByHospId")
	@ResponseBody
	List<Map<String, Object>> countByHospId(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countBySeverityLevel")
	@ResponseBody
	List<Map<String, Object>> countBySeverityLevel(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countByEventLevel")
	@ResponseBody
	List<Map<String, Object>> countByEventLevel(@RequestBody AeInfoCondition cond);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countBySubspecialty")
	@ResponseBody
	List<Map<String, Object>> countBySubspecialty(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countProvinceByHospId")
	@ResponseBody
	List<Map<String, Object>> countProvinceByHospId(AeInfoCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/findByParamsPage", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> findByParamsPage(@RequestBody AeInfoCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countHospitalByHospName")
	@ResponseBody
	List<Map<String, Object>> countHospitalByHospName(@RequestBody AeInfoCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countByParamsPage", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> countByParamsPage(@RequestBody AeInfoCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/queryInfectionByParams", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> queryInfectionByParams(@RequestBody AeInfoCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/queryOtherMultipleByParams", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> queryOtherMultipleByParams(@RequestBody AeInfoCondition cond);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/countProvReviewOver10Days")
	@ResponseBody
	List<Map<String, Object>> countProvReviewOver10Days(@RequestBody AeInfoCondition cond);

}
