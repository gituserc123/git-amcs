package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.api.amcs.law.domain.LawAdminPenalty;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawAdminPenaltyFeignService {

    /**
     * 根据ID查询行政处罚事项信息
     *
     * @param id 行政处罚事项的ID
     * @return 行政处罚事项信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/adminPenalty/getLawAdminPenalty")
    @ResponseBody
    LawAdminPenalty getLawAdminPenalty(@RequestParam("id") Long id);

    /**
     * 根据条件查询行政处罚事项列表
     *
     * @param condition 查询条件
     * @return 分页响应
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/adminPenalty/findListByCond", method = { RequestMethod.POST })
    @ResponseBody
    PageResponse<Map<String, Object>> findListByCond(@RequestBody LawAdminPenaltyCondition condition);

    /**
     * 根据主键ID查询行政处罚事项信息
     *
     * @param mainId 主键ID
     * @return 行政处罚事项实体
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/adminPenalty/getByMainId", method = RequestMethod.GET)
    @ResponseBody
    LawAdminPenalty getByMainId(@RequestParam("mainId") Long mainId);

    /**
     * 查询最新的行政处罚事项列表
     *
     * @return 行政处罚事项列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/adminPenalty/lastList", method = RequestMethod.GET)
    @ResponseBody
    List<Map<String, Object>> lastList();

    /**
     * 根据条件查询行政处罚事项列表
     *
     * @param condition 查询条件
     * @return 行政处罚事项列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/adminPenalty/findListByCond", method = RequestMethod.POST)
    @ResponseBody
    List<Map<String, Object>> findListByCond(@RequestBody Map<String, Object> condition);

    /**
     * 保存或更新行政处罚事项信息
     *
     * @param lawAdminPenalty 行政处罚事项实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/adminPenalty/save", method = { RequestMethod.POST })
    @ResponseBody
    Map<String, Object> save(@RequestBody LawAdminPenalty lawAdminPenalty);
}