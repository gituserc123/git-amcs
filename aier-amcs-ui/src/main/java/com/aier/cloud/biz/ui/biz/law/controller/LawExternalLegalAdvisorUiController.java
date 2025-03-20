package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.api.amcs.law.domain.LawAttachment;
import com.aier.cloud.api.amcs.law.domain.LawAuditOpinion;
import com.aier.cloud.api.amcs.law.domain.LawExternalLegalAdvisor;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawAttachmentFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawAuditOpinionFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawExternalLegalAdvisorFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawDictTypeFeignService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/ui/amcs/law/externalLegalAdvisor")
public class LawExternalLegalAdvisorUiController extends LawBaseUiController {

    @Autowired
    private LawExternalLegalAdvisorFeignService lawExternalLegalAdvisorFeignService;

    @Autowired
    private LawDictTypeFeignService lawDictTypeFeignService;

    @Autowired
    private LawAttachmentFeignService lawAttachmentFeignService;

    @Autowired
    private LawAuditOpinionFeignService lawAuditOpinionFeignService;


    private static final String EXTERNAL_LEGAL_ADVISOR_INFO = "amcs/law/externalLegalAdvisor/externalLegalAdvisorInfo";
    private static final String EXTERNAL_LEGAL_ADVISOR_LIST = "amcs/law/externalLegalAdvisor/externalLegalAdvisorList";

    /**
     * 外聘法律顾问信息列表页面
     */
    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        request.setAttribute("empType", getEmpType());
        request.setAttribute("instId", ShiroUtils.getInstId());
        return EXTERNAL_LEGAL_ADVISOR_LIST;
    }

    /**
     * 查询外聘法律顾问信息列表
     */
    @RequestMapping(value = "/queryExternalLegalAdvisorList", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> queryExternalLegalAdvisorList(LawExternalLegalAdvisorCondition cond) {
        Boolean isRetNull = wrapperCond(cond);
        if(isRetNull){
            return new PageResponse<>();
        }
        PageResponse<Map<String, Object>> resultPages = lawExternalLegalAdvisorFeignService.findListByCond(cond);
        List<Map<String, Object>> result = resultPages.getRows();
        if(CollectionUtils.isNotEmpty(result) && result.size() > 0){
            List<Map> expertiseFieldDict = lawDictTypeFeignService.selectByTypeCode("expertise_field");
            List<Map> serviceTypeDict = lawDictTypeFeignService.selectByTypeCode("service_type");
            for(Map<String, Object> map : result){
                if(Objects.nonNull(MapUtils.getString(map, "expertiseField"))){
                    Map expertiseField = expertiseFieldDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "expertiseField"))).findFirst().get();
                    map.put("expertiseFieldTxt", MapUtils.getString(expertiseField,"valueDesc"));
                }
                if(Objects.nonNull(MapUtils.getString(map, "serviceType"))){
                    Map serviceType = serviceTypeDict.stream().filter(e -> MapUtils.getString(e,"valueCode").equals(MapUtils.getString(map, "serviceType"))).findFirst().get();
                    map.put("serviceTypeTxt", MapUtils.getString(serviceType,"valueDesc"));
                }
            }
        }
        return resultPages;
    }

    /**
     * 外聘法律顾问信息详情页面
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String externalLegalAdvisorInfo(HttpServletRequest request, Long bizId) {
        LawExternalLegalAdvisor lawExternalLegalAdvisor;
        if (Objects.nonNull(bizId) && bizId.longValue() > 0) {
            lawExternalLegalAdvisor = lawExternalLegalAdvisorFeignService.getLawExternalLegalAdvisor(bizId);
            request.setAttribute("bizId", bizId);
            request.setAttribute("bizEntity", lawExternalLegalAdvisor);
            // 查询附件信息
            List<LawAttachment> attachs = lawAttachmentFeignService.selectByBizId(bizId);
            request.setAttribute("attachs", JSON.toJSONString(attachs));
            request.setAttribute("opDivDisplay", "flex");
        }else {
            request.setAttribute("opDivDisplay", "none");
        }
        request.setAttribute("attachAddBtnDisplay", "inline");
        request.setAttribute("attachDelBtnVisible", "visible");
        request.setAttribute("stashBtnDisplay", "inline");
        request.setAttribute("initBtnDisplay", "inline");

        //infoHandler(request, bizId, lawExternalLegalAdvisor.getInstId(), lawExternalLegalAdvisor);
        return EXTERNAL_LEGAL_ADVISOR_INFO;
    }

    /**
     * 保存或更新外聘法律顾问信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawExternalLegalAdvisor lawExternalLegalAdvisor) {
        boolean saveAttachFlag = false;
        if (Objects.isNull(lawExternalLegalAdvisor.getId())) {
            saveAttachFlag = true;
            Institution parentInst = getParentInst();
            lawExternalLegalAdvisor.setSuperInstId(parentInst.getId());
            lawExternalLegalAdvisor.setSuperInstName(parentInst.getName());
        }
        Map<String, Object> result = lawExternalLegalAdvisorFeignService.save(lawExternalLegalAdvisor);
        Long advisorId = MapUtils.getLong(result, "advisorId");
        //saveHandler(advisorId, lawExternalLegalAdvisor.getBizType(), lawExternalLegalAdvisor.getBizCode(), saveAttachFlag, lawExternalLegalAdvisor.getAttachs());
        if(saveAttachFlag){
            // 保存附件
            if(Objects.nonNull(lawExternalLegalAdvisor.getAttachs()) && lawExternalLegalAdvisor.getAttachs().size()>0){
                lawExternalLegalAdvisor.getAttachs().forEach(ach -> {
                    ach.setBizId(advisorId);
                    ach.setBizType(lawExternalLegalAdvisor.getBizType());
                    ach.setBizCode(lawExternalLegalAdvisor.getBizCode());
                    ach.setCreator(ShiroUtils.getId());
                    ach.setCreateDate(new Date());
                    ach.setModifer(ShiroUtils.getId());
                    ach.setModifyDate(new Date());
                    lawAttachmentFeignService.insert(ach);
                });
            }
        }
        return success(MapUtils.getString(result, "msg"), "bizId", advisorId);
    }

    /**
     * 意见提交
     * @param dataMaps
     * @return
     */
    @RequestMapping(value = "/saveOpinion", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOpinion(@RequestBody Map<String,Object> dataMaps){
        String opinion = MapUtils.getString(dataMaps,"opinion");
        addLawAuditOpinion(MapUtils.getLong(dataMaps,"bizId"),opinion);
        return success();
    }

    @RequestMapping(value = "/getLawOpinionList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getLawOpinionList(@RequestParam("bizId") Long bizId){
        LawAuditOpinionCondition cond = new LawAuditOpinionCondition();
        cond.setDetailIds(Arrays.asList(bizId));
        List<LawAuditOpinion> opinions = lawAuditOpinionFeignService.getListByDetailIds(cond);
        return opinions;
    }

}