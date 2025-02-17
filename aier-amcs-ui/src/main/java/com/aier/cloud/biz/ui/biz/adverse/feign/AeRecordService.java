package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeOperationRecord;

@FeignClient(name = "aier-amcs-service")
public interface AeRecordService {

	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/saveOpinion")
	@ResponseBody
	public Integer saveOpinion(@RequestBody Map<String, Object> m);

	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/getOperatorRecord")
	@ResponseBody
	public AeOperationRecord getOperatorRecord(@RequestBody AeInfoCondition cond);
}
