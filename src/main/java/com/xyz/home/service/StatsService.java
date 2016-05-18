package com.xyz.home.service;

import java.util.Map;

public interface StatsService {
	
	public Object[] selectTimeStats(Map<String,Object> map);
	public Object[] selectCategoryStats(Map<String,Object> map);
	public Object[] selectMemberStats(Map<String,Object> map);
	public Object[] selectEarningSpendStats(Map<String,Object> map);
}
