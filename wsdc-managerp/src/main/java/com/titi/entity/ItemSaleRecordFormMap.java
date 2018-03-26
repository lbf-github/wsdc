package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 商品销售记录 实体
 * @author 曾雄
 */
@TableSeg(tableName = "tb_item_sale_record", id="id")
public class ItemSaleRecordFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
