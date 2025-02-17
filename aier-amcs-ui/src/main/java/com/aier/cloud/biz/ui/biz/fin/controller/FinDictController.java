package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.AeDictType;
import com.aier.cloud.api.amcs.fin.domain.FinDictType;
import com.aier.cloud.api.amcs.fin.domain.FinInsMain;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;

import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import com.aier.cloud.center.common.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description: 字典维护
 * @author: hsw
 * @create: 2022-12-27 12:31
 **/
@Controller
@RequestMapping(value="/ui/amcs/fin/dict/")
public class FinDictController extends BaseController {
    @Autowired
    FinDictFeignService finDictFeignService;
    private static final String DICT_LIST="amcs/fin/config/dictList";
    private static final String HOSP_DICT_LIST="amcs/fin/config/hospDictList";
    @RequestMapping(value = "/dictList",method = { RequestMethod.GET, RequestMethod.POST})
    public String dictList(Model model){
        if (!UserContext.getTenantId().equals(0L)){
            throw new BizException("只有集团用户可以访问此页面");
        }
        model.addAttribute("dictTypes",finDictFeignService.getType(null) );
        return DICT_LIST;
    };

    @RequestMapping(value = "/hospDictList",method = { RequestMethod.GET, RequestMethod.POST})
    public String hospDictList(Model model){
        model.addAttribute("hospId", UserContext.getTenantId());
        model.addAttribute("dictTypes",finDictFeignService.getType(UserContext.getTenantId()) );
        return HOSP_DICT_LIST;
    };

    @RequestMapping(value = "/getList",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public
    List<Map> getList(@RequestParam("paraType") String paraType) {

        return finDictFeignService.getList(paraType);

    }


    @RequestMapping(value = "/save",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object save( FinDictType finDictType) {
        if (finDictFeignService.save(finDictType)){
            return this.success();
        }else{
            return this.fail();
        }


    }
    @RequestMapping(value = "/del",method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object del(@RequestParam("id") Long id) {
        if (finDictFeignService.del(id)){
            return this.success();
        }else{
            return this.fail();
        }


    }


}
