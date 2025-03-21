package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/ui/amcs/law/manage")
public class LawManageUiController extends LawBaseUiController {

    private static final String LAW_CHOOSE ="amcs/law/lawManage/lawChoose";

    private static final String NODE_AUTH_LIST = "amcs/law/lawManage/nodeAuthList";

    private static final String NODE_AUTH_EDIT = "amcs/law/lawManage/editNodeAuth";

    private static final String LAW_LIST_TAB = "amcs/law/lawManage/lawListTab";


    @RequestMapping(value = "/lawChoose",method = { RequestMethod.GET, RequestMethod.POST})
    public String lawChoose(HttpServletRequest request, Model model){
        return LAW_CHOOSE;
    };

    @RequestMapping(value = "/getAll",method = {  RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAll(@RequestParam Map<String, Object> params){
        List<Map<String,Object>> list = Lists.newArrayList();
        Map<String, Object> all = Maps.newHashMap();
        all.put("lawTitle", "法务填报");
        all.put("lawUrl", "ui/amcs/law/civilCase/info");
        all.put("lawName", "民事诉讼仲裁案");
        Map<String, Object> criminalCase = Maps.newHashMap();
        criminalCase.put("lawTitle", "法务填报");
        criminalCase.put("lawUrl", "ui/amcs/law/criminalCase/info");
        criminalCase.put("lawName", "刑事案件");
        Map<String, Object> adminPenalty = Maps.newHashMap();
        adminPenalty.put("lawTitle", "法务填报");
        adminPenalty.put("lawUrl", "ui/amcs/law/adminPenalty/info");
        adminPenalty.put("lawName", "行政处罚");
        Map<String, Object> disputeMatter = Maps.newHashMap();
        disputeMatter.put("lawTitle", "法务填报");
        disputeMatter.put("lawUrl", "ui/amcs/law/disputeMatter/info");
        disputeMatter.put("lawName", "纠纷事项");
        Map<String, Object> externalLegalAdvisor = Maps.newHashMap();
        externalLegalAdvisor.put("lawTitle", "法务填报");
        externalLegalAdvisor.put("lawUrl", "ui/amcs/law/externalLegalAdvisor/info");
        externalLegalAdvisor.put("lawName", "外聘法律顾问信息");
        list.add(all);
        list.add(criminalCase);
        list.add(adminPenalty);
        list.add(disputeMatter);
        list.add(externalLegalAdvisor);
        return list;
    }

    /**
     * 节点权限列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/node/listPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String listPage(HttpServletRequest request){
        return NODE_AUTH_LIST;
    }

    @RequestMapping(value = "/node/getNodeAuthLists", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getNodeAuthLists(LawNodeAuthCondition cond){
        return getNodeAuthPage(cond);
    }

    @RequestMapping(value = "/node/editNodeAuth", method = { RequestMethod.GET, RequestMethod.POST })
    public String editNodeAuth(Map<String, Object> map,Long staffId,String staffName,String nodeIds,String staffCode,Long staffInstId,String staffInstName){
        map.put("nodeInfoList", getAllNodeInfo());
        if(Objects.nonNull(staffId) && staffId.longValue()>0){
            map.put("staffId", staffId);
            map.put("staffName", staffName);
            map.put("staffCode", staffCode);
            map.put("staffInstId", staffInstId);
            map.put("staffInstName", staffInstName);
            map.put("nodeIds", JSON.toJSONString(nodeIds.split(",")));
        }
        return NODE_AUTH_EDIT;
    }

    @RequestMapping(value = "/node/saveNodeAuth", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveNodeAuth(@RequestBody Map<String, Object> mData){
        saveNodeAuthFromPage(mData);
        return success();
    }

    @RequestMapping(value = "/lawListTab", method = { RequestMethod.GET, RequestMethod.POST })
    public String dfInfoPage(Map<String, Object> map) {
        return LAW_LIST_TAB;
    }

}