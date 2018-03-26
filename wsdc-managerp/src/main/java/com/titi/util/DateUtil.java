package com.titi.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 有关日期时间处理的一些方法
 * @author Yu
 */
public class DateUtil {
	
	/**
	 * 获取当前时间
	 * @return	当前的时间
	 */
	public static String getNow(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 获取当前天
	 * @return	当前的时间
	 */
	public static String getDay(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
     * 以友好的方式显示时间
     * @param time
     * @return
     */
    public static String friendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);
         
        if(ct == 0) {
            return "刚刚";
        }
         
        if(ct > 0 && ct < 60) {
            return ct + "秒前";
        }
         
        if(ct >= 60 && ct < 3600) {
            return Math.max(ct / 60,1) + "分钟前";
        }
        if(ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if(ct >= 86400 && ct < 2592000){ //86400 * 30
            int day = ct / 86400 ;           
            return day + "天前";
        }
        if(ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }
    
    /**
     * 将字符串转换为Date类型
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将字符串转换为Date类型
     * @param str
     * @return
     */
    public static Date stringToDate2(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 格式化字符串，解决从MySql中查出来的数据后面多了0的问题
     * @param date
     * @return
     */
    public static String formatDate(Date date){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    /**
     * 格式化字符串，解决从MySql中查出来的数据后面多了0的问题
     * @param timestamp
     * @return
     */
    public static String getDateFormat(Timestamp timestamp){
    	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
    	String date = sDateFormat.format(timestamp);
    	return date;  
    }
    
    /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     *  
     * @param beginDate 
     * @param endDate 
     * @return List 
     */  
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
	   	 List<Date> lDate = new ArrayList<Date>(); 
	   	 if(endDate.equals(beginDate)){
	   		 lDate.add(endDate);
	   		 return lDate;
	   	 }else{
	   		 lDate.add(beginDate);// 把开始时间加入集合  
	            Calendar cal = Calendar.getInstance();  
	            // 使用给定的 Date 设置此 Calendar 的时间  
	            cal.setTime(beginDate);  
	            boolean bContinue = true;  
	            while (bContinue) {  
	                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
	                cal.add(Calendar.DAY_OF_MONTH, 1);  
	                // 测试此日期是否在指定日期之后  
	                if (endDate.after(cal.getTime())) {  
	                    lDate.add(cal.getTime());  
	                } else {  
	                    break;  
	                }  
	            }  
	            lDate.add(endDate);// 把结束时间加入集合  
	            return lDate;  
	   	 }
   } 
}
