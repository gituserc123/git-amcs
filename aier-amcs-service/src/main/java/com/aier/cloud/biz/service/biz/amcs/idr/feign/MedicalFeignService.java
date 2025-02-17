package com.aier.cloud.biz.service.biz.amcs.idr.feign;
/*
 * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
 */


import com.aier.cloud.basic.api.request.condition.inp.InpRegistCondition;
import com.aier.cloud.basic.api.request.condition.outp.RegistConditions;
import com.aier.cloud.basic.api.request.condition.trans.TransOutpExpensesCondition;
import com.aier.cloud.basic.api.request.condition.trans.TransPayCondition;
import com.aier.cloud.basic.api.request.domain.based.DchDrugs;
import com.aier.cloud.basic.api.request.domain.inp.InpAdditionalInsure;
import com.aier.cloud.basic.api.request.domain.opr.OprApplyResultDetailData;
import com.aier.cloud.basic.api.request.domain.outp.MedicalRecord;
import com.aier.cloud.basic.api.request.domain.outp.PatientMedicalInsure;
import com.aier.cloud.basic.api.request.domain.trans.TransOutpRecord;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.inp.InpRegist;
import com.aier.cloud.basic.api.response.domain.inpdoctor.InpOrder;
import com.aier.cloud.basic.api.response.domain.outp.*;
import com.aier.cloud.basic.api.response.domain.trans.TransInpExpenses;
import com.aier.cloud.basic.api.response.domain.trans.TransOutpExpenses;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * medical服务
 * 
 * @author xxc
 * @since 2019-01-18
 * @version 1.0
 * 			1.1 新增方法：getNursingDays add by 2021-07-20
 */
@FeignClient(name = "aier-service-medical")
public interface MedicalFeignService {

	/**
	 * 根据结算单号查询TransTaxOrder 发票信息实体
	 * @author zb
	 * @date 2023/5/22  17:48
	 */
	@RequestMapping("/api/trans/transTaxOrder/getByTransNumber")
	List<Map<String,Object>>  getTransTaxOrderByTransNumber(@RequestParam("transNumber") String transNumber);

	/**
	 * 根据就诊号，查询外伤和涉及第三方标记
	 * @param visitNumber 就诊号
	 * @return traumaFlag  is '外伤标记(1-外伤，0-非外伤)';thirdFlag is '涉及第三方标记(1-涉及,0不涉及)'
	 */
	@RequestMapping("/api/outinterface/insureQy/getTraumaByVisitNumber")
	List<Map<String,Object>> getTraumaByVisitNumber(@RequestParam("visitNumber") String visitNumber);

    /**
     * 据处方号查医嘱记录
     * @param recipeNumber 处方号
     */
    @RequestMapping("/api/outp/order/getOrderByRecipeNumber")
    OutpOrder getOrderByRecipeNumber(@RequestParam("recipeNumber") String recipeNumber);

    /**
     * 根据处方号查医嘱记录详细
     * @param recipeNumber 处方号
     */
    @RequestMapping("/api/outp/order/findOrderDetailByRecipeNumber")
    List<OutpOrderDetail> findOrderDetailByRecipeNumber(@RequestParam("recipeNumber") String recipeNumber);

    /**
     * 根据医嘱字典ID查询对应的收费项目数据
     * @param itemCode 医嘱字典ID
     */
    @RequestMapping("/api/outp/order/findPayItemByOrderItemCode")
    List<Map<String, Object>> findPayItemByOrderItemCode(@RequestParam("itemCode") Long itemCode);

    /**
     * 获取药品字典
     * @param id 药品字典ID
     */
    @RequestMapping("/api/outp/drugs/getById")
    DchDrugs getDrugsById(@RequestParam("id") Long id);

