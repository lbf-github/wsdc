package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.IDUtils;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.dao.StoreinfoMapper;
import com.lbf.wsdc.dao.UorderMapper;
import com.lbf.wsdc.pojo.po.*;
import com.lbf.wsdc.pojo.vo.ShoppingCart;
import com.lbf.wsdc.service.OrderService;
import com.lbf.wsdc.service.StoreInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * User: Administrator
 * Date: 2018/4/18
 * Time: 8:48
 * Version:V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UorderMapper uorderMapper;

    @Autowired
    private StoreinfoMapper storeinfoMapper;

    @Override
    public MessageResult makeOeder(Long addressid, String cartId, String tokenId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            String cartJson = jedisClient.get("Cart_TOKEN:" + cartId);

            List<ShoppingCart> menuListCart = JsonUtils.jsonToList(cartJson,ShoppingCart.class);

            //判断商家id
            List<Long> storeid =new ArrayList<>();
            for(int i=0;i<menuListCart.size();i++){
                storeid.add(menuListCart.get(i).getStoreid());
            }
            Set<Long> c = new HashSet<>();
            c.addAll(storeid);
            List<Long> newStoreid = new ArrayList<>(c);
            List<Uorder> orders = new ArrayList<>();
            for(int i=0;i<newStoreid.size();i++){
                Uorder uorder = new Uorder();
                uorder.setOrdercode(IDUtils.getItemId()+"");
                uorder.setUaccount(user.getAccount());
                uorder.setStoreid(newStoreid.get(i));
                uorder.setUaddressid(new Integer(addressid.intValue()));
                uorder.setOrderdate(new Date());
                uorder.setState(0);
                for(int j=0;j<menuListCart.size();j++){
                    if(newStoreid.get(i)==menuListCart.get(j).getStoreid()){
                        String foodid = uorder.getOrderfoodid();
                        if(foodid != null){
                            uorder.setOrderfoodid(foodid+menuListCart.get(j).getMenuid()+",");
                        }else{
                            uorder.setOrderfoodid(menuListCart.get(j).getMenuid()+",");
                        }

                        String str = uorder.getOrderfoodpricenum();
                        if(str != null){
                            uorder.setOrderfoodpricenum(str+menuListCart.get(j).getNum()+",");
                        }else{
                            uorder.setOrderfoodpricenum(menuListCart.get(j).getNum()+",");
                        }

                        String money = uorder.getUnote();
                        if(money != null){
                            uorder.setUnote(Float.parseFloat(money)+menuListCart.get(j).getNum()*menuListCart.get(j).getFoodprices()+"");
                        }else{
                            uorder.setUnote(menuListCart.get(j).getNum()*menuListCart.get(j).getFoodprices()+"");
                        }

                    }
                }
                orders.add(uorder);
            }

            for(int i=0;i<orders.size();i++){
                uorderMapper.insert(orders.get(i));
            }

            mr.setSuccess(true);
            mr.setMessage("生成订单成功");


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            mr.setSuccess(false);
            mr.setMessage("生成订单失败");
        }
        return mr;
    }


    @Override
    public List<Uorder> getOrders(String tokenId) {

        List<Uorder> uorders = null;

        try {

            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            UorderExample example = new UorderExample();
            UorderExample.Criteria criteria = example.createCriteria();
            criteria.andUaccountEqualTo(user.getAccount());

            uorders = uorderMapper.selectByExample(example);




        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return uorders;
    }

    @Override
    public MessageResult updateOrder(Uorder uorder) {
        MessageResult mr = null;
        try{
            mr = new MessageResult();
            int i = uorderMapper.updateComment(uorder);
            if(i>0){
                mr.setSuccess(true);
                mr.setMessage("评论成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("评论失败");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }


    @Override
    public List<Uorder> getStoreOrders(String tokenId) {
        List<Uorder> uorders = null;

        try {

            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            StoreinfoExample example = new StoreinfoExample();
            StoreinfoExample.Criteria criteria = example.createCriteria();
            criteria.andUaccountEqualTo(user.getAccount());
            Storeinfo storeinfo = storeinfoMapper.selectByExample(example).get(0);

            UorderExample example1 = new UorderExample();
            UorderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStoreidEqualTo(storeinfo.getStoreid());

            uorders = uorderMapper.selectByExample(example1);


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return uorders;
    }

    @Override
    public MessageResult updateState(Long orderId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            UorderExample example = new UorderExample();
            UorderExample.Criteria c = example.createCriteria();
            c.andOrderidEqualTo(orderId);
            Uorder uorders = uorderMapper.selectByExample(example).get(0);
            uorders.setState(1);
            int i = uorderMapper.updateByPrimaryKey(uorders);

            if(i>0){
                mr.setSuccess(true);
                mr.setMessage("确认订单成功");
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
