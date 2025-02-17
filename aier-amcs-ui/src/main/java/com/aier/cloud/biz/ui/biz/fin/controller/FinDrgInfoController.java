package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsDrgAnalysisCondition;
import com.aier.cloud.api.amcs.fin.domain.*;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDrgAnalysisService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDrgPayService;
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
@RequestMapping("/ui/service/biz/amcs/fin/finDrgInfo")
public class FinDrgInfoController extends BaseFinController{

    @Autowired
    private FinDrgAnalysisService finDrgAnalysisService;
    @Autowired
    private FinDrgPayService finDrgPayService;
    @Autowired
    private FinHospSettingController finHospSettingController;
    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value = "/getFinInsDrgPay")
    public @ResponseBody
    Map<String, Object> getFinInsDrgPay(@RequestParam("id") Long id) {
        return finDrgPayService.getFinInsDrgPay(id);
    }

    @RequestMapping(value = "/getFinInsDrgAnalysis")
    public @ResponseBody
    Map<String, Object> getFinInsDrgAnalysis(@RequestParam("id") Long id) {
        return finDrgAnalysisService.getFinInsDrgAnalysis(id);
    }

    @RequestMapping(value ="/saveFinInsDrgInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody FinInsDrgInfo finInsDrgInfo){
        FinInsDrgPay finInsDrgPay = new FinInsDrgPay();
        BeanUtils.copyProperties(finInsDrgInfo,finInsDrgPay);
        finInsDrgPay.setId(finInsDrgInfo.getPayId());
        boolean retP = finDrgPayService.save(finInsDrgPay);
        return retP;
    }

    @RequestMapping(value ="/getByMainId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public FinInsDrgInfo getByMainId(Long mainId){
        FinInsDrgPay finInsDrgPay = finDrgPayService.getByMainId(mainId);
        FinInsDrgInfo finInsDrgInfo = new FinInsDrgInfo();
        if(Objects.nonNull(finInsDrgPay)) {
            BeanUtils.copyProperties(finInsDrgPay, finInsDrgInfo);
            finInsDrgInfo.setPayId(finInsDrgPay.getId());
        }
        return finInsDrgInfo;
    }

    @RequestMapping(value = "/getAllDrgAnalysis",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> getAllDrgAnalysis(FinInsDrgAnalysisCondition finInsDrgAnalysisCondition){
        List<Map<String,Object>> hospSettingLists = finHospSettingController.getAreaRenderSelectList();
        Map<Long, String> hospMap = hospSettingLists.stream().collect(Collectors.toMap(m -> MapUtils.getLong(m, "id"), m -> MapUtils.getString(m, "text")));
        PageResponse<Map<String, Object>> retLists = finDrgAnalysisService.getAll(finInsDrgAnalysisCondition);
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

    @RequestMapping(value = "/saveDrgAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDrgAnalysis(FinInsDrgAnalysis finInsDrgAnalysis) {
        return finDrgAnalysisService.save(finInsDrgAnalysis);
    }

    @RequestMapping(value = "/lastList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    List<Map<String,Object>> lastList() {
        List<Map<String,Object>> l= finDrgPayService.lastList();
        l.forEach(m -> m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString())));
        return l;
    }

    @RequestMapping(value = "/lastAnalysisList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    List<Map<String,Object>> lastAnalysisList() {
        List<Map<String,Object>> l= finDrgAnalysisService.lastList();
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

    @RequestMapping(value = "/drgAnalysisExportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void drgAnalysisExportExcel(Long monthId,Long hospId) throws IOException {
        FinInsDrgAnalysisCondition finInsDrgAnalysisCondition = new FinInsDrgAnalysisCondition();
        finInsDrgAnalysisCondition.setHospId(hospId);
        finInsDrgAnalysisCondition.setMonthId(monthId);
        finInsDrgAnalysisCondition.setRows(20000);
        List<Map<String,Object>> hospSettingLists = finHospSettingController.getSelectList();
        PageResponse<Map<String, Object>> retLists = finDrgAnalysisService.getAll(finInsDrgAnalysisCondition);
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
        this.returnResult(retLists,"WEB-INF/views/amcs/fin/excelTemplate/","DrgAnalysisExportExcel",hospName+"DRG盈亏分析表_"+getDateYMD());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(FinInsDrgAnalysis finInsDrgAnalysis){
        return finDrgAnalysisService.del(finInsDrgAnalysis);
    }

    @RequestMapping(value = "/saveDrgAnalysisList", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDrgAnalysisList(@RequestBody List<FinInsDrgAnalysis> finInsDrgAnalysisLists){
        finInsDrgAnalysisLists.stream().forEach(finInsDrgAnalysis -> finDrgAnalysisService.save(finInsDrgAnalysis));
        return success("操作成功");
    }

    @RequestMapping(value = "/saveLastCommitData", method = RequestMethod.POST)
    @ResponseBody
    public Object saveLastCommitData(@RequestBody FinInsDrgAnalysis finInsDrgAnalysis){
        try{
            List<FinInsDrgAnalysis> lastDatas = finDrgAnalysisService.lastCommitData();
            if(Objects.nonNull(lastDatas) && lastDatas.size()>0){
                lastDatas.forEach(item -> {
                    item.setId(null);
                    item.setCreator(ShiroUtils.getId());
                    item.setCreateDate(new Date());
                    item.setModifer(ShiroUtils.getId());
                    item.setModifyDate(new Date());
                    item.setMonthId(finInsDrgAnalysis.getMonthId());
                    item.setMainId(finInsDrgAnalysis.getMainId());
                    finDrgAnalysisService.save(item);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            return fail("导入失败");
        }
        return success("操作成功");
    }
}
