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
package com.aier.cloud.biz.service.biz.amcs.idr.service;

import java.util.List;
import java.util.Map;

import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrDictType;
import com.baomidou.mybatisplus.service.IService;

/**
 * AmcsIdrDictType 传染病字典表
 * 
 * @author luorz
 * @since 2023-04-27 15:09:50
 */
public interface AmcsIdrDictTypeService extends IService<AmcsIdrDictType> {
	/**
	 * 根据字典类型编码查询对应的码值
	 * 
	 * @param typeCode
	 * @return
	 */
	List<AmcsIdrDictType> getTypeCodeForList(AmcsIdrDictType apply);

	/**
	 * 获取对应的枚举值
	 * 
	 * @param typeCode
	 * @return
	 */
	List<Map> getEnum(AmcsIdrDictType c);

	 
	AmcsIdrBaseInfo  getPatient(String patientName);

	List<AmcsIdrDictType> getAmcsIdrDictType(AmcsIdrDictType apply);

	AmcsIdrDictType save(AmcsIdrDictType apply, Long loginUserId, String loginUserName);

	int getdiagCodeIDR(String diagCode);

}