package com.xyz.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xyz.home.model.Call;
import com.xyz.home.model.Member;
@Repository
public interface MemberDao {
	
	public List<Map<String,Object>> selectMember(Map<String,Object> map);
	public int selectMemberCount(Map<String,Object> map);
	public int addMember(Member member);
	public int delMenber(Map<String,Object> map);
	public int modMember(Member member);
	public List<Map<String,Object>> selectMemberList(Map<String,Object> map);
	public List<Map<String,Object>> selectMemberListAll(Map<String,Object> map);
}