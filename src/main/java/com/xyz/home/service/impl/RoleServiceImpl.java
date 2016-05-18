package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.RoleDao;
import com.xyz.home.dao.UserDao;
import com.xyz.home.model.Role;
import com.xyz.home.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Override
	public List<Map<String, Object>> selectRoleCombo() {
		return roleDao.selectRoleCombo();
	}

	@Override
	public Object[] selectRole(Map<String, Object> map) {
		return new Object[]{roleDao.selectRole(map),roleDao.selectRoleCount(map)};
	}

	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public int modRole(Role role) {
		return roleDao.modRole(role);
	}

	@Override
	public int delRole(Map<String, Object> map) {
		roleDao.delRoleAction(map);
		roleDao.delRoleMember(map);
		roleDao.delRoleMenu(map);
		userDao.modUserRoleNull(map);
		return roleDao.delRole(map);
	}

	@Override
	public List<Map<String, Object>> selectRoleList(Map<String, Object> map) {
		return roleDao.selectRoleList(map);
	}


}
