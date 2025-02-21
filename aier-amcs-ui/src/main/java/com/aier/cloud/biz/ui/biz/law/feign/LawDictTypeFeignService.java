package com.aier.cloud.biz.ui.biz.law.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawDictTypeFeignService {

    /**
     * 根据ID查询法务字典信息
     *
     * @param id 法务字典的ID
     * @return 法务字典信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/dictType/getLawDictType")
    @ResponseBody
    Map<String, Object> getLawDictType(@RequestParam("id") Long id);

    /**
     * 保存或更新法务字典信息
     *
     * @param lawDictType 法务字典实体
     * @return 操作结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/dictType/save", method = RequestMethod.POST)
    Boolean save(@RequestBody Map<String, Object> lawDictType);

    /**
     * 根据字典类型编码查询字典列表
     *
     * @param typeCode 字典类型编码
     * @return 字典列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/dictType/selectByTypeCode", method = RequestMethod.GET)
    @ResponseBody
    List<Map> selectByTypeCode(@RequestParam("typeCode") String typeCode);

    /**
     * 根据父级字典ID查询子字典列表
     *
     * @param parentId 父级字典ID
     * @return 子字典列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/dictType/selectSubDicts", method = RequestMethod.GET)
    @ResponseBody
    List<Map<String, Object>> selectSubDicts(@RequestParam("typeCode") String typeCode, @RequestParam("valueCode") String valueCode);

    /**
     * 根据参数值编码查询字典信息
     *
     * @param valueCode 参数值编码
     * @return 字典信息
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/dictType/selectByValueCode", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> selectByValueCode(@RequestParam("valueCode") String valueCode);

    /**
     * 根据启停标识查询字典列表
     *
     * @param usingSign 启停标识（1启用，0停用）
     * @return 字典列表
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/dictType/selectByUsingSign", method = RequestMethod.GET)
    @ResponseBody
    List<Map<String, Object>> selectByUsingSign(@RequestParam("usingSign") Integer usingSign);
}