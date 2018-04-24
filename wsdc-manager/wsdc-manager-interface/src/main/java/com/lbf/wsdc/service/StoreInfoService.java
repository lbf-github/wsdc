package com.lbf.wsdc.service;

import com.lbf.wsdc.common.dto.MessageResult;
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

    MessageResult insetStoreInfo(Storeinfo po,String tokenId);

    MessageResult updateStoreInfo(Storeinfo po,String tokenId,Integer typeId);

    Storeinfo getByTokenId(String tokenId);

    MessageResult openOrCloseStore(int flag,String tokenId);

}
