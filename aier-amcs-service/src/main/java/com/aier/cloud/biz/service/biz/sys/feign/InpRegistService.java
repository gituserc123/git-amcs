package com.aier.cloud.biz.service.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.inp.InpRegistCondition;
import com.aier.cloud.basic.api.request.condition.inp.InpTransferDeptCondition;
import com.aier.cloud.basic.api.response.domain.inp.InpRegist;
import com.aier.cloud.basic.api.response.domain.outp.OrderTempl;

/**
 * 预约信息
 * 
 * @author lc
 * @since 2019-07-26 10:21:21
 */
@FeignClient(name = "aier-service-medical")
public interface InpRegistService {
	
	@RequestMapping(value = "/api/outp/templ/getFixedSetMealForView", method = RequestMethod.POST)
	List<OrderTempl> getFixedSetMealForView();

	/**
	 * 查询转科患者
	 *
	 * @Title: findDischargePatients
	 * @param cond
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/findChangePatients", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> findChangePatients(@RequestBody InpRegistCondition cond);

	/**
	 * 查询出院患者
	 *
	 * @Title: findDischargePatients
	 * @param cond
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/findDischargePatients", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> findDischargePatients(@RequestBody InpRegistCondition cond);

	/**
	 * 根据id获取住院信息
	 *
	 * @Title: getInpRegist
	 * @param id
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getInpRegist", method = RequestMethod.POST)
	@ResponseBody
	InpRegist getInpRegist(@RequestParam("id") Long id);

	/**
	 * 根据人员id取出住院信息
	 *
	 * @Title: selectByInpNumber
	 * @param patientId
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getByPatientId", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> getByPatientId(@RequestParam("patientId") Long patientId);

	/**
	 * 根据病案号取出住院信息
	 *
	 * @Title: getByMedicalNumber
	 * @param medicalNumber
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getByMedicalNumber", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> getByMedicalNumber(@RequestParam("medicalNumber") String medicalNumber);

	/**
	 * 查询当天出院的患者住院信息
	 *
	 * @Title: getByCurrentDate
	 * @param
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getByCurrentDate", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> getByCurrentDate();

	/**
	 * TODO(按条件查询患者住院信息)
	 * 
	 * @Title: selectByInpState1
	 * @param condition
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpState1", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> selectByInpState1(@RequestBody InpRegistCondition condition);

	/**
	 * TODO(按条件查询患者住院信息,入科状态)
	 * 
	 * @Title: selectByInpState3
	 * @param condition
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpState3", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> selectByInpState3(@RequestBody InpRegistCondition condition);

	/**
	 * TODO(按条件查询患者住院信息,预出院状态)
	 * 
	 * @Title: selectByInpState4
	 * @param condition
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpState4", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> selectByInpState4(@RequestBody InpRegistCondition condition);

	/**
	 * 根据患者id获取住院信息
	 *
	 * @Title: selectByPatientId
	 * @param patientId
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByPatientId", method = RequestMethod.POST)
	@ResponseBody
	@Deprecated
	Map<String, Object> selectByPatientId(@RequestBody Long patientId);

	/**
	 * 根据住院号获取住院信息
	 *
	 * @Title: selectByInpNumber
	 * @param inpNumber
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpNumber", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> selectByInpNumber(@RequestBody String inpNumber);

	/**
	 * 根据状态列表查询入院登记信息
	 * 
	 * @Title: selectByInpStates
	 * @param inpStateList
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpStates", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> selectByInpStates(@RequestBody List<Long> inpStateList);

	/**
	 * 查询最大住院数
	 * 
	 * @param patientId
	 * @return
	 */

	@RequestMapping(path = "/api/inpreg/inpRegist/selectMaxInpTimesByPatientid", method = RequestMethod.POST, consumes = "application/json")
	public Long selectMaxInpTimesByPatientid(@RequestParam("patientId") Long patientId);

	/**
	 * (修改入院)
	 * 
	 * @param inpRegist
	 * @return Boolean (返回成功与否)
	 */

	@RequestMapping(path = "/api/inpreg/inpRegist/doUpdate", method = RequestMethod.POST, consumes = "application/json")
	public Boolean doUpdate(@RequestBody InpRegist inpRegist);

