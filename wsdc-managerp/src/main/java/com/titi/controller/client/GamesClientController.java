package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.GameTypeFormMap;
import com.titi.entity.GamesFormMap;
import com.titi.entity.OutputResult;
import com.titi.mapper.GamesMapper;
import com.titi.util.Common;
import com.titi.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * 游戏展示接口
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class GamesClientController extends BaseController {
	
	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory.getLogger(GamesClientController.class);

	@Inject
	GamesMapper gamesMapper;

	/**
	 * 游戏类型
	 *
	 */
	@RequestMapping(value = "gameTypeList.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "游戏类型展示", methods = "gameTypeList.do")
	public @ResponseBody
	OutputResult selectGameType(String pageNow, String pageSize,String column,String sort) {
		OutputResult output = new OutputResult();
		logger.info("游戏类型展示");
		try {
			GameTypeFormMap gameTypeFormMap = getFormMap(GameTypeFormMap.class);
			String order;
			if(Common.isNotEmpty(column)){
				order = " order by "+column+" "+sort;
			}else{
				order = " order by create_date desc";
			}
			gameTypeFormMap.put("orderby", order);
			gameTypeFormMap = toFormMap(gameTypeFormMap, pageNow, pageSize,gameTypeFormMap.getStr("orderby"));
			List<GameTypeFormMap> typeList = gamesMapper.findGameTypePage(gameTypeFormMap);
			output.setResult(Constants.RESULT_SUCCESS);
			output.setMsg("查询成功");
			output.setResultList(typeList);

		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}

		return output;
	}

	/**
	 * 游戏
	 *
	 */
	@RequestMapping(value = "gameList.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "游戏查询", methods = "gameList.do")
	public @ResponseBody
	OutputResult selectGame(String pageNow, String pageSize,String column,String sort) {
		OutputResult output = new OutputResult();
		logger.info("游戏查询");
		try {
			GamesFormMap gameFormMap = getFormMap(GamesFormMap.class);
			String order;
			if(Common.isNotEmpty(column)){
				order = " order by "+column+" "+sort;
			}else{
				order = " order by create_date desc";
			}
			gameFormMap.put("orderby", order);
			gameFormMap = toFormMap(gameFormMap, pageNow, pageSize, gameFormMap.getStr("orderby"));
			List<GamesFormMap> gameList = gamesMapper.findByPage(gameFormMap);
			output.setResult(Constants.RESULT_SUCCESS);
			output.setMsg("查询成功");
			output.setResultList(gameList);
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}

		return output;
	}

	/**
	 * 游戏
	 *根据类型查询
	 */
	@RequestMapping(value = "gameByType.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "游戏查询", methods = "gameByType.do")
	public @ResponseBody
	OutputResult selectGameByType(String typeId) {
		OutputResult output = new OutputResult();
		logger.info("游戏查询,by typeId = " + typeId);
		try {
			if (Common.isEmpty(typeId)){
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
				return output;
			}
			GamesFormMap gameFormMap = getFormMap(GamesFormMap.class);
			gameFormMap.put("where", " where game_type = " + typeId
					+ " order by create_date desc");
			List<GamesFormMap> gameList = gamesMapper.findByWhere(gameFormMap);
			output.setResult(Constants.RESULT_SUCCESS);
			output.setMsg("查询成功");
			output.setResultList(gameList);
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}
		return output;
	}

	/**
	 * 游戏
	 *根据id查询
	 */
	@RequestMapping(value = "gameById.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "游戏查询", methods = "gameById.do")
	public @ResponseBody
	OutputResult selectGameById(String id) {
		OutputResult output = new OutputResult();
		logger.info("游戏查询,by id = " + id);
		try {
			if (Common.isEmpty(id)){
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
				return output;
			}
			GamesFormMap games = gamesMapper.findbyFrist("id", id, GamesFormMap.class);
			if (games != null) {
				output.setResult(Constants.RESULT_SUCCESS);
				output.setMsg("查询成功");
				output.setResultDao(games);
			}else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("游戏不存在");
			}
		}catch (Exception e) {
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("状态异常");
		}
		return output;
	}




}