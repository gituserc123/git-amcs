package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsMain;
import com.aier.cloud.api.amcs.fin.domain.FinInsMonth;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.cache.redis.FastJson2JsonRedisSerializer;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMainFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMonthFeignService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-04-03 14:12
 **/

@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsMain")
public class FinInsMainController extends BaseController {

    @Autowired
    InstitutionService instService;
    @Autowired
    FinMainFeignService finMainFeignService;
    @Autowired
    FinMonthFeignService finMonthFeignService;
    @Autowired
    private AdverseEventService adverseEventService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private HospHandleService hospHandleService;
    @Autowired
    FinDictFeignService finDictFeignService;
    @Autowired
    RedisService rs;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    // Spring 会自动装配 RedisTemplate
//    public FinInsMainController(RedisTemplate<String, Object> redisTemplate) {
//        FastJson2JsonRedisSerializer<Object> serializer = new FastJson2JsonRedisSerializer<>(Object.class);
//        // 设置 Fastjson 解析白名单
//        ParserConfig.getGlobalInstance().setSafeMode(false);
//        redisTemplate.setValueSerializer(serializer);
//        redisTemplate.afterPropertiesSet();
//        this.redisTemplate = redisTemplate;
//
//    }


    private static final String INDEX="amcs/fin/business/FinInsMainManage";
    private static final String REPORT_TABS="amcs/fin/business/ReportTabs";
    private static final String FIN_QUERY_LIST="amcs/fin/business/FinQueryList";

    private static final String PROVINCE_AUDIT="amcs/fin/business/provinceAudit";
    private static final String GROUP_MONTH="amcs/fin/business/groupMonth";

    private static final String UN_REPORT="amcs/fin/business/unReport";
    @RequestMapping(value = "/saveToRedis",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object saveToRedis(@RequestBody Map<String,Object> map){

        String mainId=(String)map.get("mainId");
        String formName=(String)map.get("formName");
        rs.set("AMCS:FIN:"+formName+":"+mainId,map,60*60*24*30L);
        return this.success();
    }

    @RequestMapping(value = "/loadFromRedis",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> loadFromRedis(@RequestBody Map<String,Object> map){
        String mainId=(String)map.get("mainId");
        String formName=(String)map.get("formName");
        Object o=ParserConfig.getGlobalInstance();
        ParserConfig.getGlobalInstance().setSafeMode(false);
        return  rs.get("AMCS:FIN:"+formName+":"+mainId);
    }
    @RequestMapping(value = "/reportTabs",method = { RequestMethod.GET, RequestMethod.POST})
    public String reportTabs(Model model,@RequestParam(value="mainId",required = true) Long mainId,@RequestParam(value="viewFlag",required = false) String viewFlag){
        FinInsMain finInsMain=finMainFeignService.getFinInsMain(mainId);
        FinInsMonth finInsMonth=finMonthFeignService.getFinInsMonth(finInsMain.getMonthId());

        model.addAttribute("hospId", finInsMain.getHospId());
        model.addAttribute("mainId", mainId);
        Map<String,String> params=new HashMap<>();
        Institution inst=instService.getByAhisHosp(finInsMain.getHospId().intValue());
        Long insId=inst.getId();
        params.put("instId",insId.toString());
        params.put("hospId",finInsMain.getHospId().toString());
        params.put("monthId",finInsMonth.getId().toString());
        params.put("viewFlag",viewFlag);
        List<Map> list=instService.getHosp(params);
        InstCondition cond=new InstCondition();
        cond.setInstId(insId);
        List<Map> list1=instService.getProvince(cond);
        List<Map> l=list1.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());

        if(list.size()>0) {
            model.addAttribute("hospMap", list.get(0));
        }
        if(l.size()>0){
            model.addAttribute("province",l.get(0).get("name"));
        }

        if(Objects.isNull(viewFlag)){
            model.addAttribute("status", finInsMonth.getStatus());
        }else{
            model.addAttribute("status", 99);
        }
        model.addAttribute("monthId", finInsMonth.getId());
        return REPORT_TABS;
    };


    @RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,@RequestParam(value="monthId",required = false)Long monthId){
        model.addAttribute("hospId", UserContext.getTenantId());
        model.addAttribute("monthId", monthId);
        return INDEX;
    };

