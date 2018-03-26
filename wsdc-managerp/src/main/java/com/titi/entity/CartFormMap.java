package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 购物车信息 实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "tb_cart", id="id")
public class CartFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
	private String item_nm;//商品名称
	
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

	public String getItem_nm() {
		return item_nm;
	}

	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	
}
