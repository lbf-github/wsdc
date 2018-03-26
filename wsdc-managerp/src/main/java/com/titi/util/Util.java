package com.titi.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Util {
	 public static String umsUrl="http://hb.ums86.com:8899/sms/Api/Send.do?SpCode=208696&LoginName=hb_ct&Password=xr2003&SerialNumber=&ScheduleTime=&f=1";
	 public static String umsQueryUrl="http://hb.ums86.com:8899/sms/Api/Send.do?SpCode=208696&LoginName=hb_ct&Password=xr2003&SerialNumber=&ScheduleTime=&f=1";
	 
	 public static String RECHANGE="RECHANGE-";
	 public static String basePath="http://service.dongqil.com/iSports/";
	 public  static String LogPath="/opt/alipay";
	 public  static boolean debug=false;
	 public  static int sign=2;
	 public  static String imgpath="http://img.dongqil.com/iSports/";
	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		Util.basePath = basePath;
	}

	static {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Object> getMap(String jsonString) {
		
		return null;
	}

	/**
	
	  */
	  public static boolean contains(String[] stringArray, String source) {
	
	   List<String> tempList = Arrays.asList(stringArray);
	   
	  if(tempList.contains(source))
	   {
	    return true;
	   } else {
	    return false;
	   }
	  } 
	  



	public static String Html2Text(String inputString) {
		String htmlStr = inputString; 
		htmlStr=htmlStr.replaceAll("</P>", "\r\n");
		htmlStr=htmlStr.replaceAll("<BR>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("<br>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("<BR/>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("<br/>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("</DIV>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("</div>", "\r\n"); 
		htmlStr=htmlStr.replaceAll("&nbsp;", " ");
		
		String textStr = "";
		Pattern p_script;
		java.util.regex.Matcher m_script;
		Pattern p_style;
		java.util.regex.Matcher m_style;
		Pattern p_html;
		java.util.regex.Matcher m_html;
		Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
			// }
			String regEx_html = "<[^>]+>"; 
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); 

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); 

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); 

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
	
		return textStr;
	}
	public static String Html2json(String inputString,String domain) {
		
		inputString=inputString.replaceAll("<img", "#img#");
		inputString=inputString.replaceAll("<IMG", "#img#");
		inputString=inputString.replaceAll("<A", "#url#");
		inputString=inputString.replaceAll("<a", "#url#");
		inputString=inputString.replaceAll("</a>", "#url#");
		inputString=inputString.replaceAll("</A>", "#url#");
		
		inputString=Html2Text(inputString);
		inputString=inputString.replaceAll("/>", "#img#");
	
		
		String [] strs = inputString.split("#img#");
	List<String> newstrs=new ArrayList<String>();
		 for (String str:strs)
			{ 
			   if (str.indexOf("#url#")!=-1)
			   {
				   for (String str2:str.split("#url#"))
				   {
					   newstrs.add(str2);  
				   }
				   
			   }else
			   {
				   newstrs.add(str); 
			   }
		}
		 
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		
		
		 
	   for (String str:newstrs)
		{  
		   if (str.toLowerCase().indexOf("src")==-1&& str.toLowerCase().indexOf("href")==-1)
		   {
			
		   sb.append("{t:\"1\",n:\""+str+"\"},");
		   
		   } else if (str.toLowerCase().indexOf("src")!=-1)
		   {
              str=str.toLowerCase();
            //  System.out.println("str1:"+str);
		      str=str.substring(str.indexOf("src")+5, str.length());
		     // System.out.println("str2:"+str);
		      str=str.substring(0,str.indexOf("\""));
		      //System.out.println("str3:"+str);
		      if (str.indexOf("http://")==-1)
		      {str=domain+str;}
		     // System.out.println("str4:"+str);
			  sb.append("{t:\"2\",n:\""+str+"\"},");
		   }
		   else if (str.toLowerCase().indexOf("href")!=-1)
		   {
              str=str.toLowerCase();
		      str=str.substring(str.indexOf("href")+6, str.length());
		      str=str.substring(0,str.indexOf("\""));
		      if (str.indexOf("http://")==-1)
		      {str=domain+str;}
			  sb.append("{t:\"3\",n:\""+str+"\"},");
		   }
	   }
	   sb.delete(sb.length()-1, sb.length());
	   sb.append("]");
		return sb.toString();
	}
	

	    /** 
	   
	     */ 
	    public static String GenerateNumber(int LENGTH) {  
	        String no="";  
	        
	        int[] defaultNums = new int[10];  
	        for (int i = 0; i < defaultNums.length; i++) {  
	            defaultNums[i] = i;  
	        }  
	   
	        Random random = new Random();  
	        int[] nums = new int[LENGTH];  
	       
	        int canBeUsed = 10;  
	      
	        for (int i = 0; i < nums.length; i++) {  
	            
	            int index = random.nextInt(canBeUsed);  
	            nums[i] = defaultNums[index];  
	           
	            swap(index, canBeUsed - 1, defaultNums);  
	            canBeUsed--;  
	        }  
	        if (nums.length>0) {  
	            for (int i = 0; i < nums.length; i++) {  
	                no+=nums[i];  
	            }  
	        }  
	   
	        return no;  
	    }  
	      
	   
	    private static void swap(int i, int j, int[] nums) {  
	        int temp = nums[i];  
	        nums[i] = nums[j];  
	        nums[j] = temp;  
	    }  
	       
	    public static String generateNumber2() {  
	        String no="";  
	        int num[]=new int[8];  
	        int c=0;  
	        for (int i = 0; i < 8; i++) {  
	            num[i] = new Random().nextInt(10);  
	            c = num[i];  
	            for (int j = 0; j < i; j++) {  
	                if (num[j] == c) {  
	                    i--;  
	                    break;  
	                }  
	            }  
	        }  
	        if (num.length>0) {  
	            for (int i = 0; i < num.length; i++) {  
	                no+=num[i];  
	            }  
	        }  
	        return no;  
	    }  
	   
		private final static double PI = 3.14159265358979323; 
		private final static double R =  6371.229;
	    public static double getDistance(double longt1, double lat1, double longt2,double lat2) {
	        double x, y, distance;
	        x = (longt2 - longt1) * PI * R
	                * Math.cos(((lat1 + lat2) / 2.0) * PI / 180.0) / 180.0;
	        y = (lat2 - lat1) * PI * R / 180.0;
	        distance = Math.hypot(x, y)*1000.0;
	        return distance;
	    }
		
	    
	    
		public static double getdLongt(double distance,double lat){
		
			double dlng =  2.0 * Math.asin(Math.sin(distance/(2.0 *R))/Math.cos(PI*(lat/180.0)));
			return dlng = (dlng/PI)*180.0;
			
		}
		
		public static double getdLat(double distance,double lat){
			double dlat = distance/R;
			return dlat = (dlat/PI)*180.0;
		}
		
		public void getFourCoordinate(double lng,double lat,double dlat,double dlng){
			List<Double>dList = new ArrayList<Double>();


			double leftTopx = lat+dlat;
			double leftTopy = lng-dlng;
			double rightTopx = lat+dlat;
			double rightTopy =lng+dlng;
			double leftBottomx=lat-dlat;
			double leftBottomy=lng-dlng;
			double rightBottomx=lat-dlat;
			double rightBottomy=lng + dlng;
			
			
			
			

		}
		
		public static String AddDate(Date date,int d) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar cal= Calendar.getInstance();	
			try {
				
				cal.setTime(date);						
			    cal.add(Calendar.DATE, d);
			} catch (Exception e) {
				
				e.printStackTrace();
			}			
			return sdf.format(cal.getTime());
		}
		
	    public static void main(String[] args) {  
	        double jl = getDistance(30.543421,114.3391,30.552090,114.340462);
	            System.out.println(jl); //  
	        
	    }  
	}


