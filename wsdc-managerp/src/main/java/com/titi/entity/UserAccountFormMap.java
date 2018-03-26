package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 账户信息 实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "tb_user_account", id="id")
public class UserAccountFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
