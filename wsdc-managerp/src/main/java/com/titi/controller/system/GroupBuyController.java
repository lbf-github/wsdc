package com.titi.controller.system;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.titi.controller.index.BaseController;
import com.titi.entity.GroupBuyFormMap;
import com.titi.entity.ItemFormMap;
import com.titi.mapper.GroupBuyMapper;
import com.titi.mapper.ItemMapper;
import com.titi.plugin.PageView;
import com.titi.util.Common;

@Controller
@RequestMapping("/group")
public class GroupBuyController extends BaseController {

	@Inject
	private GroupBuyMapper mapper;// 注入ItemMapper
	
	@Inject
	private ItemMapper itemMapper;
	
	/**
	 * 团购页面跳转
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/group/list";
	}
	
	/**
	 * 分页查询团购记录
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow, String pageSize,String column,String sort) {
		GroupBuyFormMap map = getFormMap(GroupBuyFormMap.class);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
		map.put("column", column);
		map.put("sort", sort);
		List<GroupBuyFormMap> list = mapper.selectList(map);
        for(int i=0;i<list.size();i++){
			//此商品支持几人团购
			ItemFormMap item = itemMapper.findbyFrist("id", list.get(i).get("itemId").toString(), ItemFormMap.class);
			//团购中有几人
			List<GroupBuyFormMap> groupList = mapper.getGroupNum(list.get(i).get("create_uid").toString());
			
			//是否满团
			if(!(item.get("tuan_num").toString()).equals(String.valueOf(groupList.size()))){
				//没满团时检查当前团购是否过期
				GroupBuyFormMap timeMap = getFormMap(GroupBuyFormMap.class);
				timeMap.set("create_uid", list.get(i).get("create_uid"));
				GroupBuyFormMap groupBuy = mapper.getTime(timeMap);
				String startTime = groupBuy.get("start_time").toString();//开团人的拼团开始时间
				Timestamp ts = Timestamp.valueOf(startTime);//拼团开始时间
				int tuanExpire = Integer.parseInt(item.get("tuan_expire").toString())/60;//商品有效开团时间，单位 分
				Timestamp now = new Timestamp(System.currentTimeMillis());//当前时间
				if((now.getTime()-ts.getTime())/1000/60 <= tuanExpire){
					list.get(i).put("group_status", "拼团中");
				}else{
					list.get(i).put("group_status", "拼团失败");
				}
			}else{
				list.get(i).put("groupNum", groupList.size());
				//已满团，拼团成功
				list.get(i).put("group_status", "拼团成功");
//				formMap.put('where","id=XX and name= XX order by XX") 
//				GroupBuyFormMap group = getFormMap(GroupBuyFormMap.class);
//				group.put("where", "create_uid="+list.get(i).get("create_uid").toString()+" group by tuan_uid");
//				List<GroupBuyFormMap> groupList = mapper.findByWhere(group);
				//此团购中是否有未支付成功状态
//				for(int j=0;j<groupList.size();j++){
//					if("1".equals(groupList.get(j).get("status").toString())){
//						
//					}
//				}
			}
		}
        pageView.setRecords(list);
        return pageView;
	}
	
	@RequestMapping("/orderList")
	public String toOrderList(Model model) throws Exception {
		String id = getPara("id");
		model.addAttribute("id", id);
		return Common.BACKGROUND_PATH + "/system/group/orderList";
	}
	
	/**
	 * 拼团中团列表
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 */
	@ResponseBody
	@RequestMapping("groupOrder")
	public PageView groupOrder( String pageNow, String pageSize,String column,String sort) {
		String id = getPara("id");
		GroupBuyFormMap map = getFormMap(GroupBuyFormMap.class);
		map = toFormMap(map, pageNow, pageSize,map.getStr("orderby"));
		map.put("column", column);
		map.put("sort", sort);
		map.put("create_uid", id);
		List<GroupBuyFormMap> list = mapper.getGroupList(map);
        pageView.setRecords(list);
        return pageView;
	}
}
