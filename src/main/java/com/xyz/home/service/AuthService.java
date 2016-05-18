package com.xyz.home.service;

import java.util.List;
import java.util.Map;

public interface AuthService {
	
	public int modAuth(Map<String,Object> map);
	public List<Map<String,Object>> selectRoleAction();
}
