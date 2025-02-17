package com.aier.cloud.biz.ui.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 参数服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-service-based")
public interface DictService {

	/**
	 * 查码表
	 * @param paraType
	 * @param filter 过滤条件
	 * @return
	 */
    @RequestMapping("/api/based/dict/getList")
    List<Map> getList(@RequestParam("paraType") String paraType, @RequestParam("filter") String filter);
    
    /**
	 * 查很多个码表
	 * @param paraType
	 * @return
	 */
    @RequestMapping("/api/based/dict/getMoreList")
    Map<String,List> getMoreList(@RequestParam("paraType[]") List<String> paraType);

	/**
	 * 查询单个码表
	 * @param type
	 * @param filter
	 * @return
	 */
	@RequestMapping(value="/api/based/dcgCodeDict/getCodeDictList", method=RequestMethod.POST)
	List<Map> getCodeDictList(@RequestParam(value = "type") String type, @RequestParam(value = "filter", defaultValue = "") String filter);
}
