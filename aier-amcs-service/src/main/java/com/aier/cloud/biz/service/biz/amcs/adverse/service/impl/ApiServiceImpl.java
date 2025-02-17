package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;

import com.aier.cloud.api.amcs.adverse.domain.*;
import com.aier.cloud.api.amcs.adverse.domain.amrs.AmrsMedicalRecord;
import com.aier.cloud.api.amcs.adverse.domain.amrs.AmrsPatientBase;
import com.aier.cloud.api.amcs.adverse.domain.amrs.AmrsPatientDiag;
import com.aier.cloud.basic.api.request.condition.based.DchCommonObserveCondition;
import com.aier.cloud.basic.api.request.domain.based.DchVisionExam;
import com.aier.cloud.basic.api.response.domain.inp.OprApplyResult;
import com.aier.cloud.basic.api.response.domain.outp.OutpRegist;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.*;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 14:23
 **/

@Service
public class ApiServiceImpl implements ApiService {
    private final static Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);
    @Autowired
    AeMedicalFeignService ahisService;

    @Autowired
    AhisBaseFeignService ahisBaseService;

    @Autowired
    AeAvisFeignService avisService;

    @Autowired
    AeAmrsFeignService aeAmrsService;
    @Autowired
    AeAemrFeignService aeAemrFeignService;

    @Override
    public ApiOpDomain getOpDomain(String OpNumber) {
        ApiOpDomain apiOpDomain = new ApiOpDomain();
        List<OutpRegist> ahisRegList = ahisService.getRegistByRegNumber(OpNumber);
        if (ahisRegList != null) {
            log.info("getOpDomain|ahisRegList：{}", JSON.toJSONString(ahisRegList,SerializerFeature.WriteMapNullValue));
        } else {
            log.info("getOpDomain|ahisRegList：null");
        }
        if (ahisRegList.size() > 0) {

            BeanUtils.copyProperties(ahisRegList.get(0), apiOpDomain);
        }

        List<Map<String, Object>> ahisDiagList = ahisService.getDiagByRegNumber(OpNumber);
        log.info("getOpDomain|ahisDiagList：{}",ahisDiagList);
        if (ahisDiagList.size() > 0) {
            EntityUtils.mapToObject(ahisDiagList.get(0), apiOpDomain);
            String diagName="";
            for(Map o:ahisDiagList){
                diagName+=o.get("diagName").toString()+";";
            };

            apiOpDomain.setDiagName(diagName);
            DateFormat df= new SimpleDateFormat("yyyy-MM-dd");

            //视力
            DchCommonObserveCondition condition = new DchCommonObserveCondition();
            condition.setVisitNumber(OpNumber);
            condition.setQueryDate(df.format(new Date()));
            condition.setBid(ahisRegList.get(0).getId());
            condition.setPatientId(apiOpDomain.getPatientId());
            DchVisionExam dcVisionExam = ahisBaseService.getDchVisionExam(condition);
            if(dcVisionExam!=null) {
                log.info("getOpDomain|dcVisionExam：{}", JSON.toJSONString(dcVisionExam,SerializerFeature.WriteMapNullValue));
            }else{
                log.info("getOpDomain|dcVisionExam：null");
            }
            if(dcVisionExam!=null) {
                apiOpDomain.setDvaOd(Optional.ofNullable(dcVisionExam.getScdOd()).orElse(""));
                apiOpDomain.setDvaOs(Optional.ofNullable(dcVisionExam.getScdOs()).orElse(""));
                apiOpDomain.setNvaOd(Optional.ofNullable(dcVisionExam.getScnOd()).orElse(""));
                apiOpDomain.setNvaOs(Optional.ofNullable(dcVisionExam.getScnOs()).orElse(""));
            }
//        BeanUtils.copyProperties(dcVisionExam, apiOpDomain);
            //眼压
            DchCommonObserveCondition dchCommonObserveCondition = new DchCommonObserveCondition();
            dchCommonObserveCondition.setVisitNumber(OpNumber);
            dchCommonObserveCondition.setQueryDate(df.format(new Date()));
            dchCommonObserveCondition.setPatientId(apiOpDomain.getPatientId());
            dchCommonObserveCondition.setBid(ahisRegList.get(0).getId());
            dchCommonObserveCondition.setPatientId(apiOpDomain.getPatientId());
//            JSONObject iopMap = (JSONObject) ahisBaseService.getDchIopExam(dchCommonObserveCondition);
            Object iopMapObj = ahisBaseService.getDchIopExam(dchCommonObserveCondition);



            JSONObject iopMap = null;
            if (iopMapObj instanceof JSONObject) {
                iopMap = (JSONObject) iopMapObj;
            }

            if(iopMap!=null) {
               log.info("getOpDomain|iopMapObj：{}", JSON.toJSONString(iopMap, SerializerFeature.WriteMapNullValue));
                EntityUtils.mapToObject(iopMap, apiOpDomain);
            }else{
                log.info("getOpDomain|iopMapObj：null");
            }
        }
        /** 视光接口*/

        Map<String, Object> avisMap = avisService.getOptAndSalesDataByParams(UserContext.getTenantId(), OpNumber);
        log.info("getOpDomain|avisService：{}",avisService);
        RefractiveBlVO refractiveBlVO = JSONObject.toJavaObject((JSONObject) avisMap.get("refractive"), RefractiveBlVO.class);
        apiOpDomain.setRefractiveBlVO(refractiveBlVO);
        SalesBasicBlVO salesBasicBlVO = JSONObject.toJavaObject((JSONObject) avisMap.get("salesInfo"), SalesBasicBlVO.class);
        apiOpDomain.setSalesBasicBlVO(salesBasicBlVO);
        apiOpDomain.setEmrUrl("/aemro/ui/outp/docView/getOutpDoc?regNumber="+OpNumber+"&outpRegistSource=2&templateType=1&hospId="+UserContext.getTenantId());



        return apiOpDomain;
    }

    @Override
    public ApiInDomain getInDomain(String InNumber) {

        ApiInDomain apiInDomain = new ApiInDomain();
        /** Ahis接口*/
        Map<String, Object> ahisMap = null;
        
        ahisMap = ahisService.selectByInpNumber(InNumber);
        if(ahisMap!=null) {
            EntityUtils.mapToObject(ahisMap, apiInDomain);
        }
        OprApplyResult oprApplyResult = ahisService.getByInpNumber(InNumber);
        if(Objects.nonNull(oprApplyResult)){
            // 切口类别
            if(Objects.nonNull(oprApplyResult.getWoundHealingGradeCode())){
                apiInDomain.setCutType(oprApplyResult.getWoundHealingGradeCode().toString());
            }
        }
        
        /** AMRS接口*/
        if (!ObjectUtils.isEmpty(apiInDomain.getMedicalNumber())) {
            AmrsMedicalRecord amrsMedicalRecord = aeAmrsService.loadMedicalRecord(apiInDomain.getMedicalNumber(), apiInDomain.getInpTimes());
            AmrsPatientBase base = amrsMedicalRecord.getBase();
            if (Optional.ofNullable(amrsMedicalRecord.getBase()).isPresent()) {
                BeanUtils.copyProperties(amrsMedicalRecord.getBase(), apiInDomain);
            }
            //写入住院和门诊诊断信息
            List<AmrsPatientDiag> diagList = amrsMedicalRecord.getDiag();
            StringBuilder inpDiagNameBuffer = new StringBuilder();
            diagList.stream().forEach(diag -> {
            	inpDiagNameBuffer.append(diag.getDiagEyeTypeName()).append(diag.getIcdName()).append(";");
            });
            
            apiInDomain.setDiagName(inpDiagNameBuffer.toString());
            apiInDomain.setOutpDiagName(amrsMedicalRecord.getBase().getOutpDiagName());
        }



        /** Aemr接口*/
//        AemrInfo aemrInfo = aeAemrFeignService.loadMedicalRecord(InNumber, apiInDomain.getMedicalNumber(), apiInDomain.getInpTimes());
        Map<String, AemrVision> aemrMap = aeAemrFeignService.getVisisonByInpNumber(InNumber);
        if (Optional.ofNullable(aemrMap).isPresent()) {
            String[] opArray = {"", ""};
            List<Map<String, Object>> listOP = aeAemrFeignService.getAllByInpNumber(InNumber);
            List<Map<String, Object>> listOPPLAN = aeAemrFeignService.getAllByInpNumberPlan(InNumber);
            if (listOP!=null) {
                listOP.forEach(o -> {
                    String operDate = ((String) ((JSONObject) o.get("Oper")).get("operDate")).substring(0, 10);
                    JSONArray operDettail = (JSONArray) o.get("operDettail");
                    opArray[0] += "于" + operDate;
                    operDettail.forEach(oo -> {
                        String operName = (String) ((JSONObject) oo).get("operName");
                        String operEyeTypeName = (String) ((JSONObject) oo).get("operEyeTypeName");
                        opArray[0] += "行" + operEyeTypeName + operName;
                        if (operDettail.indexOf(oo) < operDettail.size() - 1) {
                            opArray[0] += "+";
                        }
                    });
                    opArray[0] += "；";
                });
            }


            if (listOPPLAN!=null) {
                listOPPLAN.forEach(o -> {
                    String operDate = ((String) ((JSONObject) o.get("planOper")).get("operDate")).substring(0, 10);
                    JSONArray operDettail = (JSONArray) o.get("planOperDettail");
                    opArray[1] += "拟于" + operDate;
                    operDettail.forEach(oo -> {
                        String operName = (String) ((JSONObject) oo).get("operName");
                        String operEyeTypeName = (String) ((JSONObject) oo).get("operEyeTypeName");
                        
                        opArray[1] += "行" + operEyeTypeName + operName;
                        if (operDettail.indexOf(oo) < operDettail.size() - 1) {
                            opArray[1] += "+";
                        }
                    });
                    opArray[1] += ";";
                });
            }
            apiInDomain.setOper(opArray[0]);
            apiInDomain.setOperPlan(opArray[1]);
            apiInDomain.setAemrVisionMap(aemrMap);
            apiInDomain.setEmrUrl("/aemr/ui/doc/writer/groupMedicalRecord?inpNumber=" + InNumber + "&hospId=" + UserContext.getTenantId());
        }
//        由于emr中患者信息和his中有差异，再次填充ahis接口以ahis为准

//        EntityUtils.mapToObject(ahisMap, apiInDomain);
//        AemrVision avAdmission=apiInDomain.getAemrVisionMap().get("admission");
//        avAdmission.setIopOd(avAdmission.getIopOd().replace("NCT","").replace("mmHg",""));
//        avAdmission.setIopOs(avAdmission.getIopOs().replace("NCT","").replace("mmHg",""));
//
//        AemrVision avDischarge=apiInDomain.getAemrVisionMap().get("discharge");
//        avDischarge.setIopOd(avDischarge.getIopOd().replace("NCT","").replace("mmHg",""));
//        avDischarge.setIopOs(avDischarge.getIopOs().replace("NCT","").replace("mmHg",""));
        
        //拼接出院诊断信息
        /*
        List<Map<String, Object>> diagnoseMapList = ahisService.getDiagnosticByRegNumber(InNumber);

        StringJoiner sj = new StringJoiner("、");
        diagnoseMapList.forEach(diagnoseMap -> sj.add(diagnoseMap.get("diagName").toString()));
        String diagName = sj.toString();
        
        apiInDomain.setDiagName(diagName);
        */
        return apiInDomain;
    }
}
