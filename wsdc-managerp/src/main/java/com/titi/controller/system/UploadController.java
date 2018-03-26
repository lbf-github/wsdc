package com.titi.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.titi.controller.index.BaseController;
import com.titi.util.QiNiuUpload;

/**
 * 七牛云上传action
 * @author 刘放
 *
 */
@Controller
@RequestMapping("/qiniuUpload/")
public class UploadController extends BaseController {
	
	@RequestMapping(value = "/upload", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET })
    public void uploadLogo2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
    	String output="";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (!Objects.equals(myFileName.trim(), "")) {
                       float maxfilesize = 1;
                       if(file.getSize() > maxfilesize * 1024 * 1024) {
                   		   output = "{\"output\":null,\"code\":\"2000\",\"msg\":\"上传文件不能超过"+maxfilesize+"M\"}";
                       }
                        String newFileName = String.valueOf(new Date().getTime()) + Math.round(Math.random()*8999+1000) + myFileName.substring(myFileName.indexOf("."));
                        String dir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            			new QiNiuUpload().upload(file.getBytes(), dir+"/"+newFileName);
            			output = "{\"output\":null,\"code\":\"1000\",\"msg\":\""+dir+"/"+newFileName+"\"}";
                    }
                }
            }

        }
        response.setContentType("text/html");

        response.getWriter().write(output);
    }
		
}