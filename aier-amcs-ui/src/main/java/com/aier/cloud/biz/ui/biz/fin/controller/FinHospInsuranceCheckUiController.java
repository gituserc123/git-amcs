package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinAttachment;
import com.aier.cloud.api.amcs.fin.domain.FinHospInsuranceCheck;
import com.aier.cloud.api.amcs.fin.domain.FinHospIsrneChkAuth;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.domain.enums.FileEnum;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.file.FileInfo;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.encrypt.service.AesEncryptStringService;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.feign.FileService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.adverse.utils.FileValidator;
import com.aier.cloud.biz.ui.biz.fin.feign.FinAttachmentFeignService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinHospInsuranceCheckFeignService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui/amcs/fin/hospInsuranceCheck")
public class FinHospInsuranceCheckUiController extends BaseController {

    @Autowired
    private FinHospInsuranceCheckFeignService finHospInsuranceCheckFeignService;

    @Autowired
    private FinAttachmentFeignService  finAttachmentFeignService;

    @Autowired
    private AdverseEventService adverseEventService;

    @Autowired
    @Qualifier("AesEncryptStringService")
    private AesEncryptStringService aesEncryptStringService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private HospHandleService hospHandleService;

    @Autowired
    private FileService fs;

    private static final String HOSP_INSURANCE_CHECK_LIST = "amcs/fin/business/hospInsuranceCheck/hospInsuranceCheckList";

    private static final String HOSP_ISRNE_CHK_AUTH = "amcs/fin/business/hospInsuranceCheck/hospHospIsrneChkAuthList";
    private static final String EDIT_CHECK_AUTH = "amcs/fin/business/hospInsuranceCheck/editCheckAuth";

    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String hospInsuranceCheckList(HttpServletRequest request) {
        this.getLoginAuth(request);
        return HOSP_INSURANCE_CHECK_LIST;
    }

    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> getAll(FinHospInsuranceCheckCondition cond){
        boolean isRetNull=false;
        // 组装查询条件
        this.wrapperCond(cond,isRetNull);
        if(isRetNull){
            return new PageResponse<>();
        }
        PageResponse<Map<String, Object>> result = finHospInsuranceCheckFeignService.getAll(cond);
        return result;
    }

