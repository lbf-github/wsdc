package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 产品信息实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_item_product", id="id")
public class ItemProductFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
