package com.aier.cloud.biz.ui.biz.fin.utils;

import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-27 10:15
 **/

public class FinDictUtil {

    public static String  translate(String typeCode,String value){
        FinDictFeignService finDictFeignService=DictBeanFactory.getFinDictFeignService();
        List<Map> l=finDictFeignService.getList(typeCode);
        if(l.size() >0){
           return (l.stream().filter(m-> m.get("valueCode").equals(value)).collect(Collectors.toList()).get(0).get("valueDesc")).toString();
        }
        return null;
    }
}
