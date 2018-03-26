package com.titi.mapper;

import com.titi.entity.MsgRecordFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 消息记录信息 Dao层接口
 * @author 曾雄
 *
 */
public interface MsgRecordMapper extends BaseMapper{
	
	/** -- 新增数据 -- **/
	public boolean addMsgRecordEntity(MsgRecordFormMap msgRecordFormMap);
	
}
