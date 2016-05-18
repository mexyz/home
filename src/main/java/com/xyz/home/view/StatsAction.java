package com.xyz.home.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.home.service.StatsService;
import com.xyz.home.util.StringUtil;

@Controller
@RequestMapping("/stats")
public class StatsAction extends BaseAction{

	@Autowired
	private StatsService statsService;
	
	@RequestMapping(value = "/timeStatsPage",method = RequestMethod.GET)  
	public String timeStatsPage(HttpServletRequest request){
		return "timeStats";
	}
	
	@RequestMapping(value = "/timeStatsData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> timeStatsData(HttpServletRequest request){
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		String caId=request.getParameter("caId");
		String mId=request.getParameter("mId");
		Map<String,Object> map=getParmeter(request);
		map.put("uId", getUserId(request));
		map.put("rId",getRoleId(request));
		map.put("order",order);
		map.put("sort", "szDate".equals(sort)?"szDate":StringUtil.camelToUnderline(sort));
		Integer page=null;
		Integer rows=null;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
			rows=Integer.parseInt(request.getParameter("rows"));
			map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
			map.put("pageSize", rows == 0 ? 10 : rows);
		}
		map.put("caId",(caId==null||"".equals(caId))?null:caId.split(","));
		map.put("mId",(mId==null||"".equals(mId))?null:mId.split(","));
		map.put("beginDate",request.getParameter("beginDate"));
		map.put("endDate",request.getParameter("endDate"));
		map.put("beginMoney",request.getParameter("beginMoney"));
		map.put("endMoney",request.getParameter("endMoney"));
		Object[] obj=statsService.selectTimeStats(map);
        return tableData(obj[0],obj[1]);  
	}
	
	@RequestMapping(value = "/categoryStatsPage",method = RequestMethod.GET)  
	public String categoryStatsPage(HttpServletRequest request){
		return "categoryStats";
	}
	
	@RequestMapping(value = "/categoryStatsData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> categoryStatsData(HttpServletRequest request){
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		String caId=request.getParameter("caId");
		String mId=request.getParameter("mId");
		Map<String,Object> map=getParmeter(request);
		map.put("uId", getUserId(request));
		map.put("rId",getRoleId(request));
		map.put("order",order);
		map.put("sort", "szDate".equals(sort)?"szDate":StringUtil.camelToUnderline(sort));
		Integer page=null;
		Integer rows=null;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
			rows=Integer.parseInt(request.getParameter("rows"));
			map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
			map.put("pageSize", rows == 0 ? 10 : rows);
		}
		map.put("caId",(caId==null||"".equals(caId))?null:caId.split(","));
		map.put("mId",(mId==null||"".equals(mId))?null:mId.split(","));
		map.put("beginDate",request.getParameter("beginDate"));
		map.put("endDate",request.getParameter("endDate"));
		map.put("beginMoney",request.getParameter("beginMoney"));
		map.put("endMoney",request.getParameter("endMoney"));
		Object[] obj=statsService.selectCategoryStats(map);
        return tableData(obj[0],obj[1]);
	}
	
	@RequestMapping(value = "/memberStatsPage",method = RequestMethod.GET)  
	public String memberStatsPage(HttpServletRequest request){
		return "memberStats";
	}
	
	@RequestMapping(value = "/memberStatsData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> memberStatsData(HttpServletRequest request){
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		String caId=request.getParameter("caId");
		String mId=request.getParameter("mId");
		Map<String,Object> map=getParmeter(request);
		map.put("uId", getUserId(request));
		map.put("rId",getRoleId(request));
		map.put("order",order);
		map.put("sort", "szDate".equals(sort)?"szDate":StringUtil.camelToUnderline(sort));
		Integer page=null;
		Integer rows=null;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
			rows=Integer.parseInt(request.getParameter("rows"));
			map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
			map.put("pageSize", rows == 0 ? 10 : rows);
		}
		map.put("caId",(caId==null||"".equals(caId))?null:caId.split(","));
		map.put("mId",(mId==null||"".equals(mId))?null:mId.split(","));
		map.put("beginDate",request.getParameter("beginDate"));
		map.put("endDate",request.getParameter("endDate"));
		map.put("beginMoney",request.getParameter("beginMoney"));
		map.put("endMoney",request.getParameter("endMoney"));
		Object[] obj=statsService.selectMemberStats(map);
        return tableData(obj[0],obj[1]);
	}
	
	
	@RequestMapping(value = "/earningSpendStatsPage",method = RequestMethod.GET)  
	public String earningSpendStatsPage(HttpServletRequest request){
		return "earningSpendStats";
	}
	
	@RequestMapping(value = "/earningSpendStatsData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> earningSpendStatsData(HttpServletRequest request){
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		Map<String,Object> map=getParmeter(request);
		map.put("order",order);
		map.put("rId",getRoleId(request));
		map.put("sort", "szDate".equals(sort)?"szDate":StringUtil.camelToUnderline(sort));
		Integer page=null;
		Integer rows=null;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
			rows=Integer.parseInt(request.getParameter("rows"));
			map.put("start", ((page == 0? 1 : page)- 1)*(rows == 0 ? 20 : rows));
			map.put("pageSize", rows == 0 ? 10 : rows);
		}
		map.put("beginDate",request.getParameter("beginDate"));
		map.put("endDate",request.getParameter("endDate"));
		Object[] obj=statsService.selectEarningSpendStats(map);
        return tableData(obj[0],obj[1]);
	}
}
