package com.aier.cloud.biz.service.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.sys.Institution;

@FeignClient(name="aier-service-system")
public interface InstitutionService {
	
	@RequestMapping(value = "/api/sys/inst/getInstByConditionForSelect", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map> getInstByConditionForSelect(@RequestBody InstCondition sc);
	
	
	@RequestMapping(value = "/api/sys/inst/getNamesByIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<Long,String> getNamesByIds(@RequestBody List<Long> ids);
	
	 /**
     * 根据机构类型获取机构列表
     * @param instEnum
     * @return
     */
    @RequestMapping(value = "/api/sys/inst/getListByInstType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Institution> getListByInstType(@RequestParam("instEnum") InstEnum instEnum);
    
    /**
     * 根据父id查询下属机构
     * @param instId
     * @return
     */
    @RequestMapping(value = "/api/sys/inst/getInstByParent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Object getInstByParent(@RequestParam(value="instId",defaultValue="1")Long instId);
    
	@RequestMapping(value = "/api/sys/inst/getAllHospital", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<Map<String,Object>> getAllHospital();
	
	//根据机构ID获取省区信息
    @PostMapping(value = "/api/sys/inst/getProvince")
    List<Map> getProvince(@RequestBody InstCondition c) ;
	
	
	/**
     * 根据租户code查询Institution机构信息实体
     * @param ahisHosp
     * @return
     */
    @RequestMapping(value = "/api/sys/inst/getByAhisHosp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Institution getByAhisHosp(@RequestParam("ahisHosp") Integer ahisHosp);

    /**
     * 获取用户关联的所有医院信息，登录时，通过输入登录工号获取
     * @param staffCode
     * @return
     */
    @RequestMapping(value = "/api/sys/inst/getListByStaffCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Institution> getListByStaffCode(@RequestParam("staffCode") String staffCode);

}
