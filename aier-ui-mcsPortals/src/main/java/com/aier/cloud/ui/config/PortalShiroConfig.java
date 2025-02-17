package com.aier.cloud.ui.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aier.cloud.basic.common.properties.AierUiProperties;
import com.aier.cloud.basic.web.shiro.RedisSessionDAO;
import com.aier.cloud.basic.web.shiro.filter.BasicUserFilter;
import com.aier.cloud.basic.web.shiro.filter.HttpsLogoutFilter;
import com.aier.cloud.basic.web.shiro.filter.InstitutionFormAuthenticationFilter;
import com.aier.cloud.ui.shiro.ShiroDbRealm;

/**
 * @author rain_deng
 * @since 2018年1月29日 下午3:57:37
 */
@Configuration
public class PortalShiroConfig {
	 
    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    
    @Bean
    public RedisSessionDAO getRedisSessionDAO(RedisManager redisManager, AierUiProperties properties) {
        RedisSessionDAO dao = new RedisSessionDAO();
        dao.setSessionInMemoryEnabled(true);
        dao.setRedisManager(redisManager);
        dao.setExpire(properties.getSessionInvalidateTime());
        dao.setKeyPrefix("shiro:session:");
        return dao;
    }
    
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(CacheManager cacheShiroManager, RedisManager redisManager, AierUiProperties properties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(getRedisSessionDAO(redisManager,properties));
        sessionManager.setGlobalSessionTimeout(properties.getSessionInvalidateTime() * 1000);
        
      
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        //防止XSS攻击，窃取cookie内容，增加cookie的安全性
        cookie.setHttpOnly(true);
        if(properties.getHttpsOpen()) {
        	cookie.setSecure(true);
        }
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    
    
    
    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, AierUiProperties properties) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        
        /** 默认的登陆访问url */
        shiroFilter.setLoginUrl("/login");
        
        /** 登陆成功后跳转的url */
        shiroFilter.setSuccessUrl("/home");
        
        /** 没有权限跳转的url */
        shiroFilter.setUnauthorizedUrl("/global/error");
        
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        LogoutFilter logoutFilter = new HttpsLogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        filters.put("logout", logoutFilter);
        filters.put("user", new BasicUserFilter());
        filters.put("authc", institutionFormAuthenticationFilter());
        shiroFilter.setFilters(filters);
        
        /**
         * 配置shiro拦截器链
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         * 顺序从上到下,优先级依次降低
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**",           "anon");
        if (properties.getOpenActuator()) {
        	hashMap.put("/actuator/info",   "anon");  // 新增健康检查
        	hashMap.put("/favicon.ico",     "anon");  // 新增健康检查
        }
        hashMap.put("/global/sessionError", "anon");
        hashMap.put("/kaptcha",             "anon");
        hashMap.put("/login/getInst",       "anon");
        hashMap.put("/cookie/**",       	"anon");
        hashMap.put("/qywexin/**",       	"anon");
        hashMap.put("/login/getDept",       "anon");
        hashMap.put("/login",               "authc");
        hashMap.put("/logout",              "logout");
        hashMap.put("/**",                  "user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }
    
    @Bean
    public ShiroDbRealm getShiroDbRealm(){
        return new ShiroDbRealm() {
        	{
        		setAuthorizationCacheName("authorizationCache");
        	}
        };
    }
    
    @Bean
    public SecurityManager securityManager(@Qualifier("shiroRedisCacheManager")CacheManager cacheManager, 
                                           @Qualifier("RedisManager") RedisManager redisManager,
                                           AierUiProperties properties) {
        //配置核心安全事务管理器
    	logger.info("--------------portal-shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        
        //注入缓存管理器;
        manager.setCacheManager(cacheManager);
        
        manager.setSessionManager(getDefaultWebSessionManager(cacheManager,redisManager,properties));
        
        manager.setRealm(getShiroDbRealm());
        
        //禅道5442 【优化】安全扫描结果，Cookie需设置HttpOnly
        //直接干掉rememberBe这个cookie，注释掉此行代码还不行，必须强行设置为null
        manager.setRememberMeManager(null);
        
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }
    
    
    /**
     * 验证码表单验证器
     */
    @Bean
    public InstitutionFormAuthenticationFilter institutionFormAuthenticationFilter() {
        return new InstitutionFormAuthenticationFilter();
    }
}
