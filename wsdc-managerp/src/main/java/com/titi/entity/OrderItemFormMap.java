package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

import java.sql.Timestamp;

/**
 * 订单管理实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_order_item", id="id")
public class OrderItemFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	//订单时间输入起始范围
	private Timestamp startTime;
		
	private Timestamp endTime;

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}
