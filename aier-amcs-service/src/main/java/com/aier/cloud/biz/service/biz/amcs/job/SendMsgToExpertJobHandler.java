package com.aier.cloud.biz.service.biz.amcs.job;

import java.util.List;
import java.util.Map;


import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeExpert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aier.basic.weixin.api.AwxService;
import com.aier.basic.weixin.api.CpMesg;
import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeExpertEventService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * 
 * 专家定时消息
 *
 */
@JobHandler(value="com.aier.cloud.amcs.SendMsgToExpertJobHandler")
@Component
public class SendMsgToExpertJobHandler extends IJobHandler {
	
	@Autowired
	private AeExpertService aeExpertService;
	
	@Autowired
	private AeExpertEventService aeExpertEventService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SendMsgToExpertJobHandler.class);
	
	@Override
    public ReturnT<String> execute(String param) throws Exception {
		
		try {
			//获取所有的专家
			AeExpertCondition cond = new AeExpertCondition();
			List<AeExpert> experts = aeExpertService.getExpertsByCond(cond);
			for(AeExpert expert: experts){
				//获取集团不良事件
				CpMesg cpMesg = new CpMesg();
				cpMesg.setUser(expert.getStaffCode());
				Integer groupCount = aeExpertEventService.findExpertDayCount(expert.getStaffId().toString(), false);
				if(groupCount > 0) {
					String msg = Constants.ExpertJobMsg;
					msg = String.format(msg, expert.getStaffName(), groupCount);
					cpMesg.setContent(msg);
					AwxService.sendCpMesg(cpMesg);
				}

				//获取省区不良事件
				Integer provinceCount = aeExpertEventService.findExpertDayCount(expert.getStaffId().toString(), true);
				if(provinceCount > 0) {
					String msg = Constants.ProvinceExpertJobMsg;
					msg = String.format(msg, expert.getStaffName(), provinceCount);
					cpMesg.setContent(msg);
					AwxService.sendCpMesg(cpMesg);
				}

			}

			return new ReturnT<String>(ReturnT.SUCCESS_CODE, "推送专家点评提醒成功");
		}catch(Exception ex) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, ex.getMessage());
		}
	}
	
}
