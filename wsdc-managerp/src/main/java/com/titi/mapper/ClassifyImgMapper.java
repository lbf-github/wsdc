package com.titi.mapper;

import java.util.List;

import com.titi.entity.ClassifyImgFormMap;
import com.titi.mapper.base.BaseMapper;

public interface ClassifyImgMapper extends BaseMapper{
	
	//查询商品分类图片
	public List<ClassifyImgFormMap> getById(ClassifyImgFormMap map);
	
	//删除商品分类图片
	public void deleteImg(String id, int type);

}
