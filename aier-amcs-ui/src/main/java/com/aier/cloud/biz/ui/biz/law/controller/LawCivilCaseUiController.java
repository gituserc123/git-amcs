package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.api.amcs.law.domain.LawAttachment;
import com.aier.cloud.api.amcs.law.domain.LawCivilCase;
import com.aier.cloud.basic.api.domain.enums.FileEnum;
import com.aier.cloud.basic.api.response.domain.file.FileInfo;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.encrypt.service.AesEncryptStringService;
import com.aier.cloud.biz.ui.biz.adverse.feign.FileService;
import com.aier.cloud.biz.ui.biz.adverse.utils.FileValidator;
import com.aier.cloud.biz.ui.biz.law.feign.LawAttachmentFeignService;
import com.aier.cloud.biz.ui.biz.law.feign.LawCivilCaseFeignService;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;

@Controller
@RequestMapping("/ui/amcs/law/civilCase")
public class LawCivilCaseUiController extends BaseController {

    @Autowired
    private LawCivilCaseFeignService lawCivilCaseFeignService;

    @Autowired
    private AesEncryptStringService aesEncryptStringService;

    @Autowired
    private LawAttachmentFeignService lawAttachmentFeignService;

    @Autowired
    private FileService fs;

    private static final String CIVIL_CASE_INFO = "amcs/law/civilCase/civilCaseInfo";
    private static final String EDIT_CIVIL_CASE = "amcs/law/civilCase/editCivilCase";

    /**
     * 民事诉讼仲裁案件列表页面
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String civilCaseList(HttpServletRequest request,Long civilCaseId) {
        if(Objects.nonNull(civilCaseId) && civilCaseId.longValue()>0){
            request.setAttribute("civilCaseId", civilCaseId);
            request.setAttribute("civilCase", lawCivilCaseFeignService.getLawCivilCase(civilCaseId));
            // 查询附件信息
            List<LawAttachment> attachs = lawAttachmentFeignService.selectByBizId(civilCaseId);
            request.setAttribute("attachs", JSON.toJSONString(attachs));
            // TODO 查询流程信息

        }
        return CIVIL_CASE_INFO;
    }

    /**
     * 查询民事诉讼仲裁案件分页数据
     */
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(LawCivilCaseCondition cond) {
        PageResponse<Map<String, Object>> result = new PageResponse<>();
        return result;
    }

    /**
     * 查询所有民事诉讼仲裁案件实体数据
     */
    @RequestMapping(value = "/getAllEntity", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(LawCivilCaseCondition cond) {
        List<Map<String, Object>> list = Lists.newArrayList();
        return list;
    }

    /**
     * 保存或更新民事诉讼仲裁案件信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LawCivilCase lawCivilCase) {
        boolean saveAttachFlag = false;
        if(Objects.isNull(lawCivilCase.getId())){
            saveAttachFlag = true;
        }
        Map<String,Object> result = lawCivilCaseFeignService.save(lawCivilCase);
        // TODO 保存流程实例表信息

        Long civilCaseId = MapUtils.getLong(result,"civilCaseId");
        if(saveAttachFlag){
            // 保存附件
            lawCivilCase.getAttachs().forEach(ach -> {
                ach.setBizId(civilCaseId);
                ach.setBizType("1");
                ach.setBizCode("CIVIL_CASE");
                ach.setCreator(ShiroUtils.getId());
                ach.setCreateDate(new Date());
                ach.setModifer(ShiroUtils.getId());
                ach.setModifyDate(new Date());
                lawAttachmentFeignService.insert(ach);
            });
        }
        return success(MapUtils.getString(result,"msg"));
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public Object upload(@RequestParam("bizId") Long bizId,
                         @RequestParam("attachCode") String attachCode,
                         @RequestParam("file") MultipartFile file) {
        //判断文件是否包含脚步
        if(!FileValidator.isSafeFile(file)) {
            return this.fail("上传文件包含脚本！");
        }
        //判断是否为非法图片文件
        if(!FileValidator.imageCheck(file)) {
            return this.fail("上传图片文件存在异常！");
        }

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Long timeInfo = Instant.now().toEpochMilli();

        try {
            FileInfo info = fs.upload(file, FileEnum.AMCS_AE_FILE.getBucketType(),
                    FileEnum.AMCS_AE_FILE.getIsEncrypt(), FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + timeInfo + "/" + timeInfo + "." + suffix,
                    FileEnum.AMCS_AE_FILE.getPlatform());
            if (null == info) {
                return this.fail("文件上传失败！");
            }

            if(Objects.nonNull(bizId) && bizId.longValue()>0){
                LawAttachment lawAttachment = new LawAttachment();
                lawAttachment.setBizType("1");
                lawAttachment.setBizCode("CIVIL_CASE");
                lawAttachment.setBizId(bizId);
                lawAttachment.setFileName(file.getOriginalFilename());
                lawAttachment.setFileType(file.getContentType());
                lawAttachment.setFileSize(Math.toIntExact(file.getSize()));
                lawAttachment.setFileId(info.getId().toString());
                lawAttachment.setWebUrl(info.getUrl());
                lawAttachment.setFilePath(info.getPath());
                lawAttachment.setAttachCode(attachCode);
                lawAttachment.setCreator(ShiroUtils.getId());
                lawAttachment.setCreateDate(new Date());
                lawAttachment.setModifer(ShiroUtils.getId());
                lawAttachment.setModifyDate(new Date());
                lawAttachmentFeignService.insert(lawAttachment);
            }

            Map<String,Object> result = Maps.newHashMap();
            result.put("fileId", info.getId()); // 实际从文件服务返回的FileInfo的id字段
            result.put("fileName", file.getOriginalFilename());
            result.put("fileSize", Math.toIntExact(file.getSize()));
            result.put("fileUrl", info.getUrl()); // 实际从文件服务返回的FileInfo的url字段
            result.put("fileType", file.getContentType());
            result.put("filePath", info.getPath()); // 实际从文件服务返回的FileInfo的path字段
            result.put("attachCode", attachCode); // 实际从文件服务返回的FileInfo的path字段

            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return this.fail("文件上传失败！");
        }
    }

    @RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAttach(@RequestParam("filePath") String filePath,@RequestParam("fileId") Long fileId,@RequestParam("bizId") Long bizId) {
        // 先删除服务器上的文件
        String storeName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String storeFolder = storeName.substring(0, storeName.lastIndexOf("."));
        boolean flag = fs.deleteFile(FileEnum.AMCS_AE_FILE.getBucketType(),
                FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + storeFolder + "/" + storeName,
                FileEnum.AMCS_AE_FILE.getPlatform());
        if (!flag) {
            return this.fail("文件删除失败！");
        }else {
            // 再删除数据库中的附件信息
            if(Objects.nonNull(bizId) && bizId.longValue()>0){
                lawAttachmentFeignService.deleteAttach(bizId,fileId);
            }
            return this.success("文件删除成功！");
        }
    }


}