	/**
	 * (取消入院)
	 * 
	 * @param ids
	 *            (单据号)
	 * @return Boolean (返回成功与否)
	 */

	@RequestMapping(path = "/api/inpreg/inpRegist/admissionCancel", method = RequestMethod.POST, consumes = "application/json")
	public Boolean admissionCancel(@RequestParam("id") String id);

	/**
	 * 
	 * (入院登记)
	 * 
	 * @param inpRegist
	 * @param diagCode
	 * @param diagName
	 * @param diagCode1
	 * @param diagName1
	 * @param diagCode2
	 * @param diagName2
	 * @param doctorId
	 * @param eyeType
	 * @return String (返回成功与否)
	 */
	@RequestMapping(path = "/api/inpreg/inpRegist/admissionRegistration", method = RequestMethod.POST, consumes = "application/json")
	public String admissionRegistration(@RequestBody InpRegist inpRegist, @RequestParam("diagCode") String diagCode,
			@RequestParam("diagName") String diagName,
			@RequestParam(value = "diagCode1", required = false) String diagCode1,
			@RequestParam(value = "diagName1", required = false) String diagName1,
			@RequestParam(value = "diagCode2", required = false) String diagCode2,
			@RequestParam(value = "diagName2", required = false) String diagName2,
			@RequestParam("doctorId") Long doctorId, @RequestParam(value = "eyeType", required = false) Long eyeType);

	/**
	 * 取消转科业务
	 * 
	 * @param condition
	 * @return Map<String,Object>
	 *
	 * @author PengY
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/inpnurse/inpTransferDept/cancelTransferDeptInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> cancelTransferDeptInfo(@RequestBody InpTransferDeptCondition condition)
			throws Exception;

	/**
	 * 出入转管理，已入科列表， 取消入科操作
	 * 
	 * @param inRegistId
	 * @param operatedId
	 * @return Map<String,Object>
	 *
	 * @author PengY
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/cancelInSection", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> cancelInSection(@RequestParam("inRegistId") Long inRegistId,
			@RequestParam("operatedId") Long operatedId) throws Exception;

	/**
	 * 出入转管理，已入科列表，转科校验（是否存在未停止或 作废医嘱）
	 * 
	 * @param inRegistId
	 * @return Map<String,Object>
	 *
	 * @author PengY
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/transferDeptCheck", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> transferDeptCheck(@RequestParam("inRegistId") Long inRegistId);

	/**
	 * 
	 * 出入转管理，已入科列表，预出院操作
	 * 
	 * @param inRegistId
	 * @param operatedId
	 * @return Map<String,Object>
	 * @author PengY
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/plvhOperand", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> plvhOperand(@RequestParam("inRegistId") Long inRegistId,
			@RequestParam("operatedId") Long operatedId);

	/**
	 * TODO根据输入条件，检索入院患者信息(患者出入转)
	 * 
	 * @Title: selectInpPatientByCondition
	 * @param condition
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectInpPatientByCondition", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> selectInpPatientByCondition(
			@RequestBody InpRegistCondition condition);

	/**
	 * 根据住院号查询患者未发药记录
	 * 
	 * @Title: selectUntreatedDrugByInpNumber
	 * @param inpRegistId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectUntreatedDrugByInpNumber", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, Object> selectUntreatedDrugByInpNumber(
			@RequestParam("inpRegistId") Long inpRegistId);

	/**
	 * 根据门诊号获取诊断信息
	 * 
	 * @Title: getByRegNumber
	 * @param regNumber
	 * @return List<Map<String, Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getByRegNumber", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> getByRegNumber(@RequestParam("regNumber") String regNumber);

	@RequestMapping(value = "/api/inpreg/inpRegist/findInpPatientBy", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map<String, Object>> findInpPatientBy(@RequestParam("costTypeCode") String costTypeCode);

	@RequestMapping(value = "/api/inpreg/inpRegist/countByCondition", method = RequestMethod.POST, consumes = "application/json")
	public Integer countByCondition(InpRegistCondition cond);

}
