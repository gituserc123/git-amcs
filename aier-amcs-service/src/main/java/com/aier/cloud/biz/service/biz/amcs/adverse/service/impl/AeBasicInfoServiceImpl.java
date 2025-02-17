/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;


import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.*;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOperationRecordService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeOverview;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.api.amcs.adverse.enums.PageEnum;
import com.aier.cloud.api.amcs.utils.EventUtil;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.log.annotion.AierServiceLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpertEvent;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.biz.service.biz.sys.feign.InstitutionService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

import cn.hutool.json.JSONUtil;

/**
 * AeBasicInfo
 * 
 * @author 爱尔眼科
 * @since 2022-08-15 17:53:42
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AeBasicInfoServiceImpl extends ServiceImpl<AeBasicInfoMapper, AeBasicInfo> implements AeBasicInfoService {
	private static final Logger logger = LoggerFactory.getLogger(AeBasicInfoServiceImpl.class);
	@Autowired
	private QueryMapper queryMapper;

	@Autowired
	private AeEventTagsMapper aeEventTagsMapper;

	@Autowired
	private AeExpertEventMapper aeExpertEventMapper;

	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private AeOperationRecordMapper aeOperationRecordMapper;

	@Autowired
	private AeOperationRecordService aeOperationRecordService;

	@Autowired
	private AeBasicInfoGroupStatMapper aeBasicInfoGroupStatMapper;

	@Override
	public Integer updateOaRequestById(Long id, Integer requestId) {
		return this.baseMapper.updateOaRequestById(id, requestId);
	}

	@Override
	public List<Map<String, Object>> findListByCond(Page<Map<String, Object>> page, AeInfoCondition cond) {
		List<Map<String, Object>> basicList = Lists.newArrayList();
		NodeEnum nodeEnum = cond.getNodeEnum();
		if (!ObjectUtils.isEmpty(nodeEnum)) {
			// 判断是否有此事件的权限
			cond.setNodeSeq(nodeEnum.getSeq());
			basicList = this.baseMapper.findListByCond(page, cond);
			for (Map<String, Object> curBasic : basicList) {
				String eventCode = curBasic.get("eventCode").toString();
				EventEnum eventEnum = EventUtil.findByValue(eventCode);
				Integer nodeValue = eventEnum.getNode();
				if (!NodeUtil.hasNode(nodeEnum, nodeValue)) {
					basicList.remove(curBasic);
				}

			}
		} else {
			basicList = this.baseMapper.findListByCond(page, cond);
			for (Map<String, Object> curBasic : basicList) {
				// 集团查阅事件需要增加标签显示
				if (PageEnum.GROUP_REVIEWS.getValue().equals(cond.getPageType())
						|| PageEnum.PROVINCE_REVIEWS.getValue().equals(cond.getPageType())) {
					String tags = this.findTagsById(curBasic.get("id"));
					curBasic.put("tags", tags);
				}
			}
		}

		return basicList;
	}

	@Override
	public List<Map<String, Object>> findListForExpert(Page<Map<String, Object>> page, AeInfoCondition cond) {
		List<Map<String, Object>> basicList = Lists.newArrayList();
		if (cond.getProvince() != null && cond.getHospId() == null) {
			List<Long> hospList = this.listHospByParent(cond.getProvince());
			if(hospList.isEmpty()){
				return basicList;
			}else{
				cond.setHospList(hospList);
			}
		}

		basicList = this.baseMapper.findListByCond(page, cond);
		//获取basicList中的事件id,写入一个List队列中, 通过流的方式
		basicList.stream().forEach(basic -> {
			Long basicId = Long.valueOf(basic.get("id").toString());
			//通过basicId获取相关联的事件
			List<Long> relatedIds = this.getRelatedIds(basicId, null);
			EntityWrapper<AeExpertEvent> ew = new EntityWrapper<>();
			ew.in("event_id", relatedIds);
			if(Boolean.TRUE.equals(cond.getIsProvince())){
				ew.eq("is_province", 1);
			}
			List<AeExpertEvent> expertEventList = aeExpertEventMapper.selectList(ew);
			if(expertEventList.size() > 0){
				basic.put("expertStatus", 1);
			}
		});

		// 对于非省区的事件，需要增加省区字段
		if (!Boolean.TRUE.equals(cond.getIsProvince())) {
			addProvince(basicList);
		}
        // 增加点评状态
		List<Long> basicIds = basicList.stream().map(item -> Long.valueOf(item.get("id").toString())).collect(Collectors.toList());
		addExpertReview(basicList, basicIds);

		return basicList;
	}

	private List<Long> listHospByParent(Long parentId) {
		List<Long> hospList = Lists.newArrayList();
		if(ObjectUtils.isEmpty(parentId)) return hospList;
		JSONArray o=(JSONArray) institutionService.getInstByParent(parentId);
		if (o.size()==0){
			return new ArrayList<>();
		}
		o.forEach(jo->{
			if (((JSONObject)jo).get("instTypeName").equals("医院")){
				hospList.add(((JSONObject) jo).getLong("ahisHosp"));
			}else{
				hospList.addAll(listHospByParent(((JSONObject)jo).getLong("id")));
			}
		});
		return hospList;
	}

	private void addProvince(List<Map<String, Object>> basicList) {
		List<Map<String, Object>> hospList = institutionService.getAllHospital();
		Map<Long, String> hospMap = hospList.stream().collect(
				Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"), (existingValue, newValue) -> existingValue));
		//遍历basicList，根据hospId获取省区名称
		basicList.forEach(data -> {
			Long hospId = MapUtils.getLong(data, "hospId");
			if (hospId != null && hospMap.containsKey(hospId)) {
				data.put("province", hospMap.get(hospId));
			}
		});
	}

	private void addExpertReview(List<Map<String, Object>> basicList, List<Long> basicIds) {
		// 根据操作记录查找专家点评，专家点评节点为 NodeEnum.EXPERT_REVIEWS
		List<AeOperationRecord> expertReviewList = aeOperationRecordService.findReviewList(basicIds, NodeEnum.EXPERT_REVIEWS.getSeq());
		// 把所有获取的点评记录中的basicId放入到一个集合中，且不重复
		List<Long> reviewBasicIds = expertReviewList.stream().map(AeOperationRecord::getBasicId).distinct().collect(Collectors.toList());

		basicList.forEach(data -> {
			Long basicId = MapUtils.getLong(data, "id");
			if (reviewBasicIds.contains(basicId)) {
				data.put("isExpertReview", 1);
			}else{
				data.put("isExpertReview", 0);
			}
		});
	}


	private String findTagsById(Object basicId) {
		// TODO Auto-generated method stub
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.eq("event_id", basicId);
		ew.eq("using_sign", 1);
		String tags = "";
		List<AeEventTags> tagList = aeEventTagsMapper.selectList(ew);
		if (tagList.size() == 0)
			return tags;

		for (AeEventTags curEventTags : tagList) {
			tags = tags.concat(",").concat(curEventTags.getTagCode());
		}

		if (!ObjectUtils.isEmpty(tags))
			tags = tags.substring(1);

		return tags;
	}

	@Override
	public Integer getLastReportTimes(Long prevId) {
		return this.baseMapper.getLastReportTimes(prevId);
	}

	@Override
	public List<Map<String, Object>> queryListByCond(Page<Map<String, Object>> page, AeInfoCondition cond) {
		List<Map<String, Object>> basicList = this.baseMapper.queryListByCond(page, cond);
		return basicList;
	}

	@Override
	public List<Map<String, Object>> findReturnList(Page<Map<String, Object>> page, AeInfoCondition cond) {
		return this.baseMapper.findReturnList(page, cond);
	}

	@Override
	public List<Map<String, Object>> findReviewList(Page<Map<String, Object>> page, AeInfoCondition cond) {
		if (NodeEnum.GROUP_REVIEWS.getSeq().equals(cond.getPageType())) {
			// 集团点评查看省区审核完的数据
			cond.setNode(NodeEnum.PROVINCE_REVIEWS.getSeq());
		}
		if (!ObjectUtils.isEmpty(cond.getIsExpert()) && cond.getIsExpert()) {
			// 专家点评只看分配到本人的数据
			cond.setNode(null);
			cond.setExpertId(UserContext.getUserId());
		}
		List<Map<String, Object>> basicList = this.baseMapper.findReviewList(page, cond);
		for (Map<String, Object> curBasic : basicList) {
			// 集团查阅事件需要增加标签显示
			if (PageEnum.GROUP_REVIEWS.getValue().equals(cond.getPageType())
					|| PageEnum.PROVINCE_REVIEWS.getValue().equals(cond.getPageType())) {
				String tags = this.findTagsById(curBasic.get("id"));
				curBasic.put("tags", tags);
			}
			// 增加点评状态
			List<Long> basicIds = this.getRelatedIds(curBasic.get("id"), null);

			if (ObjectUtils.isEmpty(cond.getPReviewStatus())) {
				curBasic.put("pReviewStatus", this.getReviewStatus(NodeEnum.PROVINCE_REVIEWS.getSeq(), basicIds));
			} else {
				if (cond.getPReviewStatus() == 0) {
					curBasic.put("pReviewStatus", 0);
				} else if (cond.getPReviewStatus() == 1) {
					curBasic.put("pReviewStatus", 1);
				}
			}

			if (ObjectUtils.isEmpty(cond.getGReviewStatus())) {
				curBasic.put("gReviewStatus", this.getReviewStatus(NodeEnum.GROUP_REVIEWS.getSeq(), basicIds));
			} else {
				if (cond.getGReviewStatus() == 0) {
					curBasic.put("gReviewStatus", 0);
				} else if (cond.getGReviewStatus() == 1) {
					curBasic.put("gReviewStatus", 1);
				}
			}
		}
		return basicList;
	}

	private List<Long> getRelatedIds(Object basicId, Object prevId) {
		EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
		ew.eq("id", basicId).or().eq("id", prevId).or().eq("prev_id", prevId).or().eq("prev_id", basicId);
		List<AeBasicInfo> basicList = this.baseMapper.selectList(ew);
		List<Long> ids = Lists.newArrayList();
		for (AeBasicInfo basicInfo : basicList) {
			ids.add(basicInfo.getId());
		}
		return ids;
	}

	// 更新赔偿金额和减免金额
	private void addSumAmount(Map<String, Object> basicInfo) {
		Object basicId = basicInfo.get("id");
		Object masterId = basicInfo.get("masterId");
		EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
		ew.eq("id", basicId).or().eq("prev_id", basicId).or().eq("master_id", masterId).or().eq("prev_id", masterId);
		List<AeBasicInfo> basicList = this.baseMapper.selectList(ew);
		BigDecimal compensationAmount = BigDecimal.ZERO;
		BigDecimal deductionAmount = BigDecimal.ZERO;
		Date amountDate = null;
		for (AeBasicInfo curBasicInfo : basicList) {
			BigDecimal curCompensationAmount = curBasicInfo.getCompensationAmount();
			BigDecimal curDeductionAmount = curBasicInfo.getDeductionAmount();
			Date curAmountDate = curBasicInfo.getAmountDate();
			if (curAmountDate != null) {
				if (amountDate == null || curAmountDate.after(amountDate)) {
					amountDate = curAmountDate;
				}
			}

			if (curCompensationAmount != null) {
				compensationAmount = compensationAmount.add(curCompensationAmount);
			}
			if (curDeductionAmount != null) {
				deductionAmount = deductionAmount.add(curDeductionAmount);
			}
		}
		if (compensationAmount.compareTo(BigDecimal.ZERO) > 0) {
			basicInfo.put("compensationAmount", compensationAmount);
		}
		if (deductionAmount.compareTo(BigDecimal.ZERO) > 0) {
			basicInfo.put("deductionAmount", deductionAmount);
		}
		if (amountDate != null) {
			basicInfo.put("amountDate", amountDate);
		}
	}

	/**
	 * 增加点评状态
	 *
	 * @param node
	 * @return
	 */
	private Integer getReviewStatus(Object node, List<Long> basicIds) {
		EntityWrapper<AeOperationRecord> ew = new EntityWrapper<>();
		ew.in("basic_id", basicIds);
		ew.eq("type", OperateEnum.REVIEW.getType());
		ew.eq("node", node);
		return aeOperationRecordMapper.selectCount(ew);
	}

	@Override
	public Integer getCountByCond(AeInfoCondition cond) {
		return this.baseMapper.getCountByCond(cond);
	}

	@Override
	public List<Map<String, Object>> queryByQueryMapper(String table, String queryFeild, String cond) {
		List<Map<String, Object>> qm = queryMapper.queryByCond(table, queryFeild, cond);
		return qm;
	}

	@Override
	public Map<String, Object> countIndex(AeInfoCondition cond) {
		Map<String, Object> retMap = this.baseMapper.countIndex(cond);
		AeInfoCondition condReview = new AeInfoCondition();
		condReview.setPageType(4);
		condReview.setNode(3);
		condReview.setReportTimes(1);
		condReview.setIsPrimary(Boolean.TRUE);
		condReview.setShowArchived(false);
		condReview.setNodeEnum(NodeEnum.GROUP_REVIEWS);
		condReview.setOperator(UserContext.getUserId());
		condReview.setShowAllNode(false);
		condReview.setOperateType(cond.getOperateType());
		condReview.setStatus(0);
		condReview.setReportDateBegin(cond.getReportDateBegin());
		condReview.setReportDateEnd(cond.getReportDateEnd());
		condReview.setEventDateBegin(cond.getEventDateBegin());
		condReview.setEventDateEnd(cond.getEventDateEnd());
		condReview.setHospList(cond.getHospList());
		condReview.setTags(cond.getTags());
		condReview.setGradeOneCodeStr(cond.getGradeOneCodeStr());
		condReview.setIsGroupReview(1);
		condReview.setEhrLevel(cond.getEhrLevel());
		condReview.setEventCode(cond.getEventCode());
		condReview.setGroupReviewEmpIds(cond.getGroupReviewEmpIds());
		condReview.setEventLevels(cond.getEventLevels());
		Integer reviewCount = this.baseMapper.findListCountByCond(condReview);
		retMap.put("已查阅", reviewCount);
		condReview.setIsGroupReview(0);
		Integer unReviewCount = this.baseMapper.findListCountByCond(condReview);
		retMap.put("未查阅", unReviewCount);
		AeInfoCondition condInfection = new AeInfoCondition();
		BeanUtils.copyProperties(cond, condInfection);
		retMap.put("多发感染事件", this.countInfectionByParams(condInfection));
		AeInfoCondition condOtherMultiple = new AeInfoCondition();
		BeanUtils.copyProperties(cond, condOtherMultiple);
		retMap.put("其他多发事件", this.countOtherMultipleByParams(condOtherMultiple));
		AeInfoCondition condFocus = new AeInfoCondition();
		BeanUtils.copyProperties(cond, condFocus);
		condFocus.setIsFocus(true);
		condFocus.setOperator(UserContext.getUserId());
		retMap.put("重点事件", this.baseMapper.countFocusByParams(condFocus));
		return retMap;
	}

	@Override
	public List<Map<String, Object>> countByGradeOne(AeInfoCondition cond) {
		return this.baseMapper.countByGradeOne(cond);
	}

	@Override
	public List<Map<String, Object>> countByDepartment(AeInfoCondition cond) {
		List<Map<String, Object>> list = this.baseMapper.countByDepartment(cond);
		if (list.size() < 15) {
			return list;
		} else {
			return list.subList(0, 15);
		}
	}

	@Override
	public List<Map<String, Object>> countProvinceByHospId(AeInfoCondition cond) {
		List<Map<String, Object>> list = this.baseMapper.countByHospId(cond);
		return handleGroupViewData(list);
	}

	@Override
	public List<Map<String, Object>> countByHospId(AeInfoCondition cond) {
		if (CollectionUtils.isNotEmpty(cond.getHospList())) {
			return this.baseMapper.countByHospId(cond);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> countByEventLevel(AeInfoCondition cond) {
		return this.baseMapper.countByEventLevel(cond);
	}

	@Override
	public List<Map<String, Object>> countBySeverityLevel(AeInfoCondition cond) {
		return this.baseMapper.countBySeverityLevel(cond);
	}

	@Override
	public List<Map<String, Object>> countBySubspecialty(AeInfoCondition cond) {
		return this.baseMapper.countBySubspecialty(cond);
	}

	@Override
	public List<Map<String, Object>> findByParams(Page<Map<String, Object>> page, AeInfoCondition cond) {
		List<Map<String, Object>> list = this.baseMapper.findByParams(page, cond);
		if (CollectionUtils.isNotEmpty(list)) {
			List<Map<String, Object>> hospList = institutionService.getAllHospital();
			Map<Long, String> hospMap = hospList.stream().collect(
					Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"),(existing, replacement) -> replacement));
			Map<String, Object> map = null;
			for (int i = 0, len = list.size(); i < len; i++) {
				map = list.get(i);
				map.put("province", hospMap.get(MapUtils.getLong(map, "hospid")));
			}
			return list;
		}
		return null;
	}

	@Override
	@AierServiceLog(module = "事件保存", message = "{0}=>审核意见：{1}=>{2}")
	public Integer archivedById(Long id) {
		AeBasicInfo aeBasicInfo = this.selectById(id);
		if (!aeBasicInfo.getNode().equals(1)) {
			throw new BizException("此事件所处结点不能归档！！！");
		}
		AeOperationRecord record = new AeOperationRecord();
		record.setId(null);
		record.setBasicId(id);
		record.setHospId(aeBasicInfo.getHospId());
		record.setType(OperateEnum.Archived.getType());
		record.setNode(aeBasicInfo.getNode());
		record = EntityUtils.addOperatorInfo(record);
		aeOperationRecordMapper.insert(record);
		try {
			LogMessage message = LogMessage.newWrite()
					.setParams("【" + OperateEnum.typeOf(OperateEnum.Archived.getType()).getEnumDesc() + "】"
							+ EventEnum.findEnumByCode(aeBasicInfo.getEventCode()).getEnumDesc())
					.setParams("").setParams(JSONUtil.toJsonStr(aeBasicInfo));
			LogUtils.putArgs(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.baseMapper.archivedById(id);
	}

	@Override
	public AeBasicInfo getLast(Long basicId) {
		// TODO Auto-generated method stub

		AeInfoCondition cond = new AeInfoCondition();
		cond.setPrevId(basicId);
		cond.setOperateType(OperateEnum.QUERY_INCREASE.getType());
		cond.setShowAllNode(true);
		List<AeBasicInfo> basicList = this.baseMapper.findEntityList(cond);
		if (basicList.size() == 0) {
			return null;
		} else {
			return basicList.get(0);
		}

	}

	@Override
	public Map<String, Object> getLastEvent(Long prevId, EventEnum eEnum, Boolean isLast) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mObject = Maps.newHashMap();

		AeInfoCondition cond = new AeInfoCondition();
		cond.setPrevId(prevId);
		cond.setOperateType(OperateEnum.QUERY_INCREASE.getType());
		cond.setShowAllNode(true);
		List<AeBasicInfo> basicList = this.baseMapper.findEntityList(cond);
		if (basicList.size() == 0)
			return mObject;

		AeBasicInfo basicInfo = new AeBasicInfo();
		if (isLast) {
			basicInfo = basicList.get(0);
		} else {
			basicInfo = basicList.get(basicList.size() - 1);
		}
		Long eventId = basicInfo.getEventId();
		mObject = EntityUtils.transObjToMap(basicInfo);
		mObject.put("basicId", mObject.remove("id"));

		Object eventInfo = EntityUtils.findEntityById(eventId, eEnum.getName());
		Map<String, Object> mEventInfo = EntityUtils.transObjToMap(eventInfo);
		mObject.putAll(mEventInfo);
		return mObject;
	}

	@Override
	public void update(List<AeBasicInfo> basicList) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (AeBasicInfo curBasic : basicList) {
				// 标签关联表更新
				if (ObjectUtils.isEmpty(curBasic))
					continue;
				String tags = curBasic.getTags();
				this.baseMapper.updateById(curBasic);
				this.saveTags(tags, curBasic.getId());
				// 同步基础信息
				doSyncInfo(curBasic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}

	}

	/**
	 * 同步信息
	 * 
	 * @param basicInfo
	 */
	void doSyncInfo(AeBasicInfo basicInfo) {
		Long prevId = basicInfo.getPrevId();
		Long basicId = basicInfo.getId();

		AeInfoCondition cond = new AeInfoCondition();
		cond.setId(basicId);
		cond.setPrevId(prevId);
		cond.setOperateType(OperateEnum.QUERY_MULTI.getType());
		cond.setShowAllNode(true);

		AeOverview overview = new AeOverview();
		BeanUtils.copyProperties(basicInfo, overview);
		List<AeBasicInfo> basicList = this.baseMapper.findEntityList(cond);
		Date curDate = new Date();
		for (AeBasicInfo curBasicInfo : basicList) {
			if (curBasicInfo.getId().equals(basicId))
				continue;
			AeOverview oldOverview = new AeOverview();
			BeanUtils.copyProperties(curBasicInfo, oldOverview);
			if (!oldOverview.equals(overview)) {
				BeanUtils.copyProperties(overview, curBasicInfo);
				curBasicInfo.setModifyDate(curDate);
				baseMapper.updateById(curBasicInfo);
			}
		}

	}

	private void saveTags(Object tags, Long basicId) {
		if (ObjectUtils.isEmpty(tags))
			return;
		// 获取二次上报相关ID
		List<Long> basicIds = this.getRelatedIds(basicId, null);
		String[] tagArray = tags.toString().split(",");
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.in("event_id", basicIds);
		ew.eq("using_sign", 1);
		List<AeEventTags> eventTagList = aeEventTagsMapper.selectList(ew);

		for (String curTag : tagArray) {
			List<AeEventTags> hasList = Lists.newArrayList();
			Boolean hasExist = false;
			for (AeEventTags curEventTag : eventTagList) {
				if (curTag.equals(curEventTag.getTagCode())) {
					hasList.add(curEventTag);
					hasExist = true;
				}
			}

			if (hasExist) {
				for (AeEventTags hasTag : hasList) {
					eventTagList.remove(hasTag);
				}
			} else {
				for (Long curBasicId : basicIds) {
					AeEventTags eventTag = new AeEventTags();
					eventTag.setEventId(curBasicId);
					eventTag.setTagCode(curTag);
					EntityUtils.addOperatorInfo(eventTag);
					aeEventTagsMapper.insert(eventTag);
				}
			}
		}

		// 删除无效的Tags
		for (AeEventTags curEventTag : eventTagList) {
			curEventTag.setUsingSign(0);
			aeEventTagsMapper.updateById(curEventTag);
		}

	}

	@Override
	public Boolean updateGroupReview(Long id) {
		// TODO Auto-generated method stub
		// 需要判断当前登录人员是否有查阅过，没查阅的需要写入记录表中
		Wrapper<AeOperationRecord> wrapper = new EntityWrapper<>();
		wrapper.eq("basic_id", id);
		wrapper.eq("modifer", UserContext.getUserId());
		wrapper.eq("type", OperateEnum.LOOK.getType());
		Integer count = aeOperationRecordMapper.selectCount(wrapper);
		if (count > 0)
			return true;

		AeOperationRecord aeOperationRecord = new AeOperationRecord();
		aeOperationRecord.setBasicId(id);
		aeOperationRecord.setHospId(0L);
		aeOperationRecord.setNode(NodeEnum.GROUP_REVIEWS.getSeq());
		aeOperationRecord.setType(OperateEnum.LOOK.getType());
		aeOperationRecord = EntityUtils.addOperatorInfo(aeOperationRecord);
		aeOperationRecordMapper.insert(aeOperationRecord);

		AeBasicInfo basicInfo = this.selectById(id);
		if (ObjectUtils.isEmpty(basicInfo.getIsGroupReview()) || basicInfo.getIsGroupReview() == 0) {
			basicInfo.setIsGroupReview(1);
			this.updateById(basicInfo);
		}

		return true;
	}

	@Override
	public Boolean updateOaEventAmount(Long oaId, BigDecimal amount, Long aeId) {
		try {
			Wrapper<AeBasicInfo> aeBasicInfoWrapper = new EntityWrapper<>();
			aeBasicInfoWrapper.eq("oa_request_id", oaId);
			if (!ObjectUtils.isEmpty(aeId)) {
				aeBasicInfoWrapper.eq("id", aeId);
			}
			AeBasicInfo aeBasicInfo = this.selectById(aeId);

			if (ObjectUtils.isEmpty(aeBasicInfo)) {
				throw new BizException("oaId:" + oaId.toString() + "|aeId:" + aeId.toString() + "|不良事件中不存在对应的流程！！！");
			}

			this.baseMapper.updateAmountById(aeBasicInfo.getId(), amount);
			// logger.info("OA修改赔付金额,oaId:{},金额：{},原金额：{}",oaId,amount,aeBasicInfo.getCompensationAmount());

			logger.info("OA修改赔付金额,aeId:{},oaId:{},金额：{},原金额：{}", aeId, oaId, amount,
					aeBasicInfo.getCompensationAmount());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}

		return true;

	}

	@Override
	public List<Map<String, Object>> getLastEventInfo(List<Long> basicIds) {
		// TODO Auto-generated method stub
		return this.baseMapper.getLastEventInfo(basicIds);
	}

	@Override
	public AeBasicInfo sumAmount(AeInfoCondition cond) {
		cond.setShowAllNode(true);
		return this.baseMapper.sumAmount(cond);
	}

	@Override
	@AierServiceLog(module = "OA上报删除", message = "删除事件ID：{0}的oa上报信息和金额,原oaId:{1},原金额：{2}")
	public Boolean delOa(Long id) {
		AeBasicInfo aeBasicInfo = this.selectById(id);
		try {
			LogMessage message = LogMessage.newWrite()
					.setParams(id.toString()).setParams(aeBasicInfo.getOaRequestId().toString())
					.setParams(aeBasicInfo.getCompensationAmount());
			LogUtils.putArgs(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.baseMapper.delOa(id);
	}

	@Override
	public Boolean delAmount(Long id, String type) {
		try {
			AeBasicInfo aeBasicInfo = this.selectById(id);
			if ("compensation".equals(type)) {
				aeBasicInfo.setCompensationAmount(BigDecimal.ZERO);
			} else if ("deduction".equals(type)) {
				aeBasicInfo.setDeductionAmount(BigDecimal.ZERO);
			} else {
				throw new BizException("删除金额类型错误");
			}
			this.baseMapper.updateById(aeBasicInfo);
			return true;
		} catch (BizException e) {
			return false;
		}
	}

	@Override
	public Boolean updateEhrDeptReview(Long id) {
		AeBasicInfo basicInfo = this.selectById(id);
		basicInfo.setIsEhrDeptReview(1);
		this.updateById(basicInfo);
		return true;
	}

	@Override
	public List<Map<String, Object>> countHospitalByHospName(AeInfoCondition cond) {
		List<Map<String, Object>> list = this.baseMapper.countByHospId(cond);
		if (list.size() < 20) {
			return list;
		} else {
			return list.subList(0, 20);
		}
	}

	@Override
	public List<Map<String, Object>> countByParams(Page<Map<String, Object>> page, AeInfoCondition cond) {
		List<Map<String, Object>> list = this.baseMapper.countByParams(page, cond);
		if (CollectionUtils.isNotEmpty(list)) {
			List<Map<String, Object>> hospList = institutionService.getAllHospital();
			// 当有重复键时,(existing, replacement) -> replacement表示保留替代的值
			Map<Long, String> hospMap = hospList.stream().collect(
					Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"),(existing, replacement) -> replacement));
			Map<String, Object> map = null;
			for (int i = 0, len = list.size(); i < len; i++) {
				map = list.get(i);
				map.put("province", hospMap.get(MapUtils.getLong(map, "hospid")));
			}
			return list;
		}
		return null;
	}

	@Override
	public PageResponse<Map<String, Object>> queryInfectionByParams(AeInfoCondition cond) {
		PageResponse<Map<String, Object>> infectionData = new PageResponse<>();
		cond.setGradeOneCodeStr("3"); // 固定设置为"医院感染事件"
		Map<String, Integer> countMap = Maps.newHashMap();
		countMap.put("count", 0);
		List<Map<String, Object>> sumLists = com.google.common.collect.Lists.newArrayList();
		// 分割时间，统计"医院感染事件"次数
		if (cond.getReportDateBegin() != null && cond.getReportDateEnd() != null
				&& !cond.getReportDateBegin().equals("") && !cond.getReportDateEnd().equals("")) {
			List<String> reportDateList = this.generateDateRanges(cond.getReportDateBegin(), cond.getReportDateEnd());
			if (Objects.nonNull(reportDateList) && reportDateList.size() > 0) {
				reportDateList.stream().forEach(dateStr -> {
					cond.setReportDateBegin(dateStr.split(",")[0]);
					cond.setReportDateEnd(dateStr.split(",")[1]);
					List<Map<String, Object>> loopQueryLists = this.baseMapper.queryInfectionByParams(cond);
					if (Objects.nonNull(loopQueryLists) && loopQueryLists.size() > 0) {
						countMap.put("count", countMap.get("count") + loopQueryLists.size());
						sumLists.addAll(loopQueryLists);
					}
				});
			}
		}
		if (cond.getEventDateBegin() != null && cond.getEventDateEnd() != null
				&& !cond.getEventDateBegin().equals("") && !cond.getEventDateEnd().equals("")) {
			List<String> eventDateList = this.generateDateRanges(cond.getEventDateBegin(), cond.getEventDateEnd());
			if (Objects.nonNull(eventDateList) && eventDateList.size() > 0) {
				eventDateList.stream().forEach(dateStr -> {
					cond.setEventDateBegin(dateStr.split(",")[0]);
					cond.setEventDateEnd(dateStr.split(",")[1]);
					List<Map<String, Object>> loopQueryLists = this.baseMapper.queryInfectionByParams(cond);
					if (Objects.nonNull(loopQueryLists) && loopQueryLists.size() > 0) {
						countMap.put("count", countMap.get("count") + loopQueryLists.size());
						sumLists.addAll(loopQueryLists);
					}
				});
			}
		}
		// 设置数据和分页
		infectionData.setRows(sumLists.stream().skip((cond.getPage() - 1) * cond.getRows()).limit(cond.getRows())
				.collect(Collectors.toList()));
		infectionData.setTotal(countMap.get("count").intValue());
		if (CollectionUtils.isNotEmpty(infectionData.getRows())) {
			List<Map<String, Object>> hospList = institutionService.getAllHospital();
			Map<Long, String> hospMap = hospList.stream().collect(
					Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"),(existing, replacement) -> replacement));
			for (int i = 0, len = infectionData.getRows().size(); i < len; i++) {
				infectionData.getRows().get(i).put("province",
						hospMap.get(MapUtils.getLong(infectionData.getRows().get(i), "hospid")));
			}
		}
		return infectionData;
	}

	@Override
	public PageResponse<Map<String, Object>> queryOtherMultipleByParams(AeInfoCondition cond) {
		cond.setGradeOneCode(null);
		cond.setGradeOneCodeStr(null);
		PageResponse<Map<String, Object>> infectionData = new PageResponse<>();
		Map<String, Integer> countMap = Maps.newHashMap();
		countMap.put("count", 0);
		List<Map<String, Object>> sumLists = com.google.common.collect.Lists.newArrayList();
		// 分割时间，统计"其他多发事件"次数
		if (cond.getReportDateBegin() != null && cond.getReportDateEnd() != null
				&& !cond.getReportDateBegin().equals("") && !cond.getReportDateEnd().equals("")) {
			List<String> reportDateList = this.generateDateRanges(cond.getReportDateBegin(), cond.getReportDateEnd());
			if (Objects.nonNull(reportDateList) && reportDateList.size() > 0) {
				reportDateList.stream().forEach(dateStr -> {
					cond.setReportDateBegin(dateStr.split(",")[0]);
					cond.setReportDateEnd(dateStr.split(",")[1]);
					List<Map<String, Object>> loopQueryLists = this.baseMapper.queryOtherMultipleByParams(cond);
					if (Objects.nonNull(loopQueryLists) && loopQueryLists.size() > 0) {
						countMap.put("count", countMap.get("count") + loopQueryLists.size());
						sumLists.addAll(loopQueryLists);
					}
				});
			}
		}
		if (cond.getEventDateBegin() != null && cond.getEventDateEnd() != null
				&& !cond.getEventDateBegin().equals("") && !cond.getEventDateEnd().equals("")) {
			List<String> eventDateList = this.generateDateRanges(cond.getEventDateBegin(), cond.getEventDateEnd());
			if (Objects.nonNull(eventDateList) && eventDateList.size() > 0) {
				eventDateList.stream().forEach(dateStr -> {
					cond.setEventDateBegin(dateStr.split(",")[0]);
					cond.setEventDateEnd(dateStr.split(",")[1]);
					List<Map<String, Object>> loopQueryLists = this.baseMapper.queryOtherMultipleByParams(cond);
					if (Objects.nonNull(loopQueryLists) && loopQueryLists.size() > 0) {
						countMap.put("count", countMap.get("count") + loopQueryLists.size());
						sumLists.addAll(loopQueryLists);
					}
				});
			}
		}
		// 设置数据和分页
		infectionData.setRows(sumLists.stream().skip((cond.getPage() - 1) * cond.getRows()).limit(cond.getRows())
				.collect(Collectors.toList()));
		infectionData.setTotal(countMap.get("count").intValue());
		if (CollectionUtils.isNotEmpty(infectionData.getRows())) {
			List<Map<String, Object>> hospList = institutionService.getAllHospital();
			Map<Long, String> hospMap = hospList.stream().collect(
					Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"),(existing, replacement) -> replacement));
			for (int i = 0, len = infectionData.getRows().size(); i < len; i++) {
				infectionData.getRows().get(i).put("province",
						hospMap.get(MapUtils.getLong(infectionData.getRows().get(i), "hospid")));
			}
		}
		return infectionData;
	}

	@Override
	public List<Map<String, Object>> getUnfinishedEvent(Integer delayDays) {
		List<Map<String, Object>> resultList = Lists.newArrayList();

		// Fetch unfinished events
		EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
		ew.eq("finish_sign", 0);
		ew.le("create_date", DateUtils.addDays(new Date(), -delayDays));
		ew.eq("report_times", 1);
		// Filter events approved by hospital and province
		ew.andNew().eq("node", NodeEnum.HOSPITAL_REVIEWS.getSeq()).or().eq("node", NodeEnum.PROVINCE_REVIEWS.getSeq());
		ew.andNew().eq("is_send_delay", 0).or().isNull("is_send_delay");
		ew.andNew().eq("id", "master_id").or().isNull("master_id");
		ew.orderBy("hosp_id", true);
		List<AeBasicInfo> basicList = this.baseMapper.selectList(ew);
		if (basicList.size() == 0) {
			return resultList;
		}

		// Fetch all hospitals and map them by ahisHosp
		List<Map<String, Object>> instList = institutionService.getAllHospital();
		Map<Long, Map<String, String>> hospIdDetailsMap = instList.stream()
				.filter(m -> MapUtils.getObject(m, "ahisHosp") != null)
				.collect(Collectors.toMap(
						m -> MapUtils.getLong(m, "ahisHosp"),
						m -> {
							Map<String, String> details = new HashMap<>();
							details.put("hospName", MapUtils.getString(m, "name"));
							details.put("instCode", MapUtils.getString(m, "instCode"));
							return details;
						}
				));

		Map<Long, List<Map<String, String>>> hospEventMap = Maps.newHashMap();

		for (AeBasicInfo basicInfo : basicList) {
			Long ahisHosp = basicInfo.getHospId();
			basicInfo.setIsSendDelay(1);
			String eventName = basicInfo.getEventName();
			String patientName = basicInfo.getPatientName();
			if (ahisHosp != null && eventName != null) {
				Map<String, String> eventMap = new HashMap<>();
				eventMap.put("eventName", eventName);
				eventMap.put("patientName", patientName);
				hospEventMap.computeIfAbsent(ahisHosp, k -> new ArrayList<>()).add(eventMap);
			}
		}

		for (Map.Entry<Long, List<Map<String, String>>> entry : hospEventMap.entrySet()) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("ahisHosp", entry.getKey());
			resultMap.put("events", entry.getValue());
			Map<String, String> details = hospIdDetailsMap.get(entry.getKey());
			if (details != null) {
				resultMap.put("hospName", details.get("hospName"));
				resultMap.put("hospId", details.get("instCode"));
			} else {
				System.out.println("Missing key in hospIdDetailsMap: " + entry.getKey());
			}
			resultList.add(resultMap);
		}

		// Batch update basicList
		if (CollectionUtils.isNotEmpty(basicList)) {
			this.updateBatchById(basicList);
		}

		return resultList;
	}

	@Override
	public List<Map<String, Object>> countProvReviewOver10Days(AeInfoCondition cond) {
		List<Map<String, Object>> list = this.aeBasicInfoGroupStatMapper.countProvReviewOver10Days(cond);
		return handleGroupViewData(list);
	}

	private List<Map<String, Object>> handleGroupViewData(List<Map<String, Object>> list){
		List<Map<String, Object>> hospList = institutionService.getAllHospital();
		Map<Long, String> hospMap = hospList.stream().collect(
				Collectors.toMap(m -> MapUtils.getLong(m, "ahisHosp"), m -> MapUtils.getString(m, "provinceName"),
						(existingValue, newValue) -> existingValue));
		Map<String, Object> map = null;
		for (int i = 0, len = list.size(); i < len; i++) {
			map = list.get(i);
			map.put("province", hospMap.get(MapUtils.getLong(map, "hospid")));
		}
		Map<String, Integer> provinceSumMap = list.stream().collect(Collectors.groupingBy(
				m -> MapUtils.getString(m, "province"), Collectors.summingInt(m -> MapUtils.getInteger(m, "val"))));
		Map<String, String> provinceStrHospId = list.stream().collect(Collectors.groupingBy(
				m -> MapUtils.getString(m, "province"),
				Collectors.mapping(m -> MapUtils.getString(m, "hospid"), Collectors.joining(","))));
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map.Entry<String, Integer> entry : provinceSumMap.entrySet()) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("province", entry.getKey());
			m.put("val", entry.getValue());
			m.put("strHospIds", provinceStrHospId.get(entry.getKey()));
			resultList.add(m);
		}
		return resultList.stream()
				.sorted(Comparator.comparing(m -> MapUtils.getInteger(m, "val"), Comparator.reverseOrder()))
				.collect(Collectors.toList());
	}

	private Integer countInfectionByParams(AeInfoCondition cond) {
		cond.setGradeOneCodeStr("3"); // 固定设置为"医院感染事件"
		Map<String, Integer> countMap = Maps.newHashMap();
		countMap.put("count", 0);
		// 分割时间，统计"医院感染事件"次数
		if (cond.getReportDateBegin() != null && cond.getReportDateEnd() != null
				&& !cond.getReportDateBegin().equals("") && !cond.getReportDateEnd().equals("")) {
			List<String> reportDateList = this.generateDateRanges(cond.getReportDateBegin(), cond.getReportDateEnd());
			if (Objects.nonNull(reportDateList) && reportDateList.size() > 0) {
				reportDateList.stream().forEach(dateStr -> {
					cond.setReportDateBegin(dateStr.split(",")[0]);
					cond.setReportDateEnd(dateStr.split(",")[1]);
					Integer num = this.baseMapper.countInfectionByParams(cond);
					if (Objects.nonNull(num) && num.intValue() > 0) {
						countMap.put("count", countMap.get("count") + num);
					}
				});
			}
		}
		if (cond.getEventDateBegin() != null && cond.getEventDateEnd() != null
				&& !cond.getEventDateBegin().equals("") && !cond.getEventDateEnd().equals("")) {
			List<String> eventDateList = this.generateDateRanges(cond.getEventDateBegin(), cond.getEventDateEnd());
			if (Objects.nonNull(eventDateList) && eventDateList.size() > 0) {
				eventDateList.stream().forEach(dateStr -> {
					cond.setEventDateBegin(dateStr.split(",")[0]);
					cond.setEventDateEnd(dateStr.split(",")[1]);
					Integer num = this.baseMapper.countInfectionByParams(cond);
					if (Objects.nonNull(num) && num.intValue() > 0) {
						countMap.put("count", countMap.get("count") + num);
					}
				});
			}
		}
		return countMap.get("count");
	}

	private Integer countOtherMultipleByParams(AeInfoCondition cond) {
		cond.setGradeOneCode(null);
		cond.setGradeOneCodeStr(null);
		Map<String, Integer> countMap = Maps.newHashMap();
		countMap.put("count", 0);
		// 分割时间，统计"其他多发事件"次数
		if (cond.getReportDateBegin() != null && cond.getReportDateEnd() != null
				&& !cond.getReportDateBegin().equals("") && !cond.getReportDateEnd().equals("")) {
			List<String> reportDateList = this.generateDateRanges(cond.getReportDateBegin(), cond.getReportDateEnd());
			if (Objects.nonNull(reportDateList) && reportDateList.size() > 0) {
				reportDateList.stream().forEach(dateStr -> {
					cond.setReportDateBegin(dateStr.split(",")[0]);
					cond.setReportDateEnd(dateStr.split(",")[1]);
					Integer num = this.baseMapper.countOtherMultipleByParams(cond);
					if (Objects.nonNull(num) && num.intValue() > 0) {
						countMap.put("count", countMap.get("count") + num);
					}
				});
			}
		}
		if (cond.getEventDateBegin() != null && cond.getEventDateEnd() != null
				&& !cond.getEventDateBegin().equals("") && !cond.getEventDateEnd().equals("")) {
			List<String> eventDateList = this.generateDateRanges(cond.getEventDateBegin(), cond.getEventDateEnd());
			if (Objects.nonNull(eventDateList) && eventDateList.size() > 0) {
				eventDateList.stream().forEach(dateStr -> {
					cond.setEventDateBegin(dateStr.split(",")[0]);
					cond.setEventDateEnd(dateStr.split(",")[1]);
					Integer num = this.baseMapper.countOtherMultipleByParams(cond);
					if (Objects.nonNull(num) && num.intValue() > 0) {
						countMap.put("count", countMap.get("count") + num);
					}
				});
			}
		}
		return countMap.get("count");
	}

	private List<String> generateDateRanges(String reportDateBegin, String reportDateEnd) {
		// 将输入的字符串转为LocalDate对象
		LocalDate startDate = LocalDate.parse(reportDateBegin);
		LocalDate endDate = LocalDate.parse(reportDateEnd);

		List<String> dateRanges = new ArrayList<>();

		// 当开始日期在结束日期之前时，生成月份区间
		while (!startDate.isAfter(endDate)) {
			// 获取当前月的最后一天
			LocalDate endOfMonth = startDate.with(TemporalAdjusters.lastDayOfMonth());

			// 如果月份的最后一天大于结束日期，则将结束日期作为当前区间的结束
			if (endOfMonth.isAfter(endDate)) {
				endOfMonth = endDate;
			}

			dateRanges.add(startDate.toString() + "," + endOfMonth.toString());

			// 将开始日期移动到下个月的第一天
			startDate = endOfMonth.plusDays(1);
		}

		return dateRanges;
	}
}
