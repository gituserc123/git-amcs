package com.aier.cloud.biz.ui.biz.fin.controller;


import com.aier.cloud.api.amcs.fin.condition.FinMaDictInfoCondition;
import com.aier.cloud.api.amcs.fin.domain.FinMaDictInfo;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMaDictInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ui/amcs/fin/ma/dict")
public class FinMaDictInfoUiController extends BaseController {

    @Autowired
    private FinMaDictInfoFeignService finMaDictInfoFeignService;

    private static final String FIN_MA_DICT_LIST="amcs/fin/business/manageAnalysis/finMaDictInfoPage";

    @RequestMapping(value = "/finMaDictLists", method = { RequestMethod.GET, RequestMethod.POST })
    public String finMaDictLists(Map<String, Object> map) {
        return FIN_MA_DICT_LIST;
    }

    @RequestMapping(value ="/getMaDictList")
    @ResponseBody
    List<FinMaDictInfo> getMaDictList(FinMaDictInfoCondition finMaDictInfoCondition){
        return finMaDictInfoFeignService.getMaDictList(finMaDictInfoCondition);
    }

    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Map<String, Object> save(FinMaDictInfo finMaDictInfo){
        return finMaDictInfoFeignService.save(finMaDictInfo);
    }

}
