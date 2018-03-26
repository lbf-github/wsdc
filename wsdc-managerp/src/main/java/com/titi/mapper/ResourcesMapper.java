package com.titi.mapper;

import java.util.List;

import com.titi.entity.ResFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 菜单资源 Dao层接口
 * @author 曾雄
 *
 */

public interface ResourcesMapper extends BaseMapper {
	
	//查找子级资源
	public List<ResFormMap> findChildlists(ResFormMap map);

	//显示菜单资源列表
	public List<ResFormMap> findRes(ResFormMap map);

	//更改菜单资源排序
	public void updateSortOrder(List<ResFormMap> map);
	
	//查询当前用户所拥有菜单信息
	public List<ResFormMap> findUserResourcess(String userId);
	
}
