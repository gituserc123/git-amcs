/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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

import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaMain;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * FinMaMain
 * 医保管理分析主表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:36
 */
public interface FinMaMainService extends IService<FinMaMain>{

    List<FinMaMain> getAllRecord(FinMaMainCondition cond);
    Map<String, String> save(FinMaMain finMaMain);

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinMaMainCondition cond);

    List<Map<String, Object>> getStatDipRecord(FinMaMainCondition cond);
    List<Map<String, Object>> getStatDrgRecord(FinMaMainCondition cond);
    Integer getStatCount(FinMaMainCondition cond);
	
}