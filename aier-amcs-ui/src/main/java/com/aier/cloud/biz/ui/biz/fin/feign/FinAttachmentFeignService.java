package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.domain.FinAttachment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "aier-amcs-service")
public interface FinAttachmentFeignService {

    @RequestMapping(value = "/api/service/biz/amcs/fin/finAttach/selectById")
    @ResponseBody FinAttachment selectById(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finAttach/insert")
    @ResponseBody Boolean insert(@RequestBody FinAttachment finAttachment);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finAttach/deleteById")
    @ResponseBody boolean deleteById(@RequestParam("id") Long id);




}
