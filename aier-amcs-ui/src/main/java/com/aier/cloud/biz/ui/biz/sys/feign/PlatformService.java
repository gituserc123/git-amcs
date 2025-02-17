package com.aier.cloud.biz.ui.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;


/**
 * 报表权限
 * @author xiaokek
 * @since 2018年5月24日 下午4:54:28
 */
@FeignClient(name="aier-service-system")
public interface PlatformService {

    @RequestMapping(value = "/api/sys/platform/list", method = RequestMethod.POST)
	List<Map> list();

}
