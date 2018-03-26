package com.titi.mapper;

import java.util.List;

import com.titi.entity.DataDicFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 数据字典 Dao层接口
 * @author 曾雄
 *
 */

public interface DataDicMapper extends BaseMapper {
	
	//查找子级资源
	public List<DataDicFormMap> findChildlists(DataDicFormMap map);

	//显示列表
	public List<DataDicFormMap> findDic(DataDicFormMap map);
	
}
