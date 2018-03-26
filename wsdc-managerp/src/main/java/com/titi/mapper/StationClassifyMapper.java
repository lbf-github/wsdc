package com.titi.mapper;

import java.util.List;

import com.titi.entity.StationClassifyFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 站点分类管理 dao层接口
 * @author 曾雄
 *
 */
public interface StationClassifyMapper extends BaseMapper{

	/** -- 查询站点分类列表信息 -- **/
	public List<StationClassifyFormMap> findStationClassifyPage(StationClassifyFormMap stationClassifyFormMap);
	
	/** -- 查询站点分类信息列表(用于站点信息管理中显示下拉信息) -- **/
	public List<StationClassifyFormMap> findStationClassifyList(StationClassifyFormMap stationClassifyFormMap);
	
}
