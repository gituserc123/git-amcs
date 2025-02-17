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
package com.aier.cloud.biz.service.biz.amcs.coll.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsCollAttachmentService;
import com.aier.cloud.biz.service.biz.amcs.coll.dao.AmcsCollAttachmentMapper;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollAttachment;

/**
 * AmcsCollAttachment
 * 医院采集信息附件表
 * @author 爱尔眼科
 * @since 2023-03-29 09:43:33
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AmcsCollAttachmentServiceImpl extends ServiceImpl<AmcsCollAttachmentMapper, AmcsCollAttachment> implements AmcsCollAttachmentService {

	@Override
	public List<AmcsCollAttachment> findByCollId(Long collId) {
		// TODO Auto-generated method stub
		EntityWrapper<AmcsCollAttachment> ew = new EntityWrapper<>();
		ew.eq("coll_id", collId);
		ew.eq("using_sign", 1);
		return this.baseMapper.selectList(ew);
	}
	
}

