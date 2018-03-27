package com.titi.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.SearchRecordMapper;
import com.titi.controller.index.BaseController;
import com.titi.entity.SearchRecordFormMap;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 搜索记录信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/searchRecord/")
public class SearchRecordController extends BaseController {
		
	@Inject
	private SearchRecordMapper searchRecordMapper;//注入SearchRecordMapper

	/** --跳转到搜索词列表(去重)页面 -- **/
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/searchRecord/list";
	}

	/** --分页查询搜索词(去重)信息 -- **/
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		SearchRecordFormMap searchRecordFormMap = getFormMap(SearchRecordFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by search_date desc";
		}
		searchRecordFormMap.put("orderby", order);
		searchRecordFormMap = toFormMap(searchRecordFormMap, pageNow, pageSize,searchRecordFormMap.getStr("orderby"));
        pageView.setRecords(searchRecordMapper.findSearchRecordPage(searchRecordFormMap));
		return pageView;
	}
	
	/** --跳转到用户搜索详情页面 -- **/
	@RequestMapping("viewDataUI")
	public String viewDataUI(Model model) {
		String key_word = getPara("key_word");
		if(Common.isNotEmpty(key_word)){
			model.addAttribute("key_word", key_word);
		}
		return Common.BACKGROUND_PATH + "/system/searchRecord/detailList";
	}
	
	/** --根据搜索词查询用户搜索详情 -- **/
	@ResponseBody
	@RequestMapping("findDetailByPage")
	public PageView findDetailByPage( String pageNow,
			String pageSize,String column,String sort) {
		SearchRecordFormMap searchRecordFormMap = getFormMap(SearchRecordFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by search_date desc";
		}
		searchRecordFormMap.put("orderby", order);
		searchRecordFormMap = toFormMap(searchRecordFormMap, pageNow, pageSize,searchRecordFormMap.getStr("orderby"));
		
		String key_word = getPara("key_word");
		searchRecordFormMap.put("key_word", key_word);
		pageView.setRecords(searchRecordMapper.findSearchDeatailPage(searchRecordFormMap));
		return pageView;
	}
	
}