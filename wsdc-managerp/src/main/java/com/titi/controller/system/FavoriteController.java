package com.titi.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.FavoriteFormMap;
import com.titi.mapper.FavoriteMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 用户收藏
 * @author 陆彬峰
 * 
 */
@Controller
@RequestMapping("/favorite/")
public class FavoriteController extends BaseController {
	
	@Inject
	private FavoriteMapper mapper;//注入DataDicMapper
	
	/**
	 * 存放返回界面的model
	 */
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/favorite/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		FavoriteFormMap map = getFormMap(FavoriteFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}
		map.put("orderby", order);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
        pageView.setRecords(mapper.selectList(map));
		return pageView;
	}
		
}
