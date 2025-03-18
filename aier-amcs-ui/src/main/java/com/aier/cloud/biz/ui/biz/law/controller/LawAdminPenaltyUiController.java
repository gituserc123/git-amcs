package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.api.amcs.law.domain.LawAdminPenalty;
import com.aier.cloud.api.amcs.law.domain.LawFlowInstance;
import com.aier.cloud.api.amcs.law.domain.LawFlowNode;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawAdminPenaltyFeignService;
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
@RequestMapping("/ui/amcs/law/adminPenalty")
public class LawAdminPenaltyUiController extends LawBaseUiController {

    @Autowired
    private LawAdminPenaltyFeignService lawAdminPenaltyFeignService;

    @Autowired
    private LawDictTypeFeignService lawDictTypeFeignService;

    private static final String ADMIN_PENALTY_INFO = "amcs/law/adminPenalty/adminPenaltyInfo";
    private static final String ADMIN_PENALTY_LIST = "amcs/law/adminPenalty/adminPenaltyList";

    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        return ADMIN_PENALTY_LIST;
    }

    @RequestMapping(value = "/queryAdminPenaltyList", method = { RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> queryAdminPenaltyList(LawAdminPenaltyCondition cond) {
        PageResponse<Map<String, Object>> resultPages = lawAdminPenaltyFeignService.findListByCond(cond);
        List<Map<String, Object>> result = resultPages.getRows();
        if(CollectionUtils.isNotEmpty(result) && result.size() > 0){
            List<Map> documentTypeDict = lawDictTypeFeignService.selectByTypeCode("document_type");
            List<Map> penaltyReasonDict = lawDictTypeFeignService.selectByTypeCode("penalty_reason");
            List<Map> penaltyCategoryDict = lawDictTypeFeignService.selectByTypeCode("penalty_category");
            for(Map<String, Object> map : result){
                if(Objects.nonNull(MapUtils.getString(map, "documentType"))){
                    Map documentType = documentTypeDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "documentType"))).findFirst().get();
                    map.put("documentTypeTxt", MapUtils.getString(documentType,"valueDesc"));
                }
                if(Objects.nonNull(MapUtils.getString(map, "penaltyReason"))){
                    Map penaltyReasonType = penaltyReasonDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "penaltyReason"))).findFirst().get();
                    map.put("penaltyReasonTxt", MapUtils.getString(penaltyReasonType,"valueDesc"));
                }
                if(Objects.nonNull(MapUtils.getString(map, "penaltyCategory"))){
                    Map penaltyCategory = penaltyCategoryDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "penaltyCategory"))).findFirst().get();
                    map.put("penaltyCategoryTxt", MapUtils.getString(penaltyCategory,"valueDesc"));
                }
            }
        }
        return resultPages;
    }

    /**
     * 行政处罚事项信息页面
     */
    @RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
    public String adminPenaltyInfo(HttpServletRequest request, Long bizId) {
        LawAdminPenalty lawAdminPenalty = new LawAdminPenalty();
        if (Objects.nonNull(bizId) && bizId.longValue() > 0) {
            lawAdminPenalty = lawAdminPenaltyFeignService.getLawAdminPenalty(bizId);
        }
        infoHandler(request, bizId, lawAdminPenalty.getInstId(), lawAdminPenalty);
        return ADMIN_PENALTY_INFO;
    }

    /**
     * 查询行政处罚事项分页数据
     */
    @RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(LawAdminPenaltyCondition cond) {
        PageResponse<Map<String, Object>> result = new PageResponse<>();
        return result;
    }

    /**
     * 查询所有行政处罚事项实体数据
     */
    @RequestMapping(value = "/getAllEntity", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(LawAdminPenaltyCondition cond) {
        List<Map<String, Object>> list = Lists.newArrayList();
        return list;
    }

    /**
     * 保存或更新行政处罚事项信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawAdminPenalty lawAdminPenalty) {
        boolean saveAttachFlag = false;
        if (Objects.isNull(lawAdminPenalty.getId())) {
            saveAttachFlag = true;
            Institution parentInst = getParentInst();
            lawAdminPenalty.setSuperInstId(parentInst.getId());
            lawAdminPenalty.setSuperInstName(parentInst.getName());
            lawAdminPenalty.setStatus(1);
        }
        Map<String, Object> result = lawAdminPenaltyFeignService.save(lawAdminPenalty);
        Long adminPenaltyId = MapUtils.getLong(result, "adminPenaltyId");
        saveHandler(adminPenaltyId, lawAdminPenalty.getBizType(), lawAdminPenalty.getBizCode(), saveAttachFlag, lawAdminPenalty.getAttachs());
        return success(MapUtils.getString(result, "msg"), "bizId", adminPenaltyId);
    }

    /**
     * 初始流程提交
     *
     * @param lawAdminPenalty
     * @return
     */
    @RequestMapping(value = "/initCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object initCommit(@RequestBody LawAdminPenalty lawAdminPenalty) {
        // 1.保存业务信息
        lawAdminPenalty.setStatus(2);
        Map<String, Object> result = (Map<String, Object>) this.save(lawAdminPenalty);
        // 2.判断选取流程信息表中哪个流程
        Long flowId = getFlowId();
        // 3.保存流程实例信息
        Long adminPenaltyId = MapUtils.getLong(result, "bizId");
        LawFlowInstance lawFlowInstance = createFlowInstance(flowId, adminPenaltyId, lawAdminPenalty.getBizCode(), 3, "行政处罚事项");
        // 4.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + adminPenaltyId);
        // 5.保存流程明细表信息
        addLawDetailInfo(lawFlowInstance, "SUBMIT");
        // 6.更新session中的流程明细信息
        ShiroUtils.removeSessionAttr("SessionLawAuditDetails_" + lawFlowInstance.getId());
        // TODO 发送企业微信消息
        return success("提交成功！");
    }

    /**
     * 流程中提交
     *
     * @param dataMaps
     * @return
     */
    @RequestMapping(value = "/flowCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object flowCommit(@RequestBody Map<String, Object> dataMaps) {
        JSONObject data = (JSONObject) dataMaps.get("bizForm");
        String opinion = MapUtils.getString(dataMaps, "opinion");
        LawAdminPenalty lawAdminPenalty = data.toJavaObject(LawAdminPenalty.class);
        Map<String, Object> retMaps = processFlow(lawAdminPenalty.getId(), opinion, "SUBMIT");
        if (MapUtils.getBoolean(retMaps, "saveFlag")) {
            this.save(lawAdminPenalty);
        }
        // TODO 如果当前机构不存在抄送节点所授权的人员，怎么办？
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(lawAdminPenalty.getId());
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
        LawFlowNode currentNode = lawFlowNodes.stream().filter(node -> node.getNodeId().longValue() == Long.valueOf(lawFlowInstance.getCurrentNode()).longValue()).findFirst().get();
        if (currentNode.getNodeType().equals("CC")) {
            // 抄送节点
            processFlow(lawAdminPenalty.getId(), null, "CC");
        }
        return success();
    }
}