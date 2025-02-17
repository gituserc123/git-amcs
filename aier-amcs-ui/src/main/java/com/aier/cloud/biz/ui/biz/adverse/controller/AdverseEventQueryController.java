package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.alibaba.fastjson.JSONException;
import com.google.common.collect.Lists;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
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
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventTagService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeCommonService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
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
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 不良事件查询管理
 *
 * @author
 * @date 2022/4/25
 */
@Controller
@RequestMapping("/ui/amcs/adverse/event/query/")
public class AdverseEventQueryController extends BaseController {

    @Autowired
    private AeCommonService aeCommonService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private HospHandleService hospHandleService;
    @Autowired
    private AdverseEventService adverseEventService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdverseEventTagService adverseEventTagService;
    @Autowired
    private AdverseDictService aes;

    private final Semaphore semaphore = new Semaphore(2); // 最大并发数
    private final Semaphore semaphore_export = new Semaphore(5); // 最大并发数

    private final static String EVENT_QUERY = "amcs/adverseEvent/eventManage/eventQueryList";
    private final static String ROLE_PROVINCE_MANAGEMENT = "省区不良事件管理";
    private final static String ROLE_MEDICAL_MANAGEMENT = "医务管理";
    private final static String ROLE_NURSING_MANAGEMENT = "护理管理";
    private static Integer splitNum = 800;

