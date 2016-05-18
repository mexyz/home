package com.xyz.home.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.home.dao.CallDao;
import com.xyz.home.dao.EarningDao;
import com.xyz.home.dao.MemberDao;
import com.xyz.home.dao.RoleDao;
import com.xyz.home.dao.SpendDao;
import com.xyz.home.model.Call;
import com.xyz.home.model.Member;
import com.xyz.home.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CallDao callDao;
	@Autowired
	private EarningDao earningDao;
	@Autowired
	private SpendDao spendDao;
	@Autowired
	private RoleDao roleDao;
	@Override
	public Object[] selectMember(Map<String, Object> map) {
		return new Object[]{memberDao.selectMember(map),memberDao.selectMemberCount(map)};
	}

	@Override
	public int addMember(Map<String,String> map) {
		Member member=new Member();
		member.setGender(Integer.parseInt(map.get("gender")));
		member.setRealName(map.get("realName"));
		member.setAddDate(new Date());
		memberDao.addMember(member);
		Call call=new Call();
		call.setmId(member.getmId());
		call.setuId(Integer.parseInt(map.get("uId")));
		call.setCallName(map.get("callName"));
		return callDao.addCall(call);
	}

	@Override
	public int delMenber(Map<String,Object> map) {
		earningDao.modMIdNull(map);
		spendDao.modMIdNull(map);
		callDao.delCallByMId(map);
		roleDao.delRoleMemberByMId(map);
		return memberDao.delMenber(map);
	}

	@Override
	public int modMember(Map<String,String> map) {
		Member member=new Member();
		member.setGender(Integer.parseInt(map.get("gender")));
		member.setRealName(map.get("realName"));
		member.setmId(Integer.parseInt(map.get("mId")));;
		memberDao.modMember(member);
		Call call=new Call();
		call.setmId(member.getmId());
		call.setuId(Integer.parseInt(map.get("uId")));
		call.setCallName(map.get("callName"));
		int count=callDao.selectCall(call);
		if(count==0){
			return callDao.addCall(call);
		}else{
			return callDao.modCall(call);
		}
		
		
	}

	@Override
	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) {
		return memberDao.selectMemberList(map);
	}

	@Override
	public List<Map<String, Object>> selectMemberListAll(Map<String, Object> map) {
		return memberDao.selectMemberListAll(map);
	}

}
