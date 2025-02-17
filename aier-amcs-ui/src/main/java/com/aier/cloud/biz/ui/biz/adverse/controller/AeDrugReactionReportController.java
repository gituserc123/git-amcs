package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.domain.AeDrugReactDrugs;
import com.aier.cloud.api.amcs.adverse.domain.AeDrugReactions;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.DrugReactDrugsCondition;
import com.aier.cloud.api.amcs.condition.DrugReactionsCondition;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.based.DcgDrugsCondition;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseDictService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeDrugReactDrugsService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeDrugReactionReportService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.common.feign.DictService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/amcs/adverse/drugreaction")
public class AeDrugReactionReportController extends BaseController {

    @Autowired
    private AeDrugReactionReportService aeDrugReactionReportService;

    @Autowired
    private AeDrugReactDrugsService aeDrugReactDrugsService;

    @Autowired
    private AdverseEventService adverseEventService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private HospHandleService hospHandleService;

    @Autowired
    private DictService dictService;

    @Autowired
    AdverseDictService adverseDictService;

    @Autowired
    private AdverseDictService aes;

    @Autowired
    RedisService rs;

    private final static String DRUG_REACTION_REPORT_PAGE= "amcs/adverseEvent/eventManage/drugReactionList";
    private final static String DRUG_REACTION_REPORT_INFO= "amcs/adverseEvent/eventManage/drugReactionDrugs";
    private final static String paramRoleName="医务管理";
    private final static String paramRoleName_1="药学管理";
    private final static String sensitiveRoleName_1="敏感信息查看";
    private final static String sensitiveRoleName_2="敏感信息导出";

