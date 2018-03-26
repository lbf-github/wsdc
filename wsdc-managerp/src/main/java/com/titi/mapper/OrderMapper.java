package com.titi.mapper;

import java.util.List;

import com.titi.entity.OrderFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 订单信息 Dao层接口
 * @author 曾雄
 *
 */
public interface OrderMapper extends BaseMapper{

	/** --分页查询订单信息 -- **/
	public List<OrderFormMap> findOrderPage(OrderFormMap orderFormMap);
	
}
