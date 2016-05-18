package com.xyz.home.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.home.model.Menu;
import com.xyz.home.model.User;
import com.xyz.home.service.MemberService;
import com.xyz.home.util.StringUtil;
@Controller
@RequestMapping("/member")
public class MemberAction extends BaseAction{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/page",method = RequestMethod.GET)  
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "member";
	}
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> data(HttpServletRequest request) {
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId",getUserId(request));
		map.put("rId",getRoleId(request));
		map.put("order",order);
		map.put("sort", StringUtil.camelToUnderline(sort));
		map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
		map.put("pageSize", rows == 0 ? 10 : rows);
		map.put("realName",request.getParameter("realName"));
		Object[] obj=memberService.selectMember(map);
        return tableData(obj[0],obj[1]);  
    }
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request) {
		Map<String,String> map=new HashMap<String, String>();
		try {
			map.put("gender",request.getParameter("gender"));
			map.put("realName",request.getParameter("realName"));
			map.put("uId",String.valueOf(getUserId(request)));
			map.put("callName",request.getParameter("callName"));
			memberService.addMember(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(HttpServletRequest request) {
		Map<String,String> map=new HashMap<String, String>();
		try {
			map.put("mId",request.getParameter("mId"));
			map.put("gender",request.getParameter("gender"));
			map.put("realName",request.getParameter("realName"));
			map.put("uId",String.valueOf(getUserId(request)));
			map.put("callName",request.getParameter("callName"));
			memberService.modMember(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("ids",request.getParameter("ids").split(","));
			map.put("uId", getUserId(request));
			memberService.delMenber(map);
			param.put("msg","Y");
		} catch (Exception e) {
			e.printStackTrace();
			param.put("msg","N");
		}
        return param;
    }
	
	
	@RequestMapping(value = "/comboboxData",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> comboboxData(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		map.put("uId", getUserId(request));
		map.put("rId", getRoleId(request));
		List<Map<String,Object>> list=memberService.selectMemberList(map);
		for(Map<String,Object> m:list){
			m.put("callName", m.get("callName")==null?m.get("realName"):m.get("callName"));
			l.add(m);
		}
		return l;
    }
}
