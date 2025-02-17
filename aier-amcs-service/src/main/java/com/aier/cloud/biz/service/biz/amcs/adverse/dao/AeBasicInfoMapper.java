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
package com.aier.cloud.biz.service.biz.amcs.adverse.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author 爱尔眼科
 * @since 2022-08-15 17:53:42
 */
public interface AeBasicInfoMapper extends BaseMapper<AeBasicInfo> {

	List<Map<String, Object>> findListByCond(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);
	
	List<Map<String, Object>> findListByCond(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> findReturnList(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> findReviewList(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);
	
	List<Map<String, Object>> findByParams(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);

	Integer getLastReportTimes(@Param("prevId") Long prevId);
	
	Integer getCountByCond(@Param("cond") AeInfoCondition cond);

	List<AeBasicInfo> findEntityList(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> queryListByCond(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);

	Integer updateOaRequestById(@Param("id") Long id, @Param("requestId") Integer requestId);

	Integer archivedById(@Param("id") Long id);

	Map<String, Object> countIndex(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> countByGradeOne(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> countByDepartment(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> countByHospId(@Param("cond") AeInfoCondition cond);
	
	List<Map<String, Object>> countByEventLevel(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> countBySeverityLevel(@Param("cond") AeInfoCondition cond);
	
	List<Map<String, Object>> countBySubspecialty(@Param("cond") AeInfoCondition cond);

	Integer updateAmountById(@Param("id") Long id,@Param("amount") BigDecimal amount);
	
	AeBasicInfo sumAmount(@Param("cond") AeInfoCondition cond);
	
	List<Map<String, Object>> getLastEventInfo(@Param("basicIds") List<Long> basicIds);

	Integer findListCountByCond(@Param("cond") AeInfoCondition cond);

	Boolean delOa(@Param("id") Long id);

	List<Map<String, Object>> countByParams(Page<Map<String, Object>> page, @Param("cond") AeInfoCondition cond);

	Integer countInfectionByParams(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> queryInfectionByParams(@Param("cond") AeInfoCondition cond);

	Integer countOtherMultipleByParams(@Param("cond") AeInfoCondition cond);

	List<Map<String, Object>> queryOtherMultipleByParams(@Param("cond") AeInfoCondition cond);

	Integer countFocusByParams(@Param("cond") AeInfoCondition cond);

}