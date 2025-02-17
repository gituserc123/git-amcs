package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.adverse.domain.AeDictType;
import com.aier.cloud.api.amcs.fin.domain.FinDictType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@FeignClient(name = "aier-amcs-service")
public interface FinDictFeignService {
	
	/**
	 * 查码表
	 * @param paraType
	 * @param filter 过滤条件
	 * @return
	 */
    @RequestMapping("api/service/biz/amcs/fin/finDictType/getList")
    List<Map> getList(@RequestParam("paraType") String paraType);
    
    @RequestMapping("api/service/biz/amcs/fin/finDictType/getMoreList")
    Map<String,List> getMoreList(@RequestParam("paraType[]") List<String> paraType);

	@RequestMapping("api/service/biz/amcs/fin/finDictType/save")
	Boolean save(FinDictType finDictType);

	@RequestMapping("api/service/biz/amcs/fin/finDictType/del")
	Boolean del(@RequestParam("id")Long id);

	@RequestMapping("api/service/biz/amcs/fin/finDictType/getType")
	List getType(@RequestParam(value="hospId",required = false ) Long hospId);

}
