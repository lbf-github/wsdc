package com.titi.mapper;

import com.titi.entity.ProtalUserFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 前台用户登陆信息 Dao层接口
 * @author 陆彬峰
 */
public interface ProtalUserMapper extends BaseMapper {

    //分页查询前台用户信息
    public List<ProtalUserFormMap> findProtalUserPage(ProtalUserFormMap protalUserFormMap);

}
