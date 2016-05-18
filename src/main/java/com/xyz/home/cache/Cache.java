package com.xyz.home.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {	
	
	private static Map<String,Object> cacheMap=new HashMap<String,Object>();
	
	
	public static void putCacheMap(String key, Object obj){
		cacheMap.put(key, obj);
	}
	public static Object getCacheMap(String key){
		return cacheMap.get(key);
	}

}
