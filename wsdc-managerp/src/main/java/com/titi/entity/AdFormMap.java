package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 广告 实体
 * @author 陆彬峰
 *
 */
@TableSeg(tableName = "tb_ad", id="id")
public class AdFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
}
