package com.titi.controller.system;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.titi.entity.*;
import com.titi.mapper.*;
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
import com.titi.exception.SystemException;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.DateUtil;
import com.titi.util.JsonUtils;
import com.titi.util.POIUtils;
import com.titi.util.WebUtil;

/**
 * 商品信息  action
 * @author 曾雄
 */
@Controller
@RequestMapping("/item/")
public class ItemController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
		
	@Inject
	private ItemMapper itemMapper;//注入ItemMapper
	
	@Inject
	private AttachmentMapper attachmentMapper;//注入AttachmentMapper
	
	@Inject
	private UserHealthMapper userHealthMapper;//注入UserHealthMapper

	@Inject
	private ItemBoxMapper itemBoxMapper;//注入ItemBoxMapper
	
	@Inject
	private ItemSaleRecordMapper itemSaleRecordMapper;//注入itemSaleRecordMapper

	@Inject
	private ClassifyItemMapper classifyItemMapper;//注入ClassifyItemMapper
	/**
	 * 查询操作权限,跳转到商品列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/item/list";
	}

	/**
	 * 分页查询商品列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,String pageSize,String column,String sort) {
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		String order = "";
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by seq desc,create_date desc";
		}
		itemFormMap.put("orderby", order);
		itemFormMap = toFormMap(itemFormMap, pageNow, pageSize,itemFormMap.getStr("orderby"));
        pageView.setRecords(itemMapper.findItemPage(itemFormMap));
		return pageView;
	}
	
	/**
	 * 跳转到新增商品页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		String product_id = getPara("product_id");
		if(Common.isNotEmpty(product_id)){
			model.addAttribute("product_id", product_id);
		}
		return Common.BACKGROUND_PATH + "/system/item/add";
	}
	
	/**
	 * 新增商品数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="商品管理",methods="商品管理-新增商品")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity(){
		try {
			ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
			//统一处理富文本编辑器中的图片
//			String htmlStr = filterAttach((String)itemFormMap.get("detail"));
//			itemFormMap.set("detail", Common.replaceCharacter(htmlStr));
			itemFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			itemFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			itemFormMap.set("item_code", WebUtil.getRandNum());
			itemFormMap.set("favorite_count", "0");
			itemFormMap.set("share_count", "0");
			itemFormMap.set("view_count", "0");
			itemMapper.addItem(itemFormMap);//新增后返回新增信息

			ClassifyItemFormMap classifyItemFormMap = getFormMap(ClassifyItemFormMap.class);
			classifyItemFormMap.set("classify_id", itemFormMap.get("classify_id"));
			classifyItemFormMap.set("item_id", itemFormMap.get("id"));
			int isExist = classifyItemMapper.findIsExist(classifyItemFormMap);
			try {
				if(isExist == 0){
					classifyItemFormMap.put("classify_id", itemFormMap.get("classify_id"));
					classifyItemFormMap.put("item_id", itemFormMap.get("id"));
					classifyItemMapper.addEntity(classifyItemFormMap);//新增后返回新增信息
				}
			} catch (Exception e) {
				logger.error("新增商品分类管理系信息失败", e);
				throw new SystemException("新增商品分类管理系信息失败");
			}
		} catch (Exception e) {
			logger.error("新增商品失败", e);
			throw new SystemException("新增商品失败");
		} 
		return "success";
	}

	/**
	 * 删除商品数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品管理",methods="商品管理-删除商品")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		String[] ids = Arrays.asList(getParaValues("ids")).get(0).split(",");
		for (String id : ids) {
			try {
				ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
				itemFormMap.set("id", id);
				itemFormMap.set("del_flag", "1");
				itemMapper.editEntity(itemFormMap);
			} catch (Exception e) {
				logger.error("删除商品失败", e);
				throw new SystemException("删除商品失败");
			}
		}
		return "success";
	}

	/**
	 * 跳转到编辑商品页面,需要处理商品详情图片信息
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		String type= getPara("type");
		if(Common.isNotEmpty(id)){
			ItemFormMap itemFormMap = itemMapper.findbyFrist("id", id, ItemFormMap.class);
			//处理编辑器图片
//			itemFormMap.set("detail", getUESource((String) itemFormMap.get("detail")));
			model.addAttribute("classify_id", classifyItemMapper.findClassIdByItmId(id));
			model.addAttribute("item", itemFormMap);
			model.addAttribute("type", type);
		}
		return Common.BACKGROUND_PATH + "/system/item/edit";
	}

	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="商品管理",methods="商品管理-修改商品")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() {
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		try {
			//处理详情图片
//			String htmlStr = filterAttach((String)itemFormMap.get("detail"));
//			itemFormMap.set("detail", Common.replaceCharacter(htmlStr));
			itemFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			itemFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			itemMapper.editEntity(itemFormMap);

			String clId = classifyItemMapper.findClassIdByItmId((String)itemFormMap.get("id"));
			try {
				if(!clId.equals(itemFormMap.get("classify_id"))){
					ClassifyItemFormMap classifyItemFormMap = getFormMap(ClassifyItemFormMap.class);
					classifyItemFormMap.put("classify_id", itemFormMap.get("classify_id"));
					classifyItemFormMap.put("item_id", itemFormMap.get("id"));
					classifyItemMapper.editClassId(classifyItemFormMap);//新增后返回新增信息
				}
			} catch (Exception e) {
				logger.error("修改商品失败", e);
				throw new SystemException("修改商品失败");
			}
		} catch (Exception e) {
			logger.error("修改商品失败", e);
			throw new SystemException("修改商品失败");
		}
		return "success";
	}
	
	/**
	 * 用于显示商品下拉列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("itemList")
	public List<ItemFormMap> itemList() {
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		List<ItemFormMap> items = itemMapper.findAllItem(itemFormMap);
		return items;
	}
	
	/**
	 * 商品图片修改 页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("addPicUI")
	public String addPic(Model model) {
		String id = getPara("id");
		String page = getPara("ispass");//区分跳转页面
		String pac_num = getPara("pacnum");//套餐份数 用于动态获取需要配送的天数
		String city_id = getPara("city_id");//如果是套餐,套餐所属的城市。套餐包含的单品列表要判断城市
		model.addAttribute("id",id);
		if("addPic".equals(page)){
			//商品图片
			List<ItemPicFormMap> imgList = itemMapper.getPic(id,"2");
			model.addAttribute("imgList",imgList);
			return Common.BACKGROUND_PATH + "/system/item/addpic";
		}else if("addItem".equals(page)){
			if(!WebUtil.isEmpty(pac_num)){
				List<Integer> pacList = new ArrayList<Integer>();
				for(int i = 0; i < Integer.parseInt(pac_num); i++){
					pacList.add(i);
				}
				model.addAttribute("pacList", pacList);
			}
			
			if(!WebUtil.isEmpty(city_id)){
				model.addAttribute("city_id", city_id);
			}
			return Common.BACKGROUND_PATH + "/system/item/addItem"; 
		}else if("addCoverPic".equals(page)){
			//商品封面
			List<ItemPicFormMap> coverList = itemMapper.getPic(id,"1");
			model.addAttribute("coverList",coverList);
			return Common.BACKGROUND_PATH + "/system/item/coverPic";
		}else if("addDetailPic".equals(page)){
			List<ItemPicFormMap> detailList = itemMapper.getPic(id,"3");
			model.addAttribute("list",detailList);
			return Common.BACKGROUND_PATH + "/system/item/detailPic";
		}else {
			return Common.BACKGROUND_PATH + "/system/item/viewItem";
		}
	}
	
	/**
	 * 商品图片修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatePicUI")
	@SystemLog(module="商品管理信息列表",methods="商品管理信息列表-商品图片修改")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String update(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] arr = request.getParameterValues("img_id");
		try {
			ItemFormMap item = getFormMap(ItemFormMap.class);
			//删除原有图片
			itemMapper.deleteImg(String.valueOf(item.get("item_id")),"2");
			//插入现有图片
			if(arr != null){
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", item.get("item_id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 2);
					itemMapper.saveImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("商品图片修改失败", e);
			throw new SystemException("商品图片修改失败");
		}
		return "success";
	}
	
	/**
	 * 商品封面
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCoverPicUI")
	public String updateCoverPicUI(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] arr = request.getParameterValues("img_id");
		try {
			ItemFormMap item = getFormMap(ItemFormMap.class);
			//删除原有图片
			itemMapper.deleteImg(String.valueOf(item.get("item_id")),"1");
			//插入现有图片
			if(arr != null){
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", item.get("item_id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 1);
					itemMapper.saveImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("商品图片修改失败", e);
			throw new SystemException("商品图片修改失败");
		}
		return "success";
	}
	
	
	/**
	 * 单品列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("single")
	public PageView getList(String pageNow,String pageSize,String column,String sort) {
		String name = getPara("name");
		String city_id = getPara("city_id");
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		itemFormMap = toFormMap(itemFormMap, pageNow, pageSize,itemFormMap.getStr("orderby"));
		if(!WebUtil.isEmpty(name)){
			itemFormMap.set("item_nm", name);
		}
		if(!WebUtil.isEmpty(city_id)){
			itemFormMap.set("city_id", city_id);
		}
		List<ItemFormMap> list = itemMapper.singleItem(itemFormMap);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 跳转到营养师新增套餐页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addBoxUI")
	public String addBoxUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("box_id",id);
		}
		return Common.BACKGROUND_PATH + "/system/item/addHealthBox";
	}
	
	/**
	 * 营养师新增套餐数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addHealthItemEntity")
	@SystemLog(module="商品管理",methods="商品管理-新增营养师套餐")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addHealthItemEntity(){
		try {
			ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
//			String htmlStr = filterAttach((String)itemFormMap.get("detail"));
//			itemFormMap.set("detail", Common.replaceCharacter(htmlStr));
			
			itemFormMap.set("product_id", "1");//固定营养师套餐 产品id为1
			itemFormMap.set("create_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			itemFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
			itemFormMap.set("update_by", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
			itemFormMap.set("update_date", new Timestamp(System.currentTimeMillis()));
			itemFormMap.set("item_code", WebUtil.getRandNum());
			if(itemMapper.addHealthItemEntity(itemFormMap)){
				String id = getPara("box_id");
				if(Common.isNotEmpty(id)){
					UserHealthFormMap userHealthFormMap = userHealthMapper.findbyFrist("id", id, UserHealthFormMap.class);
					userHealthFormMap.set("box_id", itemFormMap.get("id"));
					userHealthMapper.editEntity(userHealthFormMap);
				}
			}
		} catch (Exception e) {
			logger.error("新增营养师套餐失败", e);
			throw new SystemException("新增商品失败");
		} 
		return "success";
	}
	
	/**
	 * 套餐添加单件商品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addSingleItem")
	public String addSingleItem(){
		String[] idStr = Arrays.asList(getParaValues("ids")).get(0).split(",");
		try {
			for(String id : idStr){
				ItemBoxFormMap map = new ItemBoxFormMap();
				map.set("pac_number", getPara("parts"));//份数
				map.set("day", getPara("days"));//第几天
				map.set("box_id", getPara("itemId"));//套餐id
				map.set("item_id", id);//单间商品id
				map.set("create_date", new Timestamp(System.currentTimeMillis()));
				map.set("dietitian_id", SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
				itemBoxMapper.addEntity(map);
			}
			return "success";
		} catch (Exception e) {
			logger.error("商品添加失败", e);
			throw new SystemException("商品添加失败");
		}
	}
	/**
	 * 查看套餐中商品
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
		String days = getPara("days");
 		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		itemFormMap = toFormMap(itemFormMap, pageNow, pageSize,itemFormMap.getStr("orderby"));
		if(!WebUtil.isEmpty(itemId)){
			itemFormMap.set("id", Integer.valueOf(itemId));
		}
		if(!WebUtil.isEmpty(days)){
			itemFormMap.set("day", days);
		}
		List<ItemFormMap> list = itemMapper.viewItem(itemFormMap);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 删除套餐中商品数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteItem")
	public String deleteItem() {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			try {
				itemBoxMapper.deleteByAttribute("id", id, ItemBoxFormMap.class);
			} catch (Exception e) {
				logger.error("删除商品失败", e);
				throw new SystemException("删除商品失败");
			}
		}
		return "success";
	}

	/**
	 * 商品封面
	 * @return
	 */
	@ResponseBody
	@RequestMapping("detailPic")
	public String detailPic(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] arr = request.getParameterValues("img_id");
		try {
			ItemFormMap item = getFormMap(ItemFormMap.class);
			//删除原有图片
			itemMapper.deleteImg(String.valueOf(item.get("item_id")),"3");
			//插入现有图片
			if(arr != null){
				for(int i=0;i<arr.length;i++){
					AttachmentFormMap attachment = new AttachmentFormMap();
					attachment.set("create_date", new Timestamp(System.currentTimeMillis()));
					attachment.set("url",arr[i]);
					//图片存入附件表
					attachmentMapper.addAttachmentEntity(attachment);
					
					//存入商品图片表
					ItemPicFormMap pic = new ItemPicFormMap();
					pic.set("item_id", item.get("item_id"));
					pic.set("img_id", attachment.get("id"));
					pic.set("seq", i+1);
					pic.set("type", 3);
					itemMapper.saveImg(pic);
				}
			}
		} catch (Exception e) {
			logger.error("商品图片修改失败", e);
			throw new SystemException("商品图片修改失败");
		}
		return "success";
	}
	
	/**
	 * 商品信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "商品列表";
		ItemFormMap itemFormMap = findHasHMap(ItemFormMap.class);
		String exportData = itemFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<ItemFormMap> list = itemMapper.findItemPage(itemFormMap);
		for(ItemFormMap item : list){
			if((item.get("pac_num") == null)){
				item.set("pac_num", "0");
			}
			if((item.get("tuan_num") == null)){
				item.set("tuan_num", "0");
			}
			if((item.get("tuan_price") == null)){
				item.set("tuan_price", "0.00");
			}
			if((item.get("tuan_expire") == null)){
				item.set("tuan_expire", "86400");
			}
			if((item.get("favorite_count") == null)){
				item.set("favorite_count", "0");
			}
			if((item.get("share_count") == null)){
				item.set("share_count", "0");
			}
			if((item.get("view_count") == null)){
				item.set("view_count", "0");
			}
			if((item.get("sale_hot") != null)){
				if("0".equals(item.get("sale_hot").toString())){
					item.set("sale_hot", "非热卖");
				}else{
					item.set("sale_hot", "热卖");
				}
			}
			if((item.get("from_sale") != null)){
				if("0".equals(item.get("from_sale").toString())){
					item.set("from_sale", "未下架");
				}else{
					item.set("from_sale", "下架");
				}
			}
			if((item.get("rec_yn") != null)){
				if("0".equals(item.get("rec_yn").toString())){
					item.set("rec_yn", "不推荐");
				}else{
					item.set("rec_yn", "推荐");
				}
			}
			if((item.get("show_yn") != null)){
				if("0".equals(item.get("show_yn").toString())){
					item.set("show_yn", "显示");
				}else{
					item.set("show_yn", "不显示");
				}
			}
		}
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
	
	/**
	 * 跳转到
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saleList")
	public String saleList(Model model) throws Exception {
		String item_id = getPara("item_id");
		if(Common.isNotEmpty(item_id)){
			model.addAttribute("item_id",item_id);
		}
		return Common.BACKGROUND_PATH + "/system/item/saleRecordList";
	}
	
	/**
	 * 商品销量详情
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findSaleRecordByPage")
	public PageView findSaleRecordByPage( String pageNow,String pageSize,String column,String sort) {
		String item_id = getPara("item_id");
		ItemSaleRecordFormMap itemSaleRecordFormMap = getFormMap(ItemSaleRecordFormMap.class);
		itemSaleRecordFormMap = toFormMap(itemSaleRecordFormMap, pageNow, pageSize,"");
		ItemSaleRecordFormMap record = itemSaleRecordMapper.findItemSaleRecordPage(itemSaleRecordFormMap).get(0);
		
		//循环查询该商品每天的销量信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(record != null){
			Date start = DateUtil.stringToDate2(record.get("min_create_date").toString());
			Date end = DateUtil.stringToDate2(record.get("max_create_date").toString());
			List<Date> listDate = DateUtil.getDatesBetweenTwoDate(start, end);
			List<ItemSaleRecordFormMap> records = new ArrayList<ItemSaleRecordFormMap>();
	        for(int i = 0; i < listDate.size(); i++){
	        	itemSaleRecordFormMap.set("item_id", item_id);
	            itemSaleRecordFormMap.set("startTime", sdf.format(listDate.get(i)));
	            itemSaleRecordFormMap.set("endTime", sdf.format(listDate.get(i)));
	            if(itemSaleRecordMapper.findItemSaleRecordPage(itemSaleRecordFormMap).get(0) != null){
	            	records.add(itemSaleRecordMapper.findItemSaleRecordPage(itemSaleRecordFormMap).get(0));
	            }
	        }
	        Collections.reverse(records);
	        pageView.setRowCount(records.size());
			pageView.setRecords(records);
		}
		return pageView;
	}
	
	/**
	 * 商品销量信息导出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportSaleRecord")
	public void downloadSaleRecord(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "商品销量列表";
		String item_id = getPara("item_id");
		ItemSaleRecordFormMap itemSaleRecordFormMap = findHasHMap(ItemSaleRecordFormMap.class);
		String exportData = itemSaleRecordFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		
		ItemSaleRecordFormMap record = itemSaleRecordMapper.findItemSaleRecordPage(itemSaleRecordFormMap).get(0);
		//循环查询该商品每天的销量信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(record != null){
			Date start = DateUtil.stringToDate2(record.get("min_create_date").toString());
			Date end = DateUtil.stringToDate2(record.get("max_create_date").toString());
			List<Date> listDate = DateUtil.getDatesBetweenTwoDate(start, end);
			List<ItemSaleRecordFormMap> records = new ArrayList<ItemSaleRecordFormMap>();
	        for(int i = 0; i < listDate.size(); i++){
	        	itemSaleRecordFormMap.set("item_id", item_id);
	            itemSaleRecordFormMap.set("startTime", sdf.format(listDate.get(i)));
	            itemSaleRecordFormMap.set("endTime", sdf.format(listDate.get(i)));
	            ItemSaleRecordFormMap r = itemSaleRecordMapper.findItemSaleRecordPage(itemSaleRecordFormMap).get(0);
	            r.set("create_date", sdf.format((Date) r.get("create_date")));
	            records.add(r);
	        }
	        Collections.reverse(records);
	        POIUtils.exportToExcel(response, listMap, records, fileName);
		}
	}

}