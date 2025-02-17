package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.response.domain.outp.PatientSpecialWarn;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.util.DateUtils;
import com.aier.cloud.basic.log.annotion.AierServiceLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;

import com.aier.cloud.biz.service.biz.sys.feign.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.aier.basic.weixin.api.AwxService;
import com.aier.basic.weixin.api.CpMesg;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeDealResult;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.EventTypeEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.AuthorizeCondition;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeAttachmentMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeBasicInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeOperationRecordMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeEventTagsMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeCommonService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ProvinceRoleService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeAttachment;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.ProvinceRoleConfig;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.SpringBootBeanUtil;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Service
@Transactional(rollbackFor=Throwable.class)
public class AeCommonServiceImpl implements AeCommonService {
	
	private static final Logger logger = LoggerFactory.getLogger(AeCommonServiceImpl.class);
	 
	private final static String SERVICE_PREFIX = "com.aier.cloud.biz.service.biz.amcs.adverse.service.impl.";
	
	private final static String ENTITY_PREFIX= "com.aier.cloud.biz.service.biz.amcs.adverse.entity.";
	
	private final static String SERVICE_SUFFIX = "ServiceImpl";	
	
	@Value("${amcs.adverse.medicalRole}")
    private Long medicalRoleId;
	
	@Value("${amcs.adverse.careRole}")
    private Long careRoleId;
	
	@Value("${amcs.adverse.ceoRole}")
    private Long ceoRoleId;
	
	@Value("${amcs.adverse.deanRole}")
    private Long deanRoleId;
	
	@Value("${amcs.adverse.bmRole}")
	private Long bmRoleId;
	
	@Value("${amcs.adverse.groupRole}")
    private Long groupRoleId;
	
	@Value("${amcs.adverse.provinceRoles}")
    private String provinceRoleIds;
	
	@Value("${amcs.adverse.provinceDirectorRole}")
	private Long provinceDirectorRoleId;

	@Autowired
	private AeOperationRecordMapper aeOperationRecordMapper;
	
	@Autowired
	private AeBasicInfoMapper aeBasicInfoMapper;
	
	@Autowired
	private AeAttachmentMapper aeAttachmentMapper;
	
	@Autowired
	private AeEventTagsMapper aeEventTagsMapper;
	
	@Autowired
    private AuthorizeService authorizeService;

	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private InstitutionService institutionService;
	
	@Autowired
    private ProvinceRoleService provinceRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MedicalService medicalService;

	private static final String roleName = "科主任管理";
    //角塑不良反应
	private static final Integer SIRS = 25;

	private String stdJobcodes="10000022,10000023,10000028,10000029,10000031,10000035,10000036,10000037,10000038,10000039,10000040,10000041,10000042,10000043,10000044,10000045,10000046,10000047,10000048,10000049,10000050,10000051,10000052,10000054,10000057,10000404,10000405,10000406,10000407,10000451,10000479,10000486,10000493,10000500,10000569,10000608,10000639,10000662,10000677";

