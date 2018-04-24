package com.lbf.wsdc.portal.web;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.CookieUtils;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Administrator
 * Date: 2018/4/13
 * Time: 10:22
 * Version:V1.0
 */
@Controller
@RequestMapping("/protalUser")
public class UserAction {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 跳转到密码修改页面
     */
    @RequestMapping("/toEditPwd")
    public String pwd(Model model, String tokenId , HttpServletRequest request, HttpServletResponse response) {

        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        User user = JsonUtils.jsonToPojo(userJson, User.class);

        model.addAttribute("user",user);

        User oldUser = userService.getUserById(user.getUserid());
        if(oldUser.getPassword() != null){
            model.addAttribute("pwdNull",1);
        }else{
            model.addAttribute("pwdNull",0);
        }
        return "home/editpass";

    }

    /**
     * 密码修改
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public Object updatePwd(Long userId){

        MessageResult mr = null;

        try {
            mr = new MessageResult();
            User user = userService.getUserById(userId);
            String password = request.getParameter("pwd");
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(md5Password);
            int num = userService.updateUser(user,userId);
            if(num>0){
                mr.setSuccess(true);
                mr.setMessage("修改密码成功");

            }else{
                mr.setSuccess(false);
                mr.setMessage("修改密码失败");

            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


        return mr;
    }

    /**
     * 跳转到用户资料修改界面
     */
    @RequestMapping("/editform")
    public String EditForm(Model model, String tokenId , HttpServletRequest request, HttpServletResponse response) {
        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        User user = JsonUtils.jsonToPojo(userJson, User.class);

        model.addAttribute("user",user);
        return "home/edit";
    }


    /**
     * 资料修改
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(User user,String tokenId){

        MessageResult mr = null;

        try {
            mr = new MessageResult();
            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);
            User oldUser = JsonUtils.jsonToPojo(userJson, User.class);
            oldUser.setUsername(user.getUsername());
            oldUser.setSex(user.getSex());
            oldUser.setTel(user.getTel());
            oldUser.setEmail(user.getEmail());
            int num = userService.updateUser(oldUser,user.getUserid());
            if(num>0){
                mr.setSuccess(true);
                mr.setMessage("资料修改成功");

            }else{
                mr.setSuccess(false);
                mr.setMessage("资料修改失败");

            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }


        return mr;
    }

    @RequestMapping("/exit")
    @ResponseBody
    public MessageResult doExit(HttpServletRequest request, HttpServletResponse response){
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            CookieUtils.setCookie(request, response, "login_token", "");
            mr.setSuccess(true);
            mr.setMessage("注销成功");
        }catch (Exception e){
            mr.setSuccess(false);
            mr.setMessage("注销失败");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

}
