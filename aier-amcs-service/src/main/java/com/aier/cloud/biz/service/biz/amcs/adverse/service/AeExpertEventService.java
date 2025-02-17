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
package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpertEvent;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeExpertEvent
 * 不良事件专家关联表
 * @author 爱尔眼科
 * @since 2023-02-17 15:50:09
 */
public interface AeExpertEventService extends IService<AeExpertEvent>{
	
	void bind(AeExpertCondition bindData) throws Exception;
	
	Integer findExpertDayCount(String expertId, Boolean isProvince) throws Exception;
	
	List<Map<String, Object>> findListByEvent(AeExpertCondition cond);
}