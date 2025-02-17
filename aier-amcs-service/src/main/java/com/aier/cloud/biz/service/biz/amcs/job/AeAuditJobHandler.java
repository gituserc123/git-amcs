package com.aier.cloud.biz.service.biz.amcs.job;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;


@JobHandler(value="com.aier.cloud.amcs.AeAuditJobHandler")
@Component
public class AeAuditJobHandler extends IJobHandler {
	
	@Override
    public ReturnT<String> execute(String param) throws Exception {
		
		return null;
	}

}
