package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;
import com.aier.cloud.api.amcs.condition.PatientOperCondition;
import com.aier.cloud.basic.api.response.domain.amrs.AmrsMedicalRecord;
import com.aier.cloud.basic.api.response.domain.amrs.AmrsPatientBase;
import com.aier.cloud.basic.api.response.domain.amrs.AmrsPatientDiag;
import com.aier.cloud.basic.api.response.domain.amrs.AmrsPatientOper;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeApiGetService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AmrsService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-07 15:21
 **/

@Controller
@RequestMapping("/ui/amcs/adverse/event/api/")
public class AeApiGetController {

    @Autowired
    AeApiGetService aeApiGetService;
    
    @Autowired
    AmrsService amrsService;

    @RequestMapping(value = "/getOpDomain",method = {  RequestMethod.POST})
    @ResponseBody
    public JSONObject getOpDomain(@RequestParam("opNumber")String opNumber) {

        return (JSONObject)JSONObject.toJSON(aeApiGetService.getOpDomain(opNumber));
    }

    @RequestMapping(value = "/getInDomain",method = {  RequestMethod.POST})
    @ResponseBody
    public  JSONObject getInDomain(@RequestParam("inNumber")String inNumber) {
        return (JSONObject)JSONObject.toJSON(aeApiGetService.getInDomain(inNumber));
    }
    
    @RequestMapping(value = "/getMedicalNumber",method = {  RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getMedicalNumber(@RequestParam("medialNumber")String medialNumber) {
        return aeApiGetService.getMedicalNumber(medialNumber);
    }
    
    @RequestMapping(value = "/getOprInfo",method = {  RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getOprInfo(PatientOperCondition cond){
    	Map<String, Object> oprInfo = Maps.newHashMap();
    	AmrsMedicalRecord record = amrsService.loadMedicalRecordForSettlementlist(cond.getMedicalNumber(), cond.getInpTimes());
    	
    	AmrsPatientBase base = record.getBase();
    	String crystalTypeName = base.getCrystalTypeNameL();
    	
    	if(ObjectUtils.isEmpty(crystalTypeName)) crystalTypeName = base.getCrystalTypeNameR();
    	oprInfo.put("crystalTypeName", crystalTypeName);
    	List<AmrsPatientOper> diagList = record.getOper();
    	AmrsPatientOper oprDetail = diagList.stream()
                .filter(entity -> entity.getIsAttachOper().equals(0)).findFirst().orElse(null);
    	if(!ObjectUtils.isEmpty(oprDetail)) {
    		oprInfo.put("operDoctName", oprDetail.getOperDoctName());
    		oprInfo.put("operDoctCode", oprDetail.getOperDoctCode());
    	}
    	return oprInfo;
    	
    }

}
