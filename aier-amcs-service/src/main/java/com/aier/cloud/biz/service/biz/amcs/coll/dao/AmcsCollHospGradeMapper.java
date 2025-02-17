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

import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollHospGrade;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 医院等级采集表
 * @author 爱尔眼科
 * @since 2023-03-29 09:19:34
 */
public interface AmcsCollHospGradeMapper extends BaseMapper<AmcsCollHospGrade> {
	 
	public List<Map<String, Object>> findList(Page<Map<String, Object>> page, @Param("cond") CollCondition cond);
}