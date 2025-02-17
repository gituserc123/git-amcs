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

import org.springframework.web.bind.annotation.RequestBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeEventTags
 * 事件标签关联表
 * @author 爱尔眼科
 * @since 2023-03-10 17:12:11
 */
public interface AeEventTagsService extends IService<AeEventTags>{
	
	public List<AeEventTags> findListByEventId(Long eventId);
	
	public String findTagsById(Long eventId);
	
	public String findTagDescsById(Long eventId);
	
	List<AeEventTags> findListByCond(@RequestBody AeInfoCondition cond);
	
}