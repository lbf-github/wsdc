package com.titi.mapper;

import java.util.List;

import com.titi.entity.ItemFormMap;
import com.titi.entity.ItemPicFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 商品信息 Dao层接口
 * @author 陆彬峰
 *
 */
public interface ItemMapper extends BaseMapper{

	//分页查询商品信息
	public List<ItemFormMap> findItemPage(ItemFormMap itemFormMap);
	
	//查询商品列表
	public List<ItemFormMap> findAllItem(ItemFormMap itemFormMap);
	
	//商品图片
	public List<ItemPicFormMap> getPic(String id, String imgType);
	
	//删除原来图片
	public void deleteImg(String id, String imgType);
	
	//保存图片
	public void saveImg(ItemPicFormMap pic);
	
	//单品列表
	public List<ItemFormMap> singleItem(ItemFormMap itemFormMap);
	
	//新增营养师套餐
	public boolean addHealthItemEntity(ItemFormMap itemFormMap);
	
	//查看套餐中的商品
	public List<ItemFormMap> viewItem(ItemFormMap itemFormMap);
	
	//新增商品信息
	public void addItem(ItemFormMap itemFormMap);
	
}
