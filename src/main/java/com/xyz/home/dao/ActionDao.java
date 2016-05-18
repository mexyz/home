package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Action;
@Repository
public interface ActionDao {
    public List<Map<String,Object>> selectActions(Map<String,Object> map);
    public List<String> selectDescribe(Map<String,Object> map);
}