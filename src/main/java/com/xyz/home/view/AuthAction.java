package com.xyz.home.view;

import java.util.ArrayList;
import java.util.Date;
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
import com.xyz.home.service.ActionService;
import com.xyz.home.service.AuthService;
import com.xyz.home.service.MemberService;
import com.xyz.home.service.MenuService;
import com.xyz.home.service.RoleService;
import com.xyz.home.util.MD5Util;
import com.xyz.home.util.StringUtil;
@Controller
@RequestMapping("/auth")
public class AuthAction extends BaseAction{
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private ActionService actionService;
	@Autowired
	private AuthService authService;
	@RequestMapping(value = "/page",method = RequestMethod.GET)  
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "auth";
	}
	
	@RequestMapping(value = "/role",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> role(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		//map.put("uId",getUserId(request));
		//map.put("rId",getRoleId(request));
		return roleService.selectRoleList(map);
    }
	
	@RequestMapping(value = "/member",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> member(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uId",getUserId(request));
		map.put("rId","".equals(request.getParameter("rId"))?0:request.getParameter("rId"));
		return memberService.selectMemberListAll(map);
    }
	
	
	@RequestMapping(value = "/menu",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> menu(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uId",getUserId(request));
		map.put("rId","".equals(request.getParameter("rId"))?0:request.getParameter("rId"));
		
		List<Map<String,Object>> list=menuService.selectMenuList(map);
		return list;
    }
	
	
	@RequestMapping(value = "/action",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> action(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uId",getUserId(request));
		map.put("rId",request.getParameter("rId"));
		return actionService.selectActions(map);
    }
	
	
	private List<Map<String,Object>> getTree(List<Map<String,Object>> tree,List<Map<String,Object>> list,String id){
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=list.get(i);
			if("0".equals(String.valueOf(m.get("pId")))){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.get("mId"));
				map.put("pid",m.get("pId"));
				map.put("text",m.get("text"));
				map.put("rId",m.get("rId"));
				map.put("children",getTreeChildren(list,String.valueOf(m.get("mId"))));
				map.put("iconCls",m.get("icon"));
				tree.add(map);
			}
		}
		return tree;
	}
	
	
	private List<Map<String,Object>> getTreeChildren(List<Map<String,Object>> list,String id){
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> m:list){
			if(id.equals(String.valueOf(m.get("pId")))){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.get("mId"));
				map.put("pid",m.get("pId"));
				map.put("text",m.get("text"));
				map.put("iconCls",m.get("icon"));
				map.put("rId",m.get("rId"));
				List<Map<String,Object>> c=getTreeChildren(list,String.valueOf(m.get("mId")));
				if(!c.isEmpty()){
					map.put("children",c);
				}
				l.add(map);
			}
		}
		return l;
	}
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> mod(HttpServletRequest request){
		try {
			authService.modAuth(getParmeter(request));
			param.put("msg","Y");
		} catch (Exception e) {
			e.printStackTrace();
			param.put("msg","N");
		}
        return param;
	}
}
