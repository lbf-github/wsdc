package com.lbf.wsdc.service;

import com.lbf.wsdc.common.dto.MessageResult;
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

    Storemenu getStoreMenuById(Long menuid);

    MessageResult deleteInMenuListCart(Long menuid,String cartId);

    List<Storemenu> getMyStoreMenu(String tokenId);

    MessageResult addMenu(Storemenu menu,String tokenId);

    MessageResult editMenu(Storemenu memu);

    MessageResult deleteMenu(Long menuId);

}
