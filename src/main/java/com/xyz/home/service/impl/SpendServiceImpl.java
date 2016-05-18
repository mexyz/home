package com.xyz.home.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.SpendDao;
import com.xyz.home.model.Spend;
import com.xyz.home.service.SpendService;

@Service
public class SpendServiceImpl implements SpendService{

	@Autowired
	private SpendDao spendDao;

	@Override
	public Object[] selectSpend(Map<String, Object> map) {
		return new Object[]{spendDao.selectSpend(map),spendDao.selectSpendCount(map)};
	}

	@Override
	public int addSpend(Spend spend) {
		return spendDao.addSpend(spend);
	}

	@Override
	public int modSpend(Spend spend) {
		return spendDao.modSpend(spend);
	}

	@Override
	public int delSpend(Map<String,Object> map) {
		return spendDao.delSpend(map);
	}
}
