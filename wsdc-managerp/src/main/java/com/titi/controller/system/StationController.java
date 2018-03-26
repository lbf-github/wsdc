package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.StationFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.StationMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.JsonUtils;
import com.titi.util.POIUtils;

/**
 * 送货站点信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/station/")
public class StationController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(StationController.class);
		
	@Inject
	private StationMapper stationMapper;//注入StationMapper

	/**
	 * 跳转到送货站点列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/station/list";
	}

	/**
	 * 分页查询送货站点信息
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
		StationFormMap stationFormMap = getFormMap(StationFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		stationFormMap.put("orderby", order);
		stationFormMap = toFormMap(stationFormMap, pageNow, pageSize,stationFormMap.getStr("orderby"));
		List<StationFormMap> list = stationMapper.findStationPage(stationFormMap);
        pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 跳转到 送货站点新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/station/add";
	}
	
	/**
	 * 新增送货站点信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="骑手站点管理",methods="骑手站点管理-新增站点")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			StationFormMap stationFormMap = getFormMap(StationFormMap.class);
			stationFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			stationMapper.addEntity(stationFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增骑手站点失败", e);
			throw new SystemException("新增骑手站点失败");
		} 
		return "success";
	}

	/**
	 * 删除送货站点信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="骑手站点管理",methods="骑手站点管理-删除站点")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				stationMapper.deleteByAttribute("id", id, StationFormMap.class);
			} catch (Exception e) {
				logger.error("删除骑手站点失败", e);
				throw new SystemException("删除骑手站点失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("station", stationMapper.findbyFrist("id", id, StationFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/station/edit";
	}

	/**
	 * 编辑送货站点信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="骑手站点管理",methods="骑手站点管理-修改站点")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		StationFormMap stationFormMap = getFormMap(StationFormMap.class);
		try {
			stationMapper.editEntity(stationFormMap);
		} catch (Exception e) {
			logger.error("修改骑手站点失败", e);
			throw new SystemException("修改骑手站点失败");
		}
		return "success";
	}
	
	/**
	 * 送货站点列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stationList")
	public List<StationFormMap> stationList() {
		StationFormMap stationFormMap = getFormMap(StationFormMap.class);
		List<StationFormMap> stationList = stationMapper.findStationList(stationFormMap);
		return stationList;
	}
	
	/**
	 * 导出站点列表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "站点列表";
		StationFormMap stationFormMap = findHasHMap(StationFormMap.class);
		String exportData = stationFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<StationFormMap> list = stationMapper.findStationPage(stationFormMap);
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
	
}