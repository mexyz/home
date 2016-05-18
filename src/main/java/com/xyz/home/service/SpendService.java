package com.xyz.home.service;

import java.util.Map;

import com.xyz.home.model.Spend;

public interface SpendService {

	public Object[] selectSpend(Map<String,Object> map);
	public int addSpend(Spend balance);
	public int modSpend(Spend balance);
	public int delSpend(Map<String,Object> map);
}
