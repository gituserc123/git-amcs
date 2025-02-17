package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.condition.PersonCondition;
import com.aier.cloud.api.amcs.fin.domain.Person;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-08-30 15:27
 **/

@FeignClient(name = "aier-amcs-service")
public interface FinPersonService {
    @RequestMapping(value = "/api/service/biz/amcs/fin/person/save")
    @ResponseBody
    Boolean savePerson(Person person);

    @RequestMapping(value = "/api/service/biz/amcs/fin/person/getPersonByHospId")
    @ResponseBody
    Person getPersonByHospId(@RequestParam("id") Long id);
    @RequestMapping(value = "/api/service/biz/amcs/fin/person/list")
    @ResponseBody
    List<Map<String,Object>> getPersonList(PersonCondition personCondition);
}
