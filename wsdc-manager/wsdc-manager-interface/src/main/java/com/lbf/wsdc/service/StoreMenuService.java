package com.lbf.wsdc.service;

import com.lbf.wsdc.pojo.po.Storemenu;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 22:29
 * Version:V1.0
 */
public interface StoreMenuService {

    List<Storemenu> getListById(Long id);

}
