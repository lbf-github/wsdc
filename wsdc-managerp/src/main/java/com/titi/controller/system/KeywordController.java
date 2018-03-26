package com.titi.controller.system;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.KeywordFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.KeywordMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
/**
 * 关键词管理
 * @author 刘放
 *
 */
@Controller
@RequestMapping("/keyword/")
public class KeywordController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(KeywordController.class);
	
	@Inject
	private KeywordMapper mapper;
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/keyword/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		KeywordFormMap keyword = getFormMap(KeywordFormMap.class);
		keyword = toFormMap(keyword, pageNow, pageSize,keyword.getStr("orderby"));
		keyword.put("column", column);
		keyword.put("sort", sort);
        pageView.setRecords(mapper.selectList(keyword));//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/keyword/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="关键词管理",methods="关键词管理-新增关键词")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			KeywordFormMap keyword = getFormMap(KeywordFormMap.class);
			keyword.set("create_date",new Timestamp(System.currentTimeMillis()));
			keyword.set("frequency",0);
			mapper.addEntity(keyword);
		} catch (Exception e) {
			logger.error("新增失败", e);
			throw new SystemException("新增失败");
		} 
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="关键词管理",methods="关键词管理-删除关键词")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				mapper.deleteByAttribute("id", id, KeywordFormMap.class);
			} catch (Exception e) {
				logger.error("删除失败", e);
				throw new SystemException("删除失败");
			}
		}
		return "success";
	} 
	
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("keyword", mapper.findbyFrist("id", id, KeywordFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/keyword/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="关键词管理",methods="关键词管理-编辑关键词")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		KeywordFormMap keyword = getFormMap(KeywordFormMap.class);
		try {
			mapper.editEntity(keyword);
		} catch (Exception e) {
			logger.error("修改用户", e);
			e.printStackTrace();
		}
		return "success";
	}
}
