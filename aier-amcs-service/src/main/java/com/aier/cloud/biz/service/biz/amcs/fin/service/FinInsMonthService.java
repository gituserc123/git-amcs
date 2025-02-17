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
package com.aier.cloud.biz.service.biz.amcs.fin.service;

import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMonth;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * FinInsMonth
 * 医院填报月度主表
 * @author 爱尔眼科
 * @since 2023-03-27 14:39:29
 */
public interface FinInsMonthService extends IService<FinInsMonth>{
	Boolean newLine(Long hospId);

	List<Map<String,Object>> getList(FinInsMonthCondition cond);

	Boolean canSubmit(Long id);

	Boolean submitToProvince(Long id);

	Boolean submitToGroup(Long id);

	Boolean returnToHos(Long id);
	Boolean deleteByMonthId(String monthId);

	List<Map<String,Object>> unReport(FinInsMonthCondition cond);
}