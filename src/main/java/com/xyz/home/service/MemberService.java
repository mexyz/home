package com.xyz.home.service;

import java.util.List;
import java.util.Map;

import com.xyz.home.model.Member;

public interface MemberService {

	public Object[] selectMember(Map<String,Object> map);
	public int addMember(Map<String,String> map);
	public int delMenber(Map<String,Object> map);
	public int modMember(Map<String,String> map);
	public List<Map<String,Object>> selectMemberList(Map<String,Object> map);
	public List<Map<String,Object>> selectMemberListAll(Map<String,Object> map);
}
