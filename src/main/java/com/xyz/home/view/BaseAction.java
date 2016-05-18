package com.xyz.home.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.xyz.home.service.ActionService;

public class BaseAction {

    @Autowired
	public ActionService actionService;
	
	public Map<String,Object> param=new HashMap<String,Object>();
	public Map<String,Object> tableData(Object data,Object count){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("total",count);
		map.put("rows",data);
		return map;
	}
	
	public Integer getUserId(HttpServletRequest request){
		Object obj=request.getSession().getAttribute("user");
		if(obj!=null){
			Map<String,String> map=(Map<String, String>) request.getSession().getAttribute("user");
			return Integer.parseInt(map.get("uId"));
		}else{
			return null;
		}
	}
	
	public Integer getRoleId(HttpServletRequest request){
		Object obj=request.getSession().getAttribute("user");
		if(obj!=null){
			Map<String,String> map=(Map<String, String>) request.getSession().getAttribute("user");
			if(map.get("rId")==null){
				return null;
			}else{
				return Integer.parseInt(map.get("rId"));
			}
		}else{
			return null;
		}
	}
	
	public Map<String,Object> getParmeter(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Enumeration<String> pars=request.getParameterNames();
		while(pars.hasMoreElements()){
			String key=pars.nextElement();
			resultMap.put(key, request.getParameter(key));
		}
		return resultMap;
	}
	
	public List<String> getOpeartion(HttpServletRequest request){
		Map<String,Object> map=getParmeter(request);
		map.put("rId",getRoleId(request));
		return actionService.selectDescribe(map);
	}

}
