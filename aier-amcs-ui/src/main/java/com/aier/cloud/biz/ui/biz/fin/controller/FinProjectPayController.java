package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.fin.domain.FinInsProjectPay;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.fin.feign.FinProjectPayService;
import com.aier.cloud.biz.ui.biz.fin.utils.FinDictUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * FinInsProjectPay
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsProjectPay")
public class FinProjectPayController extends BaseController {

    @Autowired
    FinProjectPayService finProjectPayService;

    //@RequiresPermissions("getFinInsProjectPay")
    @RequestMapping(value = "/getFinInsProjectPay")
    public @ResponseBody
    Map<String, Object> getFinInsProjectPay(@RequestParam("id") Long id) {
        return finProjectPayService.getFinInsProjectPay(id);
    }

    @RequestMapping(value ="/saveFinInsProjectPay",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody FinInsProjectPay finInsProjectPay){
        return this.success(finProjectPayService.save(finInsProjectPay));
    }

    @RequestMapping(value ="/getByMainId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public FinInsProjectPay getByMainId(Long mainId){
        FinInsProjectPay finInsProjectPay = finProjectPayService.getByMainId(mainId);
        if(finInsProjectPay!=null){
            return finInsProjectPay;
        }else {
            return new FinInsProjectPay();
        }
    }

    @RequestMapping(value = "/lastList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    List<Map<String,Object>> lastList() {
        List<Map<String,Object>> l= finProjectPayService.lastList();
        l.forEach(m ->{
            m.put("typeName", FinDictUtil.translate("INSURANCE_TYPE",m.get("insuranceType").toString()));
        });
        return l;
    }

}
