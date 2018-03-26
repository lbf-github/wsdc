package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 用户健康信息 实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "tb_user_health", id="id")
public class UserHealthFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	/** --用户昵称 -- **/
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
