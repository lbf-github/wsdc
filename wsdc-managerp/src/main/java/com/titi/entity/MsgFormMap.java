package com.titi.entity;

import java.sql.Timestamp;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 系统消息 实体
 * @author 陆彬峰
 *
 */
@TableSeg(tableName = "tb_msg", id="id")
public class MsgFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	//用于条件搜索   userName:后台发件人id对应用户名  nickname:收件人id对应昵称 r_yn是否已读
	private String userName;
	
	private String nickname;
	
	private Timestamp expiry_date;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Timestamp getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Timestamp expiry_date) {
		this.expiry_date = expiry_date;
	}
	
}
