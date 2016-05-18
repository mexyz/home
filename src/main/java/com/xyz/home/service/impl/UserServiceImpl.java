package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.CallDao;
import com.xyz.home.dao.UserDao;
import com.xyz.home.model.User;
import com.xyz.home.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private CallDao callDao;
	@Override
	public List<User> selectUserLogin(User user) {
		return userDao.selectUserLogin(user);
	}

	@Override
	public int modUserLogInfo(User user) {
		return userDao.modUserLogInfo(user);
	}

	@Override
	public Object[] selectUser(Map<String, Object> map) {
		return new Object[]{userDao.selectUser(map),userDao.selectUserCount(map)};
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int modUser(Map<String, Object> map) {
		
		return userDao.modUser(map);
	}

	@Override
	public int delUser(Map<String, Object> map) {
		callDao.modUIdNull(map);
		return userDao.delUser(map);
	}

	@Override
	public List<User> selectUserList(Map<String, Object> map) {
		return userDao.selectUserList(map);
	}
}
