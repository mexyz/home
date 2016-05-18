package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import com.xyz.home.model.Role;
import com.xyz.home.model.User;

public interface RoleService {
	
	public List<Map<String,Object>> selectRoleCombo();
	public Object[] selectRole(Map<String,Object> map);
	public int addRole(Role role);
	public int modRole(Role role);
	public int delRole(Map<String,Object> map);
	public List<Map<String, Object>> selectRoleList(Map<String,Object> map);
}
