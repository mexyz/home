package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import com.xyz.home.model.Category;

public interface CategoryService {
	
	public List<Map<String,Object>> selectCategory(Category category);
	public int addCategory(Category category);
	public int modCategory(Category category);
	public int delCategory(Map<String,Object> map);
}