    //不良反应类型
    private static Map<String , String> mapTypeLevel = new HashMap<String , String>(){{
        put("1", "新的");
        put("2", "严重");
        put("3", "一般");
    }};
    //是否
    private static Map<String , String> mapIsNot = new HashMap<String , String>(){{
        put("1", "是");
        put("2", "否");
    }};
    //事件的结果
    private static Map<String , String> mapAdverseResults = new HashMap<String , String>(){{
        put("1", "治愈");
        put("2", "好转");
        put("3", "未好转");
        put("4", "有后遗症");
        put("5", "死亡");
    }};
    //类似不良反应
    private static Map<String , String> mapSimilarReact = new HashMap<String , String>(){{
        put("1", "有");
        put("2", "无");
        put("3", "不详");
    }};
    //评价
    private static Map<String , String> mapEvaluation = new HashMap<String , String>(){{
        put("1", "肯定");
        put("2", "很可能");
        put("3", "可能");
        put("4", "可能无关");
        put("5", "待评价");
        put("6", "无法评价");
    }};

    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        Integer empType = this.getEmpType();
        request.setAttribute("empType", empType);
        request.setAttribute("instId", ShiroUtils.getInstId());
        if(ShiroUtils.hasRole(paramRoleName) || ShiroUtils.hasRole(paramRoleName_1)){
            request.setAttribute("reactStatus", 1);
        }else{
            request.setAttribute("reactStatus", 2);
        }
        return DRUG_REACTION_REPORT_PAGE;
    }

    @RequestMapping(value = "/findList", method = { RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> findList(DrugReactionsCondition cond){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        boolean isRetNull=false;
        // 组装查询条件
        this.wrapperCond(cond,shiroUser,provinceRoleConfigList,isRetNull);
        if(isRetNull){
            return new PageResponse<>();
        }
        // 组装查询条件 --end
        PageResponse<Map<String, Object>> response = aeDrugReactionReportService.getAll(cond);
        List<Map<String, Object>> dataList = response.getRows();
        // 组装返回数据
        this.wrapperCommonData(dataList);
        return response;
    }

    @RequestMapping(value = "/drugReaction", method = { RequestMethod.GET, RequestMethod.POST })
    public String drugReaction(Map<String, Object> map,Long reactId,@RequestParam(value = "opr", required = false) String opr){
        if(reactId!=null){
            map.put("reactId",reactId);
            AeDrugReactions aeDrugReactions = aeDrugReactionReportService.getAeDrugReactions(reactId);
            map.put("react",aeDrugReactions);
        }else{
            AeDrugReactions aeDrugReactions = new AeDrugReactions();
            aeDrugReactions.setHospName(ShiroUtils.getInstName());
            map.put("reactId","1");
            map.put("react",aeDrugReactions);
        }
        map.put("hospId", ShiroUtils.getTenantId());
        map.put("instId", ShiroUtils.getInstId());
        Integer empType = this.getEmpType();
        map.put("empType", empType);
        if(Objects.nonNull(opr) && opr.equals("view")){
            // "查看"模式下，新增和修改按钮不可见
            map.put("empType", 5);
        }
        return DRUG_REACTION_REPORT_INFO;
    }

    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Map<String, Object> save(@RequestBody AeDrugReactions aeDrugReactions) {
        Map<String, Object> result = Maps.newHashMap();
        if(checkPreSave(aeDrugReactions,result)){
            aeDrugReactions.setReactStatus(1);
            result = aeDrugReactionReportService.save(aeDrugReactions);
            if(result.get("code")!=null && String.valueOf(result.get("code")).equals("200")){
                Long reactId = Long.parseLong(String.valueOf(result.get("drugReactionId")));
                aeDrugReactions.getSmDrugReactDrugs().forEach(item -> {
                    item.setReactId(reactId);
                    aeDrugReactDrugsService.save(item);
                });
                aeDrugReactions.getAmDrugReactDrugs().forEach(item -> {
                    item.setReactId(reactId);
                    aeDrugReactDrugsService.save(item);
                });
                result.put("code","200");
                result.put("msg","保存成功！");
            }else{
                result.put("code","500");
                result.put("msg","保存失败！");
            }
        }
        return result;
    }

    /**
     * 保存前判断，如患者姓名,怀疑药品通用名,用药开始时间与之前提交的不良反应相同，视为重复提交，不予通过，并提示
     * @param
     * @return
     */
    private boolean checkPreSave(AeDrugReactions aeDrugReactions,Map<String, Object> result){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(Objects.nonNull(aeDrugReactions) && aeDrugReactions.getSmDrugReactDrugs().size()>0){
            DrugReactionsCondition dc = new DrugReactionsCondition();
            dc.setPatientName(aeDrugReactions.getPatientName());
            // 只有正常的不良事件才进行重复判断
            dc.setReactStatus("1");
            for(AeDrugReactDrugs sm : aeDrugReactions.getSmDrugReactDrugs()){
                dc.setCommonName(sm.getCommonName());
                dc.setMedicationBeginTimeStr(sdf.format(sm.getMedicationBeginTime()));
                List<Map<String, Object>> smDrugs = aeDrugReactionReportService.getAllEntity(dc);
                for(Map<String, Object> smMap : smDrugs){
                    if(aeDrugReactions.getId()!=null){
                        if(smMap.get("id").toString().equals(aeDrugReactions.getId().toString())){
                        }else{
                            result.put("code","500");
                            result.put("msg","该不良反应已由" + smMap.get("creatorName") + "上报，请勿重复上报！");
                            return false;
                        }
                    }else{
                        result.put("code","500");
                        result.put("msg","该不良反应已由" + smMap.get("creatorName") + "上报，请勿重复上报！");
                        return false;
                    }
                }
            }
        }else{
            result.put("code","500");
            result.put("msg","必须提交至少一条怀疑药品记录！");
            return false;
        }
        result.put("code","200");
        result.put("msg","不存在重复");
        return true;
    }

    @RequestMapping(value = "/findDrugsList", method = { RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> findDrugsList(DrugReactDrugsCondition cond){
        List<Map<String, Object>> response = aeDrugReactDrugsService.getAllEntity(cond);
        List<Map> reactDrugFreq = adverseDictService.getList("react_drug_freq",null);
        List<Map> reactUsageUnit = adverseDictService.getList("react_usage_unit",null);
        Map<String,String> freqCodeToDesc = reactDrugFreq.stream().collect(
                Collectors.toMap(m -> MapUtils.getString(m, "valueCode"), m -> MapUtils.getString(m, "valueDesc")));
        Map<String,String> usageUnitToDesc = reactUsageUnit.stream().collect(
                Collectors.toMap(m -> MapUtils.getString(m, "valueCode"), m -> MapUtils.getString(m, "valueDesc")));
        response.stream().forEach(item -> {
            item.put("defaultFreqDesc",freqCodeToDesc.get(String.valueOf(item.get("defaultFreq"))));
            item.put("usageUnitDesc",usageUnitToDesc.get(String.valueOf(item.get("usageUnit"))));
        });
        return response;
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public Object delete(Long id){
        return aeDrugReactDrugsService.delete(id);
    }

    @RequestMapping(value = "/getAllDrugs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    protected List<Map<String, Object>> getAllDrugs(DcgDrugsCondition dcgDrugsCondition){
        List<Map<String, Object>> drugs;
        Object o= ParserConfig.getGlobalInstance();
        ParserConfig.getGlobalInstance().setSafeMode(false);
        drugs = rs.get("AMCS:BASE:FEIGN:HISDRUGS:GRID");
        if (drugs == null) {
            PageResponse<Map<String, Object>> dcgDrugPage;
            int page = 1;
            drugs = Lists.newArrayList();
            do {
                dcgDrugsCondition.setPage(page);
                dcgDrugsCondition.setRows(1000);
                dcgDrugPage = dictService.queryDcgDrugs(dcgDrugsCondition);
                drugs.addAll(dcgDrugPage.getRows());
                page = page + 1;
            } while (dcgDrugPage.getRows() != null && dcgDrugPage.getRows().size() > 0);
            rs.set("AMCS:BASE:FEIGN:HISDRUGS:GRID", drugs, 60 * 60 * 6L);
        }
        return drugs;
    }

    private int getEmpType() {
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        if (!ShiroUtils.getIsHosp() || provinceRoleConfigList.size() > 0) {
            // 集团或省区登录
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
                return Constants.Group;
            } else {
                // 省区登录
                return Constants.Province;
            }
        }
        return Constants.Hosp;
    }

    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcel(String paramJson) throws IOException {
        DrugReactionsCondition cond = JSON.parseObject(paramJson,DrugReactionsCondition.class);
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        boolean isRetNull=false;
        // 组装查询条件
        this.wrapperCond(cond,shiroUser,provinceRoleConfigList,isRetNull);
        // 组装查询条件 --end
        if(isRetNull){
            this.returnResult(Lists.newArrayList(),"WEB-INF/views/amcs/adverseEvent/excelTemplate/","adverseDetailList","药物不良反应_"+getDateYMD());
        }else{
            List<Map<String, Object>> dataList = aeDrugReactionReportService.getAllEntity(cond);
            // 组装返回数据
            this.wrapperCommonData(dataList);
            this.returnResult(dataList,"WEB-INF/views/amcs/adverseEvent/excelTemplate/","aeDrugReactList","药物不良反应_"+getDateYMD());
        }
    }

    @RequestMapping(value = "/updateReactStatus", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Map<String, Object> updateReactStatus(Long reactId){
        Map<String, Object> result;
        AeDrugReactions aeDrugReactions = new AeDrugReactions();
        aeDrugReactions.setId(reactId);
        aeDrugReactions.setReactStatus(2);
        result = aeDrugReactionReportService.save(aeDrugReactions);
        return result;
    }

    private void wrapperCond(DrugReactionsCondition cond,ShiroUser shiroUser,List<ProvinceRoleConfig> provinceRoleConfigList,boolean isRetNull){
        if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
            // 医院人员登录,如果是"医务管理"或者"药学管理"角色,能看全院记录;否则只能看到院内自己填写的记录
            if(ShiroUtils.hasRole(paramRoleName) || ShiroUtils.hasRole(paramRoleName_1)){
                cond.setHospId(shiroUser.getTenantId());
            }else{
                cond.setHospId(shiroUser.getTenantId());
                cond.setCreator(shiroUser.getId());
            }
        } else {
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
                if (cond.getProvince() != null && cond.getProvince() > 0) {
                    if (cond.getHospId() != null && cond.getHospId() > 0) {
                        Institution inst = institutionService.getById(cond.getHospId());
                        cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                        cond.setHospList(null);
                    } else {
                        Object instList = hospHandleService.allHospFromParent(cond.getProvince());
                        if (instList != null) {
                            JSONArray ja = (JSONArray) instList;
                            ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                            ja.stream().forEach(j -> {
                                JSONObject jo = (JSONObject) j;
                                hospList.add(jo.getLong("ahisHosp"));
                            });
                            cond.setHospId(null);
                            // 如果hospList为空，说明当前机构下没有医院，直接返回
                            if (hospList.size() > 0) {
                                cond.setHospList(hospList);
                            } else {
                                isRetNull=true;
                            }
                        }
                    }
                }
            } else {
                // 省区登录
                if (cond.getHospId() != null && cond.getHospId() > 0) {
                    Institution inst = institutionService.getById(cond.getHospId());
                    cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                    cond.setHospList(null);
                } else {
                    Object instList;
                    if (provinceRoleConfigList.size() > 0) {
                        instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
                    } else {
                        instList = hospHandleService.allHospFromParent(shiroUser.getInstId());
                    }
                    if (instList != null) {
                        JSONArray ja = (JSONArray) instList;
                        ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                        ja.stream().forEach(j -> {
                            JSONObject jo = (JSONObject) j;
                            hospList.add(jo.getLong("ahisHosp"));
                        });
                        cond.setHospId(null);
                        // 如果hospList为空，说明当前机构下没有医院，直接返回
                        if (hospList.size() > 0) {
                            cond.setHospList(hospList);
                        } else {
                            isRetNull=true;
                        }
                    }
                }
            }
        }
        /** inst_id=>hosp_id */
        if (Objects.nonNull(cond.getHospId()) && cond.getHospId() > 9999) {
            Institution inst = institutionService.getById(cond.getHospId());
            if (!ObjectUtils.isEmpty(inst)) {
                cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
            }
        }
        // 正常/取消状态
        if(cond.getReactStatus()==null || cond.getReactStatus().equals("")){
            cond.setReactStatus("1");
        }
    }

    private void returnResult(List<Map<String, Object>> retVal,String tmpPath,String docPreSuffix,String docName) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String templateAbsolutePath = Strings.concat(tmpPath, docPreSuffix, ".xlsx");
        response.reset();
        ClassPathResource config = new ClassPathResource(templateAbsolutePath);
        if(!config.exists()) {
            throw new BizException("没有找到模版");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileRealName = URLEncoder.encode(docName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileRealName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(config.getInputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(false).build();
        //System.out.println(paramJson);
        excelWriter.fill(retVal,fillConfig,writeSheet);

        excelWriter.finish();
    }

    private void wrapperCommonData(List<Map<String, Object>> dataList){
        if (Objects.nonNull(dataList) && dataList.size() > 0) {
            Map<String, Map> existHosp = new HashMap<>();
            // 怀疑药品或者生产厂家模糊查询后，组装怀疑药品和生产厂家数据
            List<Long> idLists = dataList.stream().map(obj -> Long.parseLong(String.valueOf(obj.get("id")))).collect(Collectors.toList());
            DrugReactDrugsCondition drugReactDrugsCondition = new DrugReactDrugsCondition();
            drugReactDrugsCondition.setReactIdList(idLists);
            List<Map<String, Object>> reactDrugLists = aeDrugReactDrugsService.getAllEntity(drugReactDrugsCondition);
            Map<String,Integer> snMap = new HashMap<>();
            snMap.put("snNum",1);
            // 不良反应名称码表
            List<Map> listAeReactName =  aes.getList("adverse_reactions_name",null);
            listAeReactName.sort(Comparator.comparing((Map h) -> (Integer.valueOf(String.valueOf(h.get("valueCode"))))));
            Map<String,Object> reactNameMapToDesc = listAeReactName.stream().collect(
                    Collectors.toMap(m -> MapUtils.getString(m, "valueCode"), m -> MapUtils.getObject(m, "valueDesc")));
            dataList.stream().forEach(obj -> {
                obj.put("snNum", snMap.get("snNum")); // 序列号
                // 医院类型和省区
                if (Objects.nonNull(obj.get("hospId"))) {
                    if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
                        obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
                        obj.put("investNature", existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
                    } else {
                        InstCondition cond_1 = new InstCondition();
                        cond_1.setInstId(Long.parseLong(String.valueOf(obj.get("hospId"))));
                        cond_1.setInstType(InstEnum.HOSP.getEnumCode());
                        List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
                        if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
                            Map resultMap = hosps.get(0);
                            /** 查询一级省区 */
                            InstCondition cond_2 = new InstCondition();
                            cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
                            List<Map> list = institutionService.getProvince(cond_2);
                            List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
                            obj.put("hospParent", l.get(0).get("name"));
                            obj.put("investNature", resultMap.get("INVEST_NATURE"));
                            resultMap.put("PARENT_NAME", l.get(0).get("name"));
                            existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
                        }
                    }
                }
                // 不良反应类型
                if (Objects.nonNull(obj.get("typeLevel"))){
                    obj.put("typeLevelDesc",mapTypeLevel.get(String.valueOf(obj.get("typeLevel"))));
                }
                // 不良反应名称
                if(Objects.nonNull(obj.get("adverseReactionsCode"))){
                    if(String.valueOf(obj.get("adverseReactionsCode")).equals("51")){
                        // 自由输入
                        obj.put("adverseReactionsNameDesc",obj.get("adverseReactionsName"));
                    }else{
                        obj.put("adverseReactionsNameDesc",reactNameMapToDesc.get(String.valueOf(obj.get("adverseReactionsCode"))));
                    }
                }else{
                    obj.put("adverseReactionsNameDesc",obj.get("adverseReactionsName"));
                }
                // 是否构成纠纷
                if (Objects.nonNull(obj.get("dispute"))){
                    obj.put("disputeDesc",mapIsNot.get(String.valueOf(obj.get("dispute"))));
                }
                // 是否上报国家
                if (Objects.nonNull(obj.get("reportingCountries"))){
                    obj.put("reportingCountries1","是");
                }else{
                    obj.put("reportingCountries1","否");
                }
                // 事件的结果
                if (Objects.nonNull(obj.get("adverseResults"))){
                    obj.put("adverseResultsDesc",mapAdverseResults.get(String.valueOf(obj.get("adverseResults"))));
                }
                // 国内有无类似不良反应
                if (Objects.nonNull(obj.get("similarInChina"))){
                    obj.put("similarInChinaDesc",mapSimilarReact.get(String.valueOf(obj.get("similarInChina"))));
                }
                // 国外有无类似不良反应
                if (Objects.nonNull(obj.get("similarAbroad"))){
                    obj.put("similarAbroadDesc",mapSimilarReact.get(String.valueOf(obj.get("similarAbroad"))));
                }
                // 医院评价
                if (Objects.nonNull(obj.get("hospitalEvaluation"))){
                    obj.put("hospitalEvaluationDesc",mapEvaluation.get(String.valueOf(obj.get("hospitalEvaluation"))));
                }
                // 报告人评价
                if (Objects.nonNull(obj.get("reportEvaluation"))){
                    obj.put("reportEvaluationDesc",mapEvaluation.get(String.valueOf(obj.get("reportEvaluation"))));
                }
                // 表现
                String performanceDesc="";
                if(Objects.nonNull(obj.get("performance"))){
                    performanceDesc = performanceDesc + String.valueOf(obj.get("performance"));
                }
                if(Objects.nonNull(obj.get("causeOfDeath"))){
                    performanceDesc = performanceDesc + String.valueOf(obj.get("causeOfDeath"));
                }
                if(Objects.nonNull(obj.get("deathDate"))){
                    performanceDesc = performanceDesc + String.valueOf(obj.get("deathDate")).substring(0,10);
                }
                obj.put("performanceDesc",performanceDesc);
                snMap.put("snNum",snMap.get("snNum")+1);
                try{
                    String strSmCommonNames = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("1"))
                            .map(rfl -> String.valueOf(rfl.get("commonName"))).collect(Collectors.joining(","));
                    String strSmManufacturers = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("1"))
                            .map(rfl -> String.valueOf(rfl.get("manufacturer"))).collect(Collectors.joining(","));
                    String strAmCommonNames = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("2"))
                            .map(rfl -> String.valueOf(rfl.get("commonName"))).collect(Collectors.joining(","));
                    String strAmManufacturers = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("2"))
                            .map(rfl -> String.valueOf(rfl.get("manufacturer"))).collect(Collectors.joining(","));
                    String strSmBatchNumbers = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("1"))
                            .map(rfl -> String.valueOf(rfl.get("batchNumber"))).collect(Collectors.joining(","));
                    String strAmBatchNumbers = reactDrugLists.stream().filter(rl -> String.valueOf(rl.get("reactId")).equals(String.valueOf(obj.get("id"))) && String.valueOf(rl.get("typeDrug")).equals("2"))
                            .map(rfl -> String.valueOf(rfl.get("batchNumber"))).collect(Collectors.joining(","));
                    if(Objects.nonNull(strSmCommonNames) && !strSmCommonNames.equals("")){
                        obj.put("smCommonNames", strSmCommonNames);
                    }else {
                        obj.put("smCommonNames", "");
                    }
                    obj.put("smManufacturers", strSmManufacturers);
                    if(Objects.nonNull(strAmCommonNames) && !strAmCommonNames.equals("")){
                        obj.put("amCommonNames", strAmCommonNames);
                    }else {
                        obj.put("amCommonNames", "");
                    }
                    obj.put("amManufacturers", strAmManufacturers);
                    obj.put("smBatchNumbers", strSmBatchNumbers);
                    obj.put("amBatchNumbers", strAmBatchNumbers);
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 电话号码脱敏
                if(!(ShiroUtils.hasRole(sensitiveRoleName_1) || ShiroUtils.hasRole(sensitiveRoleName_2))){
                    obj.put("telephone",maskPhoneNumber(MapUtils.getString(obj,"telephone")));
                }
            });
        }
    }

    private static String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

    /**
     * 电话号码脱敏
     * 例如：138****1234
     *
     * @param phoneNumber 待脱敏的电话号码
     * @return 脱敏后的电话号码
     */
    public static String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 11) {
            return "";
        }

        // 获取电话号码前3位和后4位，其他位置用*替代
        String prefix = phoneNumber.substring(0, 3);  // 获取前三位
        String suffix = phoneNumber.substring(phoneNumber.length() - 4);  // 获取后四位

        // 构造脱敏后的电话号码
        return prefix + "****" + suffix;
    }
}
