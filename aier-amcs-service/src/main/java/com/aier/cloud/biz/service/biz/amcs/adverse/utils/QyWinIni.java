package com.aier.cloud.biz.service.biz.amcs.adverse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aier.basic.weixin.api.AwxService;
import com.aier.cloud.biz.service.config.WxProperties;
 


/**
 * 初始化企业微信组件
 * @author liuyu
 *
 */
@Component
public class QyWinIni implements CommandLineRunner {
	
	@Autowired
    private WxProperties wxProperties;
	
	@Override
    public void run(String... args) throws Exception {
		
        //初始化企业微信消息组件
		if(wxProperties.getFuCorpSecret().trim().equals("XXXXXX")) {
			AwxService.initCp(wxProperties.getCorpId(), wxProperties.getFuCorpSecret(), wxProperties.getFuAgentId());
		}else {
			AwxService.initCp(wxProperties.getCorpId(), "qyMHLZ7_VS_hUid_Uzi3zKLJ9DTBu-CCq5fTmFwGrNs", 1000364);
		}
		
    }
}
