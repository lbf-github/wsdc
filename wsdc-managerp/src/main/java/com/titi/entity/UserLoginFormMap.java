package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 用户登陆记录 实体
 * @author 曾雄
 */
@TableSeg(tableName = "cy_userlogin", id="id")
public class UserLoginFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
