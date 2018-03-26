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

import com.titi.mapper.SeoCasesMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.SeoCasesFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 经典案例信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/seoCases/")
public class SeoCasesController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SeoCasesController.class);
		
	@Inject
	private SeoCasesMapper seoCasesMapper;//注入SeoCasesMapper
	
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/seoCases/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		SeoCasesFormMap seoCasesFormMap = getFormMap(SeoCasesFormMap.class);
		seoCasesFormMap = toFormMap(seoCasesFormMap, pageNow, pageSize, "");
        pageView.setRecords(seoCasesMapper.findSeoCasesPage(seoCasesFormMap));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/seoCases/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="经典案例管理",methods="经典案例管理-新增案例")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			SeoCasesFormMap seoCasesFormMap = getFormMap(SeoCasesFormMap.class);
			//处理UEditor图片
			seoCasesFormMap.set("content", filterAttach((String) seoCasesFormMap.get("content")));
			
			//设置保存经典案例信息
			seoCasesFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			seoCasesFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			seoCasesFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			seoCasesFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			seoCasesMapper.addEntity(seoCasesFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增案例失败", e);
			throw new SystemException("新增案例失败");
		} 
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="经典案例管理",methods="经典案例管理-删除案例")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				seoCasesMapper.deleteByAttribute("id", id, SeoCasesFormMap.class);
			} catch (Exception e) {
				logger.error("删除案例失败", e);
				throw new SystemException("删除案例失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			SeoCasesFormMap seoCasesFormMap = seoCasesMapper.findbyFrist("id", id, SeoCasesFormMap.class);
			//处理UEditor图片
			seoCasesFormMap.set("content", getUESource((String) seoCasesFormMap.get("content")));
			model.addAttribute("seoCases", seoCasesFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/seoCases/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="经典案例管理",methods="经典案例管理-修改案例")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		SeoCasesFormMap seoCasesFormMap = getFormMap(SeoCasesFormMap.class);
		try {
			//处理UEditor图片
			seoCasesFormMap.set("content", Common.replaceCharacter(filterAttach((String)seoCasesFormMap.get("content"))));
			
			seoCasesFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			seoCasesFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			seoCasesMapper.editEntity(seoCasesFormMap);
		} catch (Exception e) {
			logger.error("修改案例失败", e);
			throw new SystemException("修改案例失败");
		}
		return "success";
	}
}