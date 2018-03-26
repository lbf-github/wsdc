package com.titi.mapper;

import java.util.List;

import com.titi.entity.CartFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 购物车 Dao层接口
 * @author 曾雄
 *
 */
public interface CartMapper extends BaseMapper{

	//分页查询购物车信息
	public List<CartFormMap> findCartPage(CartFormMap cartFormMap);
}
