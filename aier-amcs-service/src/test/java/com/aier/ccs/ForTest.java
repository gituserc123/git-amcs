package com.aier.ccs;
import com.aier.cloud.AierAmcsService;
import com.aier.cloud.api.amcs.adverse.domain.ApiOpDomain;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.AeTransCodeService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeMedicalFeignService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ApiService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.impl.ApiServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorStatistics;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorStatisticsService;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.impl.DoctorTitleServiceImpl;
import com.aier.cloud.center.common.context.AierUser;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AierAmcsService.class)
public class ForTest{
    @Autowired
    private ApiService apiService;

    @Test
    public void test() throws ClassNotFoundException {
        Long hospId = 9999L;
        Long id = 100630100630L;
        //以便后续的插入能自动带上hospId
        AierUser au = new AierUser();
        au.setTenantId(hospId);
        au.setInstId(id);
        UserContext.setUser(au);
        apiService.getInDomain("ZY20221123001");
    }
}