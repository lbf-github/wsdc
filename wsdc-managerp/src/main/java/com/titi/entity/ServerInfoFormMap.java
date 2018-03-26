package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 系统(服务器)信息实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "cy_server_info", id="id")
public class ServerInfoFormMap extends FormMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
}