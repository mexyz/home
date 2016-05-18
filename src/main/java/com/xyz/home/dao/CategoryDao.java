package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Category;

@Repository
public interface CategoryDao {

	public List<Map<String,Object>> selectCategory(Category category);
	public int selectCategoryCount();
	public int addCategory(Category category);
	public int modCategory(Category category);
	public int delCategory(Map<String,Object> map);
	public int modPId(Map<String,Object> map);
	
}
