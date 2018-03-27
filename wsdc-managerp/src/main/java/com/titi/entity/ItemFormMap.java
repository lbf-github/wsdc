package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 商品信息实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_item", id="id")
public class ItemFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	private int day;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
