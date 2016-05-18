package com.xyz.home.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyz.home.service.RoleService;
@Component
public class InitCache implements InitializingBean{

	@Autowired
	private AuthCache authCache;
	@Override
	public void afterPropertiesSet() throws Exception {
		authCache.initCache();
	}

	
}
