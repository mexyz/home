package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Earning;
@Repository
public interface EarningDao {

	public List<Map<String,Object>> selectEarning(Map<String,Object> map);
	public int selectEarningCount(Map<String,Object> map);
	public int addEarning(Earning earning);
	public int modEarning(Earning earning);
	public int delEarning(Map<String,Object> map);
	public int modCaIdNull(Map<String,Object> map);
	public int modMIdNull(Map<String,Object> map);
	
}