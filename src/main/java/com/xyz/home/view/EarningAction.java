package com.xyz.home.view;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.home.model.Earning;
import com.xyz.home.service.EarningService;
import com.xyz.home.util.StringUtil;

@Controller
@RequestMapping("/earning")
public class EarningAction extends BaseAction{
	@Autowired
	private EarningService earningService;
	
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	public String list(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "earning";
	}
	

	
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> data(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		String sort=request.getParameter("sort");
		String caId=request.getParameter("caId");
		String mId=request.getParameter("mId");
		Map<String,Object> map=getParmeter(request);
		map.put("uId", getUserId(request));
		map.put("sort", StringUtil.camelToUnderline(sort));
		map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
		map.put("pageSize", rows == 0 ? 10 : rows);
		map.put("caId",(caId==null||"".equals(caId))?null:caId.split(","));
		map.put("mId",(mId==null||"".equals(mId))?null:mId.split(","));
		Object[] obj=earningService.selectEarning(map);
        return tableData(obj[0],obj[1]);  
	}

	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(Earning earning,HttpServletRequest request) {
		try {
			earning.setCreateDate(new Date());
			if(request.getParameter("szDate")!=null){
				earning.setEarningDate(java.sql.Date.valueOf(request.getParameter("szDate")));
			}
			earningService.addEarning(earning);
			param.put("msg","Y");
		} catch (Exception e) {
			e.printStackTrace();
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(Earning earning,HttpServletRequest request) {
		try {
			earningService.modEarning(earning);
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
			map.put("eId",request.getParameter("ids").split(","));
			earningService.delEarning(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    }
}