    /**
	 * 根据ID查挂号记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/outp/regist/selectById")
	Map<String, Object> selectById(@RequestParam(value = "id") Long id);

	/**
	 * 根据id集合查询费用明细记录
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getByIds")
	List<TransOutpExpenses> getByIds(@RequestBody List<Long> ids);


	/**
	 * 根据id集合查询费用明细记录
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getByIds")
	List<TransInpExpenses> getInpExpensesByIds(@RequestBody List<Long> ids);

	/**
	 * 根据id查询对应门诊医嘱记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/outp/recipe/getOrderDetailTempById",method = RequestMethod.POST)
	OutpOrderDetailTemp getOrderDetailTempById(@RequestParam(value = "id") Long id);

	/**
	 * 根据id查询对应住院医嘱记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/inpOrder/getInpOrder",method = RequestMethod.POST)
	InpOrder getInpOrderById(@RequestParam(value = "id") Long id);

	/**
	 * 根据住院号查找AHIS住院登记信息
	* @Title: selectByInpNumber  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param inpNumber
	* @param @return    设定文件  
	* @return Map<String,Object>    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpNumber", method = RequestMethod.POST)
    @ResponseBody
    Map<String,Object> selectByInpNumber(@RequestBody String inpNumber);
	
	/**
	 * 根据住院号，查询医保需要上传的费用明细数据，包括退费正负费用
	 * @Title: getInsureFeeByInpNumber
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inpNumber
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getInsureFeeByInpNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getInsureFeeByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 根据住院号，查询医保需要上传的截止时间之前的费用明细数据，包括退费正负费用
	 * @Title: getInsureFeeByInpNumber
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inpNumber
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getInsureFeeByInpNumberFeeDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getInsureFeeByInpNumberFeeDate(@RequestParam(value = "inpNumber") String inpNumber,@RequestParam(value = "feeDate") String feeDate);

	/**
	 * 根据住院号，查询医保需要上传的费用明细数据，不包括退费正负费用
	 * @Title: getInsureFeeByInpNumberPro
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inpNumber
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getInsureFeeByInpNumberPro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getInsureFeeByInpNumberPro(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 根据门诊号查询门诊挂号记录
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(value = "/api/outp/regist/getRegistByRegNumber")
	public @ResponseBody List<OutpRegist> getRegistByRegNumber(@RequestParam(value = "regNumber") String regNumber);

	/**
	 * 根据住院号查询住院登记记录
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getByInpNumber", method = RequestMethod.POST)
	public @ResponseBody InpRegist getInpRegistByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 根据住院号查询住院结算主表
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/trans/inpsettlement/getByInpNumber", method = RequestMethod.POST)
	public  List<Map<String, Object>>  getInpRecordByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	  
	/**
	 * 根据门诊号查询门诊结算记录
	 * @param visitNumber
	 * @return
	 */
	@RequestMapping(value = "/api/trans/outpRecord/getTransOutpRecByVisitNumber")
	public @ResponseBody List<TransOutpRecord> getTransOutpRecByVisitNumber(@RequestParam("visitNumber") String visitNumber);

	/**
	 * 根据结算单号查询结算主表记录
	 * @param transNumber
	 * @return
	 */
	@RequestMapping(value = "/api/trans/outpRecord/getByTransNumber")
	public @ResponseBody List<TransOutpRecord> getRecordByTransNumber(@RequestParam("transNumber") String transNumber);


	/**
	 * 根据住院号查询出院诊断相关信息
	* @Title: getOutDiagnoseByInpNumber  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param inpNumber
	* @param @return    设定文件  
	* @return List<Map<String,Object>>    返回类型  
	* @throws
	 */                      
	@RequestMapping(value = "/api/inpreg/inpRegist/getOutDiagnoseByInpNumber", method = RequestMethod.POST)
	public List<Map<String, Object>> getOutDiagnoseByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 根据住院号查询全部出院诊断相关信息
	 * @Title: getOutDiagnoseByInpNumber
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inpNumber
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getAllOutDiagnoseByInpNumber", method = RequestMethod.POST)
	public List<Map<String, Object>> getAllOutDiagnoseByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 根据门诊号查询诊断信息
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(value = "/api/outp/doctorStation/getDiagnosticByRegNumber", method = RequestMethod.POST)
	public List<Map<String,Object>> getDiagByRegNumber(@RequestParam(value = "regNumber") String regNumber);

	/**
	 * 根据住院号查询其所有住院诊断信息
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/getAllDiagByInpNumber", method = RequestMethod.POST)
	List<Map<String,Object>> getAllDiagByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);

	/**
	 * 医保——根据输入条件，检索门诊患者信息
	 * @return
	 */
	@RequestMapping(value = "/api/outp/regist/selectByOutpInsure" , method = RequestMethod.POST)
	List<Map<String, Object>> selectByOutpInsure(@RequestBody RegistConditions cond);

