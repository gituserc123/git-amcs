package com.aier.cloud.ui.biz.sys.controller;

import java.util.Objects;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aier.cloud.basic.web.controller.BaseController;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/cookie")
public class CheckCookieController extends BaseController {
	
	private static final String COOKIE_STATUS_INVALID = "invalid";
	private static final String COOKIE_STATUS_VALID = "valid";

	@GetMapping("/check/{cookieid}")
    public String check(@PathVariable String cookieid){
		if (StringUtils.isBlank(cookieid)) {
			return COOKIE_STATUS_INVALID;
		}
		//判断session是否存在
		try{
			Session session = SecurityUtils.getSecurityManager().getSession(new DefaultSessionKey(cookieid));
			if (Objects.isNull(session)) {
				return COOKIE_STATUS_INVALID;
			}
		} catch (Exception e) {
			return COOKIE_STATUS_INVALID;
		}
        
        return COOKIE_STATUS_VALID;
	}
	
}
