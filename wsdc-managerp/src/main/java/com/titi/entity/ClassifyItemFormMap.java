package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 商品分类关系表  实体
 * @author 曾雄
 */
@TableSeg(tableName = "tb_classify_item", id="id")
public class ClassifyItemFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
