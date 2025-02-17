package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOperationRecordService;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;


@Api("审核信息接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeOperationRecord")
public class AeOperationRecordController extends BaseController{
	
	@Autowired
	private AeOperationRecordService aeOperationRecordService;
	
	@RequestMapping(value="/findOpinionList")
	@ResponseBody
	public PageResponse<Map<String, Object>> findOpinionList(@RequestBody AeInfoCondition cond){
		add("modifer", "t_sys_staff|id|name", "modifyName");
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page,aeOperationRecordService.findOpinionList(cond));
	}
	
	@RequestMapping(value="/findOpinionForReview")
	@ResponseBody
	public Map<String, Object> findOpinionForReview(@RequestBody AeInfoCondition cond) {
		 return aeOperationRecordService.findOpinionByReview(cond);
	}

	@RequestMapping(value="/findOperator")
	@ResponseBody
	public Long findOperator(@RequestParam("id")Long id, @RequestParam("node")Integer node, @RequestParam("type")Integer type){
		return aeOperationRecordService.findOperator(id,node,type);
	}
	
	@RequestMapping(value="/hasGroupLook")
	@ResponseBody
	public Boolean hasGroupLook(@RequestParam("basicId")Long basicId){
		return aeOperationRecordService.hasGroupLook(basicId);
	}
	
	
	@RequestMapping(value="/findLookList")
	@ResponseBody
	public List<AeOperationRecord> findLookList(@RequestParam("basicIds") List<Long> basicIds){
		return aeOperationRecordService.findLookList(basicIds);
	}
	
	@RequestMapping(value="/findReviewList")
	@ResponseBody
	public List<AeOperationRecord> findReviewList(@RequestParam("basicIds") List<Long> basicIds, @RequestParam("node") Integer node){
		return aeOperationRecordService.findReviewList(basicIds, node);
	}
	
	
	@RequestMapping(value="/saveOpinion")
	@ResponseBody
	public Integer saveOpinion(@RequestBody Map<String, Object> mOpinionInfo){
		return aeOperationRecordService.saveOpinion(mOpinionInfo);
	}
	
	@RequestMapping(value="/getOperatorRecord")
	@ResponseBody
	public AeOperationRecord getOperatorRecord(@RequestBody AeInfoCondition cond){
		return aeOperationRecordService.getOperatorRecord(cond);
	}

}