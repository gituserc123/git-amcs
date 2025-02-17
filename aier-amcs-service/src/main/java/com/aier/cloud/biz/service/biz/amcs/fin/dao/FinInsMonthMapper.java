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
package com.aier.cloud.biz.service.biz.amcs.fin.dao;

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMonth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 医院填报月度主表
 * @author 爱尔眼科
 * @since 2023-03-27 14:39:29
 */
public interface FinInsMonthMapper extends BaseMapper<FinInsMonth> {
    List<Map<String,Object>> getList(@Param("cond") FinInsMonthCondition cond);
    Boolean deleteAdvance(@Param("monthId") String monthId);
    Boolean deleteConsum(@Param("monthId") String monthId);
    Boolean deleteDipA(@Param("monthId") String monthId);
    Boolean deleteDipP(@Param("monthId") String monthId);
    Boolean deleteDrgA(@Param("monthId") String monthId);
    Boolean deleteDrgP(@Param("monthId") String monthId);
    Boolean deleteMain(@Param("monthId") String monthId);
    Boolean deletePerP(@Param("monthId") String monthId);
    Boolean deleteProP(@Param("monthId") String monthId);
    Boolean deleteSingle(@Param("monthId") String monthId);
    Boolean deleteMonth(@Param("monthId") String monthId);

    List<Map<String,Object>> unReport(@Param("cond") FinInsMonthCondition cond);
    List<Map<String,Object>> reporting(@Param("cond") FinInsMonthCondition cond);
    List<Map<String,Object>> returnBack(@Param("cond") FinInsMonthCondition cond);

    List<Map<String,Object>> noPrice(@Param("cond") FinInsMonthCondition cond);

    List<Map<String,Object>> noDrgDip(@Param("cond") FinInsMonthCondition cond ,@Param("quarter") String quarter);
}