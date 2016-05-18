package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.User;
@Repository
public interface UserDao {
	
	public List<User> selectUserLogin(User user);
	public int modUserLogInfo(User user);
	public List<Map<String,Object>> selectUser(Map<String,Object> map);
	public List<User> selectUserList(Map<String,Object> map);
	public int selectUserCount(Map<String,Object> map);
	public int addUser(User user);
	public int modUser(Map<String,Object> map);
	public int delUser(Map<String,Object> map);
	public int modUserRoleNull(Map<String,Object> map);
}