package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.inp.InpPatientListCondition;
import com.aier.cloud.basic.api.request.domain.inp.InpRegistPatientVo;
import com.aier.cloud.basic.api.response.domain.outp.PatientInfo;

/**
 * 住院患者信息
 * @author 爱尔眼科
 * @since 2018/8/14 10:20
 */
@FeignClient(name = "aier-service-medical")
public interface InpPatientInfoService {

    /**
     * 模糊查询住院患者列表
     * @param searchValue
     * @return
     */
    @RequestMapping(value = "/api/inpnurse/inpPatientInfo/selectPageByParams", method = RequestMethod.POST, consumes = "application/json")
    List<Map<String, Object>> selectPageByParams(@RequestParam("searchValue") String searchValue);

    /**
     * 修改患者信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/api/outp/patientInfo/updateById", method = RequestMethod.POST, consumes = "application/json")
    PatientInfo updatePatientInfo(PatientInfo entity);
    
    /**
     * 修改住院患者信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/api/outp/patientInfo/editInpById", method = RequestMethod.POST, consumes = "application/json")
    Boolean editInpById(PatientInfo entity);

    /**
     * 修改住院信息
     * @param inpRegistPatientVo
     * @return
     */
    @RequestMapping(value = "/api/inpnurse/inpPatientInfo/updateRegistInfo", method = RequestMethod.POST)
    Boolean updateRegistInfo(@RequestBody  InpRegistPatientVo inpRegistPatientVo);

    /**
     * 根据条件查询住院患者列表（住院护士卡片）
     *
     * @param inpPatientListCondition
     * @return
     */
    @RequestMapping(value = "/api/inpnurse/inpPatientInfo/queryInpPatientInfoListByCard", method = RequestMethod.POST)
    List queryInpPatientInfoListByCard(InpPatientListCondition inpPatientListCondition);

    /**
     * 根据条件查询住院患者列表（住院护士）
     *
     * @param inpPatientListCondition
     * @return
     */
    @RequestMapping(value = "/api/inpnurse/inpPatientInfo/queryInpPatientInfoList", method = RequestMethod.POST)
    List queryInpPatientInfoList(InpPatientListCondition inpPatientListCondition);

}