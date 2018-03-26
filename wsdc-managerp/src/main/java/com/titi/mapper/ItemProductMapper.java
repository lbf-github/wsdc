package com.titi.mapper;

import java.util.List;

import com.titi.entity.ItemProductFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 产品信息 Dao层接口
 * @author 曾雄
 *
 */
public interface ItemProductMapper extends BaseMapper{

	/** -- 分页查询产品信息 -- **/
	public List<ItemProductFormMap> findItemProductPage(ItemProductFormMap itemProductFormMap);
	
	/** -- 新增产品信息 -- **/
	public void addItemProductEntity(ItemProductFormMap itemProductFormMap);
	
}
