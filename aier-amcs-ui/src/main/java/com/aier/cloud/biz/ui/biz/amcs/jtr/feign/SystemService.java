package com.aier.cloud.biz.ui.biz.amcs.jtr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-08-29 15:31
 **/
@FeignClient(name="aier-service-system")
public interface SystemService {
    @RequestMapping(value = "/api/sys/appSpread/getByHospId")
    @ResponseBody
    Map<String, Object> getByHospId(@RequestParam("hospId") Long hospId);
}
