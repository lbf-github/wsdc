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
import com.titi.entity.AttachmentFormMap;
import com.titi.entity.ItemPicFormMap;
import com.titi.entity.ItemProductFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.AttachmentMapper;
import com.titi.mapper.ItemMapper;
import com.titi.mapper.ItemProductMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

/**
 * 产品信息  action
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/itemProduct/")
public class ItemProductController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ItemProductController.class);
		
	@Inject
	private ItemProductMapper itemProductMapper;//注入ItemProductMapper
	
	@Inject
	private ItemMapper itemMapper;//注入itemMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入AttachmentMapper
	
	/**
	 * 查询操作权限,跳转到产品列表页面
	 * @param model modle
	 * @return string
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/itemProduct/list";
	}

	/**
	 * 分页查询产品列表
	 * @param pageNow pageNow
	 * @param pageSize pageSize
	 * @param column column
	 * @param sort sort
	 * @return PageView
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,String pageSize,String column,String sort) {
		ItemProductFormMap itemProductFormMap = getFormMap(ItemProductFormMap.class);
		String order;
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by seq desc,create_date desc";
		}
		itemProductFormMap.put("orderby", order);
		itemProductFormMap = toFormMap(itemProductFormMap, pageNow, pageSize,itemProductFormMap.getStr("orderby"));
        pageView.setRecords(itemProductMapper.findItemProductPage(itemProductFormMap));
		return pageView;
	}
	
	/**
	 * 跳转到新增商品页面
	 * @return string
	 */
	@RequestMapping("addUI")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/system/itemProduct/add";
	}
	
	/**
	 * 新增产品数据
	 * @return string
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="产品管理",methods="产品管理-新增产品")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			ItemProductFormMap itemProductFormMap = getFormMap(ItemProductFormMap.class);
			
			//添加产品详情图片
			if(itemProductFormMap.get("detailpic") != null){
				String[] arr = itemProductFormMap.get("detailpic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 3);
					itemMapper.saveImg(pic);
				}
			}
			
			//产品图片
			if(itemProductFormMap.get("itempic") != null){
				String[] arr = itemProductFormMap.get("itempic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 2);
					itemMapper.saveImg(pic);
				}
			}
			
			//产品封面
			if(itemProductFormMap.get("coverpic") != null){
				String[] arr = itemProductFormMap.get("coverpic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 1);
					itemMapper.saveImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("新增产品失败", e);
			throw new SystemException("新增产品失败");
		} 
		return "success";
	}

	/**
	 * 跳转到编辑产品页面
	 * @param model model
	 * @return string
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		String type= getPara("type");
		
		if(Common.isNotEmpty(id)){
			ItemProductFormMap itemProductFormMap = itemMapper.findbyFrist("id", id, ItemProductFormMap.class);
			//itemProductFormMap.set("detail", getUESource((String) itemProductFormMap.get("detail")));
			model.addAttribute("itemProduct", itemProductFormMap);
			model.addAttribute("type", type);
			
			List<ItemPicFormMap> coverPicList = itemMapper.getPic(id,"1");//封面图片
			String list1 = "";
			for(ItemPicFormMap map: coverPicList){
				list1 += ","+map.get("img_id").toString();
			}
			
			List<ItemPicFormMap> itemPicList = itemMapper.getPic(id,"2");//商品图片
			String list2 = "";
			for(ItemPicFormMap map: itemPicList){
				list2 += ","+map.get("img_id").toString();
			}
			
			List<ItemPicFormMap> detailPicList = itemMapper.getPic(id,"3");//详情图片
			String list3 = "";
			for(ItemPicFormMap map: detailPicList){
				list3 += ","+map.get("img_id").toString();
			}
			model.addAttribute("list1", list1);
			model.addAttribute("coverPicList", coverPicList);
			model.addAttribute("list2", list2);
			model.addAttribute("itemPicList", itemPicList);
			model.addAttribute("list3", list3);
			model.addAttribute("detailPicList", detailPicList);
		}
		return Common.BACKGROUND_PATH + "/system/itemProduct/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品管理",methods="产品管理-修改产品")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		ItemProductFormMap itemProductFormMap = getFormMap(ItemProductFormMap.class);
		try {
			//图片处理
			//删除原有图片
			itemMapper.deleteImg(String.valueOf(itemProductFormMap.get("id")),"1");
			itemMapper.deleteImg(String.valueOf(itemProductFormMap.get("id")),"2");
			itemMapper.deleteImg(String.valueOf(itemProductFormMap.get("id")),"3");
			
			//添加产品详情图片
			if(itemProductFormMap.get("detailpic") != null){
				String[] arr = itemProductFormMap.get("detailpic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 3);
					itemMapper.saveImg(pic);
				}
			}
			
			//产品图片
			if(itemProductFormMap.get("itempic") != null){
				String[] arr = itemProductFormMap.get("itempic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 2);
					itemMapper.saveImg(pic);
				}
			}
			
			//商品封面
			if(itemProductFormMap.get("coverpic") != null){
				String[] arr = itemProductFormMap.get("coverpic").toString().substring(1).split(",");
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", itemProductFormMap.get("id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 1);
					itemMapper.saveImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("修改产品失败", e);
			throw new SystemException("修改产品失败");
		}
		return "success";
	}
	
}