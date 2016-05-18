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
import com.xyz.home.service.MenuService;
import com.xyz.home.service.UserService;

@Controller
@RequestMapping("/menu")
public class MenuAction extends BaseAction{
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)  
    public String main() {
        return "main";  
    } 
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> data(HttpServletRequest request) {
		List<Menu> list=menuService.selectMenu(null);
		List<Map<String,Object>> tree=new ArrayList<Map<String,Object>>();
        return getTree(tree,list,0);  
    } 
	
	@RequestMapping(value = "/menu",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> menu(HttpServletRequest request) {
		List<Menu> list=menuService.selectMenu(getRoleId(request));
		List<Map<String,Object>> tree=new ArrayList<Map<String,Object>>();
        return getTree(tree,list,0);  
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