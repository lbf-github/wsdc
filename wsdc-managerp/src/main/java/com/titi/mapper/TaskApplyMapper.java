package com.titi.mapper;

import com.titi.entity.TaskApplyFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;

/**
 * 任务申请记录信息接口
 * @author 曾雄
 *
 */
public interface TaskApplyMapper extends BaseMapper{

	/** -- 任务申请记录列表信息 -- **/
	public List<TaskApplyFormMap> findTaskApplyPage(TaskApplyFormMap taskApplyFormMap);
	
	/** -- 设置任务申请状态为执行中 (对应数值2)-- **/
	public void editApplyStatusTo2();
	
	/** -- 设置任务申请状态为已完成(对应数值3) -- **/
	public void editApplyStatusTo3();
	
	/** -- 任务申请记录列表信息 -- **/
	public List<TaskApplyFormMap> findMyTaskApplyPage(TaskApplyFormMap taskApplyFormMap);
}
