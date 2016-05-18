package com.xyz.home.service;

import java.util.Map;

import com.xyz.home.model.Earning;

public interface EarningService {

	public Object[] selectEarning(Map<String,Object> map);
	public int addEarning(Earning earning);
	public int modEarning(Earning earning);
	public int delEarning(Map<String,Object> map);
}
