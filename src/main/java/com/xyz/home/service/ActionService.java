package com.xyz.home.service;

import java.util.List;
import java.util.Map;

public interface ActionService {
	public List<Map<String,Object>> selectActions(Map<String,Object> map);
	public List<String> selectDescribe(Map<String,Object> map);
}
