package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="aier-service-system")
public interface StaffService {
	
	/**
     * 根据sc查询医院用户
     * @param sc 
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getHospStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List getHospStaff(StaffCondition sc);


	@RequestMapping(value = "/api/sys/external/hr/getJobPos")
	@ResponseBody
	List<Map<String, Object>> getJobPos(@RequestBody SysCommonCondition c) ;

}
