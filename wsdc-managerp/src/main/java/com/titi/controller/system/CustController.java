package com.titi.controller.system;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.CustInfoMapper;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.CustInfoFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 前台用户附属信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/cust/")
public class CustController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(CustController.class);
		
	@Inject
	private CustInfoMapper custInfoMapper;

	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/cust/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		CustInfoFormMap custInfoFormMap = getFormMap(CustInfoFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by birth desc";
		}
		custInfoFormMap.put("orderby", order);
		custInfoFormMap = toFormMap(custInfoFormMap, pageNow, pageSize,custInfoFormMap.getStr("orderby"));
        pageView.setRecords(custInfoMapper.findCustInfoPage(custInfoFormMap));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/cust/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="前台用户管理",methods="前台用户管理-新增用户")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			CustInfoFormMap custInfoFormMap = getFormMap(CustInfoFormMap.class);
			custInfoMapper.addEntity(custInfoFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增用户失败", e);
			throw new SystemException("新增用户失败");
		} 
		return "success";
	}

	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="前台用户管理",methods="用户管理-删除用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				custInfoMapper.deleteByAttribute("id", id, CustInfoFormMap.class);
			} catch (Exception e) {
				logger.error("删除用户失败", e);
				throw new SystemException("删除用户失败");
			}
		}
		return "success";
	}

	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("cust", custInfoMapper.findbyFrist("id", id, CustInfoFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/cust/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="前台用户管理",methods="用户管理-修改用户")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		CustInfoFormMap custInfoFormMap = getFormMap(CustInfoFormMap.class);
		try {
			custInfoMapper.editEntity(custInfoFormMap);
		} catch (Exception e) {
			logger.error("修改用户失败", e);
			throw new SystemException("修改用户失败");
		}
		return "success";
	}
}