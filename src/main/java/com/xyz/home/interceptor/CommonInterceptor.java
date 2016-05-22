package com.xyz.home.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.home.cache.AuthCache;

public class CommonInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url=request.getRequestURL().toString();//获取当前的url
		url=url.split("\\?")[0];
		Object obj=request.getSession().getAttribute("user");
		if(obj==null){//用户未登录
			
			if(url.endsWith("login")||url.endsWith("loginPage")){
				return true;
			}else{
				response.sendRedirect("../system/loginPage");
				return false;
			}
		}else{//用户已登录
			if(url.endsWith("add")||url.endsWith("mod")||url.endsWith("del")){
				Map<String,String> map=(Map<String, String>) obj;
				//从权限缓存中查询当前角色是否有调用权限
				List<String> list=(List<String>) AuthCache.getCacheMap(map.get("rId"));
				boolean f=false;
				for(String u:list){
					if(url.endsWith(u)){
						f=true;
						break;
					}
				}
				return f;
			}else{
				return true;
			}
		}
		
	}

}
