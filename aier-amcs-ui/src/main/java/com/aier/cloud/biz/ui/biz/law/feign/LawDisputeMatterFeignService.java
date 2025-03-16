package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.api.amcs.law.domain.LawDisputeMatter;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawDisputeMatterFeignService {

    /**
     * 根据ID查询纠纷事项信息
     *
     * @param id 纠纷事项的ID
     * @return 纠纷事项信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/disputeMatter/getLawDisputeMatter")
    @ResponseBody
    LawDisputeMatter getLawDisputeMatter(@RequestParam("id") Long id);

    /**
     * 根据条件查询纠纷事项列表
     *
     * @param condition 查询条件
     * @return 分页响应
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/disputeMatter/findListByCond", method = { RequestMethod.POST })
    @ResponseBody
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawDisputeMatterCondition condition);

    /**
     * 根据条件查询纠纷事项列表
     *
     * @param condition 查询条件
     * @return 纠纷事项列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/disputeMatter/findListByCond", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> findListByCond(@RequestBody Map<String, Object> condition);

    /**
     * 保存或更新纠纷事项信息
     *
     * @param lawDisputeMatter 纠纷事项实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/disputeMatter/save", method = { RequestMethod.POST })
    @ResponseBody
    Map<String, Object> save(@RequestBody LawDisputeMatter lawDisputeMatter);
}