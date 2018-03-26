package com.titi.mapper;

import java.util.List;

import com.titi.entity.OrderFormMap;
import com.titi.entity.RiderFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 骑手信息管理 dao层接口
 * @author 曾雄
 *
 */

public interface RiderMapper extends BaseMapper{

	/** -- 分页查询骑手信息列表 -- **/
	public List<RiderFormMap> findRiderPage(RiderFormMap riderFormMap);
	
	/** -- 分页查询关注骑手的用户订单列表-- **/
	public List<OrderFormMap> findOrderByRider(RiderFormMap riderFormMap);
	
}
