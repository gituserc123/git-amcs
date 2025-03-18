package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.api.amcs.law.condition.LawBaseCondition;
import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.api.amcs.law.constant.LawConstants;
import com.aier.cloud.api.amcs.law.domain.*;
import com.aier.cloud.basic.api.domain.enums.FileEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.file.FileInfo;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.FileService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.adverse.utils.FileValidator;
import com.aier.cloud.biz.ui.biz.law.feign.*;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/ui/amcs/law/baseui")
public class LawBaseUiController extends BaseController {

    @Autowired
    private LawNodeAuthFeignService lawNodeAuthFeignService;

    @Autowired
    private LawFlowInstanceFeignService lawFlowInstanceFeignService;

    @Autowired
    private LawFlowNodeFeignService lawFlowNodeFeignService;

    @Autowired
    private LawAuditDetailFeignService lawAuditDetailFeignService;

    @Autowired
    private LawNodeInfoFeignService lawNodeInfoFeignService;

    @Autowired
    private LawAuditOpinionFeignService lawAuditOpinionFeignService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private LawAttachmentFeignService lawAttachmentFeignService;

    @Autowired
    private HospHandleService hospHandleService;

    @Autowired
    private FileService fs;

    @PostMapping(value = "/upload")
    @ResponseBody
    public Object upload(@RequestParam("bizId") Long bizId,
                         @RequestParam("bizType") String bizType,
                         @RequestParam("bizCode") String bizCode,
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
                lawAttachment.setBizType(bizType);
                lawAttachment.setBizCode(bizCode);
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
            result.put("attachCode", attachCode);

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


    /**
     * 查询当前节点前的节点
     */
    @RequestMapping(value = "/getPreNode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<LawFlowNode> getPreNode(@RequestParam("bizId") Long bizId){
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(bizId);
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
        return getNodesBeforeCurrent(lawFlowNodes,Long.parseLong(lawFlowInstance.getCurrentNode()));
    }

    /**
     * 查询所有民事诉讼仲裁案件实体数据
     */
    @RequestMapping(value = "/getLawOpinionList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getLawOpinionList(@RequestParam("bizId") Long bizId){
        List<LawAuditOpinion> opinions = Lists.newArrayList();
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(bizId);
        if(Objects.nonNull(lawFlowInstance)){
            List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
            List<LawAuditDetail> auditDetails = getAuditDetailByInstanceId(lawFlowInstance.getId());
            if(CollectionUtils.isNotEmpty(auditDetails) && auditDetails.size()>0){
                auditDetails.stream().forEach(ad -> ad.setNodeName(lawFlowNodes.stream().filter(node -> node.getNodeId().longValue() == ad.getNodeId().longValue()).findFirst().get().getNodeName()));
                LawAuditOpinionCondition cond = new LawAuditOpinionCondition();
                cond.setDetailIds(auditDetails.stream().map(LawAuditDetail::getId).collect(Collectors.toList()));
                opinions = lawAuditOpinionFeignService.getListByDetailIds(cond);
                if(CollectionUtils.isNotEmpty(opinions) && opinions.size()>0){
                    opinions.stream().forEach(on -> on.setNodeName(auditDetails.stream().filter(ad -> ad.getId().longValue() == on.getDetailId().longValue()).findFirst().get().getNodeName()));
                }
            }
        }
        return opinions;
    }

    /**
     * 流程退回
     * @param dataMaps
     * @return
     */
    @RequestMapping(value = "/flowReject", method = RequestMethod.POST)
    @ResponseBody
    public Object flowReject(@RequestBody Map<String,Object> dataMaps){
        JSONObject data = (JSONObject) dataMaps.get("bizForm");
        Long bizId = data.getLong("id");
        String opinion = MapUtils.getString(dataMaps,"opinion");
        Long currentNodeId = MapUtils.getLong(dataMaps,"commitNode");
        String commiteNodeName = MapUtils.getString(dataMaps,"commitNodeName");
        rejectHandler(bizId,opinion,currentNodeId,commiteNodeName);
        return success();
    }

    /**
     * 根据员工id获取节点权限列表
     * @param staffId
     * @return
     */
    protected List<LawNodeAuth> getLawNodeAuthListByStaffId(Long staffId) {
        List<LawNodeAuth> lawNodeAuths = ShiroUtils.getSessionAttr("SessionLawNodeAuths_" + staffId);
        if(CollectionUtils.isEmpty(lawNodeAuths)){
            lawNodeAuths = lawNodeAuthFeignService.getLawNodeAuthListByStaffId(staffId);
            if (CollectionUtils.isNotEmpty(lawNodeAuths)) {
                ShiroUtils.setSessionAttr("SessionLawNodeAuths_" + staffId,lawNodeAuths);
            }
        }
        return lawNodeAuths;
    }

    protected <T extends BaseEntity> void infoHandler(HttpServletRequest request,Long bizId, Long bizInstId,T bizEntity) {
        if(Objects.nonNull(bizId) && bizId.longValue()>0){
            // 1.查询授权信息表T_LAW_NODE_AUTH;
            List<LawNodeAuth> lawNodeAuths = getLawNodeAuthListByStaffId(ShiroUtils.getId());
            // 2.获取流程实例信息,放到shiro的session中;
            LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(bizId);
            // 3.获取流程节点信息,放到shiro的session中;
            List<LawFlowNode> lawFlowNodes = Lists.newArrayList();
            if(Objects.nonNull(lawFlowInstance)){
                lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
            }
            // 4.查询审核明细表T_LAW_FLOW_AUDIT_DETAIL;
            List<LawAuditDetail> lawAuditDetails = Lists.newArrayList();
            if(Objects.nonNull(lawFlowInstance)){
                lawAuditDetails = getAuditDetailByInstanceId(lawFlowInstance.getId());
            }
            // 5.判断"暂存","提交","退回"按钮的显示隐藏
            htmlElementShow(request,lawNodeAuths,lawFlowInstance,lawFlowNodes,lawAuditDetails,bizInstId);
            request.setAttribute("bizId", bizId);
            request.setAttribute("bizEntity", bizEntity);
            // 查询附件信息
            List<LawAttachment> attachs = lawAttachmentFeignService.selectByBizId(bizId);
            request.setAttribute("attachs", JSON.toJSONString(attachs));

        }else{
            request.setAttribute("opDivDisplay", "flex");
            request.setAttribute("backBtnDisplay", "none");
            request.setAttribute("saveBtnDisplay", "none");
        }
    }

    protected Institution getParentInst(){
        Long curUserInstId = ShiroUtils.getInstId();
        Institution institution = institutionService.getById(curUserInstId);
        String treePath = institution.getTreepath();
        if(Objects.nonNull(treePath) && treePath.length() > 0){
            List<Long> result = Arrays.stream(treePath.split(","))  // 分割字符串
                    .map(String::trim)                           // 去除前后空格
                    .filter(s -> !s.isEmpty())                   // 过滤空字符串
                    .map(Long::valueOf)                          // 转为 Long 类型
                    .collect(Collectors.toList());
            if(result.size() == 1){
                return institutionService.getById(result.get(0));
            }else{
                return institutionService.getById(result.get(1));
            }
        }else{
            return institutionService.getById(institution.getParentId());
        }
    }

    protected void saveHandler(Long bizId,String bizType,String bizCode,boolean saveAttachFlag,List<LawAttachment> attachments){
        if(saveAttachFlag){
            // 保存附件
            if(Objects.nonNull(attachments) && attachments.size()>0){
                attachments.forEach(ach -> {
                    ach.setBizId(bizId);
                    ach.setBizType(bizType);
                    ach.setBizCode(bizCode);
                    ach.setCreator(ShiroUtils.getId());
                    ach.setCreateDate(new Date());
                    ach.setModifer(ShiroUtils.getId());
                    ach.setModifyDate(new Date());
                    lawAttachmentFeignService.insert(ach);
                });
            }
        }
        // 暂存，或提交时，默认把当前操作人加入到申请人节点权限;同时更新Session中的节点权限信息
        List<LawNodeAuth> lawNodeAuths = lawNodeAuthFeignService.getLawNodeAuthListByStaffId(ShiroUtils.getId());
        Boolean isAddNodeAuth = false;
        if(Objects.nonNull(lawNodeAuths) && lawNodeAuths.size()>0){
            if(!isHasNodeAuth(lawNodeAuths,"NODE001")){
                isAddNodeAuth = true;
            }
        }else{
            isAddNodeAuth = true;
        }
        if(isAddNodeAuth){
            saveNodeAuthByNodeCode("NODE001");
            List<LawNodeAuth> sessLawNodeAuths = lawNodeAuthFeignService.getLawNodeAuthListByStaffId(ShiroUtils.getId());
            // 更新session中的节点权限信息
            ShiroUtils.setSessionAttr("SessionLawNodeAuths_" + ShiroUtils.getId(),sessLawNodeAuths);
        }
    }

    /**
     * 根据业务id获取流程实例
     * @param bizId
     * @return
     */
    protected LawFlowInstance getFlowInstanceByBizId(Long bizId) {
        LawFlowInstance lawFlowInstance = ShiroUtils.getSessionAttr("SessionLawFlowInstance_" + bizId);
        if(lawFlowInstance == null){
            lawFlowInstance = lawFlowInstanceFeignService.getFlowInstanceByBizId(bizId);
            if (lawFlowInstance != null) {
                ShiroUtils.setSessionAttr("SessionLawFlowInstance_" + bizId,lawFlowInstance);
            }
        }
        return lawFlowInstance;
    }

    /**
     * 根据流程id获取流程节点
     * @param flowId
     * @return
     */
    protected List<LawFlowNode> getFlowNodeByFlowId(Long flowId) {
        List<LawFlowNode> lawFlowNodes = ShiroUtils.getSessionAttr("SessionLawFlowNodes_" + flowId);
        if(CollectionUtils.isEmpty(lawFlowNodes)){
            lawFlowNodes = lawFlowNodeFeignService.getFlowNodeByFlowId(flowId);
            if (CollectionUtils.isNotEmpty(lawFlowNodes)) {
                ShiroUtils.setSessionAttr("SessionLawFlowNodes_" + flowId,lawFlowNodes);
            }
        }
        return lawFlowNodes;
    }

    /**
     * 根据流程实例id获取审核明细
     * @param instanceId
     * @return
     */
    protected List<LawAuditDetail> getAuditDetailByInstanceId(Long instanceId) {
        List<LawAuditDetail> lawAuditDetails = ShiroUtils.getSessionAttr("SessionLawAuditDetails_" + instanceId);
        if(CollectionUtils.isEmpty(lawAuditDetails)){
            lawAuditDetails = lawAuditDetailFeignService.getLawAuditDetailListByInstanceId(instanceId);
            if (CollectionUtils.isNotEmpty(lawAuditDetails)) {
                ShiroUtils.setSessionAttr("SessionLawAuditDetails_" + instanceId,lawAuditDetails);
            }
        }
        return lawAuditDetails;
    }

    /**
     * 页面元素显示隐藏
     * @param request
     * @param lawNodeAuths
     * @param lawFlowInstance
     * @param lawFlowNodes
     * @param lawAuditDetails
     */
    protected void htmlElementShow(HttpServletRequest request,List<LawNodeAuth> lawNodeAuths, LawFlowInstance lawFlowInstance,
                                   List<LawFlowNode> lawFlowNodes,List<LawAuditDetail> lawAuditDetails,Long instId){
        // 1."暂存","退回"等按钮的显示隐藏
        if(lawFlowInstance != null && lawAuditDetails != null && lawAuditDetails.size() > 0){
            // 当流程实例不为空且审核明细不为空时
            Long currentNodeId = Long.parseLong(lawFlowInstance.getCurrentNode());
            if(lawNodeAuths.stream().anyMatch(lawNodeAuth -> lawNodeAuth.getNodeId().longValue() == currentNodeId)){
                // 当前用户拥有流程当前节点的权限,则侧边按钮显示
                request.setAttribute("opDivDisplay", "flex");
                // 判断是否最后一个节点,最后一个节点时,流程"提交"按钮隐藏
                if(isLastNode(lawFlowNodes,currentNodeId)){
                    request.setAttribute("saveBtnDisplay", "none");
                }else{
                    request.setAttribute("saveBtnDisplay", "inline");
                }
                // lawAuditDetails按创建时间倒序排序
                lawAuditDetails.sort((detail1, detail2) -> detail2.getCreateDate().compareTo(detail1.getCreateDate()));
                // 获取最新的一条审核明细
                String lastAction = lawAuditDetails.isEmpty() ? null : lawAuditDetails.get(0).getAction();
                if(Objects.nonNull(lastAction) && lastAction.equals("REJECT")){
                    // 最新一条审核明细的操作为"退回"时,"退回"按钮隐藏
                    request.setAttribute("backBtnDisplay", "none");
                    request.setAttribute("attachAddBtnDisplay", "inline");
                    request.setAttribute("attachDelBtnVisible", "visible");
                    // TODO 判断是否要弹出选择提交节点下拉框;判断是否要保存业务表单;初始化下拉框
                    request.setAttribute("nodeSelectEle", "true");
                    request.setAttribute("isSaveBizForm", "true");
                }else{
                    // 正常提交时,"退回"按钮显示
                    request.setAttribute("backBtnDisplay", "inline");
                    request.setAttribute("attachAddBtnDisplay", "none");
                    request.setAttribute("attachDelBtnVisible", "hidden");
                    request.setAttribute("nodeSelectEle", "false");
                    request.setAttribute("isSaveBizForm", "false");
                }
                request.setAttribute("stashBtnDisplay", "none");
                request.setAttribute("initBtnDisplay", "none");
            }else{
                // 当前用户不拥有流程当前节点的权限,则侧边按钮隐藏;只能查看,不能操作
                request.setAttribute("opDivDisplay", "none");
                request.setAttribute("attachAddBtnDisplay", "none");
                request.setAttribute("attachDelBtnVisible", "hidden");
            }
            LawFlowNode curNode = lawFlowNodes.stream().filter(node -> node.getNodeId().equals(currentNodeId)).findFirst().orElse(null);
            if(curNode.getNodeCode().equals("NODE001")){
                // 当前节点为申请人节点时,显示"暂存"按钮
                request.setAttribute("curNodeName", "");
            }else{
                request.setAttribute("curNodeName", curNode.getNodeName());
            }
        }else{
            if(isHasNodeAuth(lawNodeAuths,"NODE001") && instId.longValue() == ShiroUtils.getInstId().longValue()){
                // 判断当前用户是否有申请人角色，同时是否属于当前业务的创建机构
                request.setAttribute("opDivDisplay", "flex");
                request.setAttribute("backBtnDisplay", "none");
                request.setAttribute("saveBtnDisplay", "none");
                request.setAttribute("stashBtnDisplay", "inline");
                request.setAttribute("initBtnDisplay", "inline");
                request.setAttribute("attachAddBtnDisplay", "inline");
                request.setAttribute("attachDelBtnVisible", "visible");
            }else{
                // 没有权限,则侧边按钮隐藏;只能查看,不能操作
                request.setAttribute("opDivDisplay", "none");
                request.setAttribute("attachAddBtnDisplay", "none");
                request.setAttribute("attachDelBtnVisible", "hidden");
            }
            request.setAttribute("curNodeName", "");
        }

    }

    /**
     * // TODO 待完善
     *    1 是否存在省区法务节点
     *    2 当前申请人所在机构是否集团
     *    3 默认无省区法务流程
     * 获取流程id
     * @return
     */
    protected Long getFlowId(){
        Long curUserInstId = ShiroUtils.getInstId();
        if(curUserInstId.longValue() == LawConstants.GroupInstId.longValue()){
            // 集团登录作为申请人,返回"流程3":申报人(开始)→集团各职能条线总监（法务主管选择）→集团法律事务部经理→法务监察中心总监（对接OA上报选择权）
            return 1894296705186250823L;
        }
        // 判断当前申请人所在机构是否包含省区法务节点
        Institution institution = institutionService.getById(curUserInstId);
        String treePath = institution.getTreepath();
        if(Objects.nonNull(treePath) && treePath.length() > 0){
            List<Long> result = Arrays.stream(treePath.split(","))  // 分割字符串
                    .map(String::trim)                           // 去除前后空格
                    .filter(s -> !s.isEmpty())                   // 过滤空字符串
                    .map(Long::valueOf)                          // 转为 Long 类型
                    .collect(Collectors.toList());
            result.add(curUserInstId);
            List<LawNodeAuth> lawNodeAuths = lawNodeAuthFeignService.getLawNodeAuthListByParams(result,"NODE003");
            if(CollectionUtils.isNotEmpty(lawNodeAuths) && lawNodeAuths.size() > 0){
                // 存在省区法务节点
                return 1894296705186250824L;
            }
        }
        // 默认返回"流程1":申报人(开始)→医院CEO→省区CEO（抄送节点，非必经）→集团法务主管（配置分工区域）→集团法律事务部经理→法务监察中心总监（对接OA上报选择权）
        return 1894296705186250825L;
    }

    /**
     * 创建流程实例表
     * @param flowId
     * @param bizId
     * @param bizCode
     * @param bizType
     * @param bizName
     */
    protected LawFlowInstance createFlowInstance(Long flowId,Long bizId,String bizCode,Integer bizType,String bizName){
        List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(flowId);
        LawFlowInstance lawFlowInstance = new LawFlowInstance();
        lawFlowInstance.setFlowId(flowId);
        lawFlowInstance.setBizId(bizId);
        lawFlowInstance.setBizCode(bizCode);
        lawFlowInstance.setBizType(bizType);
        lawFlowInstance.setBizName(bizName);
        lawFlowInstance.setPrevNode(lawFlowNodes.get(0).getNodeId().toString());
        lawFlowInstance.setPrevNodeName(lawFlowNodes.get(0).getNodeName());
        lawFlowInstance.setCurrentNode(lawFlowNodes.get(1).getNodeId().toString());
        lawFlowInstance.setCurrentNodeName(lawFlowNodes.get(1).getNodeName());
        lawFlowInstance.setCreator(ShiroUtils.getId());
        lawFlowInstance.setModifer(ShiroUtils.getId());
        lawFlowInstance.setCreateDate(new Date());
        JSONObject jo = (JSONObject)lawFlowInstanceFeignService.save(lawFlowInstance);
        lawFlowInstance = JSON.parseObject(jo.toJSONString(), LawFlowInstance.class);
        return lawFlowInstance;
    }

    protected LawFlowInstance insertOrUpdateFlowInstance(LawFlowInstance lawFlowInstance){
        JSONObject jo = (JSONObject)lawFlowInstanceFeignService.save(lawFlowInstance);
        lawFlowInstance = JSON.parseObject(jo.toJSONString(), LawFlowInstance.class);
        return lawFlowInstance;
    }

    /**
     * 添加流程明细表信息
     * @param lawFlowInstance
     */
    protected LawAuditDetail addLawDetailInfo(LawFlowInstance lawFlowInstance,String action){
        LawAuditDetail lawAuditDetail = new LawAuditDetail();
        lawAuditDetail.setInstanceId(lawFlowInstance.getId());
        lawAuditDetail.setNodeId(Long.parseLong(lawFlowInstance.getPrevNode()));
        lawAuditDetail.setStaffId(ShiroUtils.getId());
        lawAuditDetail.setAction(action);
        lawAuditDetail.setNextNodeId(Long.parseLong(lawFlowInstance.getCurrentNode()));
        lawAuditDetail.setCreator(ShiroUtils.getId());
        return lawAuditDetailFeignService.save(lawAuditDetail);
    }

    protected void addLawAuditOpinion(LawAuditDetail lawAuditDetail,String opinion){
        LawAuditOpinion lawAuditOpinion = new LawAuditOpinion();
        lawAuditOpinion.setDetailId(lawAuditDetail.getId());
        lawAuditOpinion.setOpinion(opinion);
        lawAuditOpinion.setCreator(ShiroUtils.getId());
        lawAuditOpinion.setCreateDate(new Date());
        lawAuditOpinionFeignService.save(lawAuditOpinion);
    }

    protected Boolean isHasNodeAuth(List<LawNodeAuth> lawNodeAuths, String nodeCode) {
        return lawNodeAuths.stream().anyMatch(lawNodeAuth -> lawNodeAuth.getNodeCode().equals(nodeCode));
    }

    protected void saveNodeAuthByNodeCode(String nodeCode) {
        LawNodeInfo lawNodeInfo = lawNodeInfoFeignService.getNodeByCode(nodeCode);
        LawNodeAuth lawNodeAuth = new LawNodeAuth();
        lawNodeAuth.setStaffId(ShiroUtils.getId());
        lawNodeAuth.setStaffName(ShiroUtils.getStaff().getLoginName());
        lawNodeAuth.setStaffInstName(ShiroUtils.getInstName());
        lawNodeAuth.setStaffInstId(ShiroUtils.getInstId());
        lawNodeAuth.setNodeId(lawNodeInfo.getId());
        lawNodeAuth.setNodeName(lawNodeInfo.getNodeName());
        lawNodeAuth.setNodeCode(nodeCode);
        lawNodeAuthFeignService.save(lawNodeAuth);
    }

    protected void saveNodeAuthFromPage(Map<String, Object> mData){
        // 先根据staffId删除所有权限
        lawNodeAuthFeignService.deleteNodeAuth(MapUtils.getLong(mData,"staffId"),MapUtils.getLong(mData,"staffInstId"));
        // 保存权限
        List<LawNodeAuth> lawNodeAuths = JSON.parseArray(JSON.toJSONString(mData.get("lawNodeAuths")), LawNodeAuth.class);
        List<LawNodeInfo> nodeInfos = getAllNodeInfo();
        lawNodeAuths.stream().forEach(la -> {
            la.setStaffId(MapUtils.getLong(mData,"staffId"));
            la.setStaffName(MapUtils.getString(mData,"staffName"));
            la.setStaffCode(MapUtils.getString(mData,"staffCode"));
            la.setStaffInstId(MapUtils.getLong(mData,"staffInstId"));
            la.setStaffInstName(MapUtils.getString(mData,"staffInstName"));
            nodeInfos.stream().filter(ni -> ni.getId().longValue()==la.getNodeId().longValue()).findFirst().ifPresent(ni -> {
                la.setNodeName(ni.getNodeName());
                la.setNodeCode(ni.getNodeCode());
            });
        });
        lawNodeAuthFeignService.saveNodeAuth(lawNodeAuths);
    }

    /**
     *
     * @param lawFlowNodes
     * @param currentNodeId
     * @return
     */
    private boolean isLastNode(List<LawFlowNode> lawFlowNodes, Long currentNodeId) {
        return lawFlowNodes.stream()
                .reduce((first, second) -> second) // 获取最后一个元素
                .map(node -> node.getNodeId().equals(currentNodeId)) // 比较nodeId
                .orElse(false); // 处理空列表情况
    }

    /**
     * 获取当前节点之前的节点
     * @param lawFlowNodes
     * @param currentNodeId
     * @return
     */
    public List<LawFlowNode> getNodesBeforeCurrent(List<LawFlowNode> lawFlowNodes, Long currentNodeId) {
        // 找到 currentNodeId 的索引
        int index = IntStream.range(0, lawFlowNodes.size())
                .filter(i -> lawFlowNodes.get(i).getNodeId().equals(currentNodeId))
                .findFirst()
                .orElse(lawFlowNodes.size()); // 如果找不到，返回列表大小

        // 返回索引之前的节点
        return lawFlowNodes.stream().limit(index).collect(Collectors.toList());
    }

    /**
     * 获取当前节点之后的下一个节点
     * @param lawFlowNodes
     * @param currentNodeId
     * @return
     */
    protected LawFlowNode getNextNodeAfterCurrent(List<LawFlowNode> lawFlowNodes, Long currentNodeId) {
        return lawFlowNodes.stream()
                .filter(node -> node.getNodeId().equals(currentNodeId))
                .findFirst()
                .flatMap(currentNode -> lawFlowNodes.stream()
                        .skip(lawFlowNodes.indexOf(currentNode) + 1)
                        .findFirst())
                .orElse(null); // 返回 LawFlowNode 或 null
    }

    protected Map<String,Object> processFlow(Long bizId,String opinion,String action){
        Map<String,Object> retMaps = Maps.newHashMap();
        retMaps.put("saveFlag", Boolean.FALSE);
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(bizId);
        String preNode = lawFlowInstance.getPrevNode();
        String preNodeName = lawFlowInstance.getPrevNodeName();
        String curNode = lawFlowInstance.getCurrentNode();
        String curNodeName = lawFlowInstance.getCurrentNodeName();
        // 1.判断最后一条审核记录是"SUBMIT"，还是"REJECT";
        List<LawAuditDetail> lawAuditDetails = getAuditDetailByInstanceId(lawFlowInstance.getId());
        if(CollectionUtils.isNotEmpty(lawAuditDetails) && lawAuditDetails.size()>0){
            // 1.1 如果是"REJECT"，则需要保存表单信息,保存标志为设为True
            if("REJECT".equals(lawAuditDetails.get(0).getAction())){
                // 默认提交到退回的发起节点
                lawFlowInstance.setCurrentNode(preNode);
                lawFlowInstance.setCurrentNodeName(preNodeName);
                retMaps.put("saveFlag", Boolean.TRUE);
            }else{
                // 获取流程当前节点的下一个节点
                List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
                LawFlowNode lawFlowNode = getNextNodeAfterCurrent(lawFlowNodes,Long.parseLong(curNode));
                lawFlowInstance.setCurrentNode(String.valueOf(lawFlowNode.getNodeId()));
                lawFlowInstance.setCurrentNodeName(lawFlowNode.getNodeName());
            }
        }else{
            // 获取流程当前节点的下一个节点
            List<LawFlowNode> lawFlowNodes = getFlowNodeByFlowId(lawFlowInstance.getFlowId());
            LawFlowNode lawFlowNode = getNextNodeAfterCurrent(lawFlowNodes,Long.parseLong(curNode));
            lawFlowInstance.setCurrentNode(String.valueOf(lawFlowNode.getNodeId()));
            lawFlowInstance.setCurrentNodeName(lawFlowNode.getNodeName());
        }
        // 2.修改流程实例表信息
        lawFlowInstance.setPrevNode(curNode);
        lawFlowInstance.setPrevNodeName(curNodeName);
        lawFlowInstance.setModifer(ShiroUtils.getId());
        lawFlowInstance.setModifyDate(new Date());
        lawFlowInstance = insertOrUpdateFlowInstance(lawFlowInstance);
        // 3.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + bizId);
        // 4.保存审核明细
        LawAuditDetail retdetail = addLawDetailInfo(lawFlowInstance,action);
        // 5.更新session中的流程明细信息
        ShiroUtils.removeSessionAttr("SessionLawAuditDetails_" + lawFlowInstance.getId());
        // 6.保存审核意见信息
        if(Objects.nonNull(opinion) && opinion.length() > 0){
            if(Objects.nonNull(retdetail.getId()) && retdetail.getId().longValue()>0){
                addLawAuditOpinion(retdetail,opinion);
            }
        }
        // TODO 发送企业微信消息

        return retMaps;
    }

    protected void rejectHandler(Long bizId,String opinion,Long currentNodeId,String commiteNodeName){
        LawFlowInstance lawFlowInstance = getFlowInstanceByBizId(bizId);
        String curNode = lawFlowInstance.getCurrentNode();
        String curNodeName = lawFlowInstance.getCurrentNodeName();
        // 2.修改流程实例表信息
        lawFlowInstance.setCurrentNode(String.valueOf(currentNodeId));
        lawFlowInstance.setCurrentNodeName(commiteNodeName);
        lawFlowInstance.setPrevNode(curNode);
        lawFlowInstance.setPrevNodeName(curNodeName);
        lawFlowInstance.setModifer(ShiroUtils.getId());
        lawFlowInstance.setModifyDate(new Date());
        lawFlowInstance = insertOrUpdateFlowInstance(lawFlowInstance);
        // 3.更新session中的流程实例信息
        ShiroUtils.removeSessionAttr("SessionLawFlowInstance_" + bizId);
        // 4.保存审核明细
        LawAuditDetail retdetail = addLawDetailInfo(lawFlowInstance,"REJECT");
        // 5.更新session中的流程明细信息
        ShiroUtils.removeSessionAttr("SessionLawAuditDetails_" + lawFlowInstance.getId());
        // 6.保存审核意见信息
        if(Objects.nonNull(opinion) && opinion.length() > 0){
            if(Objects.nonNull(retdetail.getId()) && retdetail.getId().longValue()>0){
                addLawAuditOpinion(retdetail,opinion);
            }
        }
        // TODO 发送企业微信消息
    }

    protected List<LawNodeInfo> getAllNodeInfo(){
        List<LawNodeInfo> lawNodeInfos = ShiroUtils.getSessionAttr("SessionLawNodeInfos");
        if(CollectionUtils.isEmpty(lawNodeInfos)){
            lawNodeInfos = lawNodeInfoFeignService.getAllNodeInfo();
            if (CollectionUtils.isNotEmpty(lawNodeInfos)) {
                ShiroUtils.setSessionAttr("SessionLawNodeInfos",lawNodeInfos);
            }
        }
        return lawNodeInfos;
    }

    protected PageResponse<Map<String, Object>> getNodeAuthPage(LawNodeAuthCondition cond){
        return lawNodeAuthFeignService.getAll(cond);
    }


    protected <T extends LawBaseCondition> Boolean wrapperCond(T cond){
        boolean isRetNull = false;
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
        provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
        if (shiroUser.getIsHosp()) {
            // 医院登录
            cond.setInstId(shiroUser.getInstId());
        } else {
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
                if (cond.getProvince() != null && cond.getProvince() > 0) {
                    if (cond.getInstId() != null && cond.getInstId() > 0) {
                        Institution inst = institutionService.getById(cond.getInstId());
                        cond.setInstId(Long.parseLong(inst.getAhisHosp().toString()));
                        cond.setInstList(null);
                    } else {
                        Object instList = hospHandleService.allHospFromParent(cond.getProvince());
                        if (instList != null) {
                            JSONArray ja = (JSONArray) instList;
                            ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                            ja.stream().forEach(j -> {
                                JSONObject jo = (JSONObject) j;
                                hospList.add(jo.getLong("ahisHosp"));
                            });
                            cond.setInstId(null);
                            // 如果hospList为空，说明当前机构下没有医院，直接返回
                            if (hospList.size() > 0) {
                                cond.setInstList(hospList);
                            } else {
                                isRetNull=true;
                            }
                        }
                    }
                }
            } else {
                // 省区登录
                if (cond.getInstId() != null && cond.getInstId() > 0) {
                    Institution inst = institutionService.getById(cond.getInstId());
                    cond.setInstId(Long.parseLong(inst.getAhisHosp().toString()));
                    cond.setInstList(null);
                } else {
                    Object instList = hospHandleService.allHospFromParent(shiroUser.getInstId());

                    if (instList != null) {
                        JSONArray ja = (JSONArray) instList;
                        ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
                        ja.stream().forEach(j -> {
                            JSONObject jo = (JSONObject) j;
                            hospList.add(jo.getLong("ahisHosp"));
                        });
                        cond.setInstId(null);
                        // 如果hospList为空，说明当前机构下没有医院，直接返回
                        if (hospList.size() > 0) {
                            cond.setInstList(hospList);
                        } else {
                            isRetNull=true;
                        }
                    }
                }
            }
        }
        /** inst_id=>hosp_id */
        /*if (Objects.nonNull(cond.getInstId()) && cond.getInstId() > 9999) {
            Institution inst = institutionService.getById(cond.getInstId());
            if (!ObjectUtils.isEmpty(inst)) {
                cond.setInstId(Long.parseLong(inst.getAhisHosp().toString()));
            }
        }*/

        return isRetNull;
    }

}