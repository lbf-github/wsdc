package com.titi.mapper;

import java.util.List;

import com.titi.entity.MsgFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 系统消息 dao层接口
 * @author 曾雄
 *
 */
public interface MsgMapper extends BaseMapper{

	/** --查询消息分页列表 -- **/
	public List<MsgFormMap> findMsgPage(MsgFormMap msgFormMap);
	
}
