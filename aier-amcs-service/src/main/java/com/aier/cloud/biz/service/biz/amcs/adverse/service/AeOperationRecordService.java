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

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeOperationRecord
 * 
 * @author 爱尔眼科
 * @since 2022-10-21 10:48:39
 */
public interface AeOperationRecordService extends IService<AeOperationRecord>{
	
	List<Map<String, Object>> findOpinionList(AeInfoCondition cond);
	
	Map<String, Object> findOpinionByReview(AeInfoCondition cond);

	Long findOperator(Long id,Integer node,Integer type);
	
	Boolean hasGroupLook(Long basicId);
	
	List<AeOperationRecord> findLookList(List<Long> basicIds);
	
	List<AeOperationRecord> findReviewList(List<Long> basicIds, Integer node);
	
	Integer saveOpinion(Map<String, Object> mOpinionInfo);

	AeOperationRecord getOperatorRecord(AeInfoCondition cond);
		
}