    @RequestMapping(value = "/getAllEntity", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAllEntity(FinHospInsuranceCheckCondition cond){
        List<Map<String,Object>> list = finHospInsuranceCheckFeignService.getAllEntity(cond);
        list.stream().forEach(lt -> lt.put("caseClosed",MapUtils.getInteger(lt, "caseClosed").intValue()==1?"是":"否"));
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(FinHospInsuranceCheck finHospInsuranceCheck){
        return finHospInsuranceCheckFeignService.save(finHospInsuranceCheck);
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public Object upload(@RequestParam("bizId") Long bizId,
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

            // 保存至T_FIN_ATTACHMENT表
            FinAttachment attach = new FinAttachment();
            attach.setCreateDate(new Date());
            attach.setCreator(ShiroUtils.getId());
            attach.setModifer(ShiroUtils.getId());
            attach.setModifyDate(new Date());
            attach.setBizId(bizId);
            attach.setFileName(file.getOriginalFilename());
            attach.setFileType(file.getContentType());
            attach.setFileSize(Math.toIntExact(file.getSize()));
            attach.setFileId(info.getId().toString());
            attach.setFilePath(info.getPath());
            //加密
            String encryptEcbModeUrl = aesEncryptStringService.encryptEcbMode(info.getUrl(), info.getId().toString());
            attach.setWebUrl(encryptEcbModeUrl);
            finAttachmentFeignService.insert(attach);

            return this.success(attach);
        } catch (Exception e) {
            e.printStackTrace();
            return this.fail("文件上传失败！");
        }
    }

    @RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAttach(@RequestParam("id") Long id) {
        FinAttachment attach = finAttachmentFeignService.selectById(id);
        if(Objects.nonNull(attach)){
            String storeName = attach.getFilePath().substring(attach.getFilePath().lastIndexOf("/") + 1);
            String storeFolder = storeName.substring(0, storeName.lastIndexOf("."));
            boolean flag = fs.deleteFile(FileEnum.AMCS_AE_FILE.getBucketType(),
                    FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + storeFolder + "/" + storeName,
                    FileEnum.AMCS_AE_FILE.getPlatform());
            if (!flag) {
                return this.fail("文件删除失败！");
            }else {
                //删除文件记录
                finAttachmentFeignService.deleteById(id);
                return this.success("文件删除成功！");
            }
        }

        return success("删除成功！");
    }

    @RequestMapping(value = "/getHospInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHospInfo(){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        Map<String,Object> map = Maps.newConcurrentMap();
        map.put("hospId", shiroUser.getTenantId());
        map.put("hospName", shiroUser.getInstName());
        InstCondition cond_1 = new InstCondition();
        cond_1.setInstId(shiroUser.getTenantId());
        cond_1.setInstType(InstEnum.HOSP.getEnumCode());
        List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
        if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
            Map resultMap = hosps.get(0);
            /** 查询一级省区 */
            InstCondition cond_2 = new InstCondition();
            cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
            List<Map> list = institutionService.getProvince(cond_2);
            List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
            map.put("hospParent", Objects.nonNull(l) && l.size()>0 ? MapUtils.getString(l.get(0), "name") : "");
            // 医院类型
            if (Objects.nonNull(resultMap.get("INVEST_NATURE"))) {
                if(String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("10")){
                    map.put("investNature","上市");
                }else if(String.valueOf(resultMap.get("INVEST_NATURE")).equalsIgnoreCase("11")){
                    map.put("investNature","合伙");
                }else{
                    map.put("investNature","");
                }
            } else {
                map.put("investNature","");
            }
            if(resultMap.get("EHR_LEVEL")!=null){
                map.put("ehrLevel",EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
            }
        }
        return map;
    }

    @RequestMapping(value = "/getProvince", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Institution> getProvince() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        List<FinHospIsrneChkAuth> finHospIsrneChkAuths = finHospInsuranceCheckFeignService.getAuthByStaffCode(shiroUser.getLoginCode());
        List<Long> provinceIds = finHospIsrneChkAuths.stream().map(FinHospIsrneChkAuth::getProvinceId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(provinceIds)) {
            if(provinceIds.contains(99999999L)){
                return institutionService.getListByInstType(InstEnum.PROVINCE);
            }else {
                List<Institution> institutions = finHospIsrneChkAuths.stream().map(auth -> {
                    Institution institution = new Institution();
                    institution.setId(auth.getProvinceId());
                    institution.setName(auth.getProvinceName());
                    return institution;
                }).collect(Collectors.toList());
                return institutions;
            }
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/authList", method = { RequestMethod.GET, RequestMethod.POST })
    public String hospHospIsrneChkAuthList(HttpServletRequest request){
        this.getLoginAuth(request);
        return HOSP_ISRNE_CHK_AUTH;
    }

    @RequestMapping(value = "/getAuthLists", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> getAuthLists(FinHospIsrneChkAuthCondition cond){
        return finHospInsuranceCheckFeignService.getAuthLists(cond);
    }

    @RequestMapping(value = "/editCheckAuth", method = { RequestMethod.GET, RequestMethod.POST })
    public String editCheckAuth(Map<String, Object> map,String staffCode){
        List<Institution> ls = institutionService.getListByInstType(InstEnum.PROVINCE);
        Institution allProvinces = new Institution();
        allProvinces.setName("全部省区");
        allProvinces.setId(99999999L);
        allProvinces.setShortName("全部省区");
        allProvinces.setFirstSpell("QBSQ");
        allProvinces.setInstType(3);
        allProvinces.setTreepath(null);
        allProvinces.setGrade(1);
        allProvinces.setInstCode("99999999");
        ls.add(0, allProvinces);
        map.put("provinceList", ls);

        if(StringUtils.isNotBlank(staffCode)){
            List<FinHospIsrneChkAuth> finHospIsrneChkAuths = finHospInsuranceCheckFeignService.getAuthByStaffCode(staffCode);
            map.put("staffProvince", JSON.toJSONString(finHospIsrneChkAuths));
            map.put("staffCode", staffCode);
        }

        return EDIT_CHECK_AUTH;
    }

    @RequestMapping(value = "/saveAuth", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveAuth(@RequestBody Map<String, Object> mData) {
        // 先根据staffCode删除所有权限
        finHospInsuranceCheckFeignService.deleteAuth(mData.get("staffCode").toString());
        // 保存权限
        List<FinHospIsrneChkAuth> finHospIsrneChkAuths = JSON.parseArray(JSON.toJSONString(mData.get("provinceDist")), FinHospIsrneChkAuth.class);
        List<FinHospIsrneChkAuth> saveAuths = finHospIsrneChkAuths.stream()
                .filter(auth -> auth.getProvinceId().longValue() == 99999999L
                        || finHospIsrneChkAuths.stream().noneMatch(a -> a.getProvinceId().longValue() == 99999999L))
                .collect(Collectors.toList());
        saveAuths.stream().forEach(f->{
            f.setStaffName(mData.get("staffName").toString());
            f.setStaffCode(mData.get("staffCode").toString());
            f.setStaffId(Long.parseLong(mData.get("staffId").toString()));
        });
        finHospInsuranceCheckFeignService.saveAuth(saveAuths);

        return this.success();
    }


    @RequestMapping(value = "/downloadAttach", method = RequestMethod.GET)
    @ResponseBody
    public void downloadAttach(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        List<FinAttachment> attachments = finHospInsuranceCheckFeignService.getFinAttachments(id);

        if (attachments == null || attachments.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "未找到相关附件");
            return;
        }

        FinHospInsuranceCheck finHospInsuranceCheck = finHospInsuranceCheckFeignService.getFinHospInsuranceCheck(id);

        // 设置响应头
        response.setContentType("application/zip");
        // 文件名规则：省区+医院名称+检查时间+医院涉规金额
        String zipFileName = finHospInsuranceCheck.getHospParent() + finHospInsuranceCheck.getHospName() +
                new SimpleDateFormat("yyyy-MM-dd").format(finHospInsuranceCheck.getCheckDate()) +
                "(" + finHospInsuranceCheck.getRegulatoryAmount() + "万元)_" + System.currentTimeMillis() + ".zip";
        String encodedZipFileName = URLEncoder.encode(zipFileName, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedZipFileName);

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            for (FinAttachment attachment : attachments) {
                String fileUrl = attachment.getWebUrl();
                String fileName = sanitizeFileName(attachment.getFileName());

                if (StringUtils.isBlank(fileUrl) || StringUtils.isBlank(fileName)) {
                    continue; // 跳过无效条目
                }

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(fileUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(30000);

                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        continue; // 跳过无法下载的文件
                    }

                    try (InputStream inputStream = connection.getInputStream()) {
                        ZipEntry zipEntry = new ZipEntry(fileName);
                        zipOut.putNextEntry(zipEntry);

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            zipOut.write(buffer, 0, bytesRead);
                        }
                        zipOut.closeEntry();
                    }
                } catch (Exception e) {
                    // 日志记录错误，继续处理其他文件
                    LoggerFactory.getLogger(this.getClass()).error("下载文件失败: {}", fileUrl, e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
            zipOut.finish();
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("生成压缩包失败", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "生成压缩包失败");
        }
    }

    // 处理文件名中的非法字符和路径问题
    private String sanitizeFileName(String fileName) {
        if (fileName == null) {
            return "noname";
        }
        // 替换非法字符和路径分隔符
        String safeName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
        // 仅保留文件名部分，防止路径
        return new File(safeName).getName();
    }
    private void wrapperCond(FinHospInsuranceCheckCondition cond, boolean isRetNull){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
        if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
            // 医院登录
            cond.setHospId(shiroUser.getTenantId());
        } else {
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
                if (cond.getProvince() != null && cond.getProvince() > 0) {
                    if (cond.getHospId() != null && cond.getHospId() > 0) {
                        Institution inst = institutionService.getById(cond.getHospId());
                        cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                        cond.setHospList(null);
                    } else {
                        Object instList = hospHandleService.allHospFromParent(cond.getProvince());
                        if (instList != null) {
                                JSONArray ja = (JSONArray) instList;
                                ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                                ja.stream().forEach(j -> {
                                    JSONObject jo = (JSONObject) j;
                                    hospList.add(jo.getLong("ahisHosp"));
                                });
                                cond.setHospId(null);
                                // 如果hospList为空，说明当前机构下没有医院，直接返回
                                if (hospList.size() > 0) {
                                    cond.setHospList(hospList);
                                } else {
                                    isRetNull=true;
                                }
                            }
                        }
                }else{
                    if (cond.getHospId() != null && cond.getHospId() > 0) {
                        Institution inst = institutionService.getById(cond.getHospId());
                        cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                        cond.setHospList(null);
                    } else {
                        List<FinHospIsrneChkAuth> finHospIsrneChkAuths = finHospInsuranceCheckFeignService.getAuthByStaffCode(shiroUser.getLoginCode());
                        List<Long> provinceIds = finHospIsrneChkAuths.stream().map(FinHospIsrneChkAuth::getProvinceId).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(provinceIds)) {
                            cond.setHospId(null);
                            if(provinceIds.contains(99999999L)){
                                cond.setHospList(null);
                            }else{
                                ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                                provinceIds.stream().forEach(p -> {
                                    Object instList = hospHandleService.allHospFromParent(p);
                                    if (instList != null) {
                                        JSONArray ja = (JSONArray) instList;
                                        ja.stream().forEach(j -> {
                                            JSONObject jo = (JSONObject) j;
                                            hospList.add(jo.getLong("ahisHosp"));
                                        });
                                    }
                                });
                                // 如果hospList为空，说明当前机构下没有医院，直接返回
                                if (hospList.size() > 0) {
                                    cond.setHospList(hospList);
                                } else {
                                    isRetNull=true;
                                }
                            }
                        } else {
                            isRetNull=true;
                        }
                    }
                }
            } else {
                // 省区登录
                if (cond.getHospId() != null && cond.getHospId() > 0) {
                    Institution inst = institutionService.getById(cond.getHospId());
                    cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
                    cond.setHospList(null);
                } else {
                    Object instList;
                    if (provinceRoleConfigList.size() > 0) {
                        instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
                    } else {
                        instList = hospHandleService.allHospFromParent(shiroUser.getInstId());
                    }

                    if (instList != null) {
                        JSONArray ja = (JSONArray) instList;
                        ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                        ja.stream().forEach(j -> {
                            JSONObject jo = (JSONObject) j;
                            hospList.add(jo.getLong("ahisHosp"));
                        });
                        cond.setHospId(null);
                        // 如果hospList为空，说明当前机构下没有医院，直接返回
                        if (hospList.size() > 0) {
                            cond.setHospList(hospList);
                        } else {
                            isRetNull=true;
                        }
                    }
                }
            }
        }
        /** inst_id=>hosp_id */
        if (Objects.nonNull(cond.getHospId()) && cond.getHospId() > 9999) {
            Institution inst = institutionService.getById(cond.getHospId());
            if (!ObjectUtils.isEmpty(inst)) {
                cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
            }
        }
    }

    private void getLoginAuth(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
        List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
        if(shiroUser.getIsHosp()&&provinceRoleConfigList.size()==0){
            // 医院人员登录
            request.setAttribute("empType", 3);
            request.setAttribute("insiId", shiroUser.getInstId());
        }else{
            // 集团或省区登录
            request.setAttribute("insiId", shiroUser.getInstId());
            if(shiroUser.getInstId().longValue() == Constants.GroupInstId.longValue()){
                // 集团登录
                request.setAttribute("empType", 1);
            }else{
                // 省区登录
                request.setAttribute("empType", 2);
                request.setAttribute("insiId", provinceRoleConfigList.size()>0?provinceRoleConfigList.get(0).getProvinceId():shiroUser.getInstId());
            }
        }
    }

}
