package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.ApiInDomain;
import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-10 00:18
 **/
public interface ApiService {
    ApiInDomain getInDomain(String inNumber);
    ApiOpDomain getOpDomain(String opNumber);
}
