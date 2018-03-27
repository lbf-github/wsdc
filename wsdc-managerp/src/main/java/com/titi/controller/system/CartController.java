package com.titi.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.CartMapper;
import com.titi.controller.index.BaseController;
import com.titi.entity.CartFormMap;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 购物车信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/cart/")
public class CartController extends BaseController {
	
	@Inject
	private CartMapper cartMapper;//注入cartMapper

	@RequestMapping("list")
	public String listUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/cart/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		CartFormMap cartFormMap = getFormMap(CartFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		cartFormMap.put("orderby", order);
		cartFormMap = toFormMap(cartFormMap, pageNow, pageSize,cartFormMap.getStr("orderby"));
        pageView.setRecords(cartMapper.findCartPage(cartFormMap));
		return pageView;
	}
}