package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

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
import com.titi.entity.ItemFormMap;
import com.titi.entity.PackageFormMap;
import com.titi.entity.WrapFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.PackageMapper;
import com.titi.mapper.WrapMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 
 * @author alice
 * 
 */
@Controller
@RequestMapping("/package/")
public class PackageController extends BaseController{

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ClassifyController.class);
	
	@Inject
	private PackageMapper mapper;
	
	@Inject
	private WrapMapper wrapMapper;
	
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/package/list";
	}
	
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,String pageSize,String column,String sort) {
		PackageFormMap pack = getFormMap(PackageFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		pack.put("orderby", order);
		pack = toFormMap(pack, pageNow, pageSize,pack.getStr("orderby"));
		List<PackageFormMap> list = mapper.selectList(pack);
        pageView.setRecords(list);
		return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/package/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="商品包管理",methods="商品包管理-新增商品包")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			PackageFormMap packageFormMap = getFormMap(PackageFormMap.class);
			packageFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			packageFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			packageFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			packageFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			mapper.addEntity(packageFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增商品包失败", e);
			throw new SystemException("新增商品包失败");
		} 
		return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("pack", mapper.findbyFrist("id", id, PackageFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/package/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品包管理",methods="商品包管理-修改分类")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		PackageFormMap pack = getFormMap(PackageFormMap.class);
		try {
			pack.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			pack.set("update_date", new Timestamp(System.currentTimeMillis()));
			mapper.editEntity(pack);
		} catch (Exception e) {
			logger.error("修改商品包失败", e);
			throw new SystemException("修改商品包失败");
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品包管理",methods="商品包管理-删除商品包")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				//删除包
				mapper.deleteByAttribute("id", id, PackageFormMap.class);
				//将包中商品清除
				mapper.delItem(id);
			} catch (Exception e) {
				logger.error("删除商品包失败", e);
				throw new SystemException("删除商品包失败");
			}
		}
		return "success";
	}
	
	@RequestMapping("viewItem")
	public String viewItem(Model model) {
		String id = getPara("id");
		model.addAttribute("id",id);
		return Common.BACKGROUND_PATH + "/system/package/item";
	}
	
	/***
	 * 包中商品列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectItem")
	public PageView selectItem(String pageNow,String pageSize,String column,String sort){
		String id = getPara("id");
		WrapFormMap wrap = getFormMap(WrapFormMap.class);
		wrap.set("package_id", id);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}
		wrap.put("orderby", order);
		wrap = toFormMap(wrap, pageNow, pageSize,wrap.getStr("orderby"));
		List<WrapFormMap> list = mapper.selectItem(wrap);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 删除包中商品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteItem")
	public String deleteItem() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				wrapMapper.deleteByAttribute("id", id, WrapFormMap.class);
			} catch (Exception e) {
				logger.error("删除商品包失败", e);
				throw new SystemException("删除商品包失败");
			}
		}
		return "success";
	}
	
	
	@RequestMapping("addItem")
	public String addItem(Model model) {
		String id = getPara("id");
		model.addAttribute("id",id);
		return Common.BACKGROUND_PATH + "/system/package/addItem";
	}
	
	/**
	 * 查询未被打包的商品
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectItemList")
	public PageView selectListItem(String pageNow,String pageSize,String column,String sort){
		String id = getPara("id");
		WrapFormMap wrap = getFormMap(WrapFormMap.class);
		wrap.set("package_id", id);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}
		wrap.put("orderby", order);
		wrap = toFormMap(wrap, pageNow, pageSize,wrap.getStr("orderby"));
		List<ItemFormMap> list = mapper.selectItemList(wrap);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 向包中添加商品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addList")
	public String insertItem(){
		WrapFormMap wrap = getFormMap(WrapFormMap.class);
		try {
			wrap.set("title", getPara("name"));
			wrap.set("item_id", getParaValues("ids")[0]);
			wrap.set("package_id", getPara("id"));
			mapper.insertItem(wrap);
		} catch (Exception e) {
			logger.error("商品包添加商品失败", e);
			throw new SystemException("商品包添加商品失败");
		}
		return "success";
	}
	
	@RequestMapping("editName")
	public String editName(Model model) {
		String id = getPara("id");
		WrapFormMap wrap = wrapMapper.findbyFrist("id", id, WrapFormMap.class);
		model.addAttribute("wrap",wrap);
		return Common.BACKGROUND_PATH + "/system/package/editName";
	}
	
	/**
	 * 编辑包中商品名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateTitle")
	public String updateTitle() {
		WrapFormMap wrap = getFormMap(WrapFormMap.class);
		try {
			wrapMapper.editEntity(wrap);
		} catch (Exception e) {
			logger.error("修改商品包失败", e);
			throw new SystemException("修改商品包失败");
		}
		return "success";
	}
}
