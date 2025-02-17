package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: amcs
 * @description: 视光接口
 * @author: hsw
 * @create: 2022-12-06 16:26
 **/

@FeignClient(name = "aier-service-avis")
public interface AeAvisFeignService {

    @PostMapping("/api/avis/sales/salesBasic/getOptAndSalesDataByParams")
    Map<String, Object> getOptAndSalesDataByParams(@RequestParam(value = "hospId")Long hospId, @RequestParam(value = "visitNumber")String visitNumber);
}
