package com.aier.cloud.biz.ui.biz.adverse.feign;

import com.aier.cloud.api.amcs.adverse.domain.AeDrugReactDrugs;
import com.aier.cloud.api.amcs.condition.DrugReactDrugsCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface AeDrugReactDrugsService {

    @PostMapping("/api/service/biz/amcs/adverse/aeDrugReactDrugs/save")
    @ResponseBody
    boolean save(@RequestBody AeDrugReactDrugs aeDrugReactDrugs);

    @PostMapping( "/api/service/biz/amcs/adverse/aeDrugReactDrugs/getAllEntity")
    @ResponseBody
    List<Map<String,Object>> getAllEntity(@RequestBody DrugReactDrugsCondition cond);

    @PostMapping( "/api/service/biz/amcs/adverse/aeDrugReactDrugs/delete")
    @ResponseBody
    Integer delete(@RequestParam("id") Long id);
}
