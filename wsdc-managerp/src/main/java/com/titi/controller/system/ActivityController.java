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

import com.titi.mapper.ActivityMapper;
import com.titi.mapper.AttachmentMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.ActivityFormMap;
import com.titi.entity.AttachmentFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 活动信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/activity/")
public class ActivityController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
		
	@Inject
	private ActivityMapper activityMapper;//注入ActivityMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入attachmentMapper

	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/activity/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		ActivityFormMap activityFormMap = getFormMap(ActivityFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by seq";
		}
		activityFormMap.put("orderby", order);
		activityFormMap = toFormMap(activityFormMap, pageNow, pageSize,activityFormMap.getStr("orderby"));
        pageView.setRecords(activityMapper.findActivityPage(activityFormMap));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/activity/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="商品活动管理",methods="商品活动管理-新增活动")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			ActivityFormMap activityFormMap = getFormMap(ActivityFormMap.class);
			
			//图片存入附件表
			AttachmentFormMap attachment = new AttachmentFormMap();
			attachment.set("url", activityFormMap.get("img_id"));
			attachmentMapper.addAttachmentEntity(attachment);
			
			//设置保存活动信息
			activityFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			activityFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			activityFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			activityFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			activityFormMap.set("img_id", attachment.get("id"));
			activityMapper.addEntity(activityFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增商品活动失败", e);
			throw new SystemException("新增商品活动失败");
		} 
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品活动管理",methods="商品活动管理-删除活动")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				activityMapper.deleteByAttribute("id", id, ActivityFormMap.class);
			} catch (Exception e) {
				logger.error("删除商品活动失败", e);
				throw new SystemException("删除商品活动失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("activity", activityMapper.findActivityById(id));
		}
		return Common.BACKGROUND_PATH + "/system/activity/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品活动管理",methods="商品活动管理-修改活动")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		ActivityFormMap activityFormMap = getFormMap(ActivityFormMap.class);
		AttachmentFormMap attachment = new AttachmentFormMap();
		try {
			//图片存入附件表
			attachment.set("url", activityFormMap.get("img_id"));
			attachmentMapper.addAttachmentEntity(attachment);
			
			activityFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			activityFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			activityFormMap.set("img_id", attachment.get("id"));
			activityMapper.editEntity(activityFormMap);
		} catch (Exception e) {
			logger.error("修改商品活动失败", e);
			throw new SystemException("修改商品活动失败");
		}
		return "success";
	}
}