package com.xyz.home.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	/**
	 * 获取某月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month);     
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
       return  new  SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());  
    } 
}
