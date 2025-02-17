package com.aier.cloud.biz.ui.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.based.DcgOprTypeCondition;

@FeignClient(name="aier-service-based")
public interface OprTypeService {
	
	@RequestMapping(value="/api/based/dcgOprType/findByParams", method=RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> findByParams(@RequestBody DcgOprTypeCondition condition);
	
	
	@RequestMapping(value="/api/based/dcgOprType/getForTree", method=RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> getForTree(@RequestBody DcgOprTypeCondition condition);
	

}
