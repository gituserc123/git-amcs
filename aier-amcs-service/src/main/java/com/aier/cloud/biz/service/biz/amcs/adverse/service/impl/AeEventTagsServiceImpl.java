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

import io.micrometer.core.instrument.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeEventTagsService;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeDictTypeMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeEventTagsMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeEventTags;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDictType;

/**
 * AeEventTags
 * 事件标签关联表
 * @author 爱尔眼科
 * @since 2023-03-10 17:12:11
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeEventTagsServiceImpl extends ServiceImpl<AeEventTagsMapper, AeEventTags> implements AeEventTagsService {
	
	@Resource
    AeDictTypeMapper dictTypeMapper;

	@Override
	public List<AeEventTags> findListByEventId(Long eventId) {
		// TODO Auto-generated method stub
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.eq("event_id", eventId);
		ew.eq("using_sign", 1);
        return this.baseMapper.selectList(ew);
	}

	@Override
	public String findTagsById(Long eventId) {
		// TODO Auto-generated method stub
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.eq("event_id", eventId);
		ew.eq("using_sign", 1);
		String tags = "";
        List<AeEventTags> tagList = this.baseMapper.selectList(ew);
        for(AeEventTags curEventTags: tagList) {
        	tags = tags.concat(",").concat(curEventTags.getTagCode());
        }
        if(!ObjectUtils.isEmpty(tags)) tags = tags.substring(1);
        
        return tags;
	}

	@Override
	public String findTagDescsById(Long eventId) {
		// TODO Auto-generated method stub
		EntityWrapper<AeEventTags> ew = new EntityWrapper<>();
		ew.eq("event_id", eventId);
		ew.eq("using_sign", 1);
		String tagCodes = "";
		String tagDescs = "";
        List<AeEventTags> tagList = this.baseMapper.selectList(ew);
        
        EntityWrapper<AeDictType> dictEw = new EntityWrapper<>();
        dictEw.eq("type_code", "tag_type");
        List<AeDictType> dictList = this.dictTypeMapper.selectList(dictEw);
          
        for(AeEventTags curEventTags: tagList) {
        	tagCodes = tagCodes.concat(",").concat(curEventTags.getTagCode());
        }
        
        for(AeDictType curDict: dictList) {
        	if(tagCodes.indexOf(curDict.getValueCode()) > 0) {
        		tagDescs = tagDescs.concat(",").concat(curDict.getValueDesc());
        	}
        }
        
        if(!ObjectUtils.isEmpty(tagDescs)) tagDescs = tagDescs.substring(1);
        
        return tagDescs;
	}

	@Override
	public List<AeEventTags> findListByCond(AeInfoCondition cond) {
		// TODO Auto-generated method stub
		return this.baseMapper.findListByCond(cond);
	}
	
	

}

