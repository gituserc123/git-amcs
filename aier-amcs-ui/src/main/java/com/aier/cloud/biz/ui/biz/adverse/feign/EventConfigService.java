package com.aier.cloud.biz.ui.biz.adverse.feign;

import com.aier.cloud.api.amcs.adverse.domain.EventConfig;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-18 11:18
 **/

@FeignClient(name="aier-amcs-service")
public interface EventConfigService {

    @RequestMapping("/api/service/biz/amcs/adverse/nodeConfig/getAll")
    PageResponse <Map<String, Object>> getAll(@RequestBody EventConfigCondition cond);

    @RequestMapping("/api/service/biz/amcs/adverse/nodeConfig/save")
    Boolean save(@RequestBody EventConfig eventConfig);

    @RequestMapping("/api/service/biz/amcs/adverse/nodeConfig/getNodeConfigByCode")
    EventConfig getNodeConfigByCode(@RequestParam("eventCode") String eventCode);
    
    @RequestMapping("/api/service/biz/amcs/adverse/nodeConfig/getPreNodeList")
    PageResponse<Map<String, Object>> getPreNodeList(@RequestBody AeInfoCondition cond);
}
