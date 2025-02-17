package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.api.amcs.condition.PatientOperCondition;
import com.aier.cloud.basic.api.response.domain.amrs.AmrsMedicalRecord;

@FeignClient(name = "aier-service-amrs")
public interface AmrsService {
	
	@RequestMapping(value = "/api/amrs/patientOper/findByParams", method = RequestMethod.POST, consumes = "application/json")
    List<Map<String, Object>> findByParams(@RequestBody PatientOperCondition cond);
	
	/**
	 * 获取患者的诊断和手术信息
	 * @param medicalNumber
	 * @param inpTimes
	 * @return
	 */
	@RequestMapping(value = "/api/amrs/amrsDiagInsure/loadMedicalRecordForSettlementlist", method = RequestMethod.POST)
	AmrsMedicalRecord loadMedicalRecordForSettlementlist(@RequestParam("medicalNumber") String medicalNumber,
										   @RequestParam("inpTimes") Integer inpTimes);
}
