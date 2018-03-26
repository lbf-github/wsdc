package com.titi.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微网通 短信发送
 * @author Administrator
 */
public class Send {
	 public static void main(String[] args) throws UnsupportedEncodingException {
         System.out.println(getSendCode());

		Properties prop = PropertiesUtils.getProperties();
		String sname = prop.getProperty("sname").trim();//账户名称
		String spwd = prop.getProperty("spwd").trim();//账户密码
		String scorpid = prop.getProperty("scorpid").trim();//企业代码
		String sprdid = prop.getProperty("sprdid").trim();//产品编号
		String sdst = "18971256486";
		String smsg = "你好【签名】";
		String postData = "sname="+sname+"&spwd="+spwd+"&scorpid="+scorpid+"&sprdid="+sprdid+"&sdst="+sdst+"&smsg="+java.net.URLEncoder.encode(smsg,"utf-8");
//    	String postData = "sname=dltiti00&spwd=4XaTY0UU&scorpid=&sprdid=101281814&sdst=18971256486&smsg="+java.net.URLEncoder.encode("短信内容【签名】","utf-8");
		String postUrl = prop.getProperty("postUrl").trim();//产品编号
    	String ret = SMS(postData, postUrl);
    	System.out.println(ret);
	}

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

    /**
     * 生成短信验证码
     *
     * @return
     */
    public static String getSendCode() {
		/*
		 * String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
		 * "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
		 * "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		 * };
		 */
        String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

    /**
     * 解析短信发送返回的XML
     * @param protocolXML
     * @return
     */
    public static String parse(String protocolXML) {
        String redultcode = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(
                    protocolXML)));

            Element root = doc.getDocumentElement();
            NodeList books = root.getChildNodes();

            if (books != null) {
                for (int i = 0; i < books.getLength(); i++) {
                    Node book = books.item(i);
                    if (book.getNodeName().equals("State")) {
                        redultcode = book.getFirstChild().getNodeValue();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return redultcode;
    }
   
    /**
     * 
     * @param tel 手机号
     * @param msg 短信内容
     * @throws UnsupportedEncodingException
     */
//    public static void SendMsg(String tel,String msg) throws UnsupportedEncodingException{
//    	Properties prop = PropertiesUtils.getProperties();
//		String sname = prop.getProperty("sname").trim();//账户名称
//		String spwd = prop.getProperty("spwd").trim();//账户密码
//		String scorpid = prop.getProperty("scorpid").trim();//企业代码
//		String sprdid = prop.getProperty("sprdid").trim();//产品编号
//		String postData = "sname="+sname+"&spwd="+spwd+"&scorpid="+scorpid+"&sprdid="+sprdid+"&sdst="+tel+"&smsg="+java.net.URLEncoder.encode(msg,"utf-8");
//		String postUrl = prop.getProperty("postUrl").trim();//产品编号
//    	SMS(postData, postUrl);
//    }
    /**
     * 获取短信发送时间间隔(秒)
     * @param starttime
     * @param endtime
     * @return
     * @throws Exception
     */
    public static int getDistanceTime(String starttime, String endtime)
            throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(starttime);
        Date date2 = sdf.parse(endtime);
        int result = (int) ((date2.getTime() - date1.getTime()) /1000);
        return result;
    }

    /**
     *
     * @param tel
     *            手机号
     * @param msg
     *            短信内容
     * @throws UnsupportedEncodingException
     */
    public static String SendMsg(String tel, String msg)
            throws UnsupportedEncodingException {
        Properties prop = PropertiesUtils.getProperties();
        String sname = prop.getProperty("sname").trim();// 账户名称
        String spwd = prop.getProperty("spwd").trim();// 账户密码
        String scorpid = prop.getProperty("scorpid").trim();// 企业代码
        String sprdid = prop.getProperty("sprdid").trim();// 产品编号
        String postData = "sname=" + sname + "&spwd=" + spwd + "&scorpid="
                + scorpid + "&sprdid=" + sprdid + "&sdst=" + tel + "&smsg="
                + java.net.URLEncoder.encode(msg, "utf-8");
        String postUrl = prop.getProperty("postUrl").trim();// 产品编号
        return SMS(postData, postUrl);
    }
}
