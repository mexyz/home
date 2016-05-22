package com.xyz.home.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyz.home.service.AuthService;
@Component
public class AuthCache extends Cache{

	@Autowired
	private AuthService authService;
	
	public void initCache(){
		List<Map<String,Object>> list=authService.selectRoleAction();
		for(Map<String,Object> m:list){
			String key=String.valueOf(m.get("rId"));
			if(Cache.getCacheMap(key)==null){
				List<String> urls=new ArrayList<String>();
				urls.add(String.valueOf(m.get("url")));
				Cache.putCacheMap(key, urls);
			}else{
				((List<String>) Cache.getCacheMap(key)).add(String.valueOf(m.get("url")));
			}
		}
	}
	
	public void reloadCache(){
		initCache();
	}

}
