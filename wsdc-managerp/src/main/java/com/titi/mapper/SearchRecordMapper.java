package com.titi.mapper;

import java.util.List;

import com.titi.entity.SearchRecordFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 搜索记录信息 Dao层接口
 * @author 曾雄
 *
 */
public interface SearchRecordMapper extends BaseMapper{

	/** -- 分页查询去重后的搜索词信息 -- **/
	public List<SearchRecordFormMap> findSearchRecordPage(SearchRecordFormMap searchRecordFormMap);
		
	/** -- 分页查询某个关键词的搜索详情 -- **/
	public List<SearchRecordFormMap> findSearchDeatailPage(SearchRecordFormMap searchRecordFormMap);
	
}
