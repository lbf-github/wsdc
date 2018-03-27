package com.titi.mapper;

import java.util.List;

import com.titi.entity.CustInfoFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 前台用户附属信息 Dao层接口
 * @author 陆彬峰
 *
 */
public interface CustInfoMapper extends BaseMapper{

	//分页查询用户附属信息
	public List<CustInfoFormMap> findCustInfoPage(CustInfoFormMap custInfoFormMap);
	
}
