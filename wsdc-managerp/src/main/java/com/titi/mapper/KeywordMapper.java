package com.titi.mapper;

import java.util.List;

import com.titi.entity.KeywordFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 关键词管理 Dao层接口
 * @author 陆彬峰
 *
 */
public interface KeywordMapper extends BaseMapper{
	
	/** --分页查询关键词列表 -- **/
	public List<KeywordFormMap> selectList(KeywordFormMap keyword);

}
