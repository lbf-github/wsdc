package com.titi.mapper;

import java.util.List;

import com.titi.entity.FavoriteFormMap;
import com.titi.mapper.base.BaseMapper;

public interface FavoriteMapper extends BaseMapper{

	//商品列表关注集合
	public List<FavoriteFormMap> selectList(FavoriteFormMap map);
}
