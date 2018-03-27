package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.StationClassifyFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.StationClassifyMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 站点分类信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/stationClassify/")
public class StationClassifyController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(StationClassifyController.class);
		
	@Inject
	private StationClassifyMapper stationClassifyMapper;//注入StationClassifyMapper
	
	/**
	 * 分类列表页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/stationClassify/list";
	}

	/**
	 * 分页查询分类信息
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
		StationClassifyFormMap stationClassifyFormMap = getFormMap(StationClassifyFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		stationClassifyFormMap.put("orderby", order);
		stationClassifyFormMap = toFormMap(stationClassifyFormMap, pageNow, pageSize,stationClassifyFormMap.getStr("orderby"));
		List<StationClassifyFormMap> list = stationClassifyMapper.findStationClassifyPage(stationClassifyFormMap);
        pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 新增页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/stationClassify/add";
	}
	
	/**
	 * 新增分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="站点分类管理",methods="站点分类管理-新增分类")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			StationClassifyFormMap stationClassifyFormMap = getFormMap(StationClassifyFormMap.class);
			stationClassifyFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			stationClassifyFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			stationClassifyFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			stationClassifyFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			stationClassifyMapper.addEntity(stationClassifyFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增站点分类失败", e);
			throw new SystemException("新增站点分类失败");
		} 
		return "success";
	}

	/**
	 * 删除分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="站点分类管理",methods="站点分类管理-删除分类")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				stationClassifyMapper.deleteByAttribute("id", id, StationClassifyFormMap.class);
			} catch (Exception e) {
				logger.error("删除站点分类失败", e);
				throw new SystemException("删除站点分类失败");
			}
		}
		return "success";
	}

	/**
	 * 编辑页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if (Common.isNotEmpty(id)) {
			model.addAttribute("stationClassify", stationClassifyMapper.findbyFrist(
					"id", id, StationClassifyFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/stationClassify/edit";
	}

	/**
	 * 编辑分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="站点分类管理",methods="站点分类管理-修改分类")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		StationClassifyFormMap stationClassifyFormMap = getFormMap(StationClassifyFormMap.class);
		try {
			stationClassifyFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			stationClassifyFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			stationClassifyMapper.editEntity(stationClassifyFormMap);
		} catch (Exception e) {
			logger.error("修改站点分类失败", e);
			throw new SystemException("修改站点分类失败");
		}
		return "success";
	}
	
	/**
	 * 提供分类信息查询接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("classifyList")
	public List<StationClassifyFormMap> classifyList() {
		StationClassifyFormMap stationClassifyFormMap = getFormMap(StationClassifyFormMap.class);
		List<StationClassifyFormMap> classifyList = stationClassifyMapper.findStationClassifyList(stationClassifyFormMap);
		return classifyList;
	}
	
}