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

import com.xyz.home.model.Category;
import com.xyz.home.model.Menu;
import com.xyz.home.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryAction extends BaseAction{
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/page",method = RequestMethod.GET)  
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "category";
	}
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> data(Category category,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,Object>> list=categoryService.selectCategory(category);
		for(Map<String,Object> m:list){
			if("0".equals(String.valueOf(m.get("_parentId")))){
				m.put("state","closed");
				m.remove("_parentId");
			}
		}
		map.put("rows", list);
        return map;  
    }
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(Category category,HttpServletRequest request) {
		try {
			categoryService.addCategory(category);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(Category category,HttpServletRequest request) {
		try {
			categoryService.modCategory(category);
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
			categoryService.delCategory(map);
			param.put("msg","Y");
		} catch (Exception e) {
			e.printStackTrace();
			param.put("msg","N");
		}
        return param;
    }
	
	
	@RequestMapping(value = "/validateCategory",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> validateCategory(Category category,HttpServletRequest request) {
		
			List<Map<String,Object>> l=categoryService.selectCategory(category);
			if(l.size()==0){
				param.put("msg","Y");
			}else if(l.size()==1){
				Map<String,Object> map=l.get(0);
				if(String.valueOf(map.get("caId")).equals(request.getParameter("caId"))){
					param.put("msg","Y");
				}else{
					param.put("msg","N");
				}
			}else{
				param.put("msg","N");
			}
        return param;
    }
	
	@RequestMapping(value = "/treeData",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> treeData(Category category,HttpServletRequest request) {
		List<Map<String,Object>> tree=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list=categoryService.selectCategory(category);
        return getTree(tree,list,"0");  
    }
	
	private List<Map<String,Object>> getTree(List<Map<String,Object>> tree,List<Map<String,Object>> list,String id){
		for(int i=0;i<list.size();i++){
			Map<String,Object> m=list.get(i);
			if(id.equals(String.valueOf(m.get("_parentId")))){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.get("id"));
				map.put("text",m.get("name"));
				map.put("state","closed");
				map.put("children",getTreeChildren(list,String.valueOf(m.get("id"))));
				tree.add(map);
			}
		}
		return tree;
	}
	
	
	private List<Map<String,Object>> getTreeChildren(List<Map<String,Object>> list,String id){
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> m:list){
			if(id.equals(String.valueOf(m.get("_parentId")))){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",m.get("id"));
				map.put("text",m.get("name"));
				List<Map<String,Object>> c=getTreeChildren(list,String.valueOf(m.get("id")));
				if(!c.isEmpty()){
					map.put("children",c);
				}
				l.add(map);
			}
		}
		return l;
	}
}
