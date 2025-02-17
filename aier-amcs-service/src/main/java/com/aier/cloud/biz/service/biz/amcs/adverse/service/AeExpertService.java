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
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpert;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeExpert
 * 专家记录表
 * @author 爱尔眼科
 * @since 2023-02-07 15:54:34
 */
public interface AeExpertService extends IService<AeExpert>{
	
	List<Map<String, Object>> findListByCond(Page<Map<String, Object>> page, AeExpertCondition cond);
	
	List<Map<String, Object>> findListByCond(AeExpertCondition cond);

	List<AeExpert> getExpertsByCond(AeExpertCondition cond);
	
	Integer save(Map<String, Object> mData) throws Exception;

	Integer del(Long id) throws Exception;

	void refresh(String staffCode) throws Exception;

}