package com.titi.mapper;

import java.util.List;

import com.titi.entity.GroupBuyFormMap;
import com.titi.mapper.base.BaseMapper;

public interface GroupBuyMapper extends BaseMapper{
	
	//查询拼团列表
	public List<GroupBuyFormMap> selectList(GroupBuyFormMap map);
	
	public GroupBuyFormMap getdetail(String id);
	
	public List<GroupBuyFormMap> getGroupList(GroupBuyFormMap map);
	
	public List<GroupBuyFormMap> getGroupNum(String createUid);
	
	public GroupBuyFormMap getTime(GroupBuyFormMap map);

}
