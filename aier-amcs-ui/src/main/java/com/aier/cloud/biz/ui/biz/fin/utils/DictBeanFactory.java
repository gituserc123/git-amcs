package com.aier.cloud.biz.ui.biz.fin.utils;

import com.aier.cloud.biz.ui.biz.fin.feign.FinDictFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-03-27 10:35
 **/

@Component
public class DictBeanFactory {
    private static FinDictFeignService finDictFeignService;

    @Autowired
    public void setMyBean(FinDictFeignService finDictFeignService) {
        DictBeanFactory.finDictFeignService = finDictFeignService;
    }

    public static FinDictFeignService getFinDictFeignService() {
        return finDictFeignService;
    }
}
