package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Menu;

@Repository
public interface MenuDao {
    
	public List<Menu> selectMenu(Integer roleId);
	public List<Map<String,Object>> selectMenuList(Map<String,Object> map);
}