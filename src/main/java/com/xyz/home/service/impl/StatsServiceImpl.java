package com.xyz.home.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.StatsDao;
import com.xyz.home.service.StatsService;
@Service
public class StatsServiceImpl implements StatsService{

	@Autowired
	private StatsDao statsDao;
	
	@Override
	public Object[] selectTimeStats(Map<String, Object> map) {
		if(String.valueOf(map.get("type")).equals("0")){
			return new Object[]{statsDao.selectTimeSpendStats(map),statsDao.selectTimeSpendStatsCount(map)};
		}else{
			return new Object[]{statsDao.selectTimeEarningStats(map),statsDao.selectTimeEarningStatsCount(map)};
		}
		
	}

	@Override
	public Object[] selectCategoryStats(Map<String, Object> map) {
		if(String.valueOf(map.get("type")).equals("0")){
			return new Object[]{statsDao.selectCategorySpendStats(map),statsDao.selectCategorySpendStatsCount(map)};
		}else{
			return new Object[]{statsDao.selectCategoryEarningStats(map),statsDao.selectCategoryEarningStatsCount(map)};
		}
		
	}

	@Override
	public Object[] selectMemberStats(Map<String, Object> map) {
		if(String.valueOf(map.get("type")).equals("0")){
			return new Object[]{statsDao.selectMemberSpendStats(map),statsDao.selectMemberSpendStatsCount(map)};
		}else{
			return new Object[]{statsDao.selectMemberEarningStats(map),statsDao.selectMemberEarningStatsCount(map)};
		}
		
	}

	@Override
	public Object[] selectEarningSpendStats(Map<String, Object> map) {
		return new Object[]{statsDao.selectEarningSpendStats(map),statsDao.selectEarningSpendStatsCount(map)};
	}

}
