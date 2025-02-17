package com.aier.cloud.biz.ui.biz.amcs.cat.feign;

import com.aier.cloud.basic.api.request.condition.base.PatientInfoCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="aier-service-medical")
public interface MedicalFeignService {

    /**
     * 根据条件查询眼压超过21的患者检查信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/api/outinterface/query/getExamIopByRegDate")
    List<Map<String,Object>> getExamIopByRegDate(@RequestBody Map<String, Object> param);


    @RequestMapping(value = "/api/outinterface/query/pageBlurFindByParam")
    PageResponse<Map<String, Object>> pageBlurFindByParam(@RequestBody PatientInfoCondition cond);

    @RequestMapping(value = "/api/outinterface/query/getOutpRegistTrans")
    List<Map<String,Object>> getOutpRegistTrans(@RequestParam(value="hospId") Long hospId, @RequestParam(value="patientId") Long patientId);
}
