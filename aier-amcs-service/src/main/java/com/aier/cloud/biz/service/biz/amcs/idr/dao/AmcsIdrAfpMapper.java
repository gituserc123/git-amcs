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
package com.aier.cloud.biz.service.biz.amcs.idr.dao;

import org.apache.ibatis.annotations.Param;

import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrAfp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * AFP病附卡
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
public interface AmcsIdrAfpMapper extends BaseMapper<AmcsIdrAfp> {

	AmcsIdrAfp selectByTid(@Param("tId")  Long tId);

	void delTid(@Param("tId")  Long tId);
	 
}