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
package com.aier.cloud.biz.service.biz.amcs.cat.service;

import com.aier.cloud.api.amcs.condition.GlaucomaCondition;
import com.aier.cloud.biz.service.biz.amcs.cat.entity.Glaucoma;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * Glaucoma
 * 青光眼筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
public interface GlaucomaService extends IService<Glaucoma>{

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, GlaucomaCondition cond);

    boolean save(Glaucoma glaucoma);

    List<Glaucoma> getAll(GlaucomaCondition cond);

    Integer delGlaucoma(GlaucomaCondition cond);
	
}