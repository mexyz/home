package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Role;

@Repository
public interface RoleDao {
	
	public List<Map<String,Object>> selectRoleCombo();
	public List<Map<String,Object>> selectRole(Map<String,Object> map);
	public int selectRoleCount(Map<String,Object> map);
	public int addRole(Role role);
	public int modRole(Role role);
	public int delRole(Map<String,Object> map);
	public int delRoleMemberByMId(Map<String,Object> map);
	public List<Map<String, Object>> selectRoleList(Map<String,Object> map);
	
	public int delRoleMember(Map<String,Object> map);
	public int delRoleMenu(Map<String,Object> map);
	public int delRoleAction(Map<String,Object> map);
	public int addRoleMember(Map<String,Object> map);
	public int addRoleMenu(Map<String,Object> map);
	public int addRoleAction(Map<String,Object> map);
	
	public List<Map<String,Object>> selectRoleAction();
}