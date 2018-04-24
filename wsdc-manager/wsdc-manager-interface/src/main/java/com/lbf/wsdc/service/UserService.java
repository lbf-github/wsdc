package com.lbf.wsdc.service;

import com.lbf.wsdc.pojo.po.User;

/**
 * User: Administrator
 * Date: 2018/4/13
 * Time: 10:39
 * Version:V1.0
 */
public interface UserService {


    User getUserById(Long userId);

    int updateUser(User user,Long userId);

    User getUserByAccount(String account);

    int addUser(User user);


}
