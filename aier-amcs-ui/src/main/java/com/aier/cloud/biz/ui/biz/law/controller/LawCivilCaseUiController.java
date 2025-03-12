package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.api.amcs.law.domain.*;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawCivilCaseFeignService;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/ui/amcs/law/civilCase")
public class LawCivilCaseUiController extends LawBaseUiController {

    @Autowired
    private LawCivilCaseFeignService lawCivilCaseFeignService;

    private static final String CIVIL_CASE_INFO = "amcs/law/civilCase/civilCaseInfo";
    private static final String CIVIL_CASE_LIST = "amcs/law/civilCase/civilCaseList";


    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request){
        return CIVIL_CASE_LIST;
    }

    @RequestMapping(value = "/queryCivilCaseList", method = { RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> queryCivilCaseList(){
        LawCivilCaseCondition cond = new LawCivilCaseCondition();
        return lawCivilCaseFeignService.findListByCond(cond);
    }

    /**
     * 民事诉讼仲裁案件列表页面
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String civilCaseList(HttpServletRequest request,Long bizId) {
        LawCivilCase lawCivilCase = new LawCivilCase();
        if(Objects.nonNull(bizId) && bizId.longValue()>0){
            lawCivilCase = lawCivilCaseFeignService.getLawCivilCase(bizId);
        }
        infoHandler(request,bizId,lawCivilCase.getInstId(),lawCivilCase);
        return CIVIL_CASE_INFO;
    }

    /**
     * 查询民事诉讼仲裁案件分页数据
     */
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(LawCivilCaseCondition cond) {
        PageResponse<Map<String, Object>> result = new PageResponse<>();
        return result;
    }

    /**
     * 查询所有民事诉讼仲裁案件实体数据
     */
    @RequestMapping(value = "/getAllEntity", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(LawCivilCaseCondition cond) {
        List<Map<String, Object>> list = Lists.newArrayList();
        return list;
    }

    /**
     * 保存或更新民事诉讼仲裁案件信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawCivilCase lawCivilCase) {
        boolean saveAttachFlag = false;
        if(Objects.isNull(lawCivilCase.getId())){
            saveAttachFlag = true;
        }
        Map<String,Object> result = lawCivilCaseFeignService.save(lawCivilCase);
        Long civilCaseId = MapUtils.getLong(result,"civilCaseId");
        saveHandler(civilCaseId,"1",lawCivilCase.getBizCode(),saveAttachFlag,lawCivilCase.getAttachs());
        return success(MapUtils.getString(result,"msg"),"bizId",civilCaseId);
    }

    /**
     * 初始流程提交
     * @param lawCivilCase
     * @return
     */
    @RequestMapping(value = "/initCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object initCommit(@RequestBody LawCivilCase lawCivilCase){
        // 1.保存业务信息
        Map<String,Object> result = (Map<String,Object>)this.save(lawCivilCase);
        // 2.判断选取流程信息表中哪个流程
        Long flowId = getFlowId();
        // 3.保存流程实例信息
        Long civilCaseId = MapUtils.getLong(result,"bizId");
        LawFlowInstance lawFlowInstance = createFlowInstance(flowId,civilCaseId,lawCivilCase.getBizCode(),1,"民事诉讼仲裁案件");
        // 4.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + civilCaseId);
        // 5.保存流程明细表信息
        addLawDetailInfo(lawFlowInstance,"SUBMIT");
        // 6.更新session中的流程明细信息
        ShiroUtils.removeSessionAttr("SessionLawAuditDetails_" + lawFlowInstance.getId());
        // TODO 发送企业微信消息
        return success("提交成功！");
    }


    /**
     * 流程中提交
     * @param dataMaps
     * @return
     */
    @RequestMapping(value = "/flowCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object flowCommit(@RequestBody Map<String,Object> dataMaps){
        JSONObject data = (JSONObject) dataMaps.get("bizForm");
        String opinion = MapUtils.getString(dataMaps,"opinion");
        LawCivilCase lawCivilCase = data.toJavaObject(LawCivilCase.class);
        Map<String,Object> retMaps = processFlow(lawCivilCase.getId(),opinion,"SUBMIT");
        if(MapUtils.getBoolean(retMaps,"saveFlag")){
            this.save(lawCivilCase);
        }
        // TODO 如果当前机构不存抄送节点所授权的人员，怎么办？
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(lawCivilCase.getId());
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
        LawFlowNode currentNode = lawFlowNodes.stream().filter(node -> node.getNodeId().longValue() == Long.valueOf(lawFlowInstance.getCurrentNode()).longValue()).findFirst().get();
        if(currentNode.getNodeType().equals("CC")){
            //抄送节点
            processFlow(lawCivilCase.getId(),null,"CC");
        }
        return success();
    }


}