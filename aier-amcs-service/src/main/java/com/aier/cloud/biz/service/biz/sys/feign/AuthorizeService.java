package com.aier.cloud.biz.service.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.api.request.condition.sys.AuthorizeCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Role;


/**
 * 角色服务
 * @author rain_deng
 * @since 2018年4月9日 下午4:40:39
 */
@FeignClient(name="aier-service-system")
public interface AuthorizeService{

    
    /**
	 * 获取角色值List
     * @param r 
	 * @return
	 */
    @RequestMapping(value="/api/sys/authorize/getRoleForTree", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> getRoleForTree(@RequestParam("platformCode") String platformCode);
    
    
    /**
     * 获取医院角色List
     * @param r 
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getHospRoleForTree", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> getHospRoleForTree(@RequestParam("platformCode") String platformCode);
    
    /**
     * 获取当前用户当前机构的角色组
     * @param staffId
     * @param instId
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getRoleTreeByStaffInst", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> getRoleTreeByStaffInst(@RequestParam("staffId") Long staffId, @RequestParam("instId")  Long instId, @RequestParam("platformCode") String platformCode);
    
    /**
     * 获取当前用户当前医院的角色组
     * @param staffId
     * @param instId
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getRoleTreeByStaffInstHosp", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> getRoleTreeByStaffInstHosp(@RequestParam("staffId")  Long staffId, @RequestParam("instId")  Long instId, @RequestParam("platformCode") String platformCode);
    
    
    /**
     * 获取角色详细信息
     * @param roleId
     * @return Role
     */
    @RequestMapping(value="/api/sys/authorize/getRoleById", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Role getRoleById(@RequestParam("roleId") Long roleId);
    
    /**
     * 获取当前角色下，某医院的所有用户
     * @param authorizeCondition
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getListByRoleAuthorize", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Map<String,Object>> getListByRoleAuthorize(@RequestBody AuthorizeCondition authorizeCondition);
    
    /**
     * 获取还未授权当前医院当前角色的用户
     * @param authorizeCondition
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getListNotAuthorize", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> getListNotAuthorize(@RequestBody AuthorizeCondition authorizeCondition);
    
    /**
     * 查询出用户列表在某个医院的多个角色，多个科室
     * @param authorizeCondition
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/getListByStaffAuthorize", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Map<String,Object>> getListByStaffAuthorize(@RequestBody AuthorizeCondition authorizeCondition);
    
    /**
     * 查看当前用户在某医院的所有访问权限
     * @param staffId
     * @param instId
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/lookUpAuthorize", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String,Object>> lookUpAuthorize(@RequestParam("staffId")Long staffId, @RequestParam("instId") Long instId, @RequestParam("platformCode") String platformCode);
    
    
    /**
     * 当前角色对用户授权
     * @param roleId
     * @param instId
     * @param staffIds
     * @return Boolean
     */
    @RequestMapping(value="/api/sys/authorize/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean create(@RequestParam("roleId")Long roleId, @RequestParam("instId") Long instId, @RequestParam("staffIds") String staffIds, @RequestParam("platformCode") String platformCode);
    
    /**
     * 当前用户对角色授权
     * @param roleIds
     * @param staffId
     * @param instId
     * @return Boolean
     */
    @RequestMapping(value="/api/sys/authorize/update", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean update(@RequestParam("staffId")Long staffId, @RequestParam("instId") Long instId, @RequestParam(name="roleIds", defaultValue = "") String roleIds, @RequestParam("platformCode") String platformCode);
    
    /**
     * 解除角色授权
     * @param id
     * @return
     */
    @RequestMapping(value="/api/sys/authorize/delete", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean delete(@RequestParam("id") Long id);

    /**
     * 批量保存用户跟角色关系
     * 批量保存 用户角色关系  staffIds 为员工id字符串，多个员工中间用 , 分割
     */
    @PostMapping(value = "/api/sys/authorize/createProvinceRole")
    Boolean createProvinceRole(@RequestParam("roleId")Long roleId, @RequestParam("staffIds") String staffIds);

    /**
     * 用户撤销省区角色授权
     * @param roleId
     * @param staffId
     * @return
     */
    @PostMapping(value = "/api/sys/authorize/cancelStaffProvinceRole")
    Boolean cancelStaffProvinceRole(@RequestParam("roleId")Long roleId, @RequestParam("staffId") Long staffId);



    /**
     * 获取用户当前医院所有角色的编号名称合集
     * @param staffId
     * @param instId
     * @return
     */
    @PostMapping(value = "/api/sys/authorize/selectRolesByStaffIdAndInstId")
    List<Map<String,Object>>  selectRolesByStaffIdAndInstId(@RequestParam("staffId") Long staffId, @RequestParam("instId") Long instId);

}
