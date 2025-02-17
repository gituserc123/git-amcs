package com.aier.cloud.biz.ui.biz.adverse.feign;

import com.aier.cloud.api.amcs.adverse.domain.ApiInDomain;
import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-07 15:16
 **/

@FeignClient(name = "aier-amcs-service")
public interface AeApiGetService {

    @RequestMapping("/api/service/biz/amcs/adverse/api/getUnplanInfo")
    @ResponseBody Map<String, Object> getUnplanInfo(@RequestParam("medicalNumber")String medicalNumber);

    @RequestMapping("/api/service/biz/amcs/adverse/api/getOpDomain")
    @ResponseBody
    ApiOpDomain getOpDomain(@RequestParam("opNumber")String opNumber);

    @RequestMapping("/api/service/biz/amcs/adverse/api/getInDomain")
    @ResponseBody
    ApiInDomain getInDomain(@RequestParam("inNumber")String inNumber);
    
    @RequestMapping("/api/service/biz/amcs/adverse/api/getMedicalNumber")
    @ResponseBody
    List<Map<String, Object>> getMedicalNumber(@RequestParam("medicalNumber")String medicalNumber);
    
    @RequestMapping("/api/service/biz/amcs/adverse/api/getEnv")
    @ResponseBody
    Map<String,String> getEnv();


}
