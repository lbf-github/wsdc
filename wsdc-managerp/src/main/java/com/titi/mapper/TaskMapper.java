package com.titi.mapper;

import com.titi.entity.TaskFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 任务 接口
 * @author 曾雄
 *
 */
public interface TaskMapper extends BaseMapper{
	
	/** --查询任务列表信息 -- **/
	public List<TaskFormMap> findTaskPage(TaskFormMap taskFormMap);
	
	/** -- 设置任务状态为已过期(对应数值:2) -- **/
	public void editTaskStatusTo2();
	
	public List<TaskFormMap> findMyTaskPage(TaskFormMap taskFormMap);
	
	public List<TaskFormMap> findMyQueryTaskPage(TaskFormMap taskFormMap);
}
