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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertEventService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeExpertEventMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpertEvent;

/**
 * AeExpertEvent
 * 不良事件专家关联表
 * @author 爱尔眼科
 * @since 2023-02-17 15:50:09
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeExpertEventServiceImpl extends ServiceImpl<AeExpertEventMapper, AeExpertEvent> implements AeExpertEventService {

	@Override
	public void bind(AeExpertCondition bindData) throws Exception {
		// TODO Auto-generated method stub
		List<Long> eventIds = JSONArray.parseArray(bindData.getEventIds(), Long.class);
		List<AeExpertEvent> experts = JSONArray.parseArray(bindData.getExperts(), AeExpertEvent.class);

		Boolean isProvince = Boolean.TRUE.equals(bindData.getIsProvince());

		for(Long eventId: eventIds) {				
			//删除之前的事件专家绑定
			List<Long> ids = Lists.newArrayList();
			EntityWrapper<AeExpertEvent> ew = new EntityWrapper<>();
			ew.eq("event_id", eventId);
			if(Boolean.TRUE.equals(isProvince)) {
				ew.eq("is_province", 1);
			}else{
				//is_province为null 或者 0
				ew.eq("is_province", 0).or().isNull("is_province");
			}
	        List<AeExpertEvent> expertEventList = this.baseMapper.selectList(ew);
	        for(AeExpertEvent eEvent: expertEventList) {
	        	ids.add(eEvent.getId());
	        }
	        if(ids.size() > 0) this.baseMapper.deleteBatchIds(ids);
			for(AeExpertEvent expert: experts) {
				AeExpertEvent expertEvent = new AeExpertEvent();
				expertEvent.setEventId(eventId);
				expertEvent.setExpertId(expert.getExpertId());
				expertEvent.setModifer(UserContext.getUserId());
				expertEvent.setModifyDate(new Date());
				expertEvent.setIsProvince(isProvince ? 1 : 0);
				this.baseMapper.insert(expertEvent);
			}
		}
	}


	@Override
	public Integer findExpertDayCount(String expertId, Boolean isProvince) {
		return this.baseMapper.findExpertDayCount(expertId, isProvince);
	}

	@Override
	public List<Map<String, Object>> findListByEvent(AeExpertCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findListByEvent(cond);
	}


}

