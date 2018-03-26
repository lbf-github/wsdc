package com.titi.mapper;

import java.util.List;

import com.titi.entity.ClassifyFormMap;
import com.titi.entity.ItemFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 商品分类信息 Dao层接口
 * @author 曾雄
 *
 */
public interface ClassifyMapper extends BaseMapper{

	//分页查询商品分类信息
	public List<ClassifyFormMap> findClassifyPage(ClassifyFormMap classifyFormMap);
	
	//查询分类信息列表(用于商品管理中显示下拉信息)
	public List<ClassifyFormMap> findClassifyList(ClassifyFormMap classifyFormMap);
	
	//根据id查询分类信息
	public ClassifyFormMap findClassifyById(String id);
	
	//分类中的商品
	public List<ItemFormMap> viewItem(ClassifyFormMap classifyFormMap);
	
}
