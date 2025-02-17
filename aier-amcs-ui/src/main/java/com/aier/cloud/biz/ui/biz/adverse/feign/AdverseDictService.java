package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.domain.AeDictType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "aier-amcs-service")
public interface AdverseDictService {
	
	/**
	 * 查码表
	 * @param paraType
	 * @param filter 过滤条件
	 * @return
	 */
    @RequestMapping("api/amcs/adverse/aeDictType/getList")
    List<Map> getList(@RequestParam("paraType") String paraType, @RequestParam("filter") String filter);

	@RequestMapping("api/amcs/adverse/aeDictType/getListByFilters")
	List<Map> getListByFilters(@RequestParam("paraType") String paraType, @RequestParam("filters") String filters);
    
    @RequestMapping("api/amcs/adverse/aeDictType/getMoreList")
    Map<String,List> getMoreList(@RequestParam("paraType[]") List<String> paraType);

	@RequestMapping("api/amcs/adverse/aeDictType/save")
	Boolean save(AeDictType aeDictType);

	@RequestMapping("api/amcs/adverse/aeDictType/del")
	Boolean del(@RequestParam("id")Long id);

}
