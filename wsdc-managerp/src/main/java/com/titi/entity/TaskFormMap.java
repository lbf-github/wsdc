package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 任务实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_task", id="id")
public class TaskFormMap extends FormMap<String,Object>{
	
	private String unit_price;

	private static final long serialVersionUID = 1L;

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	
}
