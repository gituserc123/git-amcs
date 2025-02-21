package com.aier.cloud.biz.service.biz.amcs.law.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAttachment;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAttachmentService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 法务系统附件表相关接口
 */
@Api("法务系统附件表相关接口")
@RestController
@RequestMapping("/api/service/biz/amcs/law/attachment")
public class LawAttachmentController extends BaseController {

    @Autowired
    private LawAttachmentService lawAttachmentService;

    @RequestMapping(value = "/insertBatch")
    public @ResponseBody Boolean insertBatch(@RequestBody List<LawAttachment> lawAttachmentList) {
        return lawAttachmentService.insertBatch(lawAttachmentList);
    }

    @RequestMapping(value = "/insert")
    public @ResponseBody Boolean insert(@RequestBody LawAttachment lawAttachment) {
        return lawAttachmentService.insert(lawAttachment);
    }

    /**
     *
     *
     * @param
     * @return 附件列表
     */
    @ApiOperation(value = "根据bizId查询附件列表")
    @RequestMapping(value = "/selectByBizId", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody List<LawAttachment> selectByBizId(@RequestParam("bizId") Long bizId) {
        return lawAttachmentService.selectByBizId(bizId);
    }

    @RequestMapping(value = "/deleteAttach")
    public @ResponseBody Boolean deleteAttach(@RequestParam("bizId") Long bizId,@RequestParam("fileId") Long fileId) {
        EntityWrapper<LawAttachment> wrapper = new EntityWrapper<>();
        wrapper.eq("biz_id",bizId);
        wrapper.eq("file_id",fileId);
        lawAttachmentService.delete(wrapper);
        return lawAttachmentService.delete(wrapper);
    }
}