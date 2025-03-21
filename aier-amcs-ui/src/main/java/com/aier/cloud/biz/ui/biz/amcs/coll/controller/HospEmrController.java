package com.aier.cloud.biz.ui.biz.amcs.coll.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.api.amcs.condition.CollCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.amcs.coll.feign.HospEmrService;

/**
 * 医院电子病例应用情况信息采集
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:37
 */
@Controller
@RequestMapping("/ui/amcs/coll/hosp/emr")
public class HospEmrController extends BaseController {
	private final static String LIST_PAGE = "amcs/coll/hospEmrList";
	
	@Autowired
	private HospEmrService hospEmrService;
	
	@RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
		request.setAttribute("hospId", ShiroUtils.getTenantId());
		request.setAttribute("hospName", ShiroUtils.getInstName());
        return LIST_PAGE;
    }
	
	@RequestMapping(value = "/findList", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> findList(CollCondition cond) {
		if(ObjectUtils.isEmpty(cond.getHospId())) {
			cond.setHospId(ShiroUtils.getTenantId());
		}
		return hospEmrService.findList(cond);
    }
	
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	 public @ResponseBody
	 Map<String, Object> findByBasicId( @RequestParam Long id) {
		 return hospEmrService.findById(id);
	 }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody Map<String, Object> mData) {
		return hospEmrService.save(mData);
	}
}
