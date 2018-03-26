package com.titi.mapper;

import java.util.List;

import com.titi.entity.RoleFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 用户角色信息 Dao层接口
 * @author 曾雄
 *
 */
public interface RoleMapper extends BaseMapper{
	
	//查询当前用户角色信息
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	//删除角色
	public void deleteById(RoleFormMap map);
}
