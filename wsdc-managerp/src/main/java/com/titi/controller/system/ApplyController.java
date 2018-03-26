package com.titi.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.ApplyFormMap;
import com.titi.mapper.ApplyMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

@Controller
@RequestMapping("/apply/")
public class ApplyController extends BaseController {
	
	@Inject
	private ApplyMapper mapper;// 注入AdMapper
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/apply/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		ApplyFormMap map = getFormMap(ApplyFormMap.class);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
		map.put("column", column);
		map.put("sort", sort);
        pageView.setRecords(mapper.selectList(map));//不调用默认分页,调用自已的mapper中selectList
        return pageView;
	}
}
