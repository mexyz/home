package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.RoleDao;
import com.xyz.home.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public int modAuth(Map<String, Object> map) {
		map.put("ids",String.valueOf(map.get("rId")).split(","));
		roleDao.delRoleMember(map);
		roleDao.delRoleMenu(map);
		roleDao.delRoleAction(map);
		String[] menuIds=new String[]{};
		String[] memberIds=new String[]{};
		String[] actionIds=new String[]{};
		if(!"".equals(String.valueOf(map.get("menuIds")))){
			menuIds=String.valueOf(map.get("menuIds")).split(",");
		}
		if(!"".equals(String.valueOf(map.get("memberIds")))){
			memberIds=String.valueOf(map.get("memberIds")).split(",");
		}
		if(!"".equals(String.valueOf(map.get("actionIds")))){
			actionIds=String.valueOf(map.get("actionIds")).split(",");
		}
		for(String mId:menuIds){
			map.put("mId",mId);
			roleDao.addRoleMenu(map);
		}
		for(String mId:memberIds){
			map.put("mId",mId);
			roleDao.addRoleMember(map);
		}
		for(String aId:actionIds){
			map.put("aId",aId);
			roleDao.addRoleAction(map);
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> selectRoleAction() {
		return roleDao.selectRoleAction();
	}
	
}
