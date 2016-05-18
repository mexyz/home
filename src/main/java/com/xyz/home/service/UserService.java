package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import com.xyz.home.model.User;

public interface UserService {
	public List<User> selectUserLogin(User user);

	public int modUserLogInfo(User user);

	public Object[] selectUser(Map<String, Object> map);


	public int addUser(User user);

	public int modUser(Map<String, Object> map);


	public int delUser(Map<String, Object> map);

	public List<User> selectUserList(Map<String, Object> map);
}
