package com.aier.amcs;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-08-29 16:00
 **/



import com.aier.cloud.AierAmcsUi;

import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeApiGetService;
import com.aier.cloud.biz.ui.biz.amcs.jtr.feign.SystemService;

import com.aier.cloud.biz.ui.biz.common.feign.MedicalFeignService;
import com.aier.cloud.center.common.context.AierUser;
import com.aier.cloud.center.common.context.UserContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


/**
 * @program: accs
 * @description: test
 * @author: hsw
 * @create: 2021-04-20 15:35
 **/
@SpringBootTest(classes= AierAmcsUi.class)
@RunWith(SpringRunner.class)
public class ForTest {

@Autowired
MedicalFeignService medicalFeignService;
@Autowired
    AeApiGetService aeApiGetService;
    @Test
    public void test() throws Exception {
        Long hospId = 9999L;
        Long id = 100630100630L;
        //以便后续的插入能自动带上hospId
        AierUser au = new AierUser();
        au.setTenantId(hospId);
        au.setInstId(id);
        UserContext.setUser(au);
        ApiOpDomain apiOpDomain=aeApiGetService.getOpDomain("MZS202209260002");
        Object o=medicalFeignService.getPatientInfoByPatientId(apiOpDomain.getPatientId());
        System.out.println("111111111111");
    }
}
