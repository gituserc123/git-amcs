package com.aier.cloud.biz.service.biz.amcs.job;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2021-12-09 16:58
 **/

import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorStatisticsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@description: 测试job
 */
@JobHandler(value="com.aier.cloud.amcs.SendToAllJobHandler")
@Component
public class SendToAllJobHandler extends IJobHandler {

    @Autowired
    private DoctorStatisticsService doctorStatisticsService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            String info = doctorStatisticsService.sendToAll(param);
            return new ReturnT<String>(ReturnT.SUCCESS_CODE, JsonUtil.toJson(info));
        }catch (Throwable e) {
            XxlJobLogger.log(e);
            return new ReturnT<String>(ReturnT.FAIL_CODE, e.getMessage());
        }
    }

}