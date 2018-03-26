package com.titi.mapper;

import java.util.List;

import com.titi.entity.StationFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 站点管理 dao层接口
 * @author 曾雄
 *
 */
public interface StationMapper extends BaseMapper{

	/** -- 查询站点列表信息 -- **/
	public List<StationFormMap> findStationPage(StationFormMap stationFormMap);
	
	/** -- 查询站点信息列表(用于骑手信息管理中显示下拉信息) -- **/
	public List<StationFormMap> findStationList(StationFormMap stationFormMap);
	
}
