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
        list.add(all);
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

}