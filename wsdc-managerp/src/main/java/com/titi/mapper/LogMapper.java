package com.titi.mapper;

import java.util.List;

import com.titi.entity.LogFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 操作日志 Dao层接口
 * @author 陆彬峰
 *
 */
public interface LogMapper extends BaseMapper{

	//分页查询日志信息
	public List<LogFormMap> findLogPage(LogFormMap logFormMap);
}
