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
package com.aier.cloud.biz.service.biz.amcs.coll.service;

import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollHospEmr;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * AmcsCollHospEmr
 * 医院电子病历应用水平收集表
 * @author 爱尔眼科
 * @since 2023-03-29 09:28:26
 */
public interface AmcsCollHospEmrService extends IService<AmcsCollHospEmr>{
	
	List<Map<String, Object>> findList(Page<Map<String, Object>> page, CollCondition cond);
	
	void save(Map<String, Object> mData) throws Exception;
}