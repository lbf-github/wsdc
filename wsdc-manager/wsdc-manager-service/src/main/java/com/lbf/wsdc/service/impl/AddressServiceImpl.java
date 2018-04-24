package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.dao.UaddressMapper;
import com.lbf.wsdc.pojo.po.Uaddress;
import com.lbf.wsdc.pojo.po.UaddressExample;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/17
 * Time: 11:05
 * Version:V1.0
 */
@Service
public class AddressServiceImpl implements AddressService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UaddressMapper uaddressMapper;

    @Override
    public List<Uaddress> getMyAddress(String tokenId) {

        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        User user = JsonUtils.jsonToPojo(userJson, User.class);

        UaddressExample example = new UaddressExample();
        UaddressExample.Criteria criteria = example.createCriteria();
        criteria.andUaccountEqualTo(user.getAccount());

        List<Uaddress> list = uaddressMapper.selectByExample(example);

        return list;
    }

    @Override
    public MessageResult deleteAddressByAccountId(Long addressid) {
        MessageResult mr = null;
        try {

            UaddressExample example = new UaddressExample();
            UaddressExample.Criteria criteria = example.createCriteria();
            criteria.andAddressidEqualTo(addressid);

            int i = uaddressMapper.deleteByExample(example);
            if(i>0){
                mr = new MessageResult();
                mr.setSuccess(true);
                mr.setMessage("删除地址成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("删除地址失败");

            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public Uaddress getUaddressById(Long addressId) {
        Uaddress uaddress = null;
        try {

            UaddressExample example = new UaddressExample();
            UaddressExample.Criteria criteria = example.createCriteria();
            criteria.andAddressidEqualTo(addressId);

            List<Uaddress> list = uaddressMapper.selectByExample(example);

            if(list != null){
                uaddress = list.get(0);
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return uaddress;
    }

    @Override
    public MessageResult updateAddress(Uaddress uaddress) {
        MessageResult mr =null;
        try {
            mr = new MessageResult();
            int num = uaddressMapper.updateAddress(uaddress);
            if(num>0){
                mr.setSuccess(true);
                mr.setMessage("修改地址成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("修改地址失败");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }


    @Override
    public MessageResult insertAddress(Uaddress uaddress, String tokenId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            uaddress.setUaccount(user.getAccount());

            int num = uaddressMapper.insert(uaddress);
            if(num>0){
                mr.setSuccess(true);
                mr.setMessage("新增收货地址成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("新增收货地址失败");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }


        return mr;
    }
}
