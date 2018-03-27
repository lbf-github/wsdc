package com.titi.mapper;

import java.util.List;

import com.titi.entity.UserHealthFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 用户健康管理 Dao层接口
 * @author 陆彬峰
 *
 */
public interface UserHealthMapper extends BaseMapper{

	//分页查询用户健康信息
	public List<UserHealthFormMap> findUserHealthPage(UserHealthFormMap userHealthFormMap);
	
}
