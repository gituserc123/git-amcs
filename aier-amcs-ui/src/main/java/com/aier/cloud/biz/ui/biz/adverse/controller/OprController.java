package com.aier.cloud.biz.ui.biz.adverse.controller;


import cn.hutool.core.lang.Console;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AemrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/opr")
public class OprController extends BaseController{

    @Autowired
    AemrService aemrService;

    @PostMapping(value = "/selectOprQueryListPage")
    public @ResponseBody
    Map<String, Object> selectOprQueryListPage(@RequestParam("inpNumber")String inpNumber) {
        List<Map<String,Object>> list = aemrService.getForListByInpNumber(inpNumber);
        return this.list(list);
    }

    @PostMapping(value = "/getByInpNumberAndApplyNumber")
    public @ResponseBody Map<String, Object>  getByInpNumberAndApplyNumber(@RequestParam("inpNumber")String inpNumber, @RequestParam("applyNumber")String applyNumber){
        String diag = aemrService.getByInpNumberAndApplyNumber(inpNumber, applyNumber);
        Map<String, Object> rstMap = new HashMap<>(Collections.singletonMap("diag", diag));
        return this.success(rstMap);
    }

    @PostMapping(value = "/getDetailByApplyNumber")
    public @ResponseBody Map<String, Object> getDetailByApplyNumber(@RequestParam("applyNumber")String applyNumber){
        Map<String,Object> map = aemrService.getDetailByApplyNumber(applyNumber);
        return this.success(map);
    }


}
