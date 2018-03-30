package com.titi.mapper;

import com.titi.entity.ProtalStoreFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 前台用户登陆信息 Dao层接口
 * @author 陆彬峰
 */
public interface ProtalStoreMapper extends BaseMapper {

    //分页查询前台用户信息
    public List<ProtalStoreFormMap> findProtalStorePage(ProtalStoreFormMap protalStoreFormMap);
    //查询账户
    public String findById(ProtalStoreFormMap protalStoreFormMap);

}
