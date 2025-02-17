package com.aier.cloud.biz.ui.biz.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.AeOaPayVO;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-30 14:50
 **/
public interface OaHandlerService {
    String getOaCMPY(String cmpyId);

    String getOaUserId(String UserCode);

    Integer createNew(AeOaPayVO aeOaPayVO, String title, String userId);
}
