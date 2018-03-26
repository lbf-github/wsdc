package com.titi.mapper;

import java.util.List;

import com.titi.entity.ActivityFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 商品活动信息 Dao层接口
 * @author 曾雄
 *
 */
public interface ActivityMapper extends BaseMapper{

	//分页查询活动信息
	public List<ActivityFormMap> findActivityPage(ActivityFormMap activityFormMap);
	
	//根据id查询活动信息
	public ActivityFormMap findActivityById(String id);
	
}
