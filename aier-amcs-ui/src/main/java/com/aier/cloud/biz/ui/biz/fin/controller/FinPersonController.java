package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.PersonCondition;
import com.aier.cloud.api.amcs.fin.domain.Person;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinPersonService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: amcs
 * @description: 相关人员
 * @author: hsw
 * @create: 2023-08-30 11:17
 **/

@Controller
@RequestMapping("/ui/amcs/fin/config/person")
public class FinPersonController extends BaseController {
    @Autowired
    FinPersonService finPersonService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private HospHandleService hospHandleService;

    private static final String PERSON_INDEX="amcs/fin/config/person";
    private static final String PERSON_MANAGE="amcs/fin/config/PersonManage";
    @RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST})
    public String index(Model model){
        Long hospId=UserContext.getTenantId();
        Person person=finPersonService.getPersonByHospId(hospId);
        model.addAttribute("hospId", hospId);
        if(person!=null) {
            JSONObject jo=(JSONObject) JSON.toJSON(person);
            jo.put("id",jo.getString("id"));
            jo.put("modifer",jo.getString("modifer"));
            model.addAttribute("person", jo);
        }else{
            model.addAttribute("person", "undefined");
        }

        return PERSON_INDEX;
    };

    @RequestMapping(value = "/personManage",method = { RequestMethod.GET, RequestMethod.POST})
    public String personManage(Model model){
        Long hospId=UserContext.getTenantId();
        model.addAttribute("hospId", hospId);
        return PERSON_MANAGE;
    };

    @RequestMapping(value = "/save",method = { RequestMethod.POST})
    @ResponseBody
    public Object save(@RequestBody Person person){
        return this.success(finPersonService.savePerson(person));
    }

    @RequestMapping(value="/list",method = { RequestMethod.POST})
    @ResponseBody
    public List<Map<String,Object>> list(PersonCondition personCondition){
        this.paramHandle(personCondition);
        List<Map<String,Object>> dataList = finPersonService.getPersonList(personCondition);
        if (Objects.nonNull(dataList) && dataList.size() > 0) {
            Map<String, Map> existHosp = new HashMap<>();
            dataList.stream().forEach(obj -> {
                if (Objects.nonNull(obj.get("hospId"))) {
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
            });
        }
        return dataList;
    }

    @RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcel(String paramJson) throws IOException {
        PersonCondition personCondition = JSON.parseObject(paramJson,PersonCondition.class);
        this.paramHandle(personCondition);
        List<Map<String,Object>> dataList = finPersonService.getPersonList(personCondition);
        if (Objects.nonNull(dataList) && dataList.size() > 0) {
            Map<String, Map> existHosp = new HashMap<>();
            dataList.stream().forEach(obj -> {
                if (Objects.nonNull(obj.get("hospId"))) {
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
            });
            this.returnResult(dataList,"WEB-INF/views/amcs/fin/excelTemplate/","FinPersonList","医保价格人员_"+getDateYMD());
        }
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

    public void paramHandle(PersonCondition personCondition){
        if (personCondition.getProvince() != null) {
            if (personCondition.getHospId() != null && personCondition.getHospId() > 0) {
                Institution inst = institutionService.getById(personCondition.getHospId());
                personCondition.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                personCondition.setHospList(null);
            } else {
                Object instList = hospHandleService.allHospFromParent(personCondition.getProvince());
                if (instList != null) {
                    JSONArray ja = (JSONArray) instList;
                    ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                    ja.stream().forEach(j -> {
                        JSONObject jo = (JSONObject) j;
                        hospList.add(jo.getLong("ahisHosp"));
                    });
                    // 如果hospList为空，说明当前机构下没有医院，直接返回
                    if (hospList.size() > 0) {
                        personCondition.setHospList(hospList);
                        personCondition.setHospId(null);
                    } else {
                        personCondition.setHospId(0L);
                    }
                }
            }
        }else{
            if (personCondition.getHospId() != null && personCondition.getHospId() > 0) {
                Institution inst = institutionService.getById(personCondition.getHospId());
                personCondition.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                personCondition.setHospList(null);
            }
        }
    }

    public static String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }
}
