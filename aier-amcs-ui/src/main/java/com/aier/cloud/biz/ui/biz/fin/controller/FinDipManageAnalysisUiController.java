package com.aier.cloud.biz.ui.biz.fin.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.fin.condition.FinMaDipDtlCondition;
import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import com.aier.cloud.api.amcs.fin.domain.FinMaDipDtl;
import com.aier.cloud.api.amcs.fin.domain.FinMaMain;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.api.amcs.fin.enums.FinMaMedicalItemEnum;
import com.aier.cloud.api.amcs.utils.TimePeriodUtil;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMaDipDtlFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMaMainFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMainFeignService;
import com.aier.cloud.biz.ui.biz.fin.utils.MergeStrategy;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/amcs/fin/dip/ma")
public class FinDipManageAnalysisUiController extends BaseController {

    @Autowired
    private FinMaMainFeignService finMaMainFeignService;

    @Autowired
    private FinMaDipDtlFeignService finMaDipDtlFeignService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private FinMainFeignService finMainFeignService;

    @Autowired
    private FinDictFeignService finDictFeignService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HospHandleService hospHandleService;

    @Autowired
    private AdverseEventService adverseEventService;

    private static final String DIP_MA_PAGE = "amcs/fin/business/manageAnalysis/finDipMaPage";

    private static final String DIP_STAT_MA_PAGE = "amcs/fin/business/manageAnalysis/finDipStatMaPage";

    private static Integer splitNum = 999;

    // 自定义计算最大值函数式接口
    private Function<List<Map<String,Object>>, Map<String,Object>> custCommMaxFunc = (dataList) -> {
        // 首先找出最大的"singleEye"值
        Optional<BigDecimal> maxSingleCount = dataList.stream().map(map -> NumberUtil.toBigDecimal(MapUtils.getString(map,"singleEye"))).max(BigDecimal::compareTo);
        // 然后过滤出所有"singleEye"等于最大值的记录
        List<Map<String, Object>> maxSingleCountMaps = dataList.stream().filter(map -> NumberUtil.toBigDecimal(MapUtils.getString(map,"singleEye")).compareTo(maxSingleCount.orElse(BigDecimal.ZERO)) == 0)
                .collect(Collectors.toList());
        return maxSingleCountMaps.size()>0?maxSingleCountMaps.get(0):Maps.newHashMap();
    };

    // 自定义计算最小值函数式接口
    private Function<List<Map<String,Object>>, Map<String,Object>> custCommMinFunc = (dataList) -> {
        // 首先找出最大的"singleEye"值
        Optional<BigDecimal> maxSingleCount = dataList.stream().map(map -> NumberUtil.toBigDecimal(MapUtils.getString(map,"singleEye"))).min(BigDecimal::compareTo);
        // 然后过滤出所有"singleEye"等于最小值的记录
        List<Map<String, Object>> minSingleCountMaps = dataList.stream().filter(map -> NumberUtil.toBigDecimal(MapUtils.getString(map,"singleEye")).compareTo(maxSingleCount.orElse(BigDecimal.ZERO)) == 0)
                .collect(Collectors.toList());
        return minSingleCountMaps.size()>0?minSingleCountMaps.get(0):Maps.newHashMap();
    };

