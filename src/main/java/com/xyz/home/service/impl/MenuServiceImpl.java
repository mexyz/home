package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.MenuDao;
import com.xyz.home.model.Menu;
import com.xyz.home.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> selectMenu(Integer roleId) {
		return menuDao.selectMenu(roleId);
	}

	@Override
	public List<Map<String, Object>> selectMenuList(Map<String, Object> map) {
		return menuDao.selectMenuList(map);
	}


}
