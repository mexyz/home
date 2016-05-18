package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.CategoryDao;
import com.xyz.home.dao.EarningDao;
import com.xyz.home.dao.SpendDao;
import com.xyz.home.model.Category;
import com.xyz.home.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private EarningDao earningDao;
	@Autowired
	private SpendDao spendDao;
	
	@Override
	public int addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	@Override
	public int modCategory(Category category) {
		return categoryDao.modCategory(category);
	}

	@Override
	public int delCategory(Map<String, Object> map) {
		earningDao.modCaIdNull(map);
		spendDao.modCaIdNull(map);
		categoryDao.modPId(map);
		return categoryDao.delCategory(map);
	}

	@Override
	public List<Map<String, Object>> selectCategory(Category category) {
		return categoryDao.selectCategory(category);
	}

}
