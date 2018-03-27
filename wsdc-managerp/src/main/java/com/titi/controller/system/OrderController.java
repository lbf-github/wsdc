package com.titi.controller.system;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.ApplyFormMap;
import com.titi.entity.ApplyPicFormMap;
import com.titi.entity.GroupBuyFormMap;
import com.titi.entity.ItemFormMap;
import com.titi.entity.OrderFormMap;
import com.titi.entity.OrderStatusFormMap;
import com.titi.exception.SystemException;
import com.titi.mapper.ApplyMapper;
import com.titi.mapper.GroupBuyMapper;
import com.titi.mapper.ItemMapper;
import com.titi.mapper.OrderMapper;
import com.titi.mapper.OrderStatusMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;
import com.titi.util.DateUtil;
import com.titi.util.JsonUtils;
import com.titi.util.POIUtils;
import com.titi.util.Send;
import com.titi.util.WebUtil;

/**
 * 订单信息 action
 * 
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/order/")
public class OrderController extends BaseController {

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private OrderMapper orderMapper;// 注入OrderMapper

	@Inject
	private OrderStatusMapper orderStatusMapper;// 注入OrderStatusMapper

	@Inject
	private ApplyMapper applyMapper;// 注入ApplyMapper

	@Inject
	private GroupBuyMapper groupBuyMapper;// 注入GroupBuyMapper

	@Inject
	private ItemMapper itemMapper;// 注入ItemMapper

	/**
	 * 跳转到订单列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String listUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/order/list";
	}
	
	/**
	 * 分页列表(未过滤)
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow, String pageSize, String column, String sort) throws ParseException {
		OrderFormMap orderFormMap = getFormMap(OrderFormMap.class);
		String order = "";
		if (Common.isNotEmpty(column)) {
			order = " order by " + column + " " + sort;
		} else {
			order = " order by create_date desc";
		}
		orderFormMap.put("orderby", order);
		orderFormMap = toFormMap(orderFormMap, pageNow, pageSize, orderFormMap.getStr("orderby"));
		List<OrderFormMap> orderList = orderMapper.findOrderPage(orderFormMap);
		pageView.setRecords(orderList);
		return pageView;
	}

	/**
	 * 分页查询订单列表
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage2")
	public PageView findByPage2(String pageNow, String pageSize, String column,
			String sort) throws ParseException {
		OrderFormMap orderFormMap = getFormMap(OrderFormMap.class);
		String order = "";
		if (Common.isNotEmpty(column)) {
			order = " order by " + column + " " + sort;
		} else {
			order = " order by create_date desc";
		}
		orderFormMap.put("orderby", order);
		orderFormMap = toFormMap(orderFormMap, pageNow, pageSize,
				orderFormMap.getStr("orderby"));
		List<OrderFormMap> orderList = orderMapper.findOrderPage(orderFormMap);
		List<OrderFormMap> orderList2 = new ArrayList<OrderFormMap>();
		// 处理配送时间戳和订单状态
//		for (int i = 0; i < orderList.size(); i++) {
		for (OrderFormMap o : orderList) {
//			OrderFormMap o = orderList.get(i);
			if (o.get("delivery_date2") != null) {
				Date delivery_date2 = (Date) o.get("delivery_date2");
				o.set("delivery_date2", WebUtil.getWeekOfDate(delivery_date2));
			}
			// 订单状态为待付款或已支付
			if (o.get("pay_no") != null && ("1".equals(o.get("status").toString()) || ("0".equals(o.get("status").toString())))) {
				// 订单拼团信息
				GroupBuyFormMap map = groupBuyMapper.getdetail(o.get("pay_no").toString());
	
				// 判断是否为团购
				if (map != null) {
					// 当前团列表
//					List<GroupBuyFormMap> groupList = groupBuyMapper.findByAttribute("create_uid",map.get("create_uid").toString(),GroupBuyFormMap.class);
					List<GroupBuyFormMap> groupList = groupBuyMapper.getGroupNum(map.get("create_uid").toString());//已经支付成功的团购人员
					int size = groupList.size();//团人数
					// 此商品支持几人团购
					ItemFormMap item = itemMapper.findbyFrist("id",o.get("item_id").toString(), ItemFormMap.class);
					// 当前团购是否满团且全部支付成功
					if (Integer.parseInt(item.get("tuan_num").toString()) != size) {
		//				orderList.remove(o);// 没满团不显示在订单列表
						
//						// 没满团时检查当前团购是否过期
//						String startTime = map.get("start_time").toString();
//						Timestamp ts = Timestamp.valueOf(startTime);// 拼团开始时间
//						Timestamp now = new Timestamp(
//								System.currentTimeMillis());// 当前时间
//						// 拼团开始时间与当前时间是否超过45分钟
//						if ((now.getTime() - ts.getTime()) / 1000 / 60 / 24 <= 45) {
//							o.put("group", "拼团中");
//							orderList.remove(i);// 不显示在订单列表
//						} else {
//							o.put("group", "拼团失败");
//							orderList.remove(i);// 不显示在订单列表
//						}
					} else {
						// 已满团不做处理
						// o.put("group", "满团");
						
						//满团时是否所有团都付款成功
//						for(int j=0;j<groupList.size();j++){
//							//当拼团人员中有用户没有付款或付款失败时也不在列表显示
//							if("1".equals(groupList.get(j).get("status").toString()) || "3".equals(groupList.get(j).get("status").toString())){
//						//		orderList.remove(o);//
//								break;
//							}else{
//							}
//						}
								orderList2.add(o);
					}
				}else{
					orderList2.add(o);
				}
			}
		}
		pageView.setRecords(orderList2);
		return pageView;
	}

	/**
	 * 分页查询订单状态
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orderStatus")
	public PageView orderStatus(String pageNow, String pageSize, String column,
			String sort) {
		String id = getPara("id");
		OrderStatusFormMap map = getFormMap(OrderStatusFormMap.class);
		map.put("sn1", id);
		String order = "";
		if (Common.isNotEmpty(column)) {
			order = " order by " + column + " " + sort;
		} else {
			order = " order by update_date desc";
		}
		map.put("orderby", order);
		map = toFormMap(map, pageNow, pageSize, map.getStr("orderby"));
		pageView.setRecords(orderStatusMapper.findByPage(map));
		return pageView;
	}

	/**
	 * 物流页面跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("handle")
	public String handle(Model model) {
		String sn1 = getPara("id");
		List<OrderFormMap> list = orderMapper.findByAttribute("sn1", sn1,
				OrderFormMap.class);
		model.addAttribute("list", list);
		model.addAttribute("sn1", sn1);
		OrderFormMap map = list.get(0);
		if ("4".equals(map.get("status").toString())) {
			ApplyFormMap apply = applyMapper.findbyFrist("sn1",
					map.getStr("sn1"), ApplyFormMap.class);
			if (apply != null) {
				model.addAttribute("apply", apply);
			}
		}
		return Common.BACKGROUND_PATH + "/system/order/orderHandle";
	}

	/**
	 * 物流信息操作
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addSupport")
	public String addSupport() {
		OrderStatusFormMap map = getFormMap(OrderStatusFormMap.class);
		try {
			map.set("update_date", new Timestamp(System.currentTimeMillis()));
			// 订单完成时更改订单状态为完成
			if ("配送成功".equals(map.get("remark"))) {
				OrderFormMap order = new OrderFormMap();
				order.set("id", map.get("order_id"));
				order.set("status", 2);
				order.set("finish_date", new Timestamp(System.currentTimeMillis()));
				orderMapper.editEntity(order);
			}
			if("骑手接单中".equals(map.get("remark"))){
				OrderFormMap order = orderMapper.findbyFrist("id", map.get("order_id").toString(), OrderFormMap.class);
				Send.SendMsg(order.get("mobile").toString(), "【全城游】您订购的鲜果已备货完毕，骑手将火速为您派送，请耐心等待并保持电话畅通！");
			}
			orderStatusMapper.addEntity(map);
		} catch (Exception e) {
			logger.error("提交失败", e);
			throw new SystemException("提交失败");
		}
		return "success";
	}

	/**
	 * 售后申请信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("apply")
	public String applyDetail(Model model) {
		String sn1 = getPara("sn1");
		ApplyFormMap map = applyMapper.getDetail(sn1);
		if (map != null) {
			model.addAttribute("map", map);
			List<ApplyPicFormMap> list = applyMapper.getPic(map.get("id")
					.toString());
			model.addAttribute("list", list);
		}
		return Common.BACKGROUND_PATH + "/system/order/applyDetail";
	}

	/**
	 * 导出订单列表信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "订单列表";
		OrderFormMap orderFormMap = findHasHMap(OrderFormMap.class);
		String exportData = orderFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<OrderFormMap> list = orderMapper.findOrderPage(orderFormMap);
		for (int i = 0; i < list.size(); i++) {
			OrderFormMap o = list.get(i);
			if (o.get("delivery_date2") != null) {
				Date delivery_date2 = (Date) o.get("delivery_date2");
				o.set("delivery_date2", WebUtil.getWeekOfDate(delivery_date2));
			}
			if(o.get("freight") == null){
				o.set("freight", 0);
			}
			
			// 订单状态为待付款或已支付
			if (o.get("pay_no") != null
					&& ("1".equals(o.get("status").toString()))) {
				// 订单拼团信息
				GroupBuyFormMap map = groupBuyMapper.getdetail(o.get("pay_no").toString());
	
				// 判断是否为团购
				if (map != null) {
					// 当前团有几人
					int size = groupBuyMapper.findByAttribute("create_uid",
							map.get("create_uid").toString(),
							GroupBuyFormMap.class).size();
					// 此商品支持几人团购
					ItemFormMap item = itemMapper.findbyFrist("id",
							o.get("item_id").toString(), ItemFormMap.class);
					// 当前团购是否满团
					if (Integer.parseInt(item.get("tuan_num").toString()) != size
							&& size != 0) {
						// 没满团时检查当前团购是否过期
						String startTime = map.get("start_time").toString();
						Timestamp ts = Timestamp.valueOf(startTime);// 拼团开始时间
						Timestamp now = new Timestamp(
								System.currentTimeMillis());// 当前时间
						// 拼团开始时间与当前时间是否超过45分钟
						if ((now.getTime() - ts.getTime()) / 1000 / 60 / 24 <= 45) {
							o.put("group", "拼团中");
							list.remove(i);// 不显示在订单列表
						} else {
							o.put("group", "拼团失败");
							list.remove(i);// 不显示在订单列表
						}
					} else {
						// 已满团不做处理
						// o.put("group", "满团");
					}
				}
			}
			
			// 处理 订单导出的创建时间 后缀带.0的问题
			if (o.get("create_date") != null) {
				o.set("create_date",
						DateUtil.formatDate(DateUtil.stringToDate(o.get(
								"create_date").toString())));
			}
			
			if(o.get("status") != null && "2".equals(o.get("status").toString())){
				o.set("status", "待评价");
			}
			
			if(o.get("status") != null && "3".equals(o.get("status").toString())){
				o.set("status", "已完成");
			}
			
			if(o.get("sub_status") == null){
				if("-1".equals(o.get("status").toString())){
					o.set("status", "取消");
				}else if("0".equals(o.get("status").toString())){
					o.set("status", "待付款");
				}else if("1".equals(o.get("status").toString())){
					o.set("status", "待收货");
				}else if("4".equals(o.get("status").toString())){
					o.set("status", "售后");
				}
			}else{
				if("1".equals(o.get("status").toString()) || "4".equals(o.get("status").toString())){
					o.set("status", o.get("sub_status"));
				}
			}
		}
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}

}