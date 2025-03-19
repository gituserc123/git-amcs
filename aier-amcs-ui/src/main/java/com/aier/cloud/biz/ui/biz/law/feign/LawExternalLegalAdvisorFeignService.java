package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.api.amcs.law.domain.LawExternalLegalAdvisor;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 外聘法律顾问信息 Feign 客户端接口
 */
@FeignClient(name = "aier-amcs-service")
public interface LawExternalLegalAdvisorFeignService {

    /**
     * 根据ID查询外聘法律顾问信息
     *
     * @param id 外聘法律顾问信息的ID
     * @return 外聘法律顾问信息实体
     */
    @PostMapping(value = "/api/service/biz/amcs/law/externalLegalAdvisor/getLawExternalLegalAdvisor")
    @ResponseBody
    LawExternalLegalAdvisor getLawExternalLegalAdvisor(@RequestParam("id") Long id);

    /**
     * 根据条件查询外聘法律顾问信息列表
     *
     * @param condition 查询条件
     * @return 分页结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/externalLegalAdvisor/findListByCond", method = {RequestMethod.POST})
    @ResponseBody
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawExternalLegalAdvisorCondition condition);

    /**
     * 保存或更新外聘法律顾问信息
     *
     * @param lawExternalLegalAdvisor 外聘法律顾问信息实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/externalLegalAdvisor/save", method = {RequestMethod.POST})
    @ResponseBody
    Map<String, Object> save(@RequestBody LawExternalLegalAdvisor lawExternalLegalAdvisor);
}