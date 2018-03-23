package com.lbf.wsdc.service;

import com.lbf.wsdc.common.dto.MessageResult;

/**
 * User: Administrator
 * Date: 2018/3/10
 * Time: 0:19
 * Version:V1.0
 */
public interface LoginService {
     MessageResult userLogin(String tel,String password);
}
