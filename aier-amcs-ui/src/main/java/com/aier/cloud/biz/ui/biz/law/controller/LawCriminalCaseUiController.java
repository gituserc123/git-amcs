package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.api.amcs.law.domain.LawCriminalCase;
import com.aier.cloud.api.amcs.law.domain.LawFlowInstance;
import com.aier.cloud.api.amcs.law.domain.LawFlowNode;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawCriminalCaseFeignService;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.ui.biz.law.feign.LawDictTypeFeignService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/ui/amcs/law/criminalCase")
public class LawCriminalCaseUiController extends LawBaseUiController {

    @Autowired
    private LawCriminalCaseFeignService lawCriminalCaseFeignService;

    @Autowired
    private LawDictTypeFeignService lawDictTypeFeignService;

    private static final String CRIMINAL_CASE_INFO = "amcs/law/criminalCase/criminalCaseInfo";
    private static final String CRIMINAL_CASE_LIST = "amcs/law/criminalCase/criminalCaseList";

    /**
     * 列表页面跳转
     */
    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request){
        request.setAttribute("empType", getEmpType());
        request.setAttribute("instId", ShiroUtils.getInstId());
        return CRIMINAL_CASE_LIST;
    }

    /**
     * 分页查询案件列表
     */
    @RequestMapping(value = "/queryCriminalCaseList", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<Map<String, Object>> queryCriminalCaseList(LawCriminalCaseCondition cond){
        Boolean isRetNull = wrapperCond(cond);
        if(isRetNull){
            return new PageResponse<>();
        }
        PageResponse<Map<String, Object>> resultPages = lawCriminalCaseFeignService.findListByCond(cond);
        List<Map<String, Object>> result = resultPages.getRows();
        if(CollectionUtils.isNotEmpty(result) && result.size() > 0){
            List<Map> criminalCaseTypeDict = lawDictTypeFeignService.selectByTypeCode("criminal_case_type");
            List<Map> litigationPhaseDict = lawDictTypeFeignService.selectByTypeCode("criminal_litigation_phase");
            for(Map<String, Object> map : result){
                if(Objects.nonNull(MapUtils.getString(map, "caseType"))){
                    Map criminalCaseType = criminalCaseTypeDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "caseType"))).findFirst().get();
                    map.put("caseTypeTxt", MapUtils.getString(criminalCaseType,"valueDesc"));
                    if(Objects.nonNull(MapUtils.getString(map, "chargeName"))){
                        List<Map<String, Object>> chargeNameDict = lawDictTypeFeignService.selectSubDicts(MapUtils.getString(criminalCaseType,"typeCode"), MapUtils.getString(criminalCaseType,"valueCode"));
                        Map<String, Object> chargeName = chargeNameDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "chargeName"))).findFirst().get();
                        map.put("chargeNameTxt", MapUtils.getString(chargeName,"valueDesc"));
                    }
                }
                if(Objects.nonNull(MapUtils.getString(map, "litigationPhase"))){
                    Map litigationPhase = litigationPhaseDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "litigationPhase"))).findFirst().get();
                    map.put("litigationPhaseTxt", MapUtils.getString(litigationPhase,"valueDesc"));
                }
            }

        }
        return resultPages;
    }

    /**
     * 案件详情页面
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String criminalCaseInfo(HttpServletRequest request, Long bizId) {
        LawCriminalCase lawCriminalCase = new LawCriminalCase();
        if (Objects.nonNull(bizId) && bizId.longValue()>0) {
            lawCriminalCase = lawCriminalCaseFeignService.getLawCriminalCase(bizId);
        }
        infoHandler(request, bizId, lawCriminalCase.getInstId(), lawCriminalCase);
        return CRIMINAL_CASE_INFO;
    }

    /**
     * 查询民事诉讼仲裁案件分页数据
     */
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(LawCriminalCaseCondition cond) {
        PageResponse<Map<String, Object>> result = new PageResponse<>();
        return result;
    }

    /**
     * 查询所有民事诉讼仲裁案件实体数据
     */
    @RequestMapping(value = "/getAllEntity", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(LawCriminalCaseCondition cond) {
        List<Map<String, Object>> list = Lists.newArrayList();
        return list;
    }

    /**
     * 保存案件信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawCriminalCase lawCriminalCase) {
        boolean saveAttachFlag = false;
        if(Objects.isNull(lawCriminalCase.getId())){
            saveAttachFlag = true;
            Institution parentInst = getParentInst();
            lawCriminalCase.setSuperInstId(parentInst.getId());
            lawCriminalCase.setSuperInstName(parentInst.getName());
            lawCriminalCase.setStatus(1);
        }
        Map<String, Object> result = lawCriminalCaseFeignService.save(lawCriminalCase);
        Long criminalCaseId = MapUtils.getLong(result, "criminalCaseId");
        saveHandler(criminalCaseId, lawCriminalCase.getBizType(), lawCriminalCase.getBizCode(), saveAttachFlag, lawCriminalCase.getAttachs());
        return success(MapUtils.getString(result, "msg"), "bizId", criminalCaseId);
    }

    /**
     * 流程初次提交
     */
    @RequestMapping(value = "/initCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object initCommit(@RequestBody LawCriminalCase lawCriminalCase) {
        // 1.保存业务信息
        lawCriminalCase.setStatus(2);
        Map<String,Object> result = (Map<String,Object>)this.save(lawCriminalCase);
        // 2.判断选取流程信息表中哪个流程
        Long flowId = getFlowId();
        // 3.保存流程实例信息
        Long criminalCaseId = MapUtils.getLong(result,"bizId");
        LawFlowInstance lawFlowInstance = createFlowInstance(flowId,criminalCaseId,lawCriminalCase.getBizCode(),2,"刑事案件");
        // 4.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + criminalCaseId);
        // 5.保存流程明细表信息
        addLawDetailInfo(lawFlowInstance,"SUBMIT");
        // 6.更新session中的流程明细信息
        ShiroUtils.removeSessionAttr("SessionLawAuditDetails_" + lawFlowInstance.getId());
        // TODO 发送企业微信消息
        return success("提交成功！");
    }

    /**
     * 流程节点提交
     */
    @RequestMapping(value = "/flowCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object flowCommit(@RequestBody Map<String, Object> dataMaps) {
        JSONObject data = (JSONObject) dataMaps.get("bizForm");
        String opinion = MapUtils.getString(dataMaps, "opinion");
        LawCriminalCase caseData = data.toJavaObject(LawCriminalCase.class);
        Map<String, Object> retMaps = processFlow(caseData.getId(), opinion, "SUBMIT");
        if (MapUtils.getBoolean(retMaps, "saveFlag")) {
            this.save(caseData);
        }
        // 处理抄送节点
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(caseData.getId());
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
        LawFlowNode currentNode = lawFlowNodes.stream().filter(node -> node.getNodeId().longValue() == Long.valueOf(lawFlowInstance.getCurrentNode()).longValue()).findFirst().get();
        if ("CC".equals(currentNode.getNodeType())) {
            processFlow(caseData.getId(), null, "CC");
        }
        return success();
    }

}