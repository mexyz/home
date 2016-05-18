package com.xyz.home.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyz.home.service.AuthService;
public class Cache{

	

	private static Map<String,Object> cacheMap=new HashMap<String, Object>();

	public static Object getCacheMap(String key) {
		
		return cacheMap.get(key);
	}

	public static void putCacheMap(String key,Object obj) {
		cacheMap.put(key, obj);
	}

	
}
