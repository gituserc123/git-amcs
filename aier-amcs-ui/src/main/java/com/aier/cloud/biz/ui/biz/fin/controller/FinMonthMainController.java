package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsComment;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMonthFeignService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-27 15:38
 **/
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finMonthMain")
public class FinMonthMainController  extends BaseController {
    @Autowired
    FinMonthFeignService finMonthFeignService;
    @Autowired
    AdverseEventService adverseEventService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private HospHandleService hospHandleService;
    private static final String INDEX="amcs/fin/business/FinInsMonthManage";


    @RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST})
    public String index(Model model){
        if(!UserContext.getIsHospUser()){
            throw new BizException("当前登录账号不是医院用户，请以医院账号登录进行填报！");
        }
        model.addAttribute("hospId", UserContext.getTenantId());
        return INDEX;
    };
    public void wrapperCond(FinInsMonthCondition cond){
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
//                    Institution inst = institutionService.getByAhisHosp(Integer.getInteger(cond.getHospId().toString()));
                    cond.setHospId(cond.getHospId());
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
    @RequestMapping(value = "/unReport",method = { RequestMethod.POST})
    @ResponseBody
    public List<Map<String,Object>> unReport(FinInsMonthCondition cond) {
        this.wrapperCond(cond);
        return finMonthFeignService.unReport(cond);
    }
    @RequestMapping(value = "/getList",method = { RequestMethod.POST})
    @ResponseBody
    public List<Map<String,Object>> getList(FinInsMonthCondition cond){
        if(!ObjectUtils.isEmpty(cond.getStatusStr())){
            cond.setStatus(Arrays.stream(cond.getStatusStr().split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }
        this.wrapperCond(cond);
        return finMonthFeignService.getList(cond);
    }

    @RequestMapping(value = "/new",method = { RequestMethod.POST})
    @ResponseBody
    public Object newLine(@RequestParam("hospId") Long hospId){
        if(finMonthFeignService.newLine(hospId)){
            return this.success("初始化成功");
        }else{
            return this.fail();
        }
    }

    @RequestMapping(value = "/submitToProvince",method = { RequestMethod.POST})
    @ResponseBody
    public Object submitToProvince(@RequestParam("id") Long id){
        if(finMonthFeignService.submitToProvince(id)){
            return this.success("提交成功！！");
        }else{
            return this.fail("存在未完成报表，请完成后再提交");
        }
    }

    @RequestMapping(value = "/submitToGroup",method = { RequestMethod.POST})
    @ResponseBody
    public Object submitToGroup(@RequestParam("id") Long id){
        if(finMonthFeignService.submitToGroup(id)){
            return this.success("提交成功！！");
        }else{
            return this.fail("提交失败！");
        }
    }

    @RequestMapping(value = "/returnToHos",method = { RequestMethod.POST})
    @ResponseBody
    public Object returnToHos(FinInsComment finInsComment){
        if(finMonthFeignService.commentSave(finInsComment)&&finMonthFeignService.returnToHos(finInsComment.getAssociatedId())){
            return this.success("提交成功！！");
        }else{
            return this.fail("提交失败！");
        }
    }
    @RequestMapping(value = "/getCommentByAssociatedId",method = { RequestMethod.POST})
    @ResponseBody
    public  List<Map<String, Object>> getCommentByAssociatedId(@RequestParam("associatedId")Long  associatedId){
        return finMonthFeignService.getCommentByAssociatedId(associatedId);
    }
    @RequestMapping(value = "/deleteByMonthId",method = { RequestMethod.POST})
    @ResponseBody
    public Object deleteByMonthId(@RequestParam("monthId")String monthId){

        if (finMonthFeignService.deleteByMonthId(monthId)){
            return this.success("删除成功！！");
        }else{
            return this.fail("删除失败！");
        }
    }
}
