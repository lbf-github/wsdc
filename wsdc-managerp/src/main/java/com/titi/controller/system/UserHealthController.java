package com.titi.controller.system;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.UserHealthMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.UserHealthFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 用户健康信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/userHealth/")
public class UserHealthController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(UserHealthController.class);
		
	@Inject
	private UserHealthMapper userHealthMapper;//注入UserHealthMapper

	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/userHealth/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		UserHealthFormMap userHealthFormMap = getFormMap(UserHealthFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		userHealthFormMap.put("orderby", order);
		userHealthFormMap = toFormMap(userHealthFormMap, pageNow, pageSize,userHealthFormMap.getStr("orderby"));
        pageView.setRecords(userHealthMapper.findUserHealthPage(userHealthFormMap));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康管理",methods="用户健康管理-删除数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				userHealthMapper.deleteByAttribute("id", id, UserHealthFormMap.class);
			} catch (Exception e) {
				logger.error("删除健康数据失败", e);
				throw new SystemException("删除健康数据失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("userHealth", userHealthMapper.findbyFrist("id", id, UserHealthFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/userHealth/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="健康管理",methods="用户健康管理-修改数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		UserHealthFormMap userHealthFormMap = getFormMap(UserHealthFormMap.class);
		try {
			userHealthFormMap.set("status", "2");
			userHealthMapper.editEntity(userHealthFormMap);
		} catch (Exception e) {
			logger.error("修改健康数据失败", e);
			throw new SystemException("修改健康数据失败");
		}
		return "success";
	}
	
}