	@AierServiceLog(module = "事件保存",message="{0}=>审核意见：{1}=>{2}")
	@Override
	public Map<String, Object> save(Map<String, Object> mData, EventEnum eEnum) throws Exception{
		// TODO Auto-generated method stub

		Boolean saveFlag = false;
		//是否是退回提交事件
		Boolean isReturnSubmit = false;
		Integer curNode = NodeEnum.REPORTING.getSeq();
		Integer nextNode = null;
		AeBasicInfo basicInfo = new AeBasicInfo();
		Long basicId = null; //基础表ID
		Long eventId = null; //业务表ID
		Integer operateType = 0; //操作类型
		String provinceName = "";
		Long provinceId = 0L;

		Object oBasicId = mData.get("basicId");
		// 判断是否保存不良事件表单信息 
		if(ObjectUtils.isEmpty(oBasicId)) {
			String uuid = EntityUtils.generatUUID(16);
			mData.put("basicId", uuid);
			basicId = Long.parseLong(uuid);
			saveFlag = true;
		}else {
			basicId = Long.parseLong(oBasicId.toString());
		}
				
		if(!ObjectUtils.isEmpty(mData.get("operateType"))) {
			operateType = Integer.parseInt(mData.get("operateType").toString());			
		}

		if(OperateEnum.SAVE.getType().equals(operateType)||OperateEnum.STASH.getType().equals(operateType)) {	
			basicInfo = EntityUtils.transMapToObject(mData, AeBasicInfo.class);

			if(OperateEnum.SAVE.getType() == operateType) {

				NodeEnum nodeEnum = NodeEnum.REPORTING;
			
				if(!ObjectUtils.isEmpty(mData.get("pageType"))&&!NodeEnum.STASH.getSeq().toString().equals(mData.get("pageType").toString())) {
					curNode = Integer.parseInt(mData.get("pageType").toString());
					nodeEnum = NodeUtil.findBySeq(curNode);						
				}
				
				NodeEnum nextNodeEnum = NodeUtil.NextNode(nodeEnum, eEnum.getNode());

				if(NodeEnum.RETRUN.getSeq() == basicInfo.getNode()){
					isReturnSubmit = true;
				}
				if(!ObjectUtils.isEmpty(nextNodeEnum)) {
					nextNode = nextNodeEnum.getSeq();
					basicInfo.setNextNode(nextNode);
				}	
				basicInfo.setNode(nodeEnum.getSeq());
				//判断是否填写赔偿减免金额，填写后需要记录其时间
				if(ObjectUtils.isEmpty(basicInfo.getAmountDate())&&(!ObjectUtils.isEmpty(basicInfo.getCompensationAmount())||!ObjectUtils.isEmpty(basicInfo.getDeductionAmount()))) {
					basicInfo.setAmountDate(new Date());
				}
			}else {

				//暂存保存其暂存状态
				curNode = NodeEnum.STASH.getSeq();
				basicInfo.setNode(curNode);
				basicInfo.setNextNode(NodeEnum.REPORTING.getSeq());
			}
			
			eventId = this.saveEntity(mData, eEnum.getName());
			if(ObjectUtils.isEmpty(basicInfo.getEventId())) {
				mData.put("eventId", eventId);
				basicInfo.setEventId(eventId);
			}

			Map province = this.getProvinceByHosp(basicInfo.getHospId());
			if(!ObjectUtils.isEmpty(province)) {
				provinceName = province.get("name").toString();
				provinceId = Long.parseLong(province.get("id").toString());
				if(province.get("ehrLevel")!=null){
					basicInfo.setEhrLevel(MapUtils.getString(province,"ehrLevel"));
				}
			}
			
			//更新基础信息
			doSyncInfo(oBasicId, basicInfo, provinceId);
			
			if(saveFlag) {
				EntityUtils.addOperatorInfo(basicInfo);
				basicInfo.setId(basicId);
				aeBasicInfoMapper.insert(basicInfo);				
			}else {
				basicInfo.setId(basicId);
				EntityUtils.addOperatorInfo(basicInfo);
				//当节点处于上报且做了保存操作时，需要记录当前时间为事件上报时间（create_date）
				if(OperateEnum.SAVE.getType().equals(operateType)&&NodeEnum.REPORTING.getSeq().equals(curNode)&&!isReturnSubmit) {
					basicInfo.setCreateDate(new Date());
				}
				aeBasicInfoMapper.updateById(basicInfo);
			}
			//保存Tags
			this.saveTags(mData.get("tags"), basicId);

			//判断当前节点是否为提交状态，提交状态事件推送消息
			try {
				List<Map<String, Object>> users = Lists.newArrayList();
				String eventCode = basicInfo.getEventCode();
				String eventTypeName = EventEnum.findEnumByCode(eventCode).getEnumDesc();
				AuthorizeCondition authorizeCondition = new AuthorizeCondition();
				authorizeCondition.setInstitution(basicInfo.getInstId());
				authorizeCondition.setRows(1000);
				String msg = "";

				String eventLevel = basicInfo.getEventLevel();
				String levelMsg = "";
				if(Constants.EventLevelI.equals(eventLevel)||Constants.EventLevelII.equals(eventLevel)) {

					if(Constants.EventLevelI.equals(eventLevel)) {
						levelMsg = Constants.EventLevelIMsg;
					}else {
						levelMsg = Constants.EventLevelIIMsg;
					}
					String eventDateStr = DateUtils.format(basicInfo.getEventDate(), "yyyy年MM月dd日");
					levelMsg = String.format(levelMsg, provinceName, basicInfo.getHospName(), basicInfo.getEventName(), eventDateStr);
				}
				
				
				if(NodeEnum.REPORTING.getSeq().equals(curNode)) {
					if(eventCode.startsWith(EventTypeEnum.MEDICAL.getType().toString())) {
						authorizeCondition.setRoleId(medicalRoleId);		
					}else {
						authorizeCondition.setRoleId(careRoleId);
					}
					users = authorizeService.getListByRoleAuthorize(authorizeCondition).getRows();
					// 事件分类一级=【角塑不良反应】的推给护理
					if(SIRS.equals(basicInfo.getGradeOneCode())){
						if(!authorizeCondition.getRoleId().equals(careRoleId)){
							authorizeCondition.setRoleId(careRoleId);
							users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
						}
					}

					//判断是否是首次上报，非首次上报需要推送给省区角色
					if(basicInfo.getReportTimes() > 1) {
						msg = Constants.SecondaryMsg;
						msg = String.format(msg, basicInfo.getHospName(), basicInfo.getEventName());
					    //增加省区人员
						authorizeCondition.setRoleId(ceoRoleId);
						users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
						authorizeCondition.setRoleId(deanRoleId);
						users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
					}else {
						msg = Constants.SubmitMsg;
				        msg = String.format(msg, UserContext.getUserName(), eventTypeName);
						// 给科室主任发送消息
						StaffCondition c = new StaffCondition();
						c.setDistinct(true);
						c.setInstId(UserContext.getInstId());
						c.setDeptId(UserContext.getDeptId());
						c.setJobcodes(Arrays.asList(stdJobcodes.split(",")));
						List<Map> staffOutList = staffService.getHospStaff(c);
						if(CollectionUtils.isNotEmpty(staffOutList) && staffOutList.size()>0){
							AeBasicInfo abi = aeBasicInfoMapper.selectById(basicId);
							staffOutList.stream().forEach(sol -> {
								Long deptLeaderId = MapUtils.getLong(sol,"id");
								List<String> roles = roleService.selectRolesByStaff(deptLeaderId, UserContext.getInstId());
								if(roles.contains(roleName)){
									// 如果角色列表中包含"科主任管理"角色就发送消息
									CpMesg cpMesg = new CpMesg();
									cpMesg.setUser(MapUtils.getString(sol,"code"));
									cpMesg.setContent(String.format(Constants.SubmitDeptDirectorMsg, abi.getEhrAiStandDeptIdDescr(),UserContext.getUserName(), eventTypeName));
									AwxService.sendCpMesg(cpMesg);
								}
							});
						}
					}
									
				}else if(NodeEnum.HOSPITAL_REVIEWS.getSeq().equals(curNode)) {
					authorizeCondition.setRoleId(ceoRoleId);
					users = authorizeService.getListByRoleAuthorize(authorizeCondition).getRows();
			
					authorizeCondition.setRoleId(deanRoleId);
					users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
					//增加医院经管
					authorizeCondition.setRoleId(bmRoleId);
					users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
					
					
					msg = Constants.HospAuditMsg;
					Staff createor = staffService.getById(basicInfo.getCreator());
			        msg = String.format(msg, createor.getName());
			        
			        //对于一级、二级事件给省区人员推送消息
			        List<Map<String, Object>> provinceUsers = Lists.newArrayList();
			        if(!ObjectUtils.isEmpty(levelMsg)) {
			        	List<Long> idList = Arrays.stream(provinceRoleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
			        	for(Long curId: idList) {
			        		authorizeCondition.setInstitution(provinceId);
							authorizeCondition.setRoleId(curId);
							provinceUsers.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
			        	}
			        	for(Map<String, Object> curUser: provinceUsers) {
			        		CpMesg cpMesg = new CpMesg();
						    cpMesg.setUser(curUser.get("code").toString());
						    cpMesg.setContent(levelMsg);
						    AwxService.sendCpMesg(cpMesg);
			        	}
			        }
					//发生纠纷事件调用HIS接口
					if(basicInfo.getDisputeSign() == 1){
						PatientSpecialWarn patientSpecialWarn = new PatientSpecialWarn();
						patientSpecialWarn.setSpecialInfoType(4);
						patientSpecialWarn.setSpecialInfoTypeName("涉及纠纷");
						patientSpecialWarn.setSpecialInfo(1);
						patientSpecialWarn.setSpecialInfoName("存在医疗纠纷行为");
						patientSpecialWarn.setRangeSign(1); //适应范围为集团
						patientSpecialWarn.setHospId(basicInfo.getHospId());
						patientSpecialWarn.setModifer(UserContext.getUserId());
						patientSpecialWarn.setModifyDate(basicInfo.getCreateDate());
						patientSpecialWarn.setPatientId(basicInfo.getPatientId());
						patientSpecialWarn.setPatientName(basicInfo.getPatientName());
						medicalService.save(patientSpecialWarn);
					}
			        
				}else if(NodeEnum.PROVINCE_REVIEWS.getSeq().equals(curNode)) {
					
					// 省区审核
					if(Constants.EventLevelI.equals(eventLevel)) {
						authorizeCondition.setInstitution(null);
						authorizeCondition.setRoleId(groupRoleId);
						users = authorizeService.getListByRoleAuthorize(authorizeCondition).getRows();
					}else if(Constants.EventLevelII.equals(eventLevel)) {
						// II级不良事件，且涉及科室包含护的事件
						String tags = mData.get("tags").toString();
						if(tags.contains(EventTypeEnum.CARE.getType().toString())) {
							for(String curCode: Constants.EventLevelIIUser) {
								Map<String, Object> mUser = Maps.newHashMap();
								mUser.put("code", curCode);
								users.add(mUser);
							}
						}
					}
					msg = levelMsg;
				}
				
				for(Map<String, Object> curUser: users) {
					CpMesg cpMesg = new CpMesg();
			        cpMesg.setUser(curUser.get("code").toString());
			        cpMesg.setContent(msg);
			        AwxService.sendCpMesg(cpMesg);
				}
				//省区审核通过给省区领导推送消息
				if(Constants.EventLevelI.equals(eventLevel)||Constants.EventLevelII.equals(eventLevel)) {
					ProvinceRoleCondition prCondition = new ProvinceRoleCondition();
					prCondition.setProvinceId(provinceId);
					prCondition.setRoleId(provinceDirectorRoleId);
					List<ProvinceRoleConfig> prUsers = provinceRoleService.queryListPage(prCondition);
					msg = Constants.ProvinceDirectorMsg;
					msg = String.format(msg, provinceName, eventLevel, basicInfo.getEventName());
					CpMesg cpMesg = new CpMesg();
					cpMesg.setContent(msg);
					for(ProvinceRoleConfig curUser: prUsers) {
				        cpMesg.setUser(curUser.getStaffCode());
				        AwxService.sendCpMesg(cpMesg);
					}
				}
				
			}catch(Exception ex) {
				logger.info("推送不良事件{}消息体系失败!{}", basicInfo.getEventName(), ex.getMessage());
			}
			
			//判断是否存在电子病例，存在的话保存为附件
			if(!ObjectUtils.isEmpty(mData.get("emrUrl"))) {
				//已存在的电子病例直接删除
				this.delAttachmentByTag(basicId, "emr");
				//保存新的电子病例
				String emrUrl = mData.get("emrUrl").toString();
				AeAttachment attachment = new AeAttachment();
				attachment.setBasicId(basicId);
				attachment.setTag("emr");
				attachment.setUrl(emrUrl);
				attachment.setUsingSign(1);
				EntityUtils.addOperatorInfo(attachment);
				aeAttachmentMapper.insert(attachment);
			}
			
			mData.put("basicId", basicId);			
			
		}else if(OperateEnum.CANCEL.getType().equals(operateType)) {
			basicInfo = aeBasicInfoMapper.selectById(basicId);
			if(basicInfo.getIsArchived().equals(1)){
				throw new BizException("已归档事件不能修改");
			}
			basicInfo.setStatus(2);
			EntityUtils.addOperatorInfo(basicInfo);
			aeBasicInfoMapper.updateById(basicInfo);
		}else if(OperateEnum.RETURN.getType().equals(operateType)) {

			curNode = Integer.parseInt(mData.get("pageType").toString());
			basicInfo = aeBasicInfoMapper.selectById(basicId);
			EntityUtils.addOperatorInfo(basicInfo);
			basicInfo.setNode(NodeEnum.RETRUN.getSeq());
			basicInfo.setNextNode(NodeEnum.REPORTING.getSeq());
			aeBasicInfoMapper.updateById(basicInfo);
			//发起过oa的不能退回
			if(!ObjectUtils.isEmpty(basicInfo.getOaRequestId())){
				throw new BizException("已经发起oa不能退回!!!");
			}
			try {
				String msg = Constants.RebackMsg;
		        msg = String.format(msg, basicInfo.getEventName());
		        CpMesg cpMesg = new CpMesg();
		        Staff createor = staffService.getById(basicInfo.getCreator());
		        cpMesg.setUser(createor.getCode());
		        cpMesg.setContent(msg);
		        AwxService.sendCpMesg(cpMesg);
		        //推送消息给本院医务管理人员
		        Integer ahisCode = Integer.valueOf(basicInfo.getHospId().toString());
		        List<Map<String, Object>> users = this.getHospMedicalUsers(ahisCode);
		        String hMsg = Constants.HospRebackMsg;
		        
		        for(Map<String, Object> curUser: users) {
					CpMesg mCpMesg = new CpMesg();
					mCpMesg.setUser(curUser.get("code").toString());
					hMsg = String.format(hMsg, createor.getName(), basicInfo.getEventName());
					mCpMesg.setContent(hMsg);
			        AwxService.sendCpMesg(mCpMesg);
				}				
		        
			}catch(Exception ex) {
				logger.info("推送回退不良事件{}消息失败!{}", basicInfo.getEventName(), ex.getMessage());
			}
			
		}else if(OperateEnum.REVIEW.getType().equals(operateType)){
			//对于点评事件只针对首次上报进行点评记录
			if(!ObjectUtils.isEmpty(mData.get("prevId"))) {
				basicId = Long.parseLong(mData.get("prevId").toString());
			}
			basicInfo.setHospId(Long.parseLong(mData.get("hospId").toString()));
			curNode = Integer.parseInt(mData.get("pageType").toString());
		}else {
			basicInfo = EntityUtils.transMapToObject(mData, AeBasicInfo.class);
			curNode = Integer.parseInt(mData.get("pageType").toString());
		}
		//记录审计日志
		try {
			LogMessage message=LogMessage.newWrite().setParams("【"+ OperateEnum.typeOf(operateType).getEnumDesc()+"】"+eEnum.getEnumDesc()).setParams(mData.get("popOpinion")).setParams(JSONUtil.toJsonStr(mData));
			LogUtils.putArgs(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AeOperationRecord record = EntityUtils.transMapToObject(mData, AeOperationRecord.class);
		if(mData.containsKey("popId") && !ObjectUtils.isEmpty(mData.get("popId"))) {
			Long popId = Long.parseLong(mData.get("popId").toString());
			record = aeOperationRecordMapper.selectById(popId);
			if(mData.containsKey("popOpinion")) record.setOpinion(mData.get("popOpinion").toString());
			aeOperationRecordMapper.updateById(record);
		}else {
			record.setId(null);
			record.setBasicId(basicId);
			record.setHospId(basicInfo.getHospId());
			if("1".equals(mData.get("isEhrDeptReview"))){
				record.setNode(NodeEnum.DEPT_REVIEWS.getSeq());
			}

			record.setType(operateType);
			record.setNode(curNode);
			record = EntityUtils.addOperatorInfo(record);
			if(mData.containsKey("popOpinion")) record.setOpinion(mData.get("popOpinion").toString());
			aeOperationRecordMapper.insert(record);
			//省区/集团/专家提交点评后推送消息给上报人及医务管理
			if(OperateEnum.REVIEW.getType().equals(operateType)) {
				Integer ahisCode = Integer.valueOf(basicInfo.getHospId().toString());
				Long creatorId = Long.parseLong(mData.get("creator").toString());
				this.sendMsgForReview( mData.get("eventName"), ahisCode, creatorId, curNode);
			}
		}
		
		//同步节点状态
		if(!OperateEnum.REVIEW.getType().equals(operateType)&&!OperateEnum.STASH.getType().equals(operateType)) {
			this.doSyncNode(basicId, basicInfo.getPrevId(), curNode, nextNode, operateType);
		}
		
		return mData;

	}
	
	
	/**
	 * 点评后推送消息给相关人员
	 * @param eventName
	 * @param ahisCode
	 * @param creatorId
	 * @param curNode
	 */
	private void sendMsgForReview(Object eventName, Integer ahisCode, Long creatorId, Integer curNode)  {
		try {
			NodeEnum nodeEnum = NodeUtil.findBySeq(curNode);
			String reviewMsg = Constants.ReviewMsg;
			reviewMsg = String.format(reviewMsg, nodeEnum.getEnumDesc().substring(0, 2), eventName);
			List<Map<String, Object>> users = this.getHospMedicalUsers(ahisCode);
			Staff createor = staffService.getById(creatorId);
		    
			List<String> userList = Lists.newArrayList();
			for(Map<String, Object> curUser: users) {
				userList.add(curUser.get("code").toString());
			}
			if(!userList.contains(createor.getCode())) {
				userList.add(createor.getCode());
			}
			CpMesg mCpMesg = new CpMesg();
			mCpMesg.setContent(reviewMsg);
			mCpMesg.setUsers(userList);
			AwxService.sendCpMesg(mCpMesg);
		}catch(Exception ex) {
			logger.info("推送点评消息提醒失败!{}", ex.getMessage());
		}
		
	}
	
	
	/**
	 * 根据医院ID获取省区信息
	 * @param hospId
	 * @return
	 */
	private Map getProvinceByHosp(Long hospId) {
		Map province = Maps.newHashMap();
		InstCondition cond_1 = new InstCondition();
        cond_1.setInstId(hospId);
        cond_1.setInstType(InstEnum.HOSP.getEnumCode());
        List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
        if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
            Map resultMap = hosps.get(0);
            /** 查询一级省区 */
            InstCondition cond_2 = new InstCondition();
            cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
            List<Map> list = institutionService.getProvince(cond_2);
            List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
            province = l.get(0);
			if(resultMap.get("EHR_LEVEL")!=null){
				province.put("ehrLevel",resultMap.get("EHR_LEVEL"));
			}
        }
        
        return province;
	}
	
	/**
	 * 根据医院编号获取本院的医务人员
	 * @param ahisCode
	 * @return
	 */
	private List<Map<String, Object>> getHospMedicalUsers(Integer ahisCode) {
        Institution institution = institutionService.getByAhisHosp(ahisCode);
        AuthorizeCondition authorizeCondition = new AuthorizeCondition();
        authorizeCondition.setInstitution(institution.getId());
		authorizeCondition.setRows(1000);
        authorizeCondition.setRoleId(medicalRoleId);
        return authorizeService.getListByRoleAuthorize(authorizeCondition).getRows();		
	}
	
	private void saveTags(Object tags, Long basicId) {
        if(ObjectUtils.isEmpty(tags)) return;
		String[] tagArray = tags.toString().split(",");
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.eq("event_id", basicId);
		ew.eq("using_sign", 1);
		List<AeEventTags> eventTagList = aeEventTagsMapper.selectList(ew);
        
        for(String curTag: tagArray) {
        	Boolean hasExist = false;
        	for(AeEventTags curEventTag: eventTagList) {
        		if(curTag.equals(curEventTag.getTagCode())) {
        			eventTagList.remove(curEventTag);
        			hasExist = true;
        			break;
        		}
        	}

        	if(!hasExist) {
        		AeEventTags eventTag = new AeEventTags();
        		eventTag.setEventId(basicId);
        		eventTag.setTagCode(curTag);
        		EntityUtils.addOperatorInfo(eventTag);
        		aeEventTagsMapper.insert(eventTag);
        	}
        }
        
        //删除无效的Tags
        for(AeEventTags curEventTag: eventTagList) {
        	curEventTag.setUsingSign(0);
        	aeEventTagsMapper.updateById(curEventTag);
        }
		
	}
        
	
	private void delAttachmentByTag(Long basicId, String tag) {
		List<AeAttachment> attachments = aeAttachmentMapper.findByFileTag(basicId, tag);
		for(AeAttachment attachment: attachments) {
			attachment.setUsingSign(0);
			aeAttachmentMapper.updateById(attachment);
		}
	}
	
	/**
	 * 同步信息
	 * @param oBasicId
	 * @param basicInfo
	 */
	void doSyncInfo(Object oBasicId, AeBasicInfo basicInfo, Long provinceId){
		try {
			Long masterId = basicInfo.getMasterId();
			Long prevId = basicInfo.getPrevId();
			Date curDate = new Date();

			//同步基础信息
			String classPath = "com.aier.cloud.api.amcs.adverse.domain.";
			String[] classNames = {"AeOverview", "AeDealResult"};
			for (String className : classNames) {
	            Class<?> clazz = Class.forName(classPath.concat(className));
	            Object oldObj = clazz.newInstance();
	            Object newObj = clazz.newInstance();
	            Long lOBasicId = null;
				AeBasicInfo oBasicInfo = new AeBasicInfo();
				if(ObjectUtils.isEmpty(oBasicId)) {
					if(!ObjectUtils.isEmpty(prevId)) {
						//进展上报
						lOBasicId = prevId;
						oBasicInfo = aeBasicInfoMapper.selectById(lOBasicId);
					}else {
						return;
					}
					if("AeDealResult".equals(className)) {

						if (!ObjectUtils.isEmpty(basicInfo.getCompensationAmount()) && basicInfo.getCompensationAmount().compareTo(BigDecimal.ZERO) > 0) {
							basicInfo.setAmountDate(curDate);
							//推送消息给省区不良事件管理
							ProvinceRoleCondition prCondition = new ProvinceRoleCondition();
							prCondition.setProvinceId(provinceId);
							prCondition.setRoleId(provinceDirectorRoleId);
							List<ProvinceRoleConfig> prUsers = provinceRoleService.queryListPage(prCondition);
							String msg = Constants.AmountChangeMsg;
							msg = String.format(msg, basicInfo.getHospName(), basicInfo.getEventName(), basicInfo.getCompensationAmount().toString());
							CpMesg cpMesg = new CpMesg();
							cpMesg.setContent(msg);
							for(ProvinceRoleConfig curUser: prUsers) {
								cpMesg.setUser(curUser.getStaffCode());
								AwxService.sendCpMesg(cpMesg);
							}
						}
					}
				}

				if(ObjectUtils.isEmpty(oBasicInfo)) return;
				BeanUtils.copyProperties(oBasicInfo, oldObj);	
				BeanUtils.copyProperties(basicInfo, newObj);
				if(!oldObj.equals(newObj)) {
						AeInfoCondition cond = new AeInfoCondition();			
						cond.setPrevId(prevId);
						cond.setMasterId(masterId);
						cond.setOperateType(OperateEnum.QUERY_MULTI.getType());
						cond.setShowAllNode(true);
						
						List<AeBasicInfo> basicList = aeBasicInfoMapper.findEntityList(cond);
						for (AeBasicInfo curBasicInfo : basicList) {
							if (!ObjectUtils.isEmpty(oBasicId) && curBasicInfo.getId().equals(lOBasicId)) continue;

							if ("AeDealResult".equals(className)) {
								curBasicInfo.setFinishSign(basicInfo.getFinishSign());
								if (!ObjectUtils.isEmpty(basicInfo.getCompensationAmount()) && basicInfo.getCompensationAmount().compareTo(BigDecimal.ZERO) > 0) {
									//进展上报金额大于零时，则状态统一调整为医院审核通过
									curBasicInfo.setNode(NodeEnum.HOSPITAL_REVIEWS.getSeq());
									curBasicInfo.setNextNode(NodeEnum.PROVINCE_REVIEWS.getSeq());
									curBasicInfo.setAmountDate(curDate);
									basicInfo.setNode(NodeEnum.HOSPITAL_REVIEWS.getSeq());
									basicInfo.setNextNode(NodeEnum.PROVINCE_REVIEWS.getSeq());
								}
							} else {
								BeanUtils.copyProperties(newObj, curBasicInfo);
							}

							curBasicInfo.setModifyDate(curDate);
							aeBasicInfoMapper.updateById(curBasicInfo);
						}
				}
			}
		}catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新处理结果数据
	 * @param oBasicId
	 * @param basicInfo
	 */
	private void doSyncDealResult(Object oBasicId, AeBasicInfo basicInfo, Boolean isMerge) {
		try {
			Long masterId = basicInfo.getMasterId();
			Long prevId = basicInfo.getPrevId();

			AeDealResult oAeDR = new AeDealResult();
			AeDealResult nAeDR = new AeDealResult();
			Long lOBasicId = null;
			if(ObjectUtils.isEmpty(oBasicId)) {
				if(!ObjectUtils.isEmpty(prevId)) {
					//进展上报
					lOBasicId = prevId;
				}else {
					return;
				}
			}else {
				lOBasicId = Long.parseLong(oBasicId.toString());
			}

			if(ObjectUtils.isEmpty(masterId)) {
				masterId = lOBasicId;
			}

			AeBasicInfo oBasicInfo = aeBasicInfoMapper.selectById(lOBasicId);
			if(ObjectUtils.isEmpty(oBasicInfo)) return;
			BeanUtils.copyProperties(oBasicInfo, oAeDR);
			BeanUtils.copyProperties(basicInfo, nAeDR);
			//判断金额是否发生改变，发生改变的话需要记录金额修改时间
			Boolean hasChageAmount = false;
			if(oAeDR.getCompensationAmount() != nAeDR.getCompensationAmount()||oAeDR.getDeductionAmount() != nAeDR.getDeductionAmount()) {
				hasChageAmount = true;
			}
			Date curDate = new Date();
			if(!oAeDR.equals(nAeDR)) {
				AeInfoCondition cond = new AeInfoCondition();
				cond.setPrevId(prevId);
				cond.setMasterId(masterId);
				cond.setOperateType(OperateEnum.QUERY_MULTI.getType());
				cond.setShowAllNode(true);

				List<AeBasicInfo> basicList = aeBasicInfoMapper.findEntityList(cond);

				for(AeBasicInfo curBasicInfo: basicList) {
					if(!ObjectUtils.isEmpty(oBasicId)&&curBasicInfo.getId().equals(lOBasicId)) continue;
					curBasicInfo.setFinishSign(nAeDR.getFinishSign());
					curBasicInfo.setDisputeSign(nAeDR.getDisputeSign());
					if(hasChageAmount) {
						curBasicInfo.setAmountDate(curDate);
					}
					aeBasicInfoMapper.updateById(curBasicInfo);
				}
			}
		}catch(Exception ex) {
			logger.info("同步不良事件{}处理结果失败!{}", basicInfo.getEventName(), ex.getMessage());
		}
	}

	/**
	 * 二次上报节点状态更新
	 * @param basicId
	 * @param prevId
	 * @param curNode
	 * @return
	 */
	private void doSyncNode(Long basicId, Long prevId, Integer curNode, Integer nextNode, Integer operateType){
		
		if(ObjectUtils.isEmpty(prevId)&&NodeEnum.REPORTING.getSeq().equals(curNode)) return;
			
		AeInfoCondition cond = new AeInfoCondition();
		cond.setOperateType(OperateEnum.QUERY_INCREASE.getType());
		if(ObjectUtils.isEmpty(prevId)) {
			prevId = basicId;
		}
		cond.setPrevId(prevId);
		cond.setShowAllNode(true);
		List<AeBasicInfo> basicList = aeBasicInfoMapper.findEntityList(cond);
		
		//判断事件操作是否时回退或者取消
		if(OperateEnum.RETURN.getType().equals(operateType)) {
			for(AeBasicInfo basicInfo: basicList) {
				if(basicInfo.getId().equals(basicId)) continue;
				basicInfo.setNode(NodeEnum.RETRUN.getSeq());
				basicInfo.setNextNode(NodeEnum.REPORTING.getSeq());
				aeBasicInfoMapper.updateById(basicInfo);
			}
		}else if(OperateEnum.CANCEL.getType().equals(operateType)) {
			for(AeBasicInfo basicInfo: basicList) {
				if(basicInfo.getId().equals(basicId)) continue;
				basicInfo.setStatus(2);
				aeBasicInfoMapper.updateById(basicInfo);
			}
		}else {
			//获取最大的节点值
			Integer maxNode = curNode;
			Integer maxNextNode = nextNode;
			for(AeBasicInfo basicInfo: basicList) {
				if(basicInfo.getNode() > maxNode) {
					maxNode = basicInfo.getNode();
					maxNextNode = basicInfo.getNextNode();
				}
			}
			//判断当前节点值是否小于最大节点值，小于的话则更新节点状态
			for(AeBasicInfo basicInfo: basicList) {
				if(basicInfo.getNode() < maxNode) {
					basicInfo.setNode(maxNode);
					basicInfo.setNextNode(maxNextNode);
					aeBasicInfoMapper.updateById(basicInfo);
				}
			}
		}
	}
	
	
	private Long saveEntity(Map<String, Object> mData, String name) throws Exception{ 
		if(!ObjectUtils.isEmpty(mData.get("eventId"))) {
			mData.put("id", mData.get("eventId"));
		}
		
		Class<?> eClass = Class.forName(ENTITY_PREFIX.concat(name));
		Object eObject = eClass.newInstance();
		eObject =  EntityUtils.transMapToObject(mData, eClass);
		eObject = EntityUtils.addOperatorInfo(eObject);
		mData.remove("id");
        ApplicationContext applicationContext = SpringBootBeanUtil.getApplicationContext();
        Class<?> eService = Class.forName(SERVICE_PREFIX.concat(name).concat(SERVICE_SUFFIX));
        Class<?>  eSuperService = eService.getSuperclass();

        Method method = eSuperService.getDeclaredMethod("insertOrUpdate", Object.class);
        
        method.invoke(applicationContext.getBean(eService), eObject);



        Class<?> superClass = eClass.getSuperclass();
        Method idMethod = superClass.getDeclaredMethod("getId");
        Long id = (Long)idMethod.invoke(eObject);
        
        //住院、院感、门诊手术需要配置手术类型为空
        try {
        	Field field = eClass.getDeclaredField("isIol");
        	field.setAccessible(true);
        	Object isIol = field.get(eObject);
        	if(isIol != null &&  "0".equals(isIol.toString())) {
        		Method setMethod = eService.getDeclaredMethod("setIolTypeNull", Long.class);
                setMethod.invoke(applicationContext.getBean(eService), id);
        	}
        } catch (NoSuchFieldException e) {
        	Console.log("没有手术字段，无需处理");
        }
        
        return id;
	}
	

	@Override
	public Map<String, Object> findById(Long id, EventEnum eEnum) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mObject = Maps.newHashMap();
		if(!ObjectUtils.isEmpty(id)){
			AeBasicInfo basicInfo = aeBasicInfoMapper.selectById(id);
			Long eventId = basicInfo.getEventId();
		
			mObject = EntityUtils.transObjToMap(basicInfo);	
			mObject.put("basicId", String.valueOf(mObject.remove("id")));
			Object eventInfo = EntityUtils.findEntityById(eventId, eEnum.getName());
			Map<String, Object> mEventInfo = EntityUtils.transObjToMap(eventInfo);
			mObject.putAll(mEventInfo);	
		}
		
		return mObject;
	}

	@Override
	public boolean merge(Long masterId, List<Long> mergeIds) throws Exception {
		// TODO Auto-generated method stub
		List<AeBasicInfo> basics = aeBasicInfoMapper.selectBatchIds(mergeIds);
		AeBasicInfo masterBasic= aeBasicInfoMapper.selectById(masterId);
		//获取所有事件的赔偿总金额
		AeInfoCondition cond = new AeInfoCondition();
		List<Long> basicIds = Lists.newArrayList(mergeIds);
		basicIds.add(masterId);
		cond.setBasicIds(basicIds);
		//主事件完结状态
		Integer mFinishSign = masterBasic.getFinishSign();
		//子事件完结状态
		for(AeBasicInfo curBasic : basics) {
			if(curBasic.getFinishSign() == 1){
				if(mFinishSign == 0){
					masterBasic.setFinishSign(1);
				}
			}else{
				if(mFinishSign == 1){
					curBasic.setFinishSign(1);
				}
			}
			curBasic.setMasterId(masterId);
			curBasic.setNode(masterBasic.getNode());
			curBasic.setNextNode(masterBasic.getNextNode());
			aeBasicInfoMapper.updateById(curBasic);
		}
		masterBasic.setMasterId(masterId);
		aeBasicInfoMapper.updateById(masterBasic);
		// 生成合并记录
		this.doRecord(masterId, OperateEnum.MERGE.getType(), masterBasic.getNode(), null);
		return true;
	}



	@Override
	public boolean reback(Long basicId,  EventEnum eEnum, Integer node) throws Exception {
		// TODO Auto-generated method stub
		AeBasicInfo basic = aeBasicInfoMapper.selectById(basicId);
		basic.setNode(node);
		NodeEnum curNode = NodeUtil.findBySeq(node);
		Integer nextNode = NodeUtil.NextNode(curNode, eEnum.getNode()).getSeq();
		basic.setNextNode(nextNode);
		aeBasicInfoMapper.updateById(basic);
		
		return true;
	}

	private void doRecord(Long basicId, Integer operateType, Integer node, String opinion) {
		AeOperationRecord record = new AeOperationRecord();
		record.setBasicId(basicId);
		record.setModifer(UserContext.getUserId());
		record.setHospId(UserContext.getTenantId());
		record.setModifyDate(new Date());
		record.setNode(node);
		record.setType(operateType);
		if(opinion != null)	record.setOpinion(opinion);
		aeOperationRecordMapper.insert(record);
	}
	
	

}
