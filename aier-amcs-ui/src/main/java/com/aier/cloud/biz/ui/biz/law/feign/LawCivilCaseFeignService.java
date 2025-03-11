package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.api.amcs.law.domain.LawCivilCase;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawCivilCaseFeignService {

    /**
     * 根据ID查询民事诉讼仲裁案件信息
     *
     * @param id 民事诉讼仲裁案件的ID
     * @return 民事诉讼仲裁案件信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/civilCase/getLawCivilCase")
    @ResponseBody
    LawCivilCase getLawCivilCase(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/service/biz/amcs/law/civilCase/findListByCond", method = { RequestMethod.POST })
    @ResponseBody
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawCivilCaseCondition condition);

    /**
     * 根据主键ID查询民事诉讼仲裁案件信息
     *
     * @param mainId 主键ID
     * @return 民事诉讼仲裁案件实体
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/civilCase/getByMainId", method = RequestMethod.GET)
    @ResponseBody
    LawCivilCase getByMainId(@RequestParam("mainId") Long mainId);

    /**
     * 查询最新的民事诉讼仲裁案件列表
     *
     * @return 民事诉讼仲裁案件列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/civilCase/lastList", method = RequestMethod.GET)
    @ResponseBody
    List<Map<String, Object>> lastList();

    /**
     * 根据条件查询民事诉讼仲裁案件列表
     *
     * @param condition 查询条件
     * @return 民事诉讼仲裁案件列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/civilCase/findListByCond", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> findListByCond(@RequestBody Map<String, Object> condition);

    /**
     * 保存或更新民事诉讼仲裁案件信息
     *
     * @param lawCivilCase 民事诉讼仲裁案件实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/civilCase/save",method = { RequestMethod.POST })
    @ResponseBody
    Map<String, Object> save(@RequestBody LawCivilCase lawCivilCase);
}