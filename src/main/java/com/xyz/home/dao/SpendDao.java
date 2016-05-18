package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Spend;
@Repository
public interface SpendDao {

	public List<Map<String,Object>> selectSpend(Map<String,Object> map);
	public int selectSpendCount(Map<String,Object> map);
	public int addSpend(Spend spend);
	public int modSpend(Spend spend);
	public int delSpend(Map<String,Object> map);
	public int modCaIdNull(Map<String,Object> map);
	public int modMIdNull(Map<String,Object> map);
}