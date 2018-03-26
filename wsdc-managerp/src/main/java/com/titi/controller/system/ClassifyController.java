package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.AttachmentFormMap;
import com.titi.entity.ClassifyFormMap;
import com.titi.entity.ClassifyImgFormMap;
import com.titi.entity.ClassifyItemFormMap;
import com.titi.entity.ItemFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.AttachmentMapper;
import com.titi.mapper.ClassifyImgMapper;
import com.titi.mapper.ClassifyItemMapper;
import com.titi.mapper.ClassifyMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 商品分类信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/classify/")
public class ClassifyController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ClassifyController.class);
		
	@Inject
	private ClassifyMapper classifyMapper;//注入ClassifyMapper
	
	@Inject
	private ClassifyItemMapper classifyItemMapper;//注入classifyItemMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入attachmentMapper
	
	@Inject
	private ClassifyImgMapper classifyImgMapper;//注入classifyImgMapper

	/**
	 * 分类列表页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/classify/list";
	}

	/**
	 * 分页查询分类信息
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) {
		ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by create_date desc";
		}
		classifyFormMap.put("orderby", order);
		classifyFormMap = toFormMap(classifyFormMap, pageNow, pageSize,classifyFormMap.getStr("orderby"));
		List<ClassifyFormMap> list = classifyMapper.findClassifyPage(classifyFormMap);
        pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 新增页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/classify/add";
	}
	
	/**
	 * 新增分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="商品分类管理",methods="商品分类管理-新增分类")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
			classifyFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			classifyFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			classifyFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			classifyFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			classifyMapper.addEntity(classifyFormMap);//新增后返回新增信息
		} catch (Exception e) {
			logger.error("新增商品分类失败", e);
			throw new SystemException("新增商品分类失败");
		} 
		return "success";
	}

	/**
	 * 删除分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品分类管理",methods="商品分类管理-删除分类")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				classifyMapper.deleteByAttribute("id", id, ClassifyFormMap.class);
			} catch (Exception e) {
				logger.error("删除商品分类失败", e);
				throw new SystemException("删除商品分类失败");
			}
		}
		return "success";
	}

	/**
	 * 编辑页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("classify", classifyMapper.findClassifyById(id));
		}
		return Common.BACKGROUND_PATH + "/system/classify/edit";
	}

	/**
	 * 编辑分类信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品分类管理",methods="商品分类管理-修改分类")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
		try {
			classifyFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			classifyFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			classifyMapper.editEntity(classifyFormMap);
		} catch (Exception e) {
			logger.error("修改商品分类失败", e);
			throw new SystemException("修改商品分类失败");
		}
		return "success";
	}
	
	/**
	 * 提供分类信息查询接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("classifyList")
	public List<ClassifyFormMap> classifyList() {
		ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
		List<ClassifyFormMap> classifyList = classifyMapper.findClassifyList(classifyFormMap);
		return classifyList;
	}
	
	/**
	 * 查看分类下商品 页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("view")
	public String viewItem(Model model) {
		model.addAttribute("id",getPara("id"));
		return Common.BACKGROUND_PATH + "/system/classify/viewItem";
	}
	
	/**
	 * 查看该分类下商品信息
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("viewItem")
	public PageView viewItem(String pageNow,String pageSize,String column,String sort) {
		String itemId = getPara("itemId");
		ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
		classifyFormMap = toFormMap(classifyFormMap, pageNow, pageSize,classifyFormMap.getStr("orderby"));
		classifyFormMap.set("item_id", Integer.valueOf(itemId));
		List<ItemFormMap> list = classifyMapper.viewItem(classifyFormMap);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 删除分类的商品数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteItem")
	public String deleteItem() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				ClassifyItemFormMap classifyItemFormMap = getFormMap(ClassifyItemFormMap.class);
				classifyItemFormMap.set("id", id);
				classifyItemFormMap.set("del_flag", "1");
				classifyItemMapper.editEntity(classifyItemFormMap);
			} catch (Exception e) {
				logger.error("删除商品失败", e);
				throw new SystemException("删除商品失败");
			}
		}
		return "success";
	}
	
	/**
	 * 商品图片修改 页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("imgItem")
	public String addPic(Model model) {
		String id = getPara("id");
		String page = getPara("ispass");
		model.addAttribute("id",id);
		if("cover".equals(page)){
			ClassifyImgFormMap map = new ClassifyImgFormMap();
			map.set("classify_id", id);
			map.set("type", 1);
			List<ClassifyImgFormMap> list = classifyImgMapper.getById(map);
			model.addAttribute("list",list);
			return Common.BACKGROUND_PATH + "/system/classify/imgItem";
		}else{
			ClassifyImgFormMap map = new ClassifyImgFormMap();
			map.set("classify_id", id);
			map.set("type", 2);
			List<ClassifyImgFormMap> list = classifyImgMapper.getById(map);
			model.addAttribute("list",list);
			return Common.BACKGROUND_PATH + "/system/classify/img";
		}
	}
	
	/**
	 * 商品分类封面管理
	 * @return
	 */
	@RequestMapping("viewCoverImg")
	@ResponseBody
	public String ImgItem(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] imgs = request.getParameterValues("img_id");
		String[] urls = request.getParameterValues("link");
		ClassifyImgFormMap map = getFormMap(ClassifyImgFormMap.class);
		try {
			classifyImgMapper.deleteImg(map.getStr("classify_id"),1);
			if(imgs != null){
				for(int i=0;i<imgs.length;i++){
					//图片存入附件表
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",imgs[i]);
					attachmentMapper.addAttachmentEntity(attachment);
					//附件id存入分类表
					ClassifyImgFormMap imgMap = new ClassifyImgFormMap();
					imgMap.set("classify_id", map.getStr("classify_id"));
					imgMap.set("img_id",attachment.get("id"));
					imgMap.set("url", urls[i]);
					imgMap.set("type", 1);
					imgMap.set("seq", i+1);
					classifyImgMapper.addEntity(imgMap);
				}
			}
		} catch (Exception e) {
			logger.error("商品分类图片修改失败", e);
			throw new SystemException("商品分类图片修改失败");
		}
		return "success";
	}
	
	/**
	 * 商品分类截图管理
	 * @return
	 */
	@RequestMapping("viewImg")
	@ResponseBody
	public String viewCoverImg(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] imgs = request.getParameterValues("img_id");
		String[] urls = request.getParameterValues("link");
		ClassifyImgFormMap map = getFormMap(ClassifyImgFormMap.class);
		try {
			classifyImgMapper.deleteImg(map.getStr("classify_id"),2);
			if(imgs != null){
				for(int i=0;i<imgs.length;i++){
					//图片存入附件表
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",imgs[i]);
					attachmentMapper.addAttachmentEntity(attachment);
					//附件id存入分类表
					ClassifyImgFormMap imgMap = new ClassifyImgFormMap();
					imgMap.set("classify_id", map.getStr("classify_id"));
					imgMap.set("img_id",attachment.get("id"));
					imgMap.set("url", urls[i]);
					imgMap.set("type", 2);
					imgMap.set("seq", i+1);
					classifyImgMapper.addEntity(imgMap);
				}
			}
		} catch (Exception e) {
			logger.error("商品分类图片修改失败", e);
			throw new SystemException("商品分类图片修改失败");
		}
		return "success";
	}
	
}