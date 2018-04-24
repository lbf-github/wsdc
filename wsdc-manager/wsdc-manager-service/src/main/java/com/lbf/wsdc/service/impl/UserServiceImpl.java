package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.dao.UserMapper;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.pojo.po.UserExample;
import com.lbf.wsdc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/13
 * Time: 10:42
 * Version:V1.0
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        User user = userMapper.selectByExample(example).get(0);

        return user;
    }

    @Override
    public int updateUser(User user, Long userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        int i = userMapper.updateByExampleSelective(user,example);
        return i;
    }

    @Override
    public User getUserByAccount(String account) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andTelEqualTo(account);
        List<User> list= userMapper.selectByExample(example);
        if(list!=null){
            User user = list.get(0);
        }
        return null;
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
