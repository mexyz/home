package com.xyz.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.ActionDao;
import com.xyz.home.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{
	
	@Autowired
	private ActionDao actionDao;

	@Override
	public List<Map<String, Object>> selectActions(Map<String, Object> map) {
		return actionDao.selectActions(map);
	}

	@Override
	public List<String> selectDescribe(Map<String, Object> map) {
		return actionDao.selectDescribe(map);
	}

	
}
