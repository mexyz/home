package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import com.xyz.home.model.Menu;

public interface MenuService {
	public List<Menu> selectMenu(Integer roleId);
	public List<Map<String,Object>> selectMenuList(Map<String,Object> map);
}
