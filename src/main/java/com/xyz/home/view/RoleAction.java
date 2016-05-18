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

import com.xyz.home.model.Role;
import com.xyz.home.service.RoleService;
import com.xyz.home.util.StringUtil;

@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction{
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/comboboxData",method = RequestMethod.POST)
	@ResponseBody
    public List<Map<String,Object>> comboboxData(HttpServletRequest request) {
		return roleService.selectRoleCombo();
    }
	
	@RequestMapping(value = "/page",method = RequestMethod.GET)  
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "role";
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
		map.put("roleName",request.getParameter("roleName"));
		Object[] obj=roleService.selectRole(map);
        return tableData(obj[0],obj[1]);  
    }
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(Role role,HttpServletRequest request) {
		try {
			role.setAddDate(new Date());
			roleService.addRole(role);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(Role role,HttpServletRequest request) {
		Map<String,String> map=new HashMap<String, String>();
		try {
			roleService.modRole(role);
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
			roleService.delRole(map);
			param.put("msg","Y");
		} catch (Exception e) {
			e.printStackTrace();
			param.put("msg","N");
		}
        return param;
    }
	
	@RequestMapping(value = "/validateRoleName",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> validateRoleName(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleName",request.getParameter("roleName"));
		String rId=request.getParameter("rId");
		List<Map<String,Object>> l=roleService.selectRoleList(map);
		if(l.size()==0){
			param.put("msg","Y");
		}else{
			boolean t=true;
			for(Map<String,Object> m:l){
				if(!String.valueOf(m.get("rId")).equals(rId)&&String.valueOf(m.get("roleName")).equals(request.getParameter("roleName"))){
					
					t=false;
					break;
				}
			}
			if(t){
				param.put("msg","Y");
			}else{
				param.put("msg","N");
			}
			
		}
        return param;
    }
}
