package com.lbf.wsdc.service.impl;


import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Administrator
 * Date: 2017/12/3
 * Time: 12:54
 * Version:V1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult getUserByToken(String tokenId) {
        MessageResult mr = new MessageResult();
        try {
            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);
            //userJson为空时
            if (StringUtils.isBlank(userJson)){
                mr.setSuccess(false);
                mr.setMessage("登录已经过期");
                return mr;
            }
            //redis集群中存在该用户名而且在有效时间之内
            //充值半小时
            jedisClient.expire("LOGIN_TOKEN:" + tokenId, 1800);
            User user = JsonUtils.jsonToPojo(userJson, User.class);
            //返回messageresult
            mr.setSuccess(true);
            mr.setMessage("欢迎回来");
            mr.setData(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mr;
    }
}

