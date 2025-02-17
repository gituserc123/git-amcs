package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeRecordService;

@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/record")
public class AeRecordController extends BaseController {
	
	@Autowired
	private AeRecordService aeRecordService;
	
	@RequestMapping(value = "/saveOpinion", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> saveOpinion(@RequestBody Map<String, Object> mData) {
		Integer result = aeRecordService.saveOpinion(mData);
		return this.success(result);
	}

}
