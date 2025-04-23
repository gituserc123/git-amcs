package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawIntegrityFilingCondition;
import com.aier.cloud.api.amcs.law.domain.LawIntegrityFiling;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawIntegrityFilingFeignService {

    /**
     * 根据ID查询廉洁合规报备信息
     *
     * @param id 廉洁合规报备的ID
     * @return 廉洁合规报备信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/integrityFiling/getLawIntegrityFiling")
    @ResponseBody
    LawIntegrityFiling getLawIntegrityFiling(@RequestParam("id") Long id);

    /**
     * 根据条件查询廉洁合规报备列表
     *
     * @param condition 查询条件
     * @return 廉洁合规报备列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/integrityFiling/findListByCond", method = { RequestMethod.POST })
    @ResponseBody
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawIntegrityFilingCondition condition);

    @RequestMapping(value = "/api/service/biz/amcs/law/integrityFiling/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody Object saveOrUpdate(@RequestBody LawIntegrityFiling lawIntegrityFiling);

    /**
     * 根据前缀查询事件编号的数量
     *
     * @param
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/integrityFiling/getEventSnCount", method = {RequestMethod.POST})
    @ResponseBody
    Integer getEventSnCount(@RequestParam("prefixEventSn") String prefixEventSn,@RequestParam("hospId") Long hospId);



    /**
     * 保存或更新
     *
     * @param lawIntegrityFiling 行政处罚事项实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/integrityFiling/save", method = { RequestMethod.POST })
    @ResponseBody
    Map<String, Object> save(@RequestBody LawIntegrityFiling lawIntegrityFiling);

}