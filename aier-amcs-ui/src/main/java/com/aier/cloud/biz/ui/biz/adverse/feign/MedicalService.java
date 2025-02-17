package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.base.PatientInfoCondition;

@FeignClient(name = "aier-service-medical")
public interface MedicalService {
	
	/**
	 * (根据住院号/门诊号查询患者基本信息)
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(value = "/api/outp/patientInfo/getPatientInfoListByRegNumber", method = RequestMethod.POST)
	public List<Map<String, Object>> getPatientInfoByRegNumber(@RequestParam("regNumber") String regNumber);
	
	/**
	 * (模糊查询患者列表)
	 * 
	 * @Title: blurFindByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/blurFindByParam", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> blurFindByParam(@RequestBody PatientInfoCondition cond);

}
