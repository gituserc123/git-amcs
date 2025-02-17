package com.aier.cloud.biz.service.biz.amcs.jtr.service;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @program: amcs
 * @description: 医生列表
 * @author: hsw
 * @create: 2021-10-29 11:00
 **/
@FeignClient(name="aier-service-system")
public interface DoctorFeign {
    @RequestMapping(value = "/api/sys/staff/getStaffForAmcs", method = RequestMethod.POST)
    Map<String, Object> getStaffForAmcs(@RequestBody StaffCondition s);
}
