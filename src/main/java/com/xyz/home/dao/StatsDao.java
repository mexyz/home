package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface StatsDao {

	public List<Map<String,Object>> selectTimeSpendStats(Map<String,Object> map);
	public int selectTimeSpendStatsCount(Map<String,Object> map);
	
	public List<Map<String,Object>> selectCategorySpendStats(Map<String,Object> map);
	public int selectCategorySpendStatsCount(Map<String,Object> map);
	
	public List<Map<String,Object>> selectMemberSpendStats(Map<String,Object> map);
	public int selectMemberSpendStatsCount(Map<String,Object> map);
	
	
	public List<Map<String,Object>> selectTimeEarningStats(Map<String,Object> map);
	public int selectTimeEarningStatsCount(Map<String,Object> map);
	
	public List<Map<String,Object>> selectCategoryEarningStats(Map<String,Object> map);
	public int selectCategoryEarningStatsCount(Map<String,Object> map);
	
	public List<Map<String,Object>> selectMemberEarningStats(Map<String,Object> map);
	public int selectMemberEarningStatsCount(Map<String,Object> map);
	
	public List<Map<String,Object>> selectEarningSpendStats(Map<String,Object> map);
	public int selectEarningSpendStatsCount(Map<String,Object> map);
}
