package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.basic.api.request.condition.based.DcgCommonObserveCondition;
import com.aier.cloud.basic.api.request.condition.based.DchCommonObserveCondition;
import com.aier.cloud.basic.api.request.domain.based.DchIopExam;
import com.aier.cloud.basic.api.request.domain.based.DchPcOptometry;
import com.aier.cloud.basic.api.request.domain.based.DchVisionExam;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 16:18
 **/

@FeignClient(name="aier-service-based")
public interface AhisBaseFeignService {
//    @PostMapping(value = "/api/based/dcgCommonObserve/getDchPcOptometrysByPatientId")
//    PageResponse<Map<String,Object>> getDchPcOptometryByPatientId(@RequestBody DcgCommonObserveCondition condition);
//
//
//    @PostMapping(value = "/api/based/dcgCommonObserve/getDchIopExamsByPatientId")
//    PageResponse<Map<String,Object>> getDchIopExamsByPatientId(@RequestBody DcgCommonObserveCondition condition);



    @PostMapping(value = "/api/based/dchCommonObserve/getDchIopExam")
    Object getDchIopExam(DchCommonObserveCondition condition);

    @PostMapping(value = "/api/based/dchCommonObserve/getDchVisionExamV2")
    DchVisionExam getDchVisionExam(DchCommonObserveCondition condition);
}
