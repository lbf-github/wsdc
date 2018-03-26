package com.titi.mapper;

import java.util.List;

import com.titi.entity.HeadlineFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 头条管理 dao层接口
 * @author 
 *
 */
public interface HeadlineMapper extends BaseMapper{

	/** -- 分页查询头条信息 -- **/
	public List<HeadlineFormMap> findHeadLinePage(HeadlineFormMap headlineFormMap);
	
}
