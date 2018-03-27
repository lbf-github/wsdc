package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 健康问答答案点赞记录
 * @author 陆彬峰
 * 
 */
@TableSeg(tableName = "tb_favorite_record", id = "id")
public class AnswerLikeFormMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
