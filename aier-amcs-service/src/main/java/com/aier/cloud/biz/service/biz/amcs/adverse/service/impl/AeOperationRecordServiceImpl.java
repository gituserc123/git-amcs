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

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.basic.common.exception.BizAssert;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeBasicInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeOperationRecordMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOperationRecordService;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

/**
 * AeOperationRecord
 * 
 * @author 爱尔眼科
 * @since 2022-10-21 10:48:39
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AeOperationRecordServiceImpl extends ServiceImpl<AeOperationRecordMapper, AeOperationRecord>
		implements AeOperationRecordService {

	@Autowired
	private AeBasicInfoMapper aeBasicMapper;

	@Override
	public List<Map<String, Object>> findOpinionList(AeInfoCondition cond) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> opinionList = Lists.newArrayList();
		// 获取所有事件相关的ID
		EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
		ew.eq("id", cond.getBasicId()).or().eq("id", cond.getPrevId()).or().eq("prev_id", cond.getPrevId()).or()
				.eq("prev_id", cond.getBasicId());
		List<AeBasicInfo> basicList = aeBasicMapper.selectList(ew);
		List<Long> ids = Lists.newArrayList();
		for (AeBasicInfo basicInfo : basicList) {
			ids.add(basicInfo.getId());
		}
		if (ids.size() > 0) {
			cond.setBasicIds(ids);
			opinionList = this.baseMapper.findOpinionList(cond);
		}
		return opinionList;
	}

	@Override
	public Map<String, Object> findOpinionByReview(AeInfoCondition cond) {
		// TODO Auto-generated method stub
		Map<String, Object> opinion = Maps.newHashMap();
		if (!ObjectUtils.isEmpty(cond.getBasicId())) {
			List<Map<String, Object>> opinionList = this.baseMapper.findOpinionList(cond);
			if (!ObjectUtils.isEmpty(opinionList) && opinionList.size() > 0) {
				opinion = opinionList.get(0);
			}
		}
		return opinion;
	}

	@Override
	public Long findOperator(Long id, Integer node, Integer type) {
		Wrapper<AeOperationRecord> aeOperationRecordWrapper = new EntityWrapper<>();
		aeOperationRecordWrapper.eq("basic_id", id).eq("node", node).eq("type", type);
		List<AeOperationRecord> list = this.selectList(aeOperationRecordWrapper);
		if (list.size() > 0) {
			return list.get(0).getModifer();
		} else {
			return null;
		}
	}

	@Override
	public Boolean hasGroupLook(Long basicId) {
		// TODO Auto-generated method stub
		Wrapper<AeOperationRecord> wrapper = new EntityWrapper<>();
		wrapper.eq("basic_id", basicId);
		wrapper.eq("modifer", UserContext.getUserId());
		wrapper.eq("type", OperateEnum.LOOK.getType());
		Integer count = this.selectCount(wrapper);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public List<AeOperationRecord> findLookList(List<Long> basicIds) {
		// TODO Auto-generated method stub
		EntityWrapper<AeOperationRecord> ew = new EntityWrapper<>();
		ew.in("basic_id", basicIds);
		ew.eq("modifer", UserContext.getUserId());
		ew.eq("type", OperateEnum.LOOK.getType());
		return this.baseMapper.selectList(ew);
	}
	
	@Override
	public List<AeOperationRecord> findReviewList(List<Long> basicIds, Integer node) {
		EntityWrapper<AeOperationRecord> ew = new EntityWrapper<>();
		ew.in("basic_id", basicIds);
		ew.eq("type", OperateEnum.REVIEW.getType());
		ew.eq("node", node);
		return this.baseMapper.selectList(ew);
	}

	@Override
	public Integer saveOpinion(Map<String, Object> mOpinionInfo) {
		// TODO Auto-generated method stub
		AeOperationRecord record = new AeOperationRecord();
		if (!ObjectUtils.isEmpty(mOpinionInfo.get("opinionId"))) {
			Long id = Long.parseLong(mOpinionInfo.get("opinionId").toString());
			record = this.baseMapper.selectById(id);
			record.setOpinion(mOpinionInfo.get("opinion").toString());
			return this.baseMapper.updateById(record);
		} else {
			record = EntityUtils.transMapToObject(mOpinionInfo, AeOperationRecord.class);
			record = EntityUtils.addOperatorInfo(record);
			record.setType(OperateEnum.REVIEW.getType());
			return this.baseMapper.insert(record);
		}

	}

	@Override
	public AeOperationRecord getOperatorRecord(AeInfoCondition cond) {
		BizAssert.notNull(cond.getBasicId(), BizException.ERROR, "BasicId不能为空");
		BizAssert.notNull(cond.getNode(), BizException.ERROR, "Node不能为空");
		BizAssert.notNull(cond.getType(), BizException.ERROR, "Type不能为空");
		Wrapper<AeOperationRecord> ew = new EntityWrapper<>();
		ew.eq("basic_id", cond.getBasicId()).eq("node", cond.getNode()).eq("type", cond.getType());
		List<AeOperationRecord> list = this.selectList(ew);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