    @RequestMapping(value = "/getList",method = { RequestMethod.POST})
    @ResponseBody
    public List<Map<String,Object>> getList(FinInsMainCondition cond){

        List<Map<String,Object>> l= finMainFeignService.getList(cond);
        l.forEach(m ->{
            m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString()));
        });
        return l;
    }
    @RequestMapping(value = "/lastList",method = { RequestMethod.POST})
    @ResponseBody
    List<Map<String,Object>> lastList() {
        List<Map<String,Object>> l= finMainFeignService.lastList();
        l.forEach(m ->{
            m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString()));
        });
        return l;
    }
    @RequestMapping(value = "/getFinInsMain",method = { RequestMethod.POST})
    @ResponseBody
    public FinInsMain getFinInsMain(@RequestParam("id") Long id){
        return finMainFeignService.getFinInsMain(id);
    }

    @RequestMapping(value = "/save",method = { RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody FinInsMain finInsMain){
        if (finMainFeignService.save(finInsMain)){
            return this.success("提交主表成功！");
        }else{
            return this.fail();
        }
    }

    @RequestMapping(value = "/queryPage")
    public String listPage(HttpServletRequest request, @RequestParam(value = "page_type", required = false)  Integer pageType,
                           @RequestParam(value = "type", required = false)  Integer type) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
        if(shiroUser.getIsHosp()&&provinceRoleConfigList.size()==0){
            // 医院人员登录
            request.setAttribute("empType", 3);
            request.setAttribute("insiId", shiroUser.getInstId());
        }else{
            // 集团或省区登录
            request.setAttribute("insiId", shiroUser.getInstId());
            if(shiroUser.getInstId() == 100002L){
                // 集团登录
                request.setAttribute("empType", 1);
            }else{
                // 省区登录
                request.setAttribute("empType", 2);
                request.setAttribute("insiId", provinceRoleConfigList.size()>0?provinceRoleConfigList.get(0).getProvinceId():shiroUser.getInstId());
            }
        }
        request.setAttribute("type", 1);
        return FIN_QUERY_LIST;
    }

    @RequestMapping(value = "/groupMonth")
    public String groupMonth(HttpServletRequest request, @RequestParam(value = "page_type", required = false)  Integer pageType,
                                @RequestParam(value = "type", required = false)  Integer type) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
        if(shiroUser.getInstId() != 100002L){
            // 非集团登录
            throw new BizException("非集团用户不可访问！");
        }else{
            request.setAttribute("empType", 1);
        }
        request.setAttribute("type", 1);
        return GROUP_MONTH;
    }


    @RequestMapping(value = "/unReportIndex")
    public String unReportIndex(HttpServletRequest request, @RequestParam(value = "page_type", required = false)  Integer pageType,
                           @RequestParam(value = "type", required = false)  Integer type) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();

        return UN_REPORT;
    }
    @RequestMapping(value = "/provinceAudit")
    public String provinceAudit(HttpServletRequest request, @RequestParam(value = "page_type", required = false)  Integer pageType,
                           @RequestParam(value = "type", required = false)  Integer type) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
        if(shiroUser.getIsHosp()&&provinceRoleConfigList.size()==0){
            // 医院人员登录
            request.setAttribute("empType", 3);
            request.setAttribute("insiId", shiroUser.getInstId());
        }else{
            // 集团或省区登录
            request.setAttribute("insiId", shiroUser.getInstId());
            if(shiroUser.getInstId() == 100002L){
                // 集团登录
                request.setAttribute("empType", 1);
            }else{
                // 省区登录
                request.setAttribute("empType", 2);
                request.setAttribute("insiId", provinceRoleConfigList.size()>0?provinceRoleConfigList.get(0).getProvinceId():shiroUser.getInstId());
            }
        }
        request.setAttribute("type", 1);
        return PROVINCE_AUDIT;
    }

    @RequestMapping(value = "/queryList", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> queryList(FinInsMainCondition cond) {
        if(!ObjectUtils.isEmpty(cond.getStatusStr())){
            cond.setStatus(Arrays.stream(cond.getStatusStr().split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }
        this.wrapperCond(cond);
        // 数据查询
        PageResponse<Map<String, Object>> retVal = finMainFeignService.queryListByCond(cond);
        List<Map<String, Object>> dataList = retVal.getRows();
        Long retTotal = retVal.getTotal();
        // 返回数据拼装
        List<Map<String, Object>> retDataList = this.handleReturnData(dataList,cond);
        retVal.setRows(retDataList);
        retVal.setTotal(retTotal.intValue());
        return retVal;
    }

    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcel(String paramJson) throws IOException {
        FinInsMainCondition cond = JSON.parseObject(paramJson,FinInsMainCondition.class);
        this.wrapperCond(cond);
        List<Map<String, Object>> dataList = finMainFeignService.queryListByCondNoPage(cond);
        // 返回数据拼装
        List<Map<String, Object>> retDataList = this.handleReturnData(dataList,cond);
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        // 导出功能，集团可以导所有数据，省区和医院导出数据部分脱敏
        if (!(shiroUser.getInstId() == 100002L)) {
            retDataList.stream().forEach(rl -> {
                // 应收医保款期末余额（万元）
                rl.put("receivableBalanceEndPeriod","*");
                // 应收医保款回款率
                rl.put("receivableCollectionRate","*");
                // 医保坏账核销金额（万元）
                rl.put("insuranceBadDebtAmt","*");
                // 医保坏账核销情况原因
                rl.put("insuranceBadDebtCause","*");
                // 慈善核销金额（万元）
                rl.put("charityBadDebtAmt","*");
                // 慈善坏账核销情况原因
                rl.put("charityBadDebtCause","*");
                // 年医保扣罚款情况扣款金额（万元）
                rl.put("penaltyDeductionAmount","*");
                // 年医保扣罚款情况罚款金额（万元）
                rl.put("penaltyFeeAmount","*");
            });
        }
        // 返回excel
        this.returnResult(retDataList,"WEB-INF/views/amcs/fin/excelTemplate/","FinInsList","医保政策_"+getDateYMD());
        //this.returnResult(retDataList,"医保政策_"+getDateYMD());
    }

    public void wrapperCond(FinInsMainCondition cond){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        // 组装查询条件  --begin
        if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
            cond.setHospId(shiroUser.getTenantId());
        } else {
            // 集团或省区登录
            if (shiroUser.getInstId() == 100002L) {
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
                            ArrayList<Long> hospList = Lists.newArrayList();
                            ja.stream().forEach(j -> {
                                JSONObject jo = (JSONObject) j;
                                hospList.add(jo.getLong("ahisHosp"));
                            });
                            cond.setHospId(null);
                            cond.setHospList(hospList);
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
                        ArrayList<Long> hospList = Lists.newArrayList();
                        ja.stream().forEach(j -> {
                            JSONObject jo = (JSONObject) j;
                            hospList.add(jo.getLong("ahisHosp"));
                        });
                        cond.setHospId(null);
                        cond.setHospList(hospList);
                    }
                }
            }
        }
        // 组装查询条件  --end
    }

    public List<Map<String, Object>> handleReturnData(List<Map<String, Object>> dataList,FinInsMainCondition cond){
        // 前置节余/超支数据处理
        if(Objects.nonNull(dataList) && dataList.size()>0){
            List<Map<String, Object>> preBoDataLists = Lists.newArrayList();
            Boolean selectFlag = Boolean.FALSE;
            if(cond.getDipBo() != null){
                if(cond.getDipBo().intValue()==1){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("dipBalanceOrOverspend")!=null && (new BigDecimal(String.valueOf(dl.get("dipBalanceOrOverspend")))).compareTo(BigDecimal.ZERO) > -1).collect(Collectors.toList()));
                }
                if(cond.getDipBo().intValue()==2){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("dipBalanceOrOverspend")!=null && (new BigDecimal(String.valueOf(dl.get("dipBalanceOrOverspend")))).compareTo(BigDecimal.ZERO) == -1).collect(Collectors.toList()));
                }
                selectFlag = Boolean.TRUE;
            }
            if(cond.getDrgBo() != null){
                if(cond.getDrgBo().intValue()==1){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("drgBalanceOrOverspend")!=null && (new BigDecimal(String.valueOf(dl.get("drgBalanceOrOverspend")))).compareTo(BigDecimal.ZERO) > -1).collect(Collectors.toList()));
                }
                if(cond.getDrgBo().intValue()==2){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("drgBalanceOrOverspend")!=null && (new BigDecimal(String.valueOf(dl.get("drgBalanceOrOverspend")))).compareTo(BigDecimal.ZERO) == -1).collect(Collectors.toList()));
                }
                selectFlag = Boolean.TRUE;
            }
            if(cond.getPerBo() != null){
                if(cond.getPerBo().intValue()==1){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("perBalanceOrDeficitAmount")!=null && (new BigDecimal(String.valueOf(dl.get("perBalanceOrDeficitAmount")))).compareTo(BigDecimal.ZERO) > -1).collect(Collectors.toList()));
                }
                if(cond.getPerBo().intValue()==2){
                    preBoDataLists.addAll(dataList.stream().filter(dl -> dl.get("perBalanceOrDeficitAmount")!=null && (new BigDecimal(String.valueOf(dl.get("perBalanceOrDeficitAmount")))).compareTo(BigDecimal.ZERO) == -1).collect(Collectors.toList()));
                }
                selectFlag = Boolean.TRUE;
            }
            if(selectFlag){
                dataList = new ArrayList<>(preBoDataLists);
            }
        }
        if(Objects.nonNull(dataList) && dataList.size()>0){
            Map<String, Map> existHosp = new HashMap<>();
            // 住院结算政策dict
            List<Map> dictSettlementLists = finDictFeignService.getList("SETTLEMENT_POLICY");
            // 卫健委定级dict/医保结算等级dict
            List<Map> dictLevelLists = finDictFeignService.getList("LEVEL");
            // 医保类别dict
            List<Map> dictInsuranceLists = finDictFeignService.getList("INSURANCE_TYPE");
            dataList.stream().forEach(obj -> {
                // 住院结算政策
                if (Objects.isNull(obj.get("hospSettlementPolicy"))) {
                    obj.put("hospSettlementPolicyDesc", "");
                } else {
                    String hospSettlementPolicy = String.valueOf(obj.get("hospSettlementPolicy"));
                    try {
                        JSONArray hospSettlementArr = JSON.parseArray(hospSettlementPolicy);
                        StringBuffer sbfHospSettlement = new StringBuffer();
                        hospSettlementArr.stream().forEach(i -> sbfHospSettlement.append(",").append(dictSettlementLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(i))).findFirst().get().get("valueDesc")));
                        obj.put("hospSettlementPolicyDesc",sbfHospSettlement.substring(1));
                    }catch (Exception e){
                        obj.put("hospSettlementPolicyDesc",hospSettlementPolicy);
                    }
                }
                // 医院类型和省区
                if (Objects.nonNull(obj.get("hospId"))) {
                    System.out.println(obj.get("hospId"));
                    if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
                        obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
                        obj.put("investNature", existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
                        if(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL") != null){
                            obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL"))));
                        }
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
                            if(resultMap.get("EHR_LEVEL")!=null){
                                obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
                            }
                            resultMap.put("PARENT_NAME", l.get(0).get("name"));
                            existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
                        }
                    }
                }
                // 医院类型
                if (Objects.nonNull(obj.get("investNature"))) {
                    if(String.valueOf(obj.get("investNature")).equals("10")){
                        obj.put("investNatureDesc", "上市");
                    }else if(String.valueOf(obj.get("investNature")).equals("11")){
                        obj.put("investNatureDesc", "合伙");
                    }else{
                        obj.put("investNatureDesc", "");
                    }
                } else {
                    obj.put("investNatureDesc", "");
                }
                // 卫健委定级
                if (Objects.nonNull(obj.get("healthCommissionLevel"))) {
                    Optional<Map> hc = dictLevelLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(obj.get("healthCommissionLevel")))).findFirst();
                    if(hc.isPresent()){
                        obj.put("healthCommissionLevelDesc", hc.get().get("valueDesc"));
                    }else{
                        obj.put("healthCommissionLevelDesc", "");
                    }
                } else {
                    obj.put("healthCommissionLevelDesc", "");
                }
                // 医保结算等级
                if (Objects.nonNull(obj.get("insuranceSettlementLevel"))) {
                    Optional<Map> hc = dictLevelLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(obj.get("insuranceSettlementLevel")))).findFirst();
                    if(hc.isPresent()){
                        obj.put("insuranceSettlementLevelDesc", hc.get().get("valueDesc"));
                    }else{
                        obj.put("insuranceSettlementLevelDesc", "");
                    }
                } else {
                    obj.put("insuranceSettlementLevelDesc", "");
                }
                // 医保类别
                if (Objects.nonNull(obj.get("insuranceType"))) {
                    Optional<Map> ism = dictInsuranceLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(obj.get("insuranceType")))).findFirst();
                    if(ism.isPresent()){
                        obj.put("insuranceTypeDesc", ism.get().get("valueDesc"));
                    }else{
                        obj.put("insuranceTypeDesc", "");
                    }
                } else {
                    obj.put("insuranceTypeDesc", "");
                }
                // 是否医保局备案
                if (Objects.nonNull(obj.get("isAgreement"))) {
                    if(MapUtils.getString(obj,"isAgreement").equals("1")){
                        obj.put("isAgreementDesc", "是");
                    }else if(MapUtils.getString(obj,"isAgreement").equals("2")){
                        obj.put("isAgreementDesc", "否");
                    }else{
                        obj.put("isAgreementDesc", "");
                    }
                }
                // 是否优免行为
                if (Objects.nonNull(obj.get("existenceAgreedStandards"))) {
                    if(MapUtils.getString(obj,"existenceAgreedStandards").equals("1")){
                        obj.put("existenceAgreedStandardsDesc", "是");
                    }else if(MapUtils.getString(obj,"existenceAgreedStandards").equals("2")){
                        obj.put("existenceAgreedStandardsDesc", "否");
                    }else{
                        obj.put("existenceAgreedStandardsDesc", "");
                    }
                }
                // 备案医保局是否出具书面回执(1是，0否）
                if (Objects.nonNull(obj.get("isWrittenAck"))) {
                    if(MapUtils.getString(obj,"isWrittenAck").equals("1")){
                        obj.put("isWrittenAckDesc", "是");
                    }else if(MapUtils.getString(obj,"isWrittenAck").equals("0")){
                        obj.put("isWrittenAckDesc", "否");
                    }else{
                        obj.put("isWrittenAckDesc", "");
                    }
                }
                // 上报状态
                if (Objects.nonNull(obj.get("status"))) {
                    if(MapUtils.getString(obj,"status").equals("1")){
                        obj.put("statusDesc", "上报中");
                    }else if(MapUtils.getString(obj,"status").equals("5")){
                        obj.put("statusDesc", "省区审核");
                    }else if(MapUtils.getString(obj,"status").equals("6")){
                        obj.put("statusDesc", "省区审核完成");
                    }else{
                        obj.put("statusDesc", "退回");
                    }
                }
                // 按项目付费表,总额预付表,DIP付费表,DRG付费表是否向医院CEO、院长、临床科室等书面反馈
                List<String> ftmKeys = obj.keySet().stream().collect(Collectors.toList());
                ftmKeys.stream().filter(entry -> entry.contains("FeedbackToManagement"))
                        .forEach(ftm -> {
                            if(Objects.nonNull(obj.get(ftm))){
                                if(MapUtils.getString(obj,ftm).equals("1")){
                                    obj.put(ftm+"Desc", "是");
                                }else if(MapUtils.getString(obj,ftm).equals("0")){
                                    obj.put(ftm+"Desc", "否");
                                }else{
                                    obj.put(ftm+"Desc", "");
                                }
                            }
                        });
            });
        }
        return dataList;
    }

    public void returnResult(List<Map<String, Object>> dataList,String tmpPath,String docPreSuffix,String docName) throws IOException {
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
        excelWriter.fill(dataList,fillConfig,writeSheet);

        excelWriter.finish();
    }
    public static String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

    /*
    * 这个方法可改造为动态生成excel的列
    */
    public void returnResult(List<Map<String, Object>> dataList, String docName) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileRealName = URLEncoder.encode(docName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileRealName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // Extracting headers dynamically from the first map in the dataList
        Map<String, Object> firstDataMap = dataList.get(0);
        Set<String> headers = firstDataMap.keySet();

        // Writing headers to Excel
        List<List<String>> headerData = new ArrayList<>();
        headerData.add(new ArrayList<>(headers));
        excelWriter.write(headerData, writeSheet);

        // Writing dataList content to Excel
        List<List<Object>> excelData = new ArrayList<>();
        for (Map<String, Object> dataMap : dataList) {
            List<Object> rowData = new ArrayList<>();
            for (String header : headers) {
                if(Objects.nonNull(dataMap.get(header))){
                    rowData.add(String.valueOf(dataMap.get(header)));
                }else{
                    rowData.add(dataMap.get(header));
                }
            }
            excelData.add(rowData);
        }
        excelWriter.write(excelData, writeSheet);

        excelWriter.finish();
    }

}
