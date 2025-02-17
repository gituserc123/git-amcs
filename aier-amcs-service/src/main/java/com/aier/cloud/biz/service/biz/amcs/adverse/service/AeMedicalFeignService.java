package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.basic.api.request.domain.opr.OprApplyResultDetailData;
import com.aier.cloud.basic.api.response.domain.inp.OprApplyResult;
import com.aier.cloud.basic.api.response.domain.outp.OutpRegist;
import com.aier.cloud.biz.service.biz.sys.feign.RegistService;
import com.aier.cloud.center.common.context.AierUser;
import com.aier.cloud.center.common.context.UserContext;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 10:50
 **/

@FeignClient(name = "aier-service-medical")
public interface AeMedicalFeignService {




    @RequestMapping(value = "/api/inpreg/inpRegist/selectByInpNumber", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> selectByInpNumber(@RequestBody String inpNumber) ;
    
    
    @RequestMapping(value = "/api/inpreg/inpRegist/getAllOutDiagnoseByInpNumber", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> getAllOutDiagnoseByInpNumber(@RequestParam("inpNumber") String inpNumber) ;

    @ApiOperation(value = "根据门诊号查挂号记录", notes = "")
    @RequestMapping(value = "/api/outp/regist/getRegistByRegNumber", method = { RequestMethod.POST})
    List<OutpRegist> getRegistByRegNumber(@RequestParam(value = "regNumber") String regNumber) ;


    @RequestMapping(value = "/api/outp/empiDiagnostic/getDiagByRegNumber", method = { RequestMethod.POST })
    @ResponseBody List<Map<String,Object>> getDiagByRegNumber(@RequestParam("regNumber") String regNumber);

    @PostMapping(value = "/api/outp/patientInfo/getPatientInfoListByPatientId")
    List<Map<String, Object>> getPatientInfoByPatientId(@RequestParam("patientId") Long patientId);

    @RequestMapping(value = "/api/opr/oprAfterReg/getByOprApplyResultDetailData")
    List<OprApplyResultDetailData> getByOprApplyResultDetailData(
            @RequestBody OprApplyResultDetailData oprApplyResultDetailData);

    @RequestMapping(value = "/api/outinterface/aemr/getOprDetailByInpNumber")
    List<Map<String,Object>> getOprDetailByInpNumber(@RequestParam("inpNumber") String inpNumber);

    /*
    * 术后登记相关接口(HIS.T_OPR_APPLY_RESULT)
    * */
    @RequestMapping(value = "/api/opr/oprAfterReg/getByInpNumber")
    @ResponseBody OprApplyResult getByInpNumber(@RequestParam("inpNumber") String inpNumber);

}
