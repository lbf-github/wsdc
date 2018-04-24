package com.lbf.wsdc.service;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.pojo.po.Uaddress;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/17
 * Time: 11:03
 * Version:V1.0
 */
public interface AddressService {
    List<Uaddress> getMyAddress(String tokenId);

    MessageResult deleteAddressByAccountId(Long addressid);

    Uaddress getUaddressById(Long addressId);

    MessageResult updateAddress(Uaddress uaddress);

    MessageResult insertAddress(Uaddress uaddress,String tokenId);
}
