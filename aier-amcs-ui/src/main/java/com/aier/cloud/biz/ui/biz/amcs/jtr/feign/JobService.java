package com.aier.cloud.biz.ui.biz.amcs.jtr.feign;

import com.aier.cloud.api.amcs.condition.JobParam;
import com.aier.cloud.api.amcs.condition.PositionCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 参数服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-service-interface")
public interface JobService {

	@RequestMapping(value = "/api/interface/interfaceJob/runSingleJob", method = RequestMethod.POST)
	public void runSingleJob(@RequestHeader("jobClass") String jobClass, @RequestBody JobParam param);
	
}
