package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 用户-角色 实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "cy_user_role", id="userId")
public class UserGroupsFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
}
