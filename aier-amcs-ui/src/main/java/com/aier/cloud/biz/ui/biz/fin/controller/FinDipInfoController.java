package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsDipAnalysisCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsDipAnalysis;
import com.aier.cloud.api.amcs.fin.domain.FinInsDipInfo;
import com.aier.cloud.api.amcs.fin.domain.FinInsDipPay;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDipAnalysisService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDipPayService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finDipInfo")
public class FinDipInfoController extends BaseFinController{

    @Autowired
    private FinDipAnalysisService finDipAnalysisService;
    @Autowired
    private FinDipPayService finDipPayService;
    @Autowired
    private FinHospSettingController finHospSettingController;
    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value = "/getFinInsDipPay")
    public @ResponseBody
    Map<String, Object> getFinInsDipPay(@RequestParam("id") Long id) {
        return finDipPayService.getFinInsDipPay(id);
    }

    @RequestMapping(value = "/getFinInsDipAnalysis")
    public @ResponseBody
    Map<String, Object> getFinInsDipAnalysis(@RequestParam("id") Long id) {
        return finDipAnalysisService.getFinInsDipAnalysis(id);
    }

    @RequestMapping(value ="/saveFinInsDipInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody FinInsDipInfo finInsDipInfo){
        FinInsDipPay finInsDipPay = new FinInsDipPay();
        BeanUtils.copyProperties(finInsDipInfo,finInsDipPay);
        finInsDipPay.setId(finInsDipInfo.getPayId());
        boolean retP = finDipPayService.save(finInsDipPay);
        return retP;
    }

    @RequestMapping(value ="/getByMainId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public FinInsDipInfo getByMainId(Long mainId){
        FinInsDipPay finInsDipPay = finDipPayService.getByMainId(mainId);
        FinInsDipInfo finInsDipInfo = new FinInsDipInfo();
        if(Objects.nonNull(finInsDipPay)) {
            BeanUtils.copyProperties(finInsDipPay, finInsDipInfo);
            finInsDipInfo.setPayId(finInsDipPay.getId());
        }
        return finInsDipInfo;
    }

    @RequestMapping(value = "/getAllDipAnalysis",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> getAllDipAnalysis(FinInsDipAnalysisCondition finInsDipAnalysisCondition){
        List<Map<String,Object>> hospSettingLists = finHospSettingController.getAreaRenderSelectList();
        Map<Long, String> hospMap = hospSettingLists.stream().collect(Collectors.toMap(m -> MapUtils.getLong(m, "id"), m -> MapUtils.getString(m, "text")));
        PageResponse<Map<String, Object>> retLists = finDipAnalysisService.getAll(finInsDipAnalysisCondition);
        retLists.getRows().stream().forEach(item -> {
            if(Objects.nonNull(item.get("insurancePlanCategory"))){
                //Optional<Map<String, Object>> areaText = hospSettingLists.stream().filter(hsItem -> ((Long)(hsItem.get("id"))).longValue()==(new Long(String.valueOf(item.get("insurancePlanCategory")))).longValue()).findFirst();
                String areaDesc = hospMap.get(new Long(String.valueOf(item.get("insurancePlanCategory"))));
                if(areaDesc!=null && !areaDesc.equals("")){
                    item.put("poolingAreaText",areaDesc);
                }else{
                    item.put("poolingAreaText","");
                }
            }else{
                item.put("poolingAreaText","");
            }
        });

        return retLists;
    }

    @RequestMapping(value = "/saveDipAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDipAnalysis(FinInsDipAnalysis finInsDipAnalysis) {
        return finDipAnalysisService.save(finInsDipAnalysis);
    }

    @RequestMapping(value = "/lastList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    List<Map<String,Object>> lastList() {
        List<Map<String,Object>> l= finDipPayService.lastList();
        l.forEach(m -> m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString())));
        return l;
    }

    @RequestMapping(value = "/lastAnalysisList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    List<Map<String,Object>> lastAnalysisList() {
        List<Map<String,Object>> l= finDipAnalysisService.lastList();
        if(Objects.nonNull(l) && l.size()>0){
            Long hospId = MapUtils.getLong(l.get(0),"hospId");
            FinHospSettingCondition cond = new FinHospSettingCondition();
            cond.setHospId(hospId);
            List<Map<String,Object>> hospSettingLists = finHospSettingController.queryFinHospSetting(cond);
            Map<Long, String> hospMap = hospSettingLists.stream().collect(Collectors.toMap(m -> MapUtils.getLong(m, "id"), m -> MapUtils.getString(m, "text")));
            l.forEach(m ->{
                m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString()));
                if(Objects.nonNull(m.get("insurancePlanCategory"))){
                    String areaDesc = hospMap.get(MapUtils.getLong(m,"insurancePlanCategory"));
                    if(areaDesc!=null && !areaDesc.equals("")){
                        m.put("poolingAreaText",areaDesc);
                    }else{
                        m.put("poolingAreaText","");
                    }
                }else{
                    m.put("poolingAreaText","");
                }
            });
        }
        return l;
    }

    @RequestMapping(value = "/dipAnalysisExportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void dipAnalysisExportExcel(Long monthId,Long hospId) throws IOException {
        FinInsDipAnalysisCondition finInsDipAnalysisCondition = new FinInsDipAnalysisCondition();
        finInsDipAnalysisCondition.setHospId(hospId);
        finInsDipAnalysisCondition.setMonthId(monthId);
        finInsDipAnalysisCondition.setRows(20000);
        List<Map<String,Object>> hospSettingLists = finHospSettingController.getSelectList();
        PageResponse<Map<String, Object>> retLists = finDipAnalysisService.getAll(finInsDipAnalysisCondition);
        retLists.getRows().stream().forEach(item -> {
            if(Objects.nonNull(item.get("insurancePlanCategory"))){
                item.put("poolingAreaText",hospSettingLists.stream().filter(hsItem -> ((Long)(hsItem.get("id"))).longValue()==(new Long(String.valueOf(item.get("insurancePlanCategory")))).longValue()).findFirst().get().get("text"));
            }else{
                item.put("poolingAreaText","");
            }
        });
        String hospName="";
        InstCondition cond_1 = new InstCondition();
        cond_1.setInstId(hospId);
        cond_1.setInstType(InstEnum.HOSP.getEnumCode());
        List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
        if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
            Map resultMap = hosps.get(0);
            hospName = MapUtils.getString(resultMap,"NAME");
        }
        this.returnResult(retLists,"WEB-INF/views/amcs/fin/excelTemplate/","DipAnalysisExportExcel",hospName+"DIP盈亏分析表_"+getDateYMD());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(FinInsDipAnalysis finInsDipAnalysis){
        return finDipAnalysisService.del(finInsDipAnalysis);
    }

    @RequestMapping(value = "/saveDipAnalysisList", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDipAnalysisList(@RequestBody List<FinInsDipAnalysis> finInsDipAnalysisLists){
        finInsDipAnalysisLists.stream().forEach(finInsDipAnalysis -> finDipAnalysisService.save(finInsDipAnalysis));
        return success("操作成功");
    }

    @RequestMapping(value = "/saveLastCommitData", method = RequestMethod.POST)
    @ResponseBody
    public Object saveLastCommitData(@RequestBody FinInsDipAnalysis finInsDipAnalysis){
        try{
            List<FinInsDipAnalysis> lastDatas = finDipAnalysisService.lastCommitData();
            if(Objects.nonNull(lastDatas) && lastDatas.size()>0){
                lastDatas.forEach(item -> {
                    item.setId(null);
                    item.setCreator(ShiroUtils.getId());
                    item.setCreateDate(new Date());
                    item.setModifer(ShiroUtils.getId());
                    item.setModifyDate(new Date());
                    item.setMonthId(finInsDipAnalysis.getMonthId());
                    item.setMainId(finInsDipAnalysis.getMainId());
                    finDipAnalysisService.save(item);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            return fail("导入失败");
        }
        return success("操作成功");
    }
}
