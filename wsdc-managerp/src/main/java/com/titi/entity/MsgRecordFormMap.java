package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 消息记录表 实体
 * @author 陆彬峰
 *
 */
@TableSeg(tableName = "tb_msg_record", id="id")
public class MsgRecordFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 1L;
	
}
