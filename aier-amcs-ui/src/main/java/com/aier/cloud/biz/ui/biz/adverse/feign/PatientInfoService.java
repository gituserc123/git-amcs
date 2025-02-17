package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="aier-service-based")
public interface PatientInfoService {
	
	/*根据id查询DchPatientInfo患者信息表实体*/
    @RequestMapping(value = "/api/based/patientInfo/getById", method = RequestMethod.POST)
    Map<String,Object> getById(@RequestParam("id") Long patientId);

}
