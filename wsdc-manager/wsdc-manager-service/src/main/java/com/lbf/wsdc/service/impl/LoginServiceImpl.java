package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.dao.UserMapper;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.pojo.po.UserExample;
import com.lbf.wsdc.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * User: Administrator
 * Date: 2018/3/10
 * Time: 0:21
 * Version:V1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult userLogin(String tel, String password) {

        MessageResult mr = new MessageResult();
        try {

            //创建模板
            UserExample example=new UserExample();
            UserExample.Criteria criteria=example.createCriteria();
            criteria.andTelEqualTo(tel);

            //执行查询
            List<User> list = userDao.selectByExample(example);
            if (list == null || list.size() == 0) {
                //返回登录失败
                mr.setSuccess(false);
                mr.setMessage("用户名不存在");
                return mr;
            }
            //用户名已存在，这个是从数据库来的
            User user = list.get(0);
            //把前台传递过来密码进行MD5加密一次
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!md5Password.equals(user.getPassword())) {
                mr.setSuccess(false);
                mr.setMessage("用户名或者密码错误");
                return mr;
            }
            //用户名和密码都正确的情况
            String token = UUID.randomUUID().toString();
            //把登录成功的信息写入到redis中
            //将用户信息中密码置空
            user.setPassword(null);
            //用户信息存入缓存，并且设置过期时间，1800s(半小时)
            jedisClient.set("LOGIN_TOKEN:" + token, JsonUtils.objectToJson(user));
            jedisClient.expire("LOGIN_TOKEN:" + token, 1800);
            //正确情况下返回MessageResult
            mr.setSuccess(true);
            mr.setMessage("登录成功");
            mr.setData(token);

        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;


    }
}

