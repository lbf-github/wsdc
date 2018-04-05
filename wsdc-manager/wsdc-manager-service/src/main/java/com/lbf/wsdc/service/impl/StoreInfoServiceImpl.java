package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.dao.StoreinfoMapper;
import com.lbf.wsdc.pojo.po.Storeinfo;
import com.lbf.wsdc.pojo.po.StoreinfoExample;
import com.lbf.wsdc.service.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 20:51
 * Version:V1.0
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService{

    @Autowired
    public StoreinfoMapper storeinfoMapper;

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
}
