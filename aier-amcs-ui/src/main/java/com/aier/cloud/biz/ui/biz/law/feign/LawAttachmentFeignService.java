package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.domain.LawAttachment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aier-amcs-service")
public interface LawAttachmentFeignService {

    @RequestMapping(value = "/api/service/biz/amcs/law/attachment/insertBatch")
    @ResponseBody Boolean insertBatch(@RequestBody List<LawAttachment> lawAttachmentList);

    @RequestMapping(value = "/api/service/biz/amcs/law/attachment/insert")
    @ResponseBody Boolean insert(@RequestBody LawAttachment lawAttachment);

    @RequestMapping(value = "/api/service/biz/amcs/law/attachment/selectByBizId", method = { RequestMethod.POST })
    @ResponseBody List<LawAttachment> selectByBizId(@RequestParam("bizId") Long bizId);

    @RequestMapping(value = "/api/service/biz/amcs/law/attachment/deleteAttach")
    @ResponseBody Boolean deleteAttach(@RequestParam("bizId") Long bizId,@RequestParam("fileId") Long fileId);

}