package com.lbf.wsdc.service;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.pojo.po.Uorder;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/18
 * Time: 8:47
 * Version:V1.0
 */
public interface OrderService {

    MessageResult makeOeder(Long addressid,String cartId,String tokenId);

    List<Uorder> getOrders(String tokenId);

    MessageResult updateOrder(Uorder uorder);

    List<Uorder> getStoreOrders(String tokenId);

    MessageResult updateState(Long orderId);
}
