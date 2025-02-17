package com.aier.cloud.biz.service.biz.sys.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 调用第三方系统的rest服务配置
 * 如人资同步/帆软报表等
 *
 * @author xiaokek
 * @since 2018年9月5日 下午4:23:27
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
    	RestTemplate rt = new RestTemplate();
		List<HttpMessageConverter<?>> mcs = rt.getMessageConverters();
		for(int i = 0; i < mcs.size(); i++){
			HttpMessageConverter<?> mc = mcs.get(i);
			if(mc instanceof StringHttpMessageConverter){
				mcs.remove(i);
				mcs.add(i, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			}
		}
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(30000);
		factory.setReadTimeout(900000); 
		factory.setConnectionRequestTimeout(10000);
		rt.setRequestFactory(factory);
		
		return rt;
    }
    

}