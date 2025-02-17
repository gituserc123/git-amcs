package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AttachmentCondition;
import com.aier.cloud.api.amcs.adverse.domain.Attachment;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;


@FeignClient(name = "aier-amcs-service")
public interface AeAttachmentService {
	
	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/findByFileId")
    @ResponseBody
    public Attachment findByFileId(@RequestParam("fileId")String fileId);
	
	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/delByFileId")
    @ResponseBody
	public boolean delByFileId(@RequestParam("fileId")String fileId);
	
	
	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/saveAttachment")
	ResponseEntity<Boolean> saveAttachment(@RequestBody AttachmentCondition cond);
	
	
	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/findByCond")
    @ResponseBody
	public PageResponse<Map<String, Object>> findByCond(@RequestBody AttachmentCondition cond);
	
	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/hasEmr")
	@ResponseBody
	public boolean hasEmr(@RequestParam("id") Long id);

	@RequestMapping("/api/service/biz/amcs/adverse/aeAttachment/queryByCond")
	@ResponseBody
	List<Map<String, Object>> queryByCond(@RequestBody AttachmentCondition cond);

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeAttachment/saveOrModify")
	@ResponseBody Attachment saveOrModify(@RequestBody Attachment attachment);


	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeAttachment/getUploadFiles")
	@ResponseBody List<Attachment> getUploadFiles();

	@RequestMapping(value = "/api/service/biz/amcs/adverse/aeAttachment/getRepeatIds")
	@ResponseBody List<String> getRepeatIds();


}
