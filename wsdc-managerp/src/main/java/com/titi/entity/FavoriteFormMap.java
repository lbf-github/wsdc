package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 用户收藏
 * @author 刘放
 * 
 */
@TableSeg(tableName = "tb_favorite", id = "id")
public class FavoriteFormMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
