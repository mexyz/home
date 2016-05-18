package com.xyz.home.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.EarningDao;
import com.xyz.home.model.Earning;
import com.xyz.home.service.EarningService;
@Service
public class EarningServiceImpl implements EarningService{
	@Autowired
	private EarningDao earningDao;

	@Override
	public Object[] selectEarning(Map<String, Object> map) {
		return new Object[]{earningDao.selectEarning(map),earningDao.selectEarningCount(map)};
	}

	@Override
	public int addEarning(Earning earning) {
		return earningDao.addEarning(earning);
	}

	@Override
	public int modEarning(Earning earning) {
		return earningDao.modEarning(earning);
	}

	@Override
	public int delEarning(Map<String,Object> map) {
		return earningDao.delEarning(map);
	}
}
