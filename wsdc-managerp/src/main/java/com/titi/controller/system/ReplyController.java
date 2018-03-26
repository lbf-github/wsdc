package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.ReplyFormMap;
import com.titi.entity.ReplyImgFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.ReplyMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

@Controller
@RequestMapping("/eval")
public class ReplyController extends BaseController{

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Inject
	private ReplyMapper mapper;
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
//		model.addAttribute("res", findByRes());
		String id = getPara("id");
		model.addAttribute("id", id);
		return Common.BACKGROUND_PATH + "/system/eval/list";
	}
	
	@RequestMapping("evalImg")
	public String evalImg(Model model) throws Exception {
		String id = getPara("id");
		ReplyFormMap reply = mapper.findbyFrist("id", id, ReplyFormMap.class);
		model.addAttribute("reply", reply);
		String page = getPara("ispass");
		model.addAttribute("id", id);
		if("reply".equals(page)){
			ReplyFormMap map2 = new ReplyFormMap();
			map2.set("id", id);
			ReplyFormMap map = mapper.selectById(map2);
			if(map != null){
				model.addAttribute("map", map);
			}else{
				model.addAttribute("map", null);
			}
			model.addAttribute("item_id", id);
			return Common.BACKGROUND_PATH + "/system/eval/replyEval";
		}else{
			List<ReplyImgFormMap> list = mapper.getEvalPic(id);
			model.addAttribute("list", list);
			return Common.BACKGROUND_PATH + "/system/eval/evalImg";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		String item_id = getPara("item_id");
		ReplyFormMap map = getFormMap(ReplyFormMap.class);
		map.put("rel_id", item_id);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		map.put("orderby", order);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
		List<ReplyFormMap> list = mapper.selectList(map);
        pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 回复商品评价
	 * @return
	 */
	@ResponseBody
	@RequestMapping("replyEval")
	public String reply(){
		ReplyFormMap map = getFormMap(ReplyFormMap.class);
		try {
			if(map.get("id") == null){//新增回复
				map.set("type", 1);
				map.set("user_id", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
				map.set("create_date", new Timestamp(System.currentTimeMillis()));
				mapper.addEntity(map);
			}else{//修改回复
				mapper.editEntity(map);
			}
		} catch (Exception e) {
			logger.error("回复失败", e);
			throw new SystemException("回复失败");
		}
		return "success";
	}
	
	/**
	 * 删除商品评价
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEval")
	public String del(){
		String[] ids = getParaValues("ids");
		List<String> list = new ArrayList<String>();
		try {
			for(String id : ids){
				list.add(id);
			}
			mapper.delEval(list);
		} catch (Exception e) {
			logger.error("删除商品失败", e);
			throw new SystemException("删除商品失败");
			
			
		}
		return "success";
	}
	
	/**
	 * 订单跳转评价页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toEval")
	public String toEval(Model model) throws Exception {
		String user_id = getPara("user_id");
		String item_id = getPara("item_id");
		ReplyFormMap map = getFormMap(ReplyFormMap.class);
		map.set("rel_id", item_id);
		map.set("user_id", user_id);
		ReplyFormMap eval = mapper.getInfoById(map);//评价
		ReplyFormMap replyMap = getFormMap(ReplyFormMap.class);
		if(eval != null){
			replyMap.set("id", eval.get("id"));
			ReplyFormMap reply = mapper.selectById(replyMap);//回复
			model.addAttribute("reply", reply);
			model.addAttribute("id", eval.get("id"));
		}
		model.addAttribute("eval", eval);
		model.addAttribute("item_id", item_id);
		return Common.BACKGROUND_PATH + "/system/eval/evalInfo";
	}
}