    @RequestMapping(value = "/queryPage")
    public String listPage(HttpServletRequest request,
                           @RequestParam(value = "page_type", required = false) Integer pageType,
                           @RequestParam(value = "type", required = false) Integer type) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
            // 医院人员登录
            request.setAttribute("empType", 3);
            request.setAttribute("insiId", shiroUser.getInstId());
        } else {
            // 集团或省区登录
            request.setAttribute("insiId", shiroUser.getInstId());
            if (shiroUser.getInstId() == 100002L) {
                // 集团登录
                request.setAttribute("empType", 1);
            } else {
                // 省区登录
                request.setAttribute("empType", 2);
                request.setAttribute("insiId",
                        provinceRoleConfigList.size() > 0 ? provinceRoleConfigList.get(0).getProvinceId()
                                : shiroUser.getInstId());
            }
        }
        if ((ShiroUtils.hasRole(ROLE_PROVINCE_MANAGEMENT) && ShiroUtils.hasRole(ROLE_MEDICAL_MANAGEMENT))
                || (ShiroUtils.hasRole(ROLE_PROVINCE_MANAGEMENT) && ShiroUtils.hasRole(ROLE_NURSING_MANAGEMENT))) {
            // 同时有 【医务管理】角色 和【省区不良事件管理】角色 或者 同时有【护理管理】角色 和【省区不良事件管理】角色
            request.setAttribute("attachAuth", 1);
        }
        request.setAttribute("type", 1);
        LocalDate today = LocalDate.now();
        // 获取当月21号
        LocalDate currentMonth21 = LocalDate.of(today.getYear(), today.getMonth(), 21);
        // 获取上月20号
        LocalDate lastMonth20 = LocalDate.of(today.minusMonths(1).getYear(), today.minusMonths(1).getMonth(), 20);

        request.setAttribute("currentMonth21", currentMonth21);
        request.setAttribute("lastMonth20", lastMonth20);
        // request.setAttribute("insiId", shiroUser.getInstId());
        return EVENT_QUERY;
    }

    @RequestMapping(value = "/getProvince", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Institution> getProvince() {
        List<Institution> ls = institutionService.getListByInstType(InstEnum.PROVINCE);
        return ls;
    }

    @RequestMapping(value = "/getHosp", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHosp(@RequestParam(value = "insiId", required = false) Long insiId) {
        String key = "AE:SUB_HOSP:" + insiId;
        Object o = loadObjectFromRedis(key);
        if (ObjectUtils.isEmpty(o)) {
            o = reloadAndSaveObject(key, insiId);
        }
        return o;
    }

    private Object loadObjectFromRedis(String key) {
        try {
            Object redisValue = redisService.get(key);
            if (redisValue instanceof String) {
                return JSON.parseObject((String) redisValue, Object.class);
            } else if (redisValue instanceof JSONArray) {
                return redisValue;
            } else {
                return null;
            }
        } catch (ClassCastException e) {
            // 记录错误日志
            System.err.println("从Redis获取值时类型转换错误: " + e.getMessage());
            // 删除Redis中的相关值
            redisService.remove(key);
            return null;
        } catch (JSONException e) {
            // 记录错误日志
            System.err.println("从Redis解析JSON时出错: " + e.getMessage());
            // 删除Redis中的相关值
            redisService.remove(key);
            return null;
        }catch (Exception e){
            System.err.println("异常错误: " + e.getMessage());
            redisService.remove(key);
            return null;
        }
    }

    private Object reloadAndSaveObject(String key, Long insiId) {
        Object reloadedObject = hospHandleService.getHospByParent(insiId);
        redisService.set(key, JSON.toJSONString(reloadedObject), TimeUnit.DAYS.toSeconds(1));
        return reloadedObject;
    }

    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportExcel(String paramJson) throws IOException {
        if(semaphore_export.tryAcquire()){
            try{
                AeInfoCondition cond = JSON.parseObject(paramJson, AeInfoCondition.class);
                cond.setRows(20000);
                Subject subject = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
                ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
                provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
                List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
                // 组装查询条件 --begin
                this.wrapperCond(cond, shiroUser, provinceRoleConfigList);
                // 根据查询条件查询数据
                PageResponse<Map<String, Object>> retVal = aeCommonService.queryListByCond(cond);
                List<Map<String, Object>> dataList = retVal.getRows();
                if (Objects.nonNull(dataList) && dataList.size() > 0) {
                    List<Long> basicList = dataList.stream().map(item -> Long.valueOf(item.get("id").toString()))
                            .collect(Collectors.toList());
                    List<Map<String, Object>> tags = Lists.newArrayList();
                    Lists.partition(basicList, splitNum).stream().forEach(listBasicId -> {
                        AeInfoCondition basicCond = new AeInfoCondition();
                        basicCond.setBasicIds(listBasicId);
                        tags.addAll(adverseEventTagService.findListByCond(basicCond));
                    });
                    Map<String, List<Map<String, Object>>> mapTags = tags.stream()
                            .collect(Collectors.groupingBy(d -> String.valueOf(d.get("eventId"))));
                    Map<String, Object> tagStrMap = new HashMap<>();
                    mapTags.keySet().stream().forEach(mt -> tagStrMap.put(mt, mapTags.get(mt).stream()
                            .map(item -> String.valueOf(item.get("tagCodeName"))).collect(Collectors.joining(","))));
                    // 组装公共数据
                    this.wrapperCommonData(dataList, tagStrMap);
                }
                // 返回excel
                this.returnResult(retVal, "WEB-INF/views/amcs/adverseEvent/excelTemplate/", "adverseList",
                        "不良事件_" + getDateYMD());
            }finally {
                semaphore_export.release();
            }
        }else {
            // 返回错误响应，表示请求过多
            throw new BizException("当前导出任务过多，请稍后再试。");
        }
    }



    @RequestMapping(value = "/exportExcelDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportExcelDetail(String paramJson) throws IOException {
/*        if (semaphore.tryAcquire()) {
            try {*/
                AeInfoCondition cond = JSON.parseObject(paramJson, AeInfoCondition.class);
                cond.setRows(20000);
                Subject subject = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
                ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
                provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
                List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
                // 组装查询条件 --begin
                this.wrapperCond(cond, shiroUser, provinceRoleConfigList);
                // 根据查询条件查询数据
                PageResponse<Map<String, Object>> retVal = aeCommonService.queryListByCond(cond);
                List<Map<String, Object>> dataList = retVal.getRows();
                if (Objects.nonNull(dataList) && dataList.size() > 0) {
                    List<Long> basicList = dataList.stream().map(item -> Long.valueOf(item.get("id").toString()))
                            .collect(Collectors.toList());
                    List<Map<String, Object>> tags = Lists.newArrayList();
                    Lists.partition(basicList, splitNum).stream().forEach(listBasicId -> {
                        AeInfoCondition basicCond = new AeInfoCondition();
                        basicCond.setBasicIds(listBasicId);
                        tags.addAll(adverseEventTagService.findListByCond(basicCond));
                    });
                    Map<String, List<Map<String, Object>>> mapTags = tags.stream()
                            .collect(Collectors.groupingBy(d -> String.valueOf(d.get("eventId"))));
                    Map<String, Object> tagStrMap = new HashMap<>();
                    mapTags.keySet().stream().forEach(mt -> tagStrMap.put(mt, mapTags.get(mt).stream()
                            .map(item -> String.valueOf(item.get("tagCodeName"))).collect(Collectors.joining(","))));
                    // 组装公共数据
                    this.wrapperCommonData(dataList, tagStrMap);
                    // 点评情况
                    this.wrapperAeOperationRecord(dataList);
                    // 具体不良事件
                    this.wrapperAeOutp(dataList);
                    this.wrapperAeInp(dataList);
                    this.wrapperAeCornealContact(dataList);
                    this.wrapperAeFrameGlasses(dataList);
                    this.wrapperAeVisualTrain(dataList);
                    this.wrapperAeOtherMedical(dataList);
                    this.wrapperAeOtherOptometry(dataList);
                    this.wrapperAeOutpPatient(dataList);
                    this.wrapperAeTumbleFallbed(dataList);
                    this.wrapperAeDrugMistake(dataList);
                    this.wrapperAeSampleGather(dataList);
                    this.wrapperAeOtherCare(dataList);
                    this.wrapperAeInfection(dataList);
                    this.wrapperAeOther(dataList);
                    this.wrapperAeUnplReoperation(dataList);
                    // 血/体液职业暴露事件暂时没有特殊的数据处理
                    // this.wrapperAeOccupationExposure(dataList);
                }
                // 返回excel
                this.returnResult(retVal, "WEB-INF/views/amcs/adverseEvent/excelTemplate/", "adverseDetailList",
                        "不良事件明细_" + getDateYMD());
/*            } finally {
                // 释放许可
                semaphore.release();
            }
        } else {
            // 返回错误响应，表示请求过多
            throw new BizException("当前导出任务过多，请稍后再试。");
        }*/
    }

    public void wrapperCond(AeInfoCondition cond, ShiroUser shiroUser,
                            List<ProvinceRoleConfig> provinceRoleConfigList) {
        // 组装查询条件 --begin
        // 判断需要查询归档，没有时则设置为False
        if (ObjectUtils.isEmpty(cond.getShowArchived())) {
            cond.setShowArchived(false);
        }
        if (cond.getEventLevelDesc() != null && !cond.getEventLevelDesc().equals("")) {
            cond.setEventLevel(cond.getEventLevelDesc());
        }
        if (cond.getEventCode() == null || cond.getEventCode().equals("")) {
            if (cond.getEventType() != null && !cond.getEventType().equals("")) {
                if (cond.getEventType().equals("1")) {
                    cond.setEventCode("101,102,103,104,105,106,107,108");
                } else if (cond.getEventType().equals("2")) {
                    cond.setEventCode("201,202,203,204");
                } else if (cond.getEventType().equals("3")) {
                    cond.setEventCode("301,302,303");
                } else if (cond.getEventType().equals("9")) {
                    cond.setEventCode("901");
                } else {
                    cond.setEventCode("101,102,103,104,105,106,107,108,201,202,203,204,301,302,303");
                }
                cond.setEventType(null);
            }
        }
        if (cond.getUnplan() != null && cond.getUnplan() > 0) {
            // 非计划情况只存在于住院患者不良事件,门诊患者不良事件,医院感染不良事件
            cond.setEventCode("102,108,301");
        }
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
        // 组装查询条件 --end
        /** inst_id=>hosp_id */
        if (Objects.nonNull(cond.getHospId()) && cond.getHospId() > 9999) {
            Institution inst = institutionService.getById(cond.getHospId());
            if (!ObjectUtils.isEmpty(inst)) {
                cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
            }
        }
    }

    public void wrapperCommonData(List<Map<String, Object>> dataList, Map<String, Object> tagStrMap) {
        Map<String, Map> existHosp = new HashMap<>();
        List<Map> unplanDict = aes.getList("unplan", null);
        List<Map> severityLevelDict = aes.getList("severity_level", null);
        List<Map<String, Object>> dictLists;
        dictLists = aeCommonService.queryByQueryMapper("T_AE_DICT_TYPE", " * ",
                " TYPE_CODE in('technical_post_type','education_type','staff_type','work_year_type')");
        Map<String, Integer> snMap = new HashMap<>();
        snMap.put("snNum", 1);
        dataList.stream().forEach(obj -> {
            obj.put("snNum", snMap.get("snNum")); // 序列号
            // 涉及科室/人员类型
            obj.put("tagCodeName", tagStrMap.get(String.valueOf(obj.get("id"))));
            // 判断是否符合上报时限,规则:上报时间-发生时间≤3天（72小时）属于符合上报时限
            if (Objects.nonNull(obj.get("createDate")) && Objects.nonNull(obj.get("eventDate"))) {
                LocalDate eventDateTime = LocalDate.parse(String.valueOf(obj.get("eventDate")),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDate createDateTime = LocalDate.parse(String.valueOf(obj.get("createDate")),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                // long daysDiff = ChronoUnit.DAYS.between(eventDateTime, createDateTime);
                // 修改为取T_AE_OPERATION_RECORD表最早的操作时间,如果该时间为空，则取createDateTime
                LocalDate minOperDate = LocalDate.parse(
                        MapUtils.getString(obj, "minOperDate") != null ? MapUtils.getString(obj, "minOperDate")
                                : MapUtils.getString(obj, "createDate"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                long daysDiff = ChronoUnit.DAYS.between(eventDateTime, minOperDate);
                if (daysDiff > 30L) {
                    obj.put("isConform", "不符合");
                } else {
                    obj.put("isConform", "符合");
                }
                // 发生年份
                // obj.put("eventDateTimeYear",
                // Integer.valueOf(String.valueOf(eventDateTime.getYear())));
                obj.put("eventDateTimeYear", eventDateTime.getYear());
                // 发生月份
                obj.put("eventDateTimeMonth", eventDateTime.getMonthValue());
                // 上报月份
                obj.put("createDateTimeMonth", createDateTime.getMonthValue());
                obj.put("eventDate",
                        eventDateTime.getYear() + "-"
                                + (eventDateTime.getMonthValue() < 10 ? ("0" + eventDateTime.getMonthValue())
                                : eventDateTime.getMonthValue())
                                + "-" +
                                (eventDateTime.getDayOfMonth() < 10 ? ("0" + eventDateTime.getDayOfMonth())
                                        : eventDateTime.getDayOfMonth()));
            } else {
                if (Objects.nonNull(obj.get("createDate"))) {
                    LocalDate createDateTime = LocalDate.parse(String.valueOf(obj.get("createDate")),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    // 上报月份
                    obj.put("createDateTimeMonth", createDateTime.getMonthValue());
                } else {
                    obj.put("createDateTimeMonth", "");
                }
                if (Objects.nonNull(obj.get("eventDate"))) {
                    LocalDate eventDateTime = LocalDate.parse(String.valueOf(obj.get("eventDate")),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    // 发生年份
                    obj.put("eventDateTimeYear", eventDateTime.getYear());
                    // 发生月份
                    obj.put("eventDateTimeMonth", eventDateTime.getMonthValue());
                    obj.put("eventDate",
                            eventDateTime.getYear() + "-"
                                    + (eventDateTime.getMonthValue() < 10 ? ("0" + eventDateTime.getMonthValue())
                                    : eventDateTime.getMonthValue())
                                    + "-" +
                                    (eventDateTime.getDayOfMonth() < 10 ? ("0" + eventDateTime.getDayOfMonth())
                                            : eventDateTime.getDayOfMonth()));
                } else {
                    // 发生年份
                    obj.put("eventDateTimeYear", "");
                    // 发生月份
                    obj.put("eventDateTimeMonth", "");
                }
                obj.put("isConform", "不符合");
            }
            // 赔偿及减免总额
            if (Objects.nonNull(obj.get("totalCompensationAmount")) && Objects.nonNull(obj.get("totalDeductionAmount"))) {
                obj.put("diffAmount", new BigDecimal(Double.parseDouble(String.valueOf(obj.get("totalCompensationAmount"))) + Double.parseDouble(String.valueOf(obj.get("totalDeductionAmount")))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            } else {
                if (Objects.nonNull(obj.get("totalCompensationAmount")) && Objects.isNull(obj.get("totalDeductionAmount"))) {
                    obj.put("diffAmount", new BigDecimal(Double.parseDouble(String.valueOf(obj.get("totalCompensationAmount")))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                } else if (Objects.nonNull(obj.get("totalDeductionAmount")) && Objects.isNull(obj.get("totalCompensationAmount"))) {
                    obj.put("diffAmount", new BigDecimal(Double.parseDouble(String.valueOf(obj.get("totalDeductionAmount")))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                } else {
                    obj.put("diffAmount", 0);
                }
            }
            // 非计划情况
            if (obj.get("unplan") != null) {
                Optional<Map> matchItem = unplanDict.stream()
                        .filter(ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("unplan"))))
                        .findFirst();
                if (matchItem.isPresent()) {
                    obj.put("unplanDesc", matchItem.get().get("valueDesc"));
                }
            }
            if (obj.get("inpUnplan") != null) {
                Optional<Map> matchItem = unplanDict.stream().filter(
                                ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("inpUnplan"))))
                        .findFirst();
                if (matchItem.isPresent()) {
                    obj.put("unplanDesc", matchItem.get().get("valueDesc"));
                }
            }
            if (obj.get("outpPatientUnplan") != null) {
                Optional<Map> matchItem = unplanDict.stream().filter(
                                ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("outpPatientUnplan"))))
                        .findFirst();
                if (matchItem.isPresent()) {
                    obj.put("unplanDesc", matchItem.get().get("valueDesc"));
                }
            }
            if (obj.get("infectionUnplan") != null) {
                Optional<Map> matchItem = unplanDict.stream().filter(
                                ud -> String.valueOf(ud.get("valueCode")).equals(String.valueOf(obj.get("infectionUnplan"))))
                        .findFirst();
                if (matchItem.isPresent()) {
                    obj.put("unplanDesc", matchItem.get().get("valueDesc"));
                }
            }
            // 严重程度
            if (obj.get("severityLevel") != null) {
                Optional<Map> matchItem = severityLevelDict.stream().filter(sld -> String.valueOf(sld.get("valueCode"))
                        .equals(MapUtils.getString(obj, "severityLevel"))).findFirst();
                if (matchItem.isPresent()) {
                    obj.put("severityLevelDesc", matchItem.get().get("valueDesc"));
                } else {
                    obj.put("severityLevelDesc", "");
                }
            }
            // 表单分类
            if (Objects.nonNull(obj.get("eventCode"))) {
                if ("'101','102','103','104','105','106','107','108'"
                        .indexOf(String.valueOf(obj.get("eventCode"))) > -1) {
                    obj.put("eventCodeDesc", "医疗类");
                } else if ("'201','202','203','204','205','206','207','208'"
                        .indexOf(String.valueOf(obj.get("eventCode"))) > -1) {
                    obj.put("eventCodeDesc", "护理类");
                } else if ("'301','302','303','304','305','306','307','308'"
                        .indexOf(String.valueOf(obj.get("eventCode"))) > -1) {
                    obj.put("eventCodeDesc", "院感类");
                } else {
                    obj.put("eventCodeDesc", "");
                }
            } else {
                obj.put("eventCodeDesc", "");
            }
            // 患者性别
            if (Objects.nonNull(obj.get("patientSex"))) {
                if (String.valueOf(obj.get("patientSex")).equalsIgnoreCase("1")) {
                    obj.put("patientSex", "男");
                } else {
                    obj.put("patientSex", "女");
                }
            } else {
                obj.put("patientSex", "");
            }
            // 患者年龄
            if (Objects.nonNull(obj.get("patientAge"))) {
                // obj.put("patientAge",
                // Integer.valueOf(String.valueOf(obj.get("patientAge"))));
                obj.put("patientAge", obj.get("patientAge"));
            } else {
                obj.put("patientAge", "");
            }
            // 是否完结
            if (Objects.nonNull(obj.get("finishSign"))) {
                if (String.valueOf(obj.get("finishSign")).equalsIgnoreCase("1")) {
                    obj.put("finishSign", "是");
                } else {
                    obj.put("finishSign", "否");
                }
            } else {
                obj.put("finishSign", "");
            }
            // 是否构成纠纷
            if (Objects.nonNull(obj.get("disputeSign"))) {
                if (String.valueOf(obj.get("disputeSign")).equalsIgnoreCase("1")) {
                    obj.put("disputeSign", "是");
                } else {
                    obj.put("disputeSign", "否");
                }
            } else {
                obj.put("disputeSign", "");
            }
            // 医院类型和省区
            if (Objects.nonNull(obj.get("hospId"))) {
                System.out.println(obj.get("hospId"));
                if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
                    obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
                    // obj.put("investNature",
                    // existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
                    // 医院类型
                    if (Objects.nonNull(existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"))) {
                        if (String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"))
                                .equalsIgnoreCase("10")) {
                            obj.put("investNature", "上市");
                        } else if (String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"))
                                .equalsIgnoreCase("11")) {
                            obj.put("investNature", "合伙");
                        } else {
                            obj.put("investNature", "");
                        }
                    } else {
                        obj.put("investNature", "");
                    }
                    if (existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL") != null) {
                        obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(
                                String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL"))));
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
                        // 医院类型
                        if (Objects.nonNull(resultMap.get("INVEST_NATURE"))) {
                            if (String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("10")) {
                                obj.put("investNature", "上市");
                            } else if (String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("11")) {
                                obj.put("investNature", "合伙");
                            } else {
                                obj.put("investNature", "");
                            }
                        } else {
                            obj.put("investNature", "");
                        }
                        if (resultMap.get("EHR_LEVEL") != null) {
                            obj.put("ehrLevel",
                                    EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
                        }
                        resultMap.put("PARENT_NAME", l.get(0).get("name"));
                        existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
                    }
                }
            }
            // 涉事医务人员性别
            if (Objects.nonNull(obj.get("staffSex"))) {
                if (String.valueOf(obj.get("staffSex")).equalsIgnoreCase("1")) {
                    obj.put("staffSex", "男");
                } else {
                    obj.put("staffSex", "女");
                }
            } else {
                obj.put("staffSex", "");
            }
            // 专业技术职称
            if (Objects.nonNull(obj.get("technicalPost"))) {
                obj.put("technicalPostDesc",
                        dictLists.stream()
                                .filter(dl -> String.valueOf(dl.get("typeCode")).equalsIgnoreCase("technical_post_type")
                                        && String.valueOf(dl.get("valueCode"))
                                        .equalsIgnoreCase(String.valueOf(obj.get("technicalPost"))))
                                .findFirst().get().get("valueDesc"));
            } else {
                obj.put("technicalPostDesc", "");
            }
            // 学历
            if (Objects.nonNull(obj.get("staffDegree"))) {
                obj.put("staffDegreeDesc", dictLists
                        .stream().filter(dl -> String.valueOf(dl.get("typeCode")).equalsIgnoreCase("education_type")
                                && String.valueOf(dl.get("valueCode"))
                                .equalsIgnoreCase(String.valueOf(obj.get("staffDegree"))))
                        .findFirst().get().get("valueDesc"));
            } else {
                obj.put("staffDegreeDesc", "");
            }
            // 类别
            if (Objects.nonNull(obj.get("staffType"))) {
                obj.put("staffTypeDesc",
                        dictLists
                                .stream().filter(dl -> String.valueOf(dl.get("typeCode")).equalsIgnoreCase("staff_type")
                                        && String.valueOf(dl.get("valueCode"))
                                        .equalsIgnoreCase(String.valueOf(obj.get("staffType"))))
                                .findFirst().get().get("valueDesc"));
            } else {
                obj.put("staffTypeDesc", "");
            }
            // 类别
            if (Objects.nonNull(obj.get("staffYears"))) {
                obj.put("staffYearsDesc", dictLists
                        .stream().filter(dl -> String.valueOf(dl.get("typeCode")).equalsIgnoreCase("work_year_type")
                                && String.valueOf(dl.get("valueCode"))
                                .equalsIgnoreCase(String.valueOf(obj.get("staffYears"))))
                        .findFirst().get().get("valueDesc"));
            } else {
                obj.put("staffYearsDesc", "");
            }
            // 医务人员年龄
            if (Objects.nonNull(obj.get("staffAge"))) {
                obj.put("staffAge", obj.get("staffAge"));
            } else {
                obj.put("staffAge", "");
            }
            // 原因分析
            if (Objects.nonNull(obj.get("reason"))) {
                String reason = String.valueOf(obj.get("reason"));
                obj.put("reason", reason.replace("$,$", ";").replace(":::", "-"));
            } else {
                obj.put("reason", "");
            }
            // 是否高风险
            if (Objects.nonNull(obj.get("isHighRisk"))) {
                if (String.valueOf(obj.get("isHighRisk")).equalsIgnoreCase("1")) {
                    obj.put("isHighRisk", "是");
                } else {
                    obj.put("isHighRisk", "否");
                }
            } else {
                obj.put("isHighRisk", "");
            }
            snMap.put("snNum", snMap.get("snNum") + 1);
        });

    }

    public void returnResult(PageResponse<Map<String, Object>> retVal, String tmpPath, String docPreSuffix,
                             String docName) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        String templateAbsolutePath = Strings.concat(tmpPath, docPreSuffix, ".xlsx");
        response.reset();
        ClassPathResource config = new ClassPathResource(templateAbsolutePath);
        if (!config.exists()) {
            throw new BizException("没有找到模版");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileRealName = URLEncoder.encode(docName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileRealName + ".xlsx");
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(config.getInputStream()).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(false).build();
            //System.out.println(paramJson);
            excelWriter.fill(retVal.getRows(), fillConfig, writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excelWriter.finish();
        }
    }

    // 门诊患者不良事件
    public void wrapperAeOutp(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OUTP.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(
                    aeCommonService.queryByQueryMapper("T_AE_OUTP", "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode")).equalsIgnoreCase(EventEnum.OUTP.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeMatchOutOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeMatchOutOpt.isPresent()) {
                            Map<String, Object> matchMap = aeMatchOutOpt.get();
                            // 取门诊视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            String nakedFarVodName = Objects.nonNull(matchMap.get("nakedFarVodName"))
                                    ? String.valueOf(matchMap.get("nakedFarVodName"))
                                    : "";
                            String iopTypeOdName = Objects.nonNull(matchMap.get("iopTypeOdName"))
                                    ? String.valueOf(matchMap.get("iopTypeOdName"))
                                    : "";
                            String nakedFarVosName = Objects.nonNull(matchMap.get("admissionIopOdName"))
                                    ? String.valueOf(matchMap.get("nakedFarVosName"))
                                    : "";
                            String iopTypeOsName = Objects.nonNull(matchMap.get("iopTypeOsName"))
                                    ? String.valueOf(matchMap.get("iopTypeOsName"))
                                    : "";
                            String outpOdOs = "VOD:" + nakedFarVodName + "  OD:" + iopTypeOdName + "  mmhg;" + "VOS:"
                                    + nakedFarVosName + " OS:" + iopTypeOsName + "  mmhg;";
                            // 入院视力眼压
                            obj.put("outpOdOs", outpOdOs);
                            // 事件经过描述
                            String desc_ = Objects.nonNull(matchMap.get("procDescr"))
                                    ? String.valueOf(matchMap.get("procDescr"))
                                    : "";
                            String desc_one = Objects.nonNull(matchMap.get("procDescrOne"))
                                    ? String.valueOf(matchMap.get("procDescrOne"))
                                    : "";
                            String desc_two = Objects.nonNull(matchMap.get("procDescrTwo"))
                                    ? String.valueOf(matchMap.get("procDescrTwo"))
                                    : "";
                            obj.put("eventDesc", desc_ + desc_one + desc_two);
                            // 门诊诊断
                            obj.put("visitDiagnose", matchMap.get("visitDiagnose"));
                            // 目前情况
                            obj.put("curSituation", matchMap.get("curSituation"));
                        }
                    });
        }
    }

    // 住院患者不良事件
    public void wrapperAeInp(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.INPATIENT.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(
                    aeCommonService.queryByQueryMapper("T_AE_INP", "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.INPATIENT.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeMatchInpOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeMatchInpOpt.isPresent()) {
                            Map<String, Object> matchMap = aeMatchInpOpt.get();
                            // 取门诊视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            String admissionVodName = Objects.nonNull(matchMap.get("admissionVodName"))
                                    ? String.valueOf(matchMap.get("admissionVodName"))
                                    : "";
                            String admissionVosName = Objects.nonNull(matchMap.get("admissionVosName"))
                                    ? String.valueOf(matchMap.get("admissionVosName"))
                                    : "";
                            String admissionIopOdName = Objects.nonNull(matchMap.get("admissionIopOd"))
                                    ? String.valueOf(matchMap.get("admissionIopOd"))
                                    : "";
                            String admissionIopOsName = Objects.nonNull(matchMap.get("admissionIopOs"))
                                    ? String.valueOf(matchMap.get("admissionIopOs"))
                                    : "";
                            String outpOdOs = "VOD:" + admissionVodName + "  OD:" + admissionIopOdName + "  mmhg;"
                                    + "VOS:" + admissionVosName + " OS:" + admissionIopOsName + "  mmhg;";
                            // 取住院视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            // 取出院视力或者当前视力都可以，目前取的出院视力
                            String inpOdOs;
                            if (Objects.nonNull(matchMap.get("dischargeDate"))) {
                                // 出院时间不为空，取出院视力眼压
                                String dischargeVodName = Objects.nonNull(matchMap.get("dischargeVodName"))
                                        ? String.valueOf(matchMap.get("dischargeVodName"))
                                        : "";
                                String dischargeIopTypeOdName = Objects.nonNull(matchMap.get("dischargeIopOd"))
                                        ? String.valueOf(matchMap.get("dischargeIopOd"))
                                        : "";
                                String dischargeVosName = Objects.nonNull(matchMap.get("dischargeVosName"))
                                        ? String.valueOf(matchMap.get("dischargeVosName"))
                                        : "";
                                String dischargeIopTypeOsName = Objects.nonNull(matchMap.get("dischargeIopOs"))
                                        ? String.valueOf(matchMap.get("dischargeIopOs"))
                                        : "";
                                inpOdOs = "VOD:" + dischargeVodName + "  OD:" + dischargeIopTypeOdName + "  mmhg;"
                                        + "VOS:" + dischargeVosName + " OS:" + dischargeIopTypeOsName + "  mmhg;";
                            } else {
                                // 当前视力眼压
                                String dischargeVodName = Objects.nonNull(matchMap.get("currentVodName"))
                                        ? String.valueOf(matchMap.get("currentVodName"))
                                        : "";
                                String dischargeIopTypeOdName = Objects.nonNull(matchMap.get("currentIopOd"))
                                        ? String.valueOf(matchMap.get("currentIopOd"))
                                        : "";
                                String dischargeVosName = Objects.nonNull(matchMap.get("currentVosName"))
                                        ? String.valueOf(matchMap.get("currentVosName"))
                                        : "";
                                String dischargeIopTypeOsName = Objects.nonNull(matchMap.get("currentIopOs"))
                                        ? String.valueOf(matchMap.get("currentIopOs"))
                                        : "";
                                inpOdOs = "VOD:" + dischargeVodName + "  OD:" + dischargeIopTypeOdName + "  mmhg;"
                                        + "VOS:" + dischargeVosName + " OS:" + dischargeIopTypeOsName + "  mmhg;";
                            }
                            // 入院视力眼压
                            obj.put("outpOdOs", outpOdOs);
                            // 出院视力眼压
                            obj.put("inpOdOs", inpOdOs);
                            // 事件经过描述
                            String desc_ = Objects.nonNull(matchMap.get("eventDesc"))
                                    ? String.valueOf(matchMap.get("eventDesc"))
                                    : "";
                            String desc_one = Objects.nonNull(matchMap.get("eventDescOne"))
                                    ? String.valueOf(matchMap.get("eventDescOne"))
                                    : "";
                            String desc_two = Objects.nonNull(matchMap.get("eventDescTwo"))
                                    ? String.valueOf(matchMap.get("eventDescTwo"))
                                    : "";
                            obj.put("eventDesc", desc_ + desc_one + desc_two);
                            // 住院诊断
                            obj.put("inpDiagName", matchMap.get("inpDiagName"));
                            // 门诊诊断
                            obj.put("visitDiagnose", matchMap.get("outpDiagName"));
                            // 目前情况
                            obj.put("curSituation", matchMap.get("currSituation"));
                        }
                    });
        }
    }

    // 角膜接触镜不良事件
    public void wrapperAeCornealContact(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.CORNEAL.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_CORNEAL_CONTACT",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.CORNEAL.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("procDescr"))
                                            ? String.valueOf(matchMap.get("procDescr"))
                                            : "");
                            // 门诊诊断
                            obj.put("visitDiagnose",
                                    Objects.nonNull(matchMap.get("visitDiagnose"))
                                            ? String.valueOf(matchMap.get("visitDiagnose"))
                                            : "");
                            // 目前情况
                            String grugRemak = Objects.nonNull(matchMap.get("grugRemak"))
                                    ? String.valueOf(matchMap.get("grugRemak"))
                                    : "";
                            String vitalSign = Objects.nonNull(matchMap.get("vitalSign"))
                                    ? String.valueOf(matchMap.get("vitalSign"))
                                    : "";
                            obj.put("curSituation", grugRemak + vitalSign);
                        }
                    });
        }
    }

    // 框架眼镜不良反应
    public void wrapperAeFrameGlasses(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.FRAMEGLASSES.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_FRAME_GLASSES",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.FRAMEGLASSES.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("procDescr"))
                                            ? String.valueOf(matchMap.get("procDescr"))
                                            : "");
                        }
                    });
        }
    }

    // 视觉训练不良反应
    public void wrapperAeVisualTrain(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.VISUALTRAIN.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_VISUAL_TRAIN", "*",
                    " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.VISUALTRAIN.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("procDescr"))
                                            ? String.valueOf(matchMap.get("procDescr"))
                                            : "");
                            // 门诊诊断
                            obj.put("visitDiagnose",
                                    Objects.nonNull(matchMap.get("diagName")) ? String.valueOf(matchMap.get("diagName"))
                                            : "");
                        }
                    });
        }
    }

    // 其他医疗类不良事件(不涉及患者本人)
    public void wrapperAeOtherMedical(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OTHERMEDICAL.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_OTHER_MEDICAL",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.OTHERMEDICAL.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("detailInfo"))
                                            ? String.valueOf(matchMap.get("detailInfo"))
                                            : "");
                            // 门诊诊断
                            obj.put("visitDiagnose",
                                    Objects.nonNull(matchMap.get("diagName")) ? String.valueOf(matchMap.get("diagName"))
                                            : "");
                        }
                    });
        }
    }

    // 其他视光患者不良事件
    public void wrapperAeOtherOptometry(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OTHEROPTOMETRY.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_OTHER_OPTOMETRY",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.OTHEROPTOMETRY.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("detailInfo"))
                                            ? String.valueOf(matchMap.get("detailInfo"))
                                            : "");
                            // 门诊诊断
                            obj.put("visitDiagnose",
                                    Objects.nonNull(matchMap.get("diagnose")) ? String.valueOf(matchMap.get("diagnose"))
                                            : "");
                        }
                    });
        }
    }

    // 门诊患者不良事件(手术患者)
    public void wrapperAeOutpPatient(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OUTPPATIENT.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_OUTP_PATIENT", "*",
                    " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.OUTPPATIENT.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 取门诊视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            String outpVodName = Objects.nonNull(matchMap.get("outpVodName"))
                                    ? String.valueOf(matchMap.get("outpVodName"))
                                    : "";
                            String outpIopTypeOdName = Objects.nonNull(matchMap.get("outpIopOd"))
                                    ? String.valueOf(matchMap.get("outpIopOd"))
                                    : "";
                            String outpVosName = Objects.nonNull(matchMap.get("outpVosName"))
                                    ? String.valueOf(matchMap.get("outpVosName"))
                                    : "";
                            String outpIopTypeOsName = Objects.nonNull(matchMap.get("outpIopOs"))
                                    ? String.valueOf(matchMap.get("outpIopOs"))
                                    : "";
                            String outpOdOs = "VOD:" + outpVodName + "  OD:" + outpIopTypeOdName + "  mmhg;" + "VOS:"
                                    + outpVosName + " OS:" + outpIopTypeOsName + "  mmhg;";
                            // 取住院视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            String currentVodName = Objects.nonNull(matchMap.get("currentVodName"))
                                    ? String.valueOf(matchMap.get("currentVodName"))
                                    : "";
                            String currentIopOdName = Objects.nonNull(matchMap.get("currentIopOd"))
                                    ? String.valueOf(matchMap.get("currentIopOd"))
                                    : "";
                            String currentVosName = Objects.nonNull(matchMap.get("currentVosName"))
                                    ? String.valueOf(matchMap.get("currentVosName"))
                                    : "";
                            String currentIopOsName = Objects.nonNull(matchMap.get("currentIopOs"))
                                    ? String.valueOf(matchMap.get("currentIopOs"))
                                    : "";
                            String inpOdOs = "VOD:" + currentVodName + "  OD:" + currentIopOdName + "  mmhg;" + "VOS:"
                                    + currentVosName + " OS:" + currentIopOsName + "  mmhg;";
                            // 入院视力眼压
                            obj.put("outpOdOs", outpOdOs);
                            // 出院视力眼压
                            obj.put("inpOdOs", inpOdOs);
                            // 事件经过描述
                            String desc_ = Objects.nonNull(matchMap.get("eventDesc"))
                                    ? String.valueOf(matchMap.get("eventDesc"))
                                    : "";
                            String desc_one = Objects.nonNull(matchMap.get("eventDescOne"))
                                    ? String.valueOf(matchMap.get("eventDescOne"))
                                    : "";
                            String desc_two = Objects.nonNull(matchMap.get("eventDescTwo"))
                                    ? String.valueOf(matchMap.get("eventDescTwo"))
                                    : "";
                            obj.put("eventDesc", desc_ + desc_one + desc_two);
                            // 住院诊断
                            obj.put("inpDiagName", matchMap.get("inpDiagName"));
                            // 门诊诊断
                            obj.put("visitDiagnose", matchMap.get("outpDiagName"));
                            // 目前情况
                            obj.put("curSituation", matchMap.get("currSituation"));
                        }
                    });
        }
    }

    // 跌倒/坠床事件
    public void wrapperAeTumbleFallbed(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.TUMBLE.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_TUMBLE_FALLBED",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.TUMBLE.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 如果obj.get("eventDesc")为空,取"跌倒后院内处置情况"；否则直接取“详细情况”
                            if (Objects.isNull(obj.get("eventDesc"))) {
                                obj.put("eventDesc",
                                        Objects.nonNull(matchMap.get("postFallCondition"))
                                                ? String.valueOf(matchMap.get("postFallCondition"))
                                                : "");
                            }
                        }
                    });
        }
    }

    // 给药错误事件
    public void wrapperAeDrugMistake(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.DRUGMISTAKE.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_DRUG_MISTAKE", "*",
                    " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.DRUGMISTAKE.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 如果obj.get("eventDesc")为空,取"药物名称"；否则直接取“详细情况”
                            if (Objects.isNull(obj.get("eventDesc"))) {
                                obj.put("eventDesc",
                                        Objects.nonNull(matchMap.get("drugName"))
                                                ? String.valueOf(matchMap.get("drugName"))
                                                : "");
                            }
                        }
                    });
            System.out.println(
                    EventEnum.DRUGMISTAKE.getEnumCode() + ":" + paramIds + ";busiList.size():" + busiList.size());
        }
    }

    // 标本采集不良事件
    public void wrapperAeSampleGather(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.SAMPLE.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_SAMPLE_GATHER",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.SAMPLE.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("detailInfo"))
                                            ? String.valueOf(matchMap.get("detailInfo"))
                                            : "");
                        }
                    });
            System.out.println(EventEnum.SAMPLE.getEnumCode() + ":" + paramIds + ";busiList.size():" + busiList.size());
        }
    }

    // 其他护理不良事件
    public void wrapperAeOtherCare(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OTHERCARE.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_OTHER_CARE", "*",
                    " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.OTHERCARE.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("detailInfo"))
                                            ? String.valueOf(matchMap.get("detailInfo"))
                                            : "");
                        }
                    });
        }
    }

    // 医院感染事件
    public void wrapperAeInfection(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.INFECTION.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(
                    aeCommonService.queryByQueryMapper("T_AE_INFECTION", "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.INFECTION.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 取门诊视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            String admissionVodName = Objects.nonNull(matchMap.get("admissionVodName"))
                                    ? String.valueOf(matchMap.get("admissionVodName"))
                                    : "";
                            String admissionVosName = Objects.nonNull(matchMap.get("admissionVosName"))
                                    ? String.valueOf(matchMap.get("admissionVosName"))
                                    : "";
                            String admissionIopOdName = Objects.nonNull(matchMap.get("admissionIopOd"))
                                    ? String.valueOf(matchMap.get("admissionIopOd"))
                                    : "";
                            String admissionIopOsName = Objects.nonNull(matchMap.get("admissionIopOs"))
                                    ? String.valueOf(matchMap.get("admissionIopOs"))
                                    : "";
                            String outpOdOs = "VOD:" + admissionVodName + "  OD:" + admissionIopOdName + "  mmhg;"
                                    + "VOS:" + admissionVosName + " OS:" + admissionIopOsName + "  mmhg;";
                            // 取住院视力眼压 显示：VOD:_____ OD _____mmhg VOS:______OD______mmhg
                            // 取出院视力或者当前视力都可以，目前取的出院视力
                            String inpOdOs;
                            if (Objects.nonNull(matchMap.get("dischargeDate"))) {
                                // 出院时间不为空，取出院视力眼压
                                String dischargeVodName = Objects.nonNull(matchMap.get("dischargeVodName"))
                                        ? String.valueOf(matchMap.get("dischargeVodName"))
                                        : "";
                                String dischargeIopTypeOdName = Objects.nonNull(matchMap.get("dischargeIopOd"))
                                        ? String.valueOf(matchMap.get("dischargeIopOd"))
                                        : "";
                                String dischargeVosName = Objects.nonNull(matchMap.get("dischargeVosName"))
                                        ? String.valueOf(matchMap.get("dischargeVosName"))
                                        : "";
                                String dischargeIopTypeOsName = Objects.nonNull(matchMap.get("dischargeIopOs"))
                                        ? String.valueOf(matchMap.get("dischargeIopOs"))
                                        : "";
                                inpOdOs = "VOD:" + dischargeVodName + "  OD:" + dischargeIopTypeOdName + "  mmhg;"
                                        + "VOS:" + dischargeVosName + " OS:" + dischargeIopTypeOsName + "  mmhg;";
                            } else {
                                // 当前视力眼压
                                String dischargeVodName = Objects.nonNull(matchMap.get("currentVodName"))
                                        ? String.valueOf(matchMap.get("currentVodName"))
                                        : "";
                                String dischargeIopTypeOdName = Objects.nonNull(matchMap.get("currentIopOd"))
                                        ? String.valueOf(matchMap.get("currentIopOd"))
                                        : "";
                                String dischargeVosName = Objects.nonNull(matchMap.get("currentVosName"))
                                        ? String.valueOf(matchMap.get("currentVosName"))
                                        : "";
                                String dischargeIopTypeOsName = Objects.nonNull(matchMap.get("currentIopOs"))
                                        ? String.valueOf(matchMap.get("currentIopOs"))
                                        : "";
                                inpOdOs = "VOD:" + dischargeVodName + "  OD:" + dischargeIopTypeOdName + "  mmhg;"
                                        + "VOS:" + dischargeVosName + " OS:" + dischargeIopTypeOsName + "  mmhg;";
                            }
                            // 当前/入院视力眼压
                            obj.put("outpOdOs", outpOdOs);
                            // 出院视力眼压
                            obj.put("inpOdOs", inpOdOs);
                            // 事件经过描述
                            String desc_ = Objects.nonNull(matchMap.get("eventDesc"))
                                    ? String.valueOf(matchMap.get("eventDesc"))
                                    : "";
                            String desc_one = Objects.nonNull(matchMap.get("eventDescOne"))
                                    ? String.valueOf(matchMap.get("eventDescOne"))
                                    : "";
                            String desc_two = Objects.nonNull(matchMap.get("eventDescTwo"))
                                    ? String.valueOf(matchMap.get("eventDescTwo"))
                                    : "";
                            obj.put("eventDesc", desc_ + desc_one + desc_two);
                            // 住院诊断
                            obj.put("inpDiagName", matchMap.get("inpDiagName"));
                            // 门诊诊断
                            obj.put("visitDiagnose", matchMap.get("outpDiagName"));
                            // 目前情况
                            obj.put("curSituation", matchMap.get("currSituation"));
                        }
                    });
        }
    }

    // 职业暴露事件
    public void wrapperAeOccupationExposure(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OCCUPATIONEXPOSURE.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService
                    .queryByQueryMapper("T_AE_OCCUPATION_EXPOSURE", "*", " ID in(" + String.join(",", bi) + ")")));
            // System.out.println(EventEnum.OCCUPATIONEXPOSURE.getEnumCode() + ":" +
            // paramIds + ";busiList.size():" + busiList.size());
        }
    }

    // 其他信息、行政、公共安全事件不良事件
    public void wrapperAeOther(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.OTHER.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(
                    aeCommonService.queryByQueryMapper("T_AE_OTHER", "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode")).equalsIgnoreCase(EventEnum.OTHER.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述
                            obj.put("eventDesc",
                                    Objects.nonNull(matchMap.get("detailInfo"))
                                            ? String.valueOf(matchMap.get("detailInfo"))
                                            : "");
                        }
                    });
            System.out.println(EventEnum.OTHER.getEnumCode() + ":" + paramIds + ";busiList.size():" + busiList.size());
        }
    }

    // 点评情况
    public void wrapperAeOperationRecord(List<Map<String, Object>> dataList) {
        List<List<String>> basicIds = getBasicIds(dataList);
        if (Objects.nonNull(basicIds) && basicIds.size() > 0) {
            List<Map<String, Object>> recordList = Lists.newArrayList();
            basicIds.stream().forEach(bi -> recordList.addAll(aeCommonService
                    .queryByQueryMapper("T_AE_OPERATION_RECORD", "*", " BASIC_ID in(" + String.join(",", bi) + ")")));
            dataList.stream().forEach(obj -> {
                // 省区点评
                List<Map<String, Object>> provList = recordList.stream()
                        .filter(rl -> String.valueOf(rl.get("basicId")).equalsIgnoreCase(String.valueOf(obj.get("id")))
                                &&
                                String.valueOf(rl.get("node"))
                                        .equalsIgnoreCase(NodeEnum.PROVINCE_REVIEWS.getSeq().toString())
                                &&
                                String.valueOf(rl.get("type"))
                                        .equalsIgnoreCase(OperateEnum.REVIEW.getType().toString()))
                        .collect(Collectors.toList());
                if (Objects.nonNull(provList) && provList.size() > 0) {
                    obj.put("provinceRecord",
                            provList.stream().map(
                                            pl -> Objects.nonNull(pl.get("opinion")) ? String.valueOf(pl.get("opinion")) : "")
                                    .collect(Collectors.joining(";")));
                }
                // 集团点评
                List<Map<String, Object>> groupList = recordList.stream().filter(rl -> String.valueOf(rl.get("basicId"))
                                .equalsIgnoreCase(String.valueOf(obj.get("id"))) &&
                                String.valueOf(rl.get("node")).equalsIgnoreCase(NodeEnum.GROUP_REVIEWS.getSeq().toString()) &&
                                String.valueOf(rl.get("type")).equalsIgnoreCase(OperateEnum.REVIEW.getType().toString()))
                        .collect(Collectors.toList());
                if (Objects.nonNull(groupList) && groupList.size() > 0) {
                    obj.put("groupRecord",
                            groupList.stream().map(
                                            pl -> Objects.nonNull(pl.get("opinion")) ? String.valueOf(pl.get("opinion")) : "")
                                    .collect(Collectors.joining(";")));
                }
                // 专家点评
                List<Map<String, Object>> expectList = recordList.stream().filter(rl -> String
                                .valueOf(rl.get("basicId")).equalsIgnoreCase(String.valueOf(obj.get("id"))) &&
                                String.valueOf(rl.get("node")).equalsIgnoreCase(NodeEnum.EXPERT_REVIEWS.getSeq().toString()) &&
                                String.valueOf(rl.get("type")).equalsIgnoreCase(OperateEnum.REVIEW.getType().toString()))
                        .collect(Collectors.toList());
                if (Objects.nonNull(expectList) && expectList.size() > 0) {
                    obj.put("expectRecord",
                            expectList.stream().map(
                                            pl -> Objects.nonNull(pl.get("opinion")) ? String.valueOf(pl.get("opinion")) : "")
                                    .collect(Collectors.joining(";")));
                }
            });
        }
    }

    // 非计划再手术上报
    public void wrapperAeUnplReoperation(List<Map<String, Object>> dataList) {
        List<List<String>> paramIds = getParamIds(dataList, EventEnum.UNPLREOPERATION.getEnumCode());
        if (Objects.nonNull(paramIds) && paramIds.size() > 0) {
            List<Map<String, Object>> busiList = Lists.newArrayList();
            paramIds.stream().forEach(bi -> busiList.addAll(aeCommonService.queryByQueryMapper("T_AE_UNPL_REOPERATION",
                    "*", " ID in(" + String.join(",", bi) + ")")));
            dataList.stream()
                    .filter(obj -> String.valueOf(obj.get("eventCode"))
                            .equalsIgnoreCase(EventEnum.UNPLREOPERATION.getEnumCode()))
                    .collect(Collectors.toList()).stream().forEach(obj -> {
                        Optional<Map<String, Object>> aeLoopBusiOpt = busiList.stream().filter(
                                        bl -> String.valueOf(bl.get("id")).equalsIgnoreCase(String.valueOf(obj.get("eventId"))))
                                .findFirst();
                        if (aeLoopBusiOpt.isPresent()) {
                            Map<String, Object> matchMap = aeLoopBusiOpt.get();
                            // 事件经过描述需要拼接字段（字段名称和内容都拼接）,包括：病情摘要、首次手术情况及术后情况、再次手术原因和目的
                            StringBuffer eventDesc = new StringBuffer();
                            if (Objects.nonNull(matchMap.get("condSummary"))) {
                                eventDesc.append("病情摘要：").append(MapUtils.getString(matchMap,"condSummary").trim()).append(";\n");
                            }
                            if (Objects.nonNull(matchMap.get("firstSurgSit"))) {
                                eventDesc.append("首次手术情况及术后情况：").append(MapUtils.getString(matchMap,"firstSurgSit").trim()).append(";\n");
                            }
                            if (Objects.nonNull(matchMap.get("reopReason"))) {
                                eventDesc.append("再次手术原因和目的：").append(MapUtils.getString(matchMap,"reopReason").trim()).append(";\n");
                            }
                            if (eventDesc.length() > 0) {
                                obj.put("eventDesc", eventDesc.toString());
                            }
                        }
                    });
        }
    }

    public static String getDateYMD() {
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH) + 1)
                .append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY))
                .append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

    public static List<List<String>> getParamIds(List<Map<String, Object>> dataList, String enumCode) {
        // String paramIds = dataList.stream().filter(obj ->
        // String.valueOf(obj.get("eventCode")).equalsIgnoreCase(enumCode)).map(obj ->
        // String.valueOf(obj.get("eventId"))).collect(Collectors.joining(","));
        List<String> paramLists = dataList.stream()
                .filter(obj -> String.valueOf(obj.get("eventCode")).equalsIgnoreCase(enumCode))
                .map(obj -> String.valueOf(obj.get("eventId"))).collect(Collectors.toList());
        return splitList(paramLists, splitNum);
    }

    public static List<List<String>> getBasicIds(List<Map<String, Object>> dataList) {
        // String basicIds = dataList.stream().map(obj ->
        // String.valueOf(obj.get("id"))).collect(Collectors.joining(","));
        List<String> paramLists = dataList.stream().map(obj -> String.valueOf(obj.get("id")))
                .collect(Collectors.toList());
        return splitList(paramLists, splitNum);
    }

    public static List<List<String>> splitList(List<String> list, int listSize) {
        return Lists.partition(list, listSize);
    }

}
