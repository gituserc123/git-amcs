package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.api.amcs.law.domain.LawCriminalCase;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawCriminalCaseFeignService {



    /**
     * 根据ID查询民事诉讼仲裁案件信息
     *
     * @param id 民事诉讼仲裁案件的ID
     * @return 民事诉讼仲裁案件信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/criminalCase/getLawCriminalCase")
    @ResponseBody
    LawCriminalCase getLawCriminalCase(@RequestParam("id") Long id);

    /**
     * 根据条件分页查询刑事案件列表
     *
     * @param condition 查询条件
     * @return 分页结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/criminalCase/findListByCond", method = RequestMethod.POST)
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawCriminalCaseCondition condition);

    /**
     * 保存或更新刑事案件信息
     *
     * @param criminalCase 案件实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/criminalCase/save", method = RequestMethod.POST)
    Map<String, Object> save(@RequestBody LawCriminalCase criminalCase);


}
