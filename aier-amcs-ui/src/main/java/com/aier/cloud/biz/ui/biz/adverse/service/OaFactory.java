package com.aier.cloud.biz.ui.biz.adverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-31 10:11
 **/

@Component
public class OaFactory {

    @Autowired
    private Map<String, OaHandlerService> MarketOrPartnerMap= new ConcurrentHashMap<>(2) ;

    public OaHandlerService getByName(String name) {
        return MarketOrPartnerMap.get(name);
    }

}
