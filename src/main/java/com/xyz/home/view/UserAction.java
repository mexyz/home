package com.xyz.home.view;

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

import sun.security.provider.MD5;
import sun.security.rsa.RSASignature.MD5withRSA;

import com.xyz.home.model.User;
import com.xyz.home.service.UserService;
import com.xyz.home.util.MD5Util;
import com.xyz.home.util.StringUtil;
@Controller
@RequestMapping("/user")
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
			user.setPassword(MD5Util.md5Encode(user.getPassword()));
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
    public Map<String,Object> mod(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("uId",request.getParameter("uId"));
			if(request.getParameter("password")!=null){
				map.put("password",MD5Util.md5Encode(request.getParameter("password")));
			}
			map.put("rId",request.getParameter("rId"));
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
			map.put("ids",request.getParameter("ids").split(","));
			userService.delUser(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    }
	
	
	@RequestMapping(value = "/validateUserName",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> validateUserName(HttpServletRequest request) {
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userName",request.getParameter("userName"));
		List<User> l=userService.selectUserList(map);
		if(l.size()==0){
			param.put("msg","Y");
		}else{
			param.put("msg","N");
		}
        return param;
    }
}
