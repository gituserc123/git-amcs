package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinAttachment;
import com.aier.cloud.api.amcs.fin.domain.FinHospInsuranceCheck;
import com.aier.cloud.api.amcs.fin.domain.FinHospIsrneChkAuth;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface FinHospInsuranceCheckFeignService {

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getFinHospInsuranceCheck")
    @ResponseBody FinHospInsuranceCheck getFinHospInsuranceCheck(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getAll")
    @ResponseBody
    PageResponse<Map<String, Object>> getAll(@RequestBody FinHospInsuranceCheckCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getAllEntity")
    @ResponseBody
    List<Map<String, Object>> getAllEntity(@RequestBody FinHospInsuranceCheckCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/save")
    @ResponseBody
    Map<String, Object> save(@RequestBody FinHospInsuranceCheck stdAdaptation);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getAuthByStaffCode")
    @ResponseBody
    List<FinHospIsrneChkAuth> getAuthByStaffCode(@RequestParam("staffCode") String staffCode);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/saveAuth")
    @ResponseBody
    Object saveAuth(@RequestBody List<FinHospIsrneChkAuth> finHospIsrneChkAuths);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/deleteAuth")
    @ResponseBody
    Object deleteAuth(@RequestParam("staffCode") String staffCode);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getAuthLists")
    @ResponseBody
    List<Map<String, Object>> getAuthLists(@RequestBody FinHospIsrneChkAuthCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/hospInsuranceCheck/getFinAttachments")
    @ResponseBody List<FinAttachment> getFinAttachments(@RequestParam("id") Long id);
}
