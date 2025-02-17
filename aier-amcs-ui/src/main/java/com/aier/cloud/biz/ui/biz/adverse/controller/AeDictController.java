package com.aier.cloud.biz.ui.biz.adverse.controller;

import com.aier.cloud.api.amcs.adverse.domain.AeDictType;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description: 字典维护
 * @author: hsw
 * @create: 2022-12-27 12:31
 **/
@Controller
@RequestMapping(value="/ui/amcs/adverse/aeDict/")
public class AeDictController extends BaseController {
    @Autowired
    AdverseDictService adverseDictService;
    private static final String DICT_LIST="amcs/adverseEvent/config/dictList";
    @RequestMapping(value = "/dictList",method = { RequestMethod.GET, RequestMethod.POST})
    public String dictList(Model model){
        return DICT_LIST;
    };

    @RequestMapping(value = "/getList",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public
    List<Map> getList(@RequestParam("paraType") String paraType, @RequestParam("filter") String filter) {
        return adverseDictService.getList(paraType,filter);
    }


    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save( AeDictType aeDictType) {
        if (adverseDictService.save(aeDictType)){
            return this.success();
        }else{
            return this.fail();
        }


    }
    @RequestMapping(value = "/del",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam("id") Long id) {
        if (adverseDictService.del(id)){
            return this.success();
        }else{
            return this.fail();
        }


    }
}
