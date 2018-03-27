package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 搜索记录 实体
 * @author 陆彬峰
 */
@TableSeg(tableName = "tb_search_record", id="id")
public class SearchRecordFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	private String mobile;//手机号码
	
	private String nickname;//昵称

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	

}
