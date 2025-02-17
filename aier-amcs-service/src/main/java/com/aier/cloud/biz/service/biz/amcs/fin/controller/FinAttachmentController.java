package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinAttachment;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsDipAnalysis;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinAttachmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 医保附件表 前端控制器
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 05:25:21
 */

@Controller
@RequestMapping("/api/service/biz/amcs/fin/finAttach")
public class FinAttachmentController   extends BaseController {

    @Autowired
    private FinAttachmentService finAttachmentService;

    @ApiOperation(value="根据id查询FinAttachment表实体")
    @ApiParam(name="id", value="FinAttachment", required=true)
    @RequestMapping(value = "/selectById")
    public @ResponseBody FinAttachment selectById(@RequestParam("id") Long id) {
        return finAttachmentService.selectById(id);
    }

    @RequestMapping(value = "/insert")
    public @ResponseBody Boolean insert(@RequestBody FinAttachment finAttachment) {
        return finAttachmentService.insert(finAttachment);
    }

    @RequestMapping(value = "/deleteById")
    public @ResponseBody Boolean deleteById(@RequestParam("id") Long id) {
        return finAttachmentService.deleteById(id);
    }

}
