package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.common.dto.MessageResult;
import com.lbf.wsdc.common.jedis.JedisClient;
import com.lbf.wsdc.common.util.JsonUtils;
import com.lbf.wsdc.dao.StoreinfoMapper;
import com.lbf.wsdc.dao.StoreinfoTypeMapper;
import com.lbf.wsdc.dao.UserMapper;
import com.lbf.wsdc.pojo.po.*;
import com.lbf.wsdc.service.StoreInfoService;
import com.lbf.wsdc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 20:51
 * Version:V1.0
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreinfoMapper storeinfoMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StoreinfoTypeMapper storeinfoTypeMapper;

    @Override
    public List<Storeinfo> getStoreInfo() {
        return storeinfoMapper.getOpenStoreInfo();
    }

    @Override
    public List<Storeinfo> getStoreInfoById(Long id) {

        StoreinfoExample example = new StoreinfoExample();
        StoreinfoExample.Criteria criteria = example.createCriteria();
        criteria.andStoreidEqualTo(id);

        List<Storeinfo> list = storeinfoMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<Storeinfo> getStoreInfoByTypeId(Integer stypeid) {

        return storeinfoMapper.getOpenStoreInfoByTypeId(stypeid);
    }

    @Override
    public MessageResult insetStoreInfo(Storeinfo po,String tokenId) {
        MessageResult mr = null;
        try {

            mr = new MessageResult();

            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            po.setUaccount(user.getAccount());

            int i = storeinfoMapper.insertSelective(po);
            if(i>0){
                user.setStatus(2);
                userService.updateUser(user,user.getUserid());

                String token = UUID.randomUUID().toString();
                //把登录成功的信息写入到redis中
                //将用户信息中密码置空
                user.setPassword(null);
                //用户信息存入缓存，并且设置过期时间，1800s(半小时)
                jedisClient.set("LOGIN_TOKEN:" + token, JsonUtils.objectToJson(user));
                jedisClient.expire("LOGIN_TOKEN:" + token, 1800);


                mr.setSuccess(true);
                mr.setMessage("申请成功，请等待管理员审核");
                mr.setData(token);
            }else{
                mr.setSuccess(false);
                mr.setMessage("申请失败");
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public MessageResult updateStoreInfo(Storeinfo po, String tokenId,Integer typeId) {
        MessageResult mr = null;
        try {

            mr = new MessageResult();

            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);

            po.setUaccount(user.getAccount());
            int i = storeinfoMapper.updateStoreInfo(po);
            //类型表关联
            StoreinfoType type = new StoreinfoType();

            StoreinfoExample example = new StoreinfoExample();
            StoreinfoExample.Criteria criteria = example.createCriteria();
            criteria.andUaccountEqualTo(user.getAccount());

            List<Storeinfo> storeinfos = storeinfoMapper.selectByExample(example);
            if(storeinfos != null){
                type.setStoreid(storeinfos.get(0).getStoreid());
            }
            type.setStypeid(typeId);
            int insert = storeinfoTypeMapper.insert(type);



            if(i>0 && insert>0){
                mr.setSuccess(true);
                mr.setMessage("修改成功");
            }else{
                mr.setSuccess(false);
                mr.setMessage("修改失败");
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }

    @Override
    public Storeinfo getByTokenId(String tokenId) {


        Storeinfo po =null;

        String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

        User user = JsonUtils.jsonToPojo(userJson, User.class);

        StoreinfoExample example = new StoreinfoExample();
        StoreinfoExample.Criteria c=example.createCriteria();
        c.andUaccountEqualTo(user.getAccount());

        List<Storeinfo> storeinfos = storeinfoMapper.selectByExample(example);
        if(storeinfos!=null){
            po = storeinfos.get(0);
        }

        return po;
    }

    @Override
    public MessageResult openOrCloseStore(int flag, String tokenId) {
        MessageResult mr = null;
        try {
            mr = new MessageResult();
            String userJson = jedisClient.get("LOGIN_TOKEN:" + tokenId);

            User user = JsonUtils.jsonToPojo(userJson, User.class);
            UorderExample example = new UorderExample();
            UorderExample.Criteria criteria = example.createCriteria();
            if(flag == 1){
                user.setStatus(0);
                int i = userMapper.updateStatus(user);
                if(i>0){
                    mr.setSuccess(true);
                    mr.setMessage("开始营业");
                }else{
                    mr.setSuccess(false);
                    mr.setMessage("设置失败");
                }
            }else if(flag == 0){
                user.setStatus(4);
                int i = userMapper.updateStatus(user);
                if(i>0){
                    mr.setSuccess(true);
                    mr.setMessage("停止营业");
                }else{
                    mr.setSuccess(false);
                    mr.setMessage("设置失败");
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