    @RequestMapping(value = "/maDipForm", method = { RequestMethod.GET, RequestMethod.POST })
    public String dipMaPage(Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();

        LocalDateTime now = LocalDateTime.now();
        String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
        FinMaMainCondition cond = new FinMaMainCondition();
        cond.setHospId(shiroUser.getTenantId());
        cond.setMaYear(now.getYear());
        cond.setMaQuarter(Integer.parseInt(curQuarter.substring(curQuarter.length() - 1)));
        cond.setMaIdentifier(curQuarter+shiroUser.getTenantId());
        cond.setSettlementMethod("DIP");
        List<FinMaMain> finMaMains = finMaMainFeignService.getAllRecord(cond);
        if(CollectionUtils.isNotEmpty(finMaMains) && finMaMains.size()>0) {
            finMaMains.stream().forEach(fm -> {
                if(fm.getInsuranceType().intValue() == 1){
                    map.put("emp",fm);   // 职工
                }
                if(fm.getInsuranceType().intValue() == 2){
                    map.put("rdt",fm);   // 居民
                }
            });
            List<Long> maMainIdList = finMaMains.stream().map(FinMaMain::getId).distinct().collect(Collectors.toList());
            FinMaDipDtlCondition finMaDipDtlCondition = new FinMaDipDtlCondition();
            finMaDipDtlCondition.setHospId(shiroUser.getTenantId());
            finMaDipDtlCondition.setMaIdentifier(curQuarter+shiroUser.getTenantId());
            if(CollectionUtils.isNotEmpty(maMainIdList) && maMainIdList.size()>0) {
                finMaDipDtlCondition.setMainIdList(maMainIdList);
            }
            List<FinMaDipDtl> finMaDipDtls = finMaDipDtlFeignService.getAllRecord(finMaDipDtlCondition);
            if(CollectionUtils.isNotEmpty(finMaDipDtls) && finMaDipDtls.size()>0) {
                finMaDipDtls.stream().forEach(fmdp -> map.put(fmdp.getBusiSign(),fmdp));
            }
        }else{
            // 初次加载，自动反显医院信息，医保信息等
            FinMaMain showFmm = new FinMaMain();
            showFmm.setHospName(shiroUser.getInstName());
            InstCondition cond_1 = new InstCondition();
            cond_1.setInstId(shiroUser.getTenantId());
            cond_1.setInstType(InstEnum.HOSP.getEnumCode());
            List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
            if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
                Map resultMap = hosps.get(0);
                /** 查询一级省区 */
                InstCondition cond_2 = new InstCondition();
                cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
                List<Map> list = institutionService.getProvince(cond_2);
                List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
                showFmm.setHospParent(String.valueOf(l.get(0).get("name")));
                // 医院类型
                if (Objects.nonNull(resultMap.get("INVEST_NATURE"))) {
                    if(String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("10")){
                        showFmm.setInvestNature("上市");
                    }else if(String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("11")){
                        showFmm.setInvestNature("合伙");
                    }else{
                        showFmm.setInvestNature("");
                    }
                } else {
                    showFmm.setInvestNature("");
                }
                if(resultMap.get("EHR_LEVEL")!=null){
                    showFmm.setEhrLevel(EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
                }
            }
            List<Map<String,Object>> finMains = finMainFeignService.lastList();
            if(CollectionUtils.isNotEmpty(finMains) && finMains.size() > 0){
                showFmm.setHealthCommissionLevel(MapUtils.getInteger(finMains.get(0),"healthCommissionLevel"));
                showFmm.setInsuranceSettlementLevel(MapUtils.getInteger(finMains.get(0),"insuranceSettlementLevel"));
            }
            map.put("emp",showFmm);
        }

        return DIP_MA_PAGE;
    }

