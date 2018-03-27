package com.titi.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.mapper.AttnRecordMapper;
import com.titi.controller.index.BaseController;
import com.titi.entity.AttnRecordFormMap;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 关注骑手记录信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/attnRecord/")
public class AttnRecordController extends BaseController {
	
	@Inject
	private AttnRecordMapper attnRecordMapper;//注入AttnRecordMapper

	/** --跳转到关注详情页面 -- **/
	@RequestMapping("viewDataUI")
	public String viewDataUI(Model model) {
		String rider_id = getPara("rider_id");
		if(Common.isNotEmpty(rider_id)){
			model.addAttribute("rider_id", rider_id);
		}
		return Common.BACKGROUND_PATH + "/system/attnRecord/detailList";
	}
	
	/** --根据骑手id查询关注详情 -- **/
	@ResponseBody
	@RequestMapping("findDetailByPage")
	public PageView findDetailByPage( String pageNow,
			String pageSize,String column,String sort) {
		AttnRecordFormMap attnRecordFormMap = getFormMap(AttnRecordFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		attnRecordFormMap.put("orderby", order);
		attnRecordFormMap = toFormMap(attnRecordFormMap, pageNow, pageSize,attnRecordFormMap.getStr("orderby"));
		
		String rider_id = getPara("rider_id");
		attnRecordFormMap.put("rider_id", rider_id);
		pageView.setRecords(attnRecordMapper.findAttnRecordPage(attnRecordFormMap));
		return pageView;
	}
}