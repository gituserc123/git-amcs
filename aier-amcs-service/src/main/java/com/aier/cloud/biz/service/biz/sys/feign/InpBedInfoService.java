package com.aier.cloud.biz.service.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.inp.InpBedInfoCondition;
import com.aier.cloud.basic.api.request.domain.inp.InpBedInfo;

/**
 * 床位信息
 * 
 * @author lc
 * @since 2019/8/14 10:19
 */
@FeignClient(name = "aier-service-medical")
public interface InpBedInfoService {

	/**
	 * 分页列表
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/selectPageBy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> selectPageBy(InpBedInfoCondition condition);

	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Boolean save(InpBedInfo entity);

	/**
	 * 根据ID查信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/getById", method = RequestMethod.POST)
	InpBedInfo getById(@RequestParam("id") Long id);

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Boolean update(InpBedInfo entity);

	/**
	 * 检查唯一性
	 * 
	 * @param wardId
	 * @param bedNumber
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/checkUnique", method = RequestMethod.POST)
	List<InpBedInfo> checkUnique(@RequestParam("wardId") Long wardId, @RequestParam("bedNumber") String bedNumber);

	/**
	 * 根据病房ID查病房类型
	 * 
	 * @param wardId
	 * @return
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/getByWarId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map<String, Object>> getByWarId(@RequestParam("wardId") Long wardId);

	/**
	 * 根据参数类查询床位信息
	 * 
	 * @param condition
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/getListBycondition", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Map<String, Object>> getListBycondition(@RequestBody InpBedInfoCondition condition);

	/**
	 * 根据病区编号查询空闲床位信息
	 * 
	 * @param wardId
	 * @return List<Map<String,Object>>
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/queryEmptyBedList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Map<String, Object>> getEmptyBedListByWardId(@RequestParam("wardId") Long wardId);

	/**
	 * countByHospId
	 * 
	 * @param hospId
	 * @return Integer
	 */
	@RequestMapping(value = "/api/inpnurse/inpBedInfo/countByHospId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer countByHospId(@RequestParam("hospId") Long hospId);
}