	/**
	 *  医保——根据输入条件，检索住院患者信息
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpInsure", method = RequestMethod.POST)
	List<Map<String,Object>> selectByInpInsure(@RequestBody InpRegistCondition condition);

	/**
	 *  医保——根据身份证号，检索患者信息
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectPatientInfoByIdNumber", method = RequestMethod.POST)
	List<Map<String,Object>> selectPatientInfoByIdNumber(@RequestParam("idNumber") String idNumber);

	/**
	 * 门诊收费结帐
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/checkOut")
	public Map<String ,Object> checkOut(@RequestBody TransPayCondition condition);

	/**
	 * 医保-查询住院患者
	 * @param costTypeCode
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/findInpPatientBy", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> findInpPatientBy(@RequestParam(value = "costTypeCode") String costTypeCode);

	/**
	 * 医保-查询住院患者预交金
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/trans/inpPrepay/getPrepaymentAmountByInpNumber", method = RequestMethod.POST)
	@ResponseBody BigDecimal getPrepaymentAmountByInpNumber(@RequestParam("inpNumber") String inpNumber);

	/**
	 * 根据门诊号查询指定医生开立的未结算费用
	 * @param visitNumber
	 * @param doctorId
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getNotSettleFeeByVisitNumber", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> getNotSettleFeeByVisitNumber(@RequestParam("visitNumber") String visitNumber, @RequestParam("doctorId") Long doctorId);

	/**
	 * 根据门诊号查询门诊费用信息
	 * @param visitNumber
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/selectSettleFeeByVisitNumber", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> selectSettleFeeByVisitNumber(@RequestParam("visitNumber") String visitNumber);

	/**
	 * 根据主索引号查询患者医保信息
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "/api/outp/patientMedicalInsure/getByPatientId", method = RequestMethod.POST)
	@ResponseBody List<PatientMedicalInsure> getPatientInsureInfoByPatientId(@RequestParam("patientId") Long patientId);

	/**
	 * 根据主索引号查询患者信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/outp/patientInfo/selectById", method = RequestMethod.POST)
	@ResponseBody PatientInfo getPatientInfoByPatientId(@RequestParam("id") Long id);

	/**
	 * 根据itemCode获取医嘱目录详情
	 * @param itemCode
	 * @return
	 */
	@RequestMapping(value = "/api/inpOrder/getDchOrderByItemCode", method = RequestMethod.POST)
	@ResponseBody DchOrder getDchOrderByItemCode(@RequestParam("itemCode") Long itemCode);

	/**
	 * 根据住院号查询诊断
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpdoctor/inpDiag/getDiagnosticByInpNumber", method = RequestMethod.POST)
	@ResponseBody List<Map<String,Object>> getDiagnosticByInpNumber(@RequestParam("inpNumber") String inpNumber);

	/**
	 * 根据住院号，将未下达的医嘱生成临时的费用对象数据，供医审调用
	 * @param inpNumber
	 * @param costType
	 * @return
	 */
	@RequestMapping(value = "/api/inpOrder/buildFeeTempByInpNumber", method = RequestMethod.POST)
	@ResponseBody List<TransInpExpenses> buildFeeTempByInpNumber(@RequestParam("inpNumber") String inpNumber,
																 @RequestParam("costType") String costType,
																 @RequestParam("expiryDate") int expiryDate,
																 @RequestParam("doctorId") Long doctorId);
	/**
	 * 根据门诊号查询医嘱信息
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(value = "api/outp/doctorStation/getOutpOrderDetailListByVisitNumber", method = RequestMethod.POST)
	@ResponseBody List<Map<String,Object>> getOutpOrderDetailListByVisitNumber(@RequestParam("regNumber") String regNumber);

	/**
	 * 根据住院号查询医嘱信息
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/outinterface/aemr/getInpOrder", method = RequestMethod.POST)
	public List<InpOrder> getInpOrder(@RequestParam(value="inpNumber")String inpNumber);


	/**
	 * 根据门诊号查询费用汇总信息
	 * @param visitNumber
	 * @return
	 */
	@RequestMapping(value = "trans/expenses/getInvoiceTypeSumByVisitNumber", method = RequestMethod.POST)
	public List<Map<String,Object>> getInvoiceTypeSumByVisitNumber(@RequestParam(value = "visitNumber") String visitNumber);

	/**
	 * 根住院号查询费用汇总信息
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getTaxInvoiceTypeSumByInpNumber", method = RequestMethod.POST)
	public List<Map<String,Object>> getTaxInvoiceTypeSumByInpNumber(@RequestParam(value = "inpNumber") String inpNumber);


	/**
	 * 根据门诊号查询主诊断信息
	 * @param visitNumber
	 * @return
	 */
	@RequestMapping(value = "trans/expenses/getDiagOrderByVisitNumber", method = RequestMethod.POST)
	public List<Map<String,Object>> getDiagOrderByVisitNumber(@RequestParam(value = "visitNumber") String visitNumber);

