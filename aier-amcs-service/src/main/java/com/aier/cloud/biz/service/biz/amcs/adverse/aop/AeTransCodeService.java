package com.aier.cloud.biz.service.biz.amcs.adverse.aop;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 10:20
 **/

@Service
public class AeTransCodeService {
    @Autowired
    AeDictTypeService aeDictTypeService;
    public String getValue(String type ,String code){
        List<Map> list=aeDictTypeService.getList(type,null);
        String []s={null};
        list.forEach(e -> {
           if(code.equals(e.get("valueCode"))) {
               s[0]= (String)e.get("valueDesc");
           }
        });
        return s[0];
    }

}
