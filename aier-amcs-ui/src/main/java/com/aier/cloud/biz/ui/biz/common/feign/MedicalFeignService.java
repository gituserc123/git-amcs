package com.aier.cloud.biz.ui.biz.common.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="aier-service-medical")
public interface MedicalFeignService {


    @PostMapping(value = "/api/aier-service-medical/autoComplete/query")
    @ResponseBody
	Object query(@RequestBody Map m);


    @PostMapping(value = "/api/outp/patientInfo/getPatientInfoListByPatientId")
    @ResponseBody
    List<Map<String, Object>> getPatientInfoByPatientId(@RequestParam("patientId") Long patientId);
}
