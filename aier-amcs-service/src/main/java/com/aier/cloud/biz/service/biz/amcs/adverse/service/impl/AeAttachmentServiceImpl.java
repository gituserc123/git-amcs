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
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aier.cloud.center.common.context.UserContext;
import org.springframework.transaction.annotation.Transactional;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeAttachmentService;
import com.aier.cloud.api.amcs.adverse.condition.AttachmentCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeAttachmentMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeBasicInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeAttachment;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;

/**
 * AeAttachment
 * 
 * @author 爱尔眼科
 * @since 2022-10-31 14:57:42
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeAttachmentServiceImpl extends ServiceImpl<AeAttachmentMapper, AeAttachment> implements AeAttachmentService {

	@Autowired
	private AeBasicInfoMapper aeBasicMapper;
	
	@Override
	public AeAttachment findByFileId(String fileId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findByFileId(fileId);
	}

	@Override
	public AeAttachment findByFileName(String fileName) {
		// TODO Auto-generated method stub
		return this.baseMapper.findByFileName(fileName);
	}

	@Override
	public List<Map<String, Object>> findByCond(AttachmentCondition cond) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> attachmentList = Lists.newArrayList();
		EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
		ew.eq("id", cond.getBasicId()).or().eq("id", cond.getPrevId()).or().eq("prev_id", cond.getPrevId()).or().eq("prev_id", cond.getBasicId());
		List<AeBasicInfo> basicList = aeBasicMapper.selectList(ew);
		List<Long> ids = Lists.newArrayList();
		for(AeBasicInfo basicInfo: basicList) {
			ids.add(basicInfo.getId());
		}
		if(ids.size() > 0) {
			cond.setBasicIds(ids);
			attachmentList = this.baseMapper.findByCond(cond);
		}
		return attachmentList;
	}

	@Override
	public boolean delByFileId(String fileId) {
		// TODO Auto-generated method stub
		AeAttachment attachment = this.findByFileId(fileId);
		attachment.setUsingSign(0);
		return this.updateById(attachment);
	}

	@Override
	public boolean delByFileTag(Long basicId, String tag) {
		// TODO Auto-generated method stub
		List<AeAttachment> attachments = this.baseMapper.findByFileTag(basicId, tag);
		for(AeAttachment attachment: attachments) {
			attachment.setUsingSign(0);
			this.baseMapper.updateById(attachment);
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> queryByCond(AttachmentCondition cond) {
		return this.baseMapper.findByCond(cond);
	}

	@Override
	public AeAttachment saveOrModify(AeAttachment attachment){
		attachment.setModifyDate(new Date());
		attachment.setModifer(UserContext.getUserId());
		this.insertOrUpdate(attachment);
		return attachment;
	}

	@Override
	public List<AeAttachment> getUploadFiles() {
		EntityWrapper wrapper = new EntityWrapper();
		wrapper.isNotNull("file_id");
		//通过AND拼接，is_encrypt为0或者为空
		wrapper.andNew("is_encrypt = 0 or is_encrypt is null");
		wrapper.eq("using_sign",1);
		return this.selectList(wrapper);
	}

	@Override
	public List<String> getRepeatIds(){
		EntityWrapper<AeAttachment> ew = new EntityWrapper<>();
		ew.setSqlSelect("max(id) as id, LISTAGG(id, ',') WITHIN GROUP(ORDER BY id) as ids, file_size, file_name")
				.gt("file_size", 0)
				.isNotNull("basic_id")
				.eq("using_sign", 1)
				.eq("is_encrypt", 1)
				.groupBy("file_size, file_name");
		List<Map<String, Object>> result = this.selectMaps(ew);
		// 返回map中的id,ids,需要判断id是否存在于ids中，存在则从ids中删除，其余的ids拼接到一起返回，用于删除
		List<String> repeatIds = Lists.newArrayList();
		for(Map<String, Object> curMap: result) {
			String ids = curMap.get("ids").toString();
			String[] idArray = ids.split(",");
			List<String> idList = Lists.newArrayList(idArray);
			String id = curMap.get("id").toString();
			if(idList.contains(id)) {
				idList.remove(id);
				repeatIds.addAll(idList);
			}
		}
		return repeatIds;

	}


}

