package com.aier.cloud.biz.ui.biz.adverse.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-service-aemr")
public interface AemrService {

    @RequestMapping(value = "/api/doc/aemrPlanOper/getForListByInpNumber", method = RequestMethod.POST)
    List<Map<String,Object>> getForListByInpNumber(@RequestParam("inpNumber") String inpNumber);


    @RequestMapping(value = "/api/aemr/amcsOut/getByInpNumberAndApplyNumber", method = RequestMethod.POST)
    String getByInpNumberAndApplyNumber(@RequestParam("inpNumber") String inpNumber,
                                        @RequestParam("applyNumber") String applyNumber);


    @RequestMapping(value = "/api/aemr/amcsOut/getDetailByApplyNumber", method = RequestMethod.POST)
    Map<String,Object> getDetailByApplyNumber(@RequestParam("applyNumber") String applyNumber);


}
