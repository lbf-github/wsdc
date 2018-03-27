package com.titi.entity;

import java.util.List;

/**
 * 输出参数基类
 * 
 * @author 陆彬峰
 * 
 */
public class OutputResult {

	private String result; // 编码

	private String msg; // 消息

	private List<?> resultList; // 输出json对象

	private Object resultDao; //返回单个对象


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public Object getResultDao() {
		return resultDao;
	}

	public void setResultDao(Object resultDao) {
		this.resultDao = resultDao;
	}

}
