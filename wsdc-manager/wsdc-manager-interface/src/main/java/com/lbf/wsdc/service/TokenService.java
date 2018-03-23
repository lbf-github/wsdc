package com.lbf.wsdc.service;


import com.lbf.wsdc.common.dto.MessageResult;

/**
 * User: Administrator
 * Date: 2017/12/3
 * Time: 12:53
 * Version:V1.0
 */
public interface TokenService {

    MessageResult getUserByToken(String tokenId);

}
