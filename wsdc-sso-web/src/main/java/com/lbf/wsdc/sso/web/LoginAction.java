package com.lbf.wsdc.sso.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.util.CookieUtils;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.service.LoginService;
import com.lbf.wsdc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Administrator
 * Date: 2018/3/10
 * Time: 0:16
 * Version:V1.0
 */
@Controller
public class LoginAction {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public MessageResult doLogin1(String tel, String password, HttpServletRequest request, HttpServletResponse response) {
        MessageResult mr = null;
        try {
            //调用业务逻辑层方法进行登录
            mr = loginService.userLogin(tel, password);
            if (mr.isSuccess()) {
                //登录成功
                String token = mr.getData().toString();
                //写入cookie
                CookieUtils.setCookie(request, response, "login_token", token);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;


    }


    @ResponseBody
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public MessageResult registerin(String phone,String code, String recode){
        MessageResult mr=new MessageResult();
        try {

            if(code==recode){
                mr.setSuccess(false);
                mr.setMessage("手机验证码不正确");
                return mr;
            }else{
                User oldUser = userService.getUserByAccount(phone);
                if(oldUser!=null){
                    mr.setSuccess(true);
                    mr.setMessage("登录成功");
                }else{
                    User user = new User();
                    user.setTel(phone);
                    user.setAccount(phone);
                    user.setLevel(1);
                    user.setStatus(0);
                    int num=userService.addUser(user);
                    if(num!=1){
                        mr.setSuccess(false);
                        mr.setMessage("登录失败");
                        return mr;
                    }
                    mr.setSuccess(true);
                    mr.setMessage("登录成功");
                }

            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

}

