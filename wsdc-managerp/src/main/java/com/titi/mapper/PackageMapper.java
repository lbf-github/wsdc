package com.titi.mapper;

import java.util.List;

import com.titi.entity.ItemFormMap;
import com.titi.entity.PackageFormMap;
import com.titi.entity.WrapFormMap;
import com.titi.mapper.base.BaseMapper;

public interface PackageMapper extends BaseMapper{
	
	//查询列表(带搜索)
	public List<PackageFormMap> selectList(PackageFormMap pack);
	
	//商品包中的商品
	public List<WrapFormMap> selectItem(WrapFormMap wrap);
	
	public List<ItemFormMap> selectItemList(WrapFormMap wrap);
	
	public void insertItem(WrapFormMap wrap);
	
	public void delItem(String id);
}
