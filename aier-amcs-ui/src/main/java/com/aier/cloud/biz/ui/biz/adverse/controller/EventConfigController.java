package com.aier.cloud.biz.ui.biz.adverse.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.aier.cloud.api.amcs.adverse.domain.AeBasicInfo;
import com.aier.cloud.api.amcs.adverse.domain.EventConfig;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeCommonService;
import com.aier.cloud.biz.ui.biz.adverse.feign.EventConfigService;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-15 16:33
 **/
@Controller
@RequestMapping("/ui/amcs/adverse/eventConfig/")
public class EventConfigController extends BaseController {


    @Value("${amcs.adverse.cond.roleName:集团视光不良事件管理}")
    private String roleName;

    @Autowired
    private EventConfigService eventConfigService;
    
    @Autowired
    private AeCommonService aeCommonService;

    private static final String   NODE_CONFIG="amcs/adverseEvent/config/NodeConfigManage";

    @RequestMapping(value = "/nodeConfig",method = { RequestMethod.GET, RequestMethod.POST})
    public String nodeConfig(Model model){

        model.addAttribute("Nodes", NodeEnum.values());
        return NODE_CONFIG;
    };

    @RequestMapping(value = "/getAll",method = {  RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(EventConfigCondition cond){
        if(ObjectUtil.isNotEmpty(cond.getEventType())){
            cond.setEventCode(Arrays.stream(EventEnum.values()).map(EventEnum::getValue).filter(value -> value.startsWith(cond.getEventType())).collect(Collectors.joining(",")));
        }
        // 如果包含"集团视光不良事件管理"角色,下拉列表只允许看到视光表单，包括：角膜接触镜不良事件、框架眼镜不良事件、视觉训练不良事件、其他视光患者不良事件。
        if(ShiroUtils.hasRole(roleName)){
            List<String> optometryList = Arrays.asList(EventEnum.CORNEAL.getValue(),EventEnum.FRAMEGLASSES.getValue(),EventEnum.VISUALTRAIN.getValue(),EventEnum.OTHEROPTOMETRY.getValue());
            cond.setEventCode(optometryList.stream().collect(Collectors.joining(",")));
        }
        PageResponse<Map<String, Object>> o =eventConfigService.getAll(cond);
        o.getRows().forEach(i -> {
            List<NodeEnum> list=NodeUtil.NodeList((Integer)i.get("nodeValue"));
            for(NodeEnum ne:NodeEnum.values()){
                if(list.indexOf(ne)>=0){
                    i.put(ne.toString(),1);
                }else{
                    i.put(ne.toString(),0);
                }
            }
        });

        //TODO 为非计划再手术上报事件时，不显示移除节点
        Iterator<Map<String, Object>> iterator = o.getRows().iterator();
        while (iterator.hasNext()) {
            Map<String, Object> i = iterator.next();
            if (EventEnum.UNPLREOPERATION.getValue().equals(i.get("eventCode"))) {
                iterator.remove();
            }
        }

        return o;
    }
    @RequestMapping(value = "/save",method = {  RequestMethod.POST})
    @ResponseBody
    public Object save(EventConfig eventConfig){
        if(eventConfigService.save(eventConfig)){
            return this.success();
        }else{
            return this.fail();
        }
    }

    @RequestMapping(value="/getNodeConfigByCode",method={RequestMethod.POST})
    @ResponseBody
    public List< Map<String,Object>> getNodeConfigByCode(@RequestParam("eventCode") String eventCode){
        EventConfig eventConfig= eventConfigService.getNodeConfigByCode(eventCode);
        List<NodeEnum> list=NodeUtil.NodeList(eventConfig.getNodeValue().intValue());
        List< Map<String,Object>> newlist=new ArrayList();

        for(NodeEnum ne:NodeEnum.values()){
            Map<String,Object>  map=new HashMap<>();
            if(list.indexOf(ne)>=0){
                map.put("key",ne.getEnumDesc());
                if(ne.equals(NodeEnum.REPORTING)){
                    map.put("currentNode",1);
                }else{
                    map.put("currentNode",0);
                }

                newlist.add(map);
            }
        }
        return newlist;
    }
    
    
    @RequestMapping(value="/getNodeListById",method={RequestMethod.POST})
    @ResponseBody
    public List< Map<String,Object>> getNodeListById(
            @RequestParam("eventCode") String eventCode,
            @RequestParam(value = "basicId", required = false) Long basicId){
        List< Map<String,Object>> nodelist = Lists.newArrayList();
        Integer nodeValue = NodeEnum.REPORTING.getSeq();
        if(!ObjectUtils.isEmpty(basicId)) {
            AeBasicInfo basic = aeCommonService.getBasicById(basicId);
            nodeValue = basic.getNode();
            if(NodeEnum.STASH.getSeq().equals(nodeValue)||NodeEnum.RETRUN.getSeq().equals(nodeValue)) {
                nodeValue = NodeEnum.REPORTING.getValue();
            }
        }

        EventConfig eventConfig= eventConfigService.getNodeConfigByCode(eventCode);
        List<NodeEnum> list=NodeUtil.NodeList(eventConfig.getNodeValue().intValue());

        for(NodeEnum ne:NodeEnum.values()){
            //暂存和集团查阅不显示
            if(ne.equals(NodeEnum.STASH) || ne.equals(NodeEnum.GROUP_REVIEWS) || ne.equals(NodeEnum.DEPT_REVIEWS)) continue;
            Map<String,Object>  map=new HashMap<>();
            if(list.indexOf(ne)>=0){
                map.put("key",ne.getEnumDesc());
                if(ne.getSeq().equals(nodeValue)) {
                    map.put("currentNode",1);
                }else{
                    map.put("currentNode",0);
                }

                nodelist.add(map);
            }
        }
        return nodelist;

    }
    
    @RequestMapping(value = "/getPreNodeList",method = {  RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> listNode(AeInfoCondition cond){
        return eventConfigService.getPreNodeList(cond);
    }

}
