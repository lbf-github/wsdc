package com.lbf.wsdc.service;

import com.lbf.wsdc.pojo.po.Storeinfo;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 20:50
 * Version:V1.0
 */
public interface StoreInfoService {
    List<Storeinfo> getStoreInfo();

    List<Storeinfo> getStoreInfoById(Long id);

    List<Storeinfo> getStoreInfoByTypeId(Integer stypeid);
}
