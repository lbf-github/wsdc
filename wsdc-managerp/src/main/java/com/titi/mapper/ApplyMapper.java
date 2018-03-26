package com.titi.mapper;

import java.util.List;

import com.titi.entity.ApplyFormMap;
import com.titi.entity.ApplyPicFormMap;
import com.titi.mapper.base.BaseMapper;

public interface ApplyMapper extends BaseMapper{

	public List<ApplyFormMap> selectList(ApplyFormMap map);
	
	public ApplyFormMap getDetail(String sn2);
	
	public List<ApplyPicFormMap> getPic(String id);
}
