package com.aier.cloud.biz.ui.biz.common.feign;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.sys.AutoCompleteCondition;

public interface CommonService {
	List<Map<String,Object>> getAutoCompleteService(@RequestBody AutoCompleteCondition autoCompleteCondition);
	
    List<Map> getCustomList(@RequestParam("type") String paraType, @RequestParam("filter") String filter);
}
