package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsCommentCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsComment;
import com.aier.cloud.api.amcs.fin.domain.FinInsMonth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-27 15:40
 **/

@FeignClient(name = "aier-amcs-service")
public interface FinMonthFeignService {
    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/getList")
    @ResponseBody
    List<Map<String,Object>> getList(FinInsMonthCondition cond);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/unReport")
    @ResponseBody
    List<Map<String,Object>> unReport(FinInsMonthCondition cond);


    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/new")
    @ResponseBody
    Boolean newLine(@RequestParam("hospId")Long hospId);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/canSubmit")
    @ResponseBody
    Boolean canSubmit(@RequestParam("id") Long id);


    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/submitToProvince")
    @ResponseBody
    Boolean submitToProvince(@RequestParam("id") Long id);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/submitToGroup")
    @ResponseBody
    Boolean submitToGroup(@RequestParam("id") Long id);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/returnToHos")
    @ResponseBody
    Boolean returnToHos(@RequestParam("id") Long id);


    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/getFinInsMonth")
    @ResponseBody
    FinInsMonth getFinInsMonth(@RequestParam("id") Long id);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsComment/save")
    @ResponseBody
    Boolean commentSave(FinInsComment finInsComment);


    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsComment/getCommentByAssociatedId")
    @ResponseBody
    List<Map<String, Object>> getCommentByAssociatedId(@RequestParam("associatedId")Long  associatedId);

    //按monthId删除
    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsMonth/deleteByMonthId")
    @ResponseBody
    Boolean deleteByMonthId(@RequestParam("monthId")String monthId);
}
