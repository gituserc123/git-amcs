package com.aier.cloud.biz.ui.biz.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.AeOaPayVO;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.biz.ui.biz.adverse.utils.OaUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: amcs
 * @description: oa接口相关操作
 * @author: hsw
 * @create: 2023-01-09 16:07
 **/
@Service("partner")
public class OaPartnerHandleService implements OaHandlerService{
    @Value("${oa.partner.sign:649778fd-37bd-4abb-90d0-2e4ed3d4ac31}")
    private String SIGN;
//    @Value("${oa.partner.cmpy.api:/api/hrm/resful/getHrmsubcompanyWithPage}")
//    private String CMY_API;
    @Value("${oa.partner.address:http://10.0.11.234}")
    private String ADDRESS;
    @Value("${oa.partner.appid:DF15DAB7537463F7960CA3C4B7B32005}")
    private String APPID;
    @Value("${oa.partner.workflowId:99745}")
    private String WORKFLOWID;

    @Autowired
    private RedisService redisService;

    @Override
    public String getOaCMPY(String cmpyId) {
        return OaUtil.getOaCMPY(cmpyId,ADDRESS,SIGN,APPID,redisService);
    }

    @Override
    public String getOaUserId(String UserCode) {
        return OaUtil.getOaUserId(UserCode,ADDRESS,SIGN,APPID,redisService);
    }

    @Override
    public Integer createNew(AeOaPayVO aeOaPayVO, String title, String userId) {
        return  OaUtil.createNew(aeOaPayVO,title,userId,SIGN,WORKFLOWID,ADDRESS,redisService,APPID);
    }
}
