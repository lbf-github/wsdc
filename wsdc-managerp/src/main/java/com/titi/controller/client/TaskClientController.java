package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.*;
import com.titi.mapper.*;
import com.titi.util.Common;
import com.titi.util.Constants;
import com.titi.util.RequestUtils;
import com.titi.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务信息接口 action
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class TaskClientController extends BaseController {

	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(TaskClientController.class);

	@Inject
	private TaskMapper taskMapper;

	@Inject
	private UserAccountMapper userAccountMapper;

	@Inject
	private TaskApplyMapper taskApplyMapper;
	
	@Inject
	private AttachmentMapper attachmentMapper;
	
	@Inject
	private PriceUnitTypeMapper priceUnitTypeMapper;

	/**
	 * 查询任务列表信息
	 */
	@RequestMapping(value = "taskQuery.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询任务列表信息", methods = "taskQuery.do")
	public @ResponseBody
	OutputResult taskQuery(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_status"))
					|| Common.isEmpty(request.getParameter("isshow"))
					|| Common.isEmpty(request.getParameter("currentPage"))
					|| Common.isEmpty(request.getParameter("pageSize"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				TaskFormMap taskFormMap = getFormMap(TaskFormMap.class);
				taskFormMap.put("del_flag", "0");
				taskFormMap.put("isshow", "0");
				taskFormMap.put("task_status", request.getParameter("task_status"));
				taskFormMap.put("user_account_id", "0");
				taskFormMap.put("enterprice_id", "0");//
				List<TaskFormMap> tasks = taskMapper.findTaskPage(toFormMap(
						taskFormMap, request.getParameter("currentPage"),
						request.getParameter("pageSize"), ""));
				if (tasks.size() > 0) {
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("查询成功");
					output.setResultList(tasks);
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("要查询的数据为空");
				}
			}
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 查询专属任务列表信息
	 * 专属任务：
	 * 	1 企业专属：user_account_id>0,enterprice_id>0
	 * 	2 商户专属：user_account_id=0,enterprice_id=0
	 */
	@RequestMapping(value = "personalTaskQuery.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询专属任务列表信息", methods = "personalTaskQuery.do")
	public @ResponseBody
	OutputResult personalTaskQuery(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("currentPage"))
					|| Common.isEmpty(request.getParameter("pageSize"))
					|| Common.isEmpty(request.getParameter("user_account_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist("id", request.getParameter("user_account_id"), UserAccountFormMap.class);
					if(userAccount != null){
						TaskFormMap taskFormMap = getFormMap(TaskFormMap.class);
						taskFormMap.put("task_status", 1);
						taskFormMap.put("user_account_id", request.getParameter("user_account_id"));
						taskFormMap.put("del_flag", "0");
						taskFormMap.put("isshow", "0");
						taskFormMap.put("apply_status", "7");
						if(!Common.isEmpty(request.getParameter("enterprice_id"))){
							taskFormMap.put("enterprice_id", request.getParameter("enterprice_id"));
						}
//						List<TaskFormMap> tasks = taskMapper.findTaskPage(toFormMap(taskFormMap, request.getParameter("currentPage"),request.getParameter("pageSize"), ""));
						
						List<TaskFormMap> exTasks = taskMapper.findMyTaskPage(toFormMap(taskFormMap, request.getParameter("currentPage"),request.getParameter("pageSize"), ""));
						if (exTasks.size() > 0) {
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("查询成功");
//							output.setResultList(tasks);
							output.setResultList(exTasks);
						} else {
							output.setResult(Constants.RESULT_ERROR);
							output.setMsg("要查询的数据为空");
						}
					}else{
						output.setResult(Constants.RESULT_ERROR);
						output.setMsg("要查询的数据为空");
					}
			}
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * 查询我的任务
	 */
	@RequestMapping(value = "myTaskQuery.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询我的任务信息", methods = "myTaskQuery.do")
	public @ResponseBody
	OutputResult myTaskQuery(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_apply_status"))
					|| Common.isEmpty(request.getParameter("currentPage"))
					|| Common.isEmpty(request.getParameter("pageSize"))
					|| Common.isEmpty(request.getParameter("user_account_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
						"id", request.getParameter("user_account_id"),
						UserAccountFormMap.class);
				if (userAccount != null) {
					TaskApplyFormMap taskApplyFormMap = getFormMap(TaskApplyFormMap.class);
					taskApplyFormMap.put("apply_status", request.getParameter("task_apply_status"));
					taskApplyFormMap.put("user_account_id",
							request.getParameter("user_account_id"));
					String order = " order by sign_date desc";
					taskApplyFormMap.put("orderby", order);
					List<TaskApplyFormMap> taskApplyList;
					if("2".equals(request.getParameter("task_apply_status"))){
						taskApplyList = taskApplyMapper.findMyTaskApplyPage(toFormMap(taskApplyFormMap,request.getParameter("currentPage"),request.getParameter("pageSize"),taskApplyFormMap.getStr("orderby")));
					}else{
						taskApplyList = taskApplyMapper.findByPage(toFormMap(taskApplyFormMap,request.getParameter("currentPage"),request.getParameter("pageSize"),taskApplyFormMap.getStr("orderby")));
					}
					if(taskApplyList.size() > 0){
						List<TaskFormMap> myTaskList = new ArrayList<TaskFormMap>();
						for(TaskApplyFormMap taskApply : taskApplyList){
							if(taskApply.get("task_id") != null){
								TaskFormMap task = taskMapper.findbyFrist("id", taskApply.get("task_id").toString(), TaskFormMap.class);
								if(task.get("del_flag") != null && "0".equals(task.get("del_flag").toString())  && task.get("price_units") != null){
									task.set("unit_price", priceUnitTypeMapper.findbyFrist("id", task.get("price_units").toString(), PriceUnitTypeFormMap.class).get("unit_price"));
								}
								if(task.get("img_id") != null){
									task.set("img_id", attachmentMapper.selectAttaById(task.get("img_id").toString()).get("url"));
								}
								myTaskList.add(task);
								taskApply.set("taskInfo", task);
							}
						}
						output.setResult(Constants.RESULT_SUCCESS);
						System.out.println("-----------------");
//						System.out.println(taskApplyList);
						output.setMsg("查询成功");
						output.setResultList(myTaskList);
					}else{
						output.setResult(Constants.RESULT_ERROR);
						output.setMsg("要查询的数据为空");
					}
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("该用户不存在");
				}
			}
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 任务报名
	 */
	@RequestMapping(value = "addMyTask.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "任务报名", methods = "addMyTask.do")
	public @ResponseBody
	OutputResult addMyTask(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_id"))
					|| Common.isEmpty(request.getParameter("user_account_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				List<UserAccountFormMap> userAccountList = userAccountMapper.findByAttribute("id",
						request.getParameter("user_account_id"), UserAccountFormMap.class);
				if (userAccountList.size() > 0) {
					TaskFormMap task = taskMapper.findbyFrist("id", request.getParameter("task_id"), TaskFormMap.class);
					//判断任务是否能够报名
					if(task.get("in_person_num") != null && task.get("total_person_num") != null){
						if(Integer.parseInt(task.get("in_person_num").toString()) 
								< Integer.parseInt(task.get("total_person_num").toString())){
							TaskApplyFormMap taskApplyFormMap = getFormMap(TaskApplyFormMap.class);
							taskApplyFormMap.put("where", " where task_id="
									+ request.getParameter("task_id")
									+" and user_account_id="
									+ request.getParameter("user_account_id")
									+" and apply_status = 1 order by sign_date desc");
							List<TaskApplyFormMap> taskApplyList = taskApplyMapper.findByWhere(taskApplyFormMap);
							if(taskApplyList.size() > 0){
								output.setResult(Constants.RESULT_ERROR);
								output.setMsg("已经报名该任务,不能重复报名");
							}else{
								TaskApplyFormMap taskApply = getFormMap(TaskApplyFormMap.class);
								taskApply.set("version", 0);
								taskApply.set("task_id", request.getParameter("task_id"));
						
								taskApply.set("user_account_id", request.getParameter("user_account_id"));
								taskApply.set("sign_date", new Timestamp(System.currentTimeMillis()));
								
								//若是商户外部调用添加订单（MerchantController.addTaskFromMerchant外部调用添加订单）
								//，订单状态变为执行中，否则是报名中
								if(task.get("user_id") !=null && task.get("user_pwd")!=null){
									taskApply.set("apply_status", 2);
								}else{
									taskApply.set("apply_status", 1);
								}
							
								taskApply.set("isshow", 0);
								taskApplyMapper.addEntity(taskApply);
								//将该任务当前参与人数加1
								task.set("in_person_num", Integer.parseInt(task.get("in_person_num").toString())+1);
								taskMapper.editEntity(task);
								output.setResult(Constants.RESULT_SUCCESS);
								output.setMsg("报名成功");
							}
						}else{
							output.setResult(Constants.RESULT_ERROR);
							output.setMsg("报名人数已满,不能报名");
						}
					}else{
						output.setResult(Constants.RESULT_ERROR);
						output.setMsg("任务当前参与人数和总人数不能为空");
					}
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("该用户不存在");
				}
			}
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 查询任务详情
	 */
	@RequestMapping(value = "queryTaskDetail.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询任务详情", methods = "queryTaskDetail.do")
	public @ResponseBody
	OutputResult queryTaskDetail(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("任务id不能为空");
			} else {
				List<TaskFormMap> tasks = taskMapper.findByAttribute("id", request.getParameter("task_id"), TaskFormMap.class);
				if(tasks.size() > 0){
					TaskFormMap task = tasks.get(0);
					if(task.get("del_flag") != null 
							&& "0".equals(task.get("del_flag").toString()) 
							&& task.get("img_id") != null
							&& task.get("price_units") != null){
						task.set("img_id", attachmentMapper.selectAttaById(task.get("img_id").toString()).get("url"));
						task.set("unit_price", priceUnitTypeMapper.findbyFrist("id", task.get("price_units").toString(), 
								PriceUnitTypeFormMap.class).get("unit_price"));
						task.set("task_detail",Util.Html2Text(task.get("task_detail")+""));
						task.set("basic_requirement",Util.Html2Text(task.get("basic_requirement")+""));
					}
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("查询成功");
					output.setResultDao(task);
				}else{
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("要查询的数据为空");
				}
			}			
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 取消任务
	 */
	@RequestMapping(value = "cancelTask.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "取消任务", methods = "cancelTask.do")
	public @ResponseBody
	OutputResult cancelTask(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_id")) 
					|| Common.isEmpty(request.getParameter("user_account_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
						"id", request.getParameter("user_account_id"),
						UserAccountFormMap.class);
				if (userAccount != null) {
					//查询该用户已报名的所有任务申请记录
					TaskApplyFormMap taskApplyFormMap = getFormMap(TaskApplyFormMap.class);
					taskApplyFormMap.put("where", " where task_id="
							+ request.getParameter("task_id")
							+" and user_account_id="
							+ request.getParameter("user_account_id")
							+" and apply_status = 1 order by sign_date desc");
					List<TaskApplyFormMap> taskApplyList = taskApplyMapper.findByWhere(taskApplyFormMap);
					//更新任务申请记录为已取消状态(值为6)
					for(TaskApplyFormMap taskApply : taskApplyList){
						taskApply.set("apply_status", 6);
						taskApplyMapper.editEntity(taskApply);
					}
					//将该任务的当前参与人数减1
					TaskFormMap task = taskMapper.findbyFrist("id", request.getParameter("task_id"), TaskFormMap.class);
					if(task != null){
						if(task.get("in_person_num") != null){
							task.set("in_person_num", Integer.parseInt(task.get("in_person_num").toString())-1);
							taskMapper.editEntity(task);
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("任务取消成功");
						}else{
							output.setResult(Constants.RESULT_ERROR);
							output.setMsg("当前任务的参与人数为空");
						}
					}else{
						output.setResult(Constants.RESULT_ERROR);
						output.setMsg("查询不到任务信息");
					}
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("该用户不存在");
				}
			}			
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 骑手确认完成任务
	 */
	@RequestMapping(value = "completeTask.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "确认完成任务", methods = "completeTask.do")
	public @ResponseBody
	OutputResult completeTask(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("task_id")) 
					|| Common.isEmpty(request.getParameter("user_account_id"))
					|| Common.isEmpty(request.getParameter("task_status"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数不能为空");
			} else {
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
						"id", request.getParameter("user_account_id"),
						UserAccountFormMap.class);
				if (userAccount != null) {
					//框架限制(bug) 所有接口 暂时不能多次使用同一个方法 从findByFirst改为findByAttribute 
					TaskApplyFormMap taskApplyFormMap = getFormMap(TaskApplyFormMap.class);
					taskApplyFormMap.put("task_id", request.getParameter("task_id"));
					taskApplyFormMap.put("user_account_id",
							request.getParameter("user_account_id"));
					taskApplyFormMap.put("apply_status",
							"2");
					List<TaskApplyFormMap> taskApplyList = taskApplyMapper.
							findByNames(toFormMap(taskApplyFormMap, "1", "1", ""));
					
					if(taskApplyList.size() > 0){
						//判断不为空，不修正订单状态，走骑手2.0逻辑
						if(Common.isEmpty(request.getParameter("task_status"))){
							taskApplyList.get(0).set("apply_status", "3");
							taskApplyMapper.editEntity(taskApplyList.get(0));
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("确认完成成功");
							//判断为空，修正订单状态，走骑手3.0逻辑
						}else{
							taskApplyList.get(0).set("task_status", request.getParameter("task_status"));
							
							taskApplyMapper.editEntity(taskApplyList.get(0));
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("确认完成成功");
						}
						
					}else{
						output.setResult(Constants.RESULT_ERROR);
						output.setMsg("查询不到该任务申请记录");
					}
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("该用户不存在");
				}
			}			
		} catch (Exception e) {
			logger.error("程序异常,查询失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,查询失败");
			e.printStackTrace();
		}
		return output;
	}

}