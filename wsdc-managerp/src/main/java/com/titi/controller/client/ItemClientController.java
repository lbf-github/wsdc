package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.*;
import com.titi.exception.SystemException;
import com.titi.mapper.*;
import com.titi.plugin.PageView;
import com.titi.util.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品信息  action
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class ItemClientController extends BaseController {
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(ItemClientController.class);
		
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
	private ClassifyMapper classifyMapper;//注入ClassifyMapper

	@Inject
	private CartMapper cartMapper;//注入cartMapper

	@Inject
	private OrderMapper orderMapper;//注入cartMapper

	/**
	 * 查询商品类别信息
	 */
	@RequestMapping(value = "classifyList.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询商品类别", methods = "classifyList.do")
	public @ResponseBody
	OutputResult classifyList( String pageNow,String pageSize,String column,String sort) {
		OutputResult output = new OutputResult();
		ClassifyFormMap classifyForm = getFormMap(ClassifyFormMap.class);
		String order ;
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by seq desc,create_date desc";
		}
		classifyForm.put("orderby", order);
		classifyForm = toFormMap(classifyForm, pageNow, pageSize,classifyForm.getStr("orderby"));
		output.setResult(Constants.RESULT_SUCCESS);
		output.setMsg("查询成功");
		output.setResultList(classifyMapper.findClassifyPage(classifyForm));
		return output;
	}

	/**
	 * 查询商品列表信息
	 */
	@RequestMapping(value = "itemList.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询商品列表", methods = "itemList.do")
	public @ResponseBody
	OutputResult findByPage( String pageNow,String pageSize,String column,String sort) {
		OutputResult output = new OutputResult();
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		String order ;
		if(Common.isNotEmpty(column)){
			order = " order by "+column+" "+sort;
		}else{
			order = " order by seq desc,create_date desc";
		}
		itemFormMap.put("orderby", order);
		itemFormMap = toFormMap(itemFormMap, pageNow, pageSize, itemFormMap.getStr("orderby"));
		output.setResult(Constants.RESULT_SUCCESS);
		output.setMsg("查询成功");
		output.setResultList(itemMapper.findItemPage(itemFormMap));
		return output;
	}

	/**
	 * 查询商品详情
	 */
	@RequestMapping(value = "itemDetail.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询商品详情", methods = "itemDetail.do")
	public @ResponseBody
	OutputResult itemDetail(String id) {
		OutputResult output = new OutputResult();
		if (id != null) {
			ItemFormMap itemFormMap = itemMapper.findbyFrist("id", id, ItemFormMap.class);
			output.setResult(Constants.RESULT_SUCCESS);
			output.setMsg("查询成功");
			output.setResultList(itemMapper.findItemPage(itemFormMap));
		}else {
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("参数为空");
		}
		return output;
	}

	/**
	 * 根据商品类别查询商品信息
	 */
	@RequestMapping(value = "claItemList.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查询类别下商品列表", methods = "claItemList.do")
	public @ResponseBody
	OutputResult findItemByPage(String id, String pageNow,String pageSize) {
		OutputResult output = new OutputResult();
		try {
			if (!Common.isEmpty(id)) {
				ClassifyFormMap classifyFormMap = getFormMap(ClassifyFormMap.class);
				classifyFormMap = toFormMap(classifyFormMap, pageNow, pageSize,classifyFormMap.getStr("orderby"));
				classifyFormMap.set("item_id", Integer.valueOf(id));
				List<ItemFormMap> list = classifyMapper.viewItem(classifyFormMap);
				output.setResult(Constants.RESULT_SUCCESS);
				output.setResultList(list);
				output.setMsg("查询成功");
			} else {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
			}
		}catch (NumberFormatException e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("参数错误");
		}
		return output;
	}

	/**
	 * 根据商品加入购物车
	 */
	@RequestMapping(value = "addCart.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "加入购物车", methods = "addCart.do")
	public @ResponseBody
	OutputResult addCart(String item_id, String number,String user_id) {
		OutputResult output = new OutputResult();
		try {
			if (!Common.isEmpty(item_id)&&!Common.isEmpty(number)&&!Common.isEmpty(user_id)) {
				CartFormMap cartForm = getFormMap(CartFormMap.class);
				cartForm.set("item_id", Integer.valueOf(item_id));
				cartForm.set("number", Integer.valueOf(number));
				cartForm.set("user_id", Integer.valueOf(user_id));
				cartForm.set("create_date", new Timestamp(System.currentTimeMillis()));
				cartMapper.addEntity(cartForm);
				output.setResult(Constants.RESULT_SUCCESS);
				output.setMsg("添加成功");
			} else {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
			}
		}catch (NumberFormatException e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("参数错误");
		}catch (Exception e){
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("操作异常");
		}
		return output;
	}

	/**
	 * 查看购物车
	 */
	@RequestMapping(value = "selCart.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "查看购物车", methods = "selCart.do")
	public @ResponseBody
	OutputResult selCart(String user_id, String pageNow,
						 String pageSize,String column,String sort) {
		OutputResult output = new OutputResult();
		try {
			if (!Common.isEmpty(user_id)) {
				CartFormMap cartForm = getFormMap(CartFormMap.class);

				String order = "";
				if(Common.isNotEmpty(column)){
					order += " order by "+column+" "+sort;
				}else{
					order += " order by create_date desc";
				}
				cartForm.set("user_id", user_id);
				cartForm.put("orderby", order);

				cartForm = toFormMap(cartForm, pageNow, pageSize, cartForm.getStr("orderby"));
				output.setResultList(cartMapper.findCartPage(cartForm));

				output.setResult(Constants.RESULT_SUCCESS);
				output.setMsg("查询成功");
			} else {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
			}
		}catch (NumberFormatException e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("参数错误");
		}catch (Exception e){
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("操作异常");
		}
		return output;
	}

	/**
	 * 删除购物车
	 */
	@RequestMapping(value = "delCart.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "删除购物车", methods = "delCart.do")
	public @ResponseBody
	OutputResult delCart(String id) {
		OutputResult output = new OutputResult();
		try {
			if (!Common.isEmpty(id)) {
//				CartFormMap cartForm = getFormMap(CartFormMap.class);
//
//				cartForm.set("id", id);

				CartFormMap cartForm = cartMapper.findbyFrist("id", id, CartFormMap.class);

//				output.setResultList(cartMapper.findCartPage(cartForm));
				if (cartForm != null){
					cartForm.set("del_flag", 1);
					cartMapper.editEntity(cartForm);
					output.setResultDao(cartForm);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("删除成功");
				}else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("商品部存在");
				}

			} else {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("参数为空");
			}
		}catch (NumberFormatException e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("参数错误");
		}catch (Exception e){
			output.setResult(Constants.RESULT_ERROR);
			output.setMsg("操作异常");
		}
		return output;
	}

	/**
	 * 单品直接购买
	 * 生成订单
	 *
	 */
	@RequestMapping(value = "order.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "生成订单", methods = "order.do")
	public @ResponseBody
	OutputResult createOrder(HttpServletRequest request) {
		OutputResult result = new OutputResult();
		String itemId = request.getParameter("itemId"); //订单商品id
		String userId = request.getParameter("userId"); //订单商品id
		String item_num = request.getParameter("itemNum"); //商品个数
		String money = request.getParameter("money"); //订单交易额
		String name = request.getParameter("name");  //收货人姓名
		String mobile = request.getParameter("mobile"); //收货人电话
		String address = request.getParameter("address"); //收货地址
		String memo = request.getParameter("memo");  //留言
		String deliver_mode = request.getParameter("deliver");  //留言
		if (!Common.isEmpty(itemId)&&!Common.isEmpty(item_num)&&!Common.isEmpty(money)&&!Common.isEmpty(name)&&
		!Common.isEmpty(mobile)&&!Common.isEmpty(address)){
			ItemFormMap itemFormMap = itemMapper.findbyFrist("id", itemId, ItemFormMap.class);
			if (itemFormMap != null) {
				try {
					double price = Double.parseDouble(itemFormMap.get("price_single").toString());
					if (price * Double.parseDouble(item_num) != Double.parseDouble(money)) {
						result.setResult(Constants.RESULT_STATUS_EXCEPTION);
						result.setMsg("参数数据错误");
						return result;
					}
				}catch (NumberFormatException e) {
					e.printStackTrace();
					result.setResult(Constants.RESULT_STATUS_EXCEPTION);
					result.setResult("参数数据错误");
					return result;
				}

			}else {
				result.setResult(Constants.RESULT_ERROR);
				result.setMsg("商品不存在");
				return result;
			}
			try {
				//插入订单表
				OrderFormMap orderForm = getFormMap(OrderFormMap.class);
				orderForm.set("sn1", getOrderIdByUUId());
				orderForm.set("user_id", userId);
				orderForm.set("item_num", item_num);
				orderForm.set("money", money);
				orderForm.set("name", name);
				orderForm.set("mobile", mobile);
				orderForm.set("address", address);
				orderForm.set("memo", memo);
				orderForm.set("create_date", new Timestamp(System.currentTimeMillis()));
				orderForm.set("deliver_mode", deliver_mode);
				orderMapper.addEntity(orderForm);

				//插入订单商品表
				OrderItemFormMap orderItemForm = getFormMap(OrderItemFormMap.class);
				orderItemForm.set("rec_id", orderForm.get("id"));
				orderItemForm.set("order_id", orderForm.get("sn1"));
				orderItemForm.set("goods_id", itemId);
				orderItemForm.set("goods_name", itemFormMap.get("title"));
				orderItemForm.set("goods_price", money);
				orderItemForm.set("goods_num", item_num);
				orderMapper.addEntity(orderItemForm);
				result.setResult(Constants.RESULT_SUCCESS);
				result.setMsg("订单生成成功");

			} catch (Exception e) {
				e.printStackTrace();
				result.setResult(Constants.RESULT_ERROR);
				result.setMsg("网络异常");
				return result;
			}
		}else {
			result.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			result.setMsg("参数为空");
			return result;
		}


		return result;
	}

	/**
	 * 购物车结算
	 * 生成订单
	 *
	 */
	@RequestMapping(value = "cartOrder.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "购物车结算订单", methods = "cartOrder.do")
	public @ResponseBody
	OutputResult createCartOrder(HttpServletRequest request) {
		OutputResult result = new OutputResult();
		String itemId = request.getParameter("itemId"); //订单商品id,可能存在多个商品结算，id以" , " 号拼接
		String item_num = request.getParameter("itemNum"); //商品个数
		String userId = request.getParameter("userId"); //购买人
		String money = request.getParameter("money"); //订单交易额
		String name = request.getParameter("name");  //收货人姓名
		String mobile = request.getParameter("mobile"); //收货人电话
		String address = request.getParameter("address"); //收货地址
		String memo = request.getParameter("memo");  //留言
		String deliver_mode = request.getParameter("deliver");  //运送方式
		if (!Common.isEmpty(itemId)&&!Common.isEmpty(item_num)&&!Common.isEmpty(money)&&!Common.isEmpty(name)&&
				!Common.isEmpty(mobile)&&!Common.isEmpty(address)){
			String[] items = itemId.split(",");
			if (items.length > 0) {
				double totalMoney = 0;
				for (String item : items) {
					ItemFormMap itemFormMap = itemMapper.findbyFrist("id", item, ItemFormMap.class);
					if (itemFormMap != null) {
						//TODO 商品购买个数的传递
						double price = Double.parseDouble(itemFormMap.get("price_single").toString());
						totalMoney += price;
					}else {
						result.setResult(Constants.RESULT_ERROR);
						result.setMsg("商品" + item + "不存在");
						return result;
					}
				}
				if (totalMoney != Double.parseDouble(money)) {
					result.setResult(Constants.RESULT_STATUS_EXCEPTION);
					result.setMsg("参数数据错误");
					return result;
				}
				try {
					//插入订单表
					OrderFormMap orderForm = getFormMap(OrderFormMap.class);
					orderForm.set("sn1", getOrderIdByUUId());
					orderForm.set("user_id", userId);
					orderForm.set("item_num", item_num);
					orderForm.set("money", money);
					orderForm.set("name", name);
					orderForm.set("mobile", mobile);
					orderForm.set("address", address);
					orderForm.set("memo", memo);
					orderForm.set("create_date", new Timestamp(System.currentTimeMillis()));
					orderForm.set("deliver_mode", deliver_mode);
					orderMapper.addEntity(orderForm);

					//插入订单商品表
					for (String item : items) {
						OrderItemFormMap orderItemForm = getFormMap(OrderItemFormMap.class);
						orderItemForm.set("rec_id", orderForm.get("id"));
						orderItemForm.set("order_id", orderForm.get("order_id"));
						orderItemForm.set("goods_id", item);
						orderItemForm.set("goods_price", money);
						orderItemForm.set("goods_num", item_num);
						orderMapper.addEntity(orderItemForm);
					}
					result.setResult(Constants.RESULT_SUCCESS);
					result.setMsg("订单生成成功");
				} catch (Exception e) {
					e.printStackTrace();
					result.setResult(Constants.RESULT_ERROR);
					result.setMsg("网络异常");
					return result;
				}
			}
		}else {
			result.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			result.setMsg("参数为空");
			return result;
		}
		return result;
	}

	/**
	 * 生成订单编号
	 * @return string
	 */
	private String getOrderIdByUUId (){
		int hashCodeV = UUID.randomUUID().toString().hashCode();

		if(hashCodeV < 0) {//有可能是负数
			hashCodeV = - hashCodeV;
		}

		return String.format("%016d", hashCodeV);
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