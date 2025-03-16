package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.api.amcs.law.domain.LawDisputeMatter;
import com.aier.cloud.api.amcs.law.domain.LawFlowInstance;
import com.aier.cloud.api.amcs.law.domain.LawFlowNode;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawDisputeMatterFeignService;
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
@RequestMapping("/ui/amcs/law/disputeMatter")
public class LawDisputeMatterUiController extends LawBaseUiController {

    @Autowired
    private LawDisputeMatterFeignService lawDisputeMatterFeignService;

    private static final String DISPUTE_MATTER_INFO = "amcs/law/disputeMatter/disputeMatterInfo";
    private static final String DISPUTE_MATTER_LIST = "amcs/law/disputeMatter/disputeMatterList";

    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        return DISPUTE_MATTER_LIST;
    }

    @RequestMapping(value = "/queryDisputeMatterList", method = { RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> queryDisputeMatterList() {
        LawDisputeMatterCondition cond = new LawDisputeMatterCondition();
        return lawDisputeMatterFeignService.findListByCond(cond);
    }

    /**
     * 纠纷事项信息页面
     */
    @RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
    public String disputeMatterInfo(HttpServletRequest request, Long bizId) {
        LawDisputeMatter lawDisputeMatter = new LawDisputeMatter();
        if (Objects.nonNull(bizId) && bizId.longValue() > 0) {
            lawDisputeMatter = lawDisputeMatterFeignService.getLawDisputeMatter(bizId);
        }
        infoHandler(request, bizId, lawDisputeMatter.getInstId(), lawDisputeMatter);
        return DISPUTE_MATTER_INFO;
    }

    /**
     * 查询纠纷事项分页数据
     */
    @RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(LawDisputeMatterCondition cond) {
        PageResponse<Map<String, Object>> result = new PageResponse<>();
        return result;
    }

    /**
     * 查询所有纠纷事项实体数据
     */
    @RequestMapping(value = "/getAllEntity", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(LawDisputeMatterCondition cond) {
        List<Map<String, Object>> list = Lists.newArrayList();
        return list;
    }

    /**
     * 保存或更新纠纷事项信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawDisputeMatter lawDisputeMatter) {
        boolean saveAttachFlag = false;
        if (Objects.isNull(lawDisputeMatter.getId())) {
            saveAttachFlag = true;
        }
        Map<String, Object> result = lawDisputeMatterFeignService.save(lawDisputeMatter);
        Long disputeMatterId = MapUtils.getLong(result, "disputeMatterId");
        saveHandler(disputeMatterId, lawDisputeMatter.getBizType(), lawDisputeMatter.getBizCode(), saveAttachFlag, lawDisputeMatter.getAttachs());
        return success(MapUtils.getString(result, "msg"), "bizId", disputeMatterId);
    }

    /**
     * 初始流程提交
     *
     * @param lawDisputeMatter
     * @return
     */
    @RequestMapping(value = "/initCommit", method = RequestMethod.POST)
    @ResponseBody
    public Object initCommit(@RequestBody LawDisputeMatter lawDisputeMatter) {
        // 1.保存业务信息
        Map<String, Object> result = (Map<String, Object>) this.save(lawDisputeMatter);
        // 2.判断选取流程信息表中哪个流程
        Long flowId = getFlowId();
        // 3.保存流程实例信息
        Long disputeMatterId = MapUtils.getLong(result, "bizId");
        LawFlowInstance lawFlowInstance = createFlowInstance(flowId, disputeMatterId, lawDisputeMatter.getBizCode(), 1, "纠纷事项");
        // 4.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + disputeMatterId);
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
        LawDisputeMatter lawDisputeMatter = data.toJavaObject(LawDisputeMatter.class);
        Map<String, Object> retMaps = processFlow(lawDisputeMatter.getId(), opinion, "SUBMIT");
        if (MapUtils.getBoolean(retMaps, "saveFlag")) {
            this.save(lawDisputeMatter);
        }
        // TODO 如果当前机构不存在抄送节点所授权的人员，怎么办？
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(lawDisputeMatter.getId());
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
        LawFlowNode currentNode = lawFlowNodes.stream().filter(node -> node.getNodeId().longValue() == Long.valueOf(lawFlowInstance.getCurrentNode()).longValue()).findFirst().get();
        if (currentNode.getNodeType().equals("CC")) {
            // 抄送节点
            processFlow(lawDisputeMatter.getId(), null, "CC");
        }
        return success();
    }
}