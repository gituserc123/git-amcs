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
package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AttachmentCondition;
import com.aier.cloud.api.amcs.adverse.domain.Attachment;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeAttachmentService;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeAttachment;
 
/**
 * AeAttachment
 * 
 * @author 爱尔眼科
 * @since 2022-10-31 14:57:42
 */
@Api("相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment")
public class AeAttachmentController extends BaseController{
 
	@Autowired
	private AeAttachmentService aeAttachmentService;
	
	@ApiOperation(value="根据id查询AeAttachment实体")
	@ApiParam(name="id", value="的id", required=true)
	@RequestMapping(value = "/getAeAttachment")
	public @ResponseBody AeAttachment getAeAttachment(@RequestParam("id") Long id) {
		return aeAttachmentService.selectById(id);
	}
	
	@ApiParam(name="fileId", value="文件ID", required=true)
	@RequestMapping(value = "/findByFileId")
	public AeAttachment findByFileId(@RequestParam("fileId")String fileId) {
		return aeAttachmentService.findByFileId(fileId);
	}
	
	@RequestMapping(value="/findByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> findByCond(@RequestBody AttachmentCondition cond) {
		List<Map<String, Object>> listData = aeAttachmentService.findByCond(cond);
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, listData);
	}


	@RequestMapping(value = "/delByFileId")
	public ResponseEntity<Boolean> delByFileId(@RequestParam("fileId")String fileId) {
		boolean result = aeAttachmentService.deleteById(Long.parseLong(fileId));
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/saveAttachment")
	public @ResponseBody boolean saveAttachment(@RequestBody AttachmentCondition cond) {
	    List<Long> ids = Lists.newArrayList();
		
		List<Map<String, Object>> uploadedList = aeAttachmentService.findByCond(cond);
		for(Map<String, Object> curUploaded: uploadedList ) {
			ids.add(Long.parseLong(curUploaded.get("id").toString()));
		}
		if(ids.size() > 0) {
			aeAttachmentService.deleteBatchIds(ids);
		}
		
		List<AeAttachment>  attachments = Lists.newArrayList();
		
		for(Attachment curAttachment: cond.getAttachments()) {
			AeAttachment attachment = new AeAttachment();
			BeanUtils.copyProperties(curAttachment, attachment);
			EntityUtils.addOperatorInfo(attachment);
			attachment.setIsEncrypt(1);
			attachments.add(attachment);
		}
		if(attachments.size() > 0) {
			aeAttachmentService.insertBatch(attachments);
		}
		
		return true;

	}
	
	@RequestMapping(value = "/hasEmr")
	public @ResponseBody boolean hasEmr(@RequestParam("id")Long id) {
		AttachmentCondition cond = new AttachmentCondition();
		cond.setBasicId(id);
		cond.setTag("emr");
		List<Map<String, Object>> listData = aeAttachmentService.findByCond(cond);
		if(listData.size() > 0) {
			return true;
		}else {
			return false;
		}
		
	}

	@RequestMapping(value="/queryByCond")
	@ResponseBody
	public List<Map<String, Object>> queryByCond(@RequestBody AttachmentCondition cond) {
		List<Map<String, Object>> listData = aeAttachmentService.queryByCond(cond);
		return listData;
	}

	@RequestMapping(value = "/saveOrModify")
	public @ResponseBody Attachment saveOrModify(@RequestBody Attachment attachment){
		AeAttachment aeAttachment = new AeAttachment();
		BeanUtils.copyProperties(attachment, aeAttachment);
		AeAttachment oprAeAttachment = aeAttachmentService.saveOrModify(aeAttachment);
		BeanUtils.copyProperties(oprAeAttachment, attachment);
		return attachment;
	}

	@RequestMapping(value = "/getUploadFiles")
	public @ResponseBody List<AeAttachment> getUploadFiles(){
		return aeAttachmentService.getUploadFiles();
	}

	//获取repeats
	@RequestMapping(value = "/getRepeatIds")
	public @ResponseBody List<String> getRepeats(){
		return aeAttachmentService.getRepeatIds();
	}

}