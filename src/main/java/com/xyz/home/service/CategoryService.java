package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.CategoryDao;
import com.xyz.home.dao.EarningDao;
import com.xyz.home.dao.SpendDao;
import com.xyz.home.model.Category;

@Service
public interface CategoryService{


	public int addCategory(Category category);

	public int modCategory(Category category);

	public int delCategory(Map<String, Object> map);

	public List<Map<String, Object>> selectCategory(Category category);

}
