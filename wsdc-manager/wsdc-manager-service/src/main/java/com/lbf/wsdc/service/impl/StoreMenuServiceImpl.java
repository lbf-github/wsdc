package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.dao.StoremenuMapper;
import com.lbf.wsdc.pojo.po.Storemenu;
import com.lbf.wsdc.pojo.po.StoremenuExample;
import com.lbf.wsdc.service.StoreMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 22:30
 * Version:V1.0
 */
@Service
public class StoreMenuServiceImpl implements StoreMenuService {
    @Autowired
    public StoremenuMapper storemenuMapper;
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
}
