package com.titi.mapper;

import java.util.List;

import com.titi.entity.AdFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 广告管理 dao层接口
 * @author 陆彬峰
 *
 */
public interface AdMapper extends BaseMapper{

	/** --查询广告列表 -- **/
	public List<AdFormMap> selectList(AdFormMap adFormMap);
	
	/** --根据id查询 广告信息-- **/
	public AdFormMap selectById(String id);
}
