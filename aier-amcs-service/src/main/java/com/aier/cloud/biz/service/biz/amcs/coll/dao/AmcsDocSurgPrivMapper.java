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
package com.aier.cloud.biz.service.biz.amcs.coll.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aier.cloud.api.amcs.condition.DocSurgCondition;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsDocSurgPriv;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 医生手术权限表
 * @author 爱尔眼科
 * @since 2023-05-16 10:36:22
 */
public interface AmcsDocSurgPrivMapper extends BaseMapper<AmcsDocSurgPriv> {
	
	public List<Map<String, Object>> findCountByProv(Page<Map<String, Object>> page, @Param("cond") DocSurgCondition cond);
	
	public List<AmcsDocSurgPriv> findListByDoctor(Page<Map<String, Object>> page, @Param("cond") DocSurgCondition cond);
	
	public List<Long> findStaffIdByHosp(@Param("hospId") Long hospId);
	 
}