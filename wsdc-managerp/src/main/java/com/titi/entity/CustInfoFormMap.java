package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 前台用户附属信息实体
 * @author 曾雄
 */
@TableSeg(tableName = "tb_user_info", id="id")
public class CustInfoFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 附近的人使用
	 */
	private double distance;


	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
