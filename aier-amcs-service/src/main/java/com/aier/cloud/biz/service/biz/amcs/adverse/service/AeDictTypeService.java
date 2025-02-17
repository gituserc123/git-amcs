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

import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDictType;
import com.baomidou.mybatisplus.service.IService;

/**
 * AeDictType
 * 
 * @author 爱尔眼科
 * @since 2022-07-14 17:37:37
 */
public interface AeDictTypeService extends IService<AeDictType>{
	
	
	List<Map> getList(String typeCode, String filter);


	List<Map> getListByFilters(String typeCode, String filters);
	
	/**
	 * 批量取字典
	 * @param paraTypes
	 * @return
	 */
	Map<String, List> getMoreList(List<String> paraTypes);

	Boolean save(AeDictType dictType);
	Boolean del(Long id);
}