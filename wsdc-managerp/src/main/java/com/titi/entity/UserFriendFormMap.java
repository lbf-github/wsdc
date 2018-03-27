package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 后台用户实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_user_friend", id="id")
public class UserFriendFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
