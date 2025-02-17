package com.aier.cloud.biz.service.biz.sys.feign;


import com.aier.cloud.basic.api.response.domain.outp.PatientSpecialWarn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "aier-service-medical")
public interface MedicalService {

    @RequestMapping(value = "/api/outp/patientSpecialWarn/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(@RequestBody PatientSpecialWarn entity);
}
