package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.InstService;
import com.aier.cloud.center.common.context.UserContext;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-05 11:02
 **/

@Controller
@RequestMapping("/ui/amcs/adverse/eventManage/")
public class EventManageController extends BaseController {

    @Autowired
    private InstService instService;


    private static final String EVENT_CHOOSE="amcs/adverseEvent/eventManage/eventChoose";

    private static final String UNPLREOPERATION="amcs/adverseEvent/eventManage/AeUnplReoperationManage";


    @RequestMapping(value = "/eventChoose",method = { RequestMethod.GET, RequestMethod.POST})
    public String eventChoose(HttpServletRequest request, Model model, @RequestParam(value = "patientNo", required = false) String patientNo){
    	request.setAttribute("patientNo", patientNo);
    	return EVENT_CHOOSE;
    };

    @RequestMapping(value = "/unplReoperation",method = { RequestMethod.GET, RequestMethod.POST})
    public String unplReoperation(HttpServletRequest request, Model model, @RequestParam(value = "inpNumber", required = false) String inpNumber){
        Long deptId = ShiroUtils.getDeptId();
        Institution institution = instService.getDeptDetailByInstId(deptId);
        if (!ObjectUtils.isEmpty(institution)) {
            request.setAttribute("department", institution.getName());
        }
        request.setAttribute("showOperate", true);
        request.setAttribute("pageType", 0);
        request.setAttribute("operate", true);
        request.setAttribute("isIncrease", false);
        request.setAttribute("hospId", ShiroUtils.getTenantId());
        request.setAttribute("instId", ShiroUtils.getInstId());
        request.setAttribute("hospName", ShiroUtils.getInstName());

        Map<String, Object> aeInfo = Maps.newHashMap();
        aeInfo.put("reportTimes", 1);
        aeInfo.put("patientNo", inpNumber);
        request.setAttribute("ae", aeInfo);
        request.setAttribute("eventCode", EventEnum.UNPLREOPERATION.getEnumCode());
        request.setAttribute("tags", 1);
        request.setAttribute("comboboxFilter", EventEnum.UNPLREOPERATION.getFilter());
    	return UNPLREOPERATION;
    };



}
