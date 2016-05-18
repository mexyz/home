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

import com.xyz.home.model.Spend;
import com.xyz.home.service.SpendService;
import com.xyz.home.util.StringUtil;

@Controller
@RequestMapping("/spend")
public class SpendAction extends BaseAction{
	@Autowired
	private SpendService spendService;
	

	
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	public String spend(HttpServletRequest request){
		request.setAttribute("op", getOpeartion(request));
		return "spend";
	}
	
	
	@RequestMapping(value = "/data",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> spendData(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		String caId=request.getParameter("caId");
		String mId=request.getParameter("mId");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("uId", getUserId(request));
		map.put("order",order);
		map.put("sort", StringUtil.camelToUnderline(sort));
		map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
		map.put("pageSize", rows == 0 ? 10 : rows);
		map.put("caId",(caId==null||"".equals(caId))?null:caId.split(","));
		map.put("mId",(mId==null||"".equals(mId))?null:mId.split(","));
		map.put("beginDate",request.getParameter("beginDate"));
		map.put("endDate",request.getParameter("endDate"));
		map.put("beginMoney",request.getParameter("beginMoney"));
		map.put("endMoney",request.getParameter("endMoney"));
		Object[] obj=spendService.selectSpend(map);
        return tableData(obj[0],obj[1]);  
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> add(Spend spend,HttpServletRequest request) {
		try {
			spend.setCreateDate(new Date());
			if(request.getParameter("szDate")!=null){
				spend.setSpendDate(java.sql.Date.valueOf(request.getParameter("szDate")));
			}
			spendService.addSpend(spend);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    } 
	
	
	@RequestMapping(value = "/mod",method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> mod(Spend spend,HttpServletRequest request) {
		try {
			spendService.modSpend(spend);
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
			spendService.delSpend(map);
			param.put("msg","Y");
		} catch (Exception e) {
			param.put("msg","N");
		}
        return param;
    }
}
