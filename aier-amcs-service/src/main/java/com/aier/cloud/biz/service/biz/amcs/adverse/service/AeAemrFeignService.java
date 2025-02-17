package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.AemrVision;
import com.aier.cloud.basic.api.response.domain.amrs.AemrInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-08 15:34
 **/

@FeignClient(name="aier-service-aemr")
public interface AeAemrFeignService {
    @RequestMapping(value = "/api/aemr/insure/getAemrInfo", method = RequestMethod.POST)
    AemrInfo loadMedicalRecord(@RequestParam("inpNumber") String inpNumber, @RequestParam("medicalNumber") String medicalNumber, @RequestParam("inpTimes") int inpTimes);

    @RequestMapping(value = "/api/aemr/ahisOut/getVisisonByInpNumber", method = RequestMethod.POST)
    Map<String, AemrVision> getVisisonByInpNumber(@RequestParam("inpNumber") String inpNumber);

    /*  拟施手术*/

    @RequestMapping(value = "/api/doc/aemrPlanOper/getAllByInpNumber")
    List<Map<String,Object>> getAllByInpNumberPlan(@RequestParam("inpNumber") String inpNumber);


    /* 实施手术 */

    @RequestMapping(value = "/api/doc/aemrOper/getAllByInpNumber")
    List<Map<String,Object>> getAllByInpNumber(@RequestParam("inpNumber") String inpNumber) ;



}
