package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.amrs.AmrsMedicalRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-08 09:23
 **/
@FeignClient(name="aier-service-amrs")
public interface AeAmrsFeignService {
    @RequestMapping(value = "/api/amrs/patient/loadMedicalRecord", method = RequestMethod.POST)
    AmrsMedicalRecord loadMedicalRecord(@RequestParam("medicalNumber") String medicalNumber, @RequestParam("inpTimes") int inpTimes);






}
