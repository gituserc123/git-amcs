package com.aier.cloud.ui.biz.sys.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.domain.sys.Platform;
import com.aier.cloud.basic.api.request.domain.sys.SysPlatformInst;


/**
 * 报表权限
 * @author xiaokek&raindeng
 * @since 2018年5月24日 下午4:54:28
 */
@FeignClient(name="aier-service-system")
public interface PlatformService {

    @RequestMapping(value = "/api/sys/platform/list", method = RequestMethod.POST)
	List<Platform> list();
    
    /**
	 * 获取平台列表
	 * @param platType  平台类型，0:医疗云平台，1:第三方云平台；2:移动端云平台
	 * @return
	 */
   @RequestMapping(value = "/api/sys/platform/listByType", method = RequestMethod.POST)
   List<Platform> listByType(@RequestParam("platType") Integer platType);
   
   
   
   @RequestMapping(value = "/api/sys/platform/listByTypeAndCloud", method = RequestMethod.POST)
   List<Platform> listByTypeAndCloud(@RequestParam("platType") Integer platType, @RequestParam("cloudType") String cloudType);
    
    /**
     * 根据租户id 获取 关联的平台信息
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "/api/sys/platform/getPlatformsByTenantId", method = RequestMethod.POST)
    List<Platform> getPlatformsByTenantId(@RequestParam("tenantId") Long tenantId);
    
    @RequestMapping(value = "/api/sys/platform/getPlatformsByTenantIdAndCloudType", method = RequestMethod.POST)
    List<Platform> getPlatformsByTenantIdAndCloudType(@RequestParam("tenantId") Long tenantId, @RequestParam("cloudType") String cloudType);
    
    @RequestMapping(value = "/api/sys/platformInst/getSysPlatformInstList", method = RequestMethod.POST)
    List<SysPlatformInst> getSysPlatformInstList(@RequestParam("tenantId") Long tenantId);

}
