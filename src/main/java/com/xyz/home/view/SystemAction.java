package com.xyz.home.view;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.home.model.Menu;
import com.xyz.home.model.User;
import com.xyz.home.service.MenuService;
import com.xyz.home.service.UserService;
import com.xyz.home.util.MD5Util;

@Controller
@RequestMapping("/system")
public class SystemAction extends BaseAction{
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)  
    public String main() {
        return "main";  
    }
	
	@RequestMapping(value = "/loginPage",method = RequestMethod.GET)  
    public String loginPage() {
        return "login";  
    }
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)  
    public String index(HttpServletRequest request) {
		request.setAttribute("menu",menuService.selectMenu(getRoleId(request)));
        return "index";  
    } 
	
	@RequestMapping(value = "/menu",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> menu(HttpServletRequest request) {
		List<Menu> list=menuService.selectMenu(getRoleId(request));
		List<Map<String,Object>> tree=new ArrayList<Map<String,Object>>();
        return getTree(tree,list,0);  
    } 
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> login(User user,HttpServletRequest request) {
		user.setPassword(MD5Util.md5Encode(user.getPassword()));
		List<User> l=userService.selectUserLogin(user);
		if(l.isEmpty()){
			param.put("msg","N");
		}else{
			user=l.get(0);
			String lastloginTime=null;
			if(user.getLoginTime()!=null){
				lastloginTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLoginTime());
			}
			user.setLoginTime(new Date());
			user.setLoginIp(request.getRemoteAddr());
			userService.modUserLogInfo(user);
			Map<String,String> map=new HashMap<String,String>();
			map.put("userName",user.getUserName());
			map.put("roleName",user.getRoleName());
			map.put("uId",String.valueOf(user.getuId()));
			map.put("rId",String.valueOf(user.getrId()));
			map.put("loginTime",lastloginTime);
			request.getSession().setAttribute("user",map);
			param.put("msg","Y");
		}
        return param;  
    } 
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)  
    public void logout(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getSession().removeAttribute("user");
			response.sendRedirect("../login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 
	
	private List<Map<String,Object>> getTree(List<Map<String,Object>> tree,List<Menu> list,int id){
		for(int i=0;i<list.size();i++){
			Menu m=list.get(i);
			if(m.getPid()==id){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.getId());
				map.put("text",m.getText());
				map.put("children",getTreeChildren(list,m.getId()));
				map.put("iconCls",m.getIcon());
				Map<String,Object> attr=new HashMap<String, Object>();
				attr.put("url",m.getHref());
				map.put("attributes", attr);
				tree.add(map);
			}
		}
		return tree;
	}
	
	
	private List<Map<String,Object>> getTreeChildren(List<Menu> list,int id){
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		for(Menu m:list){
			if(m.getPid()==id){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.getId());
				map.put("text",m.getText());
				map.put("iconCls",m.getIcon());
				Map<String,Object> attr=new HashMap<String, Object>();
				attr.put("url",m.getHref());
				map.put("attributes", attr);
				List<Map<String,Object>> c=getTreeChildren(list,m.getId());
				if(!c.isEmpty()){
					map.put("children",c);
				}
				l.add(map);
			}
		}
		return l;
	}
}