package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.dao.StoreinfoMapper;
import com.lbf.wsdc.dao.StoremenuMapper;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.po.StoremenuExample;
import com.lbf.wsdc.pojo.po.User;
import com.lbf.wsdc.pojo.vo.ShoppingCart;
import com.lbf.wsdc.service.StoreMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 22:30
 * Version:V1.0
 */
@Service
public class StoreMenuServiceImpl implements StoreMenuService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoremenuMapper storemenuMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private StoreinfoMapper storeinfoMapper;

    @Override
    public List<Storemenu> getListById(Long storeid) {

        StoremenuExample example = new StoremenuExample();
        StoremenuExample.Criteria criteria=example.createCriteria();
        criteria.andStoreidEqualTo(storeid);

        List<Storemenu> list = storemenuMapper.selectByExample(example);


        return list;
    }

    @Override
    public Storemenu getStoreMenuById(Long menuid) {
        StoremenuExample example = new StoremenuExample();
        StoremenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuidEqualTo(menuid);
        List<Storemenu> list = storemenuMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public MessageResult deleteInMenuListCart(Long menuid, String cartId) {
        MessageResult mr = new MessageResult();
        try{
            mr.setSuccess(false);
            mr.setMessage("删除失败");
            String cartJson = jedisClient.get("Cart_TOKEN:" + cartId);
            List<ShoppingCart> menuListCart = JsonUtils.jsonToList(cartJson,ShoppingCart.class);

            for(int i=0;i<menuListCart.size();i++){
                if(menuListCart.get(i).getMenuid()==menuid){
                    if(menuListCart.get(i).getNum()>1){
                        menuListCart.get(i).setNum(menuListCart.get(i).getNum()-1);
                    }else{
                        menuListCart.remove(i);
                    }
                    mr.setSuccess(true);
                    mr.setMessage("删除成功");
                }
            }

            String cart_token = UUID.randomUUID().toString();
            jedisClient.set("Cart_TOKEN:" + cart_token, JsonUtils.objectToJson(menuListCart));
            jedisClient.expire("Cart_TOKEN:" + cart_token, 1800);
            mr.setData(cart_token);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public List<Storemenu> getMyStoreMenu(String tokenId) {

        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        User user = JsonUtils.jsonToPojo(userJson, User.class);


        return storemenuMapper.getStoreMenuByUserId(user.getUserid());
    }

    @Override
    public MessageResult addMenu(Storemenu menu,String tokenId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();

            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            Storeinfo storeInfo = storeinfoMapper.getByUserId(user.getUserid());

            menu.setStoreid(storeInfo.getStoreid());

            int i = storemenuMapper.insertSelective(menu);
            if(i>0){
                mr.setSuccess(true);
                mr.setMessage("新增菜品成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("新增菜品失败");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public MessageResult editMenu(Storemenu memu) {
        MessageResult mr = null;
        try {

            mr = new MessageResult();
            int i = storemenuMapper.updateByPrimaryKeySelective(memu);
            if(i>0){
                mr.setSuccess(true);
                mr.setMessage("菜品更新成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("菜品更新失败");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public MessageResult deleteMenu(Long menuId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            int i = storemenuMapper.deleteByPrimaryKey(menuId);
            if(i>0){
                mr.setSuccess(true);
                mr.setMessage("删除成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("删除失败");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
