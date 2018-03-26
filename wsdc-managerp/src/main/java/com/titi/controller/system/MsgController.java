package com.titi.controller.system;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.MsgMapper;
import com.titi.mapper.MsgRecordMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.MsgFormMap;
import com.titi.entity.MsgRecordFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.WebUtil;

/**
 * 系统消息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/msg/")
public class MsgController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(MsgController.class);
		
	@Inject
	private MsgMapper msgMapper;//注入MsgMapper
	
	@Inject
	private MsgRecordMapper msgRecordMapper;//注入MsgRecordMapper

	/**
	 * 跳转到消息列表
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/msg/list";
	}

	/**
	 * 分页查询消息列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		MsgFormMap msgFormMap = getFormMap(MsgFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		msgFormMap.put("orderby", order);
		msgFormMap = toFormMap(msgFormMap, pageNow, pageSize,msgFormMap.getStr("orderby"));
        pageView.setRecords(msgMapper.findMsgPage(msgFormMap));
		return pageView;
	}
	
	/**
	 * 跳转到新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/msg/add";
	}
	
	/**
	 * 发送消息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="系统消息管理",methods="系统消息管理-新增消息")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			String checkAll = getPara("checkAll");
			if(!WebUtil.isEmpty(checkAll)){
				if("1".equals(checkAll)){//选择全部用户
					MsgRecordFormMap msgRecordFormMap = getFormMap(MsgRecordFormMap.class);
					MsgFormMap msg = getFormMap(MsgFormMap.class);
					msgRecordFormMap.set("title", msg.get("title"));
					msgRecordFormMap.set("content", msg.get("content"));
					msgRecordFormMap.set("cust_id", "-1");
					msgRecordFormMap.set("expiry_date", msg.get("expiry_date"));
					msgRecordFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
					msgRecordMapper.addEntity(msgRecordFormMap);
				}else if("2".equals(checkAll)){//不选择全部用户
					MsgFormMap msgFormMap = getFormMap(MsgFormMap.class);
					String to_id = (String) msgFormMap.get("to_id");//收件人to_id
					MsgRecordFormMap msgRecordFormMap = getFormMap(MsgRecordFormMap.class);
					msgRecordFormMap.set("title", msgFormMap.get("title"));
					msgRecordFormMap.set("content", msgFormMap.get("content"));
					msgRecordFormMap.set("cust_id", to_id);
					msgRecordFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
					msgRecordMapper.addEntity(msgRecordFormMap);
					
					if(!WebUtil.isEmpty(to_id)){
						String[] idArr =to_id.split(",");
						for(String id : idArr){
							MsgFormMap msg = getFormMap(MsgFormMap.class);
							msg.set("fr_id", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
							msg.set("create_date", new Timestamp(System.currentTimeMillis()));
							msg.set("to_id", id);
							msg.set("record_id", msgRecordFormMap.get("id"));
							msgMapper.addEntity(msg);//新增后返回新增信息
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("新增消息失败", e);
			throw new SystemException("新增消息失败");
		} 
		return "success";
	}

	/**
	 * 删除消息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统消息管理",methods="系统消息管理-删除消息")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				msgMapper.deleteByAttribute("id", id, MsgFormMap.class);
			} catch (Exception e) {
				logger.error("删除消息失败", e);
				throw new SystemException("删除消息失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到消息编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("msg", msgMapper.findbyFrist("id", id, MsgFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/msg/edit";
	}

	/**
	 * 编辑消息信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统消息管理",methods="系统消息管理-修改消息")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		MsgFormMap msgFormMap = getFormMap(MsgFormMap.class);
		try {
			msgMapper.editEntity(msgFormMap);
		} catch (Exception e) {
			logger.error("修改系统消息失败", e);
			throw new SystemException("修改系统消息失败");
		}
		return "success";
	}
}