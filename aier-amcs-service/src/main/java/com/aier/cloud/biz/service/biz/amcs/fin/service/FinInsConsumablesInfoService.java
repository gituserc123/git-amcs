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

import com.aier.cloud.api.amcs.fin.condition.FinInsConsumablesInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsConsumablesInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * FinInsConsumablesInfo
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */
public interface FinInsConsumablesInfoService extends IService<FinInsConsumablesInfo>{
    Boolean save(FinInsConsumablesInfo finInsConsumablesInfo);
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinInsConsumablesInfoCondition finInsConsumablesInfoCondition);

    List<Map<String, Object>> lastList(Integer consumablesType);
}