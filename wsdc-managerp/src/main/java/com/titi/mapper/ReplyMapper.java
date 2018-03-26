package com.titi.mapper;

import java.util.List;

import com.titi.entity.ReplyFormMap;
import com.titi.entity.ReplyImgFormMap;
import com.titi.mapper.base.BaseMapper;

public interface ReplyMapper extends BaseMapper{
	
	public List<ReplyFormMap> selectList(ReplyFormMap map);
	
	public List<ReplyImgFormMap> getEvalPic(String id);
	
	public ReplyFormMap selectById(ReplyFormMap map);
	
	public void delEval(List<String> list);
	
	public ReplyFormMap getInfoById(ReplyFormMap map);

}
