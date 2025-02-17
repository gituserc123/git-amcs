package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.condition.FinInsPricePolicyCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsPricePolicy;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface FinInsPricePolicyService {

    @PostMapping("/api/service/biz/amcs/fin/finInsPricePolicy/getFinInsPricePolicy")
    @ResponseBody FinInsPricePolicy getFinInsPricePolicy(@RequestParam("id") Long id);

    @PostMapping("/api/service/biz/amcs/fin/finInsPricePolicy/getAll")
    @ResponseBody
    PageResponse<Map<String, Object>> getAll(@RequestBody FinInsPricePolicyCondition cond);

    @PostMapping("/api/service/biz/amcs/fin/finInsPricePolicy/save")
    @ResponseBody
    Map<String, Object> save(@RequestBody FinInsPricePolicy finInsPricePolicy);
}
