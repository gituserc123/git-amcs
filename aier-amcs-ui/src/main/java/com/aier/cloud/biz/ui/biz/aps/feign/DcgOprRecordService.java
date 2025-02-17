package com.aier.cloud.biz.ui.biz.aps.feign;

/**
 * DcgOprCalResult
 * 人资手术计算结果表
 * @author 爱尔眼科
 * @since 2022-03-18 08:23:06
 */

import com.aier.cloud.basic.api.request.condition.amcs.OprCalResultCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "aier-service-based")
public interface DcgOprRecordService {

    @PostMapping(value = "/api/based/dcgOprRecord/getOprRec")
    @ResponseBody
    Map<String,Object> getOprRec(@RequestParam("id") Long id);

    @PostMapping(value = "/api/based/dcgOprRecord/page")
    PageResponse<Map<String, Object>> getPage(@RequestBody OprCalResultCondition cond);

}
