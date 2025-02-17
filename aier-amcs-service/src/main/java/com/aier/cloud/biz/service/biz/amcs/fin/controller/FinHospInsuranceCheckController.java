package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import cn.hutool.core.date.DateUtil;
import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.encrypt.service.AesEncryptStringService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinAttachment;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospInsuranceCheck;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospIsrneChkAuth;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinAttachmentService;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospInsuranceCheckService;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospIsrneChkAuthService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 医院医保检查统计表 前端控制器
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 09:24:33
 */
@Api("医院医保检查前端控制器")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/hospInsuranceCheck")
public class FinHospInsuranceCheckController  extends BaseController {

    @Autowired
    private FinHospInsuranceCheckService finHospInsuranceCheckService;

    @Autowired
    private FinAttachmentService finAttachmentService;

    @Autowired
    private FinHospIsrneChkAuthService finHospIsrneChkAuthService;

    @Autowired
    @Qualifier("AesEncryptStringService")
    private AesEncryptStringService aesEncryptStringService;

    @ApiOperation(value="根据id查询FinHospInsuranceCheck表实体")
    @ApiParam(name="id", value="", required=true)
    @RequestMapping(value = "/getFinHospInsuranceCheck")
    public @ResponseBody FinHospInsuranceCheck getFinHospInsuranceCheck(@RequestParam("id") Long id) {
        return finHospInsuranceCheckService.selectById(id);
    }

    @ApiOperation(value = "条件查询详情")
    @RequestMapping(value = "/getAll")
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(@RequestBody FinHospInsuranceCheckCondition cond){
        Page<Map<String, Object>> page = tranToPage(cond);
        add("creator", "T_SYS_STAFF|ID|NAME", "creatorName");
        add("modifer", "T_SYS_STAFF|ID|NAME", "modiferName");
        List<FinHospInsuranceCheck> retLists = finHospInsuranceCheckService.getAll(page,cond);
        this.getInstCheckAttaches(retLists);
        retLists.stream().forEach(rl->{
            if(rl.getInstCheckAttaches() != null && rl.getInstCheckAttaches().size()>0){
                rl.getInstCheckAttaches().stream().forEach(i -> {
                    if (i.getFileId() != null && StringUtils.isNotBlank(i.getWebUrl()) && !i.getWebUrl().startsWith("http://")
                            && !i.getWebUrl().startsWith("https://")) {
                        String decryptEcbModeUrl = aesEncryptStringService.decryptEcbMode(i.getWebUrl(),
                                i.getFileId().toString());
                        i.setWebUrl(decryptEcbModeUrl);
                    }
                });
            }
        });
        return  returnPageResponse(page,retLists);
    }


    @ApiOperation(value = "不分页条件查询详情")
    @RequestMapping(value = "/getAllEntity")
    @ResponseBody
    public List<FinHospInsuranceCheck> getAllEntity(@RequestBody FinHospInsuranceCheckCondition cond){
        add("creator", "T_SYS_STAFF|ID|NAME", "creatorName");
        add("modifer", "T_SYS_STAFF|ID|NAME", "modiferName");
        List<FinHospInsuranceCheck> retLists = finHospInsuranceCheckService.getAllEntity(cond);
        this.getInstCheckAttaches(retLists);
        retLists.stream().forEach(rl->{
            if(rl.getInstCheckAttaches() != null && rl.getInstCheckAttaches().size()>0){
                rl.getInstCheckAttaches().stream().forEach(i -> {
                    if (i.getAttachId() != null && StringUtils.isNotBlank(i.getWebUrl()) && !i.getWebUrl().startsWith("http://")
                            && !i.getWebUrl().startsWith("https://")) {
                        String decryptEcbModeUrl = aesEncryptStringService.decryptEcbMode(i.getWebUrl(),
                                i.getAttachId().toString());
                        i.setWebUrl(decryptEcbModeUrl);
                    }
                });
            }
        });
        return retLists;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody FinHospInsuranceCheck stdAdaptation){
        return finHospInsuranceCheckService.save(stdAdaptation);
    }

