package com.titi.controller.system;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.AdFormMap;
import com.titi.entity.GamesFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.GamesMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.sql.Timestamp;

/**
 * 游戏类型管理 action
 * @author 刘放
 *
 */
@Controller
@RequestMapping("/game/")
public class GamesController extends BaseController{
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(GamesController.class);
	
	@Inject
	private GamesMapper  gamesMapper;//注入AttachmentMapper
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/games/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		GamesFormMap gameType = getFormMap(GamesFormMap.class);
		String order;
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		gameType.put("orderby", order);
		gameType = toFormMap(gameType, pageNow, pageSize,gameType.getStr("orderby"));
		pageView.setRecords(gamesMapper.findGamePage(gameType));
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/system/games/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="游戏类型管理",methods="新增")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			AdFormMap adFormMap=getFormMap(AdFormMap.class);
			GamesFormMap attachment=new GamesFormMap();
			attachment.set("game_img",adFormMap.get("game_img"));
			attachment.set("game_name",adFormMap.get("game_name"));
			attachment.set("player_num",adFormMap.get("player_num"));
			attachment.set("size",adFormMap.get("size"));
			attachment.set("intro",adFormMap.get("intro"));
			attachment.set("game_type",adFormMap.get("game_type"));
			attachment.set("url",adFormMap.get("url"));
			attachment.set("hot",adFormMap.get("hot"));
			attachment.set("newest",adFormMap.get("newest"));
			attachment.set("sort",adFormMap.get("sort"));
			attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
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
					gamesMapper.deleteByAttribute("id", id, GamesFormMap.class);
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
			model.addAttribute("ad", gamesMapper.findbyFrist("id", id, GamesFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/games/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)
	@SystemLog(module="游戏类型管理",methods="编辑")
	public String editEntity() {
		AdFormMap adFormMap=getFormMap(AdFormMap.class);
		try {
			GamesFormMap attachment=new GamesFormMap();
			attachment.set("game_img",adFormMap.get("game_img"));
			attachment.set("game_name",adFormMap.get("game_name"));
			attachment.set("player_num",adFormMap.get("player_num"));
			attachment.set("size",adFormMap.get("size"));
			attachment.set("intro",adFormMap.get("intro"));
			attachment.set("game_type",adFormMap.get("game_type"));
			attachment.set("url",adFormMap.get("url"));
			attachment.set("hot",adFormMap.get("hot"));
			attachment.set("newest",adFormMap.get("newest"));
			attachment.set("sort",adFormMap.get("sort"));
			gamesMapper.addEntity(attachment);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new SystemException("修改失败");
		}
		return "success";
	}
	
}
