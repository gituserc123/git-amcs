package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.domain.LawAttachment;
import com.aier.cloud.api.amcs.law.domain.LawIntegrityFiling;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.law.feign.LawAttachmentFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawDictTypeFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawIntegrityFilingFeignService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/ui/amcs/law/integrityfiling")
public class LawIntegrityFilingUiController extends BaseController {

    @Autowired
    private LawIntegrityFilingFeignService lawIntegrityFilingFeignService;

    @Autowired
    private LawDictTypeFeignService lawDictTypeFeignService;

    @Autowired
    private LawAttachmentFeignService lawAttachmentFeignService;

    private static final String INTEGRITY_FILING_INFO = "amcs/law/integrityFiling/integrityFilingInfo";
    private static final String INTEGRITY_FILING_LIST = "amcs/law/integrityFiling/integrityFilingList";

    /**
     * 廉洁详情页面
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String integrityFilingInfo(HttpServletRequest request, Long bizId) {
        // 计算编号
        if(bizId!=null){
            LawIntegrityFiling lawIntegrityFiling = lawIntegrityFilingFeignService.getLawIntegrityFiling(bizId);
            request.setAttribute("bizEntity", lawIntegrityFiling);
            request.setAttribute("bizId", lawIntegrityFiling.getId());
            // 附件查询
            List<LawAttachment> attachs = lawAttachmentFeignService.selectByBizId(bizId);
            request.setAttribute("attachs", JSON.toJSONString(attachs));
        }else{
            LawIntegrityFiling lawIntegrityFiling = new LawIntegrityFiling();
            lawIntegrityFiling.setInstId(ShiroUtils.getInstId());
            lawIntegrityFiling.setInstName(ShiroUtils.getInstName());
            lawIntegrityFiling.setHospId(ShiroUtils.getTenantId());
            lawIntegrityFiling.setReporterName(ShiroUtils.getLoginName());
            String prefixEventSn = ShiroUtils.getTenantId() + new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
            Integer eventSnCount = lawIntegrityFilingFeignService.getEventSnCount(prefixEventSn, ShiroUtils.getTenantId());
            if (eventSnCount == null || eventSnCount == 0) {
                lawIntegrityFiling.setEventSn(prefixEventSn + "01");
            } else if ((eventSnCount+1) < 10) {
                lawIntegrityFiling.setEventSn(prefixEventSn + "0" + (eventSnCount + 1));
            } else {
                lawIntegrityFiling.setEventSn(prefixEventSn + (eventSnCount + 1));
            }
            request.setAttribute("bizEntity", lawIntegrityFiling);
        }
        request.setAttribute("attachAddBtnDisplay", "inline");
        request.setAttribute("attachDelBtnVisible", "visible");
        // 计算时间


        return INTEGRITY_FILING_INFO;
    }



    /**
     * 保存或更新
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawIntegrityFiling lawIntegrityFiling) {
        boolean saveAttachFlag = false;
        if (Objects.isNull(lawIntegrityFiling.getId())) {
            saveAttachFlag = true;
            lawIntegrityFiling.setReporterName(ShiroUtils.getLoginName());
            lawIntegrityFiling.setHospId(ShiroUtils.getTenantId());
        }
        Map<String, Object> result = lawIntegrityFilingFeignService.save(lawIntegrityFiling);
        Long integrityFilingId = MapUtils.getLong(result, "integrityFilingId");
        if(saveAttachFlag){
            // 保存附件
            if(Objects.nonNull(lawIntegrityFiling.getAttachs()) && lawIntegrityFiling.getAttachs().size()>0){
                lawIntegrityFiling.getAttachs().forEach(ach -> {
                    ach.setBizId(integrityFilingId);
                    ach.setBizType(lawIntegrityFiling.getBizType());
                    ach.setBizCode(lawIntegrityFiling.getBizCode());
                    ach.setCreator(ShiroUtils.getId());
                    ach.setCreateDate(new Date());
                    ach.setModifer(ShiroUtils.getId());
                    ach.setModifyDate(new Date());
                    lawAttachmentFeignService.insert(ach);
                });
            }
        }
        return success(MapUtils.getString(result, "msg"), "bizId", integrityFilingId);
    }


}
