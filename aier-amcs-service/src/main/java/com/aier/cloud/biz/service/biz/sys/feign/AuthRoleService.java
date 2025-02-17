package com.aier.cloud.biz.service.biz.sys.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="aier-service-system")
public interface AuthRoleService {

    @PostMapping(value = "/api/sys/authRole/getPermByStaffId")
    List getPermByStaffId(@RequestParam(value="staffId") Long staffId, @RequestParam(value="platformCode") String platformCode,
                                               @RequestParam(value="type", required = false, defaultValue = "ALL") String type);
}

