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
import com.xyz.home.model.Spend;
import com.xyz.home.model.User;
import com.xyz.home.service.MenuService;
import com.xyz.home.service.UserService;
import com.xyz.home.util.MD5Util;
import com.xyz.home.util.StringUtil;

@Controller
@RequestMapping("/system")
public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/page",method = RequestMethod.GET)  
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "user";
	}
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> data(HttpServletRequest request) {
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("order",order);
		map.put("sort", StringUtil.camelToUnderline(sort));
		map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
		map.put("pageSize", rows == 0 ? 10 : rows);
		map.put("userName",request.getParameter("userName"));
		map.put("loginIp",request.getParameter("loginIp"));
		map.put("roleName",request.getParameter("roleName"));
		map.put("beginTime",request.getParameter("beginTime"));
		map.put("endTime",request.getParameter("endTime"));
		Object[] obj=userService.selectUser(map);
        return tableData(obj[0],obj[1]);  
    }
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(User user,HttpServletRequest request) {
		try {
			user.setAddDate(new Date());
			userService.addUser(user);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(User user,HttpServletRequest request) {
		try {
			Map<String,Object> map=getParmeter(request);
			userService.modUser(map);
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
			map.put("sId",request.getParameter("ids").split(","));
			userService.delUser(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    }
}