package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 前台用户账户信息实体
 * @author 曾雄
 */
@TableSeg(tableName = "tb_user_account", id="id")
public class CustAccountFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	private String nickname;//用于账户下拉列表显示账户昵称

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
