package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.sys.Institution;

@FeignClient(name="aier-service-system")
public interface InstService {
	
	@RequestMapping(value = "/api/sys/inst/getParentByHosp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Long, Object> getParentByHosp(@RequestBody Map<String, Object> params);

//	List<Map<String, Object>> list = [{hospId:9999}]
//	Map<String, Object> paramsMap = new HashMap<String, Object>();
//	paramsMap.put("hosps", list);
//	Map<Long, Object> resultMap = institutionService.getParentByHosp(paramsMap);

	@RequestMapping(value = "/api/sys/inst/getForTree", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map> getForTree(@RequestParam(value = "id") Long id);
	
	/**
     * 根据机构id 获取机构科室详细信息
     * @param instId
     * @return
     */
    @RequestMapping(value = "/api/sys/inst/getDeptDetailByInstId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Institution getDeptDetailByInstId(@RequestParam("instId") Long instId);
    
    @RequestMapping(value = "/api/sys/inst/getInstByConditionForSelect", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map> getInstByConditionForSelect(@RequestBody InstCondition sc);



}
