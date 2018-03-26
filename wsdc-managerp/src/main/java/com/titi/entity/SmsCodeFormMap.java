package com.titi.entity;

import com.titi.annotation.TableSeg;
import com.titi.util.FormMap;

/**
 * 验证码 实体
 * @author 曾雄
 *
 */
@TableSeg(tableName = "tb_sms_code", id="id")
public class SmsCodeFormMap extends FormMap<String,Object>{

	private static final long serialVersionUID = 1L;

}
