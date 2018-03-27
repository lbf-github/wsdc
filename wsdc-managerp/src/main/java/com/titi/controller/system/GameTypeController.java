package com.titi.controller.system;

import javax.inject.Inject;

import com.titi.entity.GameTypeFormMap;
import com.titi.mapper.GamesMapper;
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
import com.titi.entity.AdFormMap;
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;

import java.sql.Timestamp;

/**
 * 游戏类型管理 action
 * @author 陆彬峰
 *
 */
@Controller
@RequestMapping("/gameType/")
public class GameTypeController extends BaseController{
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(GameTypeController.class);
	
	@Inject
	private GamesMapper  gamesMapper;//注入AttachmentMapper
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/gameType/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow, String pageSize,String column,String sort) {
		GameTypeFormMap gameType = getFormMap(GameTypeFormMap.class);
		String order;
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		gameType.put("orderby", order);
		gameType = toFormMap(gameType, pageNow, pageSize,gameType.getStr("orderby"));
		pageView.setRecords(gamesMapper.findGameTypePage(gameType));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/system/gameType/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="游戏类型管理",methods="新增")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			AdFormMap adFormMap = getFormMap(AdFormMap.class);
			GameTypeFormMap attachment = new GameTypeFormMap();
			attachment.set("game_type", adFormMap.get("type"));
			attachment.set("description", adFormMap.get("description"));
			attachment.set("rec_yn", adFormMap.get("hot_yn"));
			attachment.set("sort", adFormMap.get("sort"));
			attachment.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			attachment.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
			attachment.set("update_date", new Timestamp(System.currentTimeMillis()));
			gamesMapper.addEntity(attachment);
		} catch (Exception e) {
			logger.error("新增失败", e);
			throw new SystemException("新增失败");
		} 
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="游戏类型管理",methods="删除")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		if (ids != null) {
			for (String id : ids) {
				try {
					gamesMapper.deleteByAttribute("id", id, GameTypeFormMap.class);
				} catch (Exception e) {
					logger.error("删除失败", e);
					throw new SystemException("删除失败");
				}
			}
		}
		return "success";
	} 
	
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("ad", gamesMapper.findbyFrist("id", id, GameTypeFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/gameType/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)
	@SystemLog(module="游戏类型管理",methods="编辑")
	public String editEntity() {
		AdFormMap adFormMap = getFormMap(AdFormMap.class);
		try {
			GameTypeFormMap attachment = new GameTypeFormMap();
			attachment.set("id", adFormMap.get("id"));
			attachment.set("game_type", adFormMap.get("type"));
			attachment.set("description", adFormMap.get("description"));
			attachment.set("rec_yn", adFormMap.get("hot_yn"));
			attachment.set("sort", adFormMap.get("sort"));
			attachment.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			attachment.set("update_date", new Timestamp(System.currentTimeMillis()));
			gamesMapper.editEntity(attachment);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new SystemException("修改失败");
		}
		return "success";
	}
	
}
