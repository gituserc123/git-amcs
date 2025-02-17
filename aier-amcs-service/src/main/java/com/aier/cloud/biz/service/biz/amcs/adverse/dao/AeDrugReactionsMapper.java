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

import com.aier.cloud.api.amcs.condition.DrugReactionsCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDrugReactions;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 药品不良反应事件报告
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
public interface AeDrugReactionsMapper extends BaseMapper<AeDrugReactions> {
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") DrugReactionsCondition cond);
    List<Map<String, Object>> getAllEntity(@Param("cond") DrugReactionsCondition cond);
}