    @RequestMapping(value = "/getAuthByStaffCode")
    @ResponseBody
    public List<FinHospIsrneChkAuth> getAuthByStaffCode(@RequestParam("staffCode") String staffCode){
        EntityWrapper<FinHospIsrneChkAuth> wrapper = new EntityWrapper<>();
        wrapper.eq("STAFF_CODE", staffCode);
        return finHospIsrneChkAuthService.selectList(wrapper);
    }

    @RequestMapping(value = "/saveAuth")
    @ResponseBody
    public Object saveAuth(@RequestBody List<FinHospIsrneChkAuth> finHospIsrneChkAuths){
        finHospIsrneChkAuths.stream().forEach(f->{
            f.setCreateDate(DateUtil.date());
            f.setCreator(UserContext.getUserId());
            f.setModifer(UserContext.getUserId());
            f.setModifyDate(DateUtil.date());
            f.setAuthCode("HIC");
            finHospIsrneChkAuthService.insert(f);
        });
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/deleteAuth")
    @ResponseBody
    public Object deleteAuth(@RequestParam("staffCode") String staffCode){
        EntityWrapper<FinHospIsrneChkAuth> wrapper = new EntityWrapper<>();
        wrapper.eq("STAFF_CODE", staffCode);
        List<FinHospIsrneChkAuth> finHospIsrneChkAuths =  finHospIsrneChkAuthService.selectList(wrapper);
        if(CollectionUtils.isNotEmpty(finHospIsrneChkAuths)){
            finHospIsrneChkAuths.stream().forEach(f-> finHospIsrneChkAuthService.deleteById(f.getId()));
        }
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/getAuthLists")
    @ResponseBody
    public List<FinHospIsrneChkAuth> getAuthLists(@RequestBody FinHospIsrneChkAuthCondition cond){

        List<FinHospIsrneChkAuth> finHospIsrneChkAuths =  finHospIsrneChkAuthService.getAllEntity(cond);

        return finHospIsrneChkAuths;
    }

    @ApiOperation(value="根据id查询附件")
    @RequestMapping(value = "/getFinAttachments")
    public @ResponseBody List<FinAttachment> getFinAttachments(@RequestParam("id") Long id) {
        EntityWrapper<FinAttachment> wrapper = new EntityWrapper<>();
        wrapper.eq("biz_id", id);
        List<FinAttachment> attachments = finAttachmentService.selectList(wrapper);
        attachments.stream().forEach(i -> {
            if (i.getFileId() != null && StringUtils.isNotBlank(i.getWebUrl()) && !i.getWebUrl().startsWith("http://")
                    && !i.getWebUrl().startsWith("https://")) {
                String decryptEcbModeUrl = aesEncryptStringService.decryptEcbMode(i.getWebUrl(),
                        i.getFileId().toString());
                i.setWebUrl(decryptEcbModeUrl);
            }
        });

        return attachments;
    }

    private void getInstCheckAttaches(List<FinHospInsuranceCheck> retLists) {
        List<Long> bizIds = retLists.stream().map(FinHospInsuranceCheck::getId).collect(Collectors.toList());
        EntityWrapper<FinAttachment> wrapper = new EntityWrapper<>();
        wrapper.in("biz_id", bizIds);
        List<FinAttachment> attachments = finAttachmentService.selectList(wrapper);
        if(CollectionUtils.isNotEmpty(attachments)){
            Map<Long, List<FinAttachment>> attachMap = attachments.stream().collect(Collectors.groupingBy(FinAttachment::getBizId));
            retLists.stream().forEach(rl->{
                if(attachMap.containsKey(rl.getId())){
                    rl.setInstCheckAttaches(attachMap.get(rl.getId()));
                }
            });
        }
    }

}
