package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeUnplReoperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.domain.ApiInDomain;
import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ApiService;
import com.aier.cloud.biz.service.biz.sys.feign.InpRegistService;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-07 14:58
 **/
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/api")
public class AeApiGetController {
    @Autowired
    ApiService apiService;
    
    @Autowired
    InpRegistService inpRegistService;

    @Autowired
    AeUnplReoperationService aeUnplReoperationService;
    
    @RequestMapping(value = "/getOpDomain")
    @ResponseBody
    public ApiOpDomain getOpDomain(@RequestParam("opNumber")String opNumber){

            return apiService.getOpDomain(opNumber);


    }

    @RequestMapping(value = "/getInDomain")
    @ResponseBody
    public ApiInDomain getInDomain(@RequestParam("inNumber")String inNumber){

            return apiService.getInDomain(inNumber);

    }
    
    @RequestMapping(value = "/getMedicalNumber")
    @ResponseBody
    public List<Map<String, Object>> getMedicalNumber(@RequestParam("medicalNumber")String medicalNumber){
    	return inpRegistService.getByMedicalNumber(medicalNumber);
    }


    @RequestMapping(value = "/getUnplanInfo")
    @ResponseBody
    public Map<String, Object> getUnplanInfo(@RequestParam("inpNumber")String inpNumber) {
        return aeUnplReoperationService.getStaffInfo(inpNumber);
    }
}
