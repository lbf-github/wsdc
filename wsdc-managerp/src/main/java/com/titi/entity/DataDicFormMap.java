package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 数据字典 实体
 * @author 陆彬峰
 *
 */
@TableSeg(tableName = "tb_data_dictionary", id="id")
public class DataDicFormMap extends FormMap<String,Object>{

	private static final long serialVersionUID = 1L;

}
