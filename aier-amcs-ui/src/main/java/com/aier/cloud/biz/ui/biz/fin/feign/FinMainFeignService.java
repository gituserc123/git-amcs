package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsMain;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-04-03 14:11
 **/

@FeignClient(name = "aier-amcs-service")
public interface FinMainFeignService {
    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMain/getList")
    @ResponseBody
    List<Map<String,Object>> getList(FinInsMainCondition cond);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMain/save")
    @ResponseBody
    Boolean save(FinInsMain finInsMain);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMain/getFinInsMain")
    @ResponseBody
    FinInsMain getFinInsMain(@RequestParam("id") Long id);

    @PostMapping(value = "/api/service/biz/amcs/fin/finInsMain/queryListByCond")
    @ResponseBody
    PageResponse<Map<String, Object>> queryListByCond(@RequestBody FinInsMainCondition m);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finInsMain/queryListByCondNoPage")
    @ResponseBody
    List<Map<String, Object>> queryListByCondNoPage(@RequestBody FinInsMainCondition cond);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMain/lastList")
    @ResponseBody
    List<Map<String,Object>> lastList();
}
