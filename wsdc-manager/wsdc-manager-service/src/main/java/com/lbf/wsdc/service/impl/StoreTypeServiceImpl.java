package com.lbf.wsdc.service.impl;

import com.lbf.wsdc.dao.StoretypeMapper;
import com.lbf.wsdc.pojo.po.Storetype;
import com.lbf.wsdc.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Administrator
 * Date: 2018/4/5
 * Time: 0:26
 * Version:V1.0
 */
@Service
public class StoreTypeServiceImpl implements StoreTypeService {
    @Autowired
    public StoretypeMapper storetypeMapper;

    @Override
    public List<Storetype> getStoreType() {
        return storetypeMapper.selectTypes();
    }
}
