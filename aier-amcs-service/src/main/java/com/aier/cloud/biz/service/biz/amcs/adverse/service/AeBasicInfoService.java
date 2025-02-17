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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeBasicInfo
 * 
 * @author 爱尔眼科
 * @since 2022-08-15 17:53:42
 */
public interface AeBasicInfoService extends IService<AeBasicInfo> {

	List<Map<String, Object>> findListByCond(Page<Map<String, Object>> page, AeInfoCondition cond);

	List<Map<String, Object>> findListForExpert(Page<Map<String, Object>> page, AeInfoCondition cond);

	List<Map<String, Object>> findReturnList(Page<Map<String, Object>> page, AeInfoCondition cond);

	List<Map<String, Object>> findReviewList(Page<Map<String, Object>> page, AeInfoCondition cond);

	List<Map<String, Object>> findByParams(Page<Map<String, Object>> page, AeInfoCondition cond);
	
	AeBasicInfo getLast(Long basicId);
	
	List<Map<String, Object>> getLastEventInfo(List<Long> basicId);

	AeBasicInfo sumAmount(AeInfoCondition cond);

	Integer getLastReportTimes(Long prevId);
	
	Map<String, Object> getLastEvent(Long prevId, EventEnum eEnum, Boolean isLast) throws Exception;

	List<Map<String, Object>> queryListByCond(Page<Map<String, Object>> page, AeInfoCondition cond);

	Integer getCountByCond(AeInfoCondition cond);

	Integer updateOaRequestById(Long id, Integer requestId);
	
	void update(List<AeBasicInfo> basicList) throws Exception;

	List<Map<String, Object>> queryByQueryMapper(String table, String queryFeild, String cond);

	Map<String, Object> countIndex(AeInfoCondition cond);

	List<Map<String, Object>> countByGradeOne(AeInfoCondition cond);

	List<Map<String, Object>> countByDepartment(AeInfoCondition cond);

	List<Map<String, Object>> countByHospId(AeInfoCondition cond);
	
	List<Map<String, Object>> countByEventLevel(AeInfoCondition cond);

	List<Map<String, Object>> countBySeverityLevel(AeInfoCondition cond);
	
	List<Map<String, Object>> countBySubspecialty(AeInfoCondition cond);

	Integer archivedById(Long id);

	List<Map<String, Object>> countProvinceByHospId(AeInfoCondition cond);
	
	Boolean updateGroupReview(Long id);

	Boolean updateOaEventAmount(Long oaId, BigDecimal amount,Long aeId);

	Boolean delOa(Long id);

	Boolean delAmount(Long id, String type);

	Boolean updateEhrDeptReview(Long id);

	List<Map<String, Object>> countHospitalByHospName(AeInfoCondition cond);

	List<Map<String, Object>> countByParams(Page<Map<String, Object>> page, AeInfoCondition cond);

	PageResponse<Map<String, Object>> queryInfectionByParams(AeInfoCondition cond);

	PageResponse<Map<String, Object>> queryOtherMultipleByParams(AeInfoCondition cond);

	List<Map<String, Object>> getUnfinishedEvent(Integer delayDays);

	List<Map<String, Object>> countProvReviewOver10Days(AeInfoCondition cond);
}