	/**
	 * 根据住院号查询已结账费用
	 * */
	@RequestMapping(value = "trans/inpsettlement/getInpRegByInpNumber", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getInpRegByInpNumber(@RequestParam("inpNumber") String inpNumber);
	
	
	/**
	 * 新增inpAdditionalInsure医保附加表
	 * */
	@RequestMapping(value = "api/inpdoctor/inpAdditionalInsure/insertInsureAdditionalInfo", method = RequestMethod.POST)
	 public @ResponseBody void insertInsureAdditionalInfo(@RequestBody InpAdditionalInsure info) throws ParseException;
	 
	/**
	 * 修改inpAdditionalInsure医保附加表
	 * */
	 @RequestMapping(value = "api/inpdoctor/inpAdditionalInsure/updateInsureAdditionalInfo", method = RequestMethod.POST)
	 public @ResponseBody   void updateInsureAdditionalInfo(@RequestBody InpAdditionalInsure info);
	 
	 
	 /**
		 * 根据门诊/住院号查询inpAdditionalInsure医保附加表实体
		 * */
	 @RequestMapping(value = "api/inpdoctor/inpAdditionalInsure/getInsureAdditionalInfo", method = RequestMethod.POST)
	 public @ResponseBody List<InpAdditionalInsure> getInsureAdditionalInfo(@RequestParam("visitNumber") String visitNumber);



	/**
	 * 根据住院号查询诊断记录
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpdoctor/inpDiag/getDiagnosticByInpNumber", method = RequestMethod.POST)
	@ResponseBody List<Map<String,Object>> getDiagnosticRecordByInpNumber(@RequestParam("inpNumber") String inpNumber);

	/**
	 * 根查询手术信息
	 * */
	@RequestMapping(value = "/api/opr/oprAfterReg/getByOprApplyResultDetailData", method = RequestMethod.POST)
	public @ResponseBody List<OprApplyResultDetailData> getByOprApplyResultDetailData(@RequestBody OprApplyResultDetailData oprApplyResultDetailData);

	/**
	 * 根据住院号，查询已结算的费用明细
	 * */
	@RequestMapping(value = "/api/inp/transInpExpenses/getSettlementFeeByInpNumber", method = RequestMethod.POST)
	@ResponseBody List<TransInpExpenses> getSettlementFeeByInpNumber(@RequestParam("inpNumber") String inpNumber);


	/**
	 * 根据住院号，医院id查询未进行住院费用医保审核的信息列表
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getNotCheckExpenseByInpNumber", method = RequestMethod.POST)
	@ResponseBody List<TransInpExpenses> getNotCheckExpenseByInpNumber(@RequestParam("inpNumber") String inpNumber);
	
	
	/**
	 * 获取部分退费中新增费用实体
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getReturnAddExpenses", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> getReturnAddExpenses(@RequestParam("ids") String ids,@RequestParam("operatorId") Long operatorId);

	/**
	 * 根据住院号、护理名称，查询相应的护理天数
	 * @param inpNumber 	住院号
	 * @param nursingNames 	护理名称
	 * @return 护理天数列表
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getNursingDays", method = RequestMethod.POST)
	@ResponseBody
	List<Map<String, Object>> getNursingDays(@RequestParam("inpNumber") String inpNumber,
				@RequestParam("nursingNames") List<String> nursingNames);

	/**
	 * 根据患者id查询住院登记记录
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByPatientId", method = RequestMethod.POST)
	@ResponseBody
	Map<String,Object> selectByPatientId(@RequestBody Long patientId);

	/**
	 * 根据主索引号查询患者   HIS医保信息
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "/api/outp/patientMedicalInsure/getByPatientId", method = RequestMethod.POST)
	@ResponseBody List<PatientMedicalInsure> getPatientMedicalInsureByPatientId(@RequestParam("patientId") String patientId);

	/**
	 * 查询自费病人费用明细数据
	 * @param startDate
	 * @param endDate
	 * @param inpNumber
	 * @param costTypeStr
	 * @return
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getPayOwnFeeByInpNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	PageResponse<Map<String,Object>> getPayOwnFeeByInpNumber(Page<Map<String,Object>> page, @RequestParam("startDate") String startDate,
															 @RequestParam("endDate") String endDate,
															 @RequestParam("inpNumber") String inpNumber,
															 @RequestParam("costTypeStr") String costTypeStr);


	/**
	 * 根据门诊号查询简要病史
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(value = "/api/outp/doctorStation/getMedicalRecordByRegNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody MedicalRecord getMedicalRecordByRegNumber( @RequestParam("regNumber") String regNumber);
	
	
	
	
	/**
	 * 查询自费病人费用明细数据  --门诊已结算费用
	 * @param startDate
	 * @param endDate
	 * @param inpNumber(门诊visiNumber)
	 * @param costTypeStr
	 * @return
	 */
	@RequestMapping(value = "/api/trans/transOutpExpenses/getPayOwnFeeByVisitNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	PageResponse<Map<String,Object>> getPayOwnFeeByVisitNumber(Page<Map<String,Object>> page, @RequestParam("startDate") String startDate,
															 @RequestParam("endDate") String endDate,
															 @RequestParam("inpNumber") String inpNumber,
															 @RequestParam("costTypeStr") String costTypeStr);

	/**
	 * 根据id查询TransInpExpenses信息实体
	 * */
	@RequestMapping(value = "/api/inp/transInpExpenses/selectById", method = RequestMethod.POST)
	@ResponseBody TransInpExpenses selectTransById(@RequestParam("id") Long id);

		/**
	 * 查询自费病人费用明细数据New（住院）
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inp/transInpExpenses/getPayOwnFeeByInpNumberNew", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map<String,Object>> getPayOwnFeeByInpNumberNew(@RequestParam("inpNumber") String inpNumber);

	/**
	 * 查询自费病人费用明细数据New（门诊）
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/trans/transOutpExpenses/getPayOwnFeeByOutpNumberNew", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map<String,Object>> getPayOwnFeeByOutpNumberNew(@RequestParam("inpNumber") String inpNumber);

	/**
	 * 挂号接诊信息-查询
	 * @return
	 */
	@RequestMapping(value = "/api/outp/regist/findRegistByDate" , method = RequestMethod.POST)
	List<OutpRegist> findRegistByDate(@RequestBody RegistConditions cond);

	/**
	 * 按照条件查询AHIS系统中检查信息
	 * @param con
	 * @return
	 */
	@RequestMapping(value = "/api/outinterface/insureQy/getPhyscApply", method = RequestMethod.POST)
	List<Map<String,Object>> getPhyscApply(@RequestBody Map<String,Object> con);

	/**
	 * 按照条件查询AHIS系统中检查明细信息
	 * @param con
	 * @return
	 */
	@RequestMapping(value = "/api/outinterface/insureQy/getPhyscDetail", method = RequestMethod.POST)
	List<Map<String,Object>> getPhyscDetail(@RequestBody Map<String,Object> con);

	/**
	 * 根据患者门诊号，查询该患者的联系人信息和关系字典
	 * @param regNumber
	 * @return
	 */
	@RequestMapping(path = "/outp/recipe/getBeltDrugContactsList", method = RequestMethod.POST)
	List<Map<String,Object>> getBeltDrugContactsList(@RequestParam("regNumber") String regNumber);

	/**
	 * 根据状态列表查询入院登记信息
	 *
	 * @Title: selectByInpStates
	 * @param inpStateList
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpreg/inpRegist/selectByInpStates", method = RequestMethod.POST)
	@ResponseBody List<Map<String, Object>> selectByInpStates(@RequestBody List<Long> inpStateList);

	/**
	 * 根据门诊号查询指定医生开立的未发送费用
	 * @param visitNumber
	 * @param doctorId
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getNotSendFeeByVisitNumber", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> getNotSendFeeByVisitNumber(@RequestParam("visitNumber") String visitNumber, @RequestParam("doctorId") Long doctorId);

	/**
	 * 根据住院号获取预交金总费用
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpNurse/inpOrder4InpNurse/getInpPrepayTotalAmountByInpNumber", method = RequestMethod.POST)
	@ResponseBody BigDecimal getInpPrepayTotalAmountByInpNumber(@RequestParam("inpNumber") String inpNumber);


	/**
	 * 根据住院号获取预交金总费用
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getByTransOutpExpensesCondition", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> getByTransOutpExpensesCondition(TransOutpExpensesCondition condition);


	/**
	 * 根据结算单号查询费用明细(不区分启用状态)
	 * @param inpNumber
	 * @return
	 */
	@RequestMapping(value = "/trans/expenses/getByTransNumber", method = RequestMethod.POST)
	@ResponseBody List<TransOutpExpenses> getByTransNumber(@RequestParam("transNumber")String transNumber);
}
