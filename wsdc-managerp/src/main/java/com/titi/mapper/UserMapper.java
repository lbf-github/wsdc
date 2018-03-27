package com.titi.mapper;

import java.util.List;

import com.titi.entity.UserFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 后台用户信息 Dao层接口
 * @author 陆彬峰
 *
 */
public interface UserMapper extends BaseMapper{

	//分页查询用户信息
	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
