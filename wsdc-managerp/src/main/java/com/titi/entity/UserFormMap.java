package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 后台用户实体
 * @author 曾雄
 */
@TableSeg(tableName = "cy_user", id="id")
public class UserFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
