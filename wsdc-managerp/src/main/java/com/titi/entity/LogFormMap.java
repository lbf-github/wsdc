package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 系统操作日志 实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "cy_log", id="id")
public class LogFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;

}
