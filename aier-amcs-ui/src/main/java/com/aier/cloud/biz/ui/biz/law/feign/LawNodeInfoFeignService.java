package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.domain.LawNodeInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "aier-amcs-service")
public interface LawNodeInfoFeignService {

    @PostMapping(value = "/api/service/biz/amcs/law/nodeInfo/getNodeByCode")
    @ResponseBody
    LawNodeInfo getNodeByCode(@RequestParam("nodeCode") String nodeCode);

    @PostMapping(value = "/api/service/biz/amcs/law/nodeInfo/getAllNodeInfo")
    @ResponseBody
    List<LawNodeInfo> getAllNodeInfo();
}
