package com.aier.cloud.biz.ui.biz.common.feign;

import java.util.List;
import java.util.Map;

import com.aier.cloud.basic.api.request.condition.based.DcgDrugsCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.based.CodeDictCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;


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
    
	@RequestMapping(value = "/api/based/dcgCodeDict/getList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> getList(CodeDictCondition condition);
    
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


	@RequestMapping(value = "/api/based/dictType/getAllSubListByRootParent")
	public @ResponseBody List getAllSubListByRootParent(@RequestParam("parentTypeCode[]") List<String> parentTypeCode);

	@RequestMapping(value = "/api/based/dcgMedicalService/query")
	public @ResponseBody PageResponse<Map> dcgMedicalService(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dcgMaterial/query")
	public @ResponseBody PageResponse<Map> dcgMaterial(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dcgDrugs/query")
	public @ResponseBody PageResponse<Map> dcgDrugs(@RequestBody Map m);
	
	@RequestMapping(value = "/api/based/dchMedicalService/query")
	public @ResponseBody PageResponse<Map> dchMedicalService(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dchMaterial/query")
	public @ResponseBody PageResponse<Map> dchMaterial(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dchDrugs/query")
	public @ResponseBody PageResponse<Map> dchDrugs(@RequestBody Map m);

    @PostMapping(value = "/api/aps/dcgMeritsLevel/page")
    PageResponse<Map<String, Object>> getDcgMeritsLevel(@RequestBody Map moduleCondition);

	// 药品字典
	@RequestMapping(value="/api/based/dcgDrugs/query", method=RequestMethod.POST)
	@ResponseBody
	PageResponse<Map<String, Object>> queryDcgDrugs(@RequestBody DcgDrugsCondition dcgDrugsCondition);
}
