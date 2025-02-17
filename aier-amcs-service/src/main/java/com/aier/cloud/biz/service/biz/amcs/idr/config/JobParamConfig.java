package com.aier.cloud.biz.service.biz.amcs.idr.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.aier.cloud.basic.common.env.CoreConfig;
import com.google.common.collect.Maps;

@Component
public class JobParamConfig implements CoreConfig {
	@Override
	public Map<String, String> getLocal() {
		Map<String, String> c = Maps.newHashMap();  
		c.put("idr.url", "http://10.92.0.72:9090/dmp/ws/CommonWebServiceTransferCqjkzx?wsdl");

		c.put("9999.userName", "511523198712272872");
		c.put("9999.passWord", "88888888");
		c.put("9999.token", "b7a1c273-5e61-4390-aa8f-164f919145e1");

		return c;
	}

	@Override
	public Map<String, String> getProd() {
		Map<String, String> c = getLocal(); 

		return c;
	}
}
