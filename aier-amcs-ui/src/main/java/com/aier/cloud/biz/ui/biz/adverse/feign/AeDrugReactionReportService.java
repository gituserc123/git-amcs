package com.aier.cloud.biz.ui.biz.adverse.feign;

import com.aier.cloud.api.amcs.adverse.domain.AeDrugReactions;
import com.aier.cloud.api.amcs.condition.DrugReactionsCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface AeDrugReactionReportService {

    @PostMapping("/api/service/biz/amcs/adverse/aeDrugReactions/getAeDrugReactions")
    @ResponseBody AeDrugReactions getAeDrugReactions(@RequestParam("id") Long id);

    @PostMapping("/api/service/biz/amcs/adverse/aeDrugReactions/getAll")
    @ResponseBody
    PageResponse<Map<String, Object>> getAll(@RequestBody DrugReactionsCondition cond);

    @PostMapping("/api/service/biz/amcs/adverse/aeDrugReactions/save")
    @ResponseBody
    Map<String, Object> save(@RequestBody AeDrugReactions aeDrugReactions);

    @RequestMapping(value = "/api/service/biz/amcs/adverse/aeDrugReactions/getAllEntity")
    @ResponseBody
    List<Map<String, Object>> getAllEntity(@RequestBody DrugReactionsCondition cond);
}
