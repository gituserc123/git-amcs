package com.aier.cloud.biz.ui.biz.law.controller;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.law.feign.LawDictTypeFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ui/amcs/law/dictType")
public class LawDictTypeUiController extends BaseController {

    @Autowired
    private LawDictTypeFeignService lawDictTypeFeignService;

    /**
     * 保存或更新法务字典表信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody Map<String, Object> lawDictType) {
        // 调用 Feign 接口保存或更新数据
        return lawDictTypeFeignService.save(lawDictType);
    }

    /**
     * 根据ID查询法务字典表信息
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public Object getById(@RequestParam("id") Long id) {
        // 调用 Feign 接口根据ID查询数据
        return lawDictTypeFeignService.getLawDictType(id);
    }

    /**
     * 根据字典类型编码查询字典列表
     */
    @RequestMapping(value = "/selectByTypeCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map> selectByTypeCode(@RequestParam("typeCode") String typeCode) {
        // 调用 Feign 接口根据字典类型编码查询数据
        return lawDictTypeFeignService.selectByTypeCode(typeCode);
    }

    /**
     * 查询子字典列表
     */
    @RequestMapping(value = "/selectSubDicts", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> selectSubDicts(@RequestParam("typeCode") String typeCode, @RequestParam("valueCode") String valueCode) {
        // 调用 Feign 接口查询数据
        return lawDictTypeFeignService.selectSubDicts(typeCode, valueCode);
    }

    /**
     * 根据启停标识查询字典列表
     */
    @RequestMapping(value = "/selectByUsingSign", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> selectByUsingSign(@RequestParam("usingSign") Integer usingSign) {
        // 调用 Feign 接口根据启停标识查询数据
        return lawDictTypeFeignService.selectByUsingSign(usingSign);
    }
}