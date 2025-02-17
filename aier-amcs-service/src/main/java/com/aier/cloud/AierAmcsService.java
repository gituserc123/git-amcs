package com.aier.cloud;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.aier.cloud.basic.starter.service.config.RedisConfig;
import com.aier.cloud.basic.starter.service.config.jwt.JwtConfig;
import com.aier.cloud.center.common.annotion.AierCloudApplication;


/**
 * 爱尔集团管控服务 Aier Group Management and Control Service（AMCS）
 *
 *
 * @author xiaokek
 * @since 2021年9月26日 下午4:05:01
 */
@AierCloudApplication
@ComponentScan(nameGenerator = BeanNameGenerator.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {}))
public class AierAmcsService implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AierAmcsService.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		SpringUtils.getBean(DruidDataSource.class).setProxyFilters(Arrays.asList(new SqlCountFilter(1)));
//		DiscoveryManager.getInstance().shutdownComponent();
	}
}

class BeanNameGenerator extends AnnotationBeanNameGenerator {
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String cn = definition.getBeanClassName();
		if(StringUtils.isNotBlank(cn) && (cn.startsWith("com.aier.cloud.biz.service") || cn.startsWith("com.aier.cloud.biz.ui.biz"))){
			return cn;
		} else{
			return super.generateBeanName(definition, registry);
		}
	}
}
