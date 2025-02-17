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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsCollHospGradeService;
import com.alibaba.fastjson.JSONArray;
import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.biz.service.biz.amcs.coll.dao.AmcsCollAttachmentMapper;
import com.aier.cloud.biz.service.biz.amcs.coll.dao.AmcsCollHospGradeMapper;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollAttachment;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollHospEmr;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollHospGrade;

/**
 * AmcsCollHospGrade
 * 医院等级采集表
 * @author 爱尔眼科
 * @since 2023-03-29 09:19:34
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AmcsCollHospGradeServiceImpl extends ServiceImpl<AmcsCollHospGradeMapper, AmcsCollHospGrade> implements AmcsCollHospGradeService {

	@Autowired
	private AmcsCollAttachmentMapper amcsCollAttachmentMapper;
	
	@Override
	public List<Map<String, Object>> findList(Page<Map<String, Object>> page, CollCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findList(page, cond);
	}

	@Override
	public void save(Map<String, Object> mData) throws Exception {
		// TODO Auto-generated method stub
		AmcsCollHospGrade grade = new AmcsCollHospGrade();
		String attachmentsJsonStr = mData.remove("attachments").toString();
		grade = EntityUtils.transMapToObject(mData, AmcsCollHospGrade.class);
		EntityUtils.addOperatorInfo(grade);
		Long collId = grade.getId();
		if(ObjectUtils.isEmpty(collId)) {
			this.baseMapper.insert(grade);
			//保存附件信息
			List<AmcsCollAttachment> attachmentList = JSONArray.parseArray(attachmentsJsonStr, AmcsCollAttachment.class);
			for(AmcsCollAttachment attachment: attachmentList) {
				attachment.setCollId(grade.getId());
				attachment.setModifer(grade.getModifer());
				attachment.setModifyDate(grade.getModifyDate());
				amcsCollAttachmentMapper.insert(attachment);
			}
		}else {
			this.baseMapper.updateById(grade);
			//获取此ID对应的附件
			EntityWrapper<AmcsCollAttachment> ew = new EntityWrapper<>();
			ew.eq("coll_id", grade.getId());
			ew.eq("using_sign", 1);
			//上次提交时的附件列表
			List<AmcsCollAttachment> attLastList = amcsCollAttachmentMapper.selectList(ew);
			//本次提交时的附件列表
			List<AmcsCollAttachment> attThisList = JSONArray.parseArray(attachmentsJsonStr, AmcsCollAttachment.class);
			//本次提交时上次已存在的附件列表
			List<AmcsCollAttachment> attOldList = Lists.newArrayList();
			
			attThisList.forEach(attachment -> {
				if(ObjectUtils.isEmpty(attachment.getId())) {
					attachment.setCollId(collId);
					EntityUtils.addOperatorInfo(attachment);
					amcsCollAttachmentMapper.insert(attachment);
				}else {
					attOldList.add(attachment);
				}
			});
			
			Set<Long> attachmentIds = new HashSet<>();
			attOldList.forEach(attachment -> {
			    attachmentIds.add(attachment.getId());
			});

			attLastList.forEach(attachment -> {
			    if (!attachmentIds.contains(attachment.getId())) {
			    	attachment.setUsingSign(0);
			    	amcsCollAttachmentMapper.updateById(attachment);
			    } 
			});
			
		}
	}
	
}

