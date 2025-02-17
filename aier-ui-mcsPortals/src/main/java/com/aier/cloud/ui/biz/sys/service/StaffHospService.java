package com.aier.cloud.ui.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.sys.SysDataScopeRequest;
import com.aier.cloud.basic.api.response.domain.sys.SysDataScopeResponse;

@FeignClient(name="aier-service-system")
public interface StaffHospService {

    @RequestMapping(value = "/api/sys/staff/getStaffByType", method = RequestMethod.POST)
    List<Map<String,Object>> getStaffByType(@RequestParam("staffId") Long staffId, @RequestParam("instId") Long instId);

    @RequestMapping(value = "/api/sys/staff/getHospStaffForExport", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map> getHospStaffForExport(@RequestBody StaffCondition sc);
    
    @RequestMapping(value = "/api/sys/dataScope/save", method = RequestMethod.POST)
    boolean save(@RequestBody SysDataScopeRequest sysDataScopeRequest);
    
    
    @RequestMapping(value = "/api/sys/dataScope/getDataScopeByStaff", method = RequestMethod.POST)
    List<SysDataScopeResponse> getDataScopeByStaff(@RequestParam("staffId") Long staffId);
}
