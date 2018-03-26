package com.titi.mapper;

import com.titi.entity.ClassifyItemFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 商品分类关联 Dao层接口
 * @author 曾雄
 *
 */
public interface ClassifyItemMapper extends BaseMapper{
	
	//根据分类id和商品id检查是否重复
	public int findIsExist(ClassifyItemFormMap classifyItemFormMap);

	String findClassIdByItmId(String item_id);

	void editClassId(ClassifyItemFormMap classifyItemFormMap);
}