    @RequestMapping(value = "/saveDip", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Map<String, Object> saveDip(@RequestBody String jsonData){
        return finMaMainFeignService.saveDipStr(jsonData);
    }

    @RequestMapping(value = "/dipStatMaPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String dipStatMaPage(Map<String, Object> map) {
        return DIP_STAT_MA_PAGE;
    }

    @RequestMapping(value = "/getDipAll", method = { RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> getDipAll(FinMaMainCondition cond){
        this.wrapperTimeToCond(cond);
        if (ShiroUtils.getIsHosp()) {
            List<ProvinceRoleConfig> provinceRoleConfigList  = this.queryProvinceRoleConfig();
            if(Objects.nonNull(provinceRoleConfigList) && provinceRoleConfigList.size()>0){
                if(this.wrapperHospLists(provinceRoleConfigList.get(0).getProvinceId(),cond)){
                    return new PageResponse<>();
                }
            }else{
                return new PageResponse<>();
            }
        }else{
            if (!Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 省区登录组装查询管理分析主表条件  --begin
                if(this.wrapperHospLists(ShiroUtils.getInstId(),cond)){
                    return new PageResponse<>();
                }
                // 省区登录组装查询管理分析主表条件  --end
            }
        }
        // 卫健委定级dict/医保结算等级dict
        List<Map> dictLevelLists = finDictFeignService.getList("LEVEL");
        cond.setSettlementMethod("DIP");
        PageResponse<Map<String, Object>> result = this.finMaMainFeignService.getAll(cond);
        List<Long> idLists = result.getRows().stream().map(rt -> MapUtils.getLong(rt,"id")).collect(Collectors.toList());
        List<FinMaDipDtl> finMaDipDtls = null;
        Map<Long, List<FinMaDipDtl>> groupedDetails;
        if(Objects.nonNull(idLists) && idLists.size()>0){
            FinMaDipDtlCondition condDip = new FinMaDipDtlCondition();
            condDip.setMainIdList(idLists);
            finMaDipDtls = finMaDipDtlFeignService.getAllRecord(condDip);
        }
        Boolean dipFlag = (Objects.nonNull(finMaDipDtls) && finMaDipDtls.size()>0)?true:false;
        if(dipFlag){
            groupedDetails = finMaDipDtls.stream().collect(Collectors.groupingBy(FinMaDipDtl::getMainId));
        } else {
            groupedDetails = null;
        }
        Boolean dipGroupFlag = (Objects.nonNull(groupedDetails) && groupedDetails.size()>0)?true:false;
        // 使用Stream API过滤出type为DIP的枚举值
        List<FinMaMedicalItemEnum> filteredEnums = Arrays.stream(FinMaMedicalItemEnum.values())
                .filter(item -> "DIP".equals(item.getType()))
                .collect(Collectors.toList());
        result.getRows().stream().forEach(rt -> {
            // 卫健委定级
            if (Objects.nonNull(rt.get("healthCommissionLevel"))) {
                Optional<Map> hc = dictLevelLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(rt.get("healthCommissionLevel")))).findFirst();
                if(hc.isPresent()){
                    rt.put("healthCommissionLevelDesc", hc.get().get("valueDesc"));
                }else{
                    rt.put("healthCommissionLevelDesc", "");
                }
            } else {
                rt.put("healthCommissionLevelDesc", "");
            }
            // 医保结算等级
            if (Objects.nonNull(rt.get("insuranceSettlementLevel"))) {
                Optional<Map> hc = dictLevelLists.stream().filter(il -> String.valueOf(il.get("valueCode")).equalsIgnoreCase(String.valueOf(rt.get("insuranceSettlementLevel")))).findFirst();
                if(hc.isPresent()){
                    rt.put("insuranceSettlementLevelDesc", hc.get().get("valueDesc"));
                }else{
                    rt.put("insuranceSettlementLevelDesc", "");
                }
            } else {
                rt.put("insuranceSettlementLevelDesc", "");
            }
            if(dipGroupFlag){
                List<FinMaDipDtl> dipDtls = groupedDetails.get(MapUtils.getLong(rt,"id"));
                if(Objects.nonNull(dipDtls) && dipDtls.size()>0){
                    filteredEnums.stream().filter(fe -> fe.getInsuranceType().intValue() == MapUtils.getInteger(rt,"insuranceType").intValue()).forEach(fe -> {
                        Optional<FinMaDipDtl> opFmddl = dipDtls.stream().filter(dl -> dl.getBusiSign().equals(fe.getValue())).findFirst();
                        if(opFmddl.isPresent()){
                            FinMaDipDtl fmddl = opFmddl.get();
                            rt.put("diagName" + fe.getSn(),fmddl.getDiagName());
                            rt.put("operationName" + fe.getSn(),fmddl.getOperationName());
                            rt.put("medicalItemName" + fe.getSn(),fmddl.getMedicalItemName());
                            rt.put("singleEye" + fe.getSn(),fmddl.getSingleEye());
                            rt.put("coupleEye" + fe.getSn(),fmddl.getCoupleEye());
                        }
                    });
                }
            }
        });

        return result;
    }

    @RequestMapping(value = "/getStatDipAll", method = { RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getStatDipAll(FinMaMainCondition cond){
        List<Map<String, Object>> resultStatLists = Lists.newArrayList();
        this.wrapperTimeToCond(cond);
        if (ShiroUtils.getIsHosp()) {
            List<ProvinceRoleConfig> provinceRoleConfigList  = this.queryProvinceRoleConfig();
            if(Objects.nonNull(provinceRoleConfigList) && provinceRoleConfigList.size()>0){
                if(this.wrapperHospLists(provinceRoleConfigList.get(0).getProvinceId(),cond)){
                    return resultStatLists;
                }
            }else{
                return resultStatLists;
            }
        }else{
            if (!Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                cond.setProvince(ShiroUtils.getInstId());
            }
            // 省区登录和集团登录不选"全部"时,需要把Province中的所有hospId都获取放入cond.setHospList中;初次进入页面,显示全部医院的统计
            if(Objects.nonNull(cond.getProvince()) && cond.getProvince().longValue()!=99){
                if(this.wrapperHospLists(cond.getProvince(),cond)){
                    return resultStatLists;
                }
            }
        }
        // 使用Stream API过滤出type为DIP的枚举值
        List<FinMaMedicalItemEnum> filteredEnums = Arrays.stream(FinMaMedicalItemEnum.values())
                .filter(item -> "DIP".equals(item.getType()))
                .collect(Collectors.toList());// 卫健委定级dict/医保结算等级dict
        List<Map> dictLevelLists = finDictFeignService.getList("LEVEL");
        dictLevelLists.stream().forEach(mapDict -> {
            // 职工
            Map<String, Object> statEmp = Maps.newHashMap();
            statEmp.put("hospParent", Objects.nonNull(cond.getProvinceName())?cond.getProvinceName():"全部");    // 省区
            statEmp.put("settlementMethod", "DIP");                                                             // 医保结算方式
            statEmp.put("healthCommissionLevel",mapDict.get("valueDesc"));                                      // 卫健委定级
            statEmp.put("insuranceType", "职工");                                                                // 医保结算方式
            FinMaMainCondition condCount = new FinMaMainCondition();
            condCount.setHealthCommissionLevel(MapUtils.getInteger(mapDict,"valueCode"));
            condCount.setInsuranceType(1);
            condCount.setSettlementMethod("DIP");
            condCount.setMaYear(cond.getMaYear());
            condCount.setMaQuarter(cond.getMaQuarter());
            condCount.setMispClearFlag(1);
            if(Objects.nonNull(cond.getHospList()) && cond.getHospList().size()>0){
                condCount.setHospList(cond.getHospList());
            }
            statEmp.put("instCount", finMaMainFeignService.getStatCount(condCount));
            filteredEnums.stream().filter(item -> item.getInsuranceType() == 1).forEach(fem -> {
                FinMaMainCondition condloop = new FinMaMainCondition();
                condloop.setHealthCommissionLevel(MapUtils.getInteger(mapDict,"valueCode"));
                condloop.setInsuranceType(fem.getInsuranceType());
                condloop.setBusiSign(fem.getValue().toString());
                condloop.setMaYear(cond.getMaYear());
                condloop.setMaQuarter(cond.getMaQuarter());
                if(Objects.nonNull(cond.getHospList()) && cond.getHospList().size()>0){
                    condloop.setHospList(cond.getHospList());
                }
                List<Map<String,Object>> statData = finMaMainFeignService.getStatDipRecord(condloop);
                if(Objects.nonNull(statData) && statData.size()>0){
                    // 均值
                    OptionalDouble avgStat = statData.stream().mapToDouble(sd -> MapUtils.getDoubleValue(sd,"singleEye")).average();
                    if(avgStat.isPresent() && avgStat.getAsDouble()>0){
                        statEmp.put("singleEyeAvg" + fem.getSn(), new BigDecimal(avgStat.getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        Map<String, Object> maxHospEmp = custCommMaxFunc.apply(statData);
                        if (maxHospEmp.size() > 0) {
                            // 省区最高值
                            statEmp.put("singleEyeMax" + fem.getSn(), maxHospEmp.get("singleEye"));
                            // 省区最高值医院名称
                            statEmp.put("singleEyeMaxHospName" + fem.getSn(), maxHospEmp.get("hospName"));
                        }
                        Map<String, Object> minHospEmp = custCommMinFunc.apply(statData);
                        if (minHospEmp.size() > 0) {
                            // 省区最低值
                            statEmp.put("singleEyeMin" + fem.getSn(), minHospEmp.get("singleEye"));
                            // 省区最低值医院名称
                            statEmp.put("singleEyeMinHospName" + fem.getSn(), minHospEmp.get("hospName"));
                        }
                    }
                }
            });
            resultStatLists.add(statEmp);
            // 居民
            Map<String, Object> statRdt = Maps.newHashMap();
            statRdt.put("hospParent", Objects.nonNull(cond.getProvinceName())?cond.getProvinceName():"全部");    // 省区
            statRdt.put("settlementMethod", "DIP");                                                             // 医保结算方式
            statRdt.put("healthCommissionLevel",mapDict.get("valueDesc"));                                      // 卫健委定级
            statRdt.put("insuranceType", "居民");                                                                // 医保结算方式
            condCount.setInsuranceType(2);
            statRdt.put("instCount", finMaMainFeignService.getStatCount(condCount));
            filteredEnums.stream().filter(item -> item.getInsuranceType() == 2).forEach(fem -> {
                FinMaMainCondition condloop = new FinMaMainCondition();
                condloop.setHealthCommissionLevel(MapUtils.getInteger(mapDict,"valueCode"));
                condloop.setInsuranceType(fem.getInsuranceType());
                condloop.setBusiSign(fem.getValue().toString());
                condloop.setMaYear(cond.getMaYear());
                condloop.setMaQuarter(cond.getMaQuarter());
                if(Objects.nonNull(cond.getHospList()) && cond.getHospList().size()>0){
                    condloop.setHospList(cond.getHospList());
                }
                List<Map<String,Object>> statData = finMaMainFeignService.getStatDipRecord(condloop);
                if(Objects.nonNull(statData) && statData.size()>0){
                    // 均值
                    OptionalDouble avgStat = statData.stream().mapToDouble(sd -> MapUtils.getDoubleValue(sd,"singleEye")).average();
                    if(avgStat.isPresent() && avgStat.getAsDouble()>0){
                        statRdt.put("singleEyeAvg" + fem.getSn(), new BigDecimal(avgStat.getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        Map<String, Object> maxHospRdt = custCommMaxFunc.apply(statData);
                        if (maxHospRdt.size() > 0) {
                            // 省区最高值
                            statRdt.put("singleEyeMax" + fem.getSn(), maxHospRdt.get("singleEye"));
                            // 省区最高值医院名称
                            statRdt.put("singleEyeMaxHospName" + fem.getSn(), maxHospRdt.get("hospName"));
                        }
                        Map<String, Object> minHospRdt = custCommMinFunc.apply(statData);
                        if (minHospRdt.size() > 0) {
                            // 省区最低值
                            statRdt.put("singleEyeMin" + fem.getSn(), minHospRdt.get("singleEye"));
                            // 省区最低值医院名称
                            statRdt.put("singleEyeMinHospName" + fem.getSn(), minHospRdt.get("hospName"));
                        }
                    }
                }
            });
            resultStatLists.add(statRdt);
        });

        return resultStatLists;
    }

    @RequestMapping(value = "/downLoadList", method = { RequestMethod.GET, RequestMethod.POST })
    public void downLoadList(HttpServletResponse response,String paramJson) throws IOException {
        FinMaMainCondition cond = JSON.parseObject(paramJson,FinMaMainCondition.class);
        // 设置响应类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("DIP统计"+this.getDateYMD(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<List<String>> dataList = this.getExcelDataList(cond);
        // 直接写入response的输出流中
        EasyExcel.write(response.getOutputStream())
                .head(getExcelHeadList()).sheet("DIP统计")
                .registerWriteHandler(new MergeStrategy(dataList.size(), 0, 1, 2, 3, 4, 5, 6, 7))
                .doWrite(dataList);
    }

    private List<List<String>> getExcelHeadList() {
        List<List<String>> head = new ArrayList<>();
        // 添加省区到医保结算等级的列，每个列只包含一个标题
        String[] titles = {"省区", "医院简称", "医院类型", "医保结算方式", "卫健委定级", "医保结算等级",  "政策是否明确", "年季","医保类型"};
        for (String title : titles) {
            List<String> headColumn = new ArrayList<>();
            headColumn.add(title);
            head.add(headColumn);
        }

        // 对于需要动态合并的列（如DRG分组, 单眼, 双眼），每个不同的项目需要单独添加
        String[] mergeTitles = {"老年性白内障结算标准（元）", "翼状胬肉结算标准（元）", "眼底病结算标准(元)"};
        head.add(Arrays.asList(mergeTitles[0], "诊断名称(不含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "手术名称(不含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "单眼(不含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "双眼(不含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "诊断名称(含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "手术名称(含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "单眼(含飞白)"));
        head.add(Arrays.asList(mergeTitles[0], "双眼(含飞白)"));
        head.add(Arrays.asList(mergeTitles[1], "诊断名称(不含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "手术名称(不含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "单眼(不含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "双眼(不含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "诊断名称(含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "手术名称(含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "单眼(含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[1], "双眼(含羊膜移植)"));
        head.add(Arrays.asList(mergeTitles[2], "诊断名称(眼底注药)"));
        head.add(Arrays.asList(mergeTitles[2], "手术名称(眼底注药)"));
        head.add(Arrays.asList(mergeTitles[2], "单眼(眼底注药)"));
        head.add(Arrays.asList(mergeTitles[2], "双眼(眼底注药)"));
        head.add(Arrays.asList(mergeTitles[2], "诊断名称(眼底玻切)"));
        head.add(Arrays.asList(mergeTitles[2], "手术名称(眼底玻切)"));
        head.add(Arrays.asList(mergeTitles[2], "单眼(眼底玻切)"));
        head.add(Arrays.asList(mergeTitles[2], "双眼(眼底玻切)"));

        return head;
    }

    private List<List<String>> getExcelDataList(FinMaMainCondition cond){
        List<List<String>> resultList = new ArrayList<>();
        this.wrapperTimeToCond(cond);
        if (ShiroUtils.getIsHosp()) {
            List<ProvinceRoleConfig> provinceRoleConfigList = this.queryProvinceRoleConfig();
            if(Objects.nonNull(provinceRoleConfigList) && provinceRoleConfigList.size()>0){
                if(this.wrapperHospLists(provinceRoleConfigList.get(0).getProvinceId(),cond)){
                    return resultList;
                }
            }else{
                return resultList;
            }
        }else{
            if (!Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 省区登录组装查询管理分析主表条件  --begin
                if(this.wrapperHospLists(ShiroUtils.getInstId(),cond)){
                    return resultList;
                }
                // 省区登录组装查询管理分析主表条件  --end
            }
        }
        cond.setSettlementMethod("DIP");
        List<FinMaMain> originalList = this.finMaMainFeignService.getAllRecord(cond);
        if(Objects.nonNull(originalList) && originalList.size()>0){
            // 卫健委定级dict/医保结算等级dict
            List<Map> dictLevelLists = finDictFeignService.getList("LEVEL");
            // 使用Stream API过滤出type为DRG的枚举值
            List<FinMaMedicalItemEnum> filteredEnums = Arrays.stream(FinMaMedicalItemEnum.values())
                    .filter(item -> "DIP".equals(item.getType()))
                    .collect(Collectors.toList());
            Lists.partition(originalList,splitNum).stream().forEach(partFinMaMain -> {
                List<Long> idLists = partFinMaMain.stream().map(FinMaMain::getId).collect(Collectors.toList());
                FinMaDipDtlCondition condDip = new FinMaDipDtlCondition();
                condDip.setMainIdList(idLists);
                List<FinMaDipDtl> finMaDipDtls = finMaDipDtlFeignService.getAllRecord(condDip);
                partFinMaMain.stream().forEach(fmm -> {
                    List<String> list = Lists.newArrayList();
                    Collections.addAll(list, fmm.getHospParent(),fmm.getHospName(),fmm.getEhrLevel(),fmm.getSettlementMethod());
                    // 卫健委定级
                    Optional<Map> hch = dictLevelLists.stream().filter(il -> MapUtils.getString(il,"valueCode").equalsIgnoreCase(fmm.getHealthCommissionLevel().toString())).findFirst();
                    list.add(hch.isPresent()?MapUtils.getString(hch.get(), "valueDesc"):"");
                    // 医保结算等级
                    Optional<Map> hci = dictLevelLists.stream().filter(il -> MapUtils.getString(il,"valueCode").equalsIgnoreCase(fmm.getInsuranceSettlementLevel().toString())).findFirst();
                    list.add(hci.isPresent()?MapUtils.getString(hci.get(), "valueDesc"):"");
                    // 政策是否明确
                    list.add(fmm.getMispClearFlag().intValue()==1?"是":"否");
                    // 年季
                    switch(fmm.getMaQuarter().intValue()){
                        case 1 :
                            list.add(fmm.getMaYear()+"第一季度");
                            break;
                        case 2 :
                            list.add(fmm.getMaYear()+"第二季度");
                            break;
                        case 3 :
                            list.add(fmm.getMaYear()+"第三季度");
                            break;
                        case 4 :
                            list.add(fmm.getMaYear()+"第四季度");
                            break;
                        default :
                            list.add("");
                    }
                    // 医保类型
                    list.add(fmm.getInsuranceType().intValue()==1?"职工":"居民");
                    filteredEnums.stream().filter(fe1 -> fe1.getInsuranceType().intValue() == fmm.getInsuranceType().intValue()).forEach(fe2 -> {
                        Optional<FinMaDipDtl> optFinMaDipDtl =  finMaDipDtls.stream().filter(fmdd ->
                                fmdd.getMainId().longValue() == fmm.getId().longValue()
                                        && fmdd.getBusiSign().equals(fe2.getValue())).findFirst();
                        if(optFinMaDipDtl.isPresent()){
                            list.add(optFinMaDipDtl.get().getDiagName()!=null?optFinMaDipDtl.get().getDiagName():"");
                            list.add(optFinMaDipDtl.get().getOperationName()!=null?optFinMaDipDtl.get().getOperationName():"");
                            list.add(optFinMaDipDtl.get().getSingleEye().toString()!=null?optFinMaDipDtl.get().getSingleEye().toString():"");
                            list.add(optFinMaDipDtl.get().getCoupleEye()!=null?optFinMaDipDtl.get().getCoupleEye().toString():"");
                        }else{
                            list.add("");
                            list.add("");
                            list.add("");
                            list.add("");
                        }

                    });
                    resultList.add(list);
                });
            });
        }

        return resultList;
    }


    public List<List<String>> getExcelStatHeadList() {
        List<List<String>> head = new ArrayList<>();
        // 添加省区到医保结算等级的列，每个列只包含一个标题
        String[] titles = {"省区", "医保结算方式", "卫健委定级", "机构数量","医保类型"};
        for (String title : titles) {
            List<String> headColumn = new ArrayList<>();
            headColumn.add(title);
            head.add(headColumn);
        }

        // 对于需要动态合并的列（如DRG分组, 单眼, 双眼），每个不同的项目需要单独添加
        String[] mergeTitles = {"老年性白内障结算标准(元)", "飞秒白内障结算标准(元)", "翼状胬肉结算标准(元)", "羊膜移植胬肉结算标准(元)", "眼底注药结算标准(元)", "眼底玻切结算标准(元)"};
        for (String mergeTitle : mergeTitles) {
            // 对于每种费用类型，都有DRG分组、单眼、双眼三个子列
            head.add(Arrays.asList(mergeTitle, "省区均值"));
            head.add(Arrays.asList(mergeTitle, "最高值"));
            head.add(Arrays.asList(mergeTitle, "医院名称"));
            head.add(Arrays.asList(mergeTitle, "最低值"));
            head.add(Arrays.asList(mergeTitle, "医院名称"));
        }

        return head;
    }

    public List<List<String>> getExcelStatDataList(FinMaMainCondition cond){
        List<List<String>> resultList = new ArrayList<>();
        List<Map<String, Object>> resultStatLists = this.getStatDipAll(cond);
        if(resultStatLists.size()>0){
            resultStatLists.stream().forEach(rsl -> {
                List<String> rowLists = Lists.newArrayList();
                rowLists.add(MapUtils.getString(rsl,"hospParent"));
                rowLists.add(MapUtils.getString(rsl,"settlementMethod"));
                rowLists.add(MapUtils.getString(rsl,"healthCommissionLevel"));
                rowLists.add(MapUtils.getString(rsl,"instCount"));
                rowLists.add(MapUtils.getString(rsl,"insuranceType"));
                for(int i=1;i<7;i++){
                    rowLists.add(rsl.containsKey("singleEyeAvg"+i)?MapUtils.getString(rsl,"singleEyeAvg"+i):"");
                    rowLists.add(rsl.containsKey("singleEyeMax"+i)?MapUtils.getString(rsl,"singleEyeMax"+i):"");
                    rowLists.add(rsl.containsKey("singleEyeMaxHospName"+i)?MapUtils.getString(rsl,"singleEyeMaxHospName"+i):"");
                    rowLists.add(rsl.containsKey("singleEyeMin"+i)?MapUtils.getString(rsl,"singleEyeMin"+i):"");
                    rowLists.add(rsl.containsKey("singleEyeMinHospName"+i)?MapUtils.getString(rsl,"singleEyeMinHospName"+i):"");
                }
                resultList.add(rowLists);
            });
        }
        return resultList;
    }

    protected List<ProvinceRoleConfig> queryProvinceRoleConfig(){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        return adverseEventService.getListAll(provinceRoleCondition);
    }
    protected void wrapperTimeToCond(FinMaMainCondition cond){
        LocalDateTime now = LocalDateTime.now();
        String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
        if(Objects.nonNull(cond.getMaQuarter()) && !cond.getMaQuarter().equals("")){
            cond.setMaQuarter(Integer.valueOf(cond.getMaQuarter()));
            cond.setMaYear(Integer.valueOf(StrUtil.sub(curQuarter, 0, 4)));
        }else{
            cond.setMaQuarter(Integer.valueOf(cn.hutool.core.util.StrUtil.sub(curQuarter, -1,curQuarter.length())));
            cond.setMaYear(Integer.valueOf(StrUtil.sub(curQuarter, 0, 4)));
        }
    }
    protected boolean wrapperHospLists(Long provinceId,FinMaMainCondition cond){
        Object o=redisService.get("AE:SUB_HOSP:"+provinceId);
        if(ObjectUtils.isEmpty(o)){
            o=hospHandleService.allHospFromParent(provinceId);
            redisService.set("AE:SUB_HOSP:"+provinceId,o, TimeUnit.DAYS.toSeconds(1));
        }
        try {
            if (Objects.nonNull(o)) {
                JSONArray arrHosp = (JSONArray) o;
                List<Long> hospIds = CollectionUtil.newArrayList();
                arrHosp.stream().forEach(hp -> {
                    JSONObject obj = (JSONObject) hp;
                    hospIds.add(obj.getLong("ahisHosp"));
                    cond.setProvinceName(obj.getString("parentName"));
                });
                if (hospIds.size() > 0) {
                    cond.setHospList(hospIds);
                } else {
                    return true;
                }
            }
        }catch (Exception e){
            redisService.remove("AE:SUB_HOSP:"+provinceId);
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

}
