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

import com.titi.mapper.HeadlineMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.HeadlineFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 首页头条信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/headline/")
public class HeadlineController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(HeadlineController.class);
		
	@Inject
	private HeadlineMapper headlineMapper;//注入HeadlineMapper
	
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/headline/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		HeadlineFormMap headlineFormMap = getFormMap(HeadlineFormMap.class);
		headlineFormMap = toFormMap(headlineFormMap, pageNow, pageSize, "");
        pageView.setRecords(headlineMapper.findHeadLinePage(headlineFormMap));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/headline/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="首页头条管理",methods="首页头条管理-新增头条")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			HeadlineFormMap headlineFormMap = getFormMap(HeadlineFormMap.class);
			//处理UEditor图片
			headlineFormMap.set("detail", filterAttach((String) headlineFormMap.get("detail")));
			
			//设置保存活动信息
			headlineFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			headlineFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			headlineFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			headlineFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			headlineMapper.addEntity(headlineFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增头条失败", e);
			throw new SystemException("新增头条失败");
		} 
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="首页头条管理",methods="首页头条管理-删除头条")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				headlineMapper.deleteByAttribute("id", id, HeadlineFormMap.class);
			} catch (Exception e) {
				logger.error("删除头条失败", e);
				throw new SystemException("删除头条失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			HeadlineFormMap headlineFormMap = headlineMapper.findbyFrist("id", id, HeadlineFormMap.class);
			//处理UEditor图片
			headlineFormMap.set("detail", getUESource((String) headlineFormMap.get("detail")));
			model.addAttribute("headline", headlineFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/headline/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="首页头条管理",methods="首页头条管理-修改头条")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		HeadlineFormMap headlineFormMap = getFormMap(HeadlineFormMap.class);
		try {
			//处理UEditor图片
			headlineFormMap.set("detail", Common.replaceCharacter(filterAttach((String)headlineFormMap.get("detail"))));
			
			headlineFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			headlineFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			headlineMapper.editEntity(headlineFormMap);
		} catch (Exception e) {
			logger.error("修改头条失败", e);
			throw new SystemException("修改头条失败");
		}
		return "success";
	}
}