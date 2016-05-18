package com.xyz.home.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Call;

@Repository
public interface CallDao {
	
	public int addCall(Call call);
	public int modCall(Call call);
	public int delCall(Map<String,Object> map);
    public int selectCall(Call call);
    public int modUIdNull(Map<String,Object> map);
    public int delCallByMId(Map<String,Object> map);
}