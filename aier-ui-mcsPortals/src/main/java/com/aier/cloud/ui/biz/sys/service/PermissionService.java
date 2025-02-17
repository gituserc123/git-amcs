package com.aier.cloud.ui.biz.sys.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.response.domain.sys.Permission;


/**
 * 权限操作服务客户端接口
 * @author rain_deng
 * @since 2018年3月14日 上午10:11:26
 */
@FeignClient(name="aier-service-system")
public interface PermissionService {
    
    /**
     *  获取系统所有操作
     * @return
     */
    @RequestMapping(value = "/api/sys/permission/getAllPermissions", method = RequestMethod.POST)
    List<Permission> getAllPermissions(@RequestParam("platformCode") String platformCode);
    
    /**
     * 获取用户当前机构的所有角色集合下的所有操作权限
     * 
     * @param staffId 用户id
     * @param instId  机构id，是指的医院，不是科室
     * @return List<Permission>
     */
    @RequestMapping(value = "/api/sys/permission/selectListByUserAndInst", method = RequestMethod.POST)
    List<Permission> selectListByUserAndInst(@RequestParam("staffId") Long staffId, @RequestParam("instId") Long instId, @RequestParam("platformCode") String platformCode);
    
    /**
     * 门户登录鉴权
     * @param staffId
     * @param instId
     * @return
     */
    @RequestMapping(value = "/api/sys/permission/selectAllListByPortal", method = RequestMethod.POST)
    List<Permission> selectAllListByPortal(@RequestParam("staffId") Long staffId, @RequestParam("instId") Long instId);
    
    /**
     * 根据模块id获取下面的所有操作
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/api/sys/permission/getListByModuleId", method = RequestMethod.POST)
    List<Permission> getListByModuleId(@RequestParam("moduleId") Long moduleId);
    
    /**
     * 根据用户id获取用户在所有平台的权限数量
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/api/sys/permission/getPlatFormListByStaff", method = RequestMethod.POST)
    List<String> getPlatFormListByStaff(@RequestParam("staffId") Long staffId);
    
}
