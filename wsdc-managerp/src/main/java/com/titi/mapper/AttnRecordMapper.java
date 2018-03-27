package com.titi.mapper;

import java.util.List;

import com.titi.entity.AttnRecordFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 关注骑手记录信息 管理 dao层接口
 * @author 陆彬峰
 *
 */
public interface AttnRecordMapper extends BaseMapper{

	/** -- 分页查询关注骑手的记录信息 -- **/
	public List<AttnRecordFormMap> findAttnRecordPage(AttnRecordFormMap attnRecordFormMap